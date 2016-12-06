import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BingTrans
{
	private static final String url_get = "http://www.bing.com/translator";
	private static final String post_template =
			"http://www.bing.com/translator/api/Translate/TranslateArray?from=%s&to=%s";
	private static boolean verbose = true;

	private static final void printv (String msg)
	{
		if (verbose) {
			System.out.println(msg);
		}
	}

	private static HttpURLConnection openConnection (String url) throws IOException
	{
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestProperty("Host", "www.bing.com");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		conn.setRequestProperty("DNT", "1");
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setRequestProperty("Referer", "https://www.bing.com/translator/");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Pragma", "no-cache");
		conn.setRequestProperty("Cache-Control", "no-cache");

		return conn;
	}

	private static class Request
	{
		private int id;
		private String text;

		public Request (int _id, String _text)
		{
			id = _id;
			text = _text;
		}
	}

	private static class TextResponse
	{
		public int id;
		public String text;
		public String wordAlignment;
	}

	private static class Response
	{
		private String from, to;
		private ArrayList<TextResponse> items;
	}

	private static Response translate (String phrase, String to, String from)
			throws IOException
	{
		HttpURLConnection con_get = openConnection(url_get);
		con_get.setDoOutput(true);

		final int resp_code = con_get.getResponseCode();
		printv("GET response code: " + resp_code);

		// Extract cookie headers:
		List<String> cookies = new ArrayList<String>();
		for (String header : con_get.getHeaderFields().get("Set-Cookie")) {
			String[] parts = header.split(";");
			cookies.add(parts[0]);
		}

		for (String cookie : cookies) {
			printv("Cookie: " + cookie);
		}

		// Set up the POST request for actual translation:
		HttpURLConnection con_post = openConnection(
				String.format(post_template, from, to));
		con_post.setRequestProperty("Cookie", String.join("; ", cookies));
		con_post.setDoOutput(true); // POST

		// Create request and encode it as JSON:
		Gson gson = new GsonBuilder().create();
		final String str = gson.toJson(new Request(phrase.hashCode(), phrase));
		final byte[] data = str.getBytes();

		printv("JSON body: " + str);

		con_post.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		con_post.setRequestProperty("Content-Length",
				Integer.toString(data.length));
		OutputStream stream = con_post.getOutputStream();
		stream.write(data);

		// Read response from server to POST request:
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con_post.getInputStream()));
		return gson.fromJson(in, Response.class);
	}

	public static void main (String[] args)
	{
		// TODO: Add -verbose argument parsing:
		if (args.length < 2) {
			System.err.println("usage: BingTrans <text> <lang-out> [lang-in]");
			System.err.println("example: BingTrans \"hello\" sv");
			return;
		}

		// Autodetect input language by default:
		String from = "-";
		if (args.length > 2) {
			from = args[3];
		}

		try {
			final Response resp = translate(args[0], args[1], from);
			System.out.println(String.format("from: %s, to: %s, phrase: %s",
					resp.from, resp.to, resp.items.get(0).text));

		} catch (IOException e) {
			System.err.println("Communications error: " + e);
		}
	}
}

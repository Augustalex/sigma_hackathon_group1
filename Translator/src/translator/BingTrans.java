import java.net.URLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.InputStream;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BingTrans
{
	private static final String post_template = "http://www.bing.com/translator/api/Translate/TranslateArray?from=%s&to=%s";

	private static URLConnection openConnection (String url) throws IOException
	{
		URLConnection conn = new URL(url).openConnection();
		conn.setRequestProperty("Host", "www.bing.com");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		conn.setRequestProperty("DNT", "1");
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
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
		int id = 1;

		final String post_url = String.format(post_template, from, to);
		URLConnection conn = openConnection(post_url);
		conn.setDoOutput(true); // POST

		// Create request and encode it as JSON:
		Gson gson = new GsonBuilder().create();
		final byte[] data = gson.toJson(new Request(id, phrase)).getBytes();

		conn.setRequestProperty("Content-Length",
				Integer.toString(data.length));
		OutputStream stream = conn.getOutputStream();
		stream.write(data);

		// Get response from server:
		BufferedReader in = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));
		return gson.fromJson(in, Response.class);
	}

	public static void main (String[] args)
	{
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
			System.err.println("Error opening POST URL: " + e);
		}
	}
}

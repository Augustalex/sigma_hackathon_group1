package translator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class BingTranslator implements Translator
{
	private static final String url_template = "http://www.bing.com/translator/api/Translate/TranslateArray?from=%s&to=%s";

	private static URLConnection openConnection (String url) throws IOException
	{
		URLConnection conn = new URL(url).openConnection();

		conn.setRequestProperty("Host", "www.bing.com");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0 Iceweasel/38.8.0");
		conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		conn.setRequestProperty("DNT", "1");
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		conn.setRequestProperty("Referer", "https://www.bing.com/translator/");
		conn.setRequestProperty("Content-Length", "27");
		conn.setRequestProperty("Cookie", "MicrosoftApplicationsTelemetryDeviceId=765f1f03-e8eb-21da-1b2a-7a564333bddb; MicrosoftApplicationsTelemetryFirstLaunchTime=1480250425807; destLang=en; smru_list=sv; dmru_list=en; sourceDia=sv-SE; destDia=en-US; srcLang=sv; mtstkn=te5D16BUzQHwMV25YrTRmNs95G9g4w7jZClEdFiPQHnoNLvgKhYgM4tUJMfi56Or; _EDGE_S=F=1&SID=2178E9FD61D067842B5AE02760346614; _EDGE_V=1; MUID=0438DAE6A44963DE38C8D33CA5AD62C1; MUIDB=0438DAE6A44963DE38C8D33CA5AD62C1; WLS=TS=63615848926");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Pragma", "no-cache");
		conn.setRequestProperty("Cache-Control", "no-cache");

		return conn;
	}

	private class TextResponse
	{
		public int id;
		public String text;
		public String wordAlignment;
	}

	private class Response
	{
		private String from, to;
		private ArrayList<TextResponse> items;
	}

	private Response request (String from, String to, String text)
	{
		final int id = 1;
		try {
			final String post_url = String.format(url_template, from, to);
			URLConnection conn = openConnection(post_url);
			conn.setDoOutput(true); // POST
			
			OutputStream stream = conn.getOutputStream();
			stream.write(String.format("[{\"id\":%d,\"text\":\"%s\"}]", id, text).getBytes());
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			Gson gson = new GsonBuilder().create();
			return gson.fromJson(in, Response.class);

		} catch (IOException e) {
			System.err.println("Error opening POST URL: " + e);
		}

		return Response();
	}

    boolean supportsLanguage (String language)
	{
		if (language.toLowerCase() == "swedish") {
			return true;
		}

		return false;
	}

    String identifyLanguage (String word)
	{
		Response r = request(originLanguage, destinationLanguage, word);

		return r.from;
	}

    String translate (String originLanguage, String word, String destinationLanguage)
	{
		Response r = request(originLanguage, destinationLanguage, word);

		return r.text;
	}
}

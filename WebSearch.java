import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class WebSearch {

	static ArrayList<String> visited = new ArrayList<String>();

	public static void main(String[] args) throws Exception {

		// 1. seed page & download html
		// 2. extract urls and repeat

		search("http://www.lsprep.org");

	}

	public static void search(String url) throws Exception {

		visited.add(url);
        URL url_obj = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(url_obj.openStream()));

        String line;
        ArrayList<String> html = new ArrayList<String>();
        PrintWriter writer = new PrintWriter("webcode.txt", "UTF-8");

        while ((line = in.readLine()) != null) {
            if (line.contains("href") && (line.contains("http://") || line.contains("https://"))) {
            	writer.println(line);
            	int startPos = Math.max(line.indexOf("http://"), line.indexOf("https://"));
            	while (startPos != -1) {
            		int stopPos = line.indexOf("\"", startPos);
            		String result = line.substring(startPos, stopPos);
            		String lastChars = result.substring(result.length()-4);
            		if (!(lastChars.equals(".css") || lastChars.equals(".jpg") || lastChars.equals(".gif") || lastChars.contains(".js")))
            			html.add(result);
            		startPos = Math.max(line.indexOf("http://",startPos+1), line.indexOf("https://",startPos+1));
            	}
            }
        }
        in.close();
        writer.close();
        for (String address : html)
        	System.out.println(address);


        // a. parse html for urls
        // b. recursively call search for each url
        // c. add some 'depth of search' mechanism to stop the search
	}

}

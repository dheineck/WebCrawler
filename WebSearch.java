import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class WebSearch {
	
	public static void main(String[] args) throws Exception {

		// 1. seed page & download html
		// 2. extract urls and repeat

		search("http://www.lsprep.org");

	}

	public static void search(String url) throws Exception {

        URL url_obj = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(url_obj.openStream()));

        String line;
        ArrayList<String> html = new ArrayList<String>();
        PrintWriter writer = new PrintWriter("webcode.txt", "UTF-8");

        while ((line = in.readLine()) != null) {
            html.add(line);
            if (line.contains("href") && (line.contains("http://") || line.contains("https://"))) {
            	writer.println(line);
            	int startPos = line.indexOf("http://");
            	int stopPos = line.indexOf("\"", startPos);
            	String result = line.substring(startPos, stopPos);
            	writer.println(result);
            }
        }
        in.close();
        writer.close();
        //System.out.println(html);


        // a. parse html for urls
        // b. recursively call search for each url
        // c. add some 'depth of search' mechanism to stop the search
	}

}

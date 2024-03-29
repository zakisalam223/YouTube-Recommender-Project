package backend;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class SearchService {

    private static String youtubeSearchPageBase = "https://www.youtube.com/results?search_query=";
    private static String youtubeSearchQuery;
    private TextFile text;
    private String path;
    private static ArrayList<String> titlesFromSearch;
    private static ArrayList<String> videoKeys;


    public static String getYoutubeSearchQuery() throws IOException {
        youtubeSearchQuery = TextFile.getRandomKeyWord();
        return youtubeSearchQuery;
    }
    
    public static ArrayList<String> getVideoKeys() {
        ArrayList<String> keys = new ArrayList<>();

        try {
            URL url = new URL(youtubeSearchPageBase + getYoutubeSearchQuery());
            Scanner sc = new Scanner(new InputStreamReader(url.openStream()));

            String s = "(url\":\"/watch\\?v=.+?((?=\")))";
            Pattern p = Pattern.compile(s);
            String line = "";
            int i = 0;
            String key = "";

            while (sc.hasNext()) {
                line = sc.next();
                Matcher m = p.matcher(line);
                if (m.find()) {
                    key = m.group().replaceAll("\\\\.*+", "");
                    key = key.replace("url\":\"/watch?v=", "");
                    if (!keys.contains(key)) {
                        keys.add(key);
                    }
                    i++;
                }
            }

        } catch (IOException e) {
            System.out.println("getting Youtube keys error");
        }

        return keys;
    }

}

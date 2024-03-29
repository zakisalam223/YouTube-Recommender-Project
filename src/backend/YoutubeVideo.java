package backend;

import frontend.Display;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javax.imageio.ImageIO;

public class YoutubeVideo {

    private static String videoKey;
    private static String baseUrl = "https://www.youtube.com/watch?v=";
    private static ArrayList<String> videoTags;
    private static String videoTitle;

    public static BufferedImage getVideoThumbnail() throws MalformedURLException, IOException {
        String thumbnailURL = "http://img.youtube.com/vi/" + getOldVideoKey() + "/0.jpg";
        URL url = new URL(thumbnailURL);
        BufferedImage image = ImageIO.read(url);
        return image;
    }

    public static String getVideoKey(String filter) throws IOException { // from SearchService's ArrayList of video keys

        boolean looking = true;
        ArrayList<String> a = SearchService.getVideoKeys();

        while (looking) {

            videoKey = a.get((int) (Math.random() * a.size()));
            if (Display.getFilter().equals("pop") && getViews(videoKey) > 250000) {
                looking = false;
            }
            if (Display.getFilter().equals("under") && getViews(videoKey) < 50000) {
                looking = false;
            }
            if (Display.getFilter().equals("none")) {
                return videoKey;
            }
        }
        return videoKey;
    }

    public static String getOldVideoKey() throws IOException {

        if (videoKey != null) {
            return videoKey;
        } else {
            return getVideoKey(Display.getFilter());
        }
    }

    public static String getVideoTitle(String key) throws MalformedURLException, IOException { // from video key
        String title = "";

        URL url = new URL(baseUrl + key);

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        Pattern p = Pattern.compile("<title>.*?[?=<]");
        String line;
        while ((line = br.readLine()) != null) {
            Matcher m = p.matcher(line);
            if (m.find()) {
                title = m.group();
            }
        }
        // cleaning title
        title = title.replaceAll("<title>", "");
        title = title.replaceAll(" - YouTube<", "");

        // for special characters
        title = title.replaceAll("&quot;", "\"");
        title = title.replaceAll("&apos;", "'");
        title = title.replaceAll("&amp;", "&");
        title = title.replaceAll("&lt;", "<");
        title = title.replaceAll("&gt;", ">");

        title = title.replaceAll("&#34;", "\"");
        title = title.replaceAll("&#39;", "'");
        title = title.replaceAll("&#38;", "&");
        title = title.replaceAll("&#60;", "<");
        title = title.replaceAll("&#62;", ">");

        return title;
    }

    public static ArrayList<String> getTags() {

        String[] arr = null;
        ArrayList<String> tags = new ArrayList<>();

        try {
            URL url = new URL(baseUrl + getOldVideoKey());
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            Pattern p = Pattern.compile("keywords([^\\]>])*");
            String line;
            while ((line = br.readLine()) != null) {
                Matcher m = p.matcher(line);
                if (m.find()) {
                    arr = m.group().split(", ");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("getting Youtube tags error");
        }

        // cleaning the array of tags
        arr[0] = arr[0].replace("keywords\" content=\"", "");

        arr[arr.length - 1] = arr[arr.length - 1].replace("\"", "");

        for (int i = 0; i < arr.length; i++) {

            tags.add(arr[i]);
        }
        return tags;
    }

    public static int getViews(String key) throws MalformedURLException, IOException {
        String views = "";

        URL url = new URL(baseUrl + key);

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        Pattern p = Pattern.compile("\"viewCount\":\\{\"simpleText\":\".*?[?=\\}]");
        String line;
        while ((line = br.readLine()) != null) {
            Matcher m = p.matcher(line);
            if (m.find()) {
                views = m.group();
            }
        }

        // cleaning views
        views = views.replaceAll("\"viewCount\":\\{\"simpleText\":\"", "");
        views = views.replaceAll(" views\"\\}", "");
        views = views.replaceAll(",", "");

        try {
            int viewsNumber = Integer.parseInt(views);
            return viewsNumber;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}

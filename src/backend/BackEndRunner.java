package backend;

import frontend.Display;
import frontend.YoutubeViewer;
import java.io.*;
import java.util.*;

public class BackEndRunner {

    public static void main(String[] args) throws IOException {

        System.out.println("What is your name? ");

        TextFile.setFilePath(TextFile.getUserInput());
        TextFile newUser = new TextFile();

        String content[] = newUser.fileContent;

        System.out.println("What is something that interests you? ");
        //  newUser.saveKeyWord();

        /// for testing
        //  System.out.println(SearchService.getYoutubeSearchQuery());
        // SearchService.getVideoKeys();
        // for testing
        YoutubeVideo video = new YoutubeVideo();
        //  System.out.println(video.getTags());
        // System.out.println(YoutubeVideo.getVideoKey());

       // String videoKey = YoutubeVideo.getVideoKey();
       // System.out.println(YoutubeVideo.getVideoTitle(videoKey));

        System.out.println(YoutubeVideo.getViews("rUknMQUfXrU"));

    }
}

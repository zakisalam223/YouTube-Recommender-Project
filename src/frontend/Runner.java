package frontend;

import backend.TextFile;
import backend.YoutubeVideo;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import java.io.File;
import java.io.IOException;
import javax.swing.UnsupportedLookAndFeelException;

public class Runner {

    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException {

        Display.setLooks();

        TextFile user = new TextFile();
        TextFile userSavedVideos = new TextFile();

        Display display = new Display(user, userSavedVideos);

        boolean displayRunning = true;
        boolean gotKeys = false;

        while (displayRunning) {
            System.out.print("");
            gotKeys = display.getGotKeys();
            if (gotKeys) {
                YoutubeViewer.playVideo(YoutubeVideo.getOldVideoKey());

                NativeInterface.runEventPump();

                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        NativeInterface.close();
                    }
                }));

            }
        }

    }

}

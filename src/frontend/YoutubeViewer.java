package frontend;

import backend.YoutubeVideo;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class YoutubeViewer {

    public static JPanel getBrowserPanel(String key) {
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        webBrowser.setBarsVisible(false);
        webBrowser.navigate("https://www.yout-ube.com/watch?v=" + key);
        return webBrowserPanel;
    }

    public static void playVideo(String key) {

        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Your recommended video");
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(YoutubeViewer.getBrowserPanel(key), BorderLayout.CENTER);
                frame.setSize(800, 600);
                frame.setResizable(true);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });

       
    }
}

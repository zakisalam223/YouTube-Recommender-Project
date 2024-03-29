package frontend;

import backend.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class Display extends javax.swing.JFrame implements ActionListener {

    private static ArrayList<String> displayedVideos;
    private static JLabel l2;

    private static TextFile newUser;
    private static TextFile newUserSavedVideos;
    private static String name;

    private static JFrame f;
    private static JFrame frame;
    private static JLabel success;
    private static JTextField nameField;
    private static JTextField keywordField;
    private static JButton confirmNameButton;
    private static JButton confirmKeyWordButton;
    private static JLabel enterNameLabel;
    private static JLabel keywordPrompt;
    private static JLabel keywordAddedText;

    private static JPanel panel;

    private static JLabel titleLabel;
    private static String videoURL = "";

    private static JLabel yesOrNoPrompt;
    private static JButton yesButton;
    private static JButton noButton;
    private static JLabel yesOrNoResponse;

    private static JButton recommendButton;

    private static JButton saveVideoButton;
    private static JButton playVideoButton;

    private static boolean gotKeys;
    private static boolean firstRun;

    private static JButton clearKeywordsButton;
    private static JLabel clearText;

    private static boolean keywordsEmpty;

    private static JButton viewSavedVideosButton;

    private static BufferedImage thumbnailBuffer;
    private static JLabel thumbnailLabel;
    private static ImageIcon thumbnailIcon;

    private static JButton underratedButton;
    private static JButton popularButton;
    private static JButton noFilterButton;
    private static JLabel filterText;

    private static String filter;

    private static ImageIcon icon;

    public Display(TextFile user, TextFile userSavedVideos) throws IOException {

        icon = new ImageIcon("ZakiIcon.png");

        filter = "none";

        underratedButton = new JButton("Underrated");
        underratedButton.setForeground(Color.cyan);
        underratedButton.setBackground(Color.LIGHT_GRAY);
        underratedButton.addActionListener(this);

        popularButton = new JButton("Popular");
        popularButton.setForeground(Color.YELLOW);
        popularButton.setBackground(Color.LIGHT_GRAY);
        popularButton.addActionListener(this);

        noFilterButton = new JButton("None");
        noFilterButton.setForeground(Color.WHITE);
        noFilterButton.setBackground(Color.LIGHT_GRAY);
        noFilterButton.addActionListener(this);

        filterText = new JLabel("Search filter: none");
        filterText.setForeground(Color.WHITE);

        displayedVideos = new ArrayList<>();
        l2 = new JLabel();

        firstRun = true;
        gotKeys = false;

        newUser = user;
        newUserSavedVideos = userSavedVideos;

        f = new JFrame();
        frame = new JFrame();

        success = new JLabel();
        success.setForeground(Color.white);
        success.setBounds(10, 110, 1000, 25);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 165, 25);
        nameField.setColumns(15);

        keywordField = new JTextField();
        keywordField.setBounds(100, 20, 165, 30);
        keywordField.setColumns(15);

        confirmNameButton = new JButton("Confirm");
        confirmNameButton.setBackground(Color.LIGHT_GRAY);
        confirmNameButton.addActionListener(this);

        confirmKeyWordButton = new JButton("Confirm");
        confirmKeyWordButton.setBackground(Color.LIGHT_GRAY);
        confirmKeyWordButton.addActionListener(this);

        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        noButton.setBackground(Color.LIGHT_GRAY);
        yesButton.setBackground(Color.LIGHT_GRAY);
        yesButton.addActionListener(this);
        noButton.addActionListener(this);

        recommendButton = new JButton("Recommend me something else!");
        recommendButton.setBackground(Color.LIGHT_GRAY);
        recommendButton.addActionListener(this);

        saveVideoButton = new JButton("Save this video");
        saveVideoButton.setBackground(Color.LIGHT_GRAY);
        saveVideoButton.addActionListener(this);

        playVideoButton = new JButton("Play Video");
        playVideoButton.setBackground(Color.LIGHT_GRAY);
        playVideoButton.addActionListener(this);

        clearKeywordsButton = new JButton("Clear interests");
        clearKeywordsButton.setBackground(Color.DARK_GRAY);
        clearKeywordsButton.setForeground(Color.ORANGE);
        clearKeywordsButton.addActionListener(this);
        clearText = new JLabel("\"Clear interests\" will remove everything from your list of interests, but you won't lose the videos you saved.");
        clearText.setForeground(Color.ORANGE);

        viewSavedVideosButton = new JButton("View Saved Videos");
        viewSavedVideosButton.setBackground(Color.DARK_GRAY);
        viewSavedVideosButton.setForeground(Color.MAGENTA);
        viewSavedVideosButton.addActionListener(this);

        yesOrNoPrompt = new JLabel("Do you like this recommendation?");
        yesOrNoResponse = new JLabel();
        yesOrNoPrompt.setForeground(Color.white);
        yesOrNoPrompt.setForeground(Color.white);

        enterNameLabel = new JLabel(" Welcome to Viewza! What is your name?");
        enterNameLabel.setForeground(Color.white);

        keywordPrompt = new JLabel("What is something that interests you?");
        keywordPrompt.setForeground(Color.white);

        keywordAddedText = new JLabel();
        keywordAddedText.setForeground(Color.white);
        keywordAddedText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(100, 300, 700, 300));

        titleLabel = new JLabel();
        titleLabel.setForeground(Color.white);

        panel.add(nameField, BorderLayout.NORTH);

        panel.add(confirmNameButton);

        panel.add(enterNameLabel);

        panel.add(success);
        success.setVisible(false);

        panel.add(keywordPrompt);
        keywordPrompt.setVisible(false);

        f.setIconImage(icon.getImage());
        frame.setIconImage(icon.getImage());
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Viewza");
        frame.setSize(1000, 1000);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        try {

            if (arg0.getSource() == confirmNameButton && !nameField.getText().isBlank()) {
                nameEntered();
            }

            if (arg0.getSource() == confirmKeyWordButton && !keywordField.getText().isBlank()) {
                keywordEntered();
                recommend();
            }

            if (arg0.getSource() == saveVideoButton) {
                saveVideoSelected();
            }

            if (arg0.getSource() == playVideoButton) {
                playVideoSelected();
            }

            if (arg0.getSource() == yesButton) {
                yesSelected();
            }
            if (arg0.getSource() == noButton) {

                noSelected();
            }

            if (arg0.getSource() == recommendButton) {
                recommend();

            }

            if (arg0.getSource() == clearKeywordsButton) {
                clearKeywords();

            }

            if (arg0.getSource() == viewSavedVideosButton) {
                viewSavedVideos();
            }

            if (arg0.getSource() == popularButton) {
                popularButtonSelected();
            }
            if (arg0.getSource() == underratedButton) {
                underratedButtonSelected();
            }
            if (arg0.getSource() == noFilterButton) {
                noFilterButtonSelected();
            }

        } catch (IOException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void nameEntered() throws IOException {

        TextFile.setFilePath(nameField.getText());
        name = nameField.getText();

        success.setText(TextFile.getSuccessText());

        newUser.createFile(false);
        newUserSavedVideos.createFile(true);

        keywordPrompt.setVisible(true);
        saveVideoButton.setVisible(false);

        panel.add(keywordField);
        panel.add(confirmKeyWordButton);
        panel.add(clearKeywordsButton);

        panel.add(clearText);
        panel.add(viewSavedVideosButton);

        panel.add(filterText);
        panel.add(noFilterButton);
        panel.add(popularButton);
        panel.add(underratedButton);

        success.setVisible(true);
        nameField.setVisible(false);
        enterNameLabel.setVisible(false);
        confirmNameButton.setVisible(false);
        clearKeywordsButton.setVisible(true);
        clearText.setVisible(true);
        viewSavedVideosButton.setVisible(true);
        filterText.setVisible(true);
        noFilterButton.setVisible(true);
        popularButton.setVisible(true);
        underratedButton.setVisible(true);

    }

    public static void keywordEntered() throws IOException {

        keywordsEmpty = false;

        newUser.saveToFile(keywordField.getText());

        panel.add(keywordAddedText);
        String s = ("<html>\"" + keywordField.getText() + "<html>\" has been added to your list of interests.");
        keywordAddedText.setText(s);

        keywordField.setText(""); // reset the keyword field

        clearText.setText("\"Clear interests\" will remove everything from your list of interests, but you won't lose the videos you saved.");
        clearText.setForeground(Color.ORANGE);
    }

    public static void recommend() throws IOException {
        if (keywordsEmpty) {
            clearText.setText("I need to know what you're interested in before I can recommend something!");
            clearText.setForeground(Color.pink);

        } else {

            clearText.setForeground(Color.ORANGE);
            gotKeys = false;

            panel.add(titleLabel);

            SearchService.getVideoKeys();
            String videoKey = YoutubeVideo.getVideoKey(filter);
            String videoTitle = YoutubeVideo.getVideoTitle(videoKey);

            String titleLabelText = "The video I recommend is titled: " + videoTitle;
            titleLabel.setText(titleLabelText);

            panel.add(yesOrNoPrompt);
            panel.add(yesButton);
            panel.add(noButton);
            panel.add(playVideoButton);

            yesOrNoResponse.setVisible(true);
            panel.add(yesOrNoResponse);

            yesOrNoPrompt.setVisible(true);
            yesButton.setVisible(true);
            noButton.setVisible(true);
            yesOrNoResponse.setVisible(false);
            recommendButton.setVisible(false);
            playVideoButton.setVisible(true);

            panel.add(saveVideoButton);
            saveVideoButton.setVisible(true);

            thumbnailBuffer = YoutubeVideo.getVideoThumbnail();
            thumbnailLabel = new JLabel(new ImageIcon(thumbnailBuffer));
            panel.add(thumbnailLabel);
            thumbnailLabel.setVisible(true);
        }
    }

    public static void yesSelected() throws IOException {
        gotKeys = false;

        yesOrNoPrompt.setVisible(false);
        yesButton.setVisible(false);
        noButton.setVisible(false);
        recommendButton.setVisible(true);
        playVideoButton.setVisible(false);

        yesOrNoResponse.setVisible(true);
        yesOrNoResponse.setText("Got it. I'll give you more recommendations like this one.");
        clearText.setText("\"Clear interests\" will remove everything from your list of interests, but you won't lose the videos you saved.");
        clearText.setForeground(Color.ORANGE);
        for (String tag : YoutubeVideo.getTags()) {
            newUser.saveToFile(tag);

        }

        panel.add(recommendButton);
        panel.remove(thumbnailLabel);
    }

    public static void noSelected() {
        gotKeys = false;

        playVideoButton.setVisible(false);
        yesOrNoPrompt.setVisible(false);
        yesButton.setVisible(false);
        noButton.setVisible(false);
        recommendButton.setVisible(true);

        yesOrNoResponse.setVisible(true);
        yesOrNoResponse.setText("Noted. I won't recommend you videos like this.");

        panel.add(recommendButton);
        panel.remove(thumbnailLabel);
    }

    public static void playVideoSelected() throws IOException {
        gotKeys = true;
        playVideoButton.setVisible(false);

        if (!firstRun) {
            YoutubeViewer.playVideo(YoutubeVideo.getOldVideoKey());
            System.out.println(YoutubeVideo.getOldVideoKey());
        }
        firstRun = false;
    }

    public static void saveVideoSelected() throws IOException {
        String key = YoutubeVideo.getOldVideoKey();
        newUserSavedVideos.saveToFileVideos(key);

    }

    public static void clearKeywords() throws IOException {
        keywordsEmpty = true;
        newUser.clearFile();
        clearText.setText("Your interests have been cleared.");
        clearText.setForeground(Color.ORANGE);
    }

    public static void viewSavedVideos() throws IOException {

        if (!f.isShowing()) {

            if (TextFile.readFile(name + "savedVideos").isBlank()) {

                l2.setText("You haven't saved any videos yet. Once you do, they'll appear here");
                l2.setVisible(true);
                f.add(l2);
                f.setSize(700, 200);

            }
            f.setTitle("Your saved videos");
            f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            f.setResizable(false);
            f.setLocationByPlatform(true);
            f.setVisible(true);
            f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
            if (!TextFile.readFile(name + "savedVideos").isBlank()) {
                f.remove(l2);

                f.setSize(700, 900);

                ArrayList<String> videos = new ArrayList<>();

                String[] videosArray = TextFile.readFile(name + "savedVideos").split(",");
                videos.addAll(Arrays.asList(videosArray));

                ArrayList<JButton> buttonList = new ArrayList<>();

                for (String video : videos) {

                    if (!displayedVideos.contains(video)) {

                        displayedVideos.add(video);

                        JLabel l = new JLabel(YoutubeVideo.getVideoTitle(video));

                        JButton b = new JButton("Play video");
                        b.setBackground(Color.LIGHT_GRAY);
                        buttonList.add(b);

                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent arg0) {
                                YoutubeViewer.playVideo(video);
                            }
                        });

                        f.add(l);
                        f.add(b);
                    }
                }
                f.setSize(f.getPreferredSize());
            }
        }
    }

    public static void popularButtonSelected() {
        filter = "pop";
        filterText.setText("Search filter: Popular");
        filterText.setForeground(Color.yellow);
    }

    public static void underratedButtonSelected() {
        filter = "under";
        filterText.setText("Search filter: Underrated");
        filterText.setForeground(Color.cyan);
    }

    public static void noFilterButtonSelected() {
        filter = "none";
        filterText.setText("Search filter: none");
        filterText.setForeground(Color.white);
    }

    public static String getFilter() {
        return filter;
    }

    public static boolean getGotKeys() {
        return gotKeys;
    }

    public static void setLooks() {
        UIManager.put("Label.font", new FontUIResource(new Font("Arial", Font.PLAIN, 20)));
        UIManager.put("Button.font", new FontUIResource(new Font("Arial", Font.BOLD, 20)));
        UIManager.put("TextField.font", new FontUIResource(new Font("Arial", Font.PLAIN, 20)));

    }
}

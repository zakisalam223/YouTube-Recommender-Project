package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TextFile {

    public static String successText;
    public static String path;
    public String tempFileContent;
    public String[] fileContent;
    public File f;

    public TextFile() {

    }

    public void createFile(boolean isForSavedVideos) throws IOException {
        String name = getFilePath().replaceAll(".txt", "");
        if (isForSavedVideos) {
            name = name + "savedVideos";
        }
        try {
            File f = new File(name);
            if (f.createNewFile()) {
                successText = getSuccessText();
            } else {
                successText = getSuccessText();
            }
        } catch (IOException e) {
            successText = ("An error has occurred with creating/opening \'" + name + "\'.");
        }

        tempFileContent = TextFile.readFile(name);

        fileContent = tempFileContent.split(",");
    }

    public void saveToFile(String input) throws IOException {

        input = input.replaceAll("\\s+", "");

        if (!tempFileContent.contains(input)) {
            if (!tempFileContent.isBlank()) {
                tempFileContent = tempFileContent + "," + input;
            } else {
                tempFileContent = input;
            }
        }

        File f = new File(path);
        FileWriter fw = new FileWriter(f);

        fw.write(tempFileContent);
        fileContent = tempFileContent.split(",");
        fw.close();
    }

    public void saveToFileVideos(String input) throws IOException {

        String videoPath = path + "savedVideos";

        if (!tempFileContent.contains(input)) {
            if (!tempFileContent.isBlank()) {
                tempFileContent = tempFileContent + "," + input;
            } else {
                tempFileContent = input;
            }
        }
        File f = new File(videoPath);
        FileWriter fw = new FileWriter(f);

        fw.write(tempFileContent);
        fileContent = tempFileContent.split(",");
        fw.close();
    }

    public static String readFile(String path) throws FileNotFoundException, IOException {
        File f = new File(path);
        FileInputStream fis = new FileInputStream(f);

        byte[] b = new byte[(int) f.length()];
        fis.read(b);

        String content = new String(b);

        return content;
    }
    
    public void clearFile() throws IOException{
        tempFileContent = "";
        File f = new File(path);
        FileWriter fw = new FileWriter(f);
        fw.write("");
        fw.close();
    }

    // testing in backend runner
    public static String getUserInput() {

        Scanner input = new Scanner(System.in);
        String word = input.nextLine();

        return word;
    }

    public void saveKeyWord() throws IOException {
        saveToFile(getUserInput());
    }

    public static String getRandomKeyWord() throws IOException {
        String s = TextFile.readFile(path);
        String[] arr = s.split(",");
        return arr[(int) (Math.random() * arr.length)];

    }

    public static void setFilePath(String p) {
        p = p.toLowerCase();
        path = p;

    }

    public static String getFilePath() {
        return path;
    }

    public static String getSuccessText() {
        String name = getFilePath();
        successText = ("Hello, " + name + ".");

        return successText;
    }

}

package biz.itehnika;

import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "./task-2.txt")
public class TextContainer {
    String str = "Write a class TextContainer that contains a string. Using the annotation " +
                 "mechanism, specify: 1) in which file the text should be saved, 2) the method " +
                 "that will save. Write a Saver class that will save a field of the " +
                 "TextContainer class to the specified file.";

    @Saver
    public void save(String path) {
        try(FileWriter writer = new FileWriter(path)){
          writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

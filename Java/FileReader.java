import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader implements ActionListener {
    private final JTextField textField;
    public FileReader(JTextField textField) {
        this.textField = textField;
    }
    Examination examination = new Examination();
    @Override
    public void actionPerformed(ActionEvent e1) {
        String filename = textField.getText().trim();
        if (!filename.toLowerCase().endsWith(".txt") &&
                !filename.toLowerCase().endsWith(".text")) {
            filename += ".txt";
        }
        if(Files.exists(Paths.get(filename))) {
            if (examination.isValidPath(filename)) {
                try (Stream<String> stream = Files.lines(Paths.get(filename))) {
                    JOptionPane.showMessageDialog(null, stream.collect(Collectors.joining("")));
                } catch (IOException e) {
                    System.out.println("Ошибка!");
                }
            } else {
                System.out.println("Ошибка! Имя файла содержит запрещённые символы!");
            }
        } else {
            System.out.println("Ошибка! Файла с таким именем не существует!");
        }
    }
}

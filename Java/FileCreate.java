import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileCreate implements ActionListener {
    Path path;
    Examination examination = new Examination();

    public FileCreate(JTextField textField) {
        this.textField = textField;
    }
    private final JTextField textField;

    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = textField.getText().trim();
        try {
            if (!filename.toLowerCase().endsWith(".txt") &&
                    !filename.toLowerCase().endsWith(".text")) {
                filename += ".txt";
            }
            if (examination.isValidPath(filename)) {
                path = Paths.get(filename);
                Files.createFile(path);
                System.out.println("Файл " + filename + " успешно создан!");
                JOptionPane.showMessageDialog(null, "Файл " + filename +" успешно создан", "Создание файла", JOptionPane.PLAIN_MESSAGE);
            } else {
                System.out.println("Неправильно введено имя файла " + filename);
            }
        }catch (IOException e1){
            System.out.println("Ошибка!");

        }

    }
}

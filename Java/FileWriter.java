import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileWriter implements ActionListener {
    private final JTextField textField;
    public FileWriter(JTextField textField) {
        this.textField = textField;
    }

    Examination examination = new Examination();
    @Override
    public void actionPerformed(ActionEvent e){
        String filename = textField.getText().trim();
        if (!filename.toLowerCase().endsWith(".txt") &&
                !filename.toLowerCase().endsWith(".text")) {
            filename += ".txt";
        }
        try {
            if (Files.exists(Paths.get(filename))) {
                if (examination.isValidPath(filename)) {
                    System.out.println("Введите текст: ");
                    String text = JOptionPane.showInputDialog(
                            null,
                            "Введите текст: ",
                            "Запись/Перезапись файла",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    Path path = Paths.get(filename);
                    Files.writeString(path, text, StandardCharsets.UTF_8);
                    System.out.println("Текст записан!");
                } else {
                    System.out.println("Неправильное имя файла");
                }
            } else {
                System.out.println("Файла с таким именем не существует");
            }
        } catch (IOException e1) {
            System.out.println("Ошибка! ");
        }
    }
}

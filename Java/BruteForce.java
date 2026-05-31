import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BruteForce implements ActionListener {
    private final JTextField textField;

    public BruteForce(JTextField textField) {
        this.textField = textField;
    }

    Examination examination = new Examination();
    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = textField.getText().trim();
        if (!filename.toLowerCase().endsWith(".txt") &&
                !filename.toLowerCase().endsWith(".text")) {
            filename += ".txt";
        }
        int key = 0;
        if (Files.exists(Paths.get(filename))) {
            if (examination.isValidPath(filename)) {
                for (int i = 0; i < 33; i++) {
                    StringBuilder result = new StringBuilder();
                    try {
                        for (char character : Files.readString(Paths.get(filename), StandardCharsets.UTF_8).toCharArray()) {
                            if (Character.isUpperCase(character)) {
                                if (character >= 'A' && character <= 'Z') {
                                    result.append((char) ((character - 'A' - key + 26) % 26 + 'A'));
                                } else if (character >= 'А' && character <= 'Я') {
                                    result.append((char) ((character - 'А' - key + 32) % 32 + 'А'));
                                } else if (character == 'Ё') {
                                    result.append((key % 2 == 0) ? 'Ё' : 'Е');
                                } else {
                                    result.append(character);
                                }
                            } else if (Character.isLowerCase(character)) {
                                if (character >= 'a' && character <= 'z') {
                                    result.append((char) ((character - 'a' - key + 26) % 26 + 'a'));
                                } else if (character >= 'а' && character <= 'я') {
                                    result.append((char) ((character - 'а' - key + 32) % 32 + 'а'));
                                } else if (character == 'ё') {
                                    result.append((key % 2 == 0) ? 'ё' : 'е');
                                } else {
                                    result.append(character);
                                }
                            } else {
                                result.append(character);
                            }
                        }
                        JOptionPane.showMessageDialog(null, result);
                        key++;
                    } catch (IOException e1) {
                        System.out.println("Ошибка! ");
                    }
                }
            } else {
                System.out.println("Ошибка! Имя файла содержит запрещённые символы!");
            }
        } else {
            System.out.println("Ошибка! Файла с таким именем не существует!");
        }
    }
}
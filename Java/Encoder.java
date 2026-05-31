import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Encoder implements ActionListener {
    private final JTextField textField;
    int key;

    public Encoder(JTextField textField) {
        this.textField = textField;
    }
    StringBuilder result = new StringBuilder();
    Examination examination = new Examination();
    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = textField.getText().trim();
        if (!filename.toLowerCase().endsWith(".txt") &&
                !filename.toLowerCase().endsWith(".text")) {
            filename += ".txt";
        }
        if (Files.exists(Paths.get(filename))) {
            if (examination.isValidPath(filename)) {
                String keys = JOptionPane.showInputDialog(
                        null,
                        "Введите ключ: ",
                        "Зашифровать файл",
                        JOptionPane.PLAIN_MESSAGE
                );
                if (keys != null) {
                    // Проверка регулярным выражением: содержит ли строка только цифры
                    if (keys.matches("\\d+")) {
                        key = Integer.parseInt(keys);
                        // Ваш дальнейший код обработки ключа...
                        JOptionPane.showMessageDialog(null, "Ключ принят: " + key);
                    } else {
                        // Ошибка, если введены буквы или символы
                        JOptionPane.showMessageDialog(null, "Ошибка! Вводить можно только цифры.", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    }
                }
                key = Math.abs(key) % 26;
                try {
                    for (char character : Files.readString(Paths.get(filename), StandardCharsets.UTF_8).toCharArray()) {
                        if (Character.isUpperCase(character)) {
                            if (character >= 'A' && character <= 'Z') {
                                result.append((char) ((character - 'A' + key) % 26 + 'A'));
                            } else if (character >= 'А' && character <= 'Я') {
                                result.append((char) ((character - 'А' + key) % 32 + 'А'));
                            } else if (character == 'Ё') {
                                result.append((key % 2 == 0) ? 'Ё' : 'Е');
                            } else {
                                result.append(character);
                            }
                        } else if (Character.isLowerCase(character)) {
                            if (character >= 'a' && character <= 'z') {
                                result.append((char) ((character - 'a' + key) % 26 + 'a'));
                            } else if (character >= 'а' && character <= 'я') {
                                result.append((char) ((character - 'а' + key) % 32 + 'а'));
                            } else if (character == 'ё') {
                                result.append((key % 2 == 0) ? 'ё' : 'е');
                            } else {
                                result.append(character);
                            }
                        } else {
                            result.append(character);
                        }
                    }
                    Files.writeString(Paths.get(filename), result.toString());
                    System.out.println("Файл успешно зашифрован! ");
                } catch (IOException e1) {
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


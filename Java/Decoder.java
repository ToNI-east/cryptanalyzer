import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Decoder {
    public void decrypt(String fileName) {
        Scanner scanner = new Scanner(System.in);
        Examination examination = new Examination();
        StringBuilder result = new StringBuilder();
        if (Files.exists(Paths.get(fileName))) {
            if (examination.isValidPath(fileName)) {
                System.out.println("Введите ключ: ");
                int key = scanner.nextInt();
                key = Math.abs(key) % 26;
                try {
                    for (char character : Files.readString(Paths.get(fileName), StandardCharsets.UTF_8).toCharArray()) {
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
                    Files.writeString(Paths.get(fileName), result.toString());
                    System.out.println("Файл успешно расшифрован! ");
                } catch (IOException e) {
                    System.out.println("Ошибка!");
                }
            } else {
                System.out.println("Ошибка! Имя файла содержит запрещённые символы!");
            }
        }else {
            System.out.println("Ошибка! Файла с таким именем не существует!");
        }
    }
}

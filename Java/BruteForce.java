import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class BruteForce {
    public void bruteForce() {
        Scanner scanner = new Scanner(System.in);
        Examination examination = new Examination();
        System.out.println("Введите имя файла: ");
        String fileName = scanner.nextLine();
        int key = 0;
        if (Files.exists(Paths.get(fileName))) {
            if (examination.isValidPath(fileName)) {
                for (int i = 0; i < 33; i++) {
                    StringBuilder result = new StringBuilder();
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
                        System.out.println(result);
                        key++;
                    } catch (IOException e) {
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
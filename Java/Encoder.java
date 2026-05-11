import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encoder {
    public void encode(String fileName) {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        Examination examination = new Examination();
        if (Files.exists(Paths.get(fileName))) {
            if (examination.isValidPath(fileName)) {
                System.out.println("Введите ключ: ");
                int key = scanner.nextInt();
                try {
                    for (char character : Files.readString(Paths.get(fileName), StandardCharsets.UTF_8).toCharArray()) {
                        result.append((char) (character + key));
                    }
                    Files.writeString(Paths.get(fileName), result.toString());
                    System.out.println("Файл успешно зашифрован! ");
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

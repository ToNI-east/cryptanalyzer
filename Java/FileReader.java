import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileReader {
    Scanner scanner = new Scanner(System.in);
    Examination examination = new Examination();
public void fileRead() {
    System.out.println("Введите имя файла: ");
    String filename = scanner.nextLine();
    if(Files.exists(Paths.get(filename))) {
        if (examination.isValidPath(filename)) {
            try (Stream<String> stream = Files.lines(Paths.get(filename))) {
                System.out.println();
                stream.forEach(System.out::println);
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

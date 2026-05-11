import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileWriter{
    Scanner scanner = new Scanner(System.in);
    Examination examination = new Examination();
    public void fileWriter(){
        System.out.println("Введите имя файла: ");
        String filename = scanner.nextLine();
        if(Files.exists(Paths.get(filename))){
        if (examination.isValidPath(filename)) {
            System.out.println("Введите текст: ");
            String text = scanner.nextLine();
            try {
                Path path = Paths.get(filename);
                Files.writeString(path, text, StandardCharsets.UTF_8);
                System.out.println("Текст записан!");
            } catch (IOException e) {
                System.out.println("Ошибка!");
            }
        } else {
            System.out.println("Неправильное имя файла");
        }
        }else {
            System.out.println("Файла с таким именем не существует");
        }
    }
}

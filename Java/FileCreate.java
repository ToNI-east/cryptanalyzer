import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileCreate {
    Path path;
    Scanner scanner = new Scanner(System.in);
    Examination examination = new Examination();
    public void fileCreate(){
        try {
            String filename = scanner.nextLine();
            if (examination.isValidPath(filename)) {
                path = Paths.get(filename);
                Files.createFile(path);
                System.out.println("Файл " + filename + " успешно создан!");
            } else {
                System.out.println("Неправильно введено имя файла");
            }
        }catch (IOException e){
            System.out.println("Ошибка!");
        }
    }
}

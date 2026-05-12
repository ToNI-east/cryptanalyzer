import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encoder {
    private static final char[] ALPHABET_RU = {
        'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
        'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф',
        'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'
    };
    private static final char[] ALPHABET_EN = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'
    };

    public void encode(String fileName) {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        Examination examination = new Examination();
        if (Files.exists(Paths.get(fileName))) {
            if (examination.isValidPath(fileName)) {
                System.out.println("Введите ключ: ");
                int key = scanner.nextInt();
                try {
                    int ruSize = ALPHABET_RU.length;
                    int enSize = ALPHABET_EN.length;
                    key = key % 33;
                    if (key < 0) key += 33;
                    for (char character : Files.readString(Paths.get(fileName), StandardCharsets.UTF_8).toCharArray()) {
                        char lowerC = Character.toLowerCase(character);
                        boolean isUpper = Character.isUpperCase(character);
                        int index = findIndex(ALPHABET_RU, lowerC);
                        if (index != -1) {
                            int newIndex = (index + key) % ruSize;
                            char newChar = ALPHABET_RU[newIndex];
                            result.append(isUpper ? Character.toUpperCase(newChar) : newChar);
                            continue;
                        }
                        index = findIndex(ALPHABET_EN, lowerC);
                        if (index != -1) {
                            int newIndex = (index + key) % enSize;
                            char newChar = ALPHABET_EN[newIndex];
                            result.append(isUpper ? Character.toUpperCase(newChar) : newChar);
                            continue;
                        }
                        result.append(character);
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
    private static int findIndex(char[] alphabet, char character) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == character) {
                return i;
            }
        }
        return -1;
    }

}

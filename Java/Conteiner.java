import javax.swing.*;
import java.awt.*;
public class Conteiner extends JFrame{
    JTextField jTextField;
    public Conteiner() {
        super("Шифр цезаря");
        super.setBounds(250, 150, 1250, 650);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Ошибка!");
        }

        Font modernFont = new Font("Segoe UI", Font.PLAIN, 14);
        UIManager.put("Label.font", modernFont);
        UIManager.put("Button.font", modernFont);
        UIManager.put("TextField.font", modernFont);

        Container conteiner = super.getContentPane();
        conteiner.setLayout(new GridLayout(9, 3,10,10));

        JLabel jLabel = new JLabel("Введите имя файла: ");
        jTextField = new JTextField( 1);
        JButton button = new JButton("Создать файл");
        JButton button1 = new JButton("Записать/Перезаписать текст в файле");
        JButton button2 = new JButton("Прочитать содержимое файла");
        JButton button3 = new JButton("Зашифровать содержимое файла");
        JButton button4 = new JButton("Расшифровать файл");
        JButton button5 = new JButton("Brute Force");

        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMargin(new Insets(10, 20, 10, 20));

        button1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button1.setFocusPainted(false);
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button1.setMargin(new Insets(10, 20, 10, 20));

        button2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button2.setFocusPainted(false);
        button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button2.setMargin(new Insets(10, 20, 10, 20));

        button3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button3.setFocusPainted(false);
        button3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button3.setMargin(new Insets(10, 20, 10, 20));

        button4.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button4.setFocusPainted(false);
        button4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button4.setMargin(new Insets(10, 20, 10, 20));

        button5.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button5.setFocusPainted(false);
        button5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button5.setMargin(new Insets(10, 20, 10, 20));

        conteiner.add(button);
        conteiner.add(button1);
        conteiner.add(button2);
        conteiner.add(button3);
        conteiner.add(button4);
        conteiner.add(button5);
        conteiner.add(jLabel);
        conteiner.add(jTextField);

        button.addActionListener( new FileCreate(jTextField));
        button1.addActionListener(new FileWriter(jTextField));
        button2.addActionListener(new FileReader(jTextField));
        button3.addActionListener(new Encoder(jTextField));
        button4.addActionListener(new Decoder(jTextField));
        button5.addActionListener(new BruteForce(jTextField));
    }
}

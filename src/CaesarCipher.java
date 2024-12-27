import java.io.*;
import java.util.Scanner;

public class CaesarCipher {

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        shift = shift % 26 + 26; 

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                character = (char) ((character - base + shift) % 26 + base);
            }
            result.append(character);
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, -shift); 
    }

    public static void processFile(String inputFilePath, String outputFilePath, int shift, boolean encrypting) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String processedLine = encrypting ? encrypt(line, shift) : decrypt(line, shift);
                writer.write(processedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите режим: 1 - Шифрование, 2 - Расшифрование");
        int mode = scanner.nextInt();
        System.out.println("Введите сдвиг (целое число):");
        int shift = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Введите путь к входному файлу:");
        String inputFilePath = scanner.nextLine();
        System.out.println("Введите путь к выходному файлу:");
        String outputFilePath = scanner.nextLine();

        boolean encrypting = (mode == 1);
        processFile(inputFilePath, outputFilePath, shift, encrypting);

        System.out.println(encrypting ? "Шифрование завершено." : "Расшифрование завершено.");
    }
}

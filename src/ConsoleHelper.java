import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        while (true) {
            try {
                String message = reader.readLine();
                if (message != null) return message;
            } catch (IOException e) {
                writeMessage("Произошла ошибка ввода текста. Введите ещё раз");
            }
        }
    }

    public static int readInt(){
        while (true){
            try {
                return Integer.parseInt(readString().trim());
            }
            catch (NumberFormatException e){
                writeMessage("Произошла ошибка ввода числа. Введите ещё раз");
            }
        }
    }
}

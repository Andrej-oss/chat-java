import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Введите порт сервера");
        final int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port)){
            ConsoleHelper.writeMessage("Чат запущен");
            while (true){
                final Socket socket = serverSocket.accept();
                new Helper(socket).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Произошла ошибка при запуске или работе сервера");
        }
    }
    public static class Helper extends Thread{

        private Socket socket;

        public Helper(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
        }
    }
    public static void sendBroadcastMessage(Message message){
        for (Connection connection: connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Нк смогли отправить сообщение на адресс " + connection.getRemoteSocketAddress());
            }
        }
    }
}

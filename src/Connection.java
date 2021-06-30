import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

public class Connection implements Closeable {

    final Socket socket;
    final ObjectOutputStream outputStream;
    final ObjectInputStream inputStream;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message message) throws IOException {
        synchronized (outputStream){
            outputStream.writeObject(message);
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {
        synchronized (inputStream) {
            return (Message) inputStream.readObject();
        }
    }

    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpServer {
    public static void main(String[] argv) {
        //
        // 1. Open UDP socket at well-known port
        //
        System.out.println("Server: Server is up and running!");
        DatagramSocket socket = null;
        byte[] buffer = new byte[512];

        try {
            socket = new DatagramSocket(17);
        } catch (SocketException e) {
        }

        while (true) {
            try {
                //
                // 2. Listen for UDP request from client
                //
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                String received = new String(request.getData(), 0, request.getLength());
                System.out.println((received));
                InetAddress address = request.getAddress();
                int port = request.getPort();

                //
                // 3. Send UDP reply to client
                //
                String replyMessage = new String("Server: Received you!");
                buffer = replyMessage.getBytes();
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(reply);

            } catch (IOException e) {
            }
        }
    }
}

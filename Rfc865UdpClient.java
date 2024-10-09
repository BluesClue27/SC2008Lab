import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Rfc865UdpClient {

    public static void main(String[] argv) throws SocketException, UnknownHostException {
        //
        // 1. Open UDP socket
        //
        DatagramSocket socket = new DatagramSocket();
        // InetAddress address = InetAddress.getByName("SWLAB2-C.scse.ntu.edu.sg");
        InetAddress address = InetAddress.getByName("127.0.0.1");
        // quote of the day server
        // QOTD from Lab: The only good is knowledge and the only evil is ignorance -
        // Socrates.

        byte[] buffer = new byte[512];

        try {
            //
            // 2. Send UDP request to server
            //
            String message = new String("Randy Tan Yu Hong, TR1, 10.91.225.194");
            System.out.println("Client: Message sent to server successfully!");
            buffer = message.getBytes();
            // QOTD port 17
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, 17);
            socket.send(request);

            //
            // 3. Receive UDP reply from server
            //
            buffer = new byte[65535];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);
            String received = new String(reply.getData(), 0, reply.getLength());
            System.out.println(received);
        } catch (IOException e) {
        }

        System.out.println("Client: Closing Socket!");
        socket.close();
    }
}

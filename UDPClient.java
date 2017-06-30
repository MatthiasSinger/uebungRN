import java.net.*;
import java.io.IOException;
import java.io.*;
public class UDPClient
{
	DatagramSocket socket;
	SocketAddress serverAddr;
	byte[] buffer;

	public UDPClient() throws SocketException, UnknownHostException
	{
		socket = new DatagramSocket(); //binde einen freien Port, da kein Parameter angegeben
		serverAddr = new InetSocketAddress(InetAddress.getByName("localhost"),4567);
		buffer = new byte[512];
	}
	
	public void run() throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line; //Eingabezeile des Nutzers
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length,serverAddr);
		boolean running = true;
		while (running) 
		{
			line = reader.readLine();
			packet.setData(line.getBytes());
			socket.send(packet);
			line = reader.readLine();
			packet.setData(line.getBytes());
			socket.send(packet);
			line = reader.readLine();
			packet.setData(line.getBytes());
			socket.send(packet);

			packet.setData(buffer);
			socket.receive(packet);
			System.out.println(new String(packet.getData()));
		}
		socket.close();
	}	

	public static void main(String[] args)
{
	try {
		new UDPClient().run();
	}
 	catch (Exception e) {}
}


}

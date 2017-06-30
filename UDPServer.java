import java.net.*;
import java.io.IOException;
public class UDPServer 
{
	DatagramSocket socket;
	byte buffer[];

	public UDPServer() throws SocketException, UnknownHostException
	{
		socket = new DatagramSocket(new InetSocketAddress(InetAddress.getByName("localhost"),4567));	
		buffer = new byte[512];
		
	}
	
	public void run() throws IOException
	{
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		//haben jetzt: ein gebundenes Socket, einen Paketpuffer
		//Jetzt kann das Anwendungsprotokoll implementiert werden
		boolean running = true;
		while(running) 
		{
			socket.receive(packet);
			double op1 = Double.valueOf(new String(packet.getData()));
			socket.receive(packet);
			char operation = new String(packet.getData()).charAt(0);
			socket.receive(packet);
			double op2 = Double.valueOf(new String(packet.getData()));
			double result = 0.;
			switch (operation) {
			case ('+'): result = op1+op2;break;
			case ('-'): result = op1-op2;break;
			case ('*'): result = op1*op2;break;
			case ('/'): result = op1/op2;break;
			default:    System.out.println("Falsche eingabe!");
			}
			packet.setData(String.valueOf(result).getBytes());
			socket.send(packet);
			packet.setData(buffer);
		}
		socket.close();
	}
	
	public static void main(String[] args) 
	{
		try
		{
			new UDPServer().run();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	


}

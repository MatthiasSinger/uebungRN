import java.net.*;
import java.io.*;
class TCPServer implements Runnable
{
	private Socket client;
	private String line;

	public TCPServer(Socket client)
	{
		this.client = client;
	}		

	public void run()
	{
		try {
		BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

		output.write("Mein Anwendungsserver");
		output.newLine();
		output.flush();
		
		
		boolean finish = false;
		while (!finish) {
		line = input.readLine();
		if (line.equals("exit")) {
		finish = true;
		line = "goodbye!";
		}

		output.write(line);
		output.newLine();
		output.flush();
		}
		client.close();
		} catch (Exception e)
		{
		}
	}	
	

	public static void main(String[] args) throws Exception
	{
		/* Echo Server Implementierung */
		ServerSocket serverSocket = new ServerSocket(4567);
		
		System.out.println("Server gestartet!");
		
		while (true) 
		{
			Socket client = serverSocket.accept();
			new Thread(new TCPServer(client)).start();
		}
	
	}

}


import java.net.Socket;
import java.io.*;
class TCPClient
{
	public static void main(String[] args) throws IOException {
		String line;
		BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));

		Socket socket = new Socket("localhost",4567);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		//Ab hier läuft das Anwendungsprotokoll
		
		//Version und Name des Servers empfangen und ausgeben
		line = input.readLine();
		System.out.println(line);
		while (true) {
		//Eingabezeile des Benutzers lesen		
		line = userReader.readLine();
		output.write(line);
		output.newLine();
		output.flush();
		//Echo vom Server empfangen und dem Benutzer anzeigen
		line = input.readLine();
		System.out.println(line);
		if (line.equals("goodbye")) break;
		}
		socket.close();
}
}

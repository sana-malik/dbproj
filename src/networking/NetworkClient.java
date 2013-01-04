package networking;

import java.io.*;
import java.net.*;

public class NetworkClient {
	
	private Socket echoSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	private String serverName;
	
	public NetworkClient(String serverName) {
		this.serverName = serverName;
	}

	public void openConnection() throws IOException {		
		try {
			echoSocket = new Socket(serverName, 7);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + serverName + ".");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: " + serverName + ".");
		}

	}

	public void sendData( String data ) {
		try {
			out.println(data);
		} catch (NullPointerException e) {
			System.err.println("Connection to " + serverName + " has not been established.");
		}
	}
	
	public void closeConnection() throws IOException {
		out.close();
		in.close();
		echoSocket.close();
	}

}

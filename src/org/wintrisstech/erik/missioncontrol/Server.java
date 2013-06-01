package org.wintrisstech.erik.missioncontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	//Change "localhost" in the line below to a string containing the IP address of the server.
	public final static String SERVER_NAME = "localhost";
	public final static int SERVER_PORT = 44444;

	public static void main(String[] args) {
		Server server = new Server();
		server.runServer();
	}

	private void runServer() {
		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		// Try setting up a server socket.
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Established server.");
			// A server socket has been set up.
			while (clientSocket == null) {
				// Try setting up a client socket and wait for client to connect
				try {
					clientSocket = serverSocket.accept();
					// Client has connected
					System.out.format("Connected with client: %s\n",
							clientSocket.getRemoteSocketAddress().toString());
					// Create a reader for reading from the client socket.
					BufferedReader in = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));
					// Read one line at the time and send it to Swing for
					// processing
					String inputLine = in.readLine();
					while (inputLine != null) {
						System.out.println(inputLine);
						inputLine = in.readLine();
					}
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				} finally {
					// If the client has closed its side of the connection, ...
					if (clientSocket != null) {
						// Close the server's side of the connection and
						// prepare the server for a new client
						try {
							clientSocket.close();
							clientSocket = null;
						} catch (IOException ex) {
							System.out.println(ex.getMessage());
						}
					}

				}
			}
		} catch (IOException ex) {
			System.out.println("Unable to set up server: " + ex.getMessage());
			return;
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e) {

				}
			}
		}
	}

}

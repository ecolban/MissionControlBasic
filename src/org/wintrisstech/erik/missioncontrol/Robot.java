package org.wintrisstech.erik.missioncontrol;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

//import org.wintrisstech.erik.iaroc.Maze.Wall;

public class Robot {

	// Change "localhost" in the line below to a string containing the IP
	// address of the server.
	public final static String SERVER_NAME = "localhost";
	public final static int SERVER_PORT = 44444;
	private PrintStream out;
	private Socket socket;

	public static void main(String[] args) {
		Robot robot = new Robot();
		try {
			robot.connect(SERVER_NAME, SERVER_PORT);
			robot.sendMessageToServer("I'm leaving now.");
			robot.sendMessageToServer("Bye");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void connect(String server, int port) throws UnknownHostException,
			IOException {
		socket = new Socket(server, port);
		out = new PrintStream(socket.getOutputStream());
		System.out
				.format("Connected with server: %s --> %s \n", //
						socket.getLocalSocketAddress(),
						socket.getRemoteSocketAddress());
		out.println("Hello from Robot");
	}

	private void sendMessageToServer(String message) {
		out.println(message);
	}

}

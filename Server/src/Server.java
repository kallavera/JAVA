import java.io.*;
import java.net.*;

public class Server
{
	
	public static void main(String[] args) throws Exception
	{
		Server server = new Server();
		server.run();
	}
	
	private void run() throws Exception
	{
		System.out.println("server iniciado");
		ServerSocket SRVSOCK = new ServerSocket(444);
		Socket SOCK = SRVSOCK.accept();
		InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		String MESSAGE = BR.readLine();
		
		// JOptionPane.showMessageDialog(null, MESSAGE);
		System.out.println(MESSAGE);
		
		if (MESSAGE != null)
		{
			PrintStream PS = new PrintStream(SOCK.getOutputStream());
			PS.println();
		}
		System.out.println("server cerrado");
	}
}

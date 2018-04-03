import java.io.*;
import java.net.*;

public class Client
{
	
	public static void main(String[] args) throws Exception
	{
		Client client = new Client();
		client.run();
	}
	
	private void run() throws Exception
	{
		System.out.println("Cliente iniciado");
		Socket SOCK = new Socket("localhost", 444);
		PrintStream PS = new PrintStream(SOCK.getOutputStream());
		PS.println("Hola al Servidor desde el Cliente");
		
		InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		String MESSAGE = BR.readLine();
		System.out.println(MESSAGE);
		System.out.println("Cliente terminado");
	}
}

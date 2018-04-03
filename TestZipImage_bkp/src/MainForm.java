import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;

public class MainForm extends JFrame
{
	static String myPath;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		File miDir = new File(".");
		try
		{
			myPath = miDir.getCanonicalPath();
			System.out.println("Directorio actual: " + myPath);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainForm frame = new MainForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public MainForm()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		add(new Imagen());
	}
	
}

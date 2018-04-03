import static org.lwjgl.opengl.GL11.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.DisplayMode;

public class SwingInputTest
{
	private JFrame frame;
	private final JPanel panel1 = new JPanel()
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -5820180097689378392L;
		
		public Dimension getPreferredSize()
		{
			return new Dimension(100, 80);
		};
	};
	private final JPanel panel2 = new JPanel()
	{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 7832540353097135601L;
		
		public Dimension getPreferredSize()
		{
			return new Dimension(100, 80);
		};
	};
	private final JTextPane panelTexto = new JTextPane();
	
	/** The Canvas where the LWJGL Display is added */
	Canvas display_parent;
	
	/** Thread which runs the main game loop */
	Thread gameThread;
	
	/** is the game loop running */
	boolean running;
	
	public SwingInputTest()
	{
	}
	
	/**
	 * Once the Canvas is created its add notify method will call this method to
	 * start the LWJGL Display and game loop in another thread.
	 */
	public void startLWJGL()
	{
		gameThread = new Thread()
		{
			public void run()
			{
				running = true;
				try
				{
					Display.setParent(display_parent);
					Display.setVSyncEnabled(true);
					Display.setDisplayMode(new DisplayMode(450, 250));
					Display.create();
					initGL();
				}
				catch (LWJGLException e)
				{
					e.printStackTrace();
				}
				gameLoop();
			}
		};
		gameThread.start();
	}
	
	protected void gameLoop()
	{
		while (running)
		{
			System.out.println("--GAMELOOP--");
			
			Display.update();
			
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			{
				glClearColor(1f, 0, 0, 0);
				System.out.println("Pigs in space!");
			}
			else
			{
				glClearColor(1f, 1f, 1f, 0f);
			}
			glClear(GL_COLOR_BUFFER_BIT);
			
			glBegin(GL_QUADS);
			{
				glColor3f(0.5f, 0.5f, 0.5f);
				glVertex2d(10, 10);
				glVertex2d(20, 10);
				glVertex2d(20, 20);
				glVertex2d(10, 20);
			}
			glEnd();
			
			Display.sync(30);
		}
		Display.destroy();
	}
	
	protected void initGL()
	{
		glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 450, 0, 250, 1, -1); // EJE 0,0EN LA ESQUINA INFERIOR
										// IZQUIERDA
		glMatrixMode(GL_MODELVIEW);
	}
	
	/**
	 * Tell game loop to stop running, after which the LWJGL Display will be
	 * destoryed. The main thread will wait for the Display.destroy() to
	 * complete
	 */
	private void stopLWJGL()
	{
		running = false;
		try
		{
			gameThread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	private void execute()
	{
		frame.setVisible(true);
	}
	
	private void initialize()
	{
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				frame.remove(display_parent);
				frame.dispose();
			}
		});
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.getContentPane().setLayout(new GridLayout(3, 3, 0, 0));
		frame.getContentPane().setLayout(new BorderLayout());
		
		frame.getContentPane().add(panel1, BorderLayout.NORTH);
		panel1.setLayout(new BorderLayout());
		// panel1.setBounds(0, 0, 300, 50);
		panelTexto.setBounds(10, 5, 124, 20);
		panel1.add(panelTexto, BorderLayout.CENTER);
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		panel2.setLayout(new BorderLayout(0, 0));
		// panel2.setBounds(0, 0, 450, 200);
		
		display_parent = new Canvas()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 4994751330221600005L;
			
			public void addNotify()
			{
				super.addNotify();
				startLWJGL();
			}
			
			public void removeNotify()
			{
				stopLWJGL();
				super.removeNotify();
			}
		};
		display_parent.setFocusable(true);
		display_parent.requestFocus();
		display_parent.setIgnoreRepaint(true);
		panel2.add(display_parent);
	}
	
	public static void main(String[] args)
	{
		SwingInputTest sit = new SwingInputTest();
		sit.initialize();
		sit.execute();
	}
}
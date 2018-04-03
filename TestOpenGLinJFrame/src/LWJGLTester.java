import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LWJGLTester
{
	AWTGLCanvas glCanvas = null;
	private final Object lock = new Object();
	private boolean isRunning = false;
	
	/*
	 * The question asker seemed to desire that the JFrame be 800x600 and that
	 * the Display be 300x300. Regardless of the desired sizes, I think the
	 * important thing is to set the Canvas and Display to the same sizes.
	 */
	private int frameWidth = 800;
	private int frameHeight = 600;
//	private int displayWidth = 300;
//	private int displayHeight = 300;
	private int displayWidth = 700;
	private int displayHeight = 500;
	
	private Thread glThread;
	
	public static void main(String[] args)
	{
		new LWJGLTester().runTester();
	}
	
	private void runTester()
	{
		final JFrame frame = new JFrame("LWJGL in Swing");
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent we)
			{
				int result = JOptionPane.showConfirmDialog(frame,
						"Do you want to quit the Application?");
				if (result == JOptionPane.OK_OPTION)
				{
					frame.setVisible(false);
					frame.dispose(); // canvas's removeNotify() will be called
				}
			}
		});
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JButton button = new JButton("BUTTON");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(button);
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		
		try
		{
			glCanvas = new AWTGLCanvas(){
				/**
				 * 
				 */
				private static final long serialVersionUID = -2915652560276649640L;

				@Override
				public void addNotify()
				{
					super.addNotify();
					startGL();
				}
				
				@Override
				public void removeNotify()
				{
					stopGL();
					super.removeNotify();
				}
			};
			glCanvas.setPreferredSize(new Dimension(displayWidth, displayHeight));
			glCanvas.setIgnoreRepaint(true);
		}
		catch (LWJGLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try
		{
			Display.setParent(glCanvas);
		}
		catch (LWJGLException e)
		{
			// handle exception
			e.printStackTrace();
		}
		JPanel canvasPanel = new JPanel();
		canvasPanel.add(glCanvas);
		mainPanel.add(canvasPanel, BorderLayout.CENTER);
		
		frame.getContentPane().add(mainPanel);
		
//		frame.pack();
		frame.setVisible(true);
	}
	
	private void startGL()
	{
		glThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				setIsRunning(true);
				try
				{
					Display.setDisplayMode(new DisplayMode(displayWidth,displayHeight));
					Display.create();
				}
				catch (LWJGLException e)
				{
					// handle exception
					e.printStackTrace();
				}
				
				// init OpenGL here
				glClearColor(1.0f,1.0f,1.0f,0.0f); //black backround                          
			    glEnable(GL11.GL_TEXTURE_2D);     
			    glEnable(GL11.GL_BLEND);  
			    
			    glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
				glMatrixMode(GL_PROJECTION);
				glLoadIdentity();
				glOrtho((displayWidth / 2), displayWidth, (displayHeight / 2), displayHeight, 1, -1); // EJE 0,0EN LA ESQUINA INFERIOR
				// IZQUIERDA
				glMatrixMode(GL_MODELVIEW);
				
				while (isRunning())
				{
					
					// render OpenGL here
//					glEnable(GL_COLOR);
//					glEnable(GL11.GL_TEXTURE_2D );
//					glDisable(GL11.GL_TEXTURE_2D );
					render();
					
					Display.update();
					Display.sync(60);
				}
				
				Display.destroy();
			}
		}, "LWJGL Thread");
		
		glThread.start();
	}
	
	private void stopGL()
	{
		setIsRunning(false);
		try
		{
			glThread.join();
		}
		catch (InterruptedException e)
		{
			// handle exception
			e.printStackTrace();
		}
	}
	
	private void setIsRunning(boolean isRunning)
	{
		synchronized (lock)
		{
			this.isRunning = isRunning;
		}
	}
	
	private boolean isRunning()
	{
		synchronized (lock)
		{
			return isRunning;
		}
	}
	
	private void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glBegin(GL_QUADS);
		{
			glColor3f(0.5f, 0.5f, 0.5f);
			glVertex2d(10, 10);
			glVertex2d(20, 10);
			glVertex2d(20, 20);
			glVertex2d(10, 20);
		}
		glEnd();
		System.out.println("rendereando");
		System.out.println(displayWidth + " " + displayHeight);
	}
	
}
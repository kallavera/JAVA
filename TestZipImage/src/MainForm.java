import java.awt.BorderLayout;
//import java.awt.Component;
//import java.awt.Container;
import java.awt.Dimension;
//import java.awt.LayoutManager;
import java.awt.ScrollPane;

import javax.swing.JFrame;


public class MainForm extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3083498813875529309L;
	private PnlImagen pnlImagen;
	private ScrollPane scroll;
	
	public MainForm(){init();}
	
	private void init()
	{
		setSize(800, 600);
//		setLayout(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		pnlImagen = new PnlImagen();
		scroll = new ScrollPane();
		scroll.setPreferredSize(new Dimension(450, 110));
//		scroll.setBounds(0, 0, (getWidth() - 50), (getHeight() - 50));
		scroll.add(pnlImagen);
		add(scroll);
	}
	
	public static void main(String[] args)
	{
		new MainForm();
	}
}

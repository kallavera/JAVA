import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PnlImagen extends Canvas
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4601035374822218749L;
	
	String archivoZip = ".\\Recursos\\Imagenes\\XMen.cbr";
	
	/**
	 * Create the panel.
	 */
	
	public PnlImagen()
	{
		// this.setSize(1200, 1600); // se selecciona el tamaño del panel
		setBounds(0, 0, 1200, 1600);
		
	}
	
	// Se crea un método cuyo parámetro debe ser un objeto Graphics
	
	public void paint(Graphics grafico)
	{
//		ZipFileHandler zfh = new ZipFileHandler(archivoZip);
		SpriteManager sm = new SpriteManager();
		Dimension height = getSize();
//		ImageIcon Img = new ImageIcon(loadImage("X-Men - First Class 001-002.jpg"));
		ImageIcon Img = new ImageIcon(sm.getSpriteSheet(".\\Recursos\\Imagenes\\", "XMen.cbr", "X-Men - First Class 001-006.jpg"));
		grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height,null);
	}
	
	public Image loadImage(String fileName)
	{
		ZipFileHandler zfh = new ZipFileHandler(archivoZip);
		InputStream in = new ByteArrayInputStream(zfh.getEntry(fileName));
		BufferedImage bgTileSprite = null;
		
		try
		{
			bgTileSprite = ImageIO.read(in);
		}
		catch (IOException e)
		{
			System.out.println("Could not convert zipped image bytearray to a BufferedImage.");
		}
		
		return bgTileSprite;
	}
	
}
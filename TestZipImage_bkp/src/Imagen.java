import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Imagen extends Canvas
{
	String archivoZip = ".\\Recursos\\Imagenes\\XMen.cbr";
	
	/**
	 * Create the panel.
	 */
	
	public Imagen()
	{
		this.setSize(300, 400); // se selecciona el tamaño del panel
	}
	
	// Se crea un método cuyo parámetro debe ser un objeto Graphics
	
	public void paint(Graphics grafico)
	{
		Dimension height = getSize();
		ImageIcon Img = new ImageIcon(loadImage("X-Men - First Class 001-000.png"));
		grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height,
				null);
	}
	
	public Image loadImage(String fileName)
	{
		System.out.println(archivoZip);
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

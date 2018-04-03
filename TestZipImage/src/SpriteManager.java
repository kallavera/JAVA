import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

public class SpriteManager
{
	private ZipFile zipFile;
	
	public SpriteManager() {}
	
	private byte[] getEntry(String filePath)
	{
		ZipEntry entry = zipFile.getEntry(filePath);
		int entrySize = (int) entry.getSize();
		try
		{
			BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
			byte[] finalByteArray = new byte[entrySize];
			
			int bufferSize = 2048;
			byte[] buffer = new byte[2048];
			int chunkSize = 0;
			int bytesRead = 0;
			
			while (true)
			{
				// Read chunk to buffer
				chunkSize = bis.read(buffer, 0, bufferSize); 	// read() returns
																// the number of
																// bytes read
				if (chunkSize == -1)
				{
					// read() returns -1 if the end of the stream has been
					// reached
					break;
				}
				
				// Write that chunk to the finalByteArray
				// System.arraycopy(src, srcPos, dest, destPos, length)
				System.arraycopy(buffer, 0, finalByteArray, bytesRead,chunkSize);
				
				bytesRead += chunkSize;
			}
			
			bis.close(); // close BufferedInputStream
			
			return finalByteArray;
		}
		catch (IOException e)
		{
			System.err.println("No zip entry found at: " + filePath);
			return null;
		}
	}
	
	private BufferedImage spriteSheet(String zipFilePath,
									  String zipFileToLoad,
									  String imageFile)
	{
		BufferedImage imagen = null;
		
		try{zipFile = new ZipFile(zipFilePath + zipFileToLoad);}
		catch (IOException e)
		{
			System.out.println("Unable to load zip file at location: " + zipFilePath + zipFileToLoad);
			System.out.println(e.getMessage());
		}
		InputStream in = new ByteArrayInputStream(this.getEntry(imageFile));
		try
		{
			imagen = ImageIO.read(in);
		}
		catch (IOException e)
		{
			System.out.println("Could not convert zipped image bytearray to a BufferedImage.");
		}
		return imagen;
	}
	
	public BufferedImage getSpriteSheet(String zipFilePath,
										String zipFileToLoad,
										String imageFile)
	{
		BufferedImage spriteSheet = spriteSheet(zipFilePath, zipFileToLoad, imageFile);
		
		return spriteSheet;
	}
}

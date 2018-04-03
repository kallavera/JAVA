import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipFileHandler
{
	private ZipFile zipFile;
	ZipInputStream zis;
	
	public ZipFileHandler(String zipFileToLoad)
	{
		try
		{
			zipFile = new ZipFile(zipFileToLoad);
		}
		catch (IOException e)
		{
			System.out.println("Unable to load zip file at location: " + zipFileToLoad);
			System.out.println(e.getMessage());
		}
	}
	
	public byte[] getEntry(String filePath)
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
				chunkSize = bis.read(buffer, 0, bufferSize); // read() returns
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
				System.arraycopy(buffer, 0, finalByteArray, bytesRead,
						chunkSize);
				
				bytesRead += chunkSize;
			}
			
			bis.close(); // close BufferedInputStream
			
			System.err.println("Entry size: " + finalByteArray.length);
			
			return finalByteArray;
		}
		catch (IOException e)
		{
			System.err.println("No zip entry found at: " + filePath);
			return null;
		}
	}
}
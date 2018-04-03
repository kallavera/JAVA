import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class MyZipFile
{
	public void getZipFile(String archivo)
	{
		try
		{
			ZipFile zf = new ZipFile("archivo.zip");
			Enumeration<?> entries = zf.entries();
			
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			while (entries.hasMoreElements())
			{
				ZipEntry ze = (ZipEntry) entries.nextElement();
				System.out.println("Read " + ze.getName() + "?");
				String inputLine = input.readLine();
				if (inputLine.equalsIgnoreCase("yes"))
				{
					long size = ze.getSize();
					if (size > 0)
					{
						BufferedReader br = new BufferedReader(
								new InputStreamReader(zf.getInputStream(ze)));
						String line;
						while ((line = br.readLine()) != null)
						{
							System.out.println(line);
						}
						br.close();
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipAnalizer
{
	public ZipAnalizer(String zipFile)
	{
		ZipInputStream zis;
		try
		{
			int contador = 0;
			zis = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry entrada;
			System.out.println("Indice de hojas:\n");
			contador = 0;
			while (null != (entrada = zis.getNextEntry()))
			{
				contador++;
				System.out.println(entrada.getName());
				zis.closeEntry();
			}
			zis.close();
			System.out.println(contador);
		}
		catch (FileNotFoundException e){System.out.println("No se puede encontrar el archivo");}
		catch (IOException e){System.out.println(e.getMessage());}
		catch (Exception e){System.out.println(e.getMessage());}
	}
}

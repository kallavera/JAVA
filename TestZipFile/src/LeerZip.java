import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.*;

public class LeerZip
{
	
	public static void main(String[] args)
	{
		ZipInputStream zis;
		try
		{
			zis = new ZipInputStream(new FileInputStream("fichero.adv"));
			ZipEntry entrada;
			int contador = 0;
			   while (null != (entrada=zis.getNextEntry()))
			   {
			      System.out.println(entrada.getName());
			      contador++;
			      FileOutputStream fos = new FileOutputStream(entrada.getName());
			      int leido;
			      byte [] buffer = new byte[1024];
			      while (0<(leido=zis.read(buffer)))
			      {
			         fos.write(buffer,0,leido);
			      }
			      fos.close();
			      zis.closeEntry();
			   }
			   zis.close();
			   System.out.println("cantiad de archivos " + contador);
		}
		catch (FileNotFoundException e){System.out.println("No se puede encontrar el archivo");}
		catch (IOException e){System.out.println(e.getMessage());}
		catch (Exception e ){System.out.println(e.getMessage());}
	}
}

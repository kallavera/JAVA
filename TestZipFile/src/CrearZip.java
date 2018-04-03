import java.io.*;
import java.util.zip.*;

public class CrearZip
{
	private static String path;

	private static String files;
	private static String carpeta = null;
	private static String newPath = null;

	public static void main(String[] args)
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Ingrese un directorio: ");
	    String path;
		try
		{
			path = reader.readLine();
			System.out.println("procesando directorio: " + path);
			//zipear("C:/ARIEL/IMAGENES/WALLPAPER/STEAMPUNK", "C:/ARIEL/IMAGENES/WALLPAPER/steampunk.cbr");
			listar(path);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Finished!!!");
	}

	public static void listar(String _path)
	{
		path = _path;

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++)
		{
			if (listOfFiles[i].isDirectory())
			{
				carpeta = listOfFiles[i].getName();
				newPath = listOfFiles[i].getPath();
				
				System.out.println(carpeta);
				System.out.println(newPath);
				
				listar(newPath);
				
				zipear(newPath, path+".cbr");
			}
			/*
			if (listOfFiles[i].isFile())
			{
				files = listOfFiles[i].getName();
				System.out.println(carpeta);
				System.out.println(newPath);
				System.out.println(files);
				zipear(newPath + "/" + files, newPath + "/" + carpeta);
			}
			*/
		}
	}

	private static void zipear(String nombreFichero, String archivoDestino)
	{
		try
		{
			String path=nombreFichero;
			File f=new File(path);
			f.mkdir();
			byte []b;
			String zipFile=archivoDestino;
			FileOutputStream fout=new FileOutputStream(zipFile);
			ZipOutputStream zout=new ZipOutputStream(new BufferedOutputStream(fout));


			File []s=f.listFiles();
			for(int i=0;i<s.length;i++)
			{
				b=new byte[1024];
			    FileInputStream fin=new FileInputStream(s[i]);
			    zout.putNextEntry(new ZipEntry(s[i].getName()));
			    int length;
			    while((length=fin.read(b, 0, 1024))>0)
			    {
			        zout.write(b,0,length);
			    }
			    zout.closeEntry();
			    fin.close();
			}
			zout.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("Done!!!");
	}

}
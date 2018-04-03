package com.kallavera.core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MakeCBR
{
	private static String path;

	private static String files;
	private static String carpeta = null;
	private static String newPath = null;

	public MakeCBR()
	{
		
	}
	
	public void procesar(String path)
	{
		System.out.println("procesando directorio: " + path);
		listar(path);
		System.out.println("Finished!!!");
	}
	
	public void listar(String _path)
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

		private void zipear(String nombreFichero, String archivoDestino)
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

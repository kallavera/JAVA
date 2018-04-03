package com.kallavera.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CBRMaker extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String path;

	private String files;
	private String carpeta = null;
	private String newPath = null;
	
	JButton btn_salir;
	JButton btn_iniciar;
	
	JTextField txt_path;
	
	JLabel lbl_inprocess;
	
	private MakeCBR maker;
	
	public CBRMaker()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setSize(450,200);
		setLocationRelativeTo(null);
		setTitle("CBR Maker 0.1");
		
		lbl_inprocess = new JLabel();
        lbl_inprocess.setBounds(25, 20, 400, 15);
        add(lbl_inprocess);
        
        txt_path = new JTextField();
        txt_path.setBounds(25, 50, 400, 25);
        add(txt_path);
        
        btn_iniciar = new JButton("Iniciar");
        btn_iniciar.setBounds(215,100,100,30);
        add(btn_iniciar);
        btn_iniciar.addActionListener(this);
        
        btn_salir = new JButton("Salir");
        btn_salir.setBounds(325,100,100,30);
        add(btn_salir);
        btn_salir.addActionListener(this);
        
        maker = new MakeCBR();
	}

	public static void main(String[] args)
	{
		CBRMaker window = new CBRMaker();
		window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==btn_salir)
		{
			System.out.println("btn_salir");
            System.exit(0);
        }
		
		if (e.getSource()==btn_iniciar)
		{
			System.out.println("btn_iniciar");
            procesar(txt_path.getText());
        }
	}
	
	public void procesar(String path)
	{
		System.out.println("procesando directorio: " + path);
		
		listar(path);
		
		System.out.println("Finished!!!");
		lbl_inprocess.setText("Creacion finalizada!");
		
		JOptionPane.showMessageDialog
		(
			null,
			"Se finalizo con la creacion de los CBR",
			"Finalizado",
            JOptionPane.INFORMATION_MESSAGE
        );
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
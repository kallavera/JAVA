import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EscribirDat
{
	public static void main(String[] args)
	{
		//variable con el path del archivo binario
		String nombreFichero = "c:/Persona.sav";
		String nombreFichero2 = "c:/matematicas.sav";
		
		try
		{
			//ser crea el objeto para escribir el archivo binario
			FileOutputStream ficheroSalida = new FileOutputStream(nombreFichero);
			ObjectOutputStream objetoSalida = new ObjectOutputStream(ficheroSalida);
			
			//se agrega un objeto de la clase Persona
			objetoSalida.writeObject(new Persona("Ariel", "Velasco", "30860304"));
			objetoSalida.writeObject(new Persona("Patricia", "Porta","26348217"));
			
			//se cierra la conexion con el archivo
			objetoSalida.close();
			
			
			FileOutputStream ficheroSalida2 = new FileOutputStream(nombreFichero2);
			ObjectOutputStream objetoSalida2 = new ObjectOutputStream(ficheroSalida2);
			
			objetoSalida2.writeObject(new Aritmetica());
			
			objetoSalida2.close();
		}
		catch (FileNotFoundException e){System.out.println("El fichero no existe");}
		catch (IOException e){System.out.println(e.getMessage());}
		catch (Exception e){System.out.println(e.getMessage());}
		
		System.out.println("Archivo creado exitosamente");
	}
}

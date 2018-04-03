import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeerDat
{
	public static void main(String[] args)
	{
		//se define el path del archivo binario
		String nombreFichero = "c:\\Persona.sav";
		String nombreFichero2 = "c:\\matematicas.sav";
		try
		{
			//se crea el objeto para la lectura del archivo
			FileInputStream ficheroEntrada = new FileInputStream(nombreFichero);
			ObjectInputStream objetoEntrada = new ObjectInputStream(ficheroEntrada);
			
			//se crean objetos de la clase persona y se le asignan los valores grabados en elarchivo
			Persona persona1 = (Persona) objetoEntrada.readObject();
			Persona persona2 = (Persona) objetoEntrada.readObject();
			
			//se cierra la conexion con el archivo
			objetoEntrada.close();
			
			//se muestran el contenido del archivo por consola
			System.out.println("Datos: ");
			System.out.println(persona1.getData());
			System.out.println(persona2.getData());
			
			FileInputStream ficheroEntrada2 = new FileInputStream(nombreFichero2);
			ObjectInputStream objetoEntrada2 = new ObjectInputStream(ficheroEntrada2);
			
			Aritmetica mates = (Aritmetica)objetoEntrada2.readObject();
			objetoEntrada2.close();
			
			System.out.println(mates.suma());
			System.out.println(mates.resta());
			System.out.println(mates.multiplicacion());
			System.out.println(mates.divicion());
			
			//se crean objetos de la clase persona y se le asignan los valores grabados en elarchivo
			
			
			//se cierra la conexion con el archivo
			objetoEntrada.close();
		}
		catch (FileNotFoundException e){System.out.println("El fichero no existe");}
		catch (IOException e){System.out.println(e.getMessage());}
		catch (Exception e){System.out.println(e.getMessage());}
	}
}

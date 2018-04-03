import java.io.Serializable;

public class Persona implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2841344320544392362L;
	
	private String nombre = "";
	private String apellido = "";
	private String dni = "";
	
	public Persona(String nombre, String apellido, String dni)
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public String getApellido()
	{
		return apellido;
	}
	
	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}
	
	public String getDni()
	{
		return dni;
	}
	
	public void setDni(String dni)
	{
		this.dni = dni;
	}
	
	public String getData()
	{
		String data = "";
		data = "Nombre: " + nombre + " Apellido: " + apellido + " DNI: " + dni;
		return data;
	}
}

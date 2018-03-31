package com.elegantwhelp.tutorial.core.shaders;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Shader
{
	private static final int ATT_VERTEX_POSITIONS = 0;
	
	private int program;
	private int vs;
	private int fs;
	
	public Shader(String fileName)
	{
		program = glCreateProgram();
		
		vs = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vs, readFile(fileName + ".vs"));
		glCompileShader(vs);
		if(glGetShaderi(vs, GL_COMPILE_STATUS) != 1)
		{
			System.err.println("Shader ERROR");
			System.err.println(glGetShaderInfoLog(vs));
			System.exit(1);
		}
		
		fs = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fs, readFile(fileName + ".fs"));
		glCompileShader(fs);
		if(glGetShaderi(fs, GL_COMPILE_STATUS) != 1)
		{
			System.err.println("Fragment ERROR");
			System.err.println(glGetShaderInfoLog(fs));
			System.exit(1);
		}
		
		glAttachShader(program, vs);
		glAttachShader(program, fs);
		
		glBindAttribLocation(program, ATT_VERTEX_POSITIONS, "vertex_position");
		
		glLinkProgram(program);
		if((glGetProgrami(program, GL_LINK_STATUS)) != 1)
		{
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
		
		glValidateProgram(program);
		if((glGetProgrami(program, GL_VALIDATE_STATUS)) != 1)
		{
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
	}
	
	public void bind()
	{
		glUseProgram(program);
	}
	
	private String readFile(String fileName)
	{
		StringBuilder str = new StringBuilder();
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(new File("./shaders/" + fileName)));
//			br = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = br.readLine()) != null)
			{
				str.append(line);
				str.append("\n");
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return str.toString();
	}
}

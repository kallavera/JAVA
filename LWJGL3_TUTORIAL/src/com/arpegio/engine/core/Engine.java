package com.arpegio.engine.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import org.lwjgl.glfw.GLFWVidMode;

/**
 * @author CodesSansFrontieres
 *<br>
 *Esta clase contiene todo lo necesario para inicializar la aplicacion y crear la ventana
 */
public class Engine
{
	private static long window;
	
	public Engine()	//	Constructor con parametros por defecto
	{
		init();
	}
	
	/**
	 * void init()
	 * Inicializa GLFW
	 */
	public void init()
	{
		if(!glfwInit())
		{
			throw new IllegalStateException("[ERROR]: Fallo al inicializar GLFW.");
		}
	}
	
	/**
	 * void createDisplay(int width, int height, String title)
	 * @param width
	 *  Ancho de la ventana en pixeles
	 * @param height
	 *  Alto de la ventana en pixeles
	 * @param title
	 *  Titulo de la ventana
	 */
	public void createDisplay(int width, int height, String title)
	{
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		window = glfwCreateWindow(width, height, title, 0,  0);	//	Windowed
//		window = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(),  0);	//	FullScreen
		
		if(window == 0)
		{
			throw new IllegalStateException("[ERROR]: Fallo al crear la ventana.");
		}
		
		GLFWVidMode vm = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vm.width() - width) / 2, (vm.height() - height) / 2);
		
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glClearColor(0.0f, 0.0f, 0.0f, 1f);
		
		while(!glfwWindowShouldClose(window))
		{
			update();
			
			render();
		}
		
		glfwTerminate();
	}
	
	private void update()
	{
		glfwPollEvents();
	}
	
	private void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		
		glBegin(GL_QUADS);
		{
			glColor4f(1,  0 , 0,  1);
			glVertex2f(-0.5f,  0.5f);
			
			glColor4f(0,  1 , 0,  1);
			glVertex2f( 0.5f,  0.5f);
			
			glColor4f(0,  0 , 1,  1);
			glVertex2f( 0.5f, -0.5f);
			
			glColor4f(1,  1 , 0,  1);
			glVertex2f(-0.5f, -0.5f);
		}
		glEnd();
		
		glfwSwapBuffers(window);
	}
}

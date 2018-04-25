package rendererEngine;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;


public class Display
{
	private static long window;
	private static int width, height;
	private static String title;
	private static boolean fullScreen = false;
	
	public Display()
	{
		Display.width = 480;
		Display.height = 320;
		Display.title = "Game";
	}
	
	public Display(int width, int height, String title)
	{
		Display.width = width;
		Display.height = height;
		Display.title = title;
	}
	
	public static void createDisplay()
	{
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		window = glfwCreateWindow
				(
					fullScreen ? videoMode.width() : width,
					fullScreen ? videoMode.height() : height,
					title,
					fullScreen ? glfwGetPrimaryMonitor() : 0,
					0
				);
		
		if(window == 0)
		{
			throw new IllegalStateException("[ERROR]: Fallo al crear la ventana");
		}
		
		if(!fullScreen)
		{
			glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
			glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
			glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		}
		
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
	}
	
	public static void updateDisplay()
	{
		glfwSwapBuffers(window);
	}
	
	public static void closeDisplay()
	{
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	public static boolean shouldClose()
	{
		return glfwWindowShouldClose(window) ? true : false;
	}
	
	public static void setShouldClose()
	{
		glfwSetWindowShouldClose(window, true);
	}
	
	public static void destroyWindow()
	{
		glfwDestroyWindow(window);
	}
	
	public static void setSize(int width, int height)
	{
		Display.width = width;
		Display.height = height;
	}
	
	public static void setFullScreen(boolean fs)
	{
		fullScreen = fs;
	}
	
	public static int getWidth()
	{
		return width;
	}
	
	public static int getHeight()
	{
		return height;
	}
	
	public static long getWindow()
	{
		return window;
	}
	
	public static boolean getFullScreen()
	{
		return fullScreen;
	}
}

package engineTester;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import rendererEngine.Display;
import rendererEngine.Loader;
import rendererEngine.RawModel;
import rendererEngine.Renderer;

public class MainGameLoop
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "LWJGL 3D Tutorial";
	
	public static Display display = new Display(WIDTH, HEIGHT, TITLE);
	
	public static void main(String[] args)
	{
		if(!glfwInit())
		{
			throw new IllegalStateException("[ERROR]: Fallo al iniciar GLFW");
		}
		
		Display.createDisplay();
		
		GL.createCapabilities();
		
		float[] vertices =
		{
				-0.5f,  0.5f, 0f,
				-0.5f, -0.5f, 0f,
				 0.5f, -0.5f, 0f,
				 
				 0.5f, -0.5f, 0f,
				 0.5f,  0.5f, 0f,
				-0.5f,  0.5f, 0f
		};
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		RawModel model = loader.loadToVao(vertices);
		
		while(!display.shouldClose())
		{
			glfwPollEvents();
			
			renderer.prepare();
			
			renderer.render(model);
			
			Display.updateDisplay();
		}
		
		loader.cleanUp();
		Display.closeDisplay();
	}
}

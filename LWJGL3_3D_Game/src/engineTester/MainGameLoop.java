package engineTester;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.opengl.GL;

import rendererEngine.Display;
import rendererEngine.Loader;
import rendererEngine.RawModel;
import rendererEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop
{
	//	Carla Lerin altos pechos
	//	https://www.youtube.com/watch?v=SPt-aogu72A&list=PLRIWtICgwaX0u7Rf9zkZhLoLuZVfUksDP&index=6
	
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
				 0.5f,  0.5f, 0f
		};
		
		int[] indices =
		{
				0, 1, 3,
				3, 1, 2
		};
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer();
		
		RawModel model = loader.loadToVao(vertices, indices);
		
		while(!display.shouldClose())
		{
			glfwPollEvents();
			
			renderer.prepare();
			
			shader.start();
			renderer.render(model);
			shader.stop();
			
			Display.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		Display.closeDisplay();
	}
}

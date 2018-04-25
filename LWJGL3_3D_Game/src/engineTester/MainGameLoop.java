package engineTester;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.opengl.GL;

import inputs.Input;
import rendererEngine.Display;
import rendererEngine.Loader;
import models.RawModel;
import models.TexturedModel;
import rendererEngine.Renderer;
import textures.Texture;
import shaders.StaticShader;

public class MainGameLoop
{
	//	Carla Lerin altos pechos
	//	https://www.youtube.com/watch?v=SPt-aogu72A&list=PLRIWtICgwaX0u7Rf9zkZhLoLuZVfUksDP&index=6
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "LWJGL 3D Tutorial";
	
	public static Display display = new Display(WIDTH, HEIGHT, TITLE);
	
	private static Input input;
	
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
		
		float[] textCoords = 
		{
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer();
		
		input = new Input();
		
		RawModel model = loader.loadToVao(vertices, textCoords, indices);
		Texture texture = new Texture(loader.loadTexture("img_01"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!display.shouldClose())
		{
			glfwPollEvents();
			
			renderer.prepare();
			
			glEnable(GL_TEXTURE_2D);
			
//			input.update();
			
			update();
			
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			
			Display.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		Display.closeDisplay();
	}
	
	private static void update()
	{
		input.update();
		
		if(input.isFire()) { System.out.println("PUM!!!"); }
		
		if(input.isClose()) { Display.setShouldClose(); }
	}
}

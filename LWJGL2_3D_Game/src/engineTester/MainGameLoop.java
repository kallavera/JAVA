package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop
{
	//https://www.youtube.com/watch?v=4w7lNF8dnYw

	public MainGameLoop()
	{
		
	}

	public static void main(String[] args)
	{
		float[] positions =
		{
			-0.5f,  0.5f, 0,	//v0 TOP LEFT
			-0.5f, -0.5f, 0,	//v1 BOTTOM LEFT
			 0.5f, -0.5f, 0,	//v2 BOTTOM RIGHT
			 0.5f,  0.5f, 0,	//v3 TOP RIGHT
		};
		
		int[] indices =
		{
			0, 1, 3,
			3, 1, 2
		};
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		RawModel model = loader.loadToVAO(positions, indices);
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		while(!Display.isCloseRequested())
		{
			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}

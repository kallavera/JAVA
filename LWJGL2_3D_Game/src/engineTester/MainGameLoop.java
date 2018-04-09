package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop
{

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
		
		while(!Display.isCloseRequested())
		{
			DisplayManager.updateDisplay();
			renderer.prepare();
			renderer.render(model);
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}

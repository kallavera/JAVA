package engineTester;

import org.lwjgl.opengl.Display;

import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop
{
	//https://www.youtube.com/watch?v=4w7lNF8dnYw

	public MainGameLoop()
	{
		
	}

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
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
		
		float[] textCoords =
		{
			0, 0,
			0, 1,
			1, 1,
			1, 0
		};
		
		RawModel model = loader.loadToVAO(positions, textCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("redhead"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!Display.isCloseRequested())
		{
			renderer.prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}

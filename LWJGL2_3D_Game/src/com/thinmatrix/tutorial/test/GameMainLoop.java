package com.thinmatrix.tutorial.test;

import org.lwjgl.opengl.Display;

import com.thinmatrix.tutorial.core.DisplayManager;
import com.thinmatrix.tutorial.core.Geometry;
import com.thinmatrix.tutorial.core.Loader;
import com.thinmatrix.tutorial.core.Renderer;
import com.thinmatrix.tutorial.shaders.StaticShader;

public class GameMainLoop
{
	//	https://www.youtube.com/watch?v=SPt-aogu72A

	public GameMainLoop()
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
			-0.5f,  0.5f, 0f,	// 0
			-0.5f, -0.5f, 0f,	// 1
			 0.5f, -0.5f, 0f,	// 2
			 0.5f,  0.5f, 0f	// 3
		};
		
		int[] indices = 
		{
			0, 1, 3,
			3, 1, 2
		};
		
		Geometry geo = loader.loadToVao(positions, indices);
		
		while(!Display.isCloseRequested())
		{
			renderer.prepare();
			// Tick
			// Render
			shader.start();
			renderer.render(geo);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}

package com.thinmatrix.tutorial.test;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.thinmatrix.tutorial.core.DisplayManager;
import com.thinmatrix.tutorial.model.Geometry;
import com.thinmatrix.tutorial.model.Mesh;
import com.thinmatrix.tutorial.core.Loader;
import com.thinmatrix.tutorial.core.Renderer;
import com.thinmatrix.tutorial.entities.Camera;
import com.thinmatrix.tutorial.entities.Entity;
import com.thinmatrix.tutorial.shaders.StaticShader;
import com.thinmatrix.tutorial.textures.Material;

public class GameMainLoop
{
	//	https://www.youtube.com/watch?v=KMWUjNE0fYI

	public GameMainLoop()
	{
		
	}

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		float[] positions = 
		{
				-0.5f, 0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				 0.5f,-0.5f,-0.5f,	
				 0.5f, 0.5f,-0.5f,		
				
				-0.5f, 0.5f, 0.5f,	
				-0.5f,-0.5f, 0.5f,	
				 0.5f,-0.5f, 0.5f,	
				 0.5f, 0.5f, 0.5f,
				
				 0.5f, 0.5f,-0.5f,	
				 0.5f,-0.5f,-0.5f,	
				 0.5f,-0.5f, 0.5f,	
				 0.5f, 0.5f, 0.5f,
				
				-0.5f, 0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f, 0.5f,	
				-0.5f, 0.5f, 0.5f,
				
				-0.5f, 0.5f, 0.5f,
				-0.5f, 0.5f,-0.5f,
				 0.5f, 0.5f,-0.5f,
				 0.5f, 0.5f, 0.5f,
				
				-0.5f,-0.5f, 0.5f,
				-0.5f,-0.5f,-0.5f,
				 0.5f,-0.5f,-0.5f,
				 0.5f,-0.5f, 0.5f
		};
		
		int[] indices = 
		{
				 0, 1, 3,	
				 3, 1, 2,	
				 4, 5, 7,
				 7, 5, 6,
				 8, 9,11,
				11, 9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22
		};
		
		float[] textureCoords =
		{
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		Geometry geo = loader.loadToVao(positions, textureCoords, indices);
		Material material = new Material(loader.loadTexture("redhead"));
		Mesh mesh = new Mesh(geo, material);
		Entity entity = new Entity(mesh, new Vector3f(0, 0, -2), 0, 0, 0, 1);
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested())
		{
			camera.move();
			entity.rotate(1, 1, 0);
			renderer.prepare();
			// Tick
			// Render
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}

package com.thinmatrix.tutorial.test;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.thinmatrix.tutorial.core.DisplayManager;
import com.thinmatrix.tutorial.model.Geometry;
import com.thinmatrix.tutorial.model.Mesh;
import com.thinmatrix.tutorial.core.Loader;
import com.thinmatrix.tutorial.core.OBJloader;
import com.thinmatrix.tutorial.core.Renderer;
import com.thinmatrix.tutorial.entities.Camera;
import com.thinmatrix.tutorial.entities.Entity;
import com.thinmatrix.tutorial.entities.Light;
import com.thinmatrix.tutorial.shaders.StaticShader;
import com.thinmatrix.tutorial.textures.Material;

public class GameMainLoop
{
	//	https://www.youtube.com/watch?v=X6KjDwA7mZg
	//	3:13

	public GameMainLoop()
	{
		
	}

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		Geometry geo = OBJloader.loadOBJGeometry("StarBall", loader);
		Material material = new Material(loader.loadTexture("StarBall"));
		material.setShineDumper(5);
		material.setReflectivity(0.0f);
		Mesh mesh = new Mesh(geo, material);
		
		Entity entity = new Entity(mesh, new Vector3f(0, -0.0f, -5), 0, 0, 0, 1);
		Camera camera = new Camera();
		Light light = new Light(new Vector3f(0, 0.0f, -3.5f), new Vector3f(1.0f, 1.0f, 1.0f));
		
		while(!Display.isCloseRequested())
		{
			camera.move();
			entity.rotate(0, 1, 0);
			renderer.prepare();
			// Tick
			// Render
			shader.start();
			shader.loadLight(light);
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

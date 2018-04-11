package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop
{
	// https://www.youtube.com/watch?v=X6KjDwA7mZg
	// 12:50

	public MainGameLoop()
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
				-0.5f,0.5f,0,   
                -0.5f,-0.5f,0,  
                0.5f,-0.5f,0,   
                0.5f,0.5f,0,        
                 
                -0.5f,0.5f,1,   
                -0.5f,-0.5f,1,  
                0.5f,-0.5f,1,   
                0.5f,0.5f,1,
                 
                0.5f,0.5f,0,    
                0.5f,-0.5f,0,   
                0.5f,-0.5f,1,   
                0.5f,0.5f,1,
                 
                -0.5f,0.5f,0,   
                -0.5f,-0.5f,0,  
                -0.5f,-0.5f,1,  
                -0.5f,0.5f,1,
                 
                -0.5f,0.5f,1,
                -0.5f,0.5f,0,
                0.5f,0.5f,0,
                0.5f,0.5f,1,
                 
                -0.5f,-0.5f,1,
                -0.5f,-0.5f,0,
                0.5f,-0.5f,0,
                0.5f,-0.5f,1
		};
		
		int[] indices =
		{
				0,1,3,  
                3,1,2,  
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,   
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22
		};
		
		float[] textCoords =
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
		
//		RawModel model = loader.loadToVAO(positions, textCoords, indices);
		RawModel model = OBJLoader.loadModel("susane", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("Susan_Skin"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -5f), 0, 0, 0, 1);
		Light light = new Light(new Vector3f(0, 0, -4), new Vector3f(1, 1, 1));
		texture.setShineDampler(0);
		texture.setReflectivity(0);
		
		RawModel geo = OBJLoader.loadModel("StarBall", loader);
		ModelTexture material = new ModelTexture(loader.loadTexture("starBall"));
		TexturedModel star = new TexturedModel(geo, material);
		material.setShineDampler(10);
		material.setReflectivity(2);
		
		Camera camera = new Camera();
		
		List<Entity> stars = new ArrayList<Entity>();
		Random random = new Random();
		
		for(int i = 0; i < 50; i++)
		{
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * -300;
			
			stars.add(new Entity(star, new Vector3f(x, y, z), random.nextFloat() * 180, random.nextFloat() * 180, 0, 1));
		}
		
		while(!Display.isCloseRequested())
		{
			entity.increaseRotation(0, 2, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			for(Entity estrella:stars)
			{
				renderer.render(estrella, shader);
			}
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}

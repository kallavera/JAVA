package com.elegantwhelp.tutorial.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.elegantwhelp.tutorial.core.camera.Camera;
import com.elegantwhelp.tutorial.core.graphics.Texture;
import com.elegantwhelp.tutorial.core.inputs.Input;
import com.elegantwhelp.tutorial.core.models.Model;
import com.elegantwhelp.tutorial.core.shaders.Shader;

public class Game
{
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final String TITLE = "LWJGL3 ElegantWhelp Tutorial";
	
	private long window = 0;
	private float x, y;
	private float green = 0;
	
	private Input input;
	private Texture myTexture;
	private Model model;
	private Shader shader;
	private Camera camera;
	
	private float[] vertices =
	{
		-0.5f,  0.5f, 0,	//0
		 0.5f,  0.5f, 0,	//1
		 0.5f, -0.5f, 0,	//2
		-0.5f, -0.5f, 0		//3
	};
	
	float[] tex =
	{
		0, 0,	//0
		1, 0,	//1
		1, 1,	//2
		0, 1	//3
	};
	
	int[] indices =
	{
		0, 1, 2,
		2, 3, 0
	};
	
	Matrix4f scale = new Matrix4f()
			.translate(x, y, 0)
			.scale(300);
	
	Matrix4f target = new Matrix4f();
	
	public Game()
	{
		init();
		
		prepare();
		
		setObjects();
		
		gameLoop();
		
		glfwTerminate();
	}
	
	private void init()
	{
		System.out.println("ElegantWhelp LWJGL3 Tutorial");
		if(!glfwInit())
		{
			throw new IllegalStateException("[ERROR]: Fallo al iniciar GLFW");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, 0, 0);
//		window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, glfwGetPrimaryMonitor(), 0);	// FullScreen
		
		if(window == 0)
		{
			throw new IllegalStateException("[ERROR]: Fallo al crear la ventana");
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (videoMode.width() - WIDTH) / 2, (videoMode.height() - HEIGHT) / 2);
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		
		glfwShowWindow(window);
		
		x = 0;
		y = 0;
	}
	
	private void prepare()
	{
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
//		glClearColor(0.06f, 0.13f, 0.447f, 1.0f);
		glClearColor(0.0f, 0.f, 0.0f, 1.0f);
	}
	
	private void setObjects()
	{
		input = new Input(window);
		input.init();
		
		shader = new Shader("shader");
		
		myTexture = new Texture("./res/img_01.jpg");
		
		model = new Model(vertices, tex, indices);
		
		scale = new Matrix4f().scale(300);
		
		camera = new Camera(WIDTH, HEIGHT);
	}
	
	private void gameLoop()
	{
		double frame_cap = 1.0/60.0;
		
		double time = Timer.getTime();
		
		double delta = 0;
		
		while(!glfwWindowShouldClose(window))
		{
			double now = Timer.getTime();
			
			delta += now - time;
			
			time = now;
			
			while(delta >= frame_cap)
			{
				glfwPollEvents();
				
				glClear(GL_COLOR_BUFFER_BIT);
				
				update();
				
				glEnable(GL_TEXTURE_2D);

				render();
				
				glfwSwapBuffers(window);
				
				delta -= frame_cap;
			}
		}
	}
	
	private void update()
	{
		input.update();
		
		if(input.isUp()) {y += 1f;}
		if(input.isDown()) {y -= 1f;}
		if(input.isRight()) {x += 1f;}
		if(input.isLeft()) {x -= 1f;}
		
		if(input.isFire()) {System.out.println("PUM!!!");}
		
		if(input.isClose()) {glfwSetWindowShouldClose(window, true);}
		
		target = scale;
		
		camera.setPosition(new Vector3f(x, y, 0));
	}
	
	private void render()
	{
		shader.bind();
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", camera.getProjection().mul(target));
		
		myTexture.bind(0);
		
		model.render();
	}
}

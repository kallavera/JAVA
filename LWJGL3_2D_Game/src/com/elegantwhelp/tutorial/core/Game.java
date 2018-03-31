package com.elegantwhelp.tutorial.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.elegantwhelp.tutorial.core.graphics.Texture;
import com.elegantwhelp.tutorial.core.inputs.Input;
import com.elegantwhelp.tutorial.core.models.Model;
import com.elegantwhelp.tutorial.core.shaders.Shader;

public class Game
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "LWJGL3 ElegantWhelp Tutorial";
	
	private long window = 0;
	private float x, y;
	
	private Input input;
	private Texture myTexture;
	private Model model;
	private Shader shader;
	
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
		
//		myTexture = new Texture("./res/img_01.jpg");
		
		model = new Model(vertices, tex, indices);
	}
	
	private void gameLoop()
	{
		while(!glfwWindowShouldClose(window))
		{
			glfwPollEvents();
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			update();
			
//			myTexture.bind();
			glEnable(GL_TEXTURE_2D);

			render();
			
			glfwSwapBuffers(window);
		}
	}
	
	private void update()
	{
		input.update();
		
		if(input.isUp()) {y += 0.0001f;}
		if(input.isDown()) {y -= 0.0001f;}
		if(input.isRight()) {x += 0.0001f;}
		if(input.isLeft()) {x -= 0.0001f;}
		
		if(input.isFire()) {System.out.println("PUM!!!");}
		
		if(input.isClose()) {glfwSetWindowShouldClose(window, true);}
	}
	
	private void render()
	{
		shader.bind();
		model.render();
	}
}

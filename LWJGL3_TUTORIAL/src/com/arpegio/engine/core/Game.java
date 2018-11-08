package com.arpegio.engine.core;

public class Game
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Arpegio Engine";

	public static void main(String[] args)
	{
		Engine arpegio = new Engine();
		arpegio.createDisplay(WIDTH, HEIGHT, TITLE);
	}
}

package com.elegantwhelp.tutorial.core;

public class Timer
{

	public Timer()
	{
		
	}
	
	public static double getTime()
	{
		return (double) System.nanoTime() / (double) 1_000_000_000;
	}

}

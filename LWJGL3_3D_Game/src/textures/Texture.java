package textures;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture
{
	private int textureID;
	private int width;
	private int height;

	public Texture(int id)
	{
		this.textureID = id;
	}
	
	public int getID()
	{
		return this.textureID;
	}

	public void bind(int sampler)
	{
		if (sampler >= 0 && sampler <= 31)
		{
			glActiveTexture(GL_TEXTURE0 + sampler);
			glBindTexture(GL_TEXTURE_2D, textureID);
		}
	}
}

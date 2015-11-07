package org.jglrxavpok.games;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * 
 * @author jglrxavpok
 *
 */
public final class AssetsManager
{

	private static HashMap<String, BufferedImage>	imgs = new HashMap<String, BufferedImage>();
	private static String	credits;

	public static BufferedImage getImage(String name)
	{
		if(imgs.containsKey(name) && imgs.get(name) != null)
		{
			return imgs.get(name);
		}
		try
		{
			BufferedImage buf = ImageIO.read(AssetsManager.class.getResource("/images/"+name));
			imgs .put(name, buf);
			return buf;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static InputStream getSound(String name)
	{
		return AssetsManager.class.getResourceAsStream("/sounds/"+name);
	}
}

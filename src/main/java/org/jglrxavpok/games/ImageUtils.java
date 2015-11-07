package org.jglrxavpok.games;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ImageUtils
{

	public static BufferedImage[] cutImagesFromSet(BufferedImage set, int numberOfImagesX, int numberOfImagesY)
	{
		int x =0, y=0;
		int w1 = set.getWidth()/numberOfImagesX;
		int h1 = set.getHeight()/numberOfImagesY;
		BufferedImage[] imgs1= new BufferedImage[numberOfImagesX*numberOfImagesY];
		for(int j=0;j<imgs1.length;j++)
		{
			try
			{
			
			imgs1[j] = set.getSubimage(x, y, w1, h1);
			x+=w1;
			if(x+w1 > set.getWidth())
			{
				x=0;
				y+= h1;
			}
			if(y+h1 > set.getHeight())
			{
				break;
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();
		x+=w1;
		if(x >= set.getWidth())
		{
			x=0;
			y+= h1;
		}
		if(y >= set.getHeight())
		{
			break;
		}
	
		}
		}
		
		return imgs1;
	}

	public static BufferedImage getImageFromClass(String name, Class<?> from)
	{
		try
		{
			return ImageIO.read(from.getResource(name));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage rotate(BufferedImage im, int angle)
	{
		double cos = Math.cos(Math.PI*angle/180.);
		double sin = Math.sin(Math.PI*angle/180.);
		int L = im.getWidth(), H = im.getHeight();
		int Xc = L/2, Yc = H/2;
		int L2 = (int) (L*Math.abs(cos)+H*Math.abs(sin));
		int H2 = (int) (L*Math.abs(sin)+H*Math.abs(cos));
		int Xc2 = L2/2, Yc2 = H2/2;
		BufferedImage  imro = new BufferedImage(L2,H2,BufferedImage.TYPE_INT_ARGB);
		for (int y2=0;y2<H2;y2++) {
			for (int x2=0;x2<L2;x2++) {
				int x = (int) Math.round( Xc + (x2-Xc2)*cos - (y2-Yc2)*sin );
				int y = (int) Math.round( Yc + (x2-Xc2)*sin + (y2-Yc2)*cos );
	 
				if (x>=0 && x<L && y>=0 && y<H)
					imro.setRGB(x2, y2, im.getRGB(x,y));
			}
		}
		return imro;
	}
	
	public static BufferedImage toBufferedImage(Image i)
	{
		BufferedImage bi = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		bi.createGraphics().drawImage(i, null, null);
		return bi;
	}
}

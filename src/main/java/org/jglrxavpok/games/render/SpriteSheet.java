package org.jglrxavpok.games.render;

import java.awt.image.BufferedImage;

public class SpriteSheet
{

	private IDrawingPixmap[][]	sprites;

	public SpriteSheet(IDrawingPixmap[][] sprites)
	{
		this.sprites = sprites; 
	}
	
	public SpriteSheet(BufferedImage img, int numberOnX, int numberOnY, IDrawingCanvas canvas)
	{
		BufferedImage[][] cuts = cut(img, numberOnX, numberOnY);
		IDrawingPixmap[][] sprites = new IDrawingPixmap[numberOnX][numberOnY];
		for(int i = 0;i<numberOnY;i++)
		{
			for(int a = 0;a<numberOnX;a++)
			{
				sprites[a][i] = canvas.load(cuts[a][i]);
			}
		}
		this.sprites = sprites;
	}

	private BufferedImage[][] cut(BufferedImage img, int numberOnX, int numberOnY)
	{
		int x1 = 0, y1 = 0, x = 0, y = 0;
		int w = img.getWidth()/numberOnX;
		int h = img.getHeight()/numberOnY;
		BufferedImage[][] result = new BufferedImage[numberOnX][numberOnY];
		while(y+h<img.getHeight())
		{
			while(x+w<img.getWidth())
			{
				x+=w;
				result[x1][y1] = img.getSubimage(x, y, w, h);
				x1++;
			}
			y+=h;
			x=0;
			x1 = 0;
			y1++;
		}
		return result;
	}
	
	public IDrawingPixmap[][] getSprites()
	{
		return sprites;
	}
	
	public void setSpriteAt(IDrawingPixmap sprite, int x, int y)
	{
		sprites[x][y] = sprite;
	}
}

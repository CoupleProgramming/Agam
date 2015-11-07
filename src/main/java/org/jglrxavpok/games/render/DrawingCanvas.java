package org.jglrxavpok.games.render;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class DrawingCanvas extends DrawingPixmap implements IDrawingCanvas, Serializable {

	private static final long	serialVersionUID	= 1439468812542433621L;
	public transient BufferedImage image;
	protected int xOffset, yOffset;

	@Override
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public DrawingCanvas(int w, int h) {
		super(w, h);
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	@Override
	public void blit(IDrawingPixmap bitmap, double x, double y) {
		blit(bitmap, (int) x, (int) y);
	}

	@Override
	public void blit(IDrawingPixmap bitmap, int x, int y) {
		super.blit(bitmap, x + xOffset, y + yOffset);
	}

	@Override
	public void blit(IDrawingPixmap bitmap, int x, int y, int w, int h) {
		super.blit(bitmap, x + xOffset, y + yOffset, w, h);
	}

	@Override
	public void alphaBlit(IDrawingPixmap bitmap, int x, int y, int alpha) {
		super.alphaBlit(bitmap, x + xOffset, y + yOffset, alpha);
	}

	@Override
	public void colorBlit(IDrawingPixmap bitmap, double x, double y, int color) {
		colorBlit(bitmap, (int) x, (int) y, color);
	}

	@Override
	public void colorBlit(IDrawingPixmap bitmap, int x, int y, int color) {
		super.colorBlit(bitmap, x + xOffset, y + yOffset, color);
	}

	@Override
	public void fill(int x, int y, int width, int height, int color) {
		super.fill(x + xOffset, y + yOffset, width, height, color);
	}

	@Override
	public void rectangle(int x, int y, int width, int height, int color) {
		super.rectangle(x + xOffset, y + yOffset, width, height, color);
	}

	@Override
	public IDrawingPixmap rectangleBitmap(int x, int y, int x2, int y2, int color) {
		DrawingPixmap rect = new DrawingPixmap(x2, y2);
		rect.rectangle(x, y, x2, y2, color);
		return rect;
	}

	@Override
	public IDrawingPixmap rangeBitmap(int radius, int color) {
		DrawingPixmap circle = new DrawingPixmap(radius * 2 + 100, radius * 2 + 100);

		circle.circleFill(radius, radius, radius, color);
		return circle;
	}

	@Override
	public IDrawingPixmap tooltipBitmap(int width, int height) 
	{
		int cRadius = 3;
		int color = Color.black.getRGB();
		DrawingPixmap tooltip = new DrawingPixmap(width + 3, height + 3);
		tooltip.fill(0, cRadius, width, height - 2 * cRadius, color);
		tooltip.fill(cRadius, 0, width - 2 * cRadius, height, color);
		// dessin des coins arrondis
		tooltip.circleFill(cRadius, cRadius, cRadius, color);
		tooltip.circleFill(width - cRadius - 1, cRadius, cRadius, color);
		tooltip.circleFill(width - cRadius - 1, height - cRadius - 1, cRadius, color);
		tooltip.circleFill(cRadius, height - cRadius - 1, cRadius, color);

		return tooltip;
	}
	
	/**
	 * Méthode inutilisée pour le moment dans la classe <b>DrawingCanvas.<font color="purple">class</font></b>
	 */
	@Override
	public void loadResources() 
	{
		
	}

	@Override
	public IDrawingPixmap createBitmap(int w, int h) {
		return new DrawingPixmap(w, h);
	}

	@Override
	public IDrawingPixmap createBitmap(int[][] pixels2D) {
		return new DrawingPixmap(pixels2D);
	}

	@Override
	public IDrawingPixmap shrink(IDrawingPixmap bitmap) {
		return bitmap.shrink();
	}

	@Override
	public IDrawingPixmap scaleBitmap(IDrawingPixmap bitmap, int width, int height) {
		return bitmap.scaleBitmap(width, height);
	}

	@Override
	public IDrawingPixmap load(String pathFile) {
		try {
			BufferedImage bi = ImageIO.read(DrawingCanvas.class.getResource(pathFile));
			return load(bi);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public IDrawingPixmap load(BufferedImage image) {
		if (image == null) return null;
		int width = image.getWidth();
		int height = image.getHeight();

		return new DrawingPixmap(width, height,image.getRGB(0, 0, width, height, null, 0, width));
	}

	@Override
	public IDrawingPixmap[][] cut(String pathFile, int w, int h) {
		return cut(pathFile, w, h, 0, 0);
	}

	@Override
	public IDrawingPixmap[][] cut(String pathFile, int w, int h, int bx, int by) {
		try {
			BufferedImage bi = ImageIO.read(DrawingCanvas.class.getResource(pathFile));

			int xTiles = (bi.getWidth() - bx) / w;
			int yTiles = (bi.getHeight() - by) / h;

			DrawingPixmap[][] result = new DrawingPixmap[xTiles][yTiles];

			for (int x = 0; x < xTiles; x++) {
				for (int y = 0; y < yTiles; y++) {
					result[x][y] = new DrawingPixmap(w, h);
					bi.getRGB(bx + x * w, by + y * h, w, h,
						result[x][y].pixels, 0, w);
				}
			}

			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int[][] getColors(IDrawingPixmap[][] tiles) {
		int[][] result = new int[tiles.length][tiles[0].length];
		for (int y = 0; y < tiles[0].length; y++) {
			for (int x = 0; x < tiles.length; x++) {
				result[x][y] = getColor(tiles[x][y]);
			}
		}
		return result;
	}

	@Override
	public int getColor(IDrawingPixmap bitmap) {
		DrawingPixmap mb = (DrawingPixmap) bitmap;
		int r = 0;
		int g = 0;
		int b = 0;
		for (int i = 0; i < mb.pixels.length; i++) {
			int col = mb.pixels[i];
			r += (col >> 16) & 0xff;
			g += (col >> 8) & 0xff;
			b += (col) & 0xff;
		}

		r /= mb.pixels.length;
		g /= mb.pixels.length;
		b /= mb.pixels.length;

		return 0xff000000 | r << 16 | g << 8 | b;
	}

	@Override
	public DrawingPixmap[][] cutv(String string, int h) {
		try {
			BufferedImage bi = ImageIO.read(DrawingCanvas.class.getResource(string));

			int yTiles = bi.getHeight() / h;

			int xTiles = 0;
			DrawingPixmap[][] result = new DrawingPixmap[yTiles][];
			for (int y = 0; y < yTiles; y++) {
				List<DrawingPixmap> row = new ArrayList<DrawingPixmap>();
				int xCursor = 0;
				while (xCursor < bi.getWidth()) {
					int w = 0;
					while (xCursor + w < bi.getWidth() && bi.getRGB(xCursor + w, y * h) != 0xffed1c24) {
						w++;
					}
					if (w > 0) {
						DrawingPixmap bitmap = new DrawingPixmap(w, h);
						bi.getRGB(xCursor, y * h, w, h, bitmap.pixels, 0, w);
						row.add(bitmap);
					}
					xCursor += w + 1;
				}
				if (xTiles < row.size()) {
					xTiles = row.size();
				}
				result[y] = row.toArray(new DrawingPixmap[0]);
			}

			DrawingPixmap[][] resultT = new DrawingPixmap[xTiles][yTiles];
			for (int x = 0; x < xTiles; x++) {
				for (int y = 0; y < yTiles; y++) {
					try {
						resultT[x][y] = result[y][x];
					} catch (IndexOutOfBoundsException e) {
						resultT[x][y] = null;
					}
				}
			}

			return resultT;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getOffsetX()
	{
		return this.xOffset;
	}
	
	public int getOffsetY()
	{
		return this.yOffset;
	}
}
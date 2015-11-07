package org.jglrxavpok.games.render;

import java.awt.image.BufferedImage;

public interface IDrawingCanvas {

	public void clear(int color);

	public int getWidth();

	public int getHeight();

	public void loadResources();

	public void setOffset(int xOffset, int yOffset);

	public IDrawingPixmap createBitmap(int w, int h);

	public IDrawingPixmap createBitmap(int[][] pixels2D);

	public void blit(IDrawingPixmap bitmap, double x, double y);
	public void blit(IDrawingPixmap bitmap, int x, int y);

	public void blit(IDrawingPixmap bitmap, int x, int y, int width, int height);

	public void colorBlit(IDrawingPixmap bitmap, double x, double y, int color);
	public void colorBlit(IDrawingPixmap bitmap, int x, int y, int color);

	public void fill(int x, int y, int width, int height, int color);

	public void alphaBlit(IDrawingPixmap bitmap, int x, int y, int alpha);

	public void alphaFill(int x, int y, int width, int height, int color, int alpha);

	public IDrawingPixmap load(String pathFile);
	public IDrawingPixmap load(BufferedImage image);

	public IDrawingPixmap[][] cut(String pathFile, int w, int h);

	public IDrawingPixmap[][] cut(String pathFile, int w, int h, int bx, int by);

	public int[][] getColors(IDrawingPixmap[][] tiles);

	public int getColor(IDrawingPixmap bitmap);

	public IDrawingPixmap[][] cutv(String string, int h);

	public IDrawingPixmap shrink(IDrawingPixmap bitmap);

	public IDrawingPixmap scaleBitmap(IDrawingPixmap bitmap, int width, int height);
	public IDrawingPixmap rectangleBitmap(int x, int y, int x2, int y2, int color) ;
	public IDrawingPixmap rangeBitmap(int radius, int color);
	public IDrawingPixmap tooltipBitmap(int width, int height);
	public void rectangle(int x, int y, int bw, int bh, int color);
}

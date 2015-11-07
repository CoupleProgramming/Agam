package org.jglrxavpok.games.render;

public interface IDrawingPixmap {

	public int getWidth();

	public int getHeight();

	public IDrawingPixmap copy();

	public void clear(int color);

	public int blendPixels(int backgroundColor, int pixelToBlendColor);

	public void blit(IDrawingPixmap bitmap, int x, int y);

	public void blit(IDrawingPixmap bitmap, int x, int y, int width, int height);

	public void alphaBlit(IDrawingPixmap bitmap, int x, int y, int alpha);

	public void colorBlit(IDrawingPixmap bitmap, int x, int y, int color);

	public void alphaFill(int x, int y, int width, int height, int color, int alpha);

	public void fill(int x, int y, int width, int height, int color);

	public void rectangle(int x, int y, int bw, int bh, int color);

	public IDrawingPixmap shrink();

	public IDrawingPixmap scaleBitmap(int width, int height);
	
	public int getPixel(int pos);
	public int getPixelSize();
	public void setPixel(int pos, int color);
}

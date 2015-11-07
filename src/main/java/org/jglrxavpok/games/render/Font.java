package org.jglrxavpok.games.render;

import org.jglrxavpok.games.Game;

import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class Font {

	public enum Align {
		LEFT, CENTERED, RIGHT
	}

	public static final Font FONT_BLUE, FONT_GOLD,  FONT_GRAY, FONT_RED, FONT_BLACK_SMALL, FONT_GOLD_SMALL, FONT_WHITE_SMALL;

	private static Font defaultFont;

	/**
	 * Build predefined fonts
	 */
	static {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ    " + "0123456789-.!?/%$\\=*+,;:()&#\"'";
		int glyphHeight = 8;
		int systemFontHeight = 10;
		int heightOffset = systemFontHeight - glyphHeight + 1;
		int spacing = 0;

		java.awt.Font fallbackFont = new java.awt.Font("SansSerif", java.awt.Font.BOLD, systemFontHeight);
		java.awt.Font systemFont = null;
		try {
			InputStream fontStream = Font.class.getResourceAsStream("/assets/assets/fonts/ubuntu-font-family-0.80/Ubuntu-B.ttf");
			systemFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontStream);
			systemFont = systemFont.deriveFont((float)systemFontHeight);
		} catch (FontFormatException e) {
			e.printStackTrace();
			systemFont = fallbackFont;
		} catch (IOException e) {
			e.printStackTrace();
			systemFont = fallbackFont;
		}

		Color shadowColor = Color.BLACK;
		Color[] blueGradient = {
				new Color(0x91aaf1),
				new Color(0x9a9ef2),
				new Color(0xd7d6fa),
				new Color(0xffffff),
				new Color(0xafadf5),
				new Color(0x5c66ea),
				new Color(0x8ab5f0)
		};
		FontCharacterFactory characterFactory;
		characterFactory = new FontCharacterFactory(systemFont, fallbackFont, blueGradient, shadowColor, heightOffset);
		FONT_BLUE = new Font(Game.instance.canvas.cut("/assets/assets/images/font_blue.png", 8, 8), letters, glyphHeight,spacing, characterFactory);

		Color[] goldGradient = {
				new Color(241, 216, 145),
				new Color(242, 236, 153),
				new Color(250, 250, 214),
				new Color(255, 255, 255),
				new Color(250, 250, 214),
				new Color(234, 221, 91),
				new Color(240, 195, 137)};
		characterFactory = new FontCharacterFactory(systemFont, fallbackFont, goldGradient, shadowColor, heightOffset);
		FONT_GOLD = new Font(Game.instance.canvas.cut("/assets/assets/images/font_gold.png", 8, 8), letters, glyphHeight, spacing, characterFactory);

		Color[] grayGradient = {
				new Color(0xb2b2b2),
				new Color(0xb7b7b7),
				new Color(0xd6d6d6),
				new Color(0xececec),
				new Color(0xc1c1c1),
				new Color(0x969696),
				new Color(0xaeaeae)
		};
		characterFactory = new FontCharacterFactory(systemFont, fallbackFont, grayGradient, shadowColor, heightOffset);
		FONT_GRAY = new Font(Game.instance.canvas.cut("/assets/assets/images/font_gray.png", 8, 8), letters, glyphHeight, spacing, characterFactory);

		Color[] redGradient = {
				new Color(0xff657b),
				new Color(0xff796f),
				new Color(0xffbbad),
				new Color(0xececec),
				new Color(0xff9583),
				new Color(0xff372d),
				new Color(0xff5d8f)
		};
		characterFactory = new FontCharacterFactory(systemFont, fallbackFont, redGradient, shadowColor, heightOffset);
		FONT_RED  = new Font(Game.instance.canvas.cut("/assets/assets/images/font_red.png", 8, 8), letters, glyphHeight, spacing, characterFactory);

		letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ    " + "abcdefghijklmnopqrstuvwxyz    " + "0123456789-.!?/%$\\=*+,;:()&#\"'";

		glyphHeight = 6;
		systemFontHeight = 8;
		heightOffset = systemFontHeight - glyphHeight + 1;
		spacing = 1;

		fallbackFont = new java.awt.Font("SansSerif", java.awt.Font.PLAIN, systemFontHeight);
		try {
		InputStream fontStream = Font.class.getResourceAsStream("/assets/assets/fonts/ubuntu-font-family-0.80/Ubuntu-L.ttf");
			systemFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontStream);
			systemFont = systemFont.deriveFont((float)systemFontHeight);
		} catch (FontFormatException e) {
			e.printStackTrace();
			systemFont = fallbackFont;
		} catch (IOException e) {
			e.printStackTrace();
			systemFont = fallbackFont;
		}

		shadowColor = new Color(0, 0, 0, 0);
		Color[] smallBlackGradient = {new Color(0x000000)};
		characterFactory = new FontCharacterFactory(systemFont, fallbackFont, smallBlackGradient, shadowColor, heightOffset);
		FONT_BLACK_SMALL = new Font(Game.instance.canvas.cut("/assets/assets/images/font_small_black.png", 8, 8), letters, glyphHeight, spacing, characterFactory);

		Color[] smallGoldGradient = {
				new Color(0xf0c389),
				new Color(0xf0d289),
				new Color(0xfbf5de),
				new Color(0xfbf3df),
				new Color(0xf1d891)};
		characterFactory = new FontCharacterFactory(systemFont, fallbackFont, smallGoldGradient, shadowColor, heightOffset);
		FONT_GOLD_SMALL  = new Font(Game.instance.canvas.cut("/assets/assets/images/font_small_gold.png", 8, 8),  letters, glyphHeight, spacing, characterFactory);

		Color[] smallWhiteGradient = {new Color(0xffffff)};
		characterFactory = new FontCharacterFactory(systemFont, fallbackFont, smallWhiteGradient, shadowColor, heightOffset);
		FONT_WHITE_SMALL = new Font(Game.instance.canvas.cut("/assets/assets/images/font_small_white.png", 8, 8), letters, glyphHeight, spacing, characterFactory);

		setDefaultFont(FONT_GOLD);
	}

	/**
	 * Get the default font
	 */
	public static Font defaultFont() {
		return defaultFont;
	}

	/**
	 * Set the default font
	 * 
	 * @param name Font name
	 */
	public static void setDefaultFont(Font defaultFont) {
		Font.defaultFont = defaultFont;
	}



	public IDrawingPixmap[][] getBitmapData()
	{
		return bitmapData;
	}

	public void setBitmapData(IDrawingPixmap[][] bitmapData)
	{
		this.bitmapData = bitmapData;
	}



	private IDrawingPixmap[][] bitmapData;
	private String letters;
	private int glyphHeight;
	private int letterSpacing;

	protected FontCharacterFactory fontCharacterFactory;

	protected Font(IDrawingPixmap[][] bitmapData, String letters, int glyphHeight, int letterSpacing, FontCharacterFactory characterFactory) {
		this.setBitmapData(bitmapData);
		this.letters = letters;
		this.glyphHeight = glyphHeight;
		this.letterSpacing = letterSpacing;
		this.fontCharacterFactory = characterFactory;
	}

	/**
	 * The width of the given string if drawn in this font
	 * 
	 * @param text
	 */
	public int calculateStringWidth(String text,IDrawingCanvas screen) {
		int w = 0;
		for (int i = 0; i < text.length(); i++) {
			char character = text.charAt(i);
			if(character == ' ')
			{
				w+= 10;
			}
			IDrawingPixmap image = getCharacterBitmap(character, screen);
			w += image.getWidth() + letterSpacing;
		}
		w -= letterSpacing;
		return w;
	}

	/**
	 * Get the height of the current font
	 * 
	 * @return Height (in pixels)
	 */
	public int getFontHeight() {
		return glyphHeight;
	}

	/**
	 * Draw the given text onto the given screen at the given position
	 * 
	 * @param screen IDrawingCanvas
	 * @param msg Message
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param width Maximum line width in pixels
	 */
	public void draw(IDrawingCanvas screen, String msg, int x, int y, int width) {
		int startX = x;
		int length = msg.length();
		for (int i = 0; i < length; i++) {
			char character = msg.charAt(i);
			IDrawingPixmap bitmap = getCharacterBitmap(character, screen);
			int heightOffset = 0;
			if (letters.indexOf(character) < 0) {
				heightOffset = fontCharacterFactory.getHeightOffset(character, screen);
			}
			screen.blit(bitmap, x, y+heightOffset);
			x += bitmap.getWidth() + letterSpacing;
			if(x > width - bitmap.getWidth()){
				x = startX;
				y += glyphHeight + 2;
			}
		}
	}

	/**
	 * Draw the given text onto the given screen at the given position with given opacity
	 * 
	 * @param screen IDrawingCanvas
	 * @param msg Message
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param width Maximum line width in pixels
	 * @param opaque Opacity of text. Range from 0x00 (transparent) to 0xff (opaque)
	 */
	public void drawOpaque(IDrawingCanvas screen, String msg, int x, int y, int width, int opaque) {
		int startX = x;
		int length = msg.length();
		for (int i = 0; i < length; i++) {
			char character = msg.charAt(i);
			IDrawingPixmap bitmap = getCharacterBitmap(character, screen);
			int heightOffset = 0;
			if (letters.indexOf(character) < 0) {
				heightOffset = fontCharacterFactory.getHeightOffset(character, screen);
			}
			screen.alphaBlit(bitmap, x, y + heightOffset, opaque);
			x += bitmap.getWidth() + letterSpacing;
			if (x > width - bitmap.getWidth()) {
				x = startX;
				y += glyphHeight + 2;
			}
		}
	}
  	 
	private IDrawingPixmap getCharacterBitmap(char character, IDrawingCanvas screen) {
//		int charPosition = letters.indexOf(character);
	/*	if (charPosition >= 0) {
			return bitmapData[charPosition % 30][charPosition / 30];
		} else {*/
			return fontCharacterFactory.getFontCharacter(character, screen);
		//}
	}

	/**
	 * 
	 * * Draw the given text onto the given screen at the given position with given opacity
	 * 
	 * @param screen IDrawingCanvas
	 * @param msg Message
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param opaque Opacity of text. Range from 0x00 (transparent) to 0xff (opaque)
	 */
	public void drawOpaque(IDrawingCanvas screen, String msg, int x, int y, int opaque) {
		drawOpaque(screen, msg, x, y, Integer.MAX_VALUE, opaque);
	}

	/**
	 * Draw the given text onto the given screen at the given position
	 * 
	 * @param screen IDrawingCanvas
	 * @param msg Message
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void draw(IDrawingCanvas screen, String msg, int x, int y) {
		draw(screen, msg, x, y, Integer.MAX_VALUE);
	}
	
	/**
	 * Draw the given text onto the given screen, centered.
	 * 
	 * Will never be split into several lines of text
	 * 
	 * @Deprecated use draw(IDrawingCanvas screen, String msg, int x, int y, Font.Align align) instead
	 * 
	 * @param screen 
	 * @param msg 
	 * @param x 
	 * @param y 
	 */
	@Deprecated
	public void drawCentered(IDrawingCanvas screen, String msg, int x, int y) {
		int width = calculateStringWidth(msg, screen);
		draw(screen, msg, x - width / 2, y - 4);
	}
	
	/**
	 * Draw the given text onto the given screen, aligned to the right.
	 * 
	 * Will never be split into several lines of text
	 * 
	 * @param screen 
	 * @param msg 
	 * @param x 
	 * @param y
	 * @param align
	 */
	public void draw(IDrawingCanvas screen, String msg, int x, int y, Align align) {
		if (Align.LEFT.equals(align)) {
			draw(screen, msg, x, y, Integer.MAX_VALUE);
		}
		else {
			int width = calculateStringWidth(msg, screen);
			if (Align.CENTERED.equals(align)) {
				draw(screen, msg, x - width / 2, y - 4);
			} else {
				draw(screen, msg, x - width, y);
			}	
		}
	}
	
	
	/**
	 * Returns the text length in pixels
	 * 
	 * @param msg
	 * @return int the text width in pixels
	 */
	public int getTextWidth(String msg, IDrawingCanvas screen) {
		int x = 0;
		int length = msg.length();
		
		for (int i = 0; i < length; i++)
			x += getCharacterBitmap(msg.charAt(i), screen).getWidth() + letterSpacing;
		
		return x;
	}
}

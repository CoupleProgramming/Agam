package org.jglrxavpok.games;

import java.awt.Component;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Keyboard implements KeyListener
{

	private int	eventKey;
	private int	releasedKey;
	
	public HashMap<Integer, Boolean> downKeys = new HashMap<Integer, Boolean>();

	public static Keyboard current = null;
	private Robot r;
	
	public Keyboard(Component comp)
	{
		try
		{
			r = new Robot();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
		}
		comp.addKeyListener(this);
		current = this;
	}
	
	public int getEventKey()
	{
		int k = eventKey;
		eventKey = -1;
		return k;
	}
	
	public void clear()
	{
		downKeys.clear();
		eventKey = -1;
	}
	
	public int getPressedKey()
	{
		return eventKey;
	}
	
	public int getReleasedKey()
	{
		return releasedKey;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		eventKey = arg0.getKeyCode();
		downKeys.put(arg0.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		releasedKey = arg0.getKeyCode();
		if(arg0.getKeyCode() == eventKey)
		{
			eventKey = 0;
		}
		downKeys.put(arg0.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		
	}
	
	public boolean isKeyDown(int id)
	{
		if(downKeys.get(id) == null)
		{
			downKeys.put(id, false);
			return false;
		}
		boolean b = downKeys.get(id);
		return b;
	}
	
	/**
	 * Release a key... virtualy of course !
	 * @param id
	 */
	public void releaseKey(int id)
	{
		if(r!=null)
			r.keyRelease(id);
	}
	
	/**
	 * Generate a false pressing key event if possible.
	 * @param id
	 */
	public void pressKey(int id)
	{
		if(r!=null)
			r.keyPress(id);
	}
}

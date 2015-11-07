package org.jglrxavpok.games;

import java.awt.Component;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener
{

	public static Mouse current = null;
	
	public Mouse(Component c)
	{
		try
		{
			r = new Robot();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
		}
		c.addMouseListener(this);
		c.addMouseMotionListener(this);
		c.addMouseWheelListener(this);
		current = this;
	}
	
	private int onScreenX = 0;
	private int onScreenY = 0;
	private int mouseX = 0;
	private int mouseY = 0;
	private int mouseWheelPos = 0;
	private Robot r;
	private int	mouseWheelValue;
	private HashMap<Integer, Boolean>	buttonsDown = new HashMap<Integer, Boolean>();
	
	public int getX()
	{
		return mouseX;
	}
	
	public int getY()
	{
		return mouseY;
	}
	
	public int getMouseWheelPos()
	{
		return mouseWheelPos;
	}
	
	public int getMouseWheelRotationValue()
	{
		int tmp = mouseWheelValue;
		mouseWheelValue = 0;
		return tmp;
	}
	
	public int getXOnScreen()
	{
		return onScreenX;
	}
	
	public int getYOnScreen()
	{
		return onScreenY;
	}
	
	public void setXOnScreen(int x)
	{
		if(r != null)
		{
			r.mouseMove(x, mouseY);
		}
	}
	
	public void setPosOnScreen(int x, int y)
	{
		if(r!=null)
			r.mouseMove(x, y);
	}
	
	public void setYOnScreen(int y)
	{
		if(r != null)
		{
			r.mouseMove(mouseX, y);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0)
	{
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		onScreenX = arg0.getXOnScreen();
		onScreenY = arg0.getYOnScreen();
		mouseWheelPos = arg0.getWheelRotation();
		mouseWheelValue = arg0.getUnitsToScroll();
	}

	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		onScreenX = arg0.getXOnScreen();
		onScreenY = arg0.getYOnScreen();
	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		onScreenX = arg0.getXOnScreen();
		onScreenY = arg0.getYOnScreen();
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		onScreenX = arg0.getXOnScreen();
		onScreenY = arg0.getYOnScreen();
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		onScreenX = arg0.getXOnScreen();
		onScreenY = arg0.getYOnScreen();
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		onScreenX = arg0.getXOnScreen();
		onScreenY = arg0.getYOnScreen();
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		buttonsDown.put(arg0.getButton(), true);
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		onScreenX = arg0.getXOnScreen();
		onScreenY = arg0.getYOnScreen();
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		buttonsDown.put(arg0.getButton(), false);
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		onScreenX = arg0.getXOnScreen();
		onScreenY = arg0.getYOnScreen();
	}

	public boolean isButtonDown(int button1)
	{
		if(buttonsDown.get(button1) == null)
		{
			buttonsDown .put(button1, false);
		}
		return buttonsDown.get(button1);
	}

}

package org.jglrxavpok.games;

import java.awt.Point;

public class Rectangle {
    public int topLeftX, topLeftY;
    public int bottomRightX, bottomRightY;
    public int width , height;
    
    public Rectangle(int x, int y, int w, int h) {
        topLeftX = x;
        topLeftY = y;
        width = w;
        height = h;
        bottomRightX = topLeftX + width;
        bottomRightY = topLeftY + height;
    }
    
    public boolean intersect(Rectangle r)
    {
    	if((topLeftX >= r.topLeftX + r.width)      // trop à droite
    		    || (topLeftX + width <= r.topLeftX) // trop à gauche
    		    || (topLeftY >= r.topLeftY + r.height) // trop en bas
    		    || (this.topLeftY + this.height <= r.topLeftY))  // trop en haut
    		          return false; 
    		   else
    		          return true; 
    }
    
    public boolean isIn(Point p)
    {
    	if((p.getX() >= topLeftX + width)      // trop à droite
    		    || (p.getX() <= topLeftX) // trop à gauche
    		    || (p.getY() >= topLeftY + height) // trop en bas
    		    || (p.getY()  <= topLeftY))  // trop en haut
    		          return false; 
    		   else
    	return true;
    }
}

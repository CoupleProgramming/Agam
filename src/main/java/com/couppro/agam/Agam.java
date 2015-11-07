package com.couppro.agam;

import org.jglrxavpok.games.Game;
import org.jglrxavpok.games.render.*;

import java.awt.Dimension;

public class Agam extends Game {

    private Font font;

    @Override
    public void init() {
        font = Font.defaultFont();
    }

    @Override
    public String getName() {
        return "Agam";
    }

    @Override
    public void render(float delta, DrawingCanvas canvas) {
        canvas.fill(0, 0, canvas.getWidth()/2, canvas.getHeight(), 0xFFFFFFFF);
        canvas.rectangle(mouse.getX(), mouse.getY(), 10, 10, 0xFFFF0000);
        font.draw(canvas, "Test string", mouse.getX(), mouse.getY());
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public Dimension getResolution() {
        return new Dimension(1137,740);
    }

    public static void main(String[] args) {
        new Agam().start();
    }
}

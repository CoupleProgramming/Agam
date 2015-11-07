package com.couppro.agam;

import org.jglrxavpok.games.Game;
import org.jglrxavpok.games.render.DrawingCanvas;

import java.awt.*;

public class Agam extends Game {
    @Override
    public String getName() {
        return "Agam";
    }

    @Override
    public void render(float delta, DrawingCanvas canvas) {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void init() {

    }

    @Override
    public Dimension getResolution() {
        return new Dimension(1137,740);
    }

    public static void main(String[] args) {
        new Agam().start();
    }
}

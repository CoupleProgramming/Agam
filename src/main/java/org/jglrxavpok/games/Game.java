package org.jglrxavpok.games;

import org.jglrxavpok.games.render.DrawingCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class Game {

    public static Game instance;
    private JFrame frame;
    protected Mouse mouse;
    protected Keyboard keyboard;
    public DrawingCanvas canvas;
    boolean running;
    private GameThread thread;

    public Game() {
        instance = this;
    }

    public abstract String getName();

    public abstract void render(float delta, DrawingCanvas canvas);

    public abstract void update(float delta);

    public abstract void init();

    public abstract Dimension getResolution();

    public final void start() {
        frame = new JFrame();
        mouse = new Mouse(frame);
        keyboard = new Keyboard(frame);
        frame.setSize(getResolution());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                running = false;
            }
        });
        frame.setVisible(true);

        canvas = new DrawingCanvas(getResolution().width, getResolution().height);

        running = true;
        thread = new GameThread(this);
        init();
        thread.start();
    }

    public final void shutdown() {
        running = false;
    }

    public final int getFPS() {
        return thread.fps;
    }

    public final int getUPS() {
        return thread.ups;
    }

    public JFrame getFrame() {
        return frame;
    }

    public final void dispose() {
        frame.dispose();
    }
}

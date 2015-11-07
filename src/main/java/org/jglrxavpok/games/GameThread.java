package org.jglrxavpok.games;

import java.awt.*;
import java.awt.image.*;

import org.jglrxavpok.games.render.DrawingCanvas;

public class GameThread extends Thread
{
    private final Game game;
    private boolean      alive                    = true;
    private int          frame;
    int          fps;
    private double       gameHertz;
    //Calculate how many ns each frame should take for our target game hertz.
    private double       timeBetweenUpdates       = 1000000000 / gameHertz;
    //At the very most we will update the game this many times before a new render.
    //If you're worried about visual hitches more than perfect timing, set this to 1.
    private final int    maxUpdatesBeforeRenders  = 5;
    //We will need the last update time.
    private double       lastUpdateTime           = System.nanoTime();
    //Store the last time we rendered.
    private double       lastRenderTime           = System.nanoTime();

    //If we are able to get as high as this FPS, don't render again.
    private final double targetFPS                = 25;
    private final double targetTimeBetweenRenders = 1000000000 / targetFPS;

    //Simple way of finding FPS.
    int                  lastSecondTime           = (int) (lastUpdateTime / 1000000000);

    boolean              paused                   = false;
    private DrawingCanvas       screen;
    public int ups;
    public int updates;

    public GameThread(Game game)
    {
        this.screen = game.canvas;
        this.game = game;
    }

    public void run()
    {
        gameHertz = 60;
        timeBetweenUpdates = 1000000000 / gameHertz;

        while(game.running)
        {
            loop();
        }
        game.dispose();
    }

    private void loop()
    {
        double now = System.nanoTime();
        int updateCount = 0;

        if(!paused)
        {
            //Do as many game updates as we need to, potentially playing catchup.
            while(now - lastUpdateTime > timeBetweenUpdates && updateCount < maxUpdatesBeforeRenders)
            {
                update();
                lastUpdateTime += timeBetweenUpdates;
                updateCount++ ;
            }

            //If for some reason an update takes forever, we don't want to do an insane number of catchups.
            //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
            if(now - lastUpdateTime > timeBetweenUpdates)
            {
                lastUpdateTime = now - timeBetweenUpdates;
            }

            //Render. To do so, we need to calculate interpolation for a smooth render.
            float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / timeBetweenUpdates));
            BufferStrategy bs = Game.instance.getFrame().getBufferStrategy();
            if(bs == null || bs.contentsLost())
            {
                Game.instance.getFrame().createBufferStrategy(2);
                return;
            }
            render(bs.getDrawGraphics(), interpolation);
            bs.show();
            lastRenderTime = now;
            //Update the frames we got.
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            frame++ ;
            if(thisSecond > lastSecondTime)
            {
                fps = frame;
                ups = updates;
                Game.instance.getFrame().setTitle(Game.instance.getName()+" - "+fps+" FPS");
                frame = 0;
                updates = 0;
                lastSecondTime = thisSecond;
            }

            if(!Game.instance.getFrame().isVisible())
                alive = false;

            //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
            while(now - lastRenderTime < targetTimeBetweenRenders && now - lastUpdateTime < timeBetweenUpdates)
            {
                Thread.yield();

                //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                try
                {
                    Thread.sleep(1);
                }
                catch(Exception e)
                {
                }

                now = System.nanoTime();
            }
        }
    }

    public void update()
    {
        Game.instance.update((float) (1.0/gameHertz));
        updates++;
    }

    public void render(Graphics g, float interpolation)
    {
        screen.fill(0,0,screen.getWidth(),screen.getHeight(),0xFF000000);
        Game.instance.render(interpolation, screen);
        g.drawImage(screen.image, 0, 0, null);
    }
}

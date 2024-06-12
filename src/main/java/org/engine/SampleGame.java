package org.engine;

import org.engine.graph.Render;
import org.engine.scene.Scene;

public class SampleGame implements GameLogicInterface {

    private int direction = 0;

    private float color = 0.0f;

    private final Render renderer;

    public SampleGame() throws Exception {
        renderer = new Render();
    }


    @Override
    public void cleanup() {

    }

    @Override
    public void init(Window window, Scene scene, Render render) {

    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {

    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {

    }
}
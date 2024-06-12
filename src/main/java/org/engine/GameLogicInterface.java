package org.engine;

import org.engine.graph.Render;
import org.engine.scene.Scene;

public interface GameLogicInterface {

    void cleanup();

    void init(Window window, Scene scene, Render render);

    void input(Window window, Scene scene, long diffTimeMillis);

    void update(Window window, Scene scene, long diffTimeMillis);
}

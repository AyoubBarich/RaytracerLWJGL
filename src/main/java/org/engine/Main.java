package org.engine;

import org.engine.graph.Mesh;
import org.engine.graph.Render;
import org.engine.scene.Scene;

public class Main implements GameLogicInterface {

    public static void main(String[] args) {
        Main main = new Main();
        org.engine.Engine gameEng = new Engine("VIEW", new Window.WindowOptions(), main);
        gameEng.start();
    }

    @Override
    public void cleanup() {
        // Nothing to be done yet
    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        float[] positions = new float[]{
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
                0.5f, -0.5f, 0.0f

        };
        int[] indicies = new int[]{0,2,1,1,2,3};
        float[] colors = new float[]{
                0.5f, 0.5f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };


        Mesh mesh = new Mesh(positions,indicies,colors);
        scene.addMesh("quad", mesh);
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        // Nothing to be done yet
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
        // Nothing to be done yet
    }
}
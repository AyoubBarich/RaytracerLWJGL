package org.engine;

import org.engine.graph.Mesh;
import org.engine.graph.Model;
import org.engine.graph.Render;
import org.engine.scene.Entity;
import org.engine.scene.Scene;
import org.joml.Vector3f;
import org.joml.Vector4f;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Main implements GameLogicInterface {
    private Entity cube;

    private float rotationAngle;
    private Vector4f camera = new Vector4f();


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
                // VO
                -0.5f, 0.5f, 0.5f,
                // V1
                -0.5f, -0.5f, 0.5f,
                // V2
                0.5f, -0.5f, 0.5f,
                // V3
                0.5f, 0.5f, 0.5f,
                // V4
                -0.5f, 0.5f, -0.5f,
                // V5
                0.5f, 0.5f, -0.5f,
                // V6
                -0.5f, -0.5f, -0.5f,
                // V7
                0.5f, -0.5f, -0.5f,
        };
        float[] colors = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.5f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.5f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        int[] indicies = new int[]{
                // Front face
                0, 1, 3, 3, 1, 2,
                // Top Face
                4, 0, 3, 5, 4, 3,
                // Right face
                3, 2, 7, 5, 3, 7,
                // Left face
                6, 1, 0, 6, 0, 4,
                // Bottom face
                2, 1, 6, 2, 6, 7,
                // Back face
                7, 6, 4, 7, 4, 5,
        };

        Mesh cubemesh = new Mesh(positions,indicies,colors);
        Model model = new Model("cube", List.of(cubemesh));
        scene.addModel(model);
        cube = new Entity("Cube","cube");
        cube.setPosition(0,0,-2);
        scene.addEntity(cube);

    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
       camera.zero();
       if (window.isKeyPressed(GLFW_KEY_W)){
           camera.z=1;
       } else if (window.isKeyPressed(GLFW_KEY_S)) {
           camera.z=-1;

       }       if (window.isKeyPressed(GLFW_KEY_Q)){

           camera.x=1;
       } else if (window.isKeyPressed(GLFW_KEY_D)) {
           camera.x=-1;

       }       if (window.isKeyPressed(GLFW_KEY_SPACE)){
           camera.y=1;
       } else if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
           camera.y=-1;
       } if (window.isMousePressed(GLFW_MOUSE_BUTTON_1)){
           camera.w=1;

       } else if (window.isMousePressed(GLFW_MOUSE_BUTTON_2)) {
           camera.w=-1;
       }
       camera.mul(diffTimeMillis/1000.0f);
       Vector3f entityPos = cube.getPosition();
       cube.setPosition(camera.x+entityPos.x,camera.y+entityPos.y,camera.z+entityPos.z);
       cube.setScale(cube.getScale()+camera.w);
       cube.updateModelMatrix();



    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
/*        rotationAngle += 1.5;
        if (rotationAngle > 360) {
            rotationAngle = 0;
        }
        cube.setRotation(1,1,1,(float) Math.toRadians(rotationAngle));
        cube.updateModelMatrix();*/
    }
}
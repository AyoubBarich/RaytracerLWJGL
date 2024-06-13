package org.engine.graph;

import org.joml.Matrix4f;
public class Projection {
    //this class let's us render depth

    private final float FOV = (float) Math.toRadians(70);
    private final float Z_NEAR=0.1f ;
    private final float Z_FAR = 1000.0f ;

    //prejection matrix
    private Matrix4f projectionMatrix;

    public Projection(int width,int height){
        projectionMatrix = new Matrix4f();
        updateProjectionMatrix(width, height);
    }

    public Matrix4f getProjectionMatrix(){return this.projectionMatrix;}
    public void updateProjectionMatrix(int width,int height){
        projectionMatrix.setPerspective(FOV,(float) (width/height),Z_NEAR,Z_FAR);
    }

}

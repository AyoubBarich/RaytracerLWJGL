package org.engine.scene;

import org.engine.graph.Mesh;
import org.engine.graph.Projection;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.Map;

public class Scene {
    private Map<String, Mesh> meshMap;
    private Projection projection;

    public Scene(int width,int height) {
        meshMap = new HashMap<>();
        this.projection = new Projection(width, height);
    }

    public void addMesh(String meshId, Mesh mesh) {
        meshMap.put(meshId, mesh);
    }

    public void cleanup() {
        meshMap.values().forEach(Mesh::cleanup);
    }

    public Map<String, Mesh> getMeshMap() {
        return meshMap;
    }

    public void resize(int width,int height){projection.updateProjectionMatrix(width, height);}
    public Matrix4f getProjectionMatrix(){return projection.getProjectionMatrix();}
}

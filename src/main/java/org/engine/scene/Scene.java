package org.engine.scene;

import org.engine.graph.Model;
import org.engine.graph.Projection;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.Map;

public class Scene {
    private Map<String, Model> modelMap;
    private Projection projection;

    public Scene(int width,int height) {
        modelMap = new HashMap<>();
        this.projection = new Projection(width, height);
    }

    public void addModel(Model model) {
        modelMap.put(model.getModelId(), model);
    }

    public void cleanup() {
        modelMap.values().forEach(Model::cleanUp);
    }

    public Map<String, Model> getModelMap() {
        return modelMap;
    }

    public void resize(int width,int height){projection.updateProjectionMatrix(width, height);}
    public Matrix4f getProjectionMatrix(){return projection.getProjectionMatrix();}

    public void addEntity(Entity entity){
        String modelId = entity.getModelId();
        Model model = modelMap.get(modelId);
        if (model == null){throw new RuntimeException("Could not find model [" + modelId + "]");}
        model.getEntitiesList().add(entity);
    }

}

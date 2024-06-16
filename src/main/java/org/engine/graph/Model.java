package org.engine.graph;

import org.engine.scene.Entity;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private final String modelId;
    private List<Entity> entitiesList;
    private List<Mesh> meshList ;

    public Model(String modelId,List<Mesh> meshList){
        this.modelId = modelId;
        this.meshList = meshList;
        this.entitiesList= new ArrayList<>();

    }
    public void cleanUp (){
        meshList.forEach(Mesh::cleanup);
    }

    public List<Entity> getEntitiesList() {
        return entitiesList;
    }

    public String getModelId() {
        return modelId;
    }

    public List<Mesh> getMeshList() {
        return meshList;
    }
}

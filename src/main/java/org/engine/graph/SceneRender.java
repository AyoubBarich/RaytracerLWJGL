package org.engine.graph;

import org.engine.scene.Entity;
import org.engine.scene.Scene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

public class SceneRender {
    private ShaderProgram shaderProgram;
    private UniformMap uniformMap;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
        createUniforms();
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render(Scene scene) {
        shaderProgram.bind();
        uniformMap.setUniform("projectionMatrix",scene.getProjectionMatrix());

        Collection<Model> modelCollection = scene.getModelMap().values();
        for (Model model : modelCollection){
            model.getMeshList().forEach(mesh ->{
                glBindVertexArray(mesh.getVaoId());
                List<Entity> entityList = model.getEntitiesList();
                for (Entity entity:entityList){
                    uniformMap.setUniform("modelMatrix",entity.getModelMatrix());
                    glDrawElements(GL_TRIANGLES,mesh.getNumVertices(),GL_UNSIGNED_INT,0);
                }
            });


        }
        glBindVertexArray(0);

        shaderProgram.unbind();
    }
    public void createUniforms(){
        uniformMap = new UniformMap(shaderProgram.getProgramId());
        uniformMap.createUniform("projectionMatrix");
        uniformMap.createUniform("modelMatrix");
    }

}

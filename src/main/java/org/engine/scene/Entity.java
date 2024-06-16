package org.engine.scene;


import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/*the Entuty class reprenstes all objects the are contatainds in the scene , it has the job of translating cordinate space and scale n rotation of every entity
* an entity is associated with  a model id and an entity Id
* */
public class Entity {

    private final String entityId;

    private final String modelId;

    private Matrix4f modelMatrix;
    private Vector3f position;
    private Quaternionf rotation;
    private float scale;


    public Entity(String entityId,String modelId){
        this.entityId = entityId;
        this.modelId = modelId;
        modelMatrix = new Matrix4f();
        position = new Vector3f();
        rotation = new Quaternionf();
        scale = 1.0f;
    }

    public String getId() {
        return entityId;
    }

    public String getModelId() {
        return modelId;
    }

    public Matrix4f getModelMatrix() {
        return modelMatrix;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setPosition(float x,float y , float z) {
        this.position.set(x,y,z);
    }
    public void setRotation(float x,float y , float z,float angle){
        this.rotation.set(x, y, z, angle);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void updateModelMatrix(){
        modelMatrix.translationRotateScale(this.position,this.rotation,this.scale);

    }
}

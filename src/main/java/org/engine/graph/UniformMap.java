package org.engine.graph;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.util.HashMap;
import java.util.Map;


import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

public class UniformMap {
    private int programId;
    private Map<String,Integer> uniforms;

    public UniformMap(int programId){
        this.programId = programId;
        uniforms = new HashMap<>();
    }

    public void createUniform(String uniformName){
        int uniformLocation = glGetUniformLocation(this.programId,uniformName);

        if (uniformLocation < 0){throw new RuntimeException("Could not find uniform [" + uniformName + "] in shader program [" +
                programId + "]");}
        uniforms.put(uniformName,uniformLocation);
    }

    public void setUniform(String uniformName, Matrix4f projectionMatrix){
        try (MemoryStack stack = MemoryStack.stackPush()){
            Integer uniformLocation = uniforms.get(uniformName);
            if (uniformLocation <0 ){
                throw new RuntimeException("Could not set uniform [" + uniformName + "] in shader program [" +
                        programId + "]");
            }
            glUniformMatrix4fv(uniformLocation.intValue(),false,projectionMatrix.get(stack.mallocFloat(16)));

        }

    }
}

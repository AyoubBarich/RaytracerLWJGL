package org.engine.graph;


import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.memFree;

public class Mesh {

    private int numVertices;
    private int vaoId;
    private int posVboId;
    private int idxVboId;
    private List<Integer> vboIdList;

    public Mesh(float[] positions, int[] indices ) {
        try (MemoryStack stack = MemoryStack.stackPush()) {

            vboIdList = new ArrayList<>();
            this.numVertices = indices.length;
            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            //Indicies VBO
            idxVboId = glGenBuffers();
            IntBuffer indiciesBuffer  = MemoryUtil.memAllocInt(indices.length);
            indiciesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER,indiciesBuffer,GL_STATIC_DRAW);
            memFree(indiciesBuffer);


            // Positions VBO
            posVboId = glGenBuffers();
            vboIdList.add(posVboId);
            FloatBuffer positionsBuffer = stack.callocFloat(positions.length);
            positionsBuffer.put(0, positions);
            glBindBuffer(GL_ARRAY_BUFFER, posVboId);
            glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }
    }



    public void cleanup() {
        glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(posVboId);
        glDeleteBuffers(idxVboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public final int getVaoId() {
        return vaoId;
    }
}
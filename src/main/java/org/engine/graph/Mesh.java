package org.engine.graph;


import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

public class Mesh {

    private int numVertices;
    private int vaoId;
    private int posVboId;
    private int idxVboId;

    private int colorVboId;

    private List<Integer> vboIdList;

    public Mesh(float[] positions, int[] indices ,float[] colors) {
        try (MemoryStack stack = MemoryStack.stackPush()) {

            vboIdList = new ArrayList<>();
            this.numVertices = indices.length;
            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);


            // Positions VBO
            posVboId = glGenBuffers();
            vboIdList.add(posVboId);
            FloatBuffer positionsBuffer = stack.callocFloat(positions.length);
            positionsBuffer.put(0, positions);
            glBindBuffer(GL_ARRAY_BUFFER, posVboId);
            glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);



            //Color VBO

            colorVboId = glGenBuffers();
            vboIdList.add(colorVboId);
            FloatBuffer colorsBuffer = stack.callocFloat(colors.length);
            colorsBuffer.put(0,colors);
            glBindBuffer(GL_ARRAY_BUFFER,colorVboId);
            glBufferData(GL_ARRAY_BUFFER,colorsBuffer,GL_STATIC_DRAW);
            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1,3,GL_FLOAT,false,0,0);



            //Indicies VBO
            idxVboId = glGenBuffers();
            vboIdList.add(idxVboId);
            IntBuffer indiciesBuffer  = stack.callocInt(indices.length);
            indiciesBuffer.put(0,indices);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER,indiciesBuffer,GL_STATIC_DRAW);


            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);


        }

    }





    public void cleanup() {
        vboIdList.forEach(GL30::glDeleteBuffers);
        glDeleteVertexArrays(vaoId);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public final int getVaoId() {
        return vaoId;
    }
}
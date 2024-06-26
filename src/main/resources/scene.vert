#version 330


layout (location =0) in vec3 position;
layout (location =1) in vec3 inColour;

out vec3 exColour;

uniform mat4 projectionMatrix;
uniform mat4 modelMatrix;

void main()
{
    gl_Position = projectionMatrix*modelMatrix*vec4(position, 1.0);
    exColour = inColour;
}
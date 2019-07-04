#version 400 core

in vec4 i_position;

out vec4 o_position;

uniform mat4 u_projection;
uniform mat4 u_transformation;

void main(void){

	vec4 worldPosition = u_transformation * vec4(i_position.xyz, 1.0);
	gl_Position = 1 * worldPosition;
	
	o_position = i_position;
}
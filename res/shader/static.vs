#version 400 core

in vec3 i_position;
out vec3 pass_position;

uniform mat4 u_projection;
uniform mat4 u_transformation;
uniform mat4 u_view;

void main(void){

	vec4 worldPosition = u_transformation * vec4(i_position, 1.0);
	
	gl_Position = u_projection * u_view * worldPosition;
	pass_position = i_position;

}
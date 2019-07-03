#version 400 core

in vec4 pass_position;

out vec3 color;

void main(void){
	color = vec3(1, 1, 1) * pass_position.xyw;
}
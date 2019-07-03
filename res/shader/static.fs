#version 400 core

in vec4 pass_position;

out vec4 color;

void main(void){
	color = vec4(1, 1, 1, 0.4) * pass_position.xyzw;
}
#version 400 core

in vec4 o_position;

out vec4 o_color;

void main(void){
	o_color = vec4(1, 1, 1, 0.4) * o_position.xyzw;
}
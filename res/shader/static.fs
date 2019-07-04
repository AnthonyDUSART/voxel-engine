#version 400 core

in vec3 pass_color;

out vec4 o_color;

void main(void){
	o_color = vec4(pass_color, 1.0);
}
#version 400 core

in vec3 pass_position;

out vec4 o_color;

void main(void){
	o_color = vec4(1, 1, 0, 0.4) * vec4(pass_position, 1.0);
}
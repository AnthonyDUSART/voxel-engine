#version 400 core

in vec4 position;

out vec4 pass_position;

void main(void){
	gl_Position = position;
	pass_position = position;
}
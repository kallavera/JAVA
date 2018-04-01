#version 120

attribute vec3 vertex_position;
attribute vec2 texture_coords;

varying vec2 tex_coords;

void main()
{
	tex_coords = texture_coords;
	gl_Position = vec4(vertex_position, 1);
}
#version 120

attribute vec3 vertex_position;
attribute vec2 texture_coords;

varying vec2 tex_coords;

uniform mat4 projection;

void main()
{
	tex_coords = texture_coords;
	gl_Position = projection * vec4(vertex_position, 1);
}
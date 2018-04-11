#version 400

in vec2 pass_textureCoords;
in vec3 surfacesNormals;
in vec3 toLight;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 lightColor;

void main(void)
{
	vec3 unitNormal = normalize(surfacesNormals);
	vec3 unitLight = normalize(toLight);
	
	float nDot1 = dot(unitNormal, unitLight);
	float brightness = max(nDot1, 0.1);
	
	vec3 diffuse  = brightness * lightColor;
	
	out_Color = vec4(diffuse, 1.0) * texture(textureSampler, pass_textureCoords);
}
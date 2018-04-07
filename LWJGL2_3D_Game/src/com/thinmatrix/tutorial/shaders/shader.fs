#version 400 core

in vec2 pass_texCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;

uniform sampler2D textureSampler;
uniform vec3 lightColor;
uniform float shineDumper;
uniform float reflectivity;

out vec4 out_Color;

void main(void)
{
	vec3 unitNormal = normalize(surfaceNormal);
	vec3 unitLightVector = normalize(toLightVector);
	
	float nDot1 = dot(unitNormal, unitLightVector);
	
	float brightness = max(nDot1, 0.15);
	vec3 diffuse = brightness * lightColor;
	
	vec3 unitToCameraVector = normalize(toCameraVector);
	vec3 lightDirection = -unitLightVector;
	vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);
	
	float specularFactor = dot(reflectedLightDirection, unitToCameraVector);
	specularFactor = max(nDot1, 0.05);
	float dampFactor = pow(specularFactor, shineDumper);
	
	vec3 finalSpecular = dampFactor * reflectivity * lightColor;
	
	out_Color = vec4(diffuse, 1.0) * texture(textureSampler, pass_texCoords) + vec4(finalSpecular, 1.0);
}
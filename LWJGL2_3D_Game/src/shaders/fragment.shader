#version 400

in vec2 pass_textureCoords;
in vec3 surfacesNormals;
in vec3 toLight;
in vec3 toCameraVector;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 lightColor;
uniform float shineDampler;
uniform float reflectivity;

void main(void)
{
	vec3 unitNormal = normalize(surfacesNormals);
	vec3 unitLight = normalize(toLight);
	
	float nDot1 = dot(unitNormal, unitLight);
	float brightness = max(nDot1, 0.1);
	
	vec3 diffuse  = brightness * lightColor;
	
	vec3 unitVectorToCamera = normalize(toCameraVector);
	vec3 lightDirection = -unitLight;
	vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);
	
	float specularFactor = dot(reflectedLightDirection, unitVectorToCamera);
	specularFactor = max(specularFactor, 0.1);
	float damplerFactor = pow(specularFactor, shineDampler);
	
	vec3 finalSpecular = damplerFactor * reflectivity * lightColor;
	
	out_Color = vec4(diffuse, 1.0) * texture(textureSampler, pass_textureCoords) + vec4(finalSpecular, 1.0);
}
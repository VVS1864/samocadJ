layout (lines) in;
layout (triangle_strip, max_vertices=4) out;
in vec4 aColor [2];
in float awidth [2];
out vec4 bColor;
uniform float current_scale;
void main ()
{
bColor = aColor[0];
float h = awidth[0] * current_scale;
float x1 = gl_in[0].gl_Position.x;
float x2 = gl_in[1].gl_Position.x;
float y1 = gl_in[0].gl_Position.y;
float y2 = gl_in[1].gl_Position.y;
float x = x2 - x1;
float y = y2 - y1;
float a = atan(y, x);
float dx = h*sin(a);
float dy = h*cos(a);

gl_Position.x = x1-dx;
gl_Position.y = y1+dy;
EmitVertex();
gl_Position.x = x1+dx;
gl_Position.y = y1-dy;
EmitVertex();
gl_Position.x = x2-dx;
gl_Position.y = y2+dy;
EmitVertex();
gl_Position.x = x2+dx;
gl_Position.y = y2-dy;
EmitVertex();
EndPrimitive();

}
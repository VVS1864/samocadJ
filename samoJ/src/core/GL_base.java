package core;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import open_dxf_lib.Color_rgb;
import open_dxf_lib.dash_type;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import samoJ.Circle;
import samoJ.GroupShape;
import samoJ.ShapeLine;
import samoJ.ObjectMode;
import samoJ.Shape;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.GLArrayDataServer;
import com.jogamp.opengl.util.GLBuffers;
import com.jogamp.opengl.util.PMVMatrix;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLUniformData;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.glsl.ShaderState;

import java.util.Random;

public class GL_base {
	static public final ShaderProgram sp0 = new ShaderProgram();
	static public ShaderState st;
	static private GLArrayDataServer interleavedVBO;
	static private GLArrayDataServer dynamic_interleavedVBO;

	public static int N = 10;// number of lines

	/**
	 * Arrays for coordinates, colors and widths:
	 */
	static float[] colors;
	static float[] vertices;
	static float[] widths;
	
	static float[] dynamic_colores;
	static float[] dynamic_vertices;
	static float[] dynamic_widths;
	static FloatBuffer fbDinamicVertices;
	
	public static float[] general_matrix = { 1, 0, 0, 0, 0, 1,
			0, 0, 0, 0, 1, 0, 0, 0, 0, 1 };

	public static float[] identity_matrix = { 1, 0, 0, 0, 0, 1,
			0, 0, 0, 0, 1, 0, 0, 0, 0, 1 };
	
	public static PMVMatrix pmvMatrix;
	/**
	 * Variable for uniform location in shader program
	 */
	public static GLUniformData pmvMatrixUniform;
	public static GLUniformData current_scaleUniform;
	
	public static boolean unproject_flag = false;
	public static float[] unproject = new float[2];
	public static float[] project = new float[2];

	public static float mvmatrix[] = new float[16];
	public static float projmatrix[] = new float[16];
	public static int viewport[] = new int[4];
	
	public static GL3 gl3;
	public static boolean gl_init = false;
	public static boolean update_data_flag = false;
	
	public void make_shaders(ShaderState st) {
		final ShaderCode vp0 = ShaderCode.create(gl3, GL2ES2.GL_VERTEX_SHADER, this.getClass(), "shaders",
                "shaders/bin", "vertex", true);
		final ShaderCode gp0 = ShaderCode.create(gl3, GL3.GL_GEOMETRY_SHADER, this.getClass(), "shaders",
                "shaders/bin", "geometry", true);
		final ShaderCode fp0 = ShaderCode.create(gl3, GL2ES2.GL_FRAGMENT_SHADER, this.getClass(), "shaders",
                "shaders/bin", "fragment", true);
		
		vp0.defaultShaderCustomization(gl3, true, true);
		gp0.defaultShaderCustomization(gl3, true, true);
        fp0.defaultShaderCustomization(gl3, true, true);
        
        sp0.add(gl3, vp0, System.err);
        sp0.add(gl3, gp0, System.err);
        sp0.add(gl3, fp0, System.err);
        if(!sp0.link(gl3, System.err)) {
            throw new GLException("Couldn't link program: "+sp0);
        }
        st.attachShaderProgram(gl3, sp0, true);
        
        pmvMatrix = new PMVMatrix();   
        pmvMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        pmvMatrix.glLoadIdentity();
        pmvMatrix.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        pmvMatrix.glLoadIdentity();
        update_pmv_matrix();
	}
	
	public static void update_pmv_matrix() { 
		pmvMatrixUniform = new GLUniformData("mgl_PMVMatrix", 4, 4, pmvMatrix.glGetPMvMatrixf()); // P, Mv
        st.ownUniform(pmvMatrixUniform);
        st.uniform(gl3, pmvMatrixUniform);

        st.ownUniform(pmvMatrixUniform);
        if(!st.uniform(gl3, pmvMatrixUniform)) {
            throw new GLException("Error setting PMVMatrix in shader: "+st);
        }
	}
	
	public static void init(GL3 gl2, int width, int height) {

		Random randomGenerator = new Random();
		long t1, t2, t_total =0;
		t1 = System.currentTimeMillis();

		// List<Shape> theShapes = new LinkedList<Shape>();
		
		for (int idx = 0; idx < N ; ++idx) {
			int x1, y1, x2, y2;
			
			x1 = randomGenerator.nextInt(1200);
			y1 = randomGenerator.nextInt(1200);
			x2 = randomGenerator.nextInt(1200);
			y2 = randomGenerator.nextInt(1200);
			/*
			x1 = 0;
			y1 = 0;
			x2 = 500;
			y2 = 50;
			new Line(ObjectMode.New_object, x1, y1, 0, x2, y2, 0, 
					Values.stipple_factor, dash_type.Continuous, Values.color, Values.width);
			x1 = 100;
			y1 = 100;
			x2 = 450;
			y2 = 150;*/
			new ShapeLine(ObjectMode.New_object, x1, y1, 0, x2, y2, 0, 
					Values.stipple_factor, dash_type.CENTER, Values.color, 2);
			//Color_rgb c = new Color_rgb(200, 0, 100);
			//new Line(ObjectMode.New_object, x1+100, y1+100, 0, x2+100, y2+100, 0, 
			//		Values.stipple_factor, dash_type.Continuous, c, Values.width);
			
			/*
			x1 = randomGenerator.nextInt(1200);
			y1 = randomGenerator.nextInt(1200);
			x2 = randomGenerator.nextInt(1200);
			y2 = randomGenerator.nextInt(1200);
			*/
			new Circle(ObjectMode.New_object, x1, y1, 0, x2, y2, 0, Values.color, Values.width);
			
			//Global_var.theShapes.add(new Circle(x1, y1, 0, x2, y2, 0));
			//Global_var.theShapes.put(new Line(x1, y1, 0, x2, y2, 0));
			// Global_var.theShapes.add(new Line(x1, y1, 0, x2, y2, 0, 10, new int[]{1,1}));

		}
		
		/*  Group of Shape
		for (Shape sh:Global_var.theShapes.values())
		{
			System.out.println(" " +sh.getClass() + sh.ID);
		}
		
		LinkedList<Shape> ss = new LinkedList<Shape>(Global_var.theShapes.values());
		GroupShape grShape  = new GroupShape(ss);
		
		for (Shape sh:Global_var.theShapes.values())
		{
			System.out.println(" " +sh.getClass() + sh.ID);
		}
		*/
		
		t2 = System.currentTimeMillis();
		System.out.println("Generation: " + N + " figures, time=" +(t2 - t1)+ " ms.");
		t_total += t2-t1;
		
		t1 = System.currentTimeMillis();
		update_data();
		t2 = System.currentTimeMillis();
		System.out.println("upate_data(): " +(t2 - t1)+ " ms.");
		t_total += t2-t1;
		
		t1 = System.currentTimeMillis();
		render(gl2, width, height);
		t2 = System.currentTimeMillis();
		System.out.println("render(): " +(t2 - t1)+ " ms.");
		t_total += t2-t1;
		
		System.out.println("TOTAL: "+ t_total+ " ms.");
	}

	/**
	 * Method for update (remove and create new) VBO from theShapes
	 */
	public static void update_data(){
		gl3 = Global_var.glcanvas.getGL().getGL3();
		if(!gl_init) {
			GL_base glb = new GL_base();
			
			st = new ShaderState();
			st.setVerbose(true);
			glb.make_shaders(st);
			
			gl_init = true;
		}
		
		FloatArrayList listFloat = new FloatArrayList(Global_var.N_DrawableLines*6);//(N*200);
		FloatArrayList listFloatColor = new FloatArrayList(Global_var.N_DrawableLines*3);
		FloatArrayList listFloatWidth = new FloatArrayList(Global_var.N_DrawableLines*2);
		
		for (Shape sh : Global_var.theShapes.values()) {
			listFloat.addAll(sh.toListFloat());
			listFloatColor.addAll(sh.toListFloatColor());
			listFloatWidth.addAll(sh.toListFloatWidth());
		}
		vertices = listFloat.elements();
		colors = listFloatColor.elements();
		widths = listFloatWidth.elements();
		interleavedVBO = GLArrayDataServer.createGLSLInterleaved(3 + 3 + 1, GL3.GL_FLOAT, false, Global_var.N_DrawableLines*2*3,
				GL3.GL_STATIC_DRAW);
		update_vao(interleavedVBO, vertices, colors, widths, Global_var.N_DrawableLines);
	}
	
	public static void update_dynamic_data(){
		gl3 = Global_var.glcanvas.getGL().getGL3();
		Global_var.N_dynamic_lines = 0;
		Dynamic_data d = new Dynamic_data();
		float[] vertices = d.get_vertices();
		float[] colors = d.get_colors();
		float[] widths = d.get_widths();
		Global_var.N_dynamic_lines = vertices.length/6;
		
		dynamic_interleavedVBO = GLArrayDataServer.createGLSLInterleaved(3 + 3 + 1, GL3.GL_FLOAT, false, Global_var.N_dynamic_lines*2*3,
				GL3.GL_STATIC_DRAW);
		update_vao(dynamic_interleavedVBO, vertices, colors, widths, Global_var.N_dynamic_lines);
	}
	
	public static void print_a(float[] a) {
		for (int i =0; i<a.length; i++) {
			System.out.print(" "+a[i] + " ");
		}
		System.out.println();
	}
	public static void update_vao(GLArrayDataServer iVBO, float[] vertices, float[] colors, float[] widths, int N_lines) {
		st.useProgram(gl3, true);

		iVBO.addGLSLSubArray("position", 3, GL3.GL_ARRAY_BUFFER);
		iVBO.addGLSLSubArray("color", 3, GL3.GL_ARRAY_BUFFER);
		iVBO.addGLSLSubArray("width", 1, GL3.GL_ARRAY_BUFFER);

		final FloatBuffer ib = (FloatBuffer) iVBO.getBuffer();

		for (int i = 0; i < N_lines*2; i++) {
			ib.put(vertices, i * 3, 3);
			ib.put(colors, i * 3, 3);
			ib.put(widths, i * 1, 1);
		}
		
		iVBO.seal(gl3, true);
		iVBO.enableBuffer(gl3, false);
		st.ownAttribute(iVBO, true);
		st.useProgram(gl3, false);
	}

	public static void draw_vao(GLArrayDataServer iVBO, int N_lines) {
		if (null != st) {
			iVBO.enableBuffer(gl3, true);
			gl3.glDrawArrays(GL3.GL_LINES, 0, N_lines * 2);
			iVBO.enableBuffer(gl3, false);
		}
	}
	
	/**
	 * This method updates glcanvas if theShapes was changed.
	 */
	public static void redraw_buffer() {
		// Updating of VBOs and another work with GL is possible in methods
		// of GLEventListener interface ONLY!
		update_data_flag = true;

		Global_var.glcanvas.display(); // Now in .display will call update_data
										// method
	}

	public static void setup(GL3 gl3, int width, int height) {
		//System.out.println("setup");
		gl3.setSwapInterval(1);
		if(null != st) {
			pmvMatrix.glPushMatrix();
            pmvMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
            pmvMatrix.glLoadIdentity();
            pmvMatrix.glOrthof(0.0f, width, 0.0f, height, -100.0f, 100.0f);

            pmvMatrix.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
            pmvMatrix.glLoadIdentity();
            pmvMatrix.glPopMatrix();
            
            gl3.glViewport(0, 0, width, height);
            st.useProgram(gl3, true);
            update_pmv_matrix();
            st.useProgram(gl3, false);
        
		}
	}

	public static void render(GL3 gl2, int width, int height) {
		// If updating VBOs is necessary
		if (update_data_flag == true) {
			update_data();
			update_data_flag = false;
		}
		update_dynamic_data();
		//System.out.println("render");
		
		st.useProgram(gl3, true);
		gl3.glClearColor(0.0f, 0.0f, 0.0f, 1);
		gl3.glClear(GL3.GL_COLOR_BUFFER_BIT | GL3.GL_DEPTH_BUFFER_BIT);
		pmvMatrix.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		pmvMatrix.glMultMatrixf(general_matrix, 0);
		general_matrix = identity_matrix.clone();
		update_pmv_matrix();

		current_scaleUniform = new GLUniformData("current_scale", Values.current_scale);
		st.ownUniform(current_scaleUniform);
		st.uniform(gl3, current_scaleUniform);

		st.ownUniform(current_scaleUniform);
		if (!st.uniform(gl3, current_scaleUniform)) {
			throw new GLException("Error setting current_scale in shader: " + st);
		}

		draw_vao(interleavedVBO, Global_var.N_DrawableLines);
		draw_vao(dynamic_interleavedVBO, Global_var.N_dynamic_lines);
		st.useProgram(gl3, false);

		pmvMatrix.glGetFloatv(GLMatrixFunc.GL_MODELVIEW_MATRIX, mvmatrix, 0);
		pmvMatrix.glGetFloatv(GLMatrixFunc.GL_PROJECTION_MATRIX, projmatrix, 0);
		gl3.glGetIntegerv(GL3.GL_VIEWPORT, viewport, 0);
    }
	
	/**
	 * Cover for glDrawArrays for draw it with a color
	 * @param vertices_array - array of coordinates xy-only.
	 * @param color - color for this vertices
	 */
	static void gl_draw_array(float[] vertices, int[] color){
		
		int N_lines = vertices.length/6;
		int N_verts = N_lines*2;
		float[] colors = new float[N_verts*3];
		float[] widths = new float[N_verts];
		
		int r = 0;
		int w = 2;
		for(int i = 0; i<vertices.length; i+=3) {
			colors[i] = color[0];
			colors[i+1] = color[1];
			colors[i+2] = color[2];
			widths[r] = w;
			r++;
		}
		
		dynamic_interleavedVBO = GLArrayDataServer.createGLSLInterleaved(3 + 3 + 1, GL3.GL_FLOAT, false, N_lines*2*3,
				GL3.GL_STATIC_DRAW);
		update_vao(dynamic_interleavedVBO, vertices, colors, widths, N_lines);
		draw_vao(dynamic_interleavedVBO, N_lines);
	}

	/**
	 * Static method for transform coordinates of glwindow to gl world
	 * coordinates.
	 * 
	 * @param glcanvas
	 * @param x
	 *            coordinate of gl window
	 * @param y
	 *            coordinate of gl window
	 * @return gl world coordinates
	 */
	public static float[] get_real_coords(GLCanvas glcanvas, float x, float y) {
		float wcoord[] = new float[4];

		float realy_y = viewport[3] - y;
		GLU glu2 = new GLU();
		glu2.gluUnProject(x, realy_y, 0, mvmatrix, 0, projmatrix, 0,
				viewport, 0, wcoord, 0);
		float[] returnable = wcoord.clone();
		return returnable;
	}
}

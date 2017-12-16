package core;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import open_dxf_lib.dash_type;

import java.nio.FloatBuffer;

import samoJ.Circle;
import samoJ.GroupShape;
import samoJ.ShapeLine;
import samoJ.ObjectMode;
import samoJ.Shape;

import core.GL2_data;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.GLArrayDataServer;
import com.jogamp.opengl.util.PMVMatrix;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLUniformData;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.glsl.ShaderState;

import java.util.Random;

public class GL_base {
	public final ShaderProgram sp0 = new ShaderProgram();
	public ShaderState st;
	private GLArrayDataServer interleavedVBO;
	private GLArrayDataServer dynamic_interleavedVBO;

	

	/**
	 * Arrays for coordinates, colors and widths:
	 */
	/*
	private float[] colors;
	private float[] vertices;
	private float[] widths;
		*/
	public float[] general_matrix = { 1, 0, 0, 0, 0, 1,
			0, 0, 0, 0, 1, 0, 0, 0, 0, 1 };

	public float[] identity_matrix = { 1, 0, 0, 0, 0, 1,
			0, 0, 0, 0, 1, 0, 0, 0, 0, 1 };
	
	public PMVMatrix pmvMatrix;
	/**
	 * Variable for uniform location in shader program
	 */
	private  GLUniformData pmvMatrixUniform;
	private  GLUniformData current_scaleUniform;
	
	public boolean unproject_flag = false;
	public float[] unproject = new float[2];
	public float[] project = new float[2];

	private  float mvmatrix[] = new float[16];
	private  float projmatrix[] = new float[16];
	public  int viewport[] = new int[4];
	
	private  GL3 gl3;
	public  boolean gl_init = false;
	public boolean update_data_flag = false;
	
	private int GL_version;
	// GL2 variables
	private GL2_data GL2_dataArray[] = new GL2_data[4];
	private  GL2 gl2;
	private Core core = Core.c;;

	public void init_GL() {
		GL_version = core.gui.glcanvas.getContext().getGLSLVersionNumber().getMajor();
		GL_version = 1;
		System.out.println("OpenGL version " + GL_version);
		if (GL_version == 3) {
			gl3 = core.gui.glcanvas.getGL().getGL3();
			st = new ShaderState();
			st.setVerbose(true);
			make_shaders(st);
		} else if (GL_version == 2) {
			gl2 = core.gui.glcanvas.getGL().getGL2();
			gl2.glMatrixMode(GL2.GL_MODELVIEW);
			gl2.glLoadIdentity();
			create_GL2_data();

		} else if (GL_version == 1) {
			System.out.println("OpenGL version is old, rendering fail");
			// Rendering fail
			return;
		}
		gl_init = true;
		
	}
	
	private void create_GL2_data() {
		// create data structure for 4 line width
		
		//GL2_dataArray = new GL2_data[4];
		for (int i = 0; i < 4; i++) {
			if(null != GL2_dataArray[i]) {
				gl2.glDeleteBuffers(2, GL2_dataArray[i].vbo_buffer, 0);
			}
			GL2_dataArray[i] = new GL2_data(i + 1);
		}
	}
	
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
	
	public void update_pmv_matrix() { 
		pmvMatrixUniform = new GLUniformData("mgl_PMVMatrix", 4, 4, pmvMatrix.glGetPMvMatrixf()); // P, Mv
        st.ownUniform(pmvMatrixUniform);
        st.uniform(gl3, pmvMatrixUniform);

        st.ownUniform(pmvMatrixUniform);
        if(!st.uniform(gl3, pmvMatrixUniform)) {
            throw new GLException("Error setting PMVMatrix in shader: "+st);
        }
	}
	
	public void init(GL3 gl2, int width, int height) {
		
		Random randomGenerator = new Random();
		long t1, t2, t_total =0;
		t1 = System.currentTimeMillis();

		// List<Shape> theShapes = new LinkedList<Shape>();
		
		for (int idx = 0; idx < core.global.N ; ++idx) {
			int x1, y1, x2, y2;
			
			x1 = randomGenerator.nextInt(1200);
			y1 = randomGenerator.nextInt(1200);
			x2 = randomGenerator.nextInt(1200);
			y2 = randomGenerator.nextInt(1200);
			int w = 2;//randomGenerator.nextInt(3)+1;
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
			
			new ShapeLine(ObjectMode.New_object, x1+20, y1+20, 0, x2+20, y2+20, 0, 
					core.values.stipple_factor, dash_type.CENTER, core.values.color, w);
			//Color_rgb c = new Color_rgb(200, 0, 100);
			//new Line(ObjectMode.New_object, x1+100, y1+100, 0, x2+100, y2+100, 0, 
			//		Values.stipple_factor, dash_type.Continuous, c, Values.width);
			
			/*
			x1 = randomGenerator.nextInt(1200);
			y1 = randomGenerator.nextInt(1200);
			x2 = randomGenerator.nextInt(1200);
			y2 = randomGenerator.nextInt(1200);
			*/
			//new Circle(ObjectMode.New_object, x1, y1, 0, x2, y2, 0, Values.color, w);
			
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
		System.out.println("Generation: " + core.global.N + " figures, time=" +(t2 - t1)+ " ms.");
		t_total += t2-t1;
		
		t1 = System.currentTimeMillis();
		for (int i = 0; i<20; i++) {
		update_data();
		}
		t2 = System.currentTimeMillis();
		System.out.println("upate_data(): " +(t2 - t1)+ " ms.");
		t_total += t2-t1;
		
		t1 = System.currentTimeMillis();
		render(width, height);
		t2 = System.currentTimeMillis();
		System.out.println("render(): " +(t2 - t1)+ " ms.");
		t_total += t2-t1;
		
		System.out.println("TOTAL: "+ t_total+ " ms.");
	}

	/**
	 * Method for update (remove and create new) VBO from theShapes
	 */
	public void update_data(){
		if(gl_init == false) {
			System.out.println("GL init...");
			init_GL();
			if(gl_init == true) System.out.println("GL init OK");
		}
		
		if(GL_version == 3) {
			FloatArrayList listFloat = new FloatArrayList(core.global.N_DrawableLines * 6);// (N*200);
			FloatArrayList listFloatColor = new FloatArrayList(core.global.N_DrawableLines * 6);
			FloatArrayList listFloatWidth = new FloatArrayList(core.global.N_DrawableLines * 2);
			for (Shape sh : core.global.theShapes.values()) {
				listFloat.addAll(sh.toListFloat());
				listFloatColor.addAll(sh.toListFloatColor());
				listFloatWidth.addAll(sh.toListFloatWidth());
			}
			float[] vertices = listFloat.elements();
			float[] colors = listFloatColor.elements();
			float[] widths = listFloatWidth.elements();
			//destroy old VBO (memory leak!)
			if(null != interleavedVBO) {
				st.ownAttribute(interleavedVBO, false);
				interleavedVBO.destroy(gl3);				
			}
			interleavedVBO = GLArrayDataServer.createGLSLInterleaved(3 + 3 + 1, GL3.GL_FLOAT, false,
					core.global.N_DrawableLines * 2 * 3, GL3.GL_STATIC_DRAW);
			
			update_vao(interleavedVBO, vertices, colors, widths, core.global.N_DrawableLines);
		}
		else if(GL_version == 2) {
			update_data_GL2();
		}
	}
	
	public void update_dynamic_data(){
		gl3 = core.gui.glcanvas.getGL().getGL3();
		core.global.N_dynamic_lines = 0;
		Dynamic_data d = new Dynamic_data();
		
		if (core.global.select_mode){
			d.put_data(core.global.select_rect_vertices, core.global.select_rect_color.get_float_rgb(), core.values.select_rect_width);
		}
		
		if (core.global.current_Shape_vertices != null){
			d.put_data(core.global.current_Shape_vertices, core.values.current_shape_color.get_float_rgb(), core.values.dynamic_width);	
		}
		if (core.global.snap_sign_vertices != null){
			d.put_data(core.global.snap_sign_vertices, core.values.snap_color.get_float_rgb(), core.values.dynamic_width);
		}
		if (core.global.preview_object_vertices != null){
			d.put_data(core.global.preview_object_vertices, core.values.color.get_float_rgb(), core.values.dynamic_width);
		}	
		
		
		float[] vertices = d.get_vertices();
		float[] colors = d.get_colors();
		float[] widths = d.get_widths();
		core.global.N_dynamic_lines = vertices.length / 6;
		//destroy old VBO (memory leak!)
		if(null != dynamic_interleavedVBO) {
			st.ownAttribute(dynamic_interleavedVBO, false);
			dynamic_interleavedVBO.destroy(gl3);				
		}
		dynamic_interleavedVBO = GLArrayDataServer.createGLSLInterleaved(3 + 3 + 1, GL3.GL_FLOAT, false,
				core.global.N_dynamic_lines * 2 * 3, GL3.GL_STATIC_DRAW);
		update_vao(dynamic_interleavedVBO, vertices, colors, widths, core.global.N_dynamic_lines);
		
	}
	
	public void print_a(float[] a) {
		for (int i =0; i<a.length; i++) {
			System.out.print(" "+a[i] + " ");
		}
		System.out.println();
	}
	public void update_vao(GLArrayDataServer iVBO, float[] vertices, float[] colors, float[] widths, int N_lines) {

		st.useProgram(gl3, true);
		
		iVBO.addGLSLSubArray("position", 3, GL3.GL_ARRAY_BUFFER);
		iVBO.addGLSLSubArray("color", 3, GL3.GL_ARRAY_BUFFER);
		iVBO.addGLSLSubArray("width", 1, GL3.GL_ARRAY_BUFFER);

		final FloatBuffer ib = (FloatBuffer) iVBO.getBuffer();
		
		for (int i = 0; i < N_lines * 2; i++) {
			ib.put(vertices, i * 3, 3);
			ib.put(colors, i * 3, 3);
			ib.put(widths, i * 1, 1);
		}

		iVBO.seal(gl3, true);
		iVBO.enableBuffer(gl3, false);
		st.ownAttribute(iVBO, true);
		st.useProgram(gl3, false);
	}

	public void draw_vao(GLArrayDataServer iVBO, int N_lines) {

		if (null != st) {
			iVBO.enableBuffer(gl3, true);
			gl3.glDrawArrays(GL3.GL_LINES, 0, N_lines * 2);
			iVBO.enableBuffer(gl3, false);
		}
	}
		
	
	
	/**
	 * This method updates glcanvas if theShapes was changed.
	 */
	public void redraw_buffer() {
		// Updating of VBOs and another work with GL is possible in methods
		// of GLEventListener interface ONLY!
		update_data_flag = true;

		core.gui.glcanvas.display(); // Now in .display will call update_data
										// method
	}

	public void setup(int width, int height) {
		//System.out.println("setup");
		if (GL_version == 3) {
			gl3.setSwapInterval(1);
			if (null != st) {
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
		else if(GL_version == 2) {
			setup_GL2(width, height);
		}
	}

	public void render(int width, int height) {
		// If updating VBOs is necessary
		if (update_data_flag == true) {
			update_data();
			update_data_flag = false;
		}
		
		//System.out.println("render");
		if (GL_version == 3) {
			update_dynamic_data();
			st.useProgram(gl3, true);
			gl3.glClearColor(0.0f, 0.0f, 0.0f, 1);
			gl3.glClear(GL3.GL_COLOR_BUFFER_BIT | GL3.GL_DEPTH_BUFFER_BIT);
			pmvMatrix.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
			pmvMatrix.glMultMatrixf(general_matrix, 0);
			general_matrix = identity_matrix.clone();
			update_pmv_matrix();

			current_scaleUniform = new GLUniformData("current_scale", core.values.current_scale);
			st.ownUniform(current_scaleUniform);
			st.uniform(gl3, current_scaleUniform);

			st.ownUniform(current_scaleUniform);
			if (!st.uniform(gl3, current_scaleUniform)) {
				throw new GLException("Error setting current_scale in shader: " + st);
			}

			draw_vao(interleavedVBO, core.global.N_DrawableLines);
			draw_vao(dynamic_interleavedVBO, core.global.N_dynamic_lines);
			st.useProgram(gl3, false);

			pmvMatrix.glGetFloatv(GLMatrixFunc.GL_MODELVIEW_MATRIX, mvmatrix, 0);
			pmvMatrix.glGetFloatv(GLMatrixFunc.GL_PROJECTION_MATRIX, projmatrix, 0);
			viewport = new int[4];
			gl3.glGetIntegerv(GL3.GL_VIEWPORT, viewport, 0);
		}
		else if(GL_version == 2) {
			render_GL2(width, height);
		}
		
    }
	
	public void dispose() {
		if (GL_version == 3) {

			if (null != st) {
				pmvMatrixUniform = null;
				pmvMatrix = null;
				core.global.theShapes = null;

				st.destroy(gl3);
				st = null;
			}
			
		}
		//core.gui.glcanvas.destroy();
		
	}
		
	public void setup_GL2(int width, int height) {
		gl2.glPushMatrix();

		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glLoadIdentity();

		GLU glu = new GLU();
		glu.gluOrtho2D(0.0, width, 0.0, height);

		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();

		gl2.glViewport(0, 0, width, height);
		gl2.glPopMatrix();
	}
	
	public void render_GL2(int width, int height) {
		gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	    
		gl2.glMatrixMode(GL2.GL_MODELVIEW);

		gl2.glMultMatrixf(general_matrix, 0);
		general_matrix = identity_matrix.clone();

		for(GL2_data d: GL2_dataArray) {
			gl2.glLineWidth(d.width);
			gl2.glEnableClientState(GL2.GL_VERTEX_ARRAY);
			gl2.glEnableClientState(GL2.GL_COLOR_ARRAY);

			// Bind color buffer
			gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, d.vbo_buffer[1]);
			gl2.glColorPointer(3, GL2.GL_FLOAT, 0, 0);
			// Bind vertex buffer
			gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, d.vbo_buffer[0]);
			gl2.glVertexPointer(3, GL2.GL_FLOAT, 0, 0);

			gl2.glDrawArrays(GL2.GL_LINES, 0, (int) d.vertices.length / 3);
			gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
			
			gl2.glDisableClientState(GL2.GL_COLOR_ARRAY);
			//gl2.glDisableClientState(GL2.GL_VERTEX_ARRAY);
			
			//gl2.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		}
		//gl2.glFlush();
		dinamic_render();	
		gl2.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		
		gl2.glGetFloatv(GL2.GL_MODELVIEW_MATRIX, mvmatrix, 0);
		gl2.glGetFloatv(GL2.GL_PROJECTION_MATRIX, projmatrix, 0);
		gl2.glGetIntegerv(GL2.GL_VIEWPORT, viewport, 0);
	}
	
	public void update_data_GL2() {
		create_GL2_data();
		for (Shape sh : core.global.theShapes.values()) {
			int w = sh.get_width();
			GL2_dataArray[w-1].listFloat.addAll(sh.toListFloat());
			GL2_dataArray[w-1].listFloatColor.addAll(sh.toListFloatColor());
		}
		for(GL2_data d: GL2_dataArray) {
			d.vertices = d.listFloat.elements();
			d.colors = d.listFloatColor.elements();
		
			FloatBuffer fbVertices = Buffers.newDirectFloatBuffer(d.vertices);
			FloatBuffer fbColores = Buffers.newDirectFloatBuffer(d.colors);
			
			gl2.glGenBuffers(2, d.vbo_buffer, 0);
			
			//Vertices buffer
			gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, d.vbo_buffer[0]);

			int numBytes = fbVertices.capacity() * Buffers.SIZEOF_FLOAT;
			gl2.glBufferData(GL2.GL_ARRAY_BUFFER, numBytes, fbVertices,
					GL2.GL_STATIC_DRAW);
			gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
			
			// Color buffer
			gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, d.vbo_buffer[1]);

			numBytes = fbColores.capacity() * Buffers.SIZEOF_FLOAT;
			gl2.glBufferData(GL2.GL_ARRAY_BUFFER, numBytes, fbColores,
					GL2.GL_STATIC_DRAW);
			gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
		}
		
		
	}
	
	void dinamic_render() {
		if (core.global.select_mode){
			gl_draw_array(core.global.select_rect_vertices, core.global.select_rect_color.get_rgb(), core.values.select_rect_width);			
		}
		if (core.global.current_Shape_vertices != null){
			gl_draw_array(core.global.current_Shape_vertices, core.values.current_shape_color.get_rgb(), core.values.dynamic_width);	
		}
		if (core.global.snap_sign_vertices != null){
			gl_draw_array(core.global.snap_sign_vertices, core.values.snap_color.get_rgb(), core.values.dynamic_width);
		}
		if (core.global.preview_object_vertices != null){
			gl_draw_array(core.global.preview_object_vertices, core.values.color.get_rgb(), core.values.dynamic_width);
		}
		
	}
	
	/**
	 * Cover for glDrawArrays for draw it with a color
	 * @param vertices_array - array of coordinates xy-only.
	 * @param color - color for this vertices
	 */
	void gl_draw_array(float[] vertices_array, int[] color, int width){
		//gl2.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		//gl2.glEnableClientState(GL2.GL_COLOR_ARRAY);
		
		gl2.glLineWidth(width);
		gl2.glColor3f(color[0], color[1], color[2]);
		
		FloatBuffer fb = Buffers.newDirectFloatBuffer(vertices_array);
		gl2.glVertexPointer(3, GL2.GL_FLOAT, 0, fb);
		gl2.glDrawArrays(GL2.GL_LINES, 0, vertices_array.length / 3);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
		
		//gl2.glDisableClientState(GL2.GL_COLOR_ARRAY);
		//gl2.glDisableClientState(GL2.GL_VERTEX_ARRAY);
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
	public float[] get_real_coords(float x, float y) {
		float wcoord[] = new float[4];

		float realy_y = viewport[3] - y;
		GLU glu = new GLU();
		glu.gluUnProject(x, realy_y, 0.0f, mvmatrix, 0, projmatrix, 0,
				viewport, 0, wcoord, 0);
		//float[] returnable = wcoord;//.clone();
		return wcoord;
	}
}

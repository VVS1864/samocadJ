package core;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import open_dxf_lib.Color_rgb;
import open_dxf_lib.dash_type;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import samoJ.Circle;
import samoJ.GroupShape;
import samoJ.Line;
import samoJ.ObjectMode;
import samoJ.Shape;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import java.util.Random;

public class GL_base {

	static int[] vbo_buffer = new int[2];
	//static int[] color_buffer = new int[1];

	public static int N = 100;// number of vertices

	static DoubleBuffer fbVertices;
	static DoubleBuffer fbColores;
	// static double[] vertices = new double[N * 3];
	static double[] colores;
	static double[] vertices;
	static DoubleBuffer fbDinamicVertices;
	//public static double[] dinamic_vertices = new double[4 * 3];

	// public static LinkedList<Integer> list1 = new LinkedList<Integer>();
	// public static double[] list1 = {};
	public static double[] general_matrix = { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
			0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0 };

	public static double[] identity_matrix = { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
			0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0 };

	public static boolean unproject_flag = false;
	public static double[] unproject = new double[2];
	public static double[] project = new double[2];

	public static double mvmatrix[] = new double[16];
	public static double projmatrix[] = new double[16];
	public static int viewport[] = new int[4];

	public static GL2 gl2;
	public static boolean update_data_flag = false;

	public static void init(GL2 gl2, int width, int height) {

		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();

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
			
			new Line(ObjectMode.New_object, x1, y1, 0, x2, y2, 0, 
					Values.stipple_factor, dash_type.Continuous, Values.color);
			Color_rgb c = new Color_rgb(200, 0, 100);
			new Line(ObjectMode.New_object, x1+100, y1+100, 0, x2+100, y2+100, 0, 
					Values.stipple_factor, dash_type.Continuous, c);
			
			/*
			x1 = randomGenerator.nextInt(1200);
			y1 = randomGenerator.nextInt(1200);
			x2 = randomGenerator.nextInt(1200);
			y2 = randomGenerator.nextInt(1200);
			*/
			//new Circle(ObjectMode.New_object, x1, y1, 0, x2, y2, 0, Values.color);
			
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
		/*
		 * System.out.println( N + " �����"); long t2 =
		 * System.currentTimeMillis();
		 * System.out.println("���������: " + (t2 - t1)+
		 * " ms.");
		 * 
		 * for (Shape sh: cad_demo.theShapes) { list1.addAll(sh.toList()); }
		 * 
		 * 
		 * t1 = System.currentTimeMillis(); vertices = ArrayUtils
		 * .toPrimitive(list1.toArray(new Integer[list1.size()]));
		 * 
		 * t2 = System.currentTimeMillis(); System.out.println("List to array: "
		 * + (t2 - t1)+ " ms."); System.out.println("size of vertices  " +
		 * vertices.length+ ", lines=" + vertices.length/6);
		 * 
		 * t1 = System.currentTimeMillis(); fbVertices =
		 * Buffers.newDirectIntBuffer(vertices); gl2.glGenBuffers(1, vbo_buffer,
		 * 0); gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo_buffer[0]);
		 * 
		 * int numBytes = fbVertices.capacity() * Buffers.SIZEOF_INT;
		 * gl2.glBufferData(GL2.GL_ARRAY_BUFFER, numBytes, fbVertices,
		 * GL2.GL_STATIC_DRAW); gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
		 * 
		 * t2 = System.currentTimeMillis(); System.out.println("before render: "
		 * + (t2 - t1)+ " ms.");
		 */
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
		gl2 = Global_var.glcanvas.getGL().getGL2();
		
		//        OLD release with linked list  and ArrayUtils 
		// ------------------------------------------------
		// Generation: 2000 figures, time=168 ms.
		// upate_data(): 2485 ms.
		// render(): 4 ms.  
		// ------------------------------------------------
		
		/*
		ArrayList<Double> list1 = new ArrayList<Double>(N*200);

		for (Shape sh : Global_var.theShapes.values()) {
			list1.addAll(sh.toList());
		}
		
		vertices = ArrayUtils.toPrimitive(list1.toArray(new Double[list1
				.size()]));
		
		*/
		// END OLD RELEASE 
		
		/////////////////NEW REALISE with DoubleArrayList (it.unimi.dsi.fastutil.doubles) 
		// ------------------------------------------------
		//		Generation: 2000 figures, time=142 ms.
		//				upate_data(): 153 ms.
		//				render(): 5 ms.
		// ------------------------------------------------	
		
		DoubleArrayList listDouble = new DoubleArrayList();//(N*200);
		for (Shape sh : Global_var.theShapes.values()) {
			listDouble.addAll(sh.toListDouble());
		}
		
		DoubleArrayList listDoubleColors = new DoubleArrayList();
		for (Shape sh : Global_var.theShapes.values()) {
			listDoubleColors.addAll(sh.toListDoubleColor());
		}
		
		vertices = listDouble.elements(); // Easy without ArrayUtils
		colores = listDoubleColors.elements();
		/*
		for(int i = 0; i<colores.length-2; i+=3){
			colores[i] = 0.0;
			colores[i+1] = 120.0;
			colores[i+2] = 0.0;
		}*/
		///////////////////////////////// END NEW REALISE 
		
		fbVertices = Buffers.newDirectDoubleBuffer(vertices);
		fbColores= Buffers.newDirectDoubleBuffer(colores);
		
		gl2.glGenBuffers(2, vbo_buffer, 0);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo_buffer[0]);

		int numBytes = fbVertices.capacity() * Buffers.SIZEOF_DOUBLE;
		gl2.glBufferData(GL2.GL_ARRAY_BUFFER, numBytes, fbVertices,
				GL2.GL_STATIC_DRAW);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
/////////// Color buffer
		//gl2.glGenBuffers(1, color_buffer, 0);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo_buffer[1]);

		numBytes = fbColores.capacity() * Buffers.SIZEOF_DOUBLE;
		gl2.glBufferData(GL2.GL_ARRAY_BUFFER, numBytes, fbColores,
				GL2.GL_STATIC_DRAW);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
////////////
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

	public static void setup(GL2 gl2, int width, int height) {

		gl2.glPushMatrix();

		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glLoadIdentity();

		GLU glu = new GLU();
		glu.gluOrtho2D(0.0, width, 0.0, height);

		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();

		gl2.glViewport(0, 0, width, height);
		gl2.glPopMatrix();
		//System.out.println("resize");

	}

	public static void render(GL2 gl2, int width, int height) {
		// If updating VBOs is necessary
		if (update_data_flag == true) {
			update_data();
			//System.out.println("N vertices " + vertices.length / 3);
			update_data_flag = false;
		}
		gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	    
		gl2.glMatrixMode(GL2.GL_MODELVIEW);

		gl2.glMultMatrixd(general_matrix, 0);
		general_matrix = identity_matrix.clone();

		//gl2.glColor3f(Values.color.get_r(), Values.color.get_g(), Values.color.get_b());

		gl2.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl2.glEnableClientState(GL2.GL_COLOR_ARRAY);
		// Bind color buffer
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo_buffer[1]);
		gl2.glColorPointer(3, GL2.GL_DOUBLE, 0, 0);
		// Bind vertex buffer
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo_buffer[0]);
		gl2.glVertexPointer(3, GL2.GL_DOUBLE, 0, 0);
		
		gl2.glDrawArrays(GL2.GL_LINES, 0, (int) vertices.length / 3);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);

		gl2.glDisableClientState(GL2.GL_COLOR_ARRAY);
		
		dinamic_render(gl2);

		gl2.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		
		
		gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, mvmatrix, 0);

		gl2.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projmatrix, 0);

		gl2.glGetIntegerv(GL2.GL_VIEWPORT, viewport, 0);

	}

	static void dinamic_render(GL2 gl2) {
		if (Global_var.select_mode){
			gl_draw_array(Global_var.select_rect_vertices, Global_var.select_rect_color, 2);			
		}
		if (Global_var.current_Shape_vertices != null){
			gl_draw_array(Global_var.current_Shape_vertices, Values.current_shape_color.get_rgb(), 3);	
		}
		if (Global_var.snap_sign_vertices != null){
			gl_draw_array(Global_var.snap_sign_vertices, Values.snap_color.get_rgb(), 2);
		}
		if (Global_var.preview_object_vertices != null){
			gl_draw_array(Global_var.preview_object_vertices, Values.color.get_rgb(), 3);
		}
		
	}
	
	/**
	 * Cover for glDrawArrays for draw it with a color
	 * @param vertices_array - array of coordinates xy-only.
	 * @param color - color for this vertices
	 */
	static void gl_draw_array(double[] vertices_array, int[] color, int dimensions){
		
		gl2.glColor3f(color[0], color[1], color[2]);
		
		fbDinamicVertices = Buffers.newDirectDoubleBuffer(vertices_array);
		gl2.glVertexPointer(dimensions, GL2.GL_DOUBLE, 0, fbDinamicVertices);
		gl2.glDrawArrays(GL2.GL_LINES, 0, vertices_array.length / dimensions);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
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
	public static double[] get_real_coords(GLCanvas glcanvas, double x, double y) {
		double wcoord[] = new double[4];

		double realy_y = viewport[3] - y;
		GLU glu2 = new GLU();
		glu2.gluUnProject(x, realy_y, 0.0, mvmatrix, 0, projmatrix, 0,
				viewport, 0, wcoord, 0);
		double[] returnable = wcoord.clone();
		return returnable;
	}
}

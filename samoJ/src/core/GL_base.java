package core;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import org.apache.commons.lang3.ArrayUtils;

import samoJ.Circle;
import samoJ.Line;
import samoJ.Shape;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GL_base {

	static int[] vbo_buffer = new int[1];

	public static int N = 20;// number of vertices
	
	static IntBuffer fbVertices;
	// static double[] vertices = new double[N * 3];
	static int[] vertices;
	static DoubleBuffer fbDinamicVertices;
	public static double[] dinamic_vertices = new double[4 * 3];

	public static LinkedList<Integer> list1 = new LinkedList<Integer>();
	//public static double[] list1 = {};
	public static double[] general_matrix = { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
			0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0 };

	public static double[] identity_matrix = { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
			0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0 };

	// static int[] gl_size = new int[2];

	public static boolean unproject_flag = false;
	public static double[] unproject = new double[2];
	public static double[] project = new double[2];

	public static double mvmatrix[] = new double[16];
	public static double projmatrix[] = new double[16];
	public static int viewport[] = new int[4];

	public static void init(GL2 gl2, int width, int height) {

		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();

		Random randomGenerator = new Random();
		/*
		 * for (int idx = 0; idx <= N - 1; ++idx) { int randomInt =
		 * randomGenerator.nextInt(1200); vertices[idx * 3] = ((double)
		 * randomInt);// x int randomInt2 = randomGenerator.nextInt(1200);
		 * vertices[idx * 3 + 1] = ((double) randomInt2);// y vertices[idx * 3 +
		 * 2] = ((double) 0); }
		 */
		
		long t1 = System.currentTimeMillis();

		/*
		 * for (int idx = 0; idx <= N - 1; ++idx) { //int randomInt =
		 * randomGenerator.nextInt(1200); list1.add(
		 * (double)randomGenerator.nextInt(1200)); list1.add(
		 * (double)randomGenerator.nextInt(1200)); list1.add( 0f); }
		 */
		List<Shape> theShapes = new LinkedList<Shape>();
		
		for (int idx = 0; idx <= N - 1; ++idx) {
			int x1, y1, x2, y2;
			x1 =  randomGenerator.nextInt(1200);
			y1 =  randomGenerator.nextInt(1200);
			x2 =  randomGenerator.nextInt(1200);
			y2 =  randomGenerator.nextInt(1200);

			//theShapes.add(new Circle(x1, y1, 0, x2, y2, 0));
			//theShapes.add(new Line(x1, y1, 0, x2, y2, 0));
			theShapes.add(new Line(x1, y1, 0, x2, y2, 0, 10, new int[]{1,1}));
			// int randomInt = randomGenerator.nextInt(1200);

		}
		
		// list1.addAll(list2);
		// convert List<Double> to double[]
		/*
		theShapes.add(new Line(500, 0, 0, -400+300, -400, 0));
		theShapes.add(new Line(500, 0, 0, 400+300, -400, 0));
		theShapes.add(new Line(500, 0, 0, 400+300, 400, 0));
		theShapes.add(new Line(500, 0, 0, -400+300, 400, 0));
		
		theShapes.add(new Line(600, 0, 0, 1700, 100, 0));
		theShapes.add(new Line(1700, 0, 0, 1700, 1000, 0));
		theShapes.add(new Line(600, 0, 0, 600, 1000, 0));
		
		theShapes.add(new Line(0, 0, 0, 0, -400, 0));
		theShapes.add(new Line(0, 0, 0, -400, 0, 0));
		theShapes.add(new Line(0, 0, 0, 0, 400, 0));
		theShapes.add(new Line(0, 0, 0, 400, 0, 0));
		*/
		System.out.println( N + " �����");
		long t2 = System.currentTimeMillis();
		System.out.println("���������: " + (t2 - t1)+ " ms.");

		// t1 =System.currentTimeMillis();
		 //vertices = ArrayUtils.toPrimitive(list1.toArray(new
		 //Double[list1.size()]));
		 for (Shape sh: theShapes) {
		 list1.addAll(sh.toList());
		 }
		//t2 = System.currentTimeMillis();
		//System.out.println("Shape to List " + (t2 - t1));
		 
		t1 = System.currentTimeMillis();
		vertices = ArrayUtils
				.toPrimitive(list1.toArray(new Integer[list1.size()]));
		//vertices = list1;
		
		t2 = System.currentTimeMillis();
		System.out.println("List to array: " + (t2 - t1)+ " ms.");
		System.out.println("size of vertices  " + vertices.length+ ", lines=" + vertices.length/6);
		
		t1 = System.currentTimeMillis();
		fbVertices = Buffers.newDirectIntBuffer(vertices);
		gl2.glGenBuffers(1, vbo_buffer, 0);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo_buffer[0]);
		//int numBytes = vertices.length * ;
		int numBytes = fbVertices.capacity() * Buffers.SIZEOF_INT;
		gl2.glBufferData(GL2.GL_ARRAY_BUFFER, numBytes, fbVertices,
				GL2.GL_STATIC_DRAW);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
		
		t2 = System.currentTimeMillis();
		System.out.println("before render: " + (t2 - t1)+ " ms.");
		
		render(gl2, width, height);

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
		System.out.println("resize");

	}

	public static void render(GL2 gl2, int width, int height) {
		gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl2.glMatrixMode(GL2.GL_MODELVIEW);

		gl2.glMultMatrixd(general_matrix, 0);
		general_matrix = identity_matrix.clone();

		gl2.glColor3f(100, 0, 0);

		gl2.glEnableClientState(GL2.GL_VERTEX_ARRAY);

		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo_buffer[0]);

		gl2.glVertexPointer(3, GL2.GL_INT, 0, 0);
		gl2.glDrawArrays(GL2.GL_LINES, 0, (int) vertices.length / 3);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);

		dinamic_render(gl2);

		gl2.glDisableClientState(GL2.GL_VERTEX_ARRAY);

		gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, mvmatrix, 0);

		gl2.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projmatrix, 0);

		gl2.glGetIntegerv(GL2.GL_VIEWPORT, viewport, 0);

	}

	static void dinamic_render(GL2 gl2) {
		gl2.glColor3f(100, 100, 0);
		fbDinamicVertices = Buffers.newDirectDoubleBuffer(dinamic_vertices);
		gl2.glVertexPointer(3, GL2.GL_INT, 0, fbDinamicVertices);
		gl2.glDrawArrays(GL2.GL_LINES, 0, ((int) dinamic_vertices.length) / 3);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
	}

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

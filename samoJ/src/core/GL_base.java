package core;
import java.nio.FloatBuffer;
import org.apache.commons.lang3.ArrayUtils;	
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

	static int N = 20000;// number of vertices

	static FloatBuffer fbVertices;
	static float[] vertices = new float[N * 3];

	static FloatBuffer fbDinamicVertices;
	public static float[] dinamic_vertices = new float[4 * 3];




	public static double[] general_matrix = { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
		0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0 };

	public static double[] identity_matrix = { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
		0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0 };


	//static int[] gl_size = new int[2];

	public static boolean unproject_flag = false;
	public static float[] unproject = new float[2];
	public static double[] project = new double[2];

	public static double mvmatrix[] = new double[16];
	public static double projmatrix[] = new double[16];
	public static int viewport[] = new int[4];

	protected static void init(GL2 gl2, int width, int height) {

		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();

		Random randomGenerator = new Random();
		/*for (int idx = 0; idx <= N - 1; ++idx) {
			int randomInt = randomGenerator.nextInt(1200);
			vertices[idx * 3] = ((float) randomInt);// x
			int randomInt2 = randomGenerator.nextInt(1200);
			vertices[idx * 3 + 1] = ((float) randomInt2);// y
			vertices[idx * 3 + 2] = ((float) 0);
		}*/
		List<Float> list1 = new LinkedList<Float>();
		for (int idx = 0; idx <= N - 1; ++idx) {
			//int randomInt = randomGenerator.nextInt(1200);
			list1.add( (float)randomGenerator.nextInt(1200));
			list1.add( (float)randomGenerator.nextInt(1200));
			list1.add( 0f);
		}
		
		
		//list1.addAll(list2);
		// convert List<Float> to float[]
		vertices = ArrayUtils.toPrimitive(list1.toArray(new Float[list1.size()]));

		fbVertices = Buffers.newDirectFloatBuffer(vertices);
		gl2.glGenBuffers(1, vbo_buffer, 0);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo_buffer[0]);
		int numBytes = vertices.length * 4;
		gl2.glBufferData(GL2.GL_ARRAY_BUFFER, numBytes, fbVertices,
				GL2.GL_STATIC_DRAW);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);

		render(gl2, width, height);

	}


	protected static void setup(GL2 gl2, int width, int height) {

		gl2.glPushMatrix();


		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glLoadIdentity();

		GLU glu = new GLU();
		glu.gluOrtho2D(0.0f, width, 0.0f, height);

		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();

		gl2.glViewport(0, 0, width, height);
		gl2.glPopMatrix();
		System.out.println("resize");

	}

	protected static void render(GL2 gl2, int width, int height) {
		gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl2.glMatrixMode(GL2.GL_MODELVIEW);

		gl2.glMultMatrixd(general_matrix, 0);
		general_matrix = identity_matrix.clone();

		gl2.glColor3f(100, 0, 0);


		gl2.glEnableClientState(GL2.GL_VERTEX_ARRAY);

		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo_buffer[0]);

		gl2.glVertexPointer(3, GL2.GL_FLOAT, 0, 0);
		gl2.glDrawArrays(GL2.GL_LINES, 0, (int)vertices.length / 3);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);

		dinamic_render(gl2);	

		gl2.glDisableClientState(GL2.GL_VERTEX_ARRAY);

		gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, mvmatrix, 0);

		gl2.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projmatrix, 0);

		gl2.glGetIntegerv(GL2.GL_VIEWPORT, viewport, 0);

	}
	static void dinamic_render(GL2 gl2) {
		gl2.glColor3f(100, 100, 0);
		fbDinamicVertices = Buffers.newDirectFloatBuffer(dinamic_vertices);
		gl2.glVertexPointer(3, GL2.GL_FLOAT, 0, fbDinamicVertices);
		gl2.glDrawArrays(GL2.GL_LINES, 0, ((int)dinamic_vertices.length) / 3);
		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
	}
	static double[] get_real_coords(GLCanvas glcanvas, float x, float y) {
		double wcoord[] = new double[4];

		double realy_y = viewport[3] - y;
		GLU glu2 = new GLU();
		glu2.gluUnProject(x, realy_y, 0.0, mvmatrix, 0, projmatrix, 0,
				viewport, 0, wcoord, 0);
		double[] returnable = wcoord.clone();
		return returnable;
	}
}

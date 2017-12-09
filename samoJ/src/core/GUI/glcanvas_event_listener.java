package core.GUI;


import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;



import core.GL_base;


public class glcanvas_event_listener implements GLEventListener{
	
	@Override
	public void reshape(GLAutoDrawable glautodrawable, int x, int y,
			int width, int height) {
		GL_base.setup(glautodrawable.getGL().getGL3(),
				glautodrawable.getSurfaceWidth(),
				glautodrawable.getSurfaceHeight());

	}

	@Override

	public void init(GLAutoDrawable glautodrawable) {
		GL_base.init(glautodrawable.getGL().getGL3(),
				glautodrawable.getSurfaceWidth(),
				glautodrawable.getSurfaceHeight());

	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();
		if(null != GL_base.st) {
			GL_base.pmvMatrixUniform = null;
			GL_base.pmvMatrix=null;
			
			GL_base.st.destroy(gl);
			GL_base.st=null;
        }
	}

	@Override
	public void display(GLAutoDrawable glautodrawable) {

		GL_base.render(glautodrawable.getGL().getGL3(),
				glautodrawable.getSurfaceWidth(),
				glautodrawable.getSurfaceHeight());

	}
	
}
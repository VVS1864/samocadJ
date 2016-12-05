package core.GUI;


import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;



import core.GL_base;


public class glcanvas_event_listener implements GLEventListener{
	
	@Override
	public void reshape(GLAutoDrawable glautodrawable, int x, int y,
			int width, int height) {
		GL_base.setup(glautodrawable.getGL().getGL2(),
				glautodrawable.getSurfaceWidth(),
				glautodrawable.getSurfaceHeight());

	}

	@Override

	public void init(GLAutoDrawable glautodrawable) {
		GL_base.init(glautodrawable.getGL().getGL2(),
				glautodrawable.getSurfaceWidth(),
				glautodrawable.getSurfaceHeight());

	}

	@Override
	public void dispose(GLAutoDrawable glautodrawable) {
	}

	@Override
	public void display(GLAutoDrawable glautodrawable) {

		GL_base.render(glautodrawable.getGL().getGL2(),
				glautodrawable.getSurfaceWidth(),
				glautodrawable.getSurfaceHeight());

	}
	
}
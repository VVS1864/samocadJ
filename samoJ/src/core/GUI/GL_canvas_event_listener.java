package core.GUI;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import core.Core;

public class GL_canvas_event_listener implements GLEventListener{
	private Core core = Core.c;
	public GL_canvas_event_listener() {
		
	}
	
	@Override
	public void reshape(GLAutoDrawable glautodrawable, int x, int y,
			int width, int height) {
		core.glRender.setup(glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight());

	}

	@Override

	public void init(GLAutoDrawable glautodrawable) {
		core.glRender.init(glautodrawable.getSurfaceWidth(),
				glautodrawable.getSurfaceHeight());

	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		core.glRender.dispose();
	}

	@Override
	public void display(GLAutoDrawable glautodrawable) {

		core.glRender.render(glautodrawable.getSurfaceWidth(),
				glautodrawable.getSurfaceHeight());

	}
	
}
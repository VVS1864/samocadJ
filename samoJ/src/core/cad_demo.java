package core;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;

import mudules.Standart;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout; 
import java.awt.BorderLayout;
//import java.awt.Color;
import java.awt.Component;
//import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import Jama.*;

public class cad_demo {

	static boolean mouse_plan_motion = false;
	static double[] mouse = new double[2];
	static GLCanvas glcanvas;

	public static void plan_motion(GLCanvas glcanvas, double x, double y) {

		double[] real_coords = GL_base.get_real_coords(glcanvas, x, y);
		double dx = real_coords[0] - mouse[0];
		double dy = real_coords[1] - mouse[1];

		double[] m = { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, dx, dy, 0.0, 1.0 };

		GL_base.general_matrix = m.clone();
		glcanvas.display();
	}

	public static void main(String[] args) {
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		
		glcanvas = new GLCanvas(glcapabilities);
		final JLabel info_down = new JLabel();
		final JLabel info = new JLabel();
		final JLabel info2 = new JLabel();
		
		glcanvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		glcanvas.addGLEventListener(new GLEventListener() {

			@Override
			public void reshape(GLAutoDrawable glautodrawable, int x, int y,
					int width, int height) {
				GL_base.setup(glautodrawable.getGL().getGL2(),
						glautodrawable.getSurfaceWidth(),
						glautodrawable.getSurfaceHeight());

			}

			@Override

			public void init(GLAutoDrawable glautodrawable) {
				GL_base.init(glcanvas.getGL().getGL2(),
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

		});

		glcanvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON2) {
					System.out.println("Button 2 pressed...");
					mouse_plan_motion = true;
					mouse = GL_base.get_real_coords(glcanvas, e.getX(),
							e.getY());
				}
				;
			};

			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON2) {
					System.out.println("Button 2 released...");
					plan_motion(glcanvas, e.getX(), e.getY());
					mouse_plan_motion = false;

				}
			}

		});

		glcanvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {

				double[] xy = GL_base.get_real_coords(glcanvas, e.getX(),
						e.getY());
				double x = (double)xy[0];
				double y = (double)xy[1];
				info_down.setText("Coordinates: X "+ String.format("%.2f", x) + "; Y "
						+ String.format("%.2f", y) + ";");

			}

			public void mouseDragged(MouseEvent e) {

				if (mouse_plan_motion == true) {

					plan_motion(glcanvas, e.getX(), e.getY());

				}
			}
		});

		glcanvas.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = -e.getWheelRotation();
				double x = e.getX();
				double y = e.getY();
				Zoom.zoom(x, y, notches);

			}
		});
		
		glcanvas.addKeyListener(new KeyListener(){


			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				Zoom.button_zoom(c);
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		

		final JFrame jframe = new JFrame("Cad_demo, display " + GL_base.N
				+ " vertices");

		jframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				jframe.dispose();
				System.exit(0);
			}
		});
		jframe.setLayout(new BorderLayout());
		jframe.setSize(800, 500);
		Standart.addButtonsBar(jframe);
		
		final JPanel bot_panel = new JPanel();
		
		
		bot_panel.setLayout(new BoxLayout(bot_panel, BoxLayout.Y_AXIS));
		
		final JPanel cmd_panel = new JPanel();
		cmd_panel.setLayout(new BoxLayout(cmd_panel, BoxLayout.X_AXIS));
		
		
		info.setText("Comand:");
		cmd_panel.add(info);
		
		final JTextField cmd = new JTextField(6);
		cmd_panel.add(cmd);
		
		bot_panel.add(cmd_panel);
		cmd_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		info2.setText(" ");
		bot_panel.add(info2);
		
		info_down.setText("Coordinates: X "+ 0 + "; Y " + 0 + ";");
		bot_panel.add(info_down);
		
		jframe.add(bot_panel, BorderLayout.SOUTH);
		jframe.add(glcanvas, BorderLayout.CENTER);

		
		
		

		jframe.setVisible(true);
	}
}
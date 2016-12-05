package core.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import core.GL_base;
import core.cad_demo;
import core.navigation.Plan_motion;
import core.navigation.Zoom;
import mudules.Standart;

public class GUI {
	public GUI(){
		
		cad_demo.info = new JLabel();
		cad_demo.info2 = new JLabel();
		cad_demo.info_down = new JLabel();
		
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		cad_demo.glcanvas = new GLCanvas(glcapabilities);
		cad_demo.glcanvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		
		cad_demo.glcanvas.addGLEventListener(new core.GUI.glcanvas_event_listener());
		
		cad_demo.glcanvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON2) {
					System.out.println("Button 2 pressed...");
					cad_demo.mouse_plan_motion = true;
					cad_demo.mouse = GL_base.get_real_coords(cad_demo.glcanvas, e.getX(),
							e.getY());
				}
				;
			};

			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON2) {
					System.out.println("Button 2 released...");
					Plan_motion.plan_motion(cad_demo.glcanvas, e.getX(), e.getY());
					cad_demo.mouse_plan_motion = false;

				}
			}

		});

		cad_demo.glcanvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {

				double[] xy = GL_base.get_real_coords(cad_demo.glcanvas, e.getX(),
						e.getY());
				double x = (double)xy[0];
				double y = (double)xy[1];
				cad_demo.info_down.setText("Coordinates: X "+ String.format("%.2f", x) + "; Y "
						+ String.format("%.2f", y) + ";");

			}

			public void mouseDragged(MouseEvent e) {

				if (cad_demo.mouse_plan_motion == true) {

					Plan_motion.plan_motion(cad_demo.glcanvas, e.getX(), e.getY());

				}
			}
		});

		cad_demo.glcanvas.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = -e.getWheelRotation();
				double x = e.getX();
				double y = e.getY();
				Zoom.zoom(x, y, notches);

			}
		});
		
		cad_demo.glcanvas.addKeyListener(new KeyListener(){


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
		
		
		cad_demo.info.setText("Comand:");
		cmd_panel.add(cad_demo.info);
		
		final JTextField cmd = new JTextField(6);
		cmd_panel.add(cmd);
		
		bot_panel.add(cmd_panel);
		cmd_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		cad_demo.info2.setText(" ");
		bot_panel.add(cad_demo.info2);
		
		cad_demo.info_down.setText("Coordinates: X "+ 0 + "; Y " + 0 + ";");
		bot_panel.add(cad_demo.info_down);
		
		jframe.add(bot_panel, BorderLayout.SOUTH);
		jframe.add(cad_demo.glcanvas, BorderLayout.CENTER);

		
		
		

		jframe.setVisible(true);
		
	}
}
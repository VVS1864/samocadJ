package core;


/**
 * Class for manage of program states
 * 
 * @author vlad
 *
 */
public class Program_state {	
	/**class for set default state, kill all processes
	 * 
	 */
	private Core core = Core.c;
	public void set_default(){
		core.global.current_function = null;
		core.global.select_rect_vertices = null;
		core.global.preview_object_vertices = null;
		core.global.draw_new_object = false;
		core.global.select_mode = false;
		core.gui.info.setText("Comand:");
		core.gui.info2.setText(" ");
		core.gui.cmd.setText("");
		//core.gui.glcanvas.display();
	}
}
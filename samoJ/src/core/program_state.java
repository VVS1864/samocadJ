package core;


/**
 * Class for manage of program states
 * 
 * @author vlad
 *
 */
public class program_state {	
	/**static class for set default state, kill all processes
	 * 
	 */
	public static void set_default(){
		Global_var.current_function = null;
		Global_var.select_rect_vertices = null;
		Global_var.draw_new_object = false;
		Global_var.select_mode = false;
		Global_var.info.setText("Comand:");
		Global_var.info2.setText(" ");
		Global_var.cmd.setText("");
		Global_var.glcanvas.display();
	}
}
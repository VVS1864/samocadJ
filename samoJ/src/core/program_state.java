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
		cad_demo.current_function = null;
		cad_demo.draw_new_object = false;
		cad_demo.info.setText("Comand:");
		cad_demo.info2.setText(" ");
		cad_demo.cmd.setText("");
	}
}
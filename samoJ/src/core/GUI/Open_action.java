package core.GUI;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import core.Core;
import open_dxf_lib.DXF_file;
import open_dxf_lib.Mode;
import samoJ.Shape;

public class Open_action {
	private Core core = Core.c;
	public Open_action() {
		Save_dialog dialog = new Save_dialog(core.gui.jframe, "Open drawing", JFileChooser.OPEN_DIALOG, 
				core.values.current_working_dir, core.values.current_file_name);
		
		if(dialog.userSelection == JFileChooser.APPROVE_OPTION){
			
			Path p = Paths.get(dialog.selected_file);
			core.values.current_file_name = p.getFileName().toString();
			core.values.current_working_dir = p.getParent().toString();
			DXF_file f = new DXF_file(Mode.Open_file, dialog.selected_file);
			System.out.println("Open file: " + dialog.selected_file);
			
		}
	}
	public void shapes_from_DXF(DXF_file f) {
		
		//for(Shape sh: ){
		//	sh.save_to_DXF(f);
		//}
	}
	

}

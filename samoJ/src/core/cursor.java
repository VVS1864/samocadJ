package core;

import samoJ.Coord;
/**
 * Class container for cursor variables.
 * @author vlad
 *
 */
public class cursor extends Coord {
	private Coord snap_coord;
	private boolean plan_motion;
	
	

	public cursor(float x, float y) {
		super(x, y);
		
	}

	public cursor(float x, float y, float z) {
		super(x, y, z);
	}
	
	

}

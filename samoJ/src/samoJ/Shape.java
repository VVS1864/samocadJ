package samoJ;

public class Shape {
	Coord coords[];

	public Shape(Coord[] newCoords) {
		coords = newCoords;
	}

	void moveTo(Coord to)
	{
		
	for(Coord theCoord:coords){
		theCoord.moveTo(to);
	};
	
	};

}

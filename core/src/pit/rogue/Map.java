package pit.rogue;

public class Map {

	private Room maps[];
	private static Character player;
	private int roomNr;
	private static 
	
	
	public Map(){
		maps=new Room[2];
		maps[0]=new Room(null, roomNr);
	}
	
	public static  Object getRoom(int roomNr) {
		if(roomNr==1) {
			if(player.getX()==13 && player.getY()==5) {
				
			}
		}
		return null;
	}
}

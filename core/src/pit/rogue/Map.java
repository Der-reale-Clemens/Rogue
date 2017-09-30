package pit.rogue;

public class Map {
	
	private static Room[] rooms;
	private static int activeRoom;
	
	public Map() {
		rooms = new Room[4];
		for(int i = 0; i < 4; i++)
			rooms[i] = new Room();
		rooms[0].createRoom(RoomTypes.DEBUGMAP, RoomTypes.ENEMIES1);
		rooms[1].createRoom(RoomTypes.DEBUGMAP2, RoomTypes.ENEMIES2);
		rooms[2].createRoom(RoomTypes.DEBUGMAP3, RoomTypes.ENEMIES2);
		rooms[3].createRoom(RoomTypes.DEBUGMAP4, RoomTypes.ENEMIES2);
	}
	
	public void draw(final Rogue game) {
		rooms[activeRoom].draw(game);
	}
	
	public static void moveToNextRoom(Character player) {
		player.setX(rooms[activeRoom].getWarpTileX());
		player.setY(rooms[activeRoom].getWarpTileY());
		activeRoom++;
		rooms[activeRoom].spawnEnemys();
	}
	
	public static int getActiveRoom() {
		return activeRoom;
	}
	
	public static Room[] getRooms() {
		return rooms;
	}
}

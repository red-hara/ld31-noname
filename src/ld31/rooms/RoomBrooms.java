package ld31.rooms;

import Engine.RedImage;
import Engine.RedTilemap;
import ld31.FullState;
import ld31.Room;

/**
 *
 * @author hara
 */
public class RoomBrooms extends Room {

	public RoomBrooms() {
		super();
		bg.loadImage(RedImage.loadImage("/ld31/data/roomBroomsBg.png"), 144, 36);
		collide.loadTilemap(RedTilemap.imageToData(RedImage.loadImage("/ld31/data/roomBroomsMap.png")), null, 1, 1);
	}
	
	@Override
	public void update() {
		super.update();
		if (members.contains(FullState.proto)) {
			if (FullState.proto.x < 2) {
				moveProto(FullState.roomHall2, 56, 33);
			}
		}
	}
	
}

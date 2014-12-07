package ld31.rooms;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedObject;
import Engine.RedTilemap;
import ld31.Button;
import ld31.FullState;
import ld31.Guard;
import ld31.Room;

/**
 *
 * @author hara
 */
public class RoomHall2 extends Room{
	
	public Button doorButton0;
	public Button doorButton1;
	
	public RoomHall2() {
		super();
		bg.loadImage(RedImage.loadImage("/ld31/data/roomHall2Bg.png"), 144, 36);
		fg.loadImage(RedImage.loadImage("/ld31/data/roomHall2Fg.png"), 144, 36);
		collide.loadTilemap(RedTilemap.imageToData(RedImage.loadImage("/ld31/data/roomHall1Map.png")), null, 1, 1);
		collide.tiles[1].immovable = true;
		collide.tiles[1].allowCollisions = RedG.ANY;
		
		doorButton0 = new Button(56, 3, "/ld31/data/buttonC.png", this);
		doorButton1 = new Button(109, 3, "/ld31/data/buttonC.png", this);
		add(doorButton0);
		add(doorButton1);
		
		Guard guard = new Guard(8, 33, this);
		guard.destinations = new int[]{7, 128, 60};
		guards.add(guard);
		
		RedObject shadow = new RedObject(45, 13, 60, 19);
		darkness.add(shadow);
	}
	
	
	@Override
	public void update() {
		super.update();
		if (members.contains(FullState.proto)) {
			if (RedG.overlap(FullState.proto, doorButton0) && RedG.keys.justPressed('C')) {
				moveProto(FullState.roomBrooms, 6, 33);
			}
			if (RedG.overlap(FullState.proto, doorButton1) && RedG.keys.justPressed('C')) {
				moveProto(FullState.roomObservation, 6, 33);
			}
			if(FullState.proto.x < 2) {
				moveProto(FullState.roomHall1, 108, 33);
			}
		}
	}

}

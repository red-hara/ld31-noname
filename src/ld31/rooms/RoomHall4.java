package ld31.rooms;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedObject;
import ld31.Button;
import ld31.FullState;
import ld31.Guard;
import ld31.Room;

/**
 *
 * @author hara
 */
public class RoomHall4 extends Room {

	public Button doorButton0;
	public Button doorButton1;

	public RoomHall4() {
		super();
		bg.loadImage(RedImage.loadImage("/ld31/data/roomHall4Bg.png"), 144, 36);
//		fg.loadImage(RedImage.loadImage("/ld31/data/roomHall3Fg.png"), 144, 36);
//		collide.loadTilemap(RedTilemap.imageToData(RedImage.loadImage("/ld31/data/roomHall3Map.png")), null, 1, 1);
//		collide.tiles[1].immovable = true;
//		collide.tiles[1].allowCollisions = RedG.ANY;

		doorButton0 = new Button(120, 3, "/ld31/data/buttonC.png", this);
		doorButton1 = new Button(55, 3, "/ld31/data/buttonC.png", this);
		add(doorButton0);
		add(doorButton1);
		
		Guard guard = new Guard(112, 33, this);
		guard.destinations = new int[]{12, 66, 119, 66};
		guards.add(guard);

		RedObject shadow = new RedObject(3, 21, 58, 12);
		darkness.add(shadow);
	}
	
	@Override
	public void update() {
		super.update();
		if (members.contains(FullState.proto)) { 
			if( FullState.proto.x < 2 ) {
				moveProto(FullState.roomGate, 40, 33);
			} else if( RedG.overlap(doorButton0, FullState.proto) && RedG.keys.justPressed('C')) {
				moveProto(FullState.roomHall3, 6, 33);
			} else if( RedG.overlap(doorButton1, FullState.proto) && RedG.keys.justPressed('C')) {
				moveProto(FullState.roomConsole, 40, 33);
			}
		}
	}
}

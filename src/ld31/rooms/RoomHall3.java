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
public class RoomHall3 extends Room {

	public Button doorButton0;
	public Button doorButton1;

	public RoomHall3() {
		super();
		bg.loadImage(RedImage.loadImage("/ld31/data/roomHall3Bg.png"), 144, 36);
		fg.loadImage(RedImage.loadImage("/ld31/data/roomHall3Fg.png"), 144, 36);
//		collide.loadTilemap(RedTilemap.imageToData(RedImage.loadImage("/ld31/data/roomHall3Map.png")), null, 1, 1);
//		collide.tiles[1].immovable = true;
//		collide.tiles[1].allowCollisions = RedG.ANY;

		doorButton0 = new Button(83, 3, "/ld31/data/buttonC.png", this);
		doorButton1 = new Button(28, 3, "/ld31/data/buttonC.png", this);
		add(doorButton0);
		add(doorButton1);

		Guard guard = new Guard(112, 33, this);
		guard.destinations = new int[]{106};
		guards.add(guard);

		RedObject shadow = new RedObject(3, 21, 58, 12);
		darkness.add(shadow);
	}

	@Override
	public void update() {
		super.update();
		if (members.contains(FullState.proto)) {
			if (RedG.overlap(FullState.proto, doorButton0) && RedG.keys.justPressed('C')) {
				moveProto(FullState.roomControl, 40, 33);
			} else if (RedG.overlap(FullState.proto, doorButton1) && RedG.keys.justPressed('C')) {
				moveProto(FullState.roomHall1, 6, 33);
			} else if (FullState.proto.x < 2) {
				moveProto(FullState.roomHall4, 116, 33);
			} else if (FullState.proto.x > 3 * 48 - 4) {
				moveProto(FullState.roomGate, 6, 33);
			}
		}
	}

}

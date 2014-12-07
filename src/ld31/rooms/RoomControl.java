package ld31.rooms;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedTilemap;
import ld31.FullState;
import ld31.Guard;
import ld31.Room;

/**
 *
 * @author hara
 */
public class RoomControl extends Room {
	
	public ScreenGuard screen;

	public RoomControl() {
		super();
		bg.loadImage(RedImage.loadImage("/ld31/data/roomControlBg.png"), 48, 36);
		collide.loadTilemap(RedTilemap.imageToData(RedImage.loadImage("/ld31/data/roomControlMap.png")), null, 1, 1);
		collide.tiles[1].immovable = true;
		collide.tiles[1].allowCollisions = RedG.ANY;
		
		screen = new ScreenGuard(this);
		guards.add(screen);

	}

	@Override
	public void update() {
		super.update();
		if (members.contains(FullState.proto)) {
			if (FullState.proto.x > 44) {
				moveProto(FullState.roomHall3, 83, 33);
			}
		}
	}
	
	public class ScreenGuard extends Guard {
		public ScreenGuard(Room RoomAround) {
			super(30, 33, RoomAround);
			destinations = new int[]{22};
		}
		
		@Override
		public void die() {
			super.die();
			FullState.fullScreen.border.loadImage(RedImage.loadImage("/ld31/data/borderBroken.png"), 400, 232);
			FullState.console.text.text = "Status:\nControl\nlost";
			FullState.proto.exist = false;
			FullState.proto.visible = false;
		}
	}
}

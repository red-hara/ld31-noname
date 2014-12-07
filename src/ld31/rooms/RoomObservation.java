package ld31.rooms;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedTilemap;
import ld31.Button;
import ld31.FullState;
import ld31.Room;

/**
 *
 * @author hara
 */
public class RoomObservation extends Room {

	public Button button;

	public RoomObservation() {
		bg.loadImage(RedImage.loadImage("/ld31/data/roomObservation0Bg.png"), 48, 36);
		collide.loadTilemap(RedTilemap.imageToData(RedImage.loadImage("/ld31/data/roomCellMap.png")), null, 1, 1);
		collide.tiles[1].immovable = true;
		collide.tiles[1].allowCollisions = RedG.ANY;

		guards.add(FullState.roomCell.glass);
		add(FullState.roomCell.effects);

		button = new Button(19, 3, "/ld31/data/buttonC.png", this);
		add(button);
	}
	
	@Override
	public void update() {
		super.update();
		button.visible = !FullState.roomCell.glass.exist && button.visible;
		if(members.contains(FullState.proto)) {
			if( FullState.proto.x < 2 ) {
				moveProto(FullState.roomHall2, 108, 33);
			}
			if( RedG.overlap(FullState.proto, button) && RedG.keys.justPressed('C') && button.visible) {
				moveProto(FullState.roomCell, 30, 33);
			}
		}
	}
}

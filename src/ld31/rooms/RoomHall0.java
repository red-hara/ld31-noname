package ld31.rooms;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedSound;
import Engine.RedSprite;
import Engine.RedTilemap;
import ld31.Button;
import ld31.FullState;
import ld31.Guard;
import ld31.Room;

/**
 *
 * @author hara
 */
public class RoomHall0 extends Room {

	public Button doorButton;
	public Button lockerButton;
	public Button attackButton;
	public Guard guard;

	public RoomHall0() {
		bg.loadImage(RedImage.loadImage("/ld31/data/roomHall0Bg.png"), 144, 36);
		fg.loadImage(RedImage.loadImage("/ld31/data/roomHall0Fg.png"), 144, 36);
		collide.loadTilemap(RedTilemap.imageToData(RedImage.loadImage("/ld31/data/roomHall0Map.png")), null, 1, 1);
		collide.tiles[1].immovable = true;
		collide.tiles[1].allowCollisions = RedG.ANY;

		doorButton = new Button(60, 3, "/ld31/data/buttonC.png", this);
		lockerButton = new Button(5, 3, "/ld31/data/buttonC.png", this);
		attackButton = new Button(28, 3, "/ld31/data/buttonX.png", this);
		add(doorButton);
		add(lockerButton);
		add(attackButton);

		guard = new Guard(48 * 2, 33, this);
		guard.destinations = new int[]{90, 102, 129};
		guards.add(guard);
		
		RedSprite shadow = new RedSprite(51, 18, 30, 6);
		darkness.add(shadow);
	}

	@Override
	public void update() {
		super.update();
		attackButton.visible = attackButton.visible && FullState.proto.canAttack;
		lockerButton.visible = lockerButton.visible && !FullState.proto.canAttack;
		if (members.contains(FullState.proto)) {
			if (RedG.overlap(FullState.proto, doorButton) && RedG.keys.justPressed('C')) {
				moveProto(FullState.roomCell, 6, 33);
			}
			if (RedG.overlap(FullState.proto, lockerButton) && RedG.keys.justPressed('C') && lockerButton.visible) {
				FullState.proto.canAttack = true;
				FullState.console.text.text = "Subject\nfound\nweapon";
				RedSound sound = new RedSound("/ld31/data/door.wav");
				sound.start();
			}
			if (FullState.proto.x > 138) {
				moveProto(FullState.roomHall1, 20, 33);
			}
		}
	}
}

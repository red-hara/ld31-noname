package ld31.rooms;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedSound;
import Engine.RedTilemap;
import ld31.Button;
import ld31.FullState;
import ld31.Room;

/**
 *
 * @author hara
 */
public class RoomConsole extends Room {

	public Button consoleButton;
	public boolean lockdown = false;

	public RoomConsole() {
		super();
		bg.loadImage(RedImage.loadImage("/ld31/data/roomConsoleBg.png"), 48, 36);
		collide.loadTilemap(RedTilemap.imageToData(RedImage.loadImage("/ld31/data/roomControlMap.png")), null, 1, 1);
		collide.tiles[1].immovable = true;
		collide.tiles[1].allowCollisions = RedG.ANY;
		
		consoleButton = new Button(12, 3, "/ld31/data/buttonC.png", this);
		add(consoleButton);

	}

	@Override
	public void update() {
		super.update();
		if (members.contains(FullState.proto)) {
			if (FullState.proto.x > 44) {
				moveProto(FullState.roomHall4, 53, 33);
			}
			if( RedG.overlap(consoleButton, FullState.proto) && RedG.keys.justPressed('C')) {
				if( lockdown ) {
					FullState.console.text.text = "Acces\ndenied";
				} else {
					FullState.roomGate.gate.visible = true;
					RedSound sound = new RedSound("/ld31/data/kill.wav");
					sound.start();
				}
			}
		}
	}

}

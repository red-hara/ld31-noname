package ld31;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedSound;
import Engine.RedSprite;
import static ld31.Guard.DEAD;

/**
 *
 * @author hara
 */
public class Glass extends Guard {

	public Glass(Room RoomAround) {
		super(8, 8, RoomAround);
		loadImage(RedImage.loadImage("/ld31/data/glass.png"), 30, 18);
		width = 30;
		height = 18;
		offset.x = offset.y = 0;
		allowCollisions = RedG.NONE;
		x = 8;
		y = 8;
	}

	@Override
	public void update() {
	}
	
	@Override
	public void die() {
		action = DEAD;
		visible = false;
		exist = false;
		allowCollisions = RedG.NONE;
		roomAround.guards.remove(this);
		for (int i = 0; i < finalFrame.width; i++) {
			for (int j = 0; j < finalFrame.height; j++) {
				int rgb;
				if ((rgb = finalFrame.getRGB(i, j)) != 0x00000000) {
					RedSprite bit = new RedSprite(x + offset.x + i, y + offset.y + j, 1, 1);
					bit.makeGraphic(1, 1, rgb);
					bit.allowCollisions = RedG.NONE;
					bit.velocity.y = 2 * (-finalFrame.height + j) * (0.5 + RedG.random());
					bit.acceleration.y = 32;
					bit.velocity.x = 16 - 32 * RedG.random();
					roomAround.effects.add(bit);
				}
			}
		}
//		FullState.roomConsole.lockdown = true;
//		FullState.console.text.text = "Unit lost\nLockdown on";
		RedSound sound = new RedSound("/ld31/data/kill.wav");
		sound.start();
	}
}

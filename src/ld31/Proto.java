package ld31;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedObject;
import Engine.RedSound;
import Engine.RedSprite;

/**
 *
 * @author hara
 */
public class Proto extends RedSprite {

	public Room roomAround;
	public boolean canBeSeen = true;
	public boolean canAttack = false;
	public RedObject killZone = new RedObject(0, 0, 8, 19);

	public Proto(double X, double Y) {
		super(X, Y - 19, 6, 19);
		offset.y = -4;

		loadImage(RedImage.loadImage("/ld31/data/proto.png"), 24, 24);
		addAnimation(new int[]{0, 1, 2, 3, 4, 5}, 0);
		addAnimation(new int[]{11, 10, 9, 8, 7, 6}, 1);
		addAnimation(new int[]{12}, 2);
		addAnimation(new int[]{13}, 3);
		addAnimation(new int[]{14, 16, 16, 16}, 4);
		addAnimation(new int[]{15, 17, 17, 17}, 5);
		offset.x = -6;
		playAnimation(2, -1);
	}

	@Override
	public void update() {
		super.update();
		velocity.x = 0;
		if (RedG.keys.isPressed(RedG.keys.LEFT)) {
			velocity.x = -30;
			facing = RedG.LEFT;
			playAnimation(1, 0.1);
			offset.x = -11;
		} else if (RedG.keys.isPressed(RedG.keys.RIGHT)) {
			velocity.x = 30;
			facing = RedG.RIGHT;
			playAnimation(0, 0.1);
			offset.x = -6;
		} else if (facing == RedG.LEFT && animationCurrent != 4 && animationCurrent != 5) {
			playAnimation(3, -1);
		} else if (animationCurrent != 4 && animationCurrent != 5) {
			playAnimation(2, -1);
		}
		if (RedG.keys.justPressed('X') && canAttack) {
			RedSound sound = new RedSound("/ld31/data/hit.wav");
			sound.start();
			if (facing == RedG.LEFT) {
				killZone.x = x - killZone.width;
				killZone.y = y;
				playAnimation(5, 0.25);
			} else {
				killZone.x = x + width;
				killZone.y = y;
				playAnimation(4, 0.25);
			}
		}
		if ((animationCurrent == 4 || animationCurrent == 5) && animationFrame == 2) {
			roomAround.killGuard(killZone);
		}
		if ((animationCurrent == 4 || animationCurrent == 5) && animationFrame == 3) {
			if (facing == RedG.LEFT) {
				playAnimation(3, -1);
			} else {
				playAnimation(2, -1);
			}
		}
		if (roomAround != null) {
			canBeSeen = !RedG.overlap(this, roomAround.darkness);
		}
	}

	public void die() {
		if (exist) {
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
			RedSound sound = new RedSound("/ld31/data/kill.wav");
			sound.start();
			FullState.arrowsScreen.arrows.loadImage(RedImage.loadImage("/ld31/data/buttonsR.png"), 48, 36);
			FullState.console.text.text = "Status:\nSubject\nterminated";
		}
	}

}

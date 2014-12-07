package ld31;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedMethod;
import Engine.RedSound;
import Engine.RedSprite;
import Engine.RedTimer;

/**
 *
 * @author hara
 */
public class Guard extends RedSprite {

	public Room roomAround;
	public static final int STAY = 0;
	public static final int WALK = 1;
	public static final int FIRE = 2;
	public static final int DEAD = 3;
	public int action = STAY;
	public int[] destinations;
	public int destinationIndex = 0;
	public RedMethod timerMethod = new RedMethod() {
		@Override
		public void execute() {
			if (action != DEAD) {
				action = WALK;
				destinationIndex = (destinationIndex + 1) % destinations.length;
				if( Math.abs(x + width / 2 - destinations[destinationIndex]) < 2 ) {
					action = STAY;
				}
			}
		}
	};
	public RedTimer timer = new RedTimer(timerMethod);

	public Guard(double X, double Y, Room RoomAround) {
		super(X, Y - 19, 6, 19);

		roomAround = RoomAround;
		offset.y = -4;

		loadImage(RedImage.loadImage("/ld31/data/guard.png"), 24, 24);
		addAnimation(new int[]{0, 1, 2, 3, 4, 5}, 0);
		addAnimation(new int[]{11, 10, 9, 8, 7, 6}, 1);
		addAnimation(new int[]{12}, 2);
		addAnimation(new int[]{13}, 3);
		addAnimation(new int[]{16, 14, 14, 14}, 4);
		addAnimation(new int[]{17, 15, 15, 15}, 5);
		offset.x = -6;
		playAnimation(2, -1);
		facing = RedG.RIGHT;
		timer.start(2 * RedG.random(), 1);
	}

	@Override
	public void update() {
		super.update();
		timer.update();
		velocity.x = 0;
		switch (action) {
			case STAY: {
				if (facing == RedG.LEFT) {
					playAnimation(3, -1);
				} else {
					playAnimation(2, -1);
				}
				break;
			}
			case WALK: {
				if (destinations[destinationIndex] < x + width / 2) {
					velocity.x = -30;
					facing = RedG.LEFT;
					playAnimation(1, 0.1);
					offset.x = -11;
				} else {
					velocity.x = 30;
					facing = RedG.RIGHT;
					playAnimation(0, 0.1);
					offset.x = -6;
				}
				if (Math.abs(x + width / 2 - destinations[destinationIndex]) < 2) {
					velocity.x = 0;
					action = STAY;
					timer.start(3, 1);
				}
				break;
			}
			case FIRE: {
				switch (animationFrame) {
					case 2:
						FullState.proto.die();
						break;
					case 3:
						action = STAY;
						if (facing == RedG.LEFT) {
							playAnimation(3, -1);
						} else {
							playAnimation(2, -1);
						}
						break;
				}
				break;
			}
		}
		if (action != DEAD && FullState.proto.exist) {
			if (roomAround == FullState.proto.roomAround
					&& ((FullState.proto.canBeSeen
					&& ((facing == RedG.LEFT && FullState.proto.x + FullState.proto.width / 2 < x + width / 2)
					|| (facing == RedG.RIGHT && FullState.proto.x + FullState.proto.width / 2 > x + width / 2))
					&& Math.abs(FullState.proto.x + FullState.proto.width / 2 - x - width / 2) < 48)
					|| (RedG.overlap(this, FullState.proto)))) {
				action = FIRE;
				if (facing == RedG.LEFT) {
					playAnimation(5, 0.25);
				} else {
					playAnimation(4, 0.25);
				}
			}
		}
	}

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
		FullState.roomConsole.lockdown = true;
		FullState.console.text.text = "Unit lost\nLockdown on";
		RedSound sound = new RedSound("/ld31/data/kill.wav");
		sound.start();
	}
}

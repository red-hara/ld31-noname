package ld31;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedSprite;

/**
 *
 * @author hara
 */
public class Button extends RedSprite{

	public Room roomAround;
	
	public Button(double X, double Y, String Adress, Room RoomAround) {
		super(X, Y, 7, 64);
		loadImage(RedImage.loadImage(Adress), 7, 9);
		
		roomAround = RoomAround;
		
		addAnimation(new int[]{0,1}, 0);
		playAnimation(0, 0.5);
		allowCollisions = RedG.NONE;
		visible = false;
	}

	@Override
	public void update() {
		super.update();
		visible = RedG.overlap(this, FullState.proto) && roomAround.members.contains(FullState.proto);
	}
	
}

package ld31;

import Engine.RedCanvas;
import Engine.RedImage;
import Engine.RedSprite;
import Engine.RedState;

/**
 *
 * @author hara
 */
public class FullScreen extends RedState{
	
	public RedSprite border = new RedSprite(0, 0, 0, 0);
	public RedSprite bar = new RedSprite(0, 0, 0, 0);
	
	public FullScreen() {
		super(8, 8, 384, 216, 1);
		bgColor = 0xff000000;
		border.loadImage(RedImage.loadImage("/ld31/data/border.png"), 400, 232);
		bar.makeGraphic(400, 2, 0x40ffffff);
		bar.velocity.y = 64;
	}
	
	@Override
	public void update(){
		bar.preUpdate();
		bar.update();
		bar.postUpdate();
		if( bar.y > 232 ) {
			bar.y = -2;
		}
	}
	
	@Override
	public void draw(RedCanvas Canvas) {
		super.draw(Canvas);
		bar.draw(Canvas);
		border.draw(Canvas);
	}

}

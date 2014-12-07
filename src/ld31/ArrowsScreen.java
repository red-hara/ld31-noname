package ld31;

import Engine.RedImage;
import Engine.RedSprite;

/**
 *
 * @author hara
 */
public class ArrowsScreen extends SubScreen{

	public RedSprite arrows;
	
	public ArrowsScreen(double X, double Y, double Width, double Height, double Zoom) {
		super(X, Y, Width, Height, Zoom);
		arrows = new RedSprite(0, 0, 0, 0);
		arrows.loadImage(RedImage.loadImage("/ld31/data/buttonsArrows.png"), 48, 36);
		arrows.addAnimation(new int[]{0,1,0,2}, 0);
		arrows.playAnimation(0, 0.5);
		add(arrows);
	}
}

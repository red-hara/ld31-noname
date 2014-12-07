package ld31;

import Engine.RedImage;
import Engine.RedSprite;

/**
 *
 * @author hara
 */
public class EscapeScreen extends SubScreen{

	public RedSprite escape;
	
	public EscapeScreen(double X, double Y, double Width, double Height, double Zoom) {
		super(X, Y, Width, Height, Zoom);
		bgColor = 0xff000000;
		escape = new RedSprite(0, 0, 0, 0);
		escape.loadImage(RedImage.loadImage("/ld31/data/buttonsEsc.png"), 48, 36);
		escape.addAnimation(new int[]{0,1,0,2}, 0);
		escape.playAnimation(0, 0.3);
		add(escape);
	}
}

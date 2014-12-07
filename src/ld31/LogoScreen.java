package ld31;

import Engine.RedImage;
import Engine.RedSprite;
import Engine.RedText;

/**
 *
 * @author hara
 */
public class LogoScreen extends SubScreen{
	
	public RedSprite head = new RedSprite(2, 36*2-55, 0, 0);
	public RedText author = new RedText(2, 2, "By Red\nHara");

	public LogoScreen(double X, double Y, double Width, double Height, double Zoom) {
		super(X, Y, Width, Height, Zoom);
		head.loadImage(RedImage.loadImage("/ld31/data/logo.png"), 48, 55);
		author.color = 0xff804c4c;
		author.shadow = 0xff402626;
		add(head);
		add(author);
	}

}

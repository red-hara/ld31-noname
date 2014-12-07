package ld31;

import Engine.RedImage;
import Engine.RedSprite;
import Engine.RedText;

/**
 *
 * @author hara
 */
public class EngineScreen extends SubScreen{

	public RedSprite engineSymbol = new RedSprite(2, 2, 0, 0);
	public RedText author = new RedText(2, 38, "Powered by\nRed Engine.");
	
	public EngineScreen(double X, double Y, double Width, double Height, double Zoom) {
		super(X, Y, Width, Height, Zoom);
		engineSymbol.loadImage(RedImage.loadImage("/Engine/system/data/EngineSymbol.png"), 40, 35);
		engineSymbol.addAnimation(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, 0);
		engineSymbol.playAnimation(0, 0.1);
		
		author.color = 0xffffff00;
		
		add(engineSymbol);
		add(author);
		
		bgColor = 0xff800000;
	}

}

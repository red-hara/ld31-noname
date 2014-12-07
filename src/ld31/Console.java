package ld31;

import Engine.RedText;

/**
 *
 * @author hara
 */
public class Console extends SubScreen{

	public RedText text;
	
	public Console(double X, double Y, double Width, double Height, double Zoom) {
		super(X, Y, Width, Height, Zoom);
		text = new RedText(2, 2, "Opening cell\n...\nComplete");
		text.color = 0xff066d06;
		add(text);
		
		bgColor = 0xff061506;
	}
}

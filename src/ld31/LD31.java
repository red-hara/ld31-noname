package ld31;

import Engine.RedG;
import Engine.RedGame;

/**
 *
 * @author hara
 */
public class LD31 extends RedGame{

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		LD31 game = new LD31();
		RedGame.state = new FullState();
		game.start();
	}

	public LD31() {
		super(400, 232, null, 1, 25, 25, "LD31", true, 0xff1c160c, 1);
		RedGame.getWindow().resizableCanvas = false;
		scaleType = PROPOTION;
		RedG.setFullscreen(true);
	}
	
}

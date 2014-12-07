package ld31.rooms;

import Engine.RedG;
import Engine.RedImage;
import Engine.RedSprite;
import ld31.Button;
import ld31.FullState;
import ld31.Room;

/**
 *
 * @author hara
 */
public class RoomGate extends Room {

	public Gate gate;
	public Button gateButton;
	
	public RoomGate() {
		super();
		bg.loadImage(RedImage.loadImage("/ld31/data/roomGateBg.png"), 48, 36);
		gate = new Gate();
		add(gate);
		
		gateButton = new Button(22, 3, "/ld31/data/buttonC.png", this);
		add(gateButton);
	}
	
	@Override
	public void update() {
		super.update();
		gateButton.visible = gateButton.visible && gate.visible;
		if (members.contains(FullState.proto)) { 
			if( FullState.proto.x < 2 ) {
				moveProto(FullState.roomHall3, 132, 33);
			} else if( FullState.proto.x > 44 ) {
				moveProto(FullState.roomHall4, 4, 33);
			} else if( gateButton.visible && RedG.keys.justPressed('C') ) {
				FullState.proto.exist = false;
				FullState.proto.visible = false;
				FullState.console.text.text = "Status:\nSubject\nescaped.";
			}
		}
	}
	
	public class Gate extends RedSprite {

		public Gate() {
			super(0, 0, 0, 0);
			loadImage(RedImage.loadImage("/ld31/data/gate.png"), 48, 36);
			visible = false;
		}
		
	}
}

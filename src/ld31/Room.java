package ld31;

import Engine.RedBasic;
import Engine.RedCanvas;
import Engine.RedG;
import Engine.RedGroup;
import Engine.RedObject;
import Engine.RedSound;
import Engine.RedSprite;
import Engine.RedTilemap;

/**
 *
 * @author hara
 */
public class Room extends RedGroup{
	public RedTilemap collide = new RedTilemap(0, 0);
	public RedSprite bg = new RedSprite(0, 0, 0, 0);
	public RedSprite fg = new RedSprite(0, 0, 0, 0);
	public RedGroup darkness = new RedGroup();
	public RedGroup guards = new RedGroup();
	public RedGroup effects = new RedGroup();
	
	public Room() {
		super();
		add(bg);
		add(effects);
		add(guards);
//		add(collide);
		collide.immovable = true;
	}
	
	@Override
	public void update() {
		super.update();
		if( collide.width > 0 && collide.height > 0 ) {
			RedG.collide(collide, this);
		}
	}
	
	public void killGuard( RedObject KillZone ) {
		for( RedBasic basic : guards.members ) {
			if( basic instanceof Guard ) {
				if( RedG.overlap(basic, KillZone)){
					((Guard)basic).die();
				}
			}
		}
	}
	
	public void moveProto(Room Destination, double X, double Y ) {
		remove(members.indexOf(FullState.proto));
		Destination.add(FullState.proto);
		FullState.proto.roomAround = Destination;
		FullState.proto.x = X;
		FullState.proto.y = Y - 19;
		FullState.proto.update();
		RedSound sound = new RedSound("/ld31/data/door.wav");
		sound.start();
	}
	
	@Override
	public void draw(RedCanvas Canvas) {
		super.draw(Canvas);
		fg.draw(Canvas);
	}
}

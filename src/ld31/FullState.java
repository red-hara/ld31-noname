package ld31;

import Engine.RedG;
import Engine.RedGame;
import Engine.RedGroup;
import ld31.rooms.RoomBrooms;
import ld31.rooms.RoomCell;
import ld31.rooms.RoomConsole;
import ld31.rooms.RoomControl;
import ld31.rooms.RoomGate;
import ld31.rooms.RoomHall0;
import ld31.rooms.RoomHall1;
import ld31.rooms.RoomHall2;
import ld31.rooms.RoomHall3;
import ld31.rooms.RoomHall4;
import ld31.rooms.RoomObservation;

/**
 *
 * @author hara
 */
public class FullState extends RedGroup {

	public static FullScreen fullScreen = new FullScreen();
	public Updater updater = new Updater();

	public static Proto proto;

	public static RoomCell roomCell;
	public static RoomHall0 roomHall0;
	public static RoomHall1 roomHall1;
	public static RoomHall2 roomHall2;
	public static RoomHall3 roomHall3;
	public static RoomHall4 roomHall4;
	public static RoomBrooms roomBrooms;
	public static RoomConsole roomConsole;
	public static RoomControl roomControl;
	public static RoomGate roomGate;
	public static RoomObservation roomObservation;

	public static SubScreen screenCell;
	public static SubScreen screenHall00;
	public static SubScreen screenHall01;
	public static SubScreen screenHall02;
	public static SubScreen screenHall10;
	public static SubScreen screenHall11;
	public static SubScreen screenHall12;
	public static SubScreen screenHall20;
	public static SubScreen screenHall21;
	public static SubScreen screenHall22;
	public static SubScreen screenHall30;
	public static SubScreen screenHall31;
	public static SubScreen screenHall32;
	public static SubScreen screenHall40;
	public static SubScreen screenHall41;
	public static SubScreen screenHall42;
	public static SubScreen screenBrooms;
	public static SubScreen screenConsole;
	public static SubScreen screenControl;
	public static SubScreen screenGate;
	public static SubScreen screenObservation0;

	public static EngineScreen engineScreen;
	public static LogoScreen logoScreen;
	public static Console console;
	public static ArrowsScreen arrowsScreen;
	public static EscapeScreen escapeScreen;

	public FullState() {
		super();
		proto = new Proto(16, 33);

		roomCell = new RoomCell();
		roomCell.add(proto);
		screenCell = new SubScreen(0, 0, 48, 36, 1);
		screenCell.zoom = 3;
		screenCell.add(roomCell);
		proto.roomAround = roomCell;

		roomHall0 = new RoomHall0();
		roomHall1 = new RoomHall1();
		roomHall2 = new RoomHall2();
		roomHall3 = new RoomHall3();
		roomHall4 = new RoomHall4();
		roomBrooms = new RoomBrooms();
		roomConsole = new RoomConsole();
		roomControl = new RoomControl();
		roomGate = new RoomGate();
		roomObservation = new RoomObservation();

		updater.add(roomCell);
		updater.add(roomHall0);
		updater.add(roomHall1);
		updater.add(roomHall2);
		updater.add(roomHall3);
		updater.add(roomHall4);
		updater.add(roomBrooms);
		updater.add(roomConsole);
		updater.add(roomControl);
		updater.add(roomGate);
		updater.add(roomObservation);

		screenHall00 = new SubScreen(48, 108, 48, 36, 1);
		screenHall00.add(roomHall0);
		screenHall01 = new SubScreen(192, 180, 48, 36, 1);
		screenHall01.stateCanvas.offset.x = -48;
		screenHall01.add(roomHall0);
		screenHall02 = new SubScreen(6 * 48, 0 * 36, 48, 36, 1);
		screenHall02.stateCanvas.offset.x = -48 * 2;
		screenHall02.add(roomHall0);

		screenHall10 = new SubScreen(2 * 48, 3 * 36, 48, 36, 1);
		screenHall10.add(roomHall1);
		screenHall11 = new SubScreen(7 * 48, 0 * 36, 48, 36, 1);
		screenHall11.stateCanvas.offset.x = -48;
		screenHall11.add(roomHall1);
		screenHall12 = new SubScreen(0 * 48, 4 * 36, 48, 36, 1);
		screenHall12.stateCanvas.offset.x = -48 * 2;
		screenHall12.add(roomHall1);

		screenHall20 = new SubScreen(5 * 48, 4 * 36, 48, 36, 1);
		screenHall20.add(roomHall2);
		screenHall21 = new SubScreen(6 * 48, 1 * 36, 48, 36, 1);
		screenHall21.stateCanvas.offset.x = -48;
		screenHall21.add(roomHall2);
		screenHall22 = new SubScreen(4 * 48, 1 * 36, 48, 36, 1);
		screenHall22.stateCanvas.offset.x = -48 * 2;
		screenHall22.add(roomHall2);

		screenHall30 = new SubScreen(3 * 48, 0 * 36, 48, 36, 1);
		screenHall30.add(roomHall3);
		screenHall31 = new SubScreen(7 * 48, 1 * 36, 48, 36, 1);
		screenHall31.stateCanvas.offset.x = -48;
		screenHall31.add(roomHall3);
		screenHall32 = new SubScreen(6 * 48 / 2, 3 * 36 / 2, 48, 36, 1);
		screenHall32.zoom = 2;
		screenHall32.stateCanvas.offset.x = -48 * 2;
		screenHall32.add(roomHall3);

		screenHall40 = new SubScreen(4 * 48, 0 * 36, 48, 36, 1);
		screenHall40.add(roomHall4);
		screenHall41 = new SubScreen(6 * 48, 2 * 36, 48, 36, 1);
		screenHall41.stateCanvas.offset.x = -48;
		screenHall41.add(roomHall4);
		screenHall42 = new SubScreen(3 * 48, 4 * 36, 48, 36, 1);
		screenHall42.stateCanvas.offset.x = -48 * 2;
		screenHall42.add(roomHall4);

		screenBrooms = new SubScreen(3 * 48, 1 * 36, 48, 36, 1);
		screenBrooms.add(roomBrooms);

		screenConsole = new SubScreen(0 * 48, 5 * 36, 48, 36, 1);
		screenConsole.add(roomConsole);

		screenControl = new SubScreen(1 * 48 / 2, 4 * 36 / 2, 48, 36, 1);
		screenControl.zoom = 2;
		screenControl.add(roomControl);

		screenGate = new SubScreen(4 * 48 / 2, 2 * 36 / 2, 48, 36, 1);
		screenGate.zoom = 2;
		screenGate.add(roomGate);

		screenObservation0 = new SubScreen(7 * 48, 5 * 36, 48, 36, 1);
		screenObservation0.add(roomObservation);

		engineScreen = new EngineScreen(5 * 48, 0 * 36, 48, 2 * 36, 1);
		updater.add(engineScreen);
		logoScreen = new LogoScreen(3 * 48, 2 * 36, 48, 2 * 36, 1);
		updater.add(logoScreen);
		console = new Console(4*48, 4*36, 48, 36, 1);
		updater.add(console);
		arrowsScreen = new ArrowsScreen(0*48, 3*36, 48, 36, 1);
		updater.add(arrowsScreen);
		escapeScreen = new EscapeScreen(7*48, 2*36, 48, 36, 1);
		updater.add(escapeScreen);

		fullScreen.add(screenCell);
		fullScreen.add(screenHall00);
		fullScreen.add(screenHall01);
		fullScreen.add(screenHall02);
		fullScreen.add(screenHall10);
		fullScreen.add(screenHall11);
		fullScreen.add(screenHall12);
		fullScreen.add(screenHall20);
		fullScreen.add(screenHall21);
		fullScreen.add(screenHall22);
		fullScreen.add(screenHall30);
		fullScreen.add(screenHall31);
		fullScreen.add(screenHall32);
		fullScreen.add(screenHall40);
		fullScreen.add(screenHall41);
		fullScreen.add(screenHall42);
		fullScreen.add(screenBrooms);
		fullScreen.add(screenConsole);
		fullScreen.add(screenControl);
		fullScreen.add(screenGate);
		fullScreen.add(screenObservation0);
		fullScreen.add(engineScreen);
		fullScreen.add(logoScreen);
		fullScreen.add(console);
		fullScreen.add(arrowsScreen);
		fullScreen.add(escapeScreen);

		add(updater);
		add(fullScreen);
	}

	@Override
	public void update() {
		super.update();
		if (RedG.keys.justPressed('R')) {
			RedGame.state = new FullState();
		}
		if (RedG.keys.justPressed(RedG.keys.ESC)) {
			System.exit(0);
		}
	}
}

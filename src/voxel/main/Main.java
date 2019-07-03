package voxel.main;

import voxel.game.Game;
import voxel.manager.GameManager;

public class Main {
	
	private static Game game;
	
	public Main() {
		Main.game = new Game();
	}
	
	public static void main(String[] args) {
		new Main();
		
		GameManager.init(Main.getGame());
		GameManager.loop(Main.getGame());
	}
	
	public static Game getGame() {
		return Main.game;
	}

}

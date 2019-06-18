package edu.ucf.daredevil;

import com.badlogic.gdx.Game;

import edu.ucf.daredevil.screens.GameOver;
import edu.ucf.daredevil.screens.MainMenu;

public class GameController extends Game {
	
	@Override
	public void create () {
		setScreen(new MainMenu(this));
	}

}

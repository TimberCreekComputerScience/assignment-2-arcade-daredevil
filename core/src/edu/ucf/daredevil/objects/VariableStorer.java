package edu.ucf.daredevil.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class VariableStorer {

    public static int score = 0;
    public static int coins = 0;
    public static Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

    public VariableStorer(){


    }
}

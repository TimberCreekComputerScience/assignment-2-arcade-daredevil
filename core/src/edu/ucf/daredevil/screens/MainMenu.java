package edu.ucf.daredevil.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import edu.ucf.daredevil.GameController;
import edu.ucf.daredevil.objects.VariableStorer;
import edu.ucf.daredevil.screens.MainGame;

public class MainMenu implements Screen {

    SpriteBatch batch;

    GameController myGame;
    //background
    Texture main;
    Sprite menu;
    //title
    Texture dare;
    Sprite devil;
    //start button
    Texture button;
    Sprite start;
    //camera
    OrthographicCamera camera;
    //hit box
    Rectangle hitbox;

    public MainMenu(GameController g){
        myGame = g;

    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        //background
        main = new Texture ("daredevil.jpg");
        menu = new Sprite (main);
        menu.setPosition(0,0);
        menu.setSize(1000, 800);
        //title
        dare = new Texture ("title.png");
        devil = new Sprite(dare);
        devil.setPosition(275, 550);
        devil.setSize(450, 150);
        //start button
        button = new Texture ("start.png");
        start = new Sprite(button);
        start.setPosition(250, 315);
        start.setSize(450, 150);
        //camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 700, 900);
        //hit box
        hitbox = new Rectangle(222, 160, 20, 100);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        menu.draw(batch);
        devil.draw(batch);
        start.draw(batch);
        batch.end();
        VariableStorer.music.play();
        if(Gdx.input.justTouched() == true) {
            Vector3 touched = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touched);
            if(touched.x > 250 && touched.x < 700 && touched.y > 315 && touched.y < 465) {
                myGame.setScreen(new MainGame(myGame));
            }

        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

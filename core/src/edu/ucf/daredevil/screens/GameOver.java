package edu.ucf.daredevil.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import edu.ucf.daredevil.GameController;
import edu.ucf.daredevil.objects.VariableStorer;

public class GameOver implements Screen {

    SpriteBatch batch;

    GameController myGame;
    //background
    Texture game;
    Sprite over;
    //play again button
    Texture button;
    Sprite playAgain;
    //score
    Texture butn;
    Sprite scoree;
    String gameScore;
    BitmapFont scoreBitmap;
    //coins collected
    Texture wow;
    Sprite coinn;
    String gameCoin;
    BitmapFont coinBitmap;
    //game over
    Texture title;
    Sprite gameOver;
    //camera
    OrthographicCamera camera;
    //hit box
    Rectangle hitbox;
    //music


    public GameOver(GameController g){
        myGame = g;
    }



    @Override
    public void show() {
        batch = new SpriteBatch();
        //background
        game = new Texture ("motorcycle.jpg");
        over = new Sprite (game);
        over.setPosition(0,0);
        over.setSize(1000, 800);
        //play again button
        button = new Texture ("playagain.png");
        playAgain = new Sprite(button);
        playAgain.setPosition(275, 150);
        playAgain.setSize(450, 125);
        //score
        butn = new Texture ("score.png");
        scoree = new Sprite(butn);
        scoree.setPosition(270, 450);
        scoree.setSize(300, 100);
        //coins collected
        wow = new Texture ("collected.png");
        coinn = new Sprite(wow);
        coinn.setPosition(270, 300);
        coinn.setSize(300, 100);
        //game over
        title = new Texture ("gameOver.png");
        gameOver = new Sprite(title);
        gameOver.setPosition(250, 600);
        gameOver.setSize(500, 150);
        //camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 700, 900);
        //hit box
        hitbox = new Rectangle(222, 160, 20, 100);
        //music
        //overMusic = Gdx.audio.newMusic(Gdx.files.internal("overMusic.mp3"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        over.draw(batch);
        playAgain.draw(batch);
        scoree.draw(batch);
        coinn.draw(batch);
        gameOver.draw(batch);
        batch.end();
        displayScore(VariableStorer.score, VariableStorer.coins);
        if(Gdx.input.justTouched() == true) {
            Vector3 touched = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touched);
            if(touched.x > 275 && touched.x < 725 && touched.y > 150 && touched.y < 275) {
                myGame.setScreen(new MainMenu(myGame));
                VariableStorer.score = 0;
                VariableStorer.music.stop();
            }

        }
    }

    public void displayScore(int score, int coins){

        gameScore = "" + score;
        gameCoin = "" + coins;

        scoreBitmap = new BitmapFont();
        scoreBitmap.getData().setScale(7);
        coinBitmap = new BitmapFont();
        coinBitmap.getData().setScale(7);


        //draw
        batch.begin();
        scoreBitmap.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        scoreBitmap.draw(batch, gameScore, 600, 525);
        coinBitmap.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        coinBitmap.draw(batch, gameCoin, 600, 375);
        batch.end();
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

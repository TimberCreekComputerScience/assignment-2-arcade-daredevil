package edu.ucf.daredevil.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

import edu.ucf.daredevil.GameController;
import edu.ucf.daredevil.objects.Biker;
import edu.ucf.daredevil.objects.Coin;
import edu.ucf.daredevil.objects.Fire;
import edu.ucf.daredevil.objects.MovingBackground;
import edu.ucf.daredevil.objects.VariableStorer;
import edu.ucf.daredevil.screens.GameOver;

public class MainGame implements Screen {

    SpriteBatch batch;
    //background
    MovingBackground bg;
    float push2 = 100;
    //biker
    public static Texture tBiker;
    Biker biker;
    Rectangle bike;
    //fire
    float lastFire = 0.0F;
    float firePerSecond = 0.5F;
    ArrayList<Fire> obstacles;
    //coins
    ArrayList<Coin> coins;
    String gameCoins;
    BitmapFont coinsBitmap;
    Sound coinSound;
    //camera
    OrthographicCamera camera;
    //game
    boolean lose;
    float time = 0;
    float increaseSpeed = 1;
    float push = 200;
    String gameScore;
    BitmapFont scoreBitmap;
    int lives = 3;


    GameController myGame;

    public MainGame(GameController g) {
        myGame = g;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        //camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 1200.0F, 800.0F);
        //background
        bg = new MovingBackground(push2);
        //biker
        tBiker = new Texture("biker.png");
        biker = new Biker(175,200,125,100);
        bike = new Rectangle();
        bike.x = 250;
        bike.y = 200;
        bike.width = 125;
        bike.height = 100;
        //fire
        this.obstacles = new ArrayList();
        //coins
        coins = new ArrayList<Coin>();
        coinSound = Gdx.audio.newSound(Gdx.files.internal("coinSound.ogg"));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw
        batch.begin();
        bg.draw(batch);

        time += Gdx.graphics.getDeltaTime();
        if(time >= 3){
            increaseSpeed *= 1.01;
            push *= increaseSpeed;
            time = 0;
        }

        //fire
        lastFire += Gdx.graphics.getDeltaTime();
        if(time >= 3){
            increaseSpeed *= 1.01;
            push *= increaseSpeed;
            time = 0;
        }
        if (lastFire >= 1.0F / firePerSecond) {
            lastFire -= 1.0F / firePerSecond;
            obstacles.add(new Fire(1000, (int)(Math.random() * 5), push));
            coins.add(new Coin(1000, (int)(Math.random() * 5), 125));
            if(increaseSpeed >= 1.13){
                obstacles.add(new Fire(1000, (int)(Math.random() * 5), push));
            }
            if(increaseSpeed>=1.18){
                obstacles.add(new Fire(1000, (int)(Math.random() * 5), push));
            }
            VariableStorer.score++;
        }
        for(Coin c : coins){
            c.draw(batch);
        }
        for(Fire f : obstacles){
            f.draw(batch);
        }

        biker.draw(batch);
        batch.end();

        //moving the bike
        if(lose == false) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                biker.moveUp();
                bike.y += 80;

            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                biker.moveDown();
                bike.y -= 80;
            }
        }




        //fire
        for(Fire f : obstacles){
            f.update(Gdx.graphics.getDeltaTime());
            if(f.getHitBox().overlaps(biker.getHitBox())){
                lives -= Gdx.graphics.getDeltaTime();
                System.out.println(lives);
            }
        }
        //coins
        int hitIndex = 0;
        for(int i = 0; i<coins.size(); i++){
            coins.get(i).update(Gdx.graphics.getDeltaTime());
            if(coins.get(i).getHitBox().overlaps(biker.getHitBox())){
                coinSound.play();
                VariableStorer.coins++;
                hitIndex = i;
                coins.remove(hitIndex);

            }
        }


        bg.update(Gdx.graphics.getDeltaTime());
        displayCurrScore(VariableStorer.score, VariableStorer.coins);

        if(lives == 0){
            lose = true;
        }

        if(lose == true){
            myGame.setScreen(new GameOver(myGame));
        }


    }

    public void displayCurrScore(int score, int coins){
        gameScore = "Score: " + score;
        gameCoins = "Coins Collected: " + coins;

        scoreBitmap = new BitmapFont();
        scoreBitmap.getData().setScale(2);
        coinsBitmap = new BitmapFont();
        coinsBitmap.getData().setScale(2);

        //draw
        batch.begin();
        scoreBitmap.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        scoreBitmap.draw(batch, gameScore, 825, 775);
        coinsBitmap.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        coinsBitmap.draw(batch, gameCoins, 750, 725);
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
    public void dispose(){
        batch.dispose();
    }
}

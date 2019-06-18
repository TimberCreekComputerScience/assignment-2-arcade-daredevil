package edu.ucf.daredevil.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import edu.ucf.daredevil.screens.MainGame;

public class Biker {

    //Position
    public float x;
    public float y;

    //Size
    float width;
    float height;

    //Sprite
    Sprite bike;

    // hit box
    private Rectangle hitBox;

    public Biker(float x, float y, float w, float h){
        // Setting the initial position and height and width of biker
        this.x = x;
        this.y = y;
        width = w;
        height = h;

        // Replicate settings for the sprite representations
        bike = new Sprite(MainGame.tBiker);
        bike.setPosition(x, y);
        bike.setSize(w, h);

        hitBox = new Rectangle(this.x, this.y, width, height - 40);
    }

    // Draw the sprite
    public void draw(SpriteBatch batch){
        bike.draw(batch);
    }

    // Update Position
    public void updatePos(float x, float y){
        this.x = x;
        this.y = y;

        bike.setPosition(x, y);
        hitBox.x = x;
        hitBox.y = y;
    }

    //Move up
    public void moveUp() {
        if(bike.getY() < 520) { //collision
            x = bike.getX();
            y = bike.getY() + 80;
            hitBox.x = x;
            hitBox.y = y;
            updatePos(x, y);
        }else{
            x = bike.getX();
            y = bike.getY();
            hitBox.x = x;
            hitBox.y = y;
            updatePos(x, y);
        }
    }

    //Move down
    public void moveDown() {
        if(bike.getY() > 200) { //collision
            x = bike.getX();
            y = bike.getY() - 80;
            hitBox.x = x;
            hitBox.y = y;
            updatePos(x, y);
        }else{
            x = bike.getX();
            y = bike.getY();
            hitBox.x = x;
            hitBox.y = y;
            updatePos(x, y);
        }
    }

    //set position
    public void setPosition(int x, int y){
        bike.setPosition(x, y);
    }

    //hitbox
    public Rectangle getHitBox(){
        return hitBox;
    }


}

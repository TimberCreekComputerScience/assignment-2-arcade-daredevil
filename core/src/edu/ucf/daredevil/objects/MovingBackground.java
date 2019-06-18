package edu.ucf.daredevil.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MovingBackground {

    public static Texture img;
    public static Sprite background;
    public static Texture img2;
    public static Sprite background2;
    Vector2 position;
    Vector2 position2;
    Vector2 velocity;
    Vector2 velocity2;

    public MovingBackground(float push) {
        img = new Texture("backgroundTrack.png");
        background = new Sprite (img);
        background.setPosition(0,0);

        img2 = new Texture("backgroundTrack.png");
        background2 = new Sprite (img2);
        background2.setPosition(1212,0);

        position = new Vector2(0, 0);
        position2 = new Vector2(1212, 0);
        velocity = new Vector2(push, 0.0F);
        velocity2 = new Vector2(push, 0.0F);
    }

    public void update(float deltaTime) {
        position.x -= this.velocity.x * deltaTime;
        position2.x -= this.velocity.x * deltaTime;
        background.setPosition(this.position.x, this.position.y);
        background2.setPosition(this.position2.x, this.position2.y);
        if(background.getX() < -1212){
            position.x = 1212;
            background.setPosition(1212, 0);
        }
        if(background2.getX() < -1212){
            position2.x = 1212;

            background2.setPosition(1212, 0);
        }
    }

    public static void draw(SpriteBatch b) {
        background.draw(b);
        background2.draw(b);
    }


}

package edu.ucf.daredevil.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Coin {

    private Sprite sprite;
    private Rectangle hitBox;
    public int y;
    public int x;
    private Vector2 position;
    private Vector2 velocity;
    private int height = 65;

    public Coin(int x, int y, float push) {
        int width = 65;
        sprite = new Sprite(new Texture("coin.png"));
        this.x = x;
        this.y = y;
        if (y == 0) {
            y = 193;
            sprite.setPosition(x, y);
        } else if (y == 1) {
            y = 268;
            sprite.setPosition(x, y);
        } else if (y == 2) {
            y = 349;
            sprite.setPosition(x, y);
        } else if (y == 3) {
            y = 437;
            sprite.setPosition(x, y);
        } else {
            y = 517;
            sprite.setPosition(x, y);
        }

        hitBox = new Rectangle((float)x, (float)y, (float)width, height);
        position = new Vector2((float)x, (float)y);
        velocity = new Vector2(push, 0.0F);
        sprite.setSize((float)width, height);
    }

    public void update(float deltaTime) {
       position.x -= velocity.x * deltaTime;
        sprite.setPosition(position.x, position.y);
        hitBox.x = position.x;
        hitBox.y = position.y;
    }

    public void draw(SpriteBatch b) {
        sprite.draw(b);
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}

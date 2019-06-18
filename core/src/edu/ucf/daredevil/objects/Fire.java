package edu.ucf.daredevil.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Fire {
    private Sprite sprite;
    private Rectangle hitBox;
    public int y;
    private int x;
    private Vector2 position;
    private Vector2 velocity;
    private float height = 75.0F;

    public Fire(int x, int y, float push) {
        int width = 200;
        this.sprite = new Sprite(new Texture("fire.png"));
        this.x = x;
        this.y = y;
        if (y == 0) {
            y = 193;
            this.sprite.setPosition((float)x, (float)y);
        } else if (y == 1) {
            y = 268;
            this.sprite.setPosition((float)x, (float)y);
        } else if (y == 2) {
            y = 349;
            this.sprite.setPosition((float)x, (float)y);
        } else if (y == 3) {
            y = 437;
            this.sprite.setPosition((float)x, (float)y);
        } else {
            y = 517;
            this.sprite.setPosition((float)x, (float)y);
        }

        this.hitBox = new Rectangle((float)x, (float)y, (float)width, this.height);
        this.position = new Vector2((float)x, (float)y);
        this.velocity = new Vector2(push, 0.0F);
        this.sprite.setSize((float)width, this.height);
    }

    public void update(float deltaTime) {
        Vector2 var10000 = this.position;
        var10000.x -= this.velocity.x * deltaTime;
        this.sprite.setPosition(this.position.x, this.position.y);
        this.hitBox.x = this.position.x;
        this.hitBox.y = this.position.y;
    }

    public void draw(SpriteBatch b) {
        this.sprite.draw(b);
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }
}

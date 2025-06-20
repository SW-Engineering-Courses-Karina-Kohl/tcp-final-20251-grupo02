package vfx;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import java.util.Random;

import com.raylib.Raylib.Texture;

import gui.*;

public class Particle{

    private Sprite sprite;
    private int x;
    private int y;
    private OurColor color;
    private float direction;
    private float rotation;
    private boolean rotate;
    private float rotationSpeed;
    private int scale;
    private float speed;
    private boolean gravity;
    private float gravitySpeed;
    private float speedX;
    private float speedY;

    private int life;

    public Particle(Texture image, int minX, int maxX, int minY, int maxY, float minDirecao, float maxDirecao, boolean rotate,
    float minSpeedRotation, float maxSpeedRotation, int minScale, int maxScale, float minSpeed, float maxSpeed,
    int minLife, int maxLife, boolean gravity, float gravitySpeed, OurColor color){

        this.rotate = rotate;
        this.gravity = gravity;
        this.gravitySpeed = gravitySpeed;

        x = getRandomRangeInt(minX, maxX);
        y = getRandomRangeInt(minY, maxY);

        direction = getRandomRangeFloat(minDirecao, maxDirecao);
        speed = getRandomRangeFloat(minSpeed, maxSpeed);
        scale = getRandomRangeInt(minScale, maxScale);
        rotationSpeed = getRandomRangeFloat(minSpeedRotation, maxSpeedRotation);

	// Getting the angle
        float angle = (float) (direction * Math.PI) / 180;
        speedX = speed * (float) Math.cos(angle);
        speedY = speed * (float) Math.sin(angle);

        this.color = color;
        sprite = new Sprite(image, scale, angle, 0, 0, color.getColor(), 1);

        life = getRandomRangeInt(minLife, maxLife);
    }

    public void updateParticle(){

        x += speedX;
        y += speedY;

        if(gravity){
            speedY += gravitySpeed;
        }

        if(rotate){
            sprite.IncrementAngle(rotationSpeed);
        }

        if (life > 0)
        {
            life --;
        }

        sprite.DrawSpritePro(x, y);
    }

    public int getRandomRangeInt(int min, int max){
        Random random = new Random();
        if (min == max)
            return min;
        return random.nextInt(max - min) + min;
    }

    public float getRandomRangeFloat(float min, float max){
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    public int getY(){
        return y;
    }

    public int getLife()
    {
        return life;
    }
}

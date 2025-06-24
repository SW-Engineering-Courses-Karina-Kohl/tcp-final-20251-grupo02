package vfx;

import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.OurColor;

public class BackgroundParticlesEmitter{

    private static Texture image = LoadTexture("res/vfx/particula3.png");
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private float direction;
    private int minScale;
    private int maxScale;
    private float minSpeed;
    private float maxSpeed;
    private int margin;

    private ArrayList <Particle> particlesList = new ArrayList <Particle>();

    public BackgroundParticlesEmitter(int screenWidth, int screenHeight, int margin){

        this.margin = margin;
        minX = margin;
        maxX = screenWidth - margin;
        minY = screenHeight + 10;
        maxY = screenHeight * 2;
        direction = 270;
        minSpeed = 1f;
        maxSpeed = 3f;
        minScale = 1;
        maxScale = 4;

        for (int i = 0; i < 10; i++){
            Particle particle = new Particle(image, minX, maxX, minY, maxY, direction, direction, false, 0, 0, minScale, maxScale
            , minSpeed, maxSpeed, 100, 100, false, 0,new OurColor(255, 255, 255, 255));
            particlesList.add(particle);
        }
    }

    public void sendParticle(){

        if (particlesList.size() > 0) {
            for (int i = 0; i < particlesList.size(); i++){

                particlesList.get(i).updateParticle();
                if (particlesList.get(i).getY() <  -10){
                    Particle particle = new Particle(image, minX, maxX, minY, maxY, direction, direction, false, 0, 0, minScale, maxScale,
						     minSpeed, maxSpeed, 2000, 2000, false, 0, new OurColor(255, 255, 255, 255));

                    particlesList.set(i, particle);
                }
            }
        }
    }

}

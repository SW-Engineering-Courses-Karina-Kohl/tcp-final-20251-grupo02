package vfx;

import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.OurColor;

public class BloodParticlesEmitter
{

    private static Texture image = LoadTexture("res/vfx/particula3.png");
    private float direction;
    private int minScale;
    private int maxScale;
    private float minSpeed;
    private float maxSpeed;
    private int x;
    private int y;

    private ArrayList <Particle> particlesList = new ArrayList <Particle>();

    public BloodParticlesEmitter()
    {

        minSpeed = 3f;
        maxSpeed = 6f;
        minScale = 1;
        maxScale = 4;

    }

    public void createParticles(int x, int y, int amount, OurColor color)
    {
        for (int i = 0; i < amount; i++)
        {
            Particle particle = new Particle(image, x, x, y, y, 0, 360, false, 0, 0, minScale, maxScale,
            minSpeed, maxSpeed, 20, 30, false, 0, color);
            particlesList.add(particle);
        }
    }

    public void updateParticles()
    {
        if (particlesList.size() > 0) {
            for (int i = 0; i < particlesList.size(); i++){

                particlesList.get(i).updateParticle();
                if (particlesList.get(i).getLife() <=  0)
                {
                    particlesList.remove(i);
                }
            }
        }
    }
}

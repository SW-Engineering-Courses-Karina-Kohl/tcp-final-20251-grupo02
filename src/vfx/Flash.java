package vfx;

import static com.raylib.Raylib.DrawRectangleRec;

import gui.OurColor;
import gui.OurRectangle;

public class Flash 
{
    private boolean activated;
    private OurColor color;
    private OurRectangle rectangle;
    private int speed;


    public Flash(OurColor color, int width, int height, int speed)
    {
        activated = false;
        this.color = color;
        color.setAlpha(255);
        rectangle = new OurRectangle(0, 0, width, height);
        this.speed = speed;
    }

    public void callFlash()
    {
        color.setAlpha(255);
        activated = true;
    }

    public boolean getActivated()
    {
        return activated;
    }

    public void updateFlash()
    {
        if (activated)
        {
            if (color.getAlpha() > 0 + speed)
            {
                color.setAlpha(color.getAlpha() - speed);
            }
            else
            {
                color.setAlpha(0);
                activated = false;
            }
            DrawRectangleRec(rectangle.GetOurRectangle(), color.getColor());
        }
    }
}

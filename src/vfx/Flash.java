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
        color.SetAlpha(255);
        rectangle = new OurRectangle(0, 0, width, height);
        this.speed = speed;
    }

    public void CallFlash()
    {
        color.SetAlpha(255);
        activated = true;
    }

    public boolean GetActivated()
    {
        return activated;
    }

    public void UpdateFlash()
    {
        if (activated)
        {
            if (color.GetAlpha() > 0 + speed)
            {
                color.SetAlpha(color.GetAlpha() - speed);
            }
            else
            {
                color.SetAlpha(0);
                activated = false;
            }
            DrawRectangleRec(rectangle.GetOurRectangle(), color.GetColor());
        }
    }
}

package vfx;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import gui.*;

public class Transition 
{
    private boolean activated;
    private boolean changed;
    private OurColor color;
    private OurRectangle rectangle;
    private int speed;
    private int type;

    private int pageIndex;
    private int pageNext;

    public Transition(OurColor color, int width, int height, int speed, int type)
    {
        activated = false;
        changed = false;
        this.color = color;
        color.setAlpha(0);
        rectangle = new OurRectangle(0, 0, width, height);
        this.speed = speed;
        this.type = type;
    }

    public void callTransition(int pageIndex, int pageNext)
    {
        this.pageIndex = pageIndex;
        this.pageNext = pageNext;
        activated = true;
        changed = false;
    }

    public boolean getActivated()
    {
        return activated;
    }

    public void updateTransition(boolean[] pages)
    {
        if (activated)
        {
            if (!changed)
            {
                
                if (color.getAlpha() < 255 - speed)
                {
                    color.setAlpha(color.getAlpha() + speed);
                }
                else
                {
                    color.setAlpha(255);

                    pages[pageIndex] = false;
                    pages[pageNext] = true;

                    changed = true;
                }
            }
            else
            {
                if (color.getAlpha() > 0 + speed)
                {
                    color.setAlpha(color.getAlpha() - speed);
                }
                else
                {
                    color.setAlpha(0);
                    changed = false;
                    activated = false;
                }
            }
            DrawRectangleRec(rectangle.GetOurRectangle(), color.getColor());
        }
    }
}

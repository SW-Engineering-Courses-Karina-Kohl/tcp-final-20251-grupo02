package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;


public class Button
{
    private int vermelho = 157;
    private int verde = 204;
    private int azul = 102;
    private int alpha = 255;
    //private Color COR_CIMA = new Color().r((byte) vermelho).g((byte) verde).b((byte) azul).a((byte) alpha);
    private OurColor COR_CIMA = new OurColor(157, 204, 102, 255);
    private Sprite sprite;
    private int x;
    private int y;
    private OurRectangle hitbox;

    public Button(int x, int y, Sprite sprite)
    {
        this.x = x;
        this.y = y;
        this.sprite = sprite;

        hitbox = new OurRectangle(this.x - this.sprite.GetWidth() * .5f, this.y - this.sprite.GetHeight() * .5f, this.sprite.GetWidth(), this.sprite.GetHeight());
        //hitbox = new Rectangle().x(this.x - this.sprite.GetWidth() * .5f).y(this.y - this.sprite.GetHeight() * .5f).width(this.sprite.GetWidth()).height(this.sprite.GetHeight());
    }
    

    public boolean MouseOn()
    {
        boolean isMouseOn = false;
        if (CheckCollisionPointRec(GetMousePosition(), hitbox.GetOurRectangle())) 
        {
            isMouseOn = true;
            sprite.SetOurColor(COR_CIMA.GetOurColor());
        }
        else
        {
            isMouseOn = false;
            sprite.SetOurColor(WHITE);
        }

        return isMouseOn;
    }

    public boolean MouseClick()
    {
        boolean click = false;
        if (MouseOn())
        {
            if (IsMouseButtonReleased(0))
            {
                click = true;
            }
        }
        else
        {
            click = false;
        }

        sprite.DrawSpritePro(x, y);
        return click;
    }
}

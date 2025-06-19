package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;


public class Button{
    //                                          R    G    B    A
    private OurColor hoverColor = new OurColor(157, 204, 102, 255);
    private Sprite sprite;
    private int x;
    private int y;
    private OurRectangle hitbox;

    public Button(int x, int y, Sprite sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;

        hitbox = new OurRectangle(this.x - this.sprite.GetWidth() * .5f, this.y - this.sprite.GetHeight() * .5f, this.sprite.GetWidth(), this.sprite.GetHeight());
    }

    public boolean MouseOn(){

        boolean isMouseOn = false;
        if (CheckCollisionPointRec(GetMousePosition(), hitbox.GetOurRectangle())){
            isMouseOn = true;
            sprite.SetColor(hoverColor.GetColor());
        } else {
            isMouseOn = false;
            sprite.SetColor(WHITE);
        }
        return isMouseOn;
    }

    public boolean MouseClick(){

        boolean click = false;
        if (MouseOn()){
            if (IsMouseButtonReleased(0)){
                click = true;
            }
        } else {
            click = false;
        }

        sprite.DrawSpritePro(x, y);
        return click;
    }
}

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

    public boolean mouseOn(Camera2D camera2d){

        boolean isMouseOn = false;
        if (CheckCollisionPointRec(GetScreenToWorld2D(GetMousePosition(), camera2d), hitbox.GetOurRectangle())){
            isMouseOn = true;
            sprite.SetColor(hoverColor.getColor());
        } else {
            isMouseOn = false;
            sprite.SetColor(WHITE);
        }
        return isMouseOn;
    }

    public boolean mouseClick(Camera2D camera2d){

        boolean click = false;
        if (mouseOn(camera2d)){
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

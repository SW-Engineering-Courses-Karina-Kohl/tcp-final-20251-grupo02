package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import com.raylib.Raylib.Color;

public class OurColor{

    private Color color;
    int r;
    int g;
    int b;
    int a;

    public OurColor(int r, int g, int b, int a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        color = new Color().r((byte) this.r).g((byte) this.g).b((byte) this.b).a((byte) this.a);
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setColor(int r, int g, int b, int a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        color = new Color().r((byte) this.r).g((byte) this.g).b((byte) this.b).a((byte) this.a);
    }

    public void setAlpha(int a)
    {
        this.a = a;
        color = new Color().r((byte) this.r).g((byte) this.g).b((byte) this.b).a((byte) this.a);
    }

    public int getAlpha()
    {
        return a;
    }
}

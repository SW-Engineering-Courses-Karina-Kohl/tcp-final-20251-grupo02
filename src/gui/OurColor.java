package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class OurColor
{   
    private Color cor;
    int r;
    int g;
    int b;
    int a;
    public OurColor(int r, int g, int b, int a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        cor = new Color().r((byte) this.r).g((byte) this.g).b((byte) this.b).a((byte) this.a);
    }

    public Color GetColor()
    {
        return cor;
    }

    public void SetOurColor(Color cor)
    {
        this.cor = cor;
    }

    public void SetOurColor(int r, int g, int b, int a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        cor = new Color().r((byte) this.r).g((byte) this.g).b((byte) this.b).a((byte) this.a);
    }
}

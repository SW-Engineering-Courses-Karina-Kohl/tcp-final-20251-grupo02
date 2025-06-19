package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

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

    public Color GetColor(){
        return color;
    }

    public void SetColor(Color color){
        this.color = color;
    }

    public void SetColor(int r, int g, int b, int a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        color = new Color().r((byte) this.r).g((byte) this.g).b((byte) this.b).a((byte) this.a);
    }

    public void SetAlpha(int a)
    {
        this.a = a;
        color = new Color().r((byte) this.r).g((byte) this.g).b((byte) this.b).a((byte) this.a);
    }

    public int GetAlpha()
    {
        return a;
    }
}

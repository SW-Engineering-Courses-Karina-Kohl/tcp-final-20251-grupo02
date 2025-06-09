package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class Cor
{   
    private Color cor;
    int r;
    int g;
    int b;
    int a;
    public Cor(int r, int g, int b, int a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        cor = new Color().r((byte) this.r).g((byte) this.g).b((byte) this.b).a((byte) this.a);
    }

    public Color GetCor()
    {
        return cor;
    }

    public void SetCor(Color cor)
    {
        this.cor = cor;
    }

    public void SetCor(int r, int g, int b, int a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        cor = new Color().r((byte) this.r).g((byte) this.g).b((byte) this.b).a((byte) this.a);
    }
}

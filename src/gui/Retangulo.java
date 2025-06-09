package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;



public class Retangulo 
{
    private Rectangle retangulo;
    private float x;
    private float y;
    private float width;
    private float height;

    public Retangulo(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        retangulo = new Rectangle().x(this.x).y(this.y).width(this.width).height(this.height);
    }
    
    public float GetX()
    {
        return x;
    }

    public float GetY()
    {
        return y;
    }

    public float GetWidth()
    {
        return width;
    }

    public float GetHeight()
    {
        return height;
    }

    public void SetX(float newX)
    {
        x = newX;
        retangulo.x(newX);
    }

    public void SetY(float newY)
    {
        y = newY;
        retangulo.y(newY);
    }

    public void SetWidth(float newWidth)
    {
        width = newWidth;
        retangulo.width(newWidth);
    }

    public void SetHeight(float newHeight)
    {
        height = newHeight;
        retangulo.height(newHeight);
    }

    public Rectangle GetRetangulo()
    {
        return retangulo;
    }
}

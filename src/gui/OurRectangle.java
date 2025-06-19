package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class OurRectangle{

    private Rectangle rectangle;
    private float x;
    private float y;
    private float width;
    private float height;

    public OurRectangle(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rectangle = new Rectangle().x(this.x).y(this.y).width(this.width).height(this.height);
    }

    public float GetX(){
        return x;
    }

    public float GetY(){
        return y;
    }

    public float GetWidth(){
        return width;
    }

    public float GetHeight(){
        return height;
    }

    public void SetX(float newX){
        x = newX;
        rectangle.x(newX);
    }

    public void SetY(float newY){
        y = newY;
        rectangle.y(newY);
    }

    public void SetWidth(float newWidth){
        width = newWidth;
        rectangle.width(newWidth);
    }

    public void SetHeight(float newHeight){
        height = newHeight;
        rectangle.height(newHeight);
    }

    public Rectangle GetOurRectangle(){
        return rectangle;
    }
}

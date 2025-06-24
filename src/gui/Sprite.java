package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import com.raylib.Raylib.Color;
import com.raylib.Raylib.Rectangle;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

public class Sprite{

    private Texture texture;
    private float scale, angle;
    private OurRectangle cut;
    private int imageSpeed;
    private int currentImage;
    private int counter = 0;
    private Color color;
    private int imageAmount;

    public Sprite(Texture texture, float scale, float angle, int imageSpeed, int currentImage, Color color, int imageAmount){

        this.texture = texture;
        this.scale = scale;
        this.angle = angle;
        this.imageSpeed = imageSpeed;
        this.currentImage = currentImage;
        this.color = color;

        cut = new OurRectangle(0, 0, this.texture.width() / imageAmount, this.texture.height());
    }


    public void DrawSpritePro(float x, float y){

        // Running the timer
        if (imageSpeed != 0){

            counter++;

            if (counter >= (60 / imageSpeed)){
                // Changing the image
                currentImage = (currentImage + 1) % (int)(texture.width() / Math.abs(cut.GetWidth()));
                cut.SetX((float)currentImage * (texture.width() / (texture.width() / Math.abs(cut.GetWidth()))));
                counter = 0;
            }
        } else {
            cut.SetX((float) currentImage * (texture.width() / (texture.width() / Math.abs(cut.GetWidth()))));
        }

        Rectangle rec_sprite = new Rectangle() .x(x) .y(y) .width(GetWidth()) .height(GetHeight());

        Vector2 pivot = new Vector2()
	    .x((texture.width() / (texture.width() / Math.abs(cut.GetWidth())) / 2) * scale)
	    .y(texture.height() * scale / 2);

        DrawTexturePro(texture, cut.GetOurRectangle(), rec_sprite, pivot, angle, color);
    }

    public float GetWidth(){
        return (texture.width() / (texture.width() / Math.abs(cut.GetWidth()))) * scale;
    }

    public float GetHeight(){
        return (float) texture.height() * scale;
    }

    public float GetScale(){
        return scale;
    }

    public void SetScale(float newScale){
        scale = newScale;
    }

    // Calculate linear interpolation between two floats
    public float Lerp(float start, float end, float amount){
        return start + amount*(end - start);
    }

    public void SetCurrentImage(int newCurrentImage){
        currentImage = newCurrentImage;
    }

    public void SetColor(Color newColor){
        color = newColor;
    }

    public void SetAngle(float newAngle){
        angle = newAngle;
    }

    public void IncrementAngle(float amount){
        angle += amount;
    }
}

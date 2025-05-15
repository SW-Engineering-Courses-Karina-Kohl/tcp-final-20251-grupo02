package app;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import jogo.*;

public class Main 
{
    public static void main(String args[]) 
    {
        InitWindow(800, 450, "Main");

        SetTargetFPS(60);


        while (!WindowShouldClose()) 
        {
            BeginDrawing();

                ClearBackground(RAYWHITE);
                DrawText("Congrats! You created your first window!", 190, 200, 20, LIGHTGRAY);
                DrawFPS(20, 20);

            EndDrawing();
        }
        CloseWindow();
    }
}
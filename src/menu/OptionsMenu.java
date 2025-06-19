package menu;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.*;

public class OptionsMenu{

    // upper text
    private static Texture optionsTexture = LoadTexture("res/ui/opcoes.png");
    private Sprite optionsSprite = new Sprite(optionsTexture, 1, 0, 0, 0, WHITE, 1);

    // time text
    private static Texture timeTexture = LoadTexture("res/ui/tempo.png");
    private Sprite timeSprite = new Sprite(timeTexture, 1, 0, 0, 0, WHITE, 1);

    // go back button
    private static Texture goBackTexture = LoadTexture("res/botoes/sair.png");
    private Sprite goBackSprite = new Sprite(goBackTexture, 1, 0, 0, 0, WHITE, 1);
    private Button goBackButton;

    // change time button
    private static Texture upArrowTexture = LoadTexture("res/botoes/seta_cima.png");
    private Sprite upArrowSprite = new Sprite(upArrowTexture, 1, 0, 0, 0, WHITE, 1);
    private Button[] upArrowButton = new Button[4];

    private Sprite downArrowSprite = new Sprite(upArrowTexture, 1, 180, 0, 0, WHITE, 1);
    private Button[] downArrowButton = new Button[4];

    private int screenCenter;

    private int time = 0;
    private int[] separetedTime = new int[4];
    private int[] numbers = new int[10];
    private int[] indexNumbers = new int[4];

    private Font font;

    public OptionsMenu(int screenWidth, Font font){

        screenCenter = screenWidth / 2;
        this.font = font;

        goBackButton = new Button(screenCenter, 320, goBackSprite);
        for (int i = 0; i < 2; i++){
            upArrowButton[i] = new Button(341 + (int) upArrowSprite.GetWidth() / 2 + i * 19,  82 + (int) upArrowSprite.GetHeight() / 2, upArrowSprite);
            downArrowButton[i] = new Button(341 + (int) downArrowSprite.GetWidth() / 2 + i * 19, 121, downArrowSprite);
        }

        for (int i = 2; i < 4; i++){
            upArrowButton[i] = new Button(388 + (int) upArrowSprite.GetWidth() / 2 + (i - 2) * 19,  82 + (int) upArrowSprite.GetHeight() / 2, upArrowSprite);
            downArrowButton[i] = new Button(388 + (int) downArrowSprite.GetWidth() / 2 + (i - 2) * 19, 121, downArrowSprite);
        }

        for (int i = 0; i < 10; i++){
            numbers[i] = i;
        }

        this.InitialTime();
    }

    // Do the menu logic and draws it
    public void OptionsMenuLogic(boolean[] pages){
        if (pages[1] == true){
            optionsSprite.DrawSpritePro(screenCenter, 32);

            timeSprite.DrawSpritePro(screenCenter - timeSprite.GetWidth() / 2, 104);

            // Going back to main menu
            if (goBackButton.MouseClick()){
                pages[1] = false;
                pages[0] = true;
            }

            UpdatingTime();
        }
    }

    private void UpdatingTime(){
        LoopIndex(0, 6, 0);
        LoopIndex(0, 9, 1);
        LoopIndex(0, 5, 2);
        LoopIndex(0, 9, 3);

        // Max time is one hour
        if (indexNumbers[0] == 6){
            indexNumbers[0] = 6;
            indexNumbers[1] = 0;
            indexNumbers[2] = 0;
            indexNumbers[3] = 0;
        }

        for (int i = 0; i < 4; i++){
            separetedTime[i] = numbers[indexNumbers[i]];
        }

        DrawTime();
    }

    public int BoolToInt(boolean bool){
        if (bool)
            return 1;
        else
            return 0;
    }

    public void LoopIndex(int min, int max, int i){
        indexNumbers[i] += BoolToInt(upArrowButton[i].MouseClick());
        indexNumbers[i] -= BoolToInt(downArrowButton[i].MouseClick());

        if (indexNumbers[i] > max){
            indexNumbers[i] = min;
        }

        if (indexNumbers[i] < min){
            indexNumbers[i] = max;
        }
    }

    public void DrawTime(){
        DrawTextEx(font, String.format("%d", separetedTime[0]),
        new Vector2().x(screenCenter + 18).y(90), 32, 1, WHITE);

        DrawTextEx(font, String.format("%d", separetedTime[1]),
        new Vector2().x(screenCenter + 18 + 19).y(90), 32, 1, WHITE);

        DrawTextEx(font, ":",
        new Vector2().x(screenCenter + 18 + 19 * 2).y(90), 32, 1, WHITE);

        DrawTextEx(font, String.format("%d", separetedTime[2]),
        new Vector2().x(screenCenter + 18 + 19 * 2 + 9).y(90), 32, 1, WHITE);

        DrawTextEx(font, String.format("%d", separetedTime[3]),
        new Vector2().x(screenCenter + 18 + 19 * 3 + 9).y(90), 32, 1, WHITE);
    }

    public int ConvertToSeconds(){
        // minutes
        time = (separetedTime[0] * 10 * 60)
        + (separetedTime[1] * 60)
        + (separetedTime[2] * 10)
        + (separetedTime[3]);

        return time;
    }

    public void InitialTime(){
        // Put a initial time in the screen
        indexNumbers[0] = 0;
        indexNumbers[1] = 5;
        indexNumbers[2] = 0;
        indexNumbers[3] = 0;

        for (int i = 0; i < 4; i++)
        {
            separetedTime[i] = numbers[indexNumbers[i]];
        }
    }
}

package gui;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.DrawRectangle;
import static com.raylib.Raylib.LoadTexture;

import com.raylib.Raylib.Camera2D;
import com.raylib.Raylib.Texture;

public class ButtonRaise 
{
    //Promocao do peao
    private static Texture bishopTextureP = LoadTexture("res/pieces/bishop.png");
    private static Texture queenTextureP = LoadTexture("res/pieces/queen.png");
    private static Texture towerTextureP = LoadTexture("res/pieces/rook.png");
    private static Texture knightTextureP = LoadTexture("res/pieces/knight.png");

    private static Texture raiseTexture = LoadTexture("res/botoes/button_raise.png");
    private static Sprite raiseSprite = new Sprite(raiseTexture, 1, 0, 0, 0, WHITE, 1);

    private Sprite[] piecesSprites = new Sprite[4];
    private int offset = 50;
    private int xBegging = 640 / 2 - 190 / 2;
    private int yBegging = 360 / 2 - 40 / 2;

    char[] whitePieces = {'Q', 'H', 'R', 'B'};
    char[] blackPieces = {'q', 'h', 'r', 'b'};
    private int scale;

    public ButtonRaise(int scale)
    {
        this.scale = scale;
        piecesSprites[0] = new Sprite(queenTextureP, scale, 0, 0, 0, WHITE, 2);
        piecesSprites[1] = new Sprite(knightTextureP, scale, 0, 0, 0, WHITE, 2);
        piecesSprites[2] = new Sprite(towerTextureP, scale, 0, 0, 0, WHITE, 2);
        piecesSprites[3] = new Sprite(bishopTextureP, scale, 0, 0, 0, WHITE, 2);
    }

    public char botaoPromocaoLogica(char cor, Camera2D camera2d)
    {
        char novo = '-';
        DrawRectangle(xBegging - (int) raiseSprite.GetWidth() / 2 - 2,  yBegging - (int)raiseSprite.GetHeight() / 2 - 2, 3 * offset + (int) raiseSprite.GetWidth() + 4, (int) raiseSprite.GetHeight() + 4, new OurColor(102, 139, 204, 255).getColor());
        for(int i = 0; i < 4; i++)
        {
            if (cor == 'b')
                piecesSprites[i].SetCurrentImage(1);
            Button buttonRaise = new Button(xBegging + i * offset,yBegging, raiseSprite);
            piecesSprites[i].DrawSpritePro(xBegging + i * offset , yBegging);

            if (buttonRaise.mouseClick(camera2d))
            {
                if (cor == 'w')
                    novo = whitePieces[i];
                else if (cor == 'b')
                    novo = blackPieces[i];
            }
        }

        return novo;
    }
}

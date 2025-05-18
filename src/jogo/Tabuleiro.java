package jogo;
import jogo.peca.*;

public class Tabuleiro {

    public int PecasNoTabuleiro;

    private static final int SIZE = 3;

    Peca bispo = new Bispo(0,0);
    Peca cavalo = new Cavalo(0,0);
    Peca dama = new Dama(0,0);
    Peca peao = new Peao(8,8);
    Peca rei = new Rei(0,0);
    Peca torre = new Torre(0,0);

    private final Peca[][] tabuleiro = 
    {
        {bispo, cavalo, dama},
        {dama, peao, rei},
        {torre, bispo, cavalo},
    };

    // da pra otimizar isso mas meu qi nao é suficiente
    public void GirarTabuleiro(){
        // inverte as linhas
        
        for(int i = 1; i < ((SIZE-1)/2)+1; i++){
            Peca[] temp = tabuleiro[i-1];
            tabuleiro[i-1] = tabuleiro[SIZE-i];
            tabuleiro[SIZE - i] = temp;
        }
    
        // inverte as colunas
        for(int i = 1 ; i < SIZE+1; i++){            
            for(int j = 1; j < ((SIZE-1)/2) + 1; j++){
                Peca temp2 = tabuleiro[i-1][j-1];
                tabuleiro[i-1][j-1] = tabuleiro[i-1][SIZE-j];
                tabuleiro[i-1][SIZE-j] = temp2;
            }
        } 
        /*
        for (int i = 0; i < SIZE-1; i++) {
            for (int j = 0; j < SIZE-1; j++) {
                // espelha verticalmente e horizontalmente
                invertida[linhas - 1 - i][colunas - 1 - j] = matriz[i][j];
            }
        }*/
    
    };

    // checa se existe peça na posicao
    boolean ChecarPosicao(int x, int y){
        if (tabuleiro[x][y] != null)
            return true;
        return false;
    };

    void MudancaNoTabuleiro(/*Jogada j*/){

    };

    public void print_tabuleiro(){
       
        for(int i = 0; i < SIZE; i ++){
            for(int j = 0; j < SIZE; j++)
            {
                System.out.printf("%s ", tabuleiro[i][j].identificador);
            }
            System.out.printf("\n");
        }
    }
    
    


    public static void main(String[] args){
         
        //Tabuleiro tab = new Tabuleiro();
        //tab.GirarTabuleiro();
        //tab.print_tabuleiro();
        //Peca peao2 = new Peao(7,7);
        //peao2.MovimentosValidos();
        //peao2.print_movimentos_validos();

        Peca rei2 = new Rei(5,5);
        rei2.MovimentosValidos();
        rei2.print_movimentos_validos();

    }

    

}
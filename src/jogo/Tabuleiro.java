package jogo;
import jogo.peca.*;

public class Tabuleiro {

    public int PecasNoTabuleiro;

    private static final int SIZE = 3;

    Peca bispo = new Bispo();
    Peca cavalo = new Cavalo();
    Peca dama = new Dama();
    Peca peao = new Peao();
    Peca rei = new Rei();
    Peca torre = new Torre();

    private Peca[][] tabuleiro = 
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
    };

    void ChecarPosicao(){
        // nao entendi oq esse metodo faz?

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
         
        Tabuleiro tab = new Tabuleiro();
        tab.GirarTabuleiro();
        tab.print_tabuleiro();

    }

    

}
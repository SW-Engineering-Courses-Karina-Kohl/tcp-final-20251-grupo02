package jogo;
import jogo.peca.*;
import jogo.peca.Peca.Pair;

public class Tabuleiro {
    
    private int PecasNoTabuleiro = 32;
    private static final int SIZE = 8;
    
    // tabuleiro[y][x] = Peca(x, y)
    private final Peca[][] tabuleiro = new Peca[SIZE][SIZE];

    // cria o tabuleiro da visão das brancas
    public Tabuleiro(){
        //  pecas brancas (id maiúsculo)
        this.tabuleiro[6][0] = new Peao(0, 6, "P");
        this.tabuleiro[6][1] = new Peao(1, 6, "P");              
        this.tabuleiro[6][2] = new Peao(2, 6, "P");
        this.tabuleiro[6][3] = new Peao(3, 6, "P");
        this.tabuleiro[6][4] = new Peao(4, 6, "P");
        this.tabuleiro[6][5] = new Peao(5, 6, "P");
        this.tabuleiro[6][6] = new Peao(6, 6, "P");
        this.tabuleiro[6][7] = new Peao(7, 6, "P");

        this.tabuleiro[7][0] = new Torre(0, 7, "T");
        this.tabuleiro[7][7] = new Torre(7, 7, "T");

        this.tabuleiro[7][1] = new Cavalo(1, 7, "C");
        this.tabuleiro[7][6] = new Cavalo(6, 7, "C");

        this.tabuleiro[7][2] = new Bispo(2, 7, "B");
        this.tabuleiro[7][5] = new Bispo(5, 7, "B");

        this.tabuleiro[7][4] = new Rei(4,7,"R"); 
        this.tabuleiro[7][3] = new Dama(3,7, "D"); 

        // pecas pretas (id minúsculo)
        this.tabuleiro[1][0] = new Peao(0, 1, "p");
        this.tabuleiro[1][1] = new Peao(1, 1, "p");
        this.tabuleiro[1][2] = new Peao(2, 1, "p");
        this.tabuleiro[1][3] = new Peao(3, 1, "p");
        this.tabuleiro[1][4] = new Peao(4, 1, "p");
        this.tabuleiro[1][5] = new Peao(5, 1, "p");
        this.tabuleiro[1][6] = new Peao(6, 1, "p");
        this.tabuleiro[1][7] = new Peao(7, 1, "p");
        
        this.tabuleiro[0][0] = new Torre(0, 0, "t");
        this.tabuleiro[0][7] = new Torre(7, 0, "t");
        
        this.tabuleiro[0][1] = new Cavalo(1, 0, "c");
        this.tabuleiro[0][6] = new Cavalo(6, 0, "c");
        
        this.tabuleiro[0][2] = new Bispo(2, 0, "b");
        this.tabuleiro[0][5] = new Bispo(5, 0, "b");

        this.tabuleiro[0][4] = new Rei(4, 0, "r");
        this.tabuleiro[0][3] = new Dama(3, 0, "d");
    }
    
    // checa qual peça está na posicao (x,y)
    Peca GetPecaNaPosicao(int x, int y){
        return this.tabuleiro[y][x];
    };

    boolean IsTherePecaNaPosicao(int x, int y){   
        if (this.tabuleiro[y][x] != null) 
            return true;
        return false;
    };

    // muda o tabuleiro de acordo com a jogada
    public void MudancaNoTabuleiro(Jogada jogada){
        
        Peca.Pair pecaMovida = jogada.peca_movida.grid_position;
        Peca.Pair posicaoFinal = jogada.peca_capturada.grid_position;
        //Peca.Pair pecaMovida_movimentada = jogada.peca_movida.grid_position.add(jogada.movimento);
        
        // move peça e anula posição anterior
        this.tabuleiro[posicaoFinal.y][posicaoFinal.x] = jogada.peca_movida;
        this.tabuleiro[pecaMovida.y][pecaMovida.x] = null;
        // jogada.peca_movida.DestruirPeca;
        
        // (capturar uma peça é simplesmente sobrescrever a posicao da peca capturada com a peca movida),
        // nao é necessario Jogada saber qual é a peca capturada.
        //boolean existe_peca_capturada = (jogada.posicao_final != jogada.peca_movida.grid_position);

        // anula peça capturada
        //if (existe_peca_capturada)
        
        //this.tabuleiro[posicaoFinal.y][posicaoFinal.x] = null;
    };

    public void GirarTabuleiro(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int i_espelhado = SIZE - 1 - i;
                int j_espelhado = SIZE - 1 - j;
                
                if (i < i_espelhado || (i == i_espelhado && j < j_espelhado)) {
                    // espelha verticalmente e horizontalmente
                    Peca temp = this.tabuleiro[i][j];
                    this.tabuleiro[i][j] = this.tabuleiro[i_espelhado][j_espelhado];
                    this.tabuleiro[i_espelhado][j_espelhado] = temp; 
                }
            }
        }
    };
    
    /*
    public void print_tabuleiro(){
       for(int i = 0; i < SIZE; i ++){
            for(int j = 0; j < SIZE; j++)
            {
                if (this.tabuleiro[i][j] == null)
                    System.out.printf("_ ", tabuleiro[i][j]);    
                else
                    System.out.printf("%s ", tabuleiro[i][j].identificador);
            }
            System.out.printf("\n");
        }
    }; */
    
    @Override
    public String toString(){
        
        String string = "\n";
        for(int i = 0; i < SIZE; i ++){
            for(int j = 0; j < SIZE; j++)
            {
                if (this.tabuleiro[i][j] == null) string = string.concat("_ ");    
                else string = string.concat(tabuleiro[i][j].identificador + " ");
            }
            string = string.concat("\n");
        }
    
        return string;
    }


    public static void main(String[] args) {
        Tabuleiro tab = new Tabuleiro();
        
        Jogada j = new Jogada(tab.GetPecaNaPosicao(4, 7), tab.GetPecaNaPosicao(7, 7));
        //j.ValidarJogada(tab);        
        j.ValidarJogada(tab);
    
    }

}
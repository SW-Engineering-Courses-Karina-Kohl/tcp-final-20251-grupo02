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
        // pecas brancas (id maiúsculo)
        Peca peao_branco1 = new Peao(0, 6, "P");
        Peca peao_branco2 = new Peao(1, 6, "P");       
        Peca peao_branco3 = new Peao(2, 6, "P");
        Peca peao_branco4 = new Peao(3, 6, "P");
        Peca peao_branco5 = new Peao(4, 6, "P");
        Peca peao_branco6 = new Peao(5, 6, "P");
        Peca peao_branco7 = new Peao(6, 6, "P");
        Peca peao_branco8 = new Peao(7, 6, "P");

        Peca torre_branca1 = new Torre(0, 7, "T");
        Peca torre_branca2 = new Torre(7, 7, "T");

        Peca cavalo_branco1 = new Cavalo(1, 7, "C");
        Peca cavalo_branco2 = new Cavalo(6, 7, "C");

        Peca bispo_branco1 = new Bispo(2, 7, "B");
        Peca bispo_branco2 = new Bispo(5, 7, "B");

        Peca rei_branco = new Rei(3,7,"R");
        Peca dama_branca = new Dama(4,7, "D");

        // pecas pretas (id minúsculo)
        Peca peao_preto1 = new Peao(0, 1, "p");
        Peca peao_preto2 = new Peao(1, 1, "p");
        Peca peao_preto3 = new Peao(2, 1, "p");
        Peca peao_preto4 = new Peao(3, 1, "p");
        Peca peao_preto5 = new Peao(4, 1, "p");
        Peca peao_preto6 = new Peao(5, 1, "p");
        Peca peao_preto7 = new Peao(6, 1, "p");
        Peca peao_preto8 = new Peao(7, 1, "p");

        Peca torre_preta1 = new Torre(0, 0, "t");
        Peca torre_preta2 = new Torre(7, 0, "t");

        Peca cavalo_preto1 = new Cavalo(1, 0, "c");
        Peca cavalo_preto2 = new Cavalo(6, 0, "c");

        Peca bispo_preto1 = new Bispo(2, 0, "b");
        Peca bispo_preto2 = new Bispo(5, 0, "b");

        Peca rei_preto = new Rei(3, 0, "r");
        Peca dama_preto = new Dama(4, 0, "d");

        // atribuindo peças brancas ao tabuleiro
        this.tabuleiro[6][0] = peao_branco1;
        this.tabuleiro[6][1] = peao_branco2;       
        this.tabuleiro[6][2] = peao_branco3;
        this.tabuleiro[6][3] = peao_branco4;
        this.tabuleiro[6][4] = peao_branco5;
        this.tabuleiro[6][5] = peao_branco6;
        this.tabuleiro[6][6] = peao_branco7;
        this.tabuleiro[6][7] = peao_branco8;

        this.tabuleiro[7][0] = torre_branca1;
        this.tabuleiro[7][7] = torre_branca2;

        this.tabuleiro[7][1] = cavalo_branco1;
        this.tabuleiro[7][6] = cavalo_branco2;

        this.tabuleiro[7][2] = bispo_branco1;
        this.tabuleiro[7][5] = bispo_branco2;

        this.tabuleiro[7][3] = rei_branco;
        this.tabuleiro[7][4] = dama_branca;

        // atribuindo peças pretas ao tabuleiro
        this.tabuleiro[1][0] = peao_preto1;
        this.tabuleiro[1][1] = peao_preto2;
        this.tabuleiro[1][2] = peao_preto3;
        this.tabuleiro[1][3] = peao_preto4;
        this.tabuleiro[1][4] = peao_preto5;
        this.tabuleiro[1][5] = peao_preto6;
        this.tabuleiro[1][6] = peao_preto7;
        this.tabuleiro[1][7] = peao_preto8;

        this.tabuleiro[0][0] = torre_preta1;
        this.tabuleiro[0][7] = torre_preta2;

        this.tabuleiro[0][1] = cavalo_preto1;
        this.tabuleiro[0][6] = cavalo_preto2;

        this.tabuleiro[0][2] = bispo_preto1;
        this.tabuleiro[0][5] = bispo_preto2;

        this.tabuleiro[0][3] = rei_preto;
        this.tabuleiro[0][4] = dama_preto;
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
        Peca.Pair posicaoFinal = jogada.posicao_final;
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
                if (this.tabuleiro[i][j] == null)
                    string = string.concat("_ ");    
                else
                    string = string.concat(tabuleiro[i][j].identificador + " ");
            }
            string = string.concat("\n");
        }
    
        return string;
    }


    public static void main(String[] args) {
        Tabuleiro tab = new Tabuleiro();
        Jogada j = new Jogada(tab.GetPecaNaPosicao(0, 6), new Pair(1, 6));
        j.ValidarJogada(tab);


        //Peca piece = tab.GetPecaNaPosicao(3, 0);
        
        //piece.MovimentosValidos();
        //System.out.print(piece);
        //piece.print_movimentos_validos();   
        
    }

}
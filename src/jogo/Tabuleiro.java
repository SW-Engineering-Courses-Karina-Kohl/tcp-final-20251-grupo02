package jogo;
import jogo.peca.*;

public class Tabuleiro {
    private int PecasNoTabuleiro = 32;
    private static final int SIZE = 8;
    
    // tabuleiro[y][x] = Peca(x, y)
    private final Peca[][] tabuleiro = new Peca[SIZE][SIZE];

    // cria o tabuleiro da visão das brancas
    public Tabuleiro(){
        // pecas brancas
        Peca peao_branco1 = new Peao(0, 6);
        Peca peao_branco2 = new Peao(1, 6);       
        Peca peao_branco3 = new Peao(2, 6);
        Peca peao_branco4 = new Peao(3, 6);
        Peca peao_branco5 = new Peao(4, 6);
        Peca peao_branco6 = new Peao(5, 6);
        Peca peao_branco7 = new Peao(6, 6);
        Peca peao_branco8 = new Peao(7, 6);

        Peca torre_branca1 = new Torre(0, 7);
        Peca torre_branca2 = new Torre(7, 7);

        Peca cavalo_branco1 = new Cavalo(1, 7);
        Peca cavalo_branco2 = new Cavalo(6, 7);

        Peca bispo_branco1 = new Bispo(2, 7);
        Peca bispo_branco2 = new Bispo(5, 7);

        Peca rei_branco = new Rei(3,7);
        Peca dama_branca = new Dama(4,7);

        // pecas pretas
        Peca peao_preto1 = new Peao(0, 1);
        Peca peao_preto2 = new Peao(1, 1);
        Peca peao_preto3 = new Peao(2, 1);
        Peca peao_preto4 = new Peao(3, 1);
        Peca peao_preto5 = new Peao(4, 1);
        Peca peao_preto6 = new Peao(5, 1);
        Peca peao_preto7 = new Peao(6, 1);
        Peca peao_preto8 = new Peao(7, 1);

        Peca torre_preta1 = new Torre(0, 0);
        Peca torre_preta2 = new Torre(7, 0);

        Peca cavalo_preto1 = new Cavalo(1, 0);
        Peca cavalo_preto2 = new Cavalo(6, 0);

        Peca bispo_preto1 = new Bispo(2, 0);
        Peca bispo_preto2 = new Bispo(5, 0);

        Peca rei_preto = new Rei(3, 0);
        Peca dama_preto = new Dama(4, 0);

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
    
    // da pra otimizar isso mas meu qi nao é suficiente
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

    // checa se existe peça na posicao
    public boolean ChecarPosicao(int x, int y){
        if (this.tabuleiro[y][x] != null)
            return true;
        return false;
    };

    // muda o tabuleiro de acordo com a jogada
    public void MudancaNoTabuleiro(Jogada jogada){
        Peca.Pair pecaMovida = jogada.peca_movida.grid_position;
        Peca.Pair pecaCapturada = jogada.peca_movida.grid_position;
        Peca.Pair pecaMovida_movimentada = jogada.peca_movida.grid_position.add(jogada.movimento);
        
        // move peça e anula posição anterior
        this.tabuleiro[pecaMovida_movimentada.y][pecaMovida_movimentada.x] = jogada.peca_movida;
        this.tabuleiro[pecaMovida.y][pecaMovida.x] = null;
        
        
        // (capturar uma peça é simplesmente sobrescrever a posicao da peca capturada com a peca movida),
        // nao é necessario Jogada saber qual é a peca capturada.
        boolean existe_peca_capturada = (jogada.peca_capturada != jogada.peca_movida);

        // anula peça capturada
        if (existe_peca_capturada)
            this.tabuleiro[pecaCapturada.y][pecaCapturada.x] = null;
    };

    
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
    };
    

    public static void main(String[] args){        
        Tabuleiro tab = new Tabuleiro();
        System.out.println("");
        Peca.Pair movimento = new Peca.Pair(0, -1);
        Jogada j = new Jogada(tab.tabuleiro[6][0], tab.tabuleiro[6][0], movimento);
        
        
        tab.print_tabuleiro();
        System.out.println("");
        
        System.out.print(tab.tabuleiro[6][1].MovimentosValidos());
        
    
    }
}
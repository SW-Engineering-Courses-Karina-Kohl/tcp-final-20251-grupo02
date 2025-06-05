package jogo;
import jogo.peca.*;
import misc.Pair;

public class Tabuleiro {

    private int PecasNoTabuleiro = 32;
    private static final int SIZE = 8;

    // tabuleiro[y][x] = Peca(x, y)
    private Peca[][] tabuleiro = new Peca[SIZE][SIZE];

    // tabuleiro[y][x] = Peca(x, y)
    private void InitializePeca(Peca peca){
        this.tabuleiro[peca.grid_position.y][peca.grid_position.x] = peca;
    }

    // cria o tabuleiro da visão das brancas
    public Tabuleiro(){
        // pecas brancas (id maiúsculo)
        this.InitializePeca(new Peao(0, 6, 'P'));
        this.InitializePeca(new Peao(1, 6, 'P'));
        this.InitializePeca(new Peao(2, 6, 'P'));
        this.InitializePeca(new Peao(3, 6, 'P'));
        this.InitializePeca(new Peao(4, 6, 'P'));
        this.InitializePeca(new Peao(5, 6, 'P'));
        this.InitializePeca(new Peao(6, 6, 'P'));
        this.InitializePeca(new Peao(7, 6, 'P'));

        this.InitializePeca(new Torre(0, 7, 'T'));
        this.InitializePeca(new Torre(7, 7, 'T'));

        this.InitializePeca(new Cavalo(1, 7, 'C'));
        this.InitializePeca(new Cavalo(6, 7, 'C'));

        this.InitializePeca(new Bispo(2, 7, 'B'));
        this.InitializePeca(new Bispo(5, 7, 'B'));

        this.InitializePeca(new Rei(4,7,'R'));
        this.InitializePeca(new Dama(3,7, 'D'));

        // pecas pretas (id minúsculo)
        this.InitializePeca(new Peao(0, 1, 'p'));
        this.InitializePeca(new Peao(1, 1, 'p'));
        this.InitializePeca(new Peao(2, 1, 'p'));
        this.InitializePeca(new Peao(3, 1, 'p'));
        this.InitializePeca(new Peao(4, 1, 'p'));
        this.InitializePeca(new Peao(5, 1, 'p'));
        this.InitializePeca(new Peao(6, 1, 'p'));
        this.InitializePeca(new Peao(7, 1, 'p'));

        this.InitializePeca(new Torre(0, 0, 't'));
        this.InitializePeca(new Torre(7, 0, 't'));

        this.InitializePeca(new Cavalo(1, 0, 'c'));
        this.InitializePeca(new Cavalo(6, 0, 'c'));

        this.InitializePeca(new Bispo(2, 0, 'b'));
        this.InitializePeca(new Bispo(5, 0, 'b'));

        this.InitializePeca(new Rei(4, 0, 'r'));
        this.InitializePeca(new Dama(3, 0, 'd'));

        for(int i = 0 ; i < SIZE; i ++){
            for (int j = 0; j < SIZE;j ++){
                if (this.tabuleiro[i][j] == null)
                    this.tabuleiro[i][j] = new Blank(j, i);
            }
        }

    }

    // checa qual peça está na posicao (x,y)
    public Peca GetPecaNaPosicao(int x, int y){
        return this.tabuleiro[y][x];
    }

    public boolean IsTherePecaNaPosicao(int x, int y){
        if (this.tabuleiro[y][x] != null)
            return true;
        return false;
    }

    // muda o tabuleiro de acordo com a jogada
    public void MudancaNoTabuleiro(Jogada jogada){

        Pair pecaMovida = jogada.peca_movida.grid_position;
        Pair posicaoFinal = jogada.peca_capturada.grid_position;
        //Peca.Pair pecaMovida_movimentada = jogada.peca_movida.grid_position.add(jogada.movimento);

        // move peça e anula posição anterior
        this.tabuleiro[posicaoFinal.y][posicaoFinal.x] = jogada.peca_movida;
        this.tabuleiro[pecaMovida.y][pecaMovida.x] = new Blank(pecaMovida.x, pecaMovida.y);
        // jogada.peca_movida.DestruirPeca;

        // (capturar uma peça é simplesmente sobrescrever a posicao da peca capturada com a peca movida),
        // nao é necessario Jogada saber qual é a peca capturada.
        //boolean existe_peca_capturada = (jogada.posicao_final != jogada.peca_movida.grid_position);

        // anula peça capturada
        //if (existe_peca_capturada)

        //this.tabuleiro[posicaoFinal.y][posicaoFinal.x] = null;
    }

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
    }

    @Override
    public String toString(){
        String string = "\n";
	    string = string.concat("  1 2 3 4 5 6 7 8\n");
        for(int i = 0; i < SIZE; i ++){
	        string = string.concat(i + 1 + " ");
            for(int j = 0; j < SIZE; j++){
                string = string.concat(tabuleiro[i][j].identificador + " ");
            }
            string = string.concat("\n");
        }
        return string;
    }

}

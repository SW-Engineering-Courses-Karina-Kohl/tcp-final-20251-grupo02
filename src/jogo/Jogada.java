package jogo;
import jogo.peca.*;
import misc.Pair;

public class Jogada {

    public Peca pecaMovida;
    public Peca peca_capturada;
    //Jogador jogador;

    // pecaMovida = tab.getPecaNaPosicao(x,y)
    // peca_capturada = tab.getPecaNaPosicao(x,y)
    public Jogada(Peca pecaMovida, Peca peca_capturada){
        this.pecaMovida = pecaMovida;
        this.peca_capturada = peca_capturada;
    }

    public boolean ValidarRoque(Tabuleiro tabuleiro) {
        if (!(pecaMovida instanceof Rei && peca_capturada instanceof Torre))
            return false;

        Rei rei = (Rei) pecaMovida;
        Torre torre = (Torre) peca_capturada;

        // ambos não devem ter movido
        if (rei.jaMovido || torre.jaMovido)
            return false;

        Pair posRei = rei.posicaoTabuleiro;
        Pair posTorre = torre.posicaoTabuleiro;

        if (posRei.y != posTorre.y)
            return false;

        int dir = Integer.compare(posTorre.x, posRei.x);

        // verificar casas entre Rei e Torre
        int x = posRei.x + dir;
        while (x != posTorre.x) {
            if (!(tabuleiro.GetPecaNaPosicao(x, posRei.y) instanceof Blank))
                return false;
            x += dir;
        }


        char moverCor = rei.GetCorPeca();
        char oponenteCor = moverCor == 'b' ? 'p' : 'b';

        Pair[] posicoes = new Pair[] {
            posRei,
            new Pair(posRei.x + dir, posRei.y),
            new Pair(posRei.x + 2*dir, posRei.y)
        };
        for (Pair p : posicoes) {
            if (isAtacado(tabuleiro, p, oponenteCor))
                return false;
        }

        return true;
    }

    // verifica se uma casa está atacada por alguma peça da cor atacante, se houver, não há roque
    private boolean isAtacado(Tabuleiro tabuleiro, Pair alvo, char atacanteCor) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Peca p = tabuleiro.GetPecaNaPosicao(x, y);
                if (p.GetCorPeca() != atacanteCor)
                    continue;
                for (Pair mv : p.GetMovimentos()) {
                    if (mv.equals(alvo))
                        return true;
                }
            }
        }
        return false;
    }

    // valida se a jogada pode ser feita e muda o tabuleiro
    public boolean ValidarJogada(Tabuleiro tabuleiro){

	for (Pair p : this.pecaMovida.GetMovimentos()) {
	    if(p.x == this.peca_capturada.posicaoTabuleiro.x && p.y == this.peca_capturada.posicaoTabuleiro.y){
		return true;
	    }
        }

	return false;
    }

    private boolean IsTherePecaInBetween(Tabuleiro tabuleiro){
        // +1 if peca_capturada.posicaoTabuleiro > pecaMovida.posicaoTabuleiro
        //  0 if peca_capturada.posicaoTabuleiro == pecaMovida.posicaoTabuleiro
        // -1 if peca_capturada.posicaoTabuleiro < pecaMovida.posicaoTabuleiro

        int dx = Integer.compare(this.peca_capturada.posicaoTabuleiro.x, this.pecaMovida.posicaoTabuleiro.x);
        int dy = Integer.compare(this.peca_capturada.posicaoTabuleiro.y, this.pecaMovida.posicaoTabuleiro.y);

        int x = pecaMovida.posicaoTabuleiro.x + dx;
        int y = pecaMovida.posicaoTabuleiro.y + dy;

        while (! this.peca_capturada.posicaoTabuleiro.equals(new Pair(x, y)) ) {
            if (!(tabuleiro.GetPecaNaPosicao(x, y) instanceof Blank)) {
                return true;
            }

            x += dx;
            y += dy;
        }

        return false;
    }

    //private boolean ValidarXeque(Tabuleiro tabuleiro) {

    //}

    //private boolean ValidarXequeMate(Tabuleiro tabuleiro) {

    //}

   public boolean ValidarPromocaoPeao(Tabuleiro tabuleiro) {
        boolean promoveu = false;
        //verificação para as brancas
        for(int x = 0; x < 8; x++) {
            Peca p = tabuleiro.GetPecaNaPosicao(x,0);
            if (p instanceof Peao && p.GetCorPeca() == 'b') {
                //promove para rainha
                Dama dama = new Dama(x, 0, 'b');
                tabuleiro.MudancaNoTabuleiro(
                    new Jogada(p, dama)
                );
                promoveu = true;
            }
        }
        //verificação para as pretas
        for(int x = 0; x < 8; x++) {
            Peca p = tabuleiro.GetPecaNaPosicao(x,7);
            if (p instanceof Peao && p.GetCorPeca() == 'p') {
                //promove para rainha
                Dama dama = new Dama(x, 7, 'p');
                tabuleiro.MudancaNoTabuleiro(
                    new Jogada(p, dama)
                );
                promoveu = true;
            }
        }

        return promoveu;
    }

    public char idPromocao(char cor, char tipoMaiusculo) {
    if (cor == 'b') {
        // jogador branco: usa letra maiúscula
        return tipoMaiusculo;
    } else {
        // jogador preto: usa letra minúscula
        return Character.toLowerCase(tipoMaiusculo);
    }
}
}

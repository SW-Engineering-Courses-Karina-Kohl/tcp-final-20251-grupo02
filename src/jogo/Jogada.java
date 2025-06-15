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

        Pair posRei = rei.grid_position;
        Pair posTorre = torre.grid_position;
      
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
                for (Pair mv : p.MovimentosValidos(tabuleiro)) {
                    if (mv.equals(alvo))
                        return true;
                }
            }
        }
        return false;
    }

    // valida se a jogada pode ser feita e muda o tabuleiro
    public boolean ValidarJogada(Tabuleiro tabuleiro){

	for (Pair p : this.pecaMovida.MovimentosValidos(tabuleiro)) {
	    if(p.x == this.peca_capturada.grid_position.x && p.y == this.peca_capturada.grid_position.y){
		return true;
	    }
        }

	return false;
    }

    private boolean IsTherePecaInBetween(Tabuleiro tabuleiro){
        // +1 if peca_capturada.grid_position > pecaMovida.grid_position
        //  0 if peca_capturada.grid_position == pecaMovida.grid_position
        // -1 if peca_capturada.grid_position < pecaMovida.grid_position

        int dx = Integer.compare(this.peca_capturada.grid_position.x, this.pecaMovida.grid_position.x);
        int dy = Integer.compare(this.peca_capturada.grid_position.y, this.pecaMovida.grid_position.y);

        int x = pecaMovida.grid_position.x + dx;
        int y = pecaMovida.grid_position.y + dy;

        while (! this.peca_capturada.grid_position.equals(new Pair(x, y)) ) {
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
}

package jogo;
import jogo.pieces.*;
import misc.Pair;

public class Jogada {

    public Piece pecaMovida;
    public Piece peca_capturada;
    //Jogador jogador;

    // pecaMovida = tab.getPieceNaPosicao(x,y)
    // peca_capturada = tab.getPieceNaPosicao(x,y)
    public Jogada(Piece pecaMovida, Piece peca_capturada){
        this.pecaMovida = pecaMovida;
        this.peca_capturada = peca_capturada;
    }

    public boolean ValidarRoque(Tabuleiro tabuleiro) {
        if (!(pecaMovida instanceof King && peca_capturada instanceof Rook))
            return false;

        King rei = (King) pecaMovida;
        Rook torre = (Rook) peca_capturada;

        // ambos não devem ter movido
        if (rei.jaMovido || torre.jaMovido)
            return false;

        Pair posKing = rei.posicaoTabuleiro;
        Pair posRook = torre.posicaoTabuleiro;

        if (posKing.y != posRook.y)
            return false;

        int dir = Integer.compare(posRook.x, posKing.x);

        // verificar casas entre King e Rook
        int x = posKing.x + dir;
        while (x != posRook.x) {
            if (!(tabuleiro.GetPieceNaPosicao(x, posKing.y) instanceof Blank))
                return false;
            x += dir;
        }


        char moverCor = rei.GetCorPiece();
        char oponenteCor = moverCor == 'b' ? 'p' : 'b';

        Pair[] posicoes = new Pair[] {
            posKing,
            new Pair(posKing.x + dir, posKing.y),
            new Pair(posKing.x + 2*dir, posKing.y)
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
                Piece p = tabuleiro.GetPieceNaPosicao(x, y);
                if (p.GetCorPiece() != atacanteCor)
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

    private boolean IsTherePieceInBetween(Tabuleiro tabuleiro){
        // +1 if peca_capturada.posicaoTabuleiro > pecaMovida.posicaoTabuleiro
        //  0 if peca_capturada.posicaoTabuleiro == pecaMovida.posicaoTabuleiro
        // -1 if peca_capturada.posicaoTabuleiro < pecaMovida.posicaoTabuleiro

        int dx = Integer.compare(this.peca_capturada.posicaoTabuleiro.x, this.pecaMovida.posicaoTabuleiro.x);
        int dy = Integer.compare(this.peca_capturada.posicaoTabuleiro.y, this.pecaMovida.posicaoTabuleiro.y);

        int x = pecaMovida.posicaoTabuleiro.x + dx;
        int y = pecaMovida.posicaoTabuleiro.y + dy;

        while (! this.peca_capturada.posicaoTabuleiro.equals(new Pair(x, y)) ) {
            if (!(tabuleiro.GetPieceNaPosicao(x, y) instanceof Blank)) {
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

   public boolean ValidarPromocaoPawn(Tabuleiro tabuleiro) {
        boolean promoveu = false;
        //verificação para as brancas
        for(int x = 0; x < 8; x++) {
            Piece p = tabuleiro.GetPieceNaPosicao(x,0);
            if (p instanceof Pawn && p.GetCorPiece() == 'b') {
                //promove para rainha
                Queen dama = new Queen(x, 0, 'b');
                tabuleiro.MudancaNoTabuleiro(
                    new Jogada(p, dama)
                );
                promoveu = true;
            }
        }
        //verificação para as pretas
        for(int x = 0; x < 8; x++) {
            Piece p = tabuleiro.GetPieceNaPosicao(x,7);
            if (p instanceof Pawn && p.GetCorPiece() == 'p') {
                //promove para rainha
                Queen dama = new Queen(x, 7, 'p');
                tabuleiro.MudancaNoTabuleiro(
                    new Jogada(p, dama)
                );
                promoveu = true;
            }
        }

        return promoveu;
    }
}

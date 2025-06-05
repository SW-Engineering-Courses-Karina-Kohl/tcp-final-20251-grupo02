package jogo;
import misc.Pair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Jogo {

    final int  MAX_PEÇAS = 32;
    final int  MAX_JOGADORES = 2;

    public Tabuleiro tabuleiro;
    Jogador jogador_branco;
    Jogador jogador_preto;

    Jogador jogador_turno_atual = jogador_branco;

    public void NovoJogo(){
        this.tabuleiro = new Tabuleiro();
        this.jogador_branco = new Jogador("branco");
        this.jogador_preto = new Jogador("preto");
        this.jogador_turno_atual = jogador_branco;
    }

    public void ProximoTurno(){
        if (this.jogador_turno_atual == this.jogador_branco)
            this.jogador_turno_atual = this.jogador_preto;
        else this.jogador_turno_atual = this.jogador_branco;

        this.tabuleiro.GirarTabuleiro();
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.NovoJogo();

        String filePath = "testing.txt";
        String line;
        int x_movido = -1;
        int y_movido = -1;
        int x_capturado = -1;
        int y_capturado = -1;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            while(true){

                //Move a = jogo.readFile(reader);
                line = reader.readLine();
                // Example line: "1,4 3,4"
                String[] parts = line.split(" ");
                String[] from = parts[0].split(",");
                String[] to = parts[1].split(",");

                x_movido = Integer.parseInt(from[0]);
                y_movido = Integer.parseInt(from[1]);
                x_capturado = Integer.parseInt(to[0]);
                y_capturado = Integer.parseInt(to[1]);


                System.out.printf("From: (%d,%d) To: (%d,%d)%n", x_movido, y_movido, x_capturado, y_capturado);
                //return new Move(fromRow, fromCol, toRow, toCol);


                Jogada jogada = new Jogada(jogo.tabuleiro.GetPecaNaPosicao(x_movido, y_movido), jogo.tabuleiro.GetPecaNaPosicao(x_capturado, y_capturado));
                jogada.ValidarJogada(jogo.tabuleiro);
                jogo.ProximoTurno();
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static record Move(int x_movido, int y_movido, int x_capturado, int y_capturado) {};

}

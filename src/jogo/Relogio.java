package jogo;
public class Relogio {
    private int tempo;                 // em segundos
    private volatile boolean ativo;    // garanta visibilidade entre threads
    private Thread thread;

    public Relogio(int tempoInicial) {
        this.tempo = tempoInicial;
        this.ativo = false;
    }

    public void IniciarRelogio() {
        // se já está ativo e a thread viva, não faz nada
        if (thread != null && thread.isAlive()) return;

        ativo = true;
        thread = new Thread(() -> {
            while (ativo && tempo > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                tempo--;
            }
        }, "Relogio-Thread");
        thread.setDaemon(true);
        thread.start();
    }

    public void PausaRelogio() {
        ativo = false;
    }

    public String formatarTempo() {
        int min = tempo / 60;
        int seg = tempo % 60;
        return String.format("%02d:%02d", min, seg);
    }

    public int getTempo() {
        return tempo;
    }
}
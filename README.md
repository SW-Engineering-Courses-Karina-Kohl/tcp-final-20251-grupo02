# Tabuleiro de Combate de Peças (TCP)

TCP is an open-source, objected-oriented implementation of traditional the Chess game maed for our final project to the "INF01120" course.

> [!WARNING]
> TCP still under development, expect to find some bugs while testing/playing. Feel free to report or even send fixes!

## Getting started

TCP intends to be an easy play and begginer-friendly chess game. You can clone this repository and build the game with the following commands:


#### Linux (terminal):
```
git clone https://github.com/SW-Engineering-Courses-Karina-Kohl/tcp-final-20251-grupo02.git tcp
cd tcp
make run
```
#### Windows (vscode/powershell):
```
git clone https://github.com/SW-Engineering-Courses-Karina-Kohl/tcp-final-20251-grupo02.git tcp
cd tcp
javac -cp lib/jaylib-5.5.0-2.jar -d bin (Get-ChildItem -Recurse -Filter *.java -Path src).FullName
java -cp lib/jaylib-5.5.0-2.jar`;bin app.Main
```

### Tarefas

| ._subtarefas/Tarefas | Menu          | Jogo      | Movimentos               | Cheque-Mate                      | Fim       |
|---------------------+---------------+-----------+--------------------------+----------------------------------+-----------|
|                   1 | Novo Jogo     | Tabuleiro | Movimento para cada peça | Veficicação de possíveis defesas | Vencedor  |
|                   2 | Sair do Jogo  | Peças     | Captura de peças         | Derrota/Vitória                  | Novo Jogo |
|                   3 | Configurações | Turnos    | Promoção                 | Afogamento                       |           |
|                   4 |               | Relógio   | Movimentos especiais     | Repetição                        |           |

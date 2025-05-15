[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/zli9RIbW)
##**TCP-Final-20251**

## "Compilando" com Jaylib
    - javac -cp lib/jaylib-5.5.0-2.jar -d bin src/app/Main.java src/jogo/*.java src/jogo/*/*.java
    - Windows: javac -cp lib/jaylib-5.5.0-2.jar -d bin (Get-ChildItem -Recurse -Filter *.java -Path src).FullName

## Rodando com Jaylib
    - Linux: java -cp lib/jaylib-5.5.0-2.jar:bin app.Main
    - Windows CMD: java -cp lib/jaylib-5.5.0-2.jar;bin app.Main
    - Windows VScode/Powershell: java -cp lib/jaylib-5.5.0-2.jar`;bin app.Main

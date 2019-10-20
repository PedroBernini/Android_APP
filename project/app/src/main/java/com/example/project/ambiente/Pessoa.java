package com.example.project.ambiente;

public class Pessoa {

    private String nome;
    private int notaD;
    private int notaI;
    private int notaS;
    private int notaC;

    public Pessoa(String nome, int notaD, int notaI, int notaS, int notaC) {
        this.nome = nome;
        this.notaD = notaD;
        this.notaI = notaI;
        this.notaS = notaS;
        this.notaC = notaC;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNotaD() {
        return notaD;
    }

    public void setNotaD(int notaD) {
        this.notaD = notaD;
    }

    public int getNotaI() {
        return notaI;
    }

    public void setNotaI(int notaI) {
        this.notaI = notaI;
    }

    public int getNotaS() {
        return notaS;
    }

    public void setNotaS(int notaS) {
        this.notaS = notaS;
    }

    public int getNotaC() {
        return notaC;
    }

    public void setNotaC(int notaC) {
        this.notaC = notaC;
    }

    public char[] ordenarNotas() {
        char disc[] = {'D', 'I', 'S', 'C'};
        int valores[] = {this.notaD, this.notaI, this.notaS, this.notaC};
        char aux1;
        int aux2;
        for(int i=0; i<disc.length; i++){
            for(int j=0; j<(disc.length-1); j++){
                if(valores[j] < valores[j + 1]){
                    aux1 = disc[j];
                    disc[j] = disc[j+1];
                    disc[j+1] = aux1;

                    aux2 = valores[j];
                    valores[j] = valores[j+1];
                    valores[j+1] = aux2;
                }
            }
        }
        return disc;
    }

    public char getPredominancia() {
        char disc[] = this.ordenarNotas();
        return disc[0];
    }
}

package com.example.project.ambiente;

import java.util.List;

public class Equipe {

    private String nome;
    private List<Pessoa> pessoas;
    private int notaD;
    private int notaI;
    private int notaS;
    private int notaC;

    public Equipe(String nome, List<Pessoa> pessoas) {
        this.nome = nome;
        this.pessoas = pessoas;
        this.atualizarEquipe();
    }

    public void atualizarEquipe() {
        notaD = 0;
        notaI = 0;
        notaS = 0;
        notaC = 0;

        for(int i=0; i<pessoas.size();i++) {
            Pessoa pessoa = pessoas.get(i);
            notaD += pessoa.getNotaD();
            notaI += pessoa.getNotaI();
            notaS += pessoa.getNotaS();
            notaC += pessoa.getNotaC();
        }

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

    public List<Pessoa> getPessoas() {
        return pessoas;
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

    public char getCaracteristicaPrimaria(){
        char disc[] = this.ordenarNotas();
        return disc[0];
    }

    public char getCaracteristicaSecundaria(){
        char disc[] = this.ordenarNotas();
        return disc[1];
    }
}

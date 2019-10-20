package com.example.project.utils;

public class Questao {

    private String questaoD;
    private String questaoI;
    private String questaoS;
    private String questaoC;

    private int pontuacaoD;
    private int pontuacaoI;
    private int pontuacaoS;
    private int pontuacaoC;

    public Questao(String questaoD, String questaoI, String questaoS, String questaoC) {
        this.questaoD = questaoD;
        this.questaoI = questaoI;
        this.questaoS = questaoS;
        this.questaoC = questaoC;

        this.pontuacaoD = 0;
        this.pontuacaoI = 0;
        this.pontuacaoS = 0;
        this.pontuacaoC = 0;
    }

    public String getQuestaoD() {
        return questaoD;
    }

    public String getQuestaoI() {
        return questaoI;
    }

    public String getQuestaoS() {
        return questaoS;
    }

    public String getQuestaoC() {
        return questaoC;
    }

    public int getPontuacaoD() {
        return pontuacaoD;
    }

    public void setPontuacaoD(int pontuacaoD) {
        this.pontuacaoD = pontuacaoD;
    }

    public int getPontuacaoI() {
        return pontuacaoI;
    }

    public void setPontuacaoI(int pontuacaoI) {
        this.pontuacaoI = pontuacaoI;
    }

    public int getPontuacaoS() {
        return pontuacaoS;
    }

    public void setPontuacaoS(int pontuacaoS) {
        this.pontuacaoS = pontuacaoS;
    }

    public int getPontuacaoC() {
        return pontuacaoC;
    }

    public void setPontuacaoC(int pontuacaoC) {
        this.pontuacaoC = pontuacaoC;
    }
}

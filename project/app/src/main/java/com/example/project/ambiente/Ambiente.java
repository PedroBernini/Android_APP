package com.example.project.ambiente;

import java.util.ArrayList;
import java.util.List;

public class Ambiente {

    private String nomeAmbiente;
    private String tipoAmbiente;
    private List<Pessoa> pessoas;
    private List<Equipe> equipes;
    private int notaD;
    private int notaI;
    private int notaS;
    private int notaC;

    public Ambiente(String nomeAmbiente, String tipoAmbiente) {
        this.nomeAmbiente = nomeAmbiente;
        this.tipoAmbiente = tipoAmbiente;
        this.pessoas = new ArrayList<Pessoa>();
        this.equipes = new ArrayList<Equipe>();
    }

    public Ambiente(String nomeAmbiente, String tipoAmbiente, List<Pessoa> pessoas) {
        this.nomeAmbiente = nomeAmbiente;
        this.tipoAmbiente = tipoAmbiente;
        this.pessoas = pessoas;
        this.equipes = new ArrayList<Equipe>();
        this.atualizarAmbiente();
    }

    public Ambiente(String nomeAmbiente, String tipoAmbiente, List<Pessoa> pessoas, List<Equipe> equipes) {
        this.nomeAmbiente = nomeAmbiente;
        this.tipoAmbiente = tipoAmbiente;
        this.pessoas = pessoas;
        this.equipes = equipes;
        this.atualizarAmbiente();
    }

    public void atualizarAmbiente() {
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

    public String getNomeAmbiente() {
        return nomeAmbiente;
    }

    public void setNomeAmbiente(String nomeAmbiente) {
        this.nomeAmbiente = nomeAmbiente;
    }

    public String getTipoAmbiente() {
        return tipoAmbiente;
    }

    public void setTipoAmbiente(String tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
    }

    public int getNotaD() {
        return notaD;
    }

    public int getNotaI() {
        return notaI;
    }

    public int getNotaS() {
        return notaS;
    }

    public int getNotaC() {
        return notaC;
    }

    public int getQtdEquipes() {
        return equipes.size();
    }

    public int getQtdPessoas() {
        return pessoas.size();
    }

    public void adicionarEquipe(Equipe equipe) {
        this.equipes.add(equipe);
    }

    public void removerEquipe(int index) {
        this.equipes.remove(index);
    }

    public void adicionarPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    public void removerPessoa(int index) {
        this.pessoas.remove(index);
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import automato.mealy.transicao.TransicaoEntrada;
import automato.mealy.transicao.TransicaoSaida;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author joel-
 */
public class Mealy {
    private Set<String> alfabetoEntrada;
    private Set<String> estados;
    private Map<TransicaoEntrada, TransicaoSaida> funcTrans;
    private String estadoInicial;
    private Set<String> alfabetoSaida;
    private Set<String> estadosFinais;

    public Mealy(Set<String> alfabetoEntrada, Set<String> estados, Map<TransicaoEntrada, TransicaoSaida> funcTrans, String estadoInicial, Set<String> alfabetoSaida, Set<String> estadosFinais) {
        this.alfabetoEntrada = alfabetoEntrada;
        this.estados = estados;
        this.funcTrans = funcTrans;
        this.estadoInicial = estadoInicial;
        this.alfabetoSaida = alfabetoSaida;
        this.estadosFinais = estadosFinais;
    }

    public Set<String> getEstadosFinais() {
        return estadosFinais;
    }

    public void setEstadosFinais(Set<String> estadosFinais) {
        this.estadosFinais = estadosFinais;
    }

    public Set<String> getAlfabetoEntrada() {
        return alfabetoEntrada;
    }

    public void setAlfabetoEntrada(Set<String> alfabetoEntrada) {
        this.alfabetoEntrada = alfabetoEntrada;
    }

    public Set<String> getEstados() {
        return estados;
    }

    public void setEstados(Set<String> estados) {
        this.estados = estados;
    }

    public Map<TransicaoEntrada, TransicaoSaida> getFuncTrans() {
        return funcTrans;
    }

    public void setFuncTrans(Map<TransicaoEntrada, TransicaoSaida> funcTrans) {
        this.funcTrans = funcTrans;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Set<String> getAlfabetoSaida() {
        return alfabetoSaida;
    }

    public void setAlfabetoSaida(Set<String> alfabetoSaida) {
        this.alfabetoSaida = alfabetoSaida;
    }
}

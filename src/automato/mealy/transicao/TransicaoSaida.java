/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato.mealy.transicao;

/**
 *
 * @author joel-
 */
public class TransicaoSaida {
    private String estado;
    private String simboloSaida;

    public TransicaoSaida(String estado, String simboloSaida) {
        this.estado = estado;
        this.simboloSaida = simboloSaida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSimboloSaida() {
        return simboloSaida;
    }

    public void setSimboloSaida(String simboloSaida) {
        this.simboloSaida = simboloSaida;
    }
}

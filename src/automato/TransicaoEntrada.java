/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

/**
 *
 * @author joel-
 */
public class TransicaoEntrada {
    private String simboloAlfabeto;
    private String estado;

    public TransicaoEntrada(String simboloAlfabeto, String estado) {
        this.simboloAlfabeto = simboloAlfabeto;
        this.estado = estado;
    }

    public String getSimboloAlfabeto() {
        return simboloAlfabeto;
    }

    public void setSimboloAlfabeto(String simboloAlfabeto) {
        this.simboloAlfabeto = simboloAlfabeto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

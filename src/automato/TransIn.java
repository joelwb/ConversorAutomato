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
public class TransIn {
    private String simboloIn;
    private String estado;

    public TransIn(String simboloIn, String estado) {
        this.simboloIn = simboloIn;
        this.estado = estado;
    }

    public String getSimboloIn() {
        return simboloIn;
    }

    public void setSimboloIn(String simboloIn) {
        this.simboloIn = simboloIn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

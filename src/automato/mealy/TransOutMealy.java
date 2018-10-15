/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato.mealy;


/**
 *
 * @author joel-
 */
public class TransOutMealy {
    private String estado;
    private String simboloOut;

    public TransOutMealy(String estado, String simboloOut) {
        this.estado = estado;
        this.simboloOut = simboloOut;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSimboloOut() {
        return simboloOut;
    }

    public void setSimboloOut(String simboloOut) {
        this.simboloOut = simboloOut;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TransOutMealy && ((TransOutMealy)obj).estado.endsWith(estado);
    }
}

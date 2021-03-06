package automato;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import sexpression.SExp;

/**
 *
 * @author joel-
 */
public abstract class AutomatoConversivel {
    private Set<String> alfabetoIn;
    private Set<String> estados;
    private String estadoInicial;
    private Set<String> alfabetoOut;
    private Set<String> estadosFinais;

    public AutomatoConversivel(Set<String> alfabetoIn, Set<String> estados, String estadoInicial, Set<String> alfabetoOut, Set<String> estadosFinais) {
        this.alfabetoIn = alfabetoIn;
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.alfabetoOut = alfabetoOut;
        this.estadosFinais = estadosFinais;
    }
    
    public Set<String> getEstadosFinais() {
        return estadosFinais;
    }

    public void setEstadosFinais(Set<String> estadosFinais) {
        this.estadosFinais = estadosFinais;
    }

    public Set<String> getAlfabetoIn() {
        return alfabetoIn;
    }

    public void setAlfabetoIn(Set<String> alfabetoIn) {
        this.alfabetoIn = alfabetoIn;
    }

    public Set<String> getEstados() {
        return estados;
    }

    public void setEstados(Set<String> estados) {
        this.estados = estados;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Set<String> getAlfabetoOut() {
        return alfabetoOut;
    }

    public void setAlfabetoSOut(Set<String> alfabetoOut) {
        this.alfabetoOut = alfabetoOut;
    }
    
    protected Iterator<TransIn> getTransInFromSExp(ArrayList<SExp> transicoes) {
        List<TransIn> transicaoEntradas = new ArrayList<>();
        for (SExp transicao : transicoes) {
            String[] tokens = transicao.getTokens();
            transicaoEntradas.add(new TransIn(tokens[2], tokens[0]));
        }

        return transicaoEntradas.iterator();
    }
    
    public abstract AutomatoConversivel converter() throws Exception;
}

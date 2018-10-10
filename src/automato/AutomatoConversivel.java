package automato;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
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

    public AutomatoConversivel(SExp sExp) {
        this.alfabetoIn = getConjunto(sExp.getChildren().get(0).getAtoms());
        this.alfabetoOut = getConjunto(sExp.getChildren().get(1).getAtoms());
        this.estados = getConjunto(sExp.getChildren().get(2).getAtoms());
        this.estadoInicial = sExp.getChildren().get(3).getAtoms()[1];
        this.estadosFinais = getConjunto(sExp.getChildren().get(4).getAtoms());
    }
    
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

    public Set<String> getAlfabetoEntrada() {
        return alfabetoIn;
    }

    public void setAlfabetoEntrada(Set<String> alfabetoEntrada) {
        this.alfabetoIn = alfabetoEntrada;
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

    public Set<String> getAlfabetoSaida() {
        return alfabetoOut;
    }

    public void setAlfabetoSaida(Set<String> alfabetoSaida) {
        this.alfabetoOut = alfabetoSaida;
    }
    
    protected Iterator<TransicaoEntrada> getTransInFromSExp(ArrayList<SExp> transicoes) {
        List<TransicaoEntrada> transicaoEntradas = new ArrayList<>();
        for (SExp transicao : transicoes) {
            String[] tokens = transicao.getAtoms();
            transicaoEntradas.add(new TransicaoEntrada(tokens[2], tokens[0]));
        }

        return transicaoEntradas.iterator();
    }
    
    public abstract AutomatoConversivel converter();
    
    protected final Set<String> getConjunto(String[] array){
        return new LinkedHashSet<>(Arrays.asList(array).subList(1,array.length));
    }
}

package sexpression;

import automato.AutomatoConversivel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import automato.AutomatoConversivel;
import automato.TransicaoEntrada;;

public abstract class SExpToAutomato{
    private Set<String> alfabetoIn;
    private Set<String> estados;
    private String estadoInicial;
    private Set<String> alfabetoOut;
    private Set<String> estadosFinais;

    public SExpToAutomato(SExp sExp){
        this.alfabetoIn = getConjunto(sExp, "symbols−in");
        this.estados = getConjunto(sExp, "states");
        this.estadoInicial = (String) getConjunto(sExp, "start").toArray()[0];
        this.alfabetoOut = getConjunto(sExp, "symbols−out");
        this.estadosFinais = getConjunto(sExp, "finals");
    }

    protected Iterator<TransicaoEntrada> getTransInFromSExp(ArrayList<SExp> transicoes) {
        List<TransicaoEntrada> transicaoEntradas = new ArrayList<>();
        for (SExp transicao : transicoes) {
            String[] tokens = transicao.getTokens();
            transicaoEntradas.add(new TransicaoEntrada(tokens[2], tokens[0]));
        }

        return transicaoEntradas.iterator();
    }

    public static final Set<String> getConjunto(SExp sExp, String child){
        SExp childSExp = null;
        for (SExp c : sExp.getChildren()){
            if (c.getTokens()[0].equalsIgnoreCase(child)){
                childSExp = c;
                break;
            }
        }

        if (childSExp == null) new Exception("Automato não possui " + child);

        String[] tokens = childSExp.getTokens();

        return new LinkedHashSet<>(Arrays.asList(tokens).subList(1,tokens.length));
    }

    public abstract AutomatoConversivel getAutomato();


    protected Set<String> getEstadosFinais() {
        return estadosFinais;
    }

    protected Set<String> getAlfabetoIn() {
        return alfabetoIn;
    }

    protected Set<String> getEstados() {
        return estados;
    }

    protected String getEstadoInicial() {
        return estadoInicial;
    }

    protected Set<String> getAlfabetoOut() {
        return alfabetoOut;
    }
}
package sexpression.to_automato;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import automato.AutomatoConversivel;
import automato.TransIn;
import sexpression.SExp;

public abstract class SExpToAutomato{
    private Set<String> alfabetoIn;
    private Set<String> estados;
    private String estadoInicial;
    private Set<String> alfabetoOut;
    private Set<String> estadosFinais;

    public SExpToAutomato(SExp sExp) throws Exception{
        this.alfabetoIn = getConjunto(sExp, "symbols-in");
        this.estados = getConjunto(sExp, "states");
        this.estadoInicial = (String) getConjunto(sExp, "start").toArray()[0];
        this.alfabetoOut = getConjunto(sExp, "symbols-out");
        this.estadosFinais = getConjunto(sExp, "finals");
    }

    protected Iterator<TransIn> getTransInFromSExp(ArrayList<SExp> transicoes) {
        List<TransIn> transicaoEntradas = new ArrayList<>();
        for (SExp transicao : transicoes) {
            String[] tokens = transicao.getTokens();
            transicaoEntradas.add(new TransIn(tokens[2], tokens[0]));
        }

        return transicaoEntradas.iterator();
    }
    
    public static int idxConjunto(SExp sExp, String child){
        
        for (int i = 0; i < sExp.getChildren().size(); i++){
            if (sExp.getChildren().get(i).getTokens()[0].equalsIgnoreCase(child)) return i;
        }
        
        return -1;
    }

    public static final Set<String> getConjunto(SExp sExp, String child) throws Exception{
        int idx = idxConjunto(sExp, child);
        if (idx == -1) throw new Exception("Automato não possui " + child);
        
        SExp childSExp = sExp.getChildren().get(idx);

        String[] tokens = childSExp.getTokens();
        
        if (tokens.length == 1) throw new Exception(child + " não tem corpo");

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
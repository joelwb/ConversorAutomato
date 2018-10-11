package sexpression;

import java.util.Set;

import automato.AutomatoConversivel;
import automato.Mealy;
import automato.TransicaoEntrada;
import automato.mealy.TransicaoSaidaMealy;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SExpToMealy extends SExpToAutomato{
    private Map<TransicaoEntrada, TransicaoSaidaMealy> funcTrans;

    public SExpToMealy(SExp sExp){
        super(sExp);
        this.funcTrans = getFuncTransFromSExp(sExp);
    }

    public AutomatoConversivel getAutomato(){
        return new Mealy(funcTrans, getAlfabetoIn(), getEstados(),getEstadoInicial(),getAlfabetoOut(),getEstadosFinais());
    }

    private Map<TransicaoEntrada,TransicaoSaidaMealy> getFuncTransFromSExp(SExp sExp){
        ArrayList<SExp> transicoes = sExp.getChildren().get(5).getChildren();
        Iterator<TransicaoEntrada> transicaoEntradas = getTransInFromSExp(transicoes);
        Iterator<TransicaoSaidaMealy> transicaoSaidas = getTransOutFromSExp(transicoes);
        
        Map<TransicaoEntrada, TransicaoSaidaMealy> funcTransFromSExp = new HashMap<>();
        
        while (transicaoEntradas.hasNext() || transicaoSaidas.hasNext()) 
            funcTransFromSExp.put(transicaoEntradas.next(), transicaoSaidas.next());
        
        return funcTransFromSExp;
    }

    private Iterator<TransicaoSaidaMealy> getTransOutFromSExp(ArrayList<SExp> transicoes) {
        List<TransicaoSaidaMealy> transicaoSaidas = new ArrayList<>();
        for (SExp transicao : transicoes) {
            String[] tokens = transicao.getTokens();
            transicaoSaidas.add(new TransicaoSaidaMealy(tokens[1], tokens[3]));
        }

        return transicaoSaidas.iterator();
    }
}
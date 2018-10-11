package sexpression;

import automato.Moore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import automato.TransicaoEntrada;
import automato.AutomatoConversivel;

public class SExpToMoore extends SExpToAutomato{
    private Map<TransicaoEntrada, String> funcTrans;
    private Map<String, String> funcSaida;

    public SExpToMoore(SExp sExp) {
        super(sExp);
        this.funcTrans = getFuncTransFromSExp(sExp);
        this.funcSaida = getFuncSaidaFromSExp(sExp);
    }

    public AutomatoConversivel getAutomato(){
        return new Moore(funcTrans, funcSaida, getAlfabetoIn(), getEstados(), getEstadoInicial(), getAlfabetoOut(), getEstadosFinais());
    }

    private Map<TransicaoEntrada,String> getFuncTransFromSExp(SExp sExp){
        ArrayList<SExp> transicoes = sExp.getChildren().get(5).getChildren();
        Iterator<TransicaoEntrada> transicaoEntradas = getTransInFromSExp(transicoes);
        Iterator<String> transicaoSaidas = getTransOutFromSExp(transicoes);
        
        Map<TransicaoEntrada, String> funcTransFromSExp = new HashMap<>();
        
        while (transicaoEntradas.hasNext() || transicaoSaidas.hasNext()) 
            funcTransFromSExp.put(transicaoEntradas.next(), transicaoSaidas.next());
        
        return funcTransFromSExp;
    }
    
    private Iterator<String> getTransOutFromSExp(ArrayList<SExp> transicoes) {
        List<String> transicaoSaidas = new ArrayList<>();
        for (SExp transicao : transicoes) {
            transicaoSaidas.add(transicao.getTokens()[1]);
        }

        return transicaoSaidas.iterator();
    }
    
    private Map<String,String> getFuncSaidaFromSExp(SExp sExp){
        ArrayList<SExp> transicoes = sExp.getChildren().get(6).getChildren();
        
        Map<String,String> funcSaidaFromSExp = new HashMap<>();
        for (SExp trans : transicoes){
            boolean transHasSaidaVazia = trans.getChildren() != null;
            String estado = trans.getTokens()[0];
            String saida = transHasSaidaVazia ? "" : trans.getTokens()[1];
            
            funcSaidaFromSExp.put(estado, saida);
        }
        
        return funcSaidaFromSExp;
    }
}
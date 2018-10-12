package sexpression.to_automato;

import automato.Moore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import automato.TransIn;
import automato.AutomatoConversivel;
import sexpression.SExp;

public class SExpToMoore extends SExpToAutomato{
    private Map<TransIn, String> funcTrans;
    private Map<String, String> funcSaida;

    public SExpToMoore(SExp sExp) throws Exception {
        super(sExp);
        this.funcTrans = getFuncTransFromSExp(sExp);
        this.funcSaida = getFuncSaidaFromSExp(sExp);
    }

    public AutomatoConversivel getAutomato(){
        return new Moore(funcTrans, funcSaida, getAlfabetoIn(), getEstados(), getEstadoInicial(), getAlfabetoOut(), getEstadosFinais());
    }

    private Map<TransIn,String> getFuncTransFromSExp(SExp sExp) throws Exception{
        int idx = idxConjunto(sExp, "trans");
        if (idx == -1) throw new Exception("Automato não possui função de transição");
        
        ArrayList<SExp> transicoes = sExp.getChildren().get(idx).getChildren();
        
        if (transicoes == null) throw new Exception("Automato não possui transições");
        
        Iterator<TransIn> transicaoEntradas = getTransInFromSExp(transicoes);
        Iterator<String> transicaoSaidas = getTransOutFromSExp(transicoes);
        
        Map<TransIn, String> funcTransFromSExp = new HashMap<>();
        
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
    
    private Map<String,String> getFuncSaidaFromSExp(SExp sExp) throws Exception{
        int idx = idxConjunto(sExp, "out−fn");
        if (idx == -1) throw new Exception("Automato não possui função de saida");
        
        ArrayList<SExp> transicoes = sExp.getChildren().get(idx).getChildren();
        
        if (transicoes == null) throw new Exception("Automato não possui saidas");
        
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
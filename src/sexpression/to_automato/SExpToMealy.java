package sexpression.to_automato;

import automato.AutomatoConversivel;
import automato.Mealy;
import automato.TransIn;
import automato.mealy.TransOutMealy;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import sexpression.SExp;

public class SExpToMealy extends SExpToAutomato{
    private Map<TransIn, TransOutMealy> funcTrans;

    public SExpToMealy(SExp sExp) throws Exception{
        super(sExp);
        this.funcTrans = getFuncTransFromSExp(sExp);
    }

    public AutomatoConversivel getAutomato(){
        return new Mealy(funcTrans, getAlfabetoIn(), getEstados(),getEstadoInicial(),getAlfabetoOut(),getEstadosFinais());
    }

    private Map<TransIn,TransOutMealy> getFuncTransFromSExp(SExp sExp) throws Exception{
        int idx = idxConjunto(sExp, "trans");
        if (idx == -1)  throw new Exception("Automato não possui função de transição");
        
        ArrayList<SExp> transicoes = sExp.getChildren().get(idx).getChildren();
        
        if (transicoes == null) throw new Exception("Automato não possui transições");
        
        Iterator<TransIn> transicaoEntradas = getTransInFromSExp(transicoes);
        Iterator<TransOutMealy> transicaoSaidas = getTransOutFromSExp(transicoes);
        
        Map<TransIn, TransOutMealy> funcTransFromSExp = new HashMap<>();
        
        while (transicaoEntradas.hasNext() || transicaoSaidas.hasNext()) 
            funcTransFromSExp.put(transicaoEntradas.next(), transicaoSaidas.next());
        
        return funcTransFromSExp;
    }

    private Iterator<TransOutMealy> getTransOutFromSExp(ArrayList<SExp> transicoes) {
        List<TransOutMealy> transicaoSaidas = new ArrayList<>();
        for (SExp transicao : transicoes) {
            String[] tokens = transicao.getTokens();
            transicaoSaidas.add(new TransOutMealy(tokens[1], tokens[3]));
        }

        return transicaoSaidas.iterator();
    }
}
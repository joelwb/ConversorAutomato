package automato;

import sexpression.SExp;
import automato.mealy.TransicaoSaidaMealy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author joel-
 */
public class Mealy extends AutomatoConversivel {
    private Map<TransicaoEntrada, TransicaoSaidaMealy> funcTrans;

    public Mealy(SExp sExp) {
        super(sExp);
        this.funcTrans = getFuncTransFromSExp(sExp);
    }

    public Mealy(Map<TransicaoEntrada, TransicaoSaidaMealy> funcTrans, Set<String> alfabetoIn, Set<String> estados, String estadoInicial, Set<String> alfabetoOut, Set<String> estadosFinais) {
        super(alfabetoIn, estados, estadoInicial, alfabetoOut, estadosFinais);
        this.funcTrans = funcTrans;
    }

    @Override
    public AutomatoConversivel converter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Map<TransicaoEntrada, TransicaoSaidaMealy> getFuncTrans() {
        return funcTrans;
    }

    public void setFuncTrans(Map<TransicaoEntrada, TransicaoSaidaMealy> funcTrans) {
        this.funcTrans = funcTrans;
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

package automato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import sexpression.SExp;

/**
 *
 * @author joel-
 */
public class Moore extends AutomatoConversivel{

    private Map<TransicaoEntrada, String> funcTrans;
    private Map<String, String> funcSaida;

    public Moore(SExp sExp) {
        super(sExp);
        this.funcTrans = getFuncTransFromSExp(sExp);
        this.funcSaida = getFuncSaidaFromSExp(sExp);
    }

    public Moore(Map<TransicaoEntrada, String> funcTrans, Map<String, String> funcSaida, Set<String> alfabetoIn, Set<String> estados, String estadoInicial, Set<String> alfabetoOut, Set<String> estadosFinais) {
        super(alfabetoIn, estados, estadoInicial, alfabetoOut, estadosFinais);
        this.funcTrans = funcTrans;
        this.funcSaida = funcSaida;
    }

    public Map<TransicaoEntrada, String> getFuncTrans() {
        return funcTrans;
    }

    public void setFuncTrans(Map<TransicaoEntrada, String> funcTrans) {
        this.funcTrans = funcTrans;
    }

    public Map<String, String> getFuncSaida() {
        return funcSaida;
    }

    public void setFuncSaida(Map<String, String> funcSaida) {
        this.funcSaida = funcSaida;
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
            transicaoSaidas.add(transicao.getAtoms()[1]);
        }

        return transicaoSaidas.iterator();
    }
    
    private Map<String,String> getFuncSaidaFromSExp(SExp sExp){
        ArrayList<SExp> transicoes = sExp.getChildren().get(6).getChildren();
        
        Map<String,String> funcSaidaFromSExp = new HashMap<>();
        for (SExp trans : transicoes){
            boolean transHasSaidaVazia = trans.getChildren() != null;
            String estado = trans.getAtoms()[0];
            String saida = transHasSaidaVazia ? "" : trans.getAtoms()[1];
            
            funcSaidaFromSExp.put(estado, saida);
        }
        
        return funcSaidaFromSExp;
    }
    @Override
    public AutomatoConversivel converter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

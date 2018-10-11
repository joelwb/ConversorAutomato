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
}

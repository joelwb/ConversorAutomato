package automato;

import automato.mealy.TransOutMealy;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author joel-
 */
public class Mealy extends AutomatoConversivel {
    private Map<TransIn, TransOutMealy> funcTrans;

    public Mealy(Map<TransIn, TransOutMealy> funcTrans, Set<String> alfabetoIn, Set<String> estados, String estadoInicial, Set<String> alfabetoOut, Set<String> estadosFinais) {
        super(alfabetoIn, estados, estadoInicial, alfabetoOut, estadosFinais);
        this.funcTrans = funcTrans;
    }

    @Override
    public AutomatoConversivel converter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Map<TransIn, TransOutMealy> getFuncTrans() {
        return funcTrans;
    }

    public void setFuncTrans(Map<TransIn, TransOutMealy> funcTrans) {
        this.funcTrans = funcTrans;
    }
}

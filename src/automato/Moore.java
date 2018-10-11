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
    
    @Override
    public AutomatoConversivel converter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

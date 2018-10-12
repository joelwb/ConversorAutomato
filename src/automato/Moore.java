package automato;

import automato.mealy.TransOutMealy;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author joel-
 */
public class Moore extends AutomatoConversivel{

    private Map<TransIn, String> funcTrans;
    private Map<String, String> funcSaida;

    public Moore(Map<TransIn, String> funcTrans, Map<String, String> funcSaida, Set<String> alfabetoIn, Set<String> estados, String estadoInicial, Set<String> alfabetoOut, Set<String> estadosFinais) {
        super(alfabetoIn, estados, estadoInicial, alfabetoOut, estadosFinais);
        this.funcTrans = funcTrans;
        this.funcSaida = funcSaida;
    }

    public Map<TransIn, String> getFuncTrans() {
        return funcTrans;
    }

    public void setFuncTrans(Map<TransIn, String> funcTrans) {
        this.funcTrans = funcTrans;
    }

    public Map<String, String> getFuncSaida() {
        return funcSaida;
    }

    public void setFuncSaida(Map<String, String> funcSaida) {
        this.funcSaida = funcSaida;
    }
    
    @Override
    public AutomatoConversivel converter() throws Exception{
        if (!funcSaida.get(getEstadoInicial()).isEmpty())
            throw new Exception("Impossivel de converter, pois o estado inicial tem saida");
        
        Map<TransIn, TransOutMealy> funcTransMealy = new HashMap<>();
        
        funcTrans.entrySet().forEach((transicao) -> {
            String estadoOut = transicao.getValue();
            TransOutMealy out = new TransOutMealy(estadoOut, funcSaida.get(estadoOut));
            
            funcTransMealy.put(transicao.getKey(), out);
        });
        
        return new Mealy(funcTransMealy, getAlfabetoIn(), getEstados(), getEstadoInicial(), getAlfabetoOut(), getEstadosFinais());
    }
    
}

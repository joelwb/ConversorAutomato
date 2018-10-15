package automato;

import automato.mealy.TransOutMealy;
import java.util.HashMap;
import java.util.LinkedHashSet;

import java.util.Map;
import java.util.Set;
import java.util.List;

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
        String estadoInicialMoore = getEstadoInicial();
        Set<String> alfabetoInMoore = getAlfabetoIn();
        Set<String> alfabetoOutMoore = getAlfabetoOut();
        Set<String> estadosMoore = new LinkedHashSet<>();
        Set<String> estadosFinaisMoore = new LinkedHashSet<>();
        Map<TransIn, String> funcTransMoore = new HashMap<>();
        Map<String, String> funcSaidaMoore = new HashMap<>();
        
        Map<String, Map<String,String>> estadosGerados = new HashMap<>();
        
        for (String estado : getEstados()){
            estadosGerados.put(estado, new HashMap<>());
            
            if (estado.equals(getEstadoInicial())){
                estadosGerados.get(estado).put(estado, "");
                funcSaidaMoore.put(estado, "");
                estadosMoore.add(estado);
            }
            int i = 1;
            for (String saida : saidasOfEstado(estado)){
                String novoEstado = repeatApostrofo(estado, i);
                estadosGerados.get(estado).put(novoEstado, saida);
                funcSaidaMoore.put(novoEstado, saida);
                estadosMoore.add(novoEstado);
                i++;
            }
        }
        
        for (Map.Entry<TransIn,TransOutMealy> trans : funcTrans.entrySet()){
            String estadoOutEquivalente = estadoNovoEquivalente(estadosGerados, trans.getValue());
            String simboloIn = trans.getKey().getSimboloIn();
            
            System.out.print(trans.getKey().getEstado() + "," + simboloIn + " : " + trans.getValue().getEstado() + "," + trans.getValue().getSimboloOut() + " -> ");
            
            for (String estadosIn : estadosGerados.get(trans.getKey().getEstado()).keySet()){
                System.out.print(estadosIn + "," + simboloIn + " : " + estadoOutEquivalente + "; ");
                funcTransMoore.put(new TransIn(simboloIn, estadosIn), estadoOutEquivalente);
            }
            
            System.out.println();
        }
        
        for (String estadoFinal : getEstadosFinais()){
            estadosFinaisMoore.addAll(estadosGerados.get(estadoFinal).keySet());
        }
        
        return new Moore(funcTransMoore, funcSaidaMoore, alfabetoInMoore, estadosMoore, estadoInicialMoore, alfabetoOutMoore, estadosFinaisMoore);
    }
    
    private String estadoNovoEquivalente(Map<String, Map<String,String>> estadosGerados, TransOutMealy transOut){
        for (Map.Entry<String,String> novosEstados : estadosGerados.get(transOut.getEstado()).entrySet()){
            if (novosEstados.getValue().equals(transOut.getSimboloOut())) return novosEstados.getKey();
        }
        
        return null;
    }
    
    private Set<String> saidasOfEstado(String estadoSaida){
        Set<String> saidas = new LinkedHashSet<>();
        
        for (TransOutMealy transOut : funcTrans.values()){
            if (transOut.equals(new TransOutMealy(estadoSaida, null)))
                saidas.add(transOut.getSimboloOut());
        }
        
        return saidas;
    }
    
    private String repeatApostrofo(String str ,int qtdRepeticao){
        for (int i = 0; i < qtdRepeticao; i++){
            str += "'";
        }
        
        return str;
    }
    
    public Map<TransIn, TransOutMealy> getFuncTrans() {
        return funcTrans;
    }

    public void setFuncTrans(Map<TransIn, TransOutMealy> funcTrans) {
        this.funcTrans = funcTrans;
    }
}

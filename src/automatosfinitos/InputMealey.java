package automatosfinitos;

import automato.Mealy;
import automato.mealy.transicao.TransicaoEntrada;
import automato.mealy.transicao.TransicaoSaida;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author joel-
 */
public class InputMealey {
    
    private static Set<String> getConjunto(String[] array){
        return new LinkedHashSet<>(Arrays.asList(array).subList(1,array.length));
    }
    
    private static Iterator<TransicaoEntrada> getTransicaoEntrada(ArrayList<SExp> transicoes){
        List<TransicaoEntrada> transicaoEntradas = new ArrayList<>();
        for (SExp transicao : transicoes){
            String[] tokens = transicao.getAtoms();
            transicaoEntradas.add(new TransicaoEntrada(tokens[2],tokens[0]));
        }
        
        return transicaoEntradas.iterator();
    }
    
    private static Iterator<TransicaoSaida> getTransicaoSaida(ArrayList<SExp> transicoes){
        List<TransicaoSaida> transicaoSaidas = new ArrayList<>();
        for (SExp transicao : transicoes){
            String[] tokens = transicao.getAtoms();
            transicaoSaidas.add(new TransicaoSaida(tokens[1],tokens[3]));
        }
        
        return transicaoSaidas.iterator();
    }
    
    public static Mealy getAutomatoMealy(FileReader fr) throws Exception{
        ArrayList<SExp> exps = SExp.parse(new BufferedReader(fr));
        SExp root = exps.get(0);
        root.printMultiLine();
        
        Set<String> alfabetoIn = getConjunto(root.getChildren().get(0).getAtoms());
        Set<String> alfabetoOut = getConjunto(root.getChildren().get(1).getAtoms());
        Set<String> estados = getConjunto(root.getChildren().get(2).getAtoms());
        String estadoInicial = root.getChildren().get(3).getAtoms()[1];
        Set<String> estadosFinais = getConjunto(root.getChildren().get(4).getAtoms());
        
        
        ArrayList<SExp> transicoes = root.getChildren().get(5).getChildren();
        Iterator<TransicaoEntrada> transicaoEntradas = getTransicaoEntrada(transicoes);
        Iterator<TransicaoSaida> transicaoSaidas = getTransicaoSaida(transicoes);
        
        Map<TransicaoEntrada, TransicaoSaida> funcTrans = new HashMap<>();
        
        while (transicaoEntradas.hasNext() || transicaoSaidas.hasNext()) 
            funcTrans.put(transicaoEntradas.next(), transicaoSaidas.next());
        
        return new Mealy(alfabetoIn, estados, funcTrans, estadoInicial, alfabetoOut, estadosFinais);
    }
}

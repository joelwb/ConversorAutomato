package sexpression.to_sexpression;

import automato.AutomatoConversivel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import sexpression.SExp;

/**
 *
 * @author joel-
 */
abstract class AutomantoToSExp {
    
    protected static SExp converter(AutomatoConversivel automato, String nome){
        SExp root = new SExp();
        SExp alfabetoIn = createSExpFromSet(automato.getAlfabetoIn(), "symbols-in", root);
        SExp estados = createSExpFromSet(automato.getEstados(), "states" , root);
        SExp alfabetoOut = createSExpFromSet(automato.getAlfabetoOut(), "symbols-out", root);
        SExp estadosFinais = createSExpFromSet(automato.getEstadosFinais(), "finals", root);
        
        SExp estadoInicial = new SExp();
        estadoInicial.setTokens(new String[]{"start",automato.getEstadoInicial()});
        estadoInicial.setParent(root);
        
        
        ArrayList<SExp> children = new ArrayList<>();
        children.add(alfabetoIn);
        children.add(alfabetoOut);
        children.add(estados);
        children.add(estadoInicial);
        children.add(estadosFinais);
        
        root.setChildren(children);
        root.setTokens(new String[]{nome});
        
        return root;
    }
    
    protected static final SExp createSExpFromSet(Set<String> set, String nome, SExp parent){

        SExp newSExp = new SExp();
        newSExp.setParent(parent);
        
        Set<String> tokens;
        tokens = new LinkedHashSet<>();
        tokens.add(nome);
        tokens.addAll(set);
        
        newSExp.setTokens(Arrays.copyOf(tokens.toArray(), tokens.size(), String[].class));
        return newSExp;
    }
}

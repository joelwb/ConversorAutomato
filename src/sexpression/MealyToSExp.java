package sexpression;

import java.util.LinkedHashSet;
import java.util.Set;

import automato.Mealy;

public class MealyToSExp {
    public static SExp converter(Mealy mealy){
        Set<String> tokens;

        SExp root = new SExp();

        SExp alfabetoIn = new SExp();
        tokens = new LinkedHashSet<>();
        tokens.addAll(mealy.getAlfabetoIn());
        alfabetoIn.setTokens((String[]) tokens.toArray());

        return root;
    }
}
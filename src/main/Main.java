package main;

import automato.AutomatoConversivel;
import automato.Mealy;
import automato.file.InputAutomato;
import sexpression.MealyToSExp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 *
 * @author joel-
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        List<AutomatoConversivel> automatos = InputAutomato.getAutomatos(new FileReader(new File("InputMealy.txt")));
        MealyToSExp.converter((Mealy) automatos.get(0));
    }
    
}

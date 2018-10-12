package main;

import automato.AutomatoConversivel;
import automato.file.InputAutomato;
import automato.file.OutputAutomato;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
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
        List<AutomatoConversivel> automatosIn = InputAutomato.getAutomatos(new FileReader("Input.txt"));
        
        List<AutomatoConversivel> automatosConvertidos = new ArrayList<>();
        
        for (AutomatoConversivel automato: automatosIn){
            automatosConvertidos.add(automato.converter());
        }
        
        OutputAutomato.writeAutomatos(new FileWriter("Output.txt"), automatosConvertidos);
    }
    
}

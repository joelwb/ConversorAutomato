package main;

import automato.AutomatoConversivel;
import automato.file.InputAutomato;
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
    }
    
}

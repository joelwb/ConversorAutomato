package main;

import automato.AutomatoConversivel;
import automato.file.InputAutomato;
import automato.file.OutputAutomato;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author joel-
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, Exception {
        if (args.length != 4){
            System.out.println("Número de argumentos inválido = " + args.length);
            return;
        }
        
        List<String> argsLst = Arrays.asList(args);
        int idxInput = argsLst.indexOf("-i");
        int idxOutput = argsLst.indexOf("-o");
        
        if (idxInput - idxOutput == 1 || idxOutput - idxInput == 1 || idxOutput == 3 || idxInput == 4){
            System.out.println("Parametros inválidos");
            return;
        }
        
        String arqInput = args[idxInput + 1];
        String arqOutput = args[idxOutput + 1];
        
        List<AutomatoConversivel> automatosIn = InputAutomato.getAutomatos(new FileReader(arqInput));
        
        List<AutomatoConversivel> automatosConvertidos = new ArrayList<>();
        
        for (AutomatoConversivel automato: automatosIn){
            automatosConvertidos.add(automato.converter());
        }
        
        OutputAutomato.writeAutomatos(new FileWriter(arqOutput), automatosConvertidos);
    }
    
}

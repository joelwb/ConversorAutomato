package automato.file;

import automato.AutomatoConversivel;
import sexpression.SExp;
import sexpression.to_automato.SExpToMealy;
import sexpression.to_automato.SExpToMoore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author joel-
 */
public abstract class InputAutomato {
    
    public static List<AutomatoConversivel> getAutomatos(FileReader fr) throws Exception{
        ArrayList<SExp> automatosSExp = SExp.parse(new BufferedReader(fr));
        
        List<AutomatoConversivel> automatos = new ArrayList<>();
        for (SExp automatoSExp : automatosSExp) {
            if (automatoSExp.getTokens()[0].equalsIgnoreCase("mealy")) 
                automatos.add(new SExpToMealy(automatoSExp).getAutomato());
            else if (automatoSExp.getTokens()[0].equalsIgnoreCase("moore"))
                automatos.add(new SExpToMoore(automatoSExp).getAutomato());
            else
                throw new Exception("Não é um automato de Mealy ou Moore");
            
        }
        
        return automatos;
    }
}

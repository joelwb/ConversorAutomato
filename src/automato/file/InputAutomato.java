package automato.file;

import automato.AutomatoConversivel;
import automato.Mealy;
import automato.Moore;
import sexpression.SExp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author joel-
 */
public class InputAutomato {
    
    public static List<AutomatoConversivel> getAutomatos(FileReader fr) throws Exception{
        ArrayList<SExp> automatosSExp = SExp.parse(new BufferedReader(fr));
        
        List<AutomatoConversivel> automatos = new ArrayList<>();
        for (SExp automatoSExp : automatosSExp) {
            automatoSExp.printMultiLine();
            if (automatoSExp.getTokens()[0].equalsIgnoreCase("mealy")) 
                automatos.add(new Mealy(automatoSExp));
            else 
                automatos.add(new Moore(automatoSExp));
        }
        
        return automatos;
    }
}

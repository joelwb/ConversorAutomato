/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato.file;

import automato.AutomatoConversivel;
import automato.Mealy;
import automato.Moore;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import sexpression.to_sexpression.MealyToSExp;
import sexpression.to_sexpression.MooreToSExp;
/**
 *
 * @author joel-
 */
public abstract class OutputAutomato {
    
    public static void writeAutomatos(FileWriter fw, List<AutomatoConversivel> automatos) throws IOException{
        for (AutomatoConversivel automato : automatos){
            if (automato instanceof Mealy){
                fw.write(MealyToSExp.converter((Mealy) automato).toString());
            }else {
                fw.write(MooreToSExp.converter((Moore) automato).toString());
            }
            
            fw.write("\n");
            fw.flush();
        }
    }
    
    
}

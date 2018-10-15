package sexpression.to_sexpression;

import automato.Mealy;
import automato.TransIn;
import automato.mealy.TransOutMealy;
import java.util.ArrayList;
import java.util.Map;
import sexpression.SExp;

public abstract class MealyToSExp{
    
    public static SExp converter(Mealy mealy){
        SExp root = AutomantoToSExp.converter(mealy,"mealy");
        
        SExp funcTrans = new SExp();
        funcTrans.setChildren(new ArrayList<>());
        funcTrans.setTokens(new String[]{"trans"});
        funcTrans.setParent(root);
        
        for (Map.Entry<TransIn,TransOutMealy> transicao : mealy.getFuncTrans().entrySet()){
            SExp trans = new SExp();
            trans.setParent(funcTrans);
            
            TransIn tranIn= transicao.getKey();
            TransOutMealy transOut = transicao.getValue();
            
            trans.setTokens(new String[] {tranIn.getEstado(),transOut.getEstado(),tranIn.getSimboloIn(),transOut.getSimboloOut()});
            funcTrans.getChildren().add(trans);
        }

        root.getChildren().add(funcTrans);
        return root;
    }
}
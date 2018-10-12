package sexpression.to_sexpression;

import automato.Moore;
import automato.TransIn;
import java.util.ArrayList;
import java.util.Map;
import sexpression.SExp;

/**
 *
 * @author joel-
 */
public class MooreToSExp {
    
    public static SExp converter(Moore moore){
        SExp root = AutomantoToSExp.converter(moore,"moore");
        
        SExp funcTrans = new SExp();
        funcTrans.setChildren(new ArrayList<>());
        funcTrans.setTokens(new String[]{"trans"});
        funcTrans.setParent(root);
        
        for (Map.Entry<TransIn,String> transicao : moore.getFuncTrans().entrySet()){
            SExp trans = new SExp();
            trans.setParent(funcTrans);
            
            TransIn tranIn= transicao.getKey();
            String transOut = transicao.getValue();
            
            trans.setTokens(new String[] {tranIn.getEstado(),transOut,tranIn.getSimboloAlfabeto()});
            funcTrans.getChildren().add(trans);
        }
        
        
        SExp funcSaida= new SExp();
        funcSaida.setChildren(new ArrayList<>());
        funcSaida.setTokens(new String[]{"out−fn"});
        funcSaida.setParent(root);
        
        for (Map.Entry<String,String> transicaoSaida : moore.getFuncSaida().entrySet()){
            SExp trans = new SExp();
            trans.setParent(funcTrans);
            
            String estado = transicaoSaida.getKey();
            String saida = transicaoSaida.getValue();
            
            if (saida.isEmpty()){
                SExp transVazia = new SExp();
                transVazia.setTokens(new String[]{""});
                transVazia.setParent(trans);
                
                trans.setChildren(new ArrayList<>());
                trans.getChildren().add(transVazia);
                trans.setTokens(new String[]{estado});
            }
            else{
                trans.setTokens(new String[]{estado, saida});
            }
            funcSaida.getChildren().add(trans);
        }
        

        root.getChildren().add(funcTrans);
        root.getChildren().add(funcSaida);
        
        return root;
    }
}

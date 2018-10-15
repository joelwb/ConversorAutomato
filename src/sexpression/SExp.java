package sexpression;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 *
 * @author joel-
 */
public class SExp {

    private static final char EXPRESSION_START = '(';
    private static final char EXPRESSION_CLOSE = ')';

    private StringBuilder atomText = new StringBuilder();
    private SExp parent;
    private ArrayList<SExp> children;
    private String[] tokens;

    public SExp() {
    }

    public String[] getTokens() {
        return tokens;
    }

    public SExp getParent() {
        return parent;
    }

    public ArrayList<SExp> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<SExp> children) {
        this.children = children;
    }

    public void setParent(SExp parent) {
        this.parent = parent;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public void printMultiLine() {
        StringBuilder sb = new StringBuilder();
        printExpression(0, sb);
        System.out.println(sb.toString());
    }

    /**
     * Multi-line expression print using indentation for according to depth
     */
    private void printExpression(int depth, StringBuilder sb) {
        // indentation 
        for (int i = 0; i < depth; i++) {
            sb.append("    ");
        }

        // print atoms 
        sb.append("(");
        for (int i = 0; i < tokens.length; i++) {
            sb.append(tokens[i] + (i < tokens.length - 1 ? " " : ""));
        }

        if (children != null && children.get(0).getTokens()[0].isEmpty()) {
            sb.append(" ()");
        }

        // children 
        if (children != null && !children.get(0).getTokens()[0].isEmpty()) {
            for (SExp se : children) {
                sb.append("\n");
                se.printExpression(depth + 1, sb);
            }
            
            sb.append("\n");
            if (tokens.length == 1) {
                for (int i = 0; i < depth; i++) {
                    sb.append("    ");
                }
            }
        }

        sb.append(")");
    }

    /**
     * Parses s-expressions contained in text
     *
     * @return the list of expressions, or null if there are none
     * @throws Exception
     */
    public static ArrayList<SExp> parse(BufferedReader reader) throws Exception {

        ArrayList<SExp> expressions = new ArrayList<>();
        SExp curExpr = null;

        int c;
        while ((c = reader.read()) != -1) {
            char character = (char) c;

            if (character == EXPRESSION_START) {
                // new expression started 
                SExp expr = new SExp();

                // if current expression is already set, the current expression 
                // is the parent of this new expression 
                if (curExpr != null) {
                    if (curExpr.children == null) {
                        curExpr.children = new ArrayList<>();
                    }
                    curExpr.children.add(expr);
                    expr.parent = curExpr;
                }
                curExpr = expr;
            } else if (character == EXPRESSION_CLOSE) {
                // end of current expression 
                if (curExpr == null) {
                    throw new Exception("Trying to end s-expression, "
                            + "but no s-expression has been started");
                }

                // if parent is null, this is the end of the list 
                if (curExpr.parent == null) {
                    expressions.add(curExpr);
                }

                // tokenize atoms 
                curExpr.tokens = curExpr.atomText.toString().split("\\s+");
                curExpr.atomText = null;
                curExpr = curExpr.parent;

            } else {
                // append character to current expression's list of atoms or 
                // ignore it if there is no expression started 
                // Só entra se o caracter for espaço em branco ou
                // Caso não seja, o atomText não pode ser vazio
                // Isso é para evitar espaços em branco após (
                if (curExpr != null && (character != ' ' || (character == ' ' && !curExpr.atomText.toString().isEmpty()))) {

                    // Valida se caso a expressão ja tenha filhos
                    // Não pode ter qualque outro caracter apos isto, tirando espaço e pular linha
                    // Ou seja, não pode adicionar mais tokens a expressão depois que ja se tem filhos
                    if (curExpr.getChildren() != null && character != ' ' && character != '\n' && character != '\r') {
                        throw new Exception("Erro! Não se pode adicionar mais tokens depois que a SExp já possui filhos!");
                    }
                    
                    if (character == '−'){
                        character = '-';
                    }
                    
                    curExpr.atomText.append(character);
                }
            }
        }

        // if current expression isn't null there is an unclosed expression 
        if (curExpr != null) {
            throw new Exception("S-expression not closed; expecting end "
                    + "of expression with ')'");
        }

        return expressions.isEmpty() ? null : expressions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        printExpression(0, sb);
        return sb.toString();
    }
}

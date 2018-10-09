/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatosfinitos;

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
    private String[] atoms;

    public String[] getAtoms() {
        return atoms;
    }

    public SExp getParent() {
        return parent;
    }

    public ArrayList<SExp> getChildren() {
        return children;
    }

    private SExp() {
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
            sb.append("--");
        }

        // print atoms 
        sb.append("[");
        for (int i = 0; i < atoms.length; i++) {
            sb.append(atoms[i] + (i < atoms.length - 1 ? "," : ""));
        }
        sb.append("]\n");

        // children 
        if (children != null) {
            for (SExp se : children) {
                se.printExpression(depth + 1, sb);
            }
        }
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
                curExpr.atoms = curExpr.atomText.toString().split("\\s+");
                curExpr.atomText = null;
                curExpr = curExpr.parent;

            } else {
                // append character to current expression's list of atoms or 
                // ignore it if there is no expression started 
                if (curExpr != null) {
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

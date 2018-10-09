package automatosfinitos;

import automato.Mealy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author joel-
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        Mealy mealy = InputMealey.getAutomatoMealy(new FileReader(new File("InputMealy.txt")));
    }
    
}

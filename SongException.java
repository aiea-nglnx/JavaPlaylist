import java.util.Scanner;

/**
 * An interactive program Java program that models
 * an environment by creating classes for a song object.
 * Number exception ensures if a number is negtiave or not.
 * 
 * @author     Pao, Angelina
 * @thing      Custom Exception
 * @bugs       Short description of bugs in the program, if any.
 */
 
public class SongException extends Exception {
     public SongException(String message) {
        super(message); // Passes the message to the Exception superclass
    }
}

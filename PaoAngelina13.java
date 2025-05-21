import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

/**
 * An interactive program Java program that models a music playlist, demonstrating the student's 
 * understanding of objects, methods, and ArrayList. A custom exception will be 
 * created to prevent runtime errors.
 * 
 * @author     Pao, Angelina
 * @assignment ICS 111 Assignment 13
 * @date       05/04/2025
 * @bugs       Short description of bugs in the program, if any.
 */
 
public class PaoAngelina13 {
   private static ArrayList<Song> playlist;
   public static void main(String[] args) {
   
      // Declaring variables and initializing resources
      Scanner reader = new Scanner(System.in);
      String greetings = ""; // Stores the user's name
      
      // Prompt the user to enter their name and greet them when the program runs
      System.out.print("Greetings, what's your name? ");
      greetings = reader.nextLine();
      System.out.println("\nHey " + greetings.toUpperCase() + "!" + " This program will display " +
         "the songs in my 'Unstrung Emotions' playlist!");
      
      // Adding at least 5 Song objects
      try {
         
      }
      catch (NumberException ne) {
         System.out.println("Error adding song: " + ne.getMessage());
      }
      // Calling the createPlaylist method
      createPlaylist(playlist);
      
      // Calling the editPlaylist method
      playlist = editPlaylist(playlist, reader);
      
      // Calling the savePlaylist method
      playlist = savePlaylist(playlist, reader);
   }
   
/**
 * A createPlaylist that prints out the ArrayList in order with Song Objects separated
 * 
 * @param       none
 * @return      the ArrayList in order with the Song objects    
 * @exception   SongException if there is an issue initalizing a Song object
 */ 
   // createPlayList method
   public static ArrayList<Song> createPlaylist() {
     // Create the Arraylist
     ArrayList<Song> playlist = new ArrayList<>();
      try {
         playlist.add(new Song("Oceans & Engines", "NIKI", 5, 36));
         playlist.add(new Song("When the Rain Stops", "eaJ", 3, 23));
         playlist.add(new Song("Stay a Little Longer", "Rose", 4, 07));
         playlist.add(new Song("I Know It Won't Work", "Grace Abrams", 4, 06));
         playlist.add(new Song("Steal The Show", "Lauv", 3, 13));
      }
      catch (SongException se) {
         System.out.println("Error initalizing playlist: " + se.getMessage());
      }
      // Return the ArrayList
      return playlist;
   }
   
/**
 * A editPlaylist that after the ArrayList of Song object that are passed
 * allows the user to remove or add a song
 * 
 * @param       ArrayList<Song> playlist to hold & retrieve the Song object
 * @param       Scanner reader to hold user input
 * @return      playlist  
 * @exception   SongException to ensure if there is an issue initalizing a Song object
 * @exception   InputMismatchException to make sure user enters the wrong type
 */ 
   // editPlayList method
   public static ArrayList<Song> editPlaylist(ArrayList<Song> playlist, Scanner reader) {
      boolean editing = true;
     
      while (editing) {
         // Ask the user if they want to make changes to the playlist
         String userResponse = "";
      
         System.out.print("\nWhat do you think? Should I make any changes to the playlist?" + 
            "\n(Or if you change your mind, you can say nevermind.)" + "\nType add, remove, or nevermind: " + userResponse);
         userResponse = reader.nextLine();
         
         if (userResponse.toLowerCase().equals("add")) {
            try {
               System.out.println("\n\tOkay, let's add a song! Please enter the song information: ");
                  
               System.out.print("\t\tSong Title: ");
               String title = reader.nextLine();
            
               System.out.print("\t\tArtist: ");
               String artist = reader.nextLine();
            
               int minutes = -1;
               while (minutes < 0) {
                  System.out.print("\t\tMinutes: ");
                  try {
                     minutes = reader.nextInt();
                     reader.nextLine();   
                    
                     if (minutes < 0) {
                        throw new NumberException("\tSorry but " + minutes + " isn't a proper or positive numerical value. Please try again.");
                     }
                  }      
                  catch (java.util.InputMismatchException ime) {
                     System.out.println("\nERROR! You must enter a valid number.");
                     reader.nextLine();
                  } 
                  catch (NumberException ne) {
                     System.out.println(ne.getMessage());
                  }
               }    
               int seconds = -1;
               while (seconds < 0) {
                  System.out.print("\t\tSeconds: ");
                  try {
                     if (!reader.hasNextInt()) { // Handles non-numeric input (including spaces)
                        System.out.println("\n\tERROR! You must enter a valid number. Please try again!");
                        reader.nextLine(); // Clear invalid input
                        continue;
                     }
                  
                     seconds = reader.nextInt();
                     reader.nextLine();   
                    
                     if (seconds < 0) {
                        throw new NumberException("\tSorry but " + seconds + " isn't a proper or positive numerical value. Please try again.");
                     }
                  }      
                  catch (java.util.InputMismatchException ime) {
                     System.out.println("\nERROR! You must enter a valid number.");
                     reader.nextLine();
                  } 
                  catch (NumberException ne) {
                     System.out.println(ne.getMessage());
                  }
               }           
            
               System.out.print("\tWhat # song in the playlist should it be: ");
                  
               playlist.add(reader.nextInt() - 1, new Song(title, artist, minutes, seconds));
               reader.nextLine();
               
               System.out.println("\nNow adding \'" + playlist.get(playlist.size() - 1).getTitle() + "\' as #" + playlist.size() + " on the playlist...");
               System.out.println("\nHere's the new playlist: ");
               for (int i = 0; i < playlist.size(); i++) {
                  System.out.println((i + 1) + ". " + playlist.get(i)); // Print all songs correctly
               }
            }
            catch (java.util.InputMismatchException ime) {
               System.out.println("ERROR! You must enter a valid number.");
            }
            catch (NumberException ne) {
               System.out.println("ERROR! " + ne.getMessage());
            }  
         }
         else if (userResponse.toLowerCase().equals("nevermind")) {
            System.out.println("\nNo changes made. Returning to the playlist view.");
            editing = false;
         }
         else if (userResponse.toLowerCase().equals("remove")) {
             // Removes the song
            System.out.println("\nOkay!, Just in case you forgot, here is the current playlist: ");
               
            System.out.println("\n***Unstrung Emotions Playlist***");
            for (int i = 0; i < playlist.size(); i++) {
               System.out.println((i + 1) + ". " + playlist.get(i));
            }
            System.out.print("Which # song would you like to remove: ");             
            if (reader.hasNextInt()) {
               Song removedSong = playlist.remove(reader.nextInt() - 1);
               reader.nextLine();
               System.out.println("\nNow removing \"" + removedSong.getTitle() + "\" by " + removedSong.getArtist() + 
                  " (" + removedSong.getMinutes() + ":" + 
                  String.format("%02d", removedSong.getSeconds()) + ")...");
               
               System.out.println("\nHere's the new playlist: ");
               for (int i = 0; i < playlist.size(); i++) {
                  System.out.println((i + 1) + ". " + playlist.get(i)); // Print all songs correctly
               }
            
            } 
            else {
               System.out.println("ERROR! You need to type a number.");
               reader.nextLine();
            }
         }
         else {
            System.out.println("\nInvalid input! Please type add, remove, or nevermind.");
         }
      
      }
      return playlist;  
   }
     
/**
 * A savePlaylist that serves as an indicator for whether the user would like
 * like to append to or overwrite a file.
 * 
 * @param       ArrayList<Song> playlist to hold & retrieve the Song object
 * @param       Scanner reader to hold user input
 * @return      playlist    
 * @exception   none
 */ 
   // savePlayList method
   
   public static ArrayList<Song> savePlaylist(ArrayList<Song> playlist, Scanner reader) {
    System.out.print("\nOnce you close this program, you're gonna lose all the playlist data."
        + "\nWould you like to save it to a file, so you can refer to it later?"
        + "\nType yes or no: ");
    
    String response = reader.nextLine().toLowerCase(); 

    if (response.equals("yes")) { // Using your preferred approach
        File outFH = new File("playlist.txt");

        System.out.print("\nWould you like to append to or overwrite this file?"
            + "\nType append or overwrite: ");
        
        response = reader.nextLine().toLowerCase(); // Reuse response

        boolean append = response.equals("append"); 

        // Format the date when the playlist is saved
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = LocalDateTime.now().format(formatter);

        try (FileWriter outFW = new FileWriter(outFH, append)) {
            outFW.write("Playlist saved on: " + currentDate + "\n");
            outFW.write("------------------------------------\n");

            if (playlist.isEmpty()) {
                outFW.write("No songs in the playlist.\n");
            } 
            else {
                for (int i = 0; i < playlist.size(); i++) {
                    Song song = playlist.get(i);
                    outFW.write((i + 1) + ". " + song.getTitle() + " - "
                                + song.getArtist() + " (" + song.getMinutes() + ":"
                                + String.format("%02d", song.getSeconds()) + ")\n");
                }
            }

            System.out.println("\n   Playlist is now saved to: " + outFH.getAbsolutePath());
        } 
        catch (IOException ioe) {
            System.out.println("An error occurred while saving the playlist. Please restart the program!");
        }
    } 
    else { // Keeping your preferred structure
        System.out.println("That's okay.");
    }
    return playlist;
}
}

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
         "the songs in my 'Unstrung Emotions' playlist!\n");
     
      // Calling the createPlaylist method
      playlist = createPlaylist();
      displayPlaylist(playlist);
      
      
            
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
 * A displayPlaylist that prints out the ArrayList in order with Song Objects separated
 * 
 * @param       playlist An ArrayList of Song objects representing the songs
 * @return      none    
 * @exception   none
 */ 
   // displayPlaylist method
   public static void displayPlaylist (ArrayList<Song> playlist) {
     // Display the playlist
     
     for (int i = 0; i < playlist.size(); i++) {
      Song song = playlist.get(i);
      System.out.printf("%s by %s | Duration: %d:%02d%n",
      song.getTitle(), 
      song.getArtist(), 
      song.getMinutes(), 
      song.getSeconds()
      ); 
     }
     
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
    boolean success = false; // Tracks whether song addition was successful
    while (!success) { // Keep looping until valid input is given
        try {
            System.out.print("\nWhat do you think? Should I make any changes to the playlist?" + 
                "\n(Or if you change your mind, you can say nevermind.)" + 
                "\n\tType add, remove, or nevermind: ");
            String userResponse = reader.nextLine();

            if (userResponse.equalsIgnoreCase("add")) {
                System.out.println("\n\tOkay, let's add a song! Please enter the song information: ");

                System.out.print("\t\tSong Title: ");
                String title = reader.nextLine();

                System.out.print("\t\tArtist: ");
                String artist = reader.nextLine();

                int minutes = 0, seconds = 0;
                try {
                    System.out.print("\t\tMinutes: ");
                    minutes = reader.nextInt();
                    System.out.print("\t\tSeconds: ");
                    seconds = reader.nextInt();
                    reader.nextLine(); // Consume leftover newline

                    // Try creating the song (this triggers validation)
                    Song newSong = new Song(title, artist, minutes, seconds);
                    playlist.add(newSong);

                    System.out.println("\nNow adding '" + newSong.getTitle() + "' to the playlist!");
                    success = true; // Exit loop if song is added successfully

                    System.out.println("\nHere's the new playlist:");
                    for (int i = 0; i < playlist.size(); i++) {
                        System.out.println((i + 1) + ". " + playlist.get(i)); // Print all songs correctly
                    }
                } catch (SongException se) { 
                    System.out.println("ERROR! Invalid song entry: " + se.getMessage());
                    System.out.println("Restarting input process... Please try again.");
                    reader.nextLine(); // Clear invalid input
                } catch (Exception e) {
                    System.out.println("ERROR! Please enter valid numbers for minutes and seconds.");
                    System.out.println("Restarting input process... Please try again.");
                    reader.nextLine(); // Clear invalid input
                }
            }
            else if (userResponse.equalsIgnoreCase("remove")) {
                System.out.println("\nOkay! Just in case you forgot, here is the current playlist:");
                System.out.println("\n*** Unstrung Emotions Playlist ***");
                for (int i = 0; i < playlist.size(); i++) {
                    System.out.println((i + 1) + ". " + playlist.get(i));
                }

                System.out.print("Which # song would you like to remove: ");
                if (reader.hasNextInt()) {
                    int index = reader.nextInt() - 1;
                    reader.nextLine(); // Consume leftover newline

                    if (index >= 0 && index < playlist.size()) {
                        Song removedSong = playlist.remove(index);
                        System.out.println("\nNow removing \"" + removedSong.getTitle() + "\" by " + removedSong.getArtist());
                        success = true; // Exit loop after successful removal
                    } else {
                        System.out.println("ERROR! Invalid song number. Please enter a number between 1 and " + playlist.size());
                    }
                } else {
                    System.out.println("ERROR! You need to type a number.");
                    reader.nextLine(); // Clear invalid input
                }
            }
            else if (userResponse.equalsIgnoreCase("nevermind")) {
                System.out.println("\nNo changes made. Returning to the playlist view.");
                success = true; // Exit loop
            }
            else {
                System.out.println("\nInvalid input! Please type add, remove, or nevermind.");
            }

        } catch (Exception e) {
            System.out.println("ERROR! An unexpected error occurred: " + e.getMessage());
            System.out.println("Restarting input process... Please try again.");
            reader.nextLine(); // Clear invalid input
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

/**
 * An interactive program Java program that models a music playlist
 * demonstrating the student's understanding of objects and ArrayList.
 * 
 * @author     Pao, Angelina
 * @thing      Song Object
 * @bugs       Short description of bugs in the program, if any.
 */
 
 public class Song {
   // Instance variables that represent Object 1's characteristics   
      private String title = ""; // Stores the title of the song
      private String artist = ""; // Stores the artist of the song
      private int minutes = 0; // Stores the minutes of the song
      private int seconds = 0; // Stores the seconds of the song
   
/**
 * Constructs a Song object with specified attributes.
 *
 * @param title     The title of the song (must be at least 2 characters long).
 * @param artist    The name of the song's artist (must be at least 2 characters long).
 * @param minutes   The minutes of the song (must be a positive integer).
 * @param seconds   The seconds of the song (must be positive and can't be over 60).
 * @throws IllegalArgumentException   If the title or artist is too short, or the type is invalid.
 * @throws NumberException            If minutes or seconds is negative.
 */
   
   // Constructor that allows you to create the Song object
   public Song(String title, String artist, int minutes, int seconds) throws NumberException {
      settitle(title);
      setartist(artist);
      setminutes(minutes);
      setseconds(seconds);
      
      this.title = title;
      this.artist = artist;
      this.minutes = minutes;
      this.seconds = seconds;

   }
   
   /**
 * Returns a string representation of the Song object.
 *
 * @return A formatted string containing the song's title, artist, minutes & seconds duration.
 *         Example format:
 *         Title: [title]
 *         Artist: [artist]
 *         Minutes: [minutes]
 *         Seconds: [seconds]
 */

   // toString method
   public String toString() {
      String output = "";
      output += "\"" + this.title + "\""; // Adds song title to output
      output += " by " + this.artist + " "; // Adds artist of the song to output
      output += "\n" + this.minutes + " minutes "; // Adds the minutes of the song to output
      output += this.seconds + " seconds\n"; // Adds the seconds of the song to output
      
      return output;
   }
   // Accessor Methods to Song class that retrieves the songs's attributes
   public String getTitle() {
      return this.title;
   }
   public String getArtist() {
      return this.artist;
   }
   public int getMinutes() {
      return this.minutes;
   }
   public int getSeconds() {
      return this.seconds;
   }
   
   // Adding Mutator methods to Song class that allows modification
   public void settitle(String newTitle) throws IllegalArgumentException {
      if (newTitle.length() >= 2) { 
      this.title = newTitle;
      }
      else {
         throw new IllegalArgumentException("ERROR! Song title must be at least 2 characters long!!");
      }
   }
   public void setartist(String newArtist) throws IllegalArgumentException {
      if (newArtist.length() >= 2) {
         this.artist = newArtist;
      }
      else {
         throw new IllegalArgumentException("ERROR! Artist must be at least 2 characters long!");
      }
   }
   public void setminutes(int newMinutes) throws NumberException {
      if (newMinutes > 0) {
         this.minutes = newMinutes;
      }
      else {
         throw new NumberException("ERROR! Sorry but " + newMinutes + " isn't a proper or positive numerical value.");
      }
      
   }
   public void setseconds(int newSeconds) throws NumberException {
      if (newSeconds > 0 || seconds < 59) {
         this.seconds = newSeconds;
      }
      else {
         throw new NumberException("Sorry but " + newSeconds + " must be above the value 0 and can't be over 59.");
      }
   }
}

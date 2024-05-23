import java.util.*;
import games.*;
import rendering.*;

class AllenArcade {
   private static final String TITLE = "ALLEN_ARCADE.exe";
   private static final int WIDTH = 64;
   private static final int HEIGHT = 18 ;
   private static final Scanner IN = new Scanner(System.in);
   private static final TextScreen OUT = new TextScreen(WIDTH, HEIGHT);
   
   private static ArcadeGame running = null;
   private static ArrayList<Class<? extends ArcadeGame>> games = new ArrayList<Class<? extends ArcadeGame>>();

   public static void main(String[] args) {
      games.add(games.TicTacToe.class);
      games.add(games.ConnectFour.class);

      while (true) {
         if (running == null) {
            
            // Game is not running
            String[] choices = new String[games.size() + 1];
            for (int i = 0; i < games.size(); i++) {
               try {
                  choices[i] = (String)games.get(i).getField("TITLE").get(null);
               } catch (Exception e) {
                  System.out.println("title missing from game " + i + "!!!!!");
               }
            }
            choices[games.size()] = "Exit";
            int choice = ask(choices);
            
            if (choice == games.size()) {
               break; //Chose to exit
            } else {
               try {
                  running = games.get(choice - 1).getConstructor().newInstance();
               } catch (Exception e) {
                  System.out.println("error starting game!!!!!");
               }
            }
            

         } else {

            // Game is running
            OUT.clear();
            running.render(OUT);
            drawSystem(running.TITLE);
            OUT.render();

            drawPrompt();
            running.input(IN);
         }
       }
   }
   
   private static void drawSystem(String title) {
      OUT.renderBox(0, 0, OUT.width-1, OUT.height-1, "+", "-", "|");
      //OUT.renderBox(OUT.width/4, OUT.height/4, 3*OUT.width/4-1, 3*OUT.height/4-1, "+", "-", "|");
      OUT.renderText(2, 0, title);
   }
   
   private static void drawPrompt() {
      System.out.print("\n> ");
   }
   
   public static int ask(String[] choices) {
      return ask(choices, TITLE);
   }

   public static int ask(String[] choices, String title) {
      
      String errorText = new String();
      
      int selected = -1;
      
      while (true) {
         OUT.clear();
         OUT.renderText(OUT.width / 2, 2, errorText, 0.5, 0); // Draw error text
         
         // Draw choices
         String choicesText = new String();
         for (int c = 0; c < choices.length; c++) {
            String before = (selected == c) ? "[" : " ";
            String after = (selected == c) ? "]" : " ";
            
            choicesText += before + " " + c + " " + after + " -   " + choices[c];
            
            if (c+1 < choices.length) {
               choicesText += "\n";
            }
         }
         OUT.renderText(OUT.width/5, OUT.height/2, choicesText, 0, 0.5);
         
         drawSystem(title);
         OUT.render();
         
         drawPrompt();

         String errmsg = "";
         
         try {
            int c = IN.nextInt();
            if (c >= choices.length || c < 0) {
               errmsg = "out of bounds!";
            } else if (c == selected) {
               return c;
            } else {
               selected = c;
               continue;
            }
         } catch (InputMismatchException e) {
            errmsg = "what have you done.\nthis is a java bug idk how to fix it.\nplease enter a number next time.";
         }

         errorText = "Invalid choice: " + errmsg;
      }
   }
}
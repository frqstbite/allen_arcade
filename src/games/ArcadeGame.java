package games;
import java.util.*;
import rendering.TextScreen;

public abstract class ArcadeGame {
   public static final String TITLE = "UNTITLED.exe"; 

   public abstract void render(TextScreen out);
   public abstract void input(Scanner in);
}
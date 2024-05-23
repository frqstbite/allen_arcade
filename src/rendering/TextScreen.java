package rendering;

public class TextScreen {
   private static final int CLEAR_LINES = 10;

   public final int width;
   public final int height;
   
   // Buffers
   private Matrix<String> buffer1;
   private Matrix<String> buffer2;
   private boolean buffer = false;
   
   public TextScreen(int w, int h) {
      width = w;
      height = h;
      
      buffer1 = new Matrix<String>(w, h, " ");
      buffer2 = new Matrix<String>(w, h, " ");
   }
   
   private String concatBuffer(Matrix<String> b) {
      String out = new String();
      
      for (int r = 0; r < b.height; r++) {
         for (int c = 0; c < b.width; c++) {
            out += b.get(r, c);
         }
         out += "\n";
      }
      
      return out;
   }
   
   public void renderText(int x, int y, String txt) { renderText(x, y, txt, 0, 0); }
   public void renderText(int x, int y, String txt, double ox, double oy) {
      Matrix<String> buff = getBuffer();
      String[] lines = txt.split("\\R", -1);
      for (int l = 0; l < lines.length; l++) {
         String line = lines[l];
         int liney = y - (int)(lines.length * oy) + l;
         for (int i = 0; i < line.length(); i++) {
            int linex = x - (int)(line.length() * ox) + i;
            buff.set(liney, linex, line.substring(i, i+1));
         }
      }
   }
   
   public void renderBox(int x1, int y1, int x2, int y2, String corner, String hori, String vert) {
      Matrix<String> buff = getBuffer();
      int w = Math.abs(x1 - x2) + 1;
      int h = Math.abs(y1 - y2) + 1;
      
      // Top
      for (int x = 1; x < w-1; x++) {
         buff.set(y1, x1 + x, hori);
      }
      
      // Bottom
      for (int x = 1; x < w-1; x++) {
         buff.set(y2, x1 + x, hori);
      }
      
      // Left
      for (int y = 1; y < h-1; y++) {
         buff.set(y1 + y, x1, vert);
      }
      
      // Right
      for (int y = 1; y < h-1; y++) {
         buff.set(y1 + y, x2, vert);
      }
      
      // Corners
      buff.set(y1, x1, corner);
      buff.set(y1, x2, corner);
      buff.set(y2, x1, corner);
      buff.set(y2, x2, corner);
   }
   
   public Matrix<String> getBuffer() {
      if (buffer) {
         return buffer2;
      } else {
         return buffer1;
      }
   }
   
   public void render() {
      Matrix<String> b = getBuffer();
      System.out.println(concatBuffer(b));
      buffer = !buffer; //Swap active buffer
   }

   public void clear() {
      String lines = new String();
      for (int i = 0; i < CLEAR_LINES; i++) {
         lines += "\n";
      }
      System.out.println(lines);
   }
}
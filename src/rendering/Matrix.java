package rendering;
import java.util.*;

public class Matrix<T> {
   private List<T> content;
   
   public int width;
   public int height;
   
   public Matrix(int w, int h, T f) {
      width = w;
      height = h;
      content = new ArrayList<T>();
      this.fill(f);
   }
   
   public void fill(T v) {
      for (int i = 0; i < width * height; i++) {
         if (content.size() <= i) {
            content.add(v); //stupid
         } else {
            content.set(i, v);
         }
      }
   }
   
   public void patch(Matrix<T> other, int px, int py) {
      for (int r = 0; r < other.height && py + r < height; r++) {
         for (int c = 0; c < other.width && px + c < width; c++) {
            set(py + r, px + c, other.get(r, c));
         }
      }
   }
   
   public T get(int r, int c) {
      return content.get(r * width + c);
   }
   
   public void set(int r, int c, T v) {
      content.set(r * width + c, v);
   }
}

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListWorker {
    
    
    Random random = new Random();
    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();
   private Object lock1 = new Object();
    private Object lock2 = new Object();
    
    public void list1DegerEkle() throws InterruptedException{
        synchronized (lock1) {
             Thread.sleep(1);
     list1.add(random.nextInt(100));
        }
       
    }
     public void list2DegerEkle() throws InterruptedException{
         synchronized (lock2) {
              Thread.sleep(1);
     list2.add(random.nextInt(100));
         }
    }
     public void degerAta(){
         try {
             for (int i = 0; i < 1000; i++) {
                 list1DegerEkle();
                 list2DegerEkle();
             }
         } catch (Exception e) {
         }
     } 
     public void calistir(){
         Thread thread1 = new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     degerAta();
                 } catch (Exception e) {
                 }
             
             }
         });
          Thread thread2 = new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     degerAta();
                 } catch (Exception e) {
                 }
             
             }
         });
          long baslangic = System.currentTimeMillis();
          thread1.start();
          thread2.start();
          try {
             thread1.join();
          thread2.join();
         } catch (Exception e) {
         }
          System.out.println("List1 Size : " + list1.size() + " List2 Size: " + list2.size());
          long bitis = System.currentTimeMillis();
          System.out.println("Gecen Sure: " + (bitis-baslangic)+ "ms");
     }
}

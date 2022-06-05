
import java.awt.HeadlessException;
import javax.swing.JFrame;




public class OyunEkrani extends JFrame{

   
    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }
    
    
    
    public static void main(String[] args) {
        
        OyunEkrani ekran = new OyunEkrani("Uzay Oyunu");
        
        
        
        ekran.setResizable(false);   //framin boyutunu değiştirmeye izin vermez
        ekran.setFocusable(false);  //odağı frame e vermez
        
        ekran.setSize(800,600);
        
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //frame kapanınca uygulama kapanır
        
        Oyun oyun = new Oyun();
        
        oyun.requestFocus();   //Odağı panele toplama istediği
        
        oyun.addKeyListener(oyun);  //klavye işlmelerini anlamasini sağlar
        
        oyun.setFocusable(true); //odaği panele verir
        
        oyun.setFocusTraversalKeysEnabled(false);  // klavye islemleriniin algılanmasi ici n gerekli metod
        
        ekran.add(oyun); //ekrana frame oyunu ekledik paneli ekledik.
        
        ekran.setVisible(true);//ekrani gorunur kıl
        
       
        
    }
    
   
    
}


import java.awt.HeadlessException;
import javax.swing.JFrame;




public class OyunEkrani extends JFrame{

   
    public OyunEkrani(String title) throws HeadlessException {
        super(title);
       
        setResizable(false);   //framin boyutunu değiştirmeye izin vermez
        setFocusable(false);  //odağı frame e vermez
        
        setSize(800,600);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //frame kapanınca uygulama kapanır
        
        Oyun oyun = new Oyun();
        
        requestFocus();   //Odağı panele toplama istediği
        
        addKeyListener(oyun);  //klavye işlmelerini anlamasini sağlar
        
        setFocusable(true); //odaği panele verir
        
        setFocusTraversalKeysEnabled(false);  // klavye islemleriniin algılanmasi ici n gerekli metod
        
        add(oyun); //ekrana frame oyunu ekledik paneli ekledik.
        
        setVisible(true);//ekrani gorunur kıl
    }
    
    
}

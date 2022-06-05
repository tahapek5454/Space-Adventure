
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


class Ates{
    
    private int x ;
    private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}



public class Oyun extends JPanel implements KeyListener,ActionListener{
    
    public Timer timer = new Timer(5,this);
   public Timer timer2 = new Timer(2000,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             ateslerD.add(new Ates(topX+15,100));
             mermi();
        }
    });

    private int geceSure = 0;    //Oyundaki Gecen süre degeri
    private int harcananAtes = 0; //oyundaki harcanan ates sayisi
    
    private BufferedImage image; //Oyunda kullanilacak gorsel
    private BufferedImage image2;
    
    private ArrayList <Ates> atesler = new ArrayList <Ates>(); //Birden fazla ates etmek icin listede tutuyoruz
    private ArrayList <Ates> yildizlar = new ArrayList<Ates>();
    public static ArrayList <Ates> ateslerD = new ArrayList <Ates>();
    Random rdn = new Random();
        
    private int atesdirY=8;  //ateslerin birim hareket degeri
    public static int topX = 0;   //topun baslangic noktasi
    private int topdirX =10; //topun birim hareket degeri
    private int uzayGemisiX=0; //uzay gemisi baslangic noktasi
    private int dirUzayX=20;  //uzay gemeisi birim hareket degeri
    private boolean flag = false;

    public Oyun() {
        
        try {
            image =  ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png"))); //gorseli okuduk
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            image2 =  ImageIO.read(new FileImageInputStream(new File("uzaygemisi2.png"))); //gorseli okuduk
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setBackground(Color.black);
    
      timer.start();
      timer2.start();
      music();
       
  
    }
    
    public void mermi()
    {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("mermi.wav"));
            
            Clip clip = AudioSystem.getClip();
            
            clip.open(stream);
            clip.start();
            
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void music()
    {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("music.wav"));
            
            Clip clip = AudioSystem.getClip();
            
            clip.open(stream);
            
            clip.start();
            
            
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean bittiMi()
    {
        for(Ates x :atesler)
        {
            if(new Rectangle(x.getX(),x.getY(),5,5).intersects(new Rectangle(topX,10,20,70)))
            {
                flag=true;
                return true;
            }
             
        }
        for(Ates x : ateslerD)
        {
            if(new Rectangle(x.getX(),x.getY(),5,5).intersects(new Rectangle(uzayGemisiX,490,20,70)))
            {
                flag=false;
                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics g) {   //Gorsellestiricez
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        
        geceSure+=20;
        
        
        g.setColor(Color.red);
        
      // g.fillOval(topX, 0, 20, 20);  //topun olusagi yer metod
      
      
     //   g.fillRect(topX, 10, 20, 20);
        
        g.drawImage(image, uzayGemisiX,490,image.getWidth()/10,image.getHeight()/10, this);
        g.drawImage(image2, topX,10,image.getWidth()/10,image.getHeight()/10, this);
        
        for(int i=0; i<yildizlar.size(); i++)
       {
           if(yildizlar.get(i).getY() > 600)
           {
               yildizlar.remove(i);
           }
           

           
       } 
        
        
        g.setColor(Color.white);
        
        for(int i=0; i<yildizlar.size(); i++)
        {
            g.fillOval(yildizlar.get(i).getX(), yildizlar.get(i).getY(), 2, 2);
            
        }
      
        
     
        
        
       /* for(Ates x : atesler)   //haritayi gecen atesleri silmek icin
        {
            if(x.getY()<0)
            {
                atesler.remove(x);
            }
            
        }*/
       
       for(int i=0; i<atesler.size(); i++)
       {
           if(atesler.get(i).getY() < 0)
           {
               atesler.remove(i);
           }
           
          
       }
        
        g.setColor(Color.blue);
        
        for(Ates x : atesler) // atesler içizer
        {
           
                g.fillRect(x.getX(), x.getY(), 5, 5);
        }
        
        
        
        for(int i=0; i<ateslerD.size(); i++)
       {
           if(ateslerD.get(i).getY() >600)
           {
               ateslerD.remove(i);
           }
           
          
       }
        
        
        g.setColor(Color.red);
        
        for(Ates x : ateslerD) // atesler içizer
        {
           
                g.fillRect(x.getX(), x.getY(), 5, 5);
        }
        
        if(bittiMi() && flag)
        {
            timer.stop();
            timer2.stop();
            
            String message ="Oyun Bitti Kazandiniz...\n"
                    + "Atis Sayisi : "+harcananAtes+"\n"
                    + "Gecen Sure : "+geceSure/1000.0;
            
            JOptionPane.showMessageDialog(this,message);
            
            System.exit(0);
       
          
        }
        else if(bittiMi()&&!flag)
        {
            timer.stop();
            timer2.stop();
            
            String message ="Oyun Bitti Kaybettiniz...\n"
                    + "Atis Sayisi : "+harcananAtes+"\n"
                    + "Gecen Sure : "+geceSure/1000.0;
            
            JOptionPane.showMessageDialog(this,message);
            
            System.exit(0);
        }
        
        


    }

    @Override
    public void repaint() {   //painti tekrar cagiriyor
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int i = e.getKeyCode();
        
        if(i==KeyEvent.VK_LEFT)
        {
            if(uzayGemisiX <= 10)
            {
                uzayGemisiX = 0;
            }
            else{
                uzayGemisiX-=dirUzayX;
            }
        }
        
        else if(i==KeyEvent.VK_RIGHT)
        {
            if(uzayGemisiX >= 740)
            {
                uzayGemisiX = 740;
            }
            else{
                uzayGemisiX+=dirUzayX;
            }
        }
        
        else if(i==KeyEvent.VK_CONTROL)
        {
            atesler.add(new Ates(uzayGemisiX+15,470));
            harcananAtes++;
            mermi();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
           
           
            yildizlar.add(new Ates(rdn.nextInt(750),0));
            for(int i = 0; i<yildizlar.size(); i++)
            {
                yildizlar.get(i).setY(yildizlar.get(i).getY()+10);
            }
         
            for(Ates x :atesler)
            {
                x.setY(x.getY()-atesdirY);
            }
            
            for(Ates x :ateslerD)
            {
                x.setY(x.getY()+atesdirY);
            }
        
            topX+=topdirX;
            
            if(topX >=750)
            {
                topdirX=-topdirX;
            }
            if(topX <=0)
            {
                 topdirX=-topdirX;
            }
            
            repaint();
        
    }
     
}

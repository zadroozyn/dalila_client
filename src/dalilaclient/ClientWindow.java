/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalilaclient;

import HostData.DataSource;
import HostData.DataSourceLinux;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * głowne okno, sprawdza czy system jest obsługiwany i odpala konstruktor 
 * metody tworzącej dane do wysłania
 * po kliknięciu przycisku wysyła
 * @author zadruzyn
 */
public class ClientWindow extends JFrame
{
   private JButton resendBtn;
   private JButton closeBtn;
   private Socket s;
   private DataSource myData;
   private boolean done;
   private String[] ip;
   private JPanel panel;
   
   public ClientWindow(String title){
      this.setTitle(title);
      this.setSize(200,100);
      this.setLocation(700, 300);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(false);
      initComp();
      
   }
   private void initComp(){
      ip  = Conn.getIP();
      panel = new JPanel();
      resendBtn = new JButton("Wyślij");
      closeBtn = new JButton("Zamknij");
      panel.add(resendBtn);
      panel.add(closeBtn);
      this.add(panel);
      resendBtn.addMouseListener(new MouseAdapter()
         {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
               if(!send(evt)) Conn.connExMsg();
            }
         });
      closeBtn.addMouseListener(new MouseAdapter()
         {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
               close();
            }
         });
   }
   private boolean send(MouseEvent evt){
      try {
         String os = System.getProperty("os.name");
         if(os.toLowerCase().contains("linux")||
                 os.toLowerCase().contains("mac"))
                     myData = new DataSourceLinux();
            else{
                    JOptionPane.showMessageDialog(
                            null, "Twój system nie jest obsługiwany");
            }
            
            s = new Socket(ip[0],Integer.parseInt(ip[1]));
         }catch (ConnectException ex){
            return false;
         } catch (IOException ex) {
            Logger.getLogger(DalilaClient.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Nie można uzyskać informacji o dysku");
            return false;
         }
       return Conn.sendData(myData, s);
   }
   private void close(){
      System.exit(0);
   }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalilaclient;

import HostData.DataSource;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * helperowa klasa do obsługi połacenia
 * @author zadruzyn
 */
public class Conn
{
   public static String[] getIP(){
       String ip =
              JOptionPane.showInputDialog(
                      null, "Podaj IP serwera i port oddzielone znakiem ':'", 
                      "IP Serwera", JOptionPane.QUESTION_MESSAGE);
       String[] out = null;
       try{
         out = ip.split(":");
       }catch (NullPointerException e){
          System.exit(0);
       }
       if(out.length==1){
          if(out[0].isEmpty()) return new String[]{"localhost","8189"};
          else return new String[]{out[0],"8189"};
       } 
       return out;
   }
   public static boolean sendData(DataSource myData, Socket s){
      try {
         ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
         out.writeObject(myData);
         out.close();
      } catch (IOException ex) {
         return false;
      } catch (NullPointerException ex){
         return false;
      }
      return true;
   }  
   public static void connExMsg(){
      JOptionPane.showMessageDialog(null, "Brak połaczenia z serwerem");
   }
}

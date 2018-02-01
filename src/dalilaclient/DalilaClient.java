/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dalilaclient;
import HostData.*;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author zadruzyn
 */
public class DalilaClient{

   /**
    * Jedyne co robi to odpala okienko
    * @param args the command line arguments
    * 
    */
   public static void main(String[] args) {
      final JFrame window = new ClientWindow("Dalila Client");
      EventQueue.invokeLater(new Runnable(){
         @Override
         public void run(){
            window.setVisible(true);
         }
      });
   }
   
   
}

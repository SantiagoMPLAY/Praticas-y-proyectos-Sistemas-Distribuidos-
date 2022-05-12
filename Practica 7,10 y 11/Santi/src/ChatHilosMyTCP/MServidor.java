/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatHilosMyTCP;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MServidor extends Thread{
private int port;
private JFrame ventana;
    public MServidor( JFrame ventana,int port) {
        this.port=port;
        this.ventana=ventana;
    }
    public void run(){
        ServerSocket ss=null;
        try{
            ss= new ServerSocket(port);
            while(true){
              Socket s=ss.accept();
        MSGestorConexiones.getInstance().conectaNuevo(new MSConexion(s));
            }
            //JOptionPane.showMessageDialog(ventana,"Se ha conectado");
        }catch(Exception ex){
 JOptionPane.showMessageDialog(ventana,"Error al abrir el puerto, "
         + "posiblemente ya esta ");
        }try{
            ss.close();
        }catch(Exception e){
            
        }
    }
}

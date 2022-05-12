package ChatHilosMyTCP;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
public class MCliente extends Thread {
    private int port;
    private Socket s;
    private String url;
    VCliente ventana;
    private boolean bConectado;
    private String nick;
   public MCliente(int port, String url,String nick, VCliente ventana) {
        this.port = port;
        this.url = url;
        this.ventana = ventana;
        this.nick=nick;
    }
        public void run(){
         try{      
             s=new Socket(url,port);
             DataInputStream dis = new DataInputStream(s.getInputStream());
             enviarTrama(1,nick);
             bConectado=true;
             while(bConectado){
                 int nCodigo=dis.readInt();
               String sTrama=dis.readUTF();
               switch(nCodigo){
                   case 1:
                       ventana.nuevaPersona(sTrama);
                       break;
                   case 2:
                       ventana.mensajeRecibido(sTrama);
                       break;
                   case 3:
                       try{
                           int nPos=Integer.parseInt(sTrama);
                           ventana.borrarPersona(nPos);
                       }catch(Exception e2){
                       }
                       break;
               }
             }          
      JOptionPane.showMessageDialog(ventana,"Se ha podido conectar");
        }catch(Exception e){
 JOptionPane.showMessageDialog(ventana,"No se pudo establecer la conexion");
        }
    }
    public void enviarMensaje(String sMensaje){
        enviarTrama(2,sMensaje);
    }
    public void enviarTrama(int nCodigo,String sTrama){
        try{
            DataOutputStream dos=new  DataOutputStream(s.getOutputStream());
            dos.writeInt(nCodigo);
            dos.writeUTF(sTrama);
         }catch(Exception e){
  JOptionPane.showMessageDialog(ventana,"No se pudo establecer la conexion");
        }
    }
}

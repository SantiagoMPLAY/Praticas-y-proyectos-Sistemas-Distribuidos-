
package ChatHilosMyTCP;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MSConexion extends Thread {
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String nick;
     public MSConexion(Socket s) throws IOException {
        try{
        this.s = s;
        dis=new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
        start();
        }catch(Exception e){
            
        }
    }
    public String getNick(){
        return nick;
    }
    public void run(){
        while (true){
            try {
                int nCodigo=dis.readInt();
                String sTrama=dis.readUTF();
                switch(nCodigo){
                    case 1:
                     nick=sTrama;
           MSGestorConexiones.getInstance().enviarTrama(nCodigo,sTrama);
                   break;
                   case 2:
                sTrama="<"+nick+"> "+sTrama;
             MSGestorConexiones.getInstance().enviarTrama(nCodigo,sTrama);
                  break;
                 case 3:
                    MSGestorConexiones.getInstance().desconecta(this);
                }
            } catch (Exception e) {
              
            }
        }
    }
     public void enviarTrama(int nCodigo,String sTrama){
         try{
             dos.writeInt(nCodigo);
             dos.writeUTF(sTrama);
         }catch(Exception e){
             
         }
     }
}

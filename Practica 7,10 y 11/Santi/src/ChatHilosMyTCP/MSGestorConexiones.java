package ChatHilosMyTCP;
import java.util.ArrayList;
public class MSGestorConexiones {
    private static MSGestorConexiones singleton=new MSGestorConexiones();
 public static MSGestorConexiones getInstance(){
     return singleton;
 }
    private ArrayList<MSConexion> conexiones = new ArrayList<MSConexion>();
    public void  enviarTrama(int nCodigo,String sTrama) {
        for (MSConexion ns:conexiones){
            ns.enviarTrama(nCodigo,sTrama);
        }
    }
     public void conectaNuevo(MSConexion nuevo){
         for(MSConexion ns:conexiones){
             nuevo.enviarTrama(1,ns.getNick());
         }
         conexiones.add(nuevo);
     }
    public void desconecta(MSConexion eliminar){
        int nPos=-1;
        for(int n=0;n<conexiones.size();n++){
            if(conexiones.get(n)==eliminar){
                nPos=n;
            }
        }if(nPos!=-1){
            for(int n=0;n<conexiones.size();n++){
                if(n!=nPos){
                    conexiones.get(n).enviarTrama(3,""+nPos);
            }
        }
        conexiones.remove(nPos);
        }
     }
}

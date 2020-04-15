
package paq_matricula;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

public class OpcionAnulacionCredito extends Pantalla{
    
        private Tabla tab_opcion_anulacion_credito = new Tabla();//instanciar tabla del framework
        
     public OpcionAnulacionCredito() {//constructor
       tab_opcion_anulacion_credito.setId("tab_opcion_anulacion_credit ");// todo objeto instanciado poner id 
        tab_opcion_anulacion_credito.setTabla("yavirac_matri_opci_anul_cred", "ide_ymaoac", 1);  // nombre de la base de datos ii la clave primaria
       tab_opcion_anulacion_credito.setHeader("OPCIÓN ANULACIÓN CRÉDITO");
        tab_opcion_anulacion_credito.dibujar();//dibuja la tabla

        PanelTabla pa_opcion_anulacion_credito = new PanelTabla();//intanciamos el panel del framework
        pa_opcion_anulacion_credito.setId("pa_opcion_anulacion_credito");//nombre id
        pa_opcion_anulacion_credito.setPanelTabla(tab_opcion_anulacion_credito);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_opcion_anulacion_credito =new Division ();//instanciamos
        div_opcion_anulacion_credito.setId("div_opcion_anulacion_credito");//es un idientificador
        div_opcion_anulacion_credito.dividir1(tab_opcion_anulacion_credito);
        
        agregarComponente(div_opcion_anulacion_credito);//agregar componente
    }
 @Override
    public void insertar() {
        tab_opcion_anulacion_credito.insertar();
    }

    @Override
    public void guardar() {
        tab_opcion_anulacion_credito.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_opcion_anulacion_credito.eliminar();
    }
    
    //generar geter and seter

    public Tabla getTab_opcion_anulacion_credito() {
        return tab_opcion_anulacion_credito;
    }

    public void setTab_opcion_anulacion_credito(Tabla tab_opcion_anulacion_credito) {
        this.tab_opcion_anulacion_credito = tab_opcion_anulacion_credito;
    }

    
   
}
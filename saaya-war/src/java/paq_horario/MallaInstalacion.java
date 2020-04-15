package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Jenny
 */
   public class MallaInstalacion extends Pantalla {
   private Tabla tab_malla_instalacion = new Tabla();
    
    public MallaInstalacion(){
    tab_malla_instalacion.setId("tab_malla_instalacion");   //identificador
    tab_malla_instalacion.setTabla("yavirac_hora_malla_instalacion", "ide_yhomin", 1);
    tab_malla_instalacion.dibujar();
       
        
        PanelTabla pat_malla_instalacion = new PanelTabla();
        pat_malla_instalacion.setId("pat_malla_instalacion");
        pat_malla_instalacion.setPanelTabla(tab_malla_instalacion);
        Division div_malla_instalacion = new Division();
        div_malla_instalacion.setId("div_malla_instalacion");
        div_malla_instalacion.dividir1(pat_malla_instalacion);
        agregarComponente(div_malla_instalacion); 
        
    }

    @Override
    public void insertar() {
        tab_malla_instalacion.insertar();
    }

    @Override
    public void guardar() {
        tab_malla_instalacion.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_malla_instalacion.eliminar();
    }

    public Tabla getTab_malla_instalacion() {
        return tab_malla_instalacion;
    }

    public void setTab_malla_instalacion(Tabla tab_malla_instalacion) {
        this.tab_malla_instalacion = tab_malla_instalacion;
    }

    



 

    
}
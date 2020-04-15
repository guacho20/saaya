package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Jenny
 */
   public class TipoAdecuacion extends Pantalla {
   private Tabla tab_tipo_adecuacion = new Tabla();
    
    public TipoAdecuacion(){
    tab_tipo_adecuacion.setId("tab_tipo_adecuacion");   //identificador
    tab_tipo_adecuacion.setTabla("yavirac_hora_tipo_adecuacion", "ide_yhotad", 1);
    tab_tipo_adecuacion.getColumna("ide_yhotad").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_tipo_adecuacion.getColumna("descripcion_yhotad").setNombreVisual("DESCRIPCIÓN");
    tab_tipo_adecuacion.dibujar();
       
        
        PanelTabla pat_tipo_adecuacion = new PanelTabla();
        pat_tipo_adecuacion.setId("pat_tipo_adecuacion");
        pat_tipo_adecuacion.setPanelTabla(tab_tipo_adecuacion);
        Division div_tipo_adecuacion = new Division();
        div_tipo_adecuacion.setId("div_tipo_adecuacion");
        div_tipo_adecuacion.dividir1(pat_tipo_adecuacion);
        agregarComponente(div_tipo_adecuacion); 
        
    }

    @Override
    public void insertar() {
        tab_tipo_adecuacion.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_adecuacion.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_adecuacion.eliminar();
    }

    public Tabla getTab_tipo_adecuacion() {
        return tab_tipo_adecuacion;
    }

    public void setTab_tipo_adecuacion(Tabla tab_tipo_adecuacion) {
        this.tab_tipo_adecuacion = tab_tipo_adecuacion;
    }

    



 

    
}

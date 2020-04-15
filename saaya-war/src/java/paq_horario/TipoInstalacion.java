package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;



public class TipoInstalacion extends Pantalla {
   private Tabla tab_tipo_instalacion = new Tabla();
    
    public TipoInstalacion(){
    tab_tipo_instalacion.setId("tab_tipo_instalacion");   //identificador
    tab_tipo_instalacion.setTabla("yavirac_stror_tipo_instalacion", "ide_ysttin", 1);
    tab_tipo_instalacion.getColumna("ide_ysttin").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_tipo_instalacion.getColumna("descripcion_ysttin").setNombreVisual("DESCRIPCIÓN");
    tab_tipo_instalacion.dibujar();
       
        
        PanelTabla pat_tipo_instalacion = new PanelTabla();
        pat_tipo_instalacion.setId("pat_tipo_instalacion");
        pat_tipo_instalacion.setPanelTabla(tab_tipo_instalacion);
        Division div_tipo_instalacion = new Division();
        div_tipo_instalacion.setId("div_tipo_instalacion");
        div_tipo_instalacion.dividir1(pat_tipo_instalacion);
        agregarComponente(div_tipo_instalacion); 
        
    }

    @Override
    public void insertar() {
        tab_tipo_instalacion.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_instalacion.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_instalacion.eliminar();
    }

    public Tabla getTab_tipo_instalacion() {
        return tab_tipo_instalacion;
    }

    public void setTab_tipo_instalacion(Tabla tab_tipo_instalacion) {
        this.tab_tipo_instalacion = tab_tipo_instalacion;
    }

 
}
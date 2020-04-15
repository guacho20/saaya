
package paq_estructura;

import framework.componentes.Division;
import framework.componentes.Imagen;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

public class Modalidad extends Pantalla {
    private Tabla tab_stror_modalidad = new Tabla();
    
    public Modalidad(){
         
        
    tab_stror_modalidad .setId("tab_stror_modalidad");   //identificador
    tab_stror_modalidad.setHeader("MODALIDADES DE ESTUDIO");
    tab_stror_modalidad .setTabla("yavirac_stror_modalidad", "ide_ystmod", 1);
    tab_stror_modalidad.getColumna("ide_ystmod").setNombreVisual("CÓDIGO");
    tab_stror_modalidad.getColumna("descripcion_ystmod").setNombreVisual("DESCRIPCIÓN");
    tab_stror_modalidad.getColumna("activo_ystmod").setNombreVisual("ACTIVO");
    
    tab_stror_modalidad .dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
     
        
        PanelTabla pat_stror_modalidad = new PanelTabla();
          
        pat_stror_modalidad.setId("pat_stror_modalidad");
        pat_stror_modalidad.setPanelTabla(tab_stror_modalidad);
        Division div_stror_modalidad = new Division();
        div_stror_modalidad.setId("div_stror_modalidad");
        div_stror_modalidad.dividir1(pat_stror_modalidad);
        agregarComponente(div_stror_modalidad); 
        
        Imagen modalidadIma = new Imagen();
        modalidadIma.setValue("imagenes/logocabeceras.png");
        pat_stror_modalidad.setWidth("100%");
        pat_stror_modalidad.setHeader(modalidadIma);
    }

    @Override
    public void insertar() {
        tab_stror_modalidad.insertar();
    }

    @Override
    public void guardar() {
        tab_stror_modalidad.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_stror_modalidad.eliminar();
    }

    public Tabla getTab_stror_modalidad() {
        return tab_stror_modalidad;
    }

    public void setTab_stror_modalidad(Tabla tab_stror_modalidad) {
        this.tab_stror_modalidad = tab_stror_modalidad;
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
/**
 *
 * @author lenovo
 */
public class NivelEducacion extends Pantalla{
 Tabla tab_nivel_educacion= new Tabla(); 
    public NivelEducacion(){
        tab_nivel_educacion.setId("tab_nivel_educacion");
        
        tab_nivel_educacion.setTabla("yavirac_stror_nivel_educacion","ide_ystnie",1);
        tab_nivel_educacion.getColumna("ide_ystnie").setNombreVisual("CÓDIGO");
        tab_nivel_educacion.getColumna("descripcion_ystnie").setNombreVisual("DESCRIPCIÓN");
        tab_nivel_educacion.getColumna("abreviatura_ystnie").setNombreVisual("ABREVIATURA");
        tab_nivel_educacion.getColumna("orden_ystnie").setNombreVisual("ORDEN");
        tab_nivel_educacion.dibujar();
        
        PanelTabla pa_nivel_educacion = new PanelTabla();
        pa_nivel_educacion.setId("pa_nivel_educacion");
        pa_nivel_educacion.setPanelTabla(tab_nivel_educacion);
        
        Division div_nivel_educacion = new Division();
        div_nivel_educacion.setId("div_nivel_educacion");
        div_nivel_educacion.dividir1(pa_nivel_educacion);
        agregarComponente(div_nivel_educacion);
             agregarComponente(div_nivel_educacion);
}
@Override
    public void insertar() {
        tab_nivel_educacion.insertar();
    }
        @Override
    public void guardar() {
        tab_nivel_educacion.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_nivel_educacion.eliminar();
                 
    }


    public Tabla getTab_nivel_educacion() {
        return tab_nivel_educacion;
    }

    public void setTab_nivel_educacion(Tabla tab_nivel_educacion) {
        this.tab_nivel_educacion= tab_nivel_educacion;
    }
    }


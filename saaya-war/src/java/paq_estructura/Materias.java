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
public class Materias extends Pantalla{
 Tabla tab_materias= new Tabla(); 
    public Materias(){
        tab_materias.setId("tab_materias");
        
         tab_materias.setTabla("yavirac_stror_materia","ide_ystmat",1);
         tab_materias.getColumna("ide_ystmat").setNombreVisual("CÓDIGO");
         tab_materias.getColumna("detalle_ystmat").setNombreVisual("DETALLE");
         tab_materias.getColumna("abreviatura_ystmat").setNombreVisual("ABREVIATURA");
       
         tab_materias.dibujar();
        
        PanelTabla pa_materias = new PanelTabla();
        pa_materias.setId("pa_materias");
        pa_materias.setPanelTabla(tab_materias);
        
        Division div_materias = new Division();
        div_materias.setId("div_materias");
        div_materias.dividir1(pa_materias);
        agregarComponente(div_materias);
             agregarComponente(div_materias);
}
@Override
    public void insertar() {
        tab_materias.insertar();
    }
        @Override
    public void guardar() {
        tab_materias.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_materias.eliminar();
                 
    }


    public Tabla getTab_materias() {
        return tab_materias;
    }

    public void setTab_materias(Tabla tab_materias) {
        this.tab_materias= tab_materias;
    }
    }



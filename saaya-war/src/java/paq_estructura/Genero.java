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
 * @author ITSY
 */
public class Genero extends Pantalla{
    Tabla tab_genero = new Tabla();
    public Genero (){
        tab_genero.setId ("tab_genero");
        tab_genero.setTabla ("yavirac_stror_genero","ide_ystgen",1);
        tab_genero.getColumna("ide_ystgen").setNombreVisual("CÓDIGO");
        tab_genero.getColumna("descripcion_ystgen").setNombreVisual("DESCRIPCIÓN");
        tab_genero.getColumna("activo_ystgen").setNombreVisual("ACTIVO");
        tab_genero.dibujar();
        
        PanelTabla pa_genero = new PanelTabla ();
        pa_genero.setId ("pa_genero");
        pa_genero.setPanelTabla(tab_genero);
        
        Division div_genero = new Division();
        div_genero.setId("div_genero");
        div_genero.dividir1(pa_genero);
        
        agregarComponente(pa_genero);    
    }

    public Tabla getTab_genero() {
        return tab_genero;
    }

    public void setTab_genero(Tabla tab_genero) {
        this.tab_genero = tab_genero;
    }

    @Override
    public void insertar() {
        tab_genero.insertar(); 
    }

    @Override
    public void guardar() {
        tab_genero.guardar();
        guardarPantalla(); 
    }

    @Override
    public void eliminar() {
        tab_genero.eliminar();
    }
    
}


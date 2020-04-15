/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_alumno;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author RICARDO COLLAHUAZO
 */
public class Ocupacion extends Pantalla{
    Tabla tab_ocupacion=new Tabla();
public Ocupacion(){
    tab_ocupacion.setId("tab_ocupacion");
    tab_ocupacion.setTabla("yavirac_alum_ocupacion", "ide_yalocu", 1);
    tab_ocupacion.dibujar();
    
    PanelTabla pat_ocupacion=new PanelTabla();
    pat_ocupacion.setId("pat_ocupacion");
    pat_ocupacion.setPanelTabla(tab_ocupacion);
    
    Division div_ocupacion=new Division();
    div_ocupacion.setId("div_ocupacion");
    div_ocupacion.dividir1(pat_ocupacion);
    
    agregarComponente(div_ocupacion);
}
    @Override
    public void insertar() {
        tab_ocupacion.insertar();
    }

    @Override
    public void guardar() {
        tab_ocupacion.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_ocupacion.eliminar();
    }

    public Tabla getTab_ocupacion() {
        return tab_ocupacion;
    }

    public void setTab_ocupacion(Tabla tab_ocupacion) {
        this.tab_ocupacion = tab_ocupacion;
    }
    
    
}

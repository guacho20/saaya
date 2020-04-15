/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ANDRES
 */
public class FuncionAsignada extends Pantalla{
    
    private Tabla tab_funcion_asignada = new Tabla();
    public FuncionAsignada () {
        tab_funcion_asignada.setId("tab_funcion_asignada");
        tab_funcion_asignada.setTabla("yavirac_titu_funcion_asignada","ide_ytifua", 1);
        tab_funcion_asignada.setHeader("FuncionAsignada");
        tab_funcion_asignada.dibujar();
        
         PanelTabla pat_line= new PanelTabla();
        pat_line.setId("pat_line");
        pat_line.setPanelTabla(tab_funcion_asignada);
        
       Division div_funcion_asignada = new Division();
        div_funcion_asignada.setId("div_funcion_asignada");
        div_funcion_asignada.dividir1(pat_line);
        
        agregarComponente(div_funcion_asignada);
        
    }
                 

    @Override
    public void insertar() {
        tab_funcion_asignada.insertar();
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        tab_funcion_asignada.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_funcion_asignada.eliminar();
    }

    public Tabla getTab_funcion_asignada() {
        return tab_funcion_asignada;
    }

    public void setTab_funcion_asignada(Tabla tab_funcion_asignada) {
        this.tab_funcion_asignada = tab_funcion_asignada;
    }



}

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
public class Indicador extends Pantalla{
    private Tabla tab_indicador = new Tabla ();
    public Indicador (){
        
        tab_indicador.setId("tab_indicador");
        tab_indicador.setTabla("yavirac_titu_indicador","ide_ytiind", 1);
        tab_indicador.setHeader("Indicador");
        tab_indicador.dibujar();        
        PanelTabla pat_line= new PanelTabla();
        pat_line.setId("pat_line");
        pat_line.setPanelTabla(tab_indicador);
        
       Division div_indicador = new Division();
        div_indicador.setId("div_indicador");
        div_indicador.dividir1(pat_line);
        
        agregarComponente(div_indicador);
        
    }
       

    @Override
    public void insertar() {
        tab_indicador.insertar();
    }

    @Override
    public void guardar() {
        tab_indicador.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_indicador.eliminar();
       
    }

    public Tabla getTab_indicador() {
        return tab_indicador;
    }

    public void setTab_indicador(Tabla tab_indicador) {
        this.tab_indicador = tab_indicador;
    }
    
    
    
    
    
    
    
    
}

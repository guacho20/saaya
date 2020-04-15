/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

/**
 *
 * @author ANDRES
 */

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import javax.ejb.EJB;
import paq_nota.ejb.ServicioNotas;
import servicios.sistema.ServicioSeguridad;
import sistema.aplicacion.Pantalla;

public class Anio extends Pantalla {
    Tabla tab_tabla1 = new Tabla(); // importar tabla    
    
    public Anio(){
        tab_tabla1.setId("tab_tabla1");// todo objeto instanciado poner id 
        tab_tabla1.setTabla("yavirac_stror_anio","ide_ystani",1);  // nom bdd
        tab_tabla1.getColumna("ide_ystani").setNombreVisual("CÓDIGO");
        tab_tabla1.getColumna("descripcion_ystani").setNombreVisual("DESCRIPCIÓN");
        tab_tabla1.getColumna("ide_ystani").setNombreVisual("AÑO");
        tab_tabla1.dibujar();
        
        PanelTabla pa_pat1 = new PanelTabla();
        pa_pat1.setId("pa_pat1");//nombre id
        pa_pat1.setPanelTabla(tab_tabla1);
        
        Division div_div =new Division ();
       div_div.setId("div_div");
       div_div.dividir1(tab_tabla1);  
       agregarComponente(div_div);
    }
    @Override
    public void insertar() {
        tab_tabla1.insertar(); 
    }

    @Override
    public void guardar() {
        tab_tabla1.guardar();
        guardarPantalla(); 
    }

    @Override
    public void eliminar() {
        tab_tabla1.eliminar();
    }

    public Tabla getTab_tabla1() {
        return tab_tabla1;
    }

    public void setTab_tabla1(Tabla tab_tabla1) {
        this.tab_tabla1 = tab_tabla1;
    }
    
}

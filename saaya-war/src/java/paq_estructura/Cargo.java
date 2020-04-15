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
public class Cargo extends Pantalla {
    Tabla tab_cargo = new Tabla(); // importar tabla    
            
    public Cargo (){// constructor
        tab_cargo.setId("tab_cargo");// todo objeto instanciado poner id 
        tab_cargo.setTabla("yavirac_stror_cargo","ide_ystcar",1);  // nom bdd
        tab_cargo.getColumna("ide_ystcar").setNombreVisual("CÓDIGO");
        tab_cargo.getColumna("descripcion_ystcar").setNombreVisual("DESCRIPCIÓN");
        tab_cargo.getColumna("activo_ystcar").setNombreVisual("ACTIVO");
        tab_cargo.dibujar();
        
        PanelTabla pa_cargo = new PanelTabla();
        pa_cargo.setId("pa_cargo");//nombre id
        pa_cargo.setPanelTabla(tab_cargo);
        
        Division div_cargo =new Division ();
       div_cargo.setId("div_cargo");
       div_cargo.dividir1(tab_cargo);  
       agregarComponente(div_cargo); 
}

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_cargo() {
        return tab_cargo;
    }

    public void setTab_cargo(Tabla tab_cargo) {
        this.tab_cargo = tab_cargo;
    }
    
}

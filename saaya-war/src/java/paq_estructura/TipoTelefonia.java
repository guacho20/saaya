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
public class TipoTelefonia extends Pantalla {
     Tabla tab_tipo_telefonia = new Tabla(); // importar tabla    
            
    public TipoTelefonia (){// constructor
        tab_tipo_telefonia.setId("tab_tipo_telefonia");// todo objeto instanciado poner id 
        tab_tipo_telefonia.setTabla("yavirac_stror_tipo_telefonia","ide_ysttit",1);  // nom bdd
        tab_tipo_telefonia.dibujar();
        
        PanelTabla pa_tipo_telefonia = new PanelTabla();
        pa_tipo_telefonia.setId("pa_tipo_telefonia");//nombre id
        pa_tipo_telefonia.setPanelTabla( tab_tipo_telefonia);
        
        Division div_tipo_telefonia =new Division ();
       div_tipo_telefonia.setId("div_tipo_telefonia");
       div_tipo_telefonia.dividir1(pa_tipo_telefonia);  
       agregarComponente(div_tipo_telefonia); 
}
    @Override
    public void insertar() {
        tab_tipo_telefonia.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_telefonia.guardar();
                guardarPantalla();
    }

    @Override
    public void eliminar(){ 
        tab_tipo_telefonia.eliminar();
  
  
        
    }

    public Tabla getTab_tipo_telefonia() {
        return tab_tipo_telefonia;
    }

    public void setTab_tipo_telefonia(Tabla tab_tipo_telefonia) {
        this.tab_tipo_telefonia = tab_tipo_telefonia;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla; 
import paq_estructura.DivisionPolitica;
import sistema.aplicacion.Pantalla;


/**
 *
 * @author ANDRES
 */
public class TipoPersonaVinculacion extends Pantalla{
    private Tabla tab_tipo_persona_vinculacion = new Tabla ();
   
//tabla de objetos/
    
    public TipoPersonaVinculacion(){
        
        tab_tipo_persona_vinculacion.setId("tab_tipo_persona_vinculacion");
        tab_tipo_persona_vinculacion.setTabla("yavirac_titu_tipo_per_vinc", "ide_ytitpv",1);
        tab_tipo_persona_vinculacion.setHeader("CARGO DE PERSONA RESPONSABLE");
        tab_tipo_persona_vinculacion.dibujar();
        
      
        PanelTabla pat_tab_tipo_persona_vinculacion = new PanelTabla();
        pat_tab_tipo_persona_vinculacion.setId("pat_tab_tipo_persona_vinculacion");
        pat_tab_tipo_persona_vinculacion.setPanelTabla(tab_tipo_persona_vinculacion);
        
        Division div_tipo_persona_vinculacion  = new Division();
        div_tipo_persona_vinculacion.setId("di_tipo_persona_vinculacion");
        div_tipo_persona_vinculacion.dividir1(pat_tab_tipo_persona_vinculacion);
        
        agregarComponente(div_tipo_persona_vinculacion);
      

//tabla de mi base de datos/         
    } 

    @Override
    public void insertar() {
        tab_tipo_persona_vinculacion.insertar();
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        tab_tipo_persona_vinculacion.guardar();
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        tab_tipo_persona_vinculacion.eliminar();
        //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_tipo_persona_vinculacion() {
        return tab_tipo_persona_vinculacion;
    }

    public void setTab_tipo_persona_vinculacion(Tabla tab_tipo_persona_vinculacion) {
        this.tab_tipo_persona_vinculacion = tab_tipo_persona_vinculacion;
    }
    



    
    
}

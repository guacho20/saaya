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
 * @author RICARDO
 */
public class TipoTratamiento extends Pantalla{

    Tabla tab_tipo_tratamiento=new Tabla();
    
public TipoTratamiento(){
    
    tab_tipo_tratamiento.setId("tab_tipo_tratamiento");
    tab_tipo_tratamiento.setTabla("yavirac_alum_tipo_tratamiento","ide_yaltit",1);
    tab_tipo_tratamiento.dibujar();
    
    PanelTabla pat_tipo_tratamiento=new PanelTabla();
    pat_tipo_tratamiento.setId("pat_tipo_tratamiento");
    pat_tipo_tratamiento.setPanelTabla(tab_tipo_tratamiento);
    
    Division div_tipo_tratamiento=new Division();
    div_tipo_tratamiento.setId("div_tipo_tratamiento");
    div_tipo_tratamiento.dividir1(pat_tipo_tratamiento);
    
    agregarComponente(div_tipo_tratamiento);
    
}
            
    @Override
    public void insertar() {
        tab_tipo_tratamiento.insertar();
    }

    @Override
    public void guardar() {
       tab_tipo_tratamiento.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_tratamiento.eliminar();
    }

    public Tabla getTab_tipo_tratamiento() {
        return tab_tipo_tratamiento;
    }

    public void setTab_tipo_tratamiento(Tabla tab_tipo_tratamiento) {
        this.tab_tipo_tratamiento = tab_tipo_tratamiento;
    }
    
}

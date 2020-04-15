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
public class TipoVivienda extends Pantalla {
    Tabla tab_tipo_vivienda=new Tabla();

    public TipoVivienda(){
    
    tab_tipo_vivienda.setId("tab_tipo_vivienda");
    tab_tipo_vivienda.setTabla("yavirac_alum_tipo_vivienda", "ide_yaltiv", 1);
    tab_tipo_vivienda.dibujar();
    
        PanelTabla pat_tipo_vivienda=new PanelTabla();
        pat_tipo_vivienda.setId("pat_tipo_vivienda");
        pat_tipo_vivienda.setPanelTabla(tab_tipo_vivienda);
        
        Division div_tipo_vivienda=new Division();
        div_tipo_vivienda.setId("div_tipo_vivienda");
        div_tipo_vivienda.dividir1(pat_tipo_vivienda);
        
        agregarComponente(div_tipo_vivienda);
       
}
    @Override
    public void insertar() {
     tab_tipo_vivienda.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_vivienda.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_vivienda.eliminar();
    }

    public Tabla getTab_tipo_vivienda() {
        return tab_tipo_vivienda;
    }

    public void setTab_tipo_vivienda(Tabla tab_tipo_vivienda) {
        this.tab_tipo_vivienda = tab_tipo_vivienda;
    }
    
}

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
public class Gasto extends Pantalla {
     Tabla tab_gasto=new Tabla();
    
public Gasto(){
    
    tab_gasto.setId("tab_gasto");
    tab_gasto.setTabla("yavirac_alum_gasto","ide_yalgas",1);
    tab_gasto.dibujar();
    
    PanelTabla pat_gasto=new PanelTabla();
    pat_gasto.setId("pat_gasto");
    pat_gasto.setPanelTabla(tab_gasto);
    
    Division div_gasto=new Division();
    div_gasto.setId("div_gasto");
    div_gasto.dividir1(pat_gasto);
    
    agregarComponente(div_gasto);
    
}
            
    @Override
    public void insertar() {
        tab_gasto.insertar();
    }

    @Override
    public void guardar() {
       tab_gasto.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_gasto.eliminar();
    }

    public Tabla getTab_gasto() {
        return tab_gasto;
    }

    public void setTab_gasto(Tabla tab_gasto) {
        this.tab_gasto = tab_gasto;
    }
    
}

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
public class AreaAplicaVinculacion extends Pantalla {
    private Tabla tab_area_aplica_vincula = new Tabla ();
    public AreaAplicaVinculacion () {
   tab_area_aplica_vincula.setId("tab_area_aplica_vincula");
   tab_area_aplica_vincula.setTabla("yavirac_titu_area_aplica_vincula","ide_ytiara",1);
   tab_area_aplica_vincula.setHeader("Area Aplica Vinculacion");
   tab_area_aplica_vincula.dibujar();
             
        
        
        PanelTabla pat_line= new PanelTabla();
        pat_line.setId("pat_line");
        pat_line.setPanelTabla(tab_area_aplica_vincula);
        
        Division div_area_aplica_vincula = new Division();
        div_area_aplica_vincula.setId("div_area_aplica_vincula");
        div_area_aplica_vincula.dividir1(pat_line);
        
        agregarComponente(div_area_aplica_vincula);
        
               
    }

    @Override
    public void insertar() {
        tab_area_aplica_vincula.insertar();

    }

    @Override
    public void guardar() {
       tab_area_aplica_vincula.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_area_aplica_vincula.eliminar(); 
    }
    

    public Tabla getTab_area_aplica_vincula() {
        return tab_area_aplica_vincula;
    }

    public void setTab_area_aplica_vincula(Tabla tab_area_aplica_vincula) {
        this.tab_area_aplica_vincula = tab_area_aplica_vincula;
    }
    
     
}

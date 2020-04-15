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
public class ParentescoFamiliar extends Pantalla {
    Tabla tab_parentesco_familiar = new Tabla ();
            public ParentescoFamiliar  (){
               tab_parentesco_familiar.setId ("tab_parentesco_familiar");
               tab_parentesco_familiar.setTabla ("yavirac_stror_parentezco_fami","ide_ystpaf",1);
               tab_parentesco_familiar.getColumna("ide_ystpaf").setNombreVisual("CÓDIGO");
               tab_parentesco_familiar.getColumna("descripcion_ystpaf").setNombreVisual("DESCRIPCIÓN");
               tab_parentesco_familiar.getColumna("activo_ystpaf").setNombreVisual("ACTIVO");
               tab_parentesco_familiar.dibujar();
               
                PanelTabla pa_parentesco_familiar =new PanelTabla ();
                pa_parentesco_familiar.setId ("pa_parentesco_familiar");
                pa_parentesco_familiar.setPanelTabla (tab_parentesco_familiar);
                
                Division div_parentesco_familiar = new Division();
        div_parentesco_familiar.setId("div_parentesco_familiar");
        div_parentesco_familiar.dividir1(pa_parentesco_familiar);
        
        agregarComponente(pa_parentesco_familiar); 
               
            }

    @Override
    public void insertar() {
        tab_parentesco_familiar.insertar();
    }

    @Override
    public void guardar() {
        tab_parentesco_familiar.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_parentesco_familiar.eliminar();
                 
    }

    public Tabla getTab_parentesco_familiar() {
        return tab_parentesco_familiar;
    }

    public void setTab_parentesco_familiar(Tabla tab_parentesco_familiar) {
        this.tab_parentesco_familiar = tab_parentesco_familiar;
    }
    
}

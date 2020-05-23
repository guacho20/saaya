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
 * @author Amanda
 */
public class TipoCorreo extends Pantalla{
     Tabla tab_tipo_correo = new Tabla();// importar tabla
    public TipoCorreo(){//constructor
    tab_tipo_correo.setId("tab_tipo_correo");// todo objeto instanciado poner id
    tab_tipo_correo.setTabla("yavirac_stror_tipo_correo","ide_ysttoc",1); // nom bdd
    tab_tipo_correo.getColumna("ide_ysttoc").setNombreVisual("CÓDIGO");
    tab_tipo_correo.getColumna("descripcion_ysttic").setNombreVisual("DESCRIPCIÓN");
    tab_tipo_correo.getColumna("activo_ystic").setNombreVisual("ACTIVO");
    tab_tipo_correo.dibujar();
            
    PanelTabla pa_tipo_correo = new PanelTabla();
    pa_tipo_correo.setId("pa_tipo_correo");
    pa_tipo_correo.setPanelTabla(tab_tipo_correo);
    
    Division div_tipo_correo = new Division();
    div_tipo_correo.setId("div_tipo_correo");
    div_tipo_correo.dividir1(pa_tipo_correo);
    
    agregarComponente(div_tipo_correo);   
                
    }
    
 @Override
    public void insertar() {
        tab_tipo_correo.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_correo.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_tipo_correo.eliminar();
                 
    }


    public Tabla getTab_tipo_correo() {
        return tab_tipo_correo;
    }

    public void setTab_tipo_correo(Tabla tab_tipo_correo) {
        this.tab_tipo_correo = tab_tipo_correo;
    }
    
    
}

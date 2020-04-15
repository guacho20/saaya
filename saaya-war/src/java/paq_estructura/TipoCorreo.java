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
public class TipoCorreo extends Pantalla{
    Tabla tab_tipo_correo = new Tabla();
    public TipoCorreo(){
        tab_tipo_correo.setId ("tab_tipo_correo");
        tab_tipo_correo.setTabla ("yavirac_stror_tipo_correo","ide_ystgen",1);
        tab_tipo_correo.dibujar();
        
        PanelTabla pa_tipo_correo =new PanelTabla ();
        pa_tipo_correo.setId ("pa_tipo_correo");
        pa_tipo_correo.setPanelTabla (tab_tipo_correo);
        
        Division div_tipo_correo = new Division();
        div_tipo_correo.setId("div_tipo_correo");
        div_tipo_correo.dividir1(pa_tipo_correo);
        
        agregarComponente(pa_tipo_correo);
                
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
    
}
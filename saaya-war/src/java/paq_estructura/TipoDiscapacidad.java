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
public class TipoDiscapacidad  extends Pantalla{
 Tabla tab_tipo_discapacidad = new Tabla();
  public  TipoDiscapacidad(){
        tab_tipo_discapacidad.setId ("tab_tipo_discapacidad");
        tab_tipo_discapacidad.setTabla ("yavirac_stror_tipo_discapacid","ide_ysttid",1);
        tab_tipo_discapacidad.getColumna("ide_ysttid").setNombreVisual("CÓDIGO");
        tab_tipo_discapacidad.getColumna("descripcion_ysttid").setNombreVisual("DESCRIPCIÓN");
        tab_tipo_discapacidad.getColumna("activo_ysttid").setNombreVisual("ACTIVO");
        tab_tipo_discapacidad.dibujar();
        
        PanelTabla pa_tipo_discapacidad =new PanelTabla ();
        pa_tipo_discapacidad.setId ("pa_tipo_discapacidad");
        pa_tipo_discapacidad.setPanelTabla (tab_tipo_discapacidad);
        
        Division div_tipo_discapacidad = new Division();
        div_tipo_discapacidad.setId("div_tipo_discapacidad");
        div_tipo_discapacidad.dividir1(pa_tipo_discapacidad);
        
        agregarComponente (div_tipo_discapacidad);
    }
    
    @Override
    public void insertar() {
        tab_tipo_discapacidad.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_discapacidad.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_tipo_discapacidad.eliminar();
                 
    }

    public Tabla getTab_tipo_discapacidad() {
        return tab_tipo_discapacidad;
    }

    public void setTab_tipo_discapacidad(Tabla tab_tipo_discapacidad) {
        this.tab_tipo_discapacidad = tab_tipo_discapacidad;
    }
    
    
}

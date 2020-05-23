
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
 * @author lenovo
 */
public class TipoFormacionEducativa extends Pantalla{
 Tabla tab_tipo_for_educaci= new Tabla(); 
    public TipoFormacionEducativa(){
        tab_tipo_for_educaci.setId("tab_tipo_for_educaci");
        
        tab_tipo_for_educaci.setTabla("yavirac_stror_tipo_for_educaci","ide_ysttfe",1);
        tab_tipo_for_educaci.getColumna("ide_ysttfe").setNombreVisual("CÓDIGO");
        tab_tipo_for_educaci.getColumna("detalle_ysttfe").setNombreVisual("DESCRIPCIÓN");
        tab_tipo_for_educaci.getColumna(" abreviatura_ysttfe").setNombreVisual("ABREVIATURA");
        tab_tipo_for_educaci.getColumna("activo_ysttif").setNombreVisual("ACTIVO");
        tab_tipo_for_educaci.dibujar();
        
        PanelTabla pa_tipo_for_educaci = new PanelTabla();
        pa_tipo_for_educaci.setId("pa_tipo_for_educaci");
        pa_tipo_for_educaci.setPanelTabla(tab_tipo_for_educaci);
Division div_tipo_for_educaci = new Division();
        div_tipo_for_educaci.setId("div_tipo_for_educaci");
        div_tipo_for_educaci.dividir1(pa_tipo_for_educaci);
        agregarComponente(div_tipo_for_educaci);
             agregarComponente(div_tipo_for_educaci);
}
@Override
    public void insertar() {
        tab_tipo_for_educaci.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_for_educaci.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_tipo_for_educaci.eliminar();
                 
    }


    public Tabla getTab_tipo_for_educaci() {
        return tab_tipo_for_educaci;
    }

    public void setTab_tipo_for_educaci(Tabla tab_tipo_for_educaci) {
        this.tab_tipo_for_educaci= tab_tipo_for_educaci;
}
    }
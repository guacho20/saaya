
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
public class TipoFormacion extends Pantalla{
 Tabla tab_tipo_formacion= new Tabla(); 
    public TipoFormacion(){
        tab_tipo_formacion.setId("tab_tipo_formacion");
        
        tab_tipo_formacion.setTabla("yavirac_stror_tipo_formacion","ide_ysttif",1);
        tab_tipo_formacion.getColumna("ide_ysttif").setNombreVisual("CÓDIGO");
        tab_tipo_formacion.getColumna("detalle_ysttif").setNombreVisual("DESCRIPCIÓN");
        tab_tipo_formacion.getColumna("abreviatura_ysttif").setNombreVisual("ABREVIATURA");
        tab_tipo_formacion.dibujar();
        
        PanelTabla pa_tipo_formacion = new PanelTabla();
        pa_tipo_formacion.setId("pa_tipo_formacion");
        pa_tipo_formacion.setPanelTabla(tab_tipo_formacion);
        
        Division div_tipo_formacion = new Division();
        div_tipo_formacion.setId("div_tipo_formacion");
div_tipo_formacion.dividir1(pa_tipo_formacion);
        agregarComponente(div_tipo_formacion);
             agregarComponente(div_tipo_formacion);
}
@Override
    public void insertar() {
        tab_tipo_formacion.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_formacion.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_tipo_formacion.eliminar();
                 
    }


    public Tabla getTab_tipo_formacion() {
        return tab_tipo_formacion;
    }

    public void setTab_tipo_formacion(Tabla tab_tipo_formacion) {
        this.tab_tipo_formacion= tab_tipo_formacion;
    }
      }

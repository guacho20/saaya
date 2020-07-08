
package paq_estructura;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ITSY
 */
public class TipoAreaDepartamento extends Pantalla{
     Tabla tab_tipo_area_depar = new Tabla();// importar tabla
    public TipoAreaDepartamento(){//constructor
    tab_tipo_area_depar.setId("tab_tipo_area_depar");// todo objeto instanciado poner id
    
    tab_tipo_area_depar.setTabla("yavirac_stror_tipo_area_depar","ide_ysttad",1); // nom bdd
    tab_tipo_area_depar.getColumna("ide_ysttad").setNombreVisual("CÓDIGO");
    tab_tipo_area_depar.getColumna("descripcion_ysttad").setNombreVisual("DESCRIPCIÓN");
    tab_tipo_area_depar.dibujar();
            
    PanelTabla pa_tipo_area_depar = new PanelTabla();
    pa_tipo_area_depar.setId("pa_tipo_area_depar");
    pa_tipo_area_depar.setPanelTabla(tab_tipo_area_depar);
    
    Division div_tipo_area_depar = new Division();
    div_tipo_area_depar.setId("div_tipo_area_depar");
    div_tipo_area_depar.dividir1(pa_tipo_area_depar);
    
    agregarComponente(div_tipo_area_depar);   
                
    }
    
 @Override
    public void insertar() {
        tab_tipo_area_depar.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_area_depar.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_tipo_area_depar.eliminar();
                 
    }


    public Tabla getTab_tipo_area_depar() {
        return tab_tipo_area_depar;
    }

    public void setTab_tipo_area_depar(Tabla tab_tipo_area_depar) {
        this.tab_tipo_area_depar = tab_tipo_area_depar;
    }
    
    
}

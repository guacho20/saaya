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
public class TipoSangre extends Pantalla{
    Tabla tab_tipo_sangre = new Tabla();// importar tabla
    public TipoSangre(){//constructor
    tab_tipo_sangre.setId("tab_tipo_sangre");// todo objeto instanciado poner id
    
    tab_tipo_sangre.setTabla("yavirac_stror_tipo_sangre","ide_ysttis",1); // nom bdd
    tab_tipo_sangre.getColumna("ide_ysttis").setNombreVisual("CÓDIGO");
    tab_tipo_sangre.getColumna("descripcion_ysttis").setNombreVisual("DESCRIPCIÓN");
    tab_tipo_sangre.getColumna("activo_ysttis").setNombreVisual("ACTIVO");
    tab_tipo_sangre.dibujar();
            
    PanelTabla pa_tipo_sangre = new PanelTabla();
    pa_tipo_sangre.setId("pa_tipo_sangre");
    pa_tipo_sangre.setPanelTabla(tab_tipo_sangre);
    
    Division div_tipo_sangre = new Division();
    div_tipo_sangre.setId("div_tipo_sangre");
    div_tipo_sangre.dividir1(pa_tipo_sangre);
    
    agregarComponente(div_tipo_sangre);   
    }

    @Override
    public void insertar() {
        tab_tipo_sangre.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_sangre.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_tipo_sangre.eliminar();
                 
    }


    public Tabla getTab_tipo_sangre() {
        return tab_tipo_sangre;
    }

    public void setTab_tipo_sangre(Tabla tab_tipo_sangre) {
        this.tab_tipo_sangre = tab_tipo_sangre;
    }
  
}

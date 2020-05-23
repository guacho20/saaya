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

public class TipoOperadora extends Pantalla{
    Tabla tab_tipo_operadora = new Tabla();// importar tabla
    public TipoOperadora(){//constructor
    tab_tipo_operadora.setId("tab_tipo_operadora");// todo objeto instanciado poner id
    
    tab_tipo_operadora.setTabla("yavirac_stror_tipo_operadora","ide_ysttio",1); // nom bdd
    tab_tipo_operadora.getColumna("ide_ysttio").setNombreVisual("CÓDIGO");
    tab_tipo_operadora.getColumna("descripcion_ysttio").setNombreVisual("DESCRIPCIÓN");
    tab_tipo_operadora.getColumna("activo_ysttio").setNombreVisual("ACTIVO");
    tab_tipo_operadora.dibujar();
            
    PanelTabla pa_tipo_operadora = new PanelTabla();
    pa_tipo_operadora.setId("pa_tipo_operadora");
    pa_tipo_operadora.setPanelTabla(tab_tipo_operadora);
    
    Division div_tipo_operadora = new Division();
    div_tipo_operadora.setId("div_tipo_operadora");
    div_tipo_operadora.dividir1(pa_tipo_operadora);
    
    agregarComponente(div_tipo_operadora);   
    }

    @Override
    public void insertar() {
        tab_tipo_operadora.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_operadora.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_tipo_operadora.eliminar();
                 
    }


    public Tabla getTab_tipo_operadora() {
        return tab_tipo_operadora;
    }

    public void setTab_tipo_operadora(Tabla tab_tipo_operadora) {
        this.tab_tipo_operadora = tab_tipo_operadora;
    }
  
}

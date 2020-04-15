/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.faces.component.UIComponent;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Martha
 */
public class TipoProducto extends Pantalla{
    
    private Tabla tab_tipo_producto = new Tabla();
    
    public TipoProducto(){
        
            tab_tipo_producto.setId("tab_tipo_producto");
            tab_tipo_producto.setTabla("yavirac_titu_tipo_producto", "ide_ytitip", 4);
            tab_tipo_producto.setHeader("TIPO PRODUCTO");
            tab_tipo_producto.dibujar();
            
            PanelTabla pat_tipo_producto = new PanelTabla();
            pat_tipo_producto.setId("pat_tipo_producto");
            pat_tipo_producto.setPanelTabla(tab_tipo_producto);
            
            
             Division div_tipo_producto = new Division();
            div_tipo_producto.setId("div_tipo_producto");
            div_tipo_producto.dividir1(pat_tipo_producto);
            
            agregarComponente(div_tipo_producto);
    }
    @Override
    public void insertar() {
      tab_tipo_producto.insertar();
    }

    @Override
    public void guardar() {
       tab_tipo_producto.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_producto.eliminar();
    }

    public Tabla getTab_tipo_producto() {
        return tab_tipo_producto;
    }

    public void setTab_tipo_producto(Tabla tab_tipo_producto) {
        this.tab_tipo_producto = tab_tipo_producto;
    }

    public Utilitario getUtilitario() {
        return utilitario;
    }

    public void setUtilitario(Utilitario utilitario) {
        this.utilitario = utilitario;
    }

    public Barra getBar_botones() {
        return bar_botones;
    }

    public void setBar_botones(Barra bar_botones) {
        this.bar_botones = bar_botones;
    }

    public Grupo getGru_pantalla() {
        return gru_pantalla;
    }

    public void setGru_pantalla(Grupo gru_pantalla) {
        this.gru_pantalla = gru_pantalla;
    }

}

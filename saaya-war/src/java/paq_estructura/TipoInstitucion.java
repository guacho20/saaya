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
public class TipoInstitucion extends Pantalla {
    Tabla tab_tipoinstitucion = new Tabla (); // importar tabla 
    public TipoInstitucion () {
         tab_tipoinstitucion.setId("tab_tipoinstitucion");  // todo objeto instanciado poner id 
        
        tab_tipoinstitucion.setTabla("yavirac_stror_tipo_institucion","ide_ysttii",1);    // nom bdd
        tab_tipoinstitucion.getColumna("ide_ysttii").setNombreVisual("CÓDIGO");
        tab_tipoinstitucion.getColumna("descripcion_ysttii").setNombreVisual("DESCRIPCIÓN");
        tab_tipoinstitucion.getColumna("activo_ysttii").setNombreVisual("ACTIVO");
        tab_tipoinstitucion.dibujar();
        
        PanelTabla pa_tipoinstitucion = new PanelTabla();
        pa_tipoinstitucion.setId("pa_tipoinstitucion"); // nombre de i
        pa_tipoinstitucion.setPanelTabla(tab_tipoinstitucion);
        
        Division div_tipoinstitucion = new Division();
        div_tipoinstitucion.setId("div_tipoinstitucion");
        div_tipoinstitucion.dividir1(pa_tipoinstitucion);
        
        agregarComponente(div_tipoinstitucion);
    }

    @Override
    public void insertar() {
       tab_tipoinstitucion.insertar();
    }

    @Override
    public void guardar() {
        tab_tipoinstitucion.guardar(); 
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipoinstitucion.eliminar();
    }

    public Tabla getTab_tipoinstitucion() {
        return tab_tipoinstitucion;
    }

    public void setTab_tipoinstitucion(Tabla tab_tipoinstitucion) {
        this.tab_tipoinstitucion = tab_tipoinstitucion;
    }
    
}

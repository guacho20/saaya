

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
public class TipoDivisionPolitica extends Pantalla{
    Tabla tab_tipo_divisio_po = new Tabla();// importar tabla
    public TipoDivisionPolitica (){//constructor
    tab_tipo_divisio_po.setId("tab_tipo_divisio_po");// todo objeto instanciado poner id
    
    tab_tipo_divisio_po.setTabla("yavirac_stror_tipo_divisio_po","ide_ysttdp",1); // nom bdd
    tab_tipo_divisio_po.getColumna("ide_ysttdp").setNombreVisual("CÓDIGO");
    tab_tipo_divisio_po.getColumna("descripcion_ysttdp").setNombreVisual("DESCRIPCIÓN");
    tab_tipo_divisio_po.getColumna("activo_ysttdp").setNombreVisual("ACTIVO");
    tab_tipo_divisio_po.dibujar();
            
    PanelTabla pa_tipo_divisio_po = new PanelTabla();
    pa_tipo_divisio_po.setId("pa_tipo_divisio_po");
    pa_tipo_divisio_po.setPanelTabla(tab_tipo_divisio_po);
    
    Division div_tipo_divisio_po = new Division();
    div_tipo_divisio_po.setId("div_tipo_divisio_po");
    div_tipo_divisio_po.dividir1(pa_tipo_divisio_po);
    
    agregarComponente(div_tipo_divisio_po);   
        }

    @Override
    public void insertar() {
        tab_tipo_divisio_po.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_divisio_po.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_tipo_divisio_po.eliminar();
                 
    }


    public Tabla getTab_tipo_divisio_po() {
        return tab_tipo_divisio_po;
    }

    public void setTab_tipo_divisio_po(Tabla tab_tipo_divisio_po) {
        this.tab_tipo_divisio_po = tab_tipo_divisio_po;
    }
  
}


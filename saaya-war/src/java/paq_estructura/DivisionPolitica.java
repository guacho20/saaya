/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Arbol;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHONPRODUCER
 */
public class DivisionPolitica extends Pantalla {

    private Tabla tab_division_politica = new Tabla();
    private Arbol arb_arbol = new Arbol();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public DivisionPolitica() {

        tab_division_politica.setId("tab_division_politica");
        tab_division_politica.setTabla("yavirac_stror_distribucion_pol", "ide_ystdip", 1);
        tab_division_politica.getColumna("ide_ysttdp").setCombo(ser_estructura.getTipoDivisionPolitica("true,false"));
        tab_division_politica.setHeader("DIVISION POLITICA"); 
        tab_division_politica.getColumna("ide_ystdip").setNombreVisual("CÓDIGO");
        tab_division_politica.getColumna("ide_ysttdp").setNombreVisual("TIPO DIVISIÓN POLITICA");
        tab_division_politica.getColumna("descripcion_ystdip").setNombreVisual("DESCRIPCIÓN");
        tab_division_politica.getColumna("codigounitario_ystdip").setNombreVisual("CÓDIGO UNITARIO");
        tab_division_politica.getColumna("activo_ystdip").setNombreVisual("ACTIVO");
        
        tab_division_politica.getColumna("ide_ystdip").setOrden(0);
        tab_division_politica.getColumna("ide_ysttdp").setOrden(1);
        tab_division_politica.getColumna("descripcion_ystdip").setOrden(2);
        tab_division_politica.getColumna("codigounitario_ystdip").setOrden(3);
        tab_division_politica.getColumna("activo_ystdip").setOrden(4);
        //arbol
        tab_division_politica.setCampoPadre("yav_ide_ystdip");
        tab_division_politica.setCampoNombre("descripcion_ystdip");
        tab_division_politica.agregarArbol(arb_arbol);
        tab_division_politica.dibujar();

        PanelTabla pat_division_politica = new PanelTabla();
        //pat_division_politica.setId("pat_division_politica");
        pat_division_politica.setPanelTabla(tab_division_politica);
        arb_arbol.setId("arb_arbol");
        arb_arbol.dibujar();

        Division div1 = new Division(); //UNE OPCION Y DIV 1
        div1.dividir2(arb_arbol, pat_division_politica, "20%", "V");  //arbol y div1
        agregarComponente(div1);
    }

    @Override
    public void insertar() {
        tab_division_politica.insertar();
    }

    @Override
    public void guardar() {
        tab_division_politica.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_division_politica.eliminar();
    }

    public Tabla getTab_division_politica() {
        return tab_division_politica;
    }

    public void setTab_division_politica(Tabla tab_division_politica) {
        this.tab_division_politica = tab_division_politica;
    }

    public Arbol getArb_arbol() {
        return arb_arbol;
    }

    public void setArb_arbol(Arbol arb_arbol) {
        this.arb_arbol = arb_arbol;
    }
    
}

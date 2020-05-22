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
import sistema.aplicacion.Pantalla;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;

/**
 *
 * @author ITSY
 */
public class AreaDepartamento extends Pantalla {

    private Tabla tab_area_departament = new Tabla();// importar tabla
    private Arbol arb_arbol = new Arbol();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public AreaDepartamento() {//constructor
        tab_area_departament.setId("tab_area_departament");// todo objeto instanciado poner id

        tab_area_departament.setTabla("yavirac_stror_area_departament", "ide_ystard", 1); // nom bdd
        tab_area_departament.setCampoPadre("yav_ide_ystard");
        tab_area_departament.setCampoNombre("descripcion_ystard");
        tab_area_departament.getColumna("ide_ysttad").setCombo(ser_estructura.getTipoAreaDepartamento());
        tab_area_departament.getColumna("ide_ystard").setNombreVisual("CÓDIGO");
        tab_area_departament.getColumna("descripcion_ystard").setNombreVisual("DESCRIPCIÓN");
        tab_area_departament.getColumna("activo_ystard").setNombreVisual("ACTIVO");
        tab_area_departament.getColumna("tipo_ystard").setVisible(false);
        tab_area_departament.getColumna("nivel_ystard").setVisible(false);
        tab_area_departament.getColumna("nivel_organico_ystard").setVisible(false);
        tab_area_departament.getColumna("posicion_hijos_ystard").setVisible(false);
        tab_area_departament.getColumna("orden_ystard").setVisible(false);
        tab_area_departament.getColumna("orden_imprime_ystard").setVisible(false);
        tab_area_departament.getColumna("abreviatura_ystard").setVisible(false);
        tab_area_departament.agregarArbol(arb_arbol);
        tab_area_departament.dibujar();

        PanelTabla pa_area_departament = new PanelTabla();
        pa_area_departament.setId("pa_area_departament");
        pa_area_departament.setPanelTabla(tab_area_departament);

        arb_arbol.setId("arb_arbol");
        arb_arbol.dibujar();

        Division div_area_departament = new Division();
        div_area_departament.setId("div_area_departament");
        div_area_departament.dividir2(arb_arbol, pa_area_departament, "21%", "V");

        agregarComponente(div_area_departament);
    }

    @Override
    public void insertar() {
        tab_area_departament.insertar();
    }

    @Override
    public void guardar() {
        tab_area_departament.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_area_departament.eliminar();

    }

    public Tabla getTab_area_departament() {
        return tab_area_departament;
    }

    public void setTab_area_departament(Tabla tab_area_departament) {
        this.tab_area_departament = tab_area_departament;
    }

    public Arbol getArb_arbol() {
        return arb_arbol;
    }

    public void setArb_arbol(Arbol arb_arbol) {
        this.arb_arbol = arb_arbol;
    }

}

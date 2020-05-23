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
 * @author ITSY
 */
public class Institucion extends Pantalla {
    private Tabla tab_tabla1 = new Tabla(); // importar tabla    
    private Arbol arb_arbol = new Arbol();
   @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
            
    public Institucion (){// constructor
        tab_tabla1.setId("tab_tabla1");// todo objeto instanciado poner id 
        tab_tabla1.setTabla("yavirac_stror_institucion","ide_ystins",1);  // nom bdd
        tab_tabla1.setCampoPadre("yav_ide_ystins");
        tab_tabla1.setCampoNombre("descripcion_ystins");
        tab_tabla1.setCampoNombre("direccion_ystins");
        tab_tabla1.getColumna("ide_ysttii").setCombo(ser_estructura.getTipoInstitucion("true,false"));
        tab_tabla1.agregarArbol(arb_arbol);
        tab_tabla1.getColumna("ide_ystins").setNombreVisual("CÓDIGO");
        tab_tabla1.getColumna("ide_ysttii").setNombreVisual("TIPO INSTITUCIÓN");
        tab_tabla1.getColumna("descripcion_ystins").setNombreVisual("DESCRIPCIÓN");
        tab_tabla1.getColumna("direccion_ystins").setNombreVisual("DIRECCIÓN");
        tab_tabla1.getColumna("activo_ystins").setNombreVisual("ACTIVO");
        tab_tabla1.dibujar();
        
        PanelTabla pa_cargo = new PanelTabla();
        pa_cargo.setId("pa_cargo");//nombre id
        pa_cargo.setPanelTabla(tab_tabla1);

        arb_arbol.setId("arb_arbol");
        arb_arbol.dibujar();
        
        Division div_cargo =new Division ();
       div_cargo.setId("div_cargo");
        div_cargo.dividir2(arb_arbol, pa_cargo, "21%", "V");  //arbol y div3
       agregarComponente(div_cargo); 
}

    @Override
    public void insertar() {
        tab_tabla1.insertar();
    }

    @Override
    public void guardar() {
        tab_tabla1.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tabla1.eliminar();
    }

    public Tabla getTab_tabla1() {
        return tab_tabla1;
    }

    public void setTab_tabla1(Tabla tab_tabla1) {
        this.tab_tabla1 = tab_tabla1;
    }

    public Arbol getArb_arbol() {
        return arb_arbol;
    }

    public void setArb_arbol(Arbol arb_arbol) {
        this.arb_arbol = arb_arbol;
    }

}

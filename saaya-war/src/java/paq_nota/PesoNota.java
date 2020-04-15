/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.componentes.Arbol;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHON
 */
public class PesoNota extends Pantalla {

    private Tabla tab_peso_nota = new Tabla();
    private Tabla tab_detalle_peso_actividad = new Tabla();
    private Arbol arb_arbol = new Arbol();

    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public PesoNota() {

        tab_peso_nota.setId("tab_peso_nota");
        tab_peso_nota.setTabla("yavirac_nota_peso_nota", "ide_ynopen", 1);
        tab_peso_nota.setCampoPadre("yav_ide_ynopen");
        tab_peso_nota.setCampoNombre("detalle_ynopen");
        tab_peso_nota.agregarRelacion(tab_detalle_peso_actividad);
        tab_peso_nota.getColumna("ide_ynotie").setCombo(ser_notas.getTipoEvaluacion("true,false"));
        tab_peso_nota.getColumna("ide_ynotie").setAncho(-1);
        tab_peso_nota.getColumna("ide_ynotie").setLongitud(-1);
        tab_peso_nota.getColumna("ide_ystpea").setCombo(ser_estructura_organizacional.getPeriodoAcademico("true,false"));
        //tab_peso_nota.getColumna("ide_ystpea").setAutoCompletar();
        tab_peso_nota.getColumna("ide_ystpea").setAncho(-1);
        tab_peso_nota.getColumna("ide_ystpea").setLongitud(-1);
        tab_peso_nota.getColumna("ide_ysttfe").setCombo(ser_estructura_organizacional.getTipoFormacionEducativa("true"));
        tab_peso_nota.getColumna("ide_ysttfe").setAncho(-1);
        tab_peso_nota.getColumna("ide_ysttfe").setLongitud(-1);
        tab_peso_nota.getColumna("nivel_ynopen").setCombo(ser_notas.getNivelResumen());
        tab_peso_nota.getColumna("nivel_ynopen").setAncho(-1);
        tab_peso_nota.getColumna("nivel_ynopen").setLongitud(-1);
        tab_peso_nota.agregarArbol(arb_arbol);
        tab_peso_nota.getColumna("ide_ynopen").setNombreVisual("CODIGO");
        tab_peso_nota.getColumna("ide_ystpea").setNombreVisual("PERIODO ACADÉMICO");
        tab_peso_nota.getColumna("ide_ysttfe").setNombreVisual("TIPO FORMACIÓN");
        tab_peso_nota.getColumna("ide_ynotie").setNombreVisual("PARCIAL");
        tab_peso_nota.getColumna("detalle_ynopen").setNombreVisual("DETALLE");
        tab_peso_nota.getColumna("peso_ynopen").setNombreVisual("PESO NOTA");
        tab_peso_nota.getColumna("peso_ynopen").setValorDefecto("0");
        tab_peso_nota.getColumna("nivel_ynopen").setNombreVisual("NIVEL");
        tab_peso_nota.getColumna("bloqueo_ynopen").setNombreVisual("ACTIVO");
        tab_peso_nota.getColumna("bloqueo_ynopen").setValorDefecto("true");
        tab_peso_nota.getColumna("bloqueo_ynopen").setLectura(true);
        tab_peso_nota.dibujar();

        PanelTabla pat_peso_nota = new PanelTabla();
        pat_peso_nota.setPanelTabla(tab_peso_nota);

        arb_arbol.setId("arb_arbol");
        arb_arbol.dibujar();

        tab_detalle_peso_actividad.setId("tab_detalle_peso_actividad");
        tab_detalle_peso_actividad.setTabla("yavirac_nota_detalle_peso_acti", "ide_ynodpn", 2);
        tab_detalle_peso_actividad.getColumna("ide_ynoace").setCombo(ser_notas.getActividadEvaluacion("true,false"));
        tab_detalle_peso_actividad.getColumna("ide_ynodpn").setNombreVisual("CODIGO");
        tab_detalle_peso_actividad.getColumna("ide_ynoace").setNombreVisual("ACTIVIDAD EVALUACIÓN");
        //tab_detalle_peso_actividad.getColumna("ide_ynoace").setUnico(true);
        tab_detalle_peso_actividad.dibujar();

        PanelTabla pat_detalle_peso = new PanelTabla();
        pat_detalle_peso.setPanelTabla(tab_detalle_peso_actividad);

        Division div3 = new Division();
        div3.dividir2(pat_peso_nota, pat_detalle_peso, "50%", "H");
        Division div_division = new Division();
        div_division.setId("div_division");
        div_division.dividir2(arb_arbol, div3, "21%", "V");  //arbol y div3
        agregarComponente(div_division);

    }

    @Override
    public void insertar() {
        if (tab_peso_nota.isFocus()) {
            tab_peso_nota.insertar();
        } else if (tab_detalle_peso_actividad.isFocus()) {
            tab_detalle_peso_actividad.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_peso_nota.guardar()) {
            tab_detalle_peso_actividad.guardar();
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        utilitario.getTablaisFocus().eliminar();
    }

    public Tabla getTab_peso_nota() {
        return tab_peso_nota;
    }

    public void setTab_peso_nota(Tabla tab_peso_nota) {
        this.tab_peso_nota = tab_peso_nota;
    }

    public Tabla getTab_detalle_peso_actividad() {
        return tab_detalle_peso_actividad;
    }

    public void setTab_detalle_peso_actividad(Tabla tab_detalle_peso_actividad) {
        this.tab_detalle_peso_actividad = tab_detalle_peso_actividad;
    }

    public Arbol getArb_arbol() {
        return arb_arbol;
    }

    public void setArb_arbol(Arbol arb_arbol) {
        this.arb_arbol = arb_arbol;
    }
    
}

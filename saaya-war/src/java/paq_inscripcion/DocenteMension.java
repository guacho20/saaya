/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion;

import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_inscripcion.ejb.ServicioInscripcion;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author CRISTIAN VEGA       
 */
public class DocenteMension extends Pantalla {

    private Combo com_periodo_academico = new Combo();
    private Boton bot_clean = new Boton();
    private Tabla tab_docentemension = new Tabla();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioInscripcion ser_inscripcion = (ServicioInscripcion) utilitario.instanciarEJB(ServicioInscripcion.class);

    public DocenteMension() {

        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
        bar_botones.agregarComponente(com_periodo_academico);
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");

        bot_clean.setIcon("ui-icon-cancel");
        bot_clean.setTitle("Limpiar");
        bot_clean.setMetodo("limpiar");
        bar_botones.agregarComponente(bot_clean);

        tab_docentemension.setId("tab_docentemension");
        tab_docentemension.setTabla("yavirac_ins_docente_mension", "ide_yindom", 1);
        tab_docentemension.setCondicion("ide_yindom = -1");
        tab_docentemension.getColumna("ide_yindom").setNombreVisual("CODIGO");
        tab_docentemension.setHeader("COORDINADOR CARRERA");
        tab_docentemension.getColumna("ide_ystmen").setCombo(ser_estructura_organizacional.getMension());
        tab_docentemension.getColumna("ide_ystmen").setNombreVisual("MENSION");
        tab_docentemension.getColumna("ide_ystpea").setVisible(false);
        tab_docentemension.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true"));
        tab_docentemension.getColumna("ide_ypedpe").setNombreVisual("DOCENTE DATO");
        tab_docentemension.getColumna("ide_ypedpe").setAutoCompletar();
        tab_docentemension.getColumna("observacion_yindom").setNombreVisual("OBSERVACION");
                           
        //tab_docentemension.setTipoFormulario(true);        
        //tab_docentemension.getGrid().setColumns(2);

        tab_docentemension.dibujar();   
         
        PanelTabla pat_docentemension = new PanelTabla();
        pat_docentemension.setId("pat_docentemension");
        pat_docentemension.setPanelTabla(tab_docentemension);

        Division div_docentemension = new Division();
        div_docentemension.setId("div_docentemension");
        div_docentemension.dividir1(pat_docentemension);
        agregarComponente(div_docentemension);

    }

    public void limpiar() {
        tab_docentemension.limpiar();
        com_periodo_academico.setValue("null");
        utilitario.addUpdate("tab_docentemension,com_periodo_academico");
    }

    String parcial = "";

    public void filtroComboPeriodoAcademico() {

        tab_docentemension.setCondicion("ide_ystpea=" + com_periodo_academico.getValue().toString());
        tab_docentemension.ejecutarSql();
        ///tab_docentemension.ejecutarValorForanea(tab_docentemension.getValorSeleccionado());   
    }

    @Override
    public void insertar() {
        if (com_periodo_academico.getValue() == null) {   

            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea generar");
            return;
        } else if (tab_docentemension.isFocus()) {
            tab_docentemension.insertar();
            tab_docentemension.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
            utilitario.addUpdate("tab_docentemension");
        } else if (tab_docentemension.isFocus()) {
            tab_docentemension.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_docentemension.isFocus()) {
            tab_docentemension.guardar();
        } else if (tab_docentemension.isFocus()) {
            tab_docentemension.guardar();
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if (tab_docentemension.isFocus()) {
            tab_docentemension.eliminar();
        } else if (tab_docentemension.isFocus()) {
            tab_docentemension.eliminar();
        }

    }

    public Boton getBot_clean() {
        return bot_clean;
    }

    public void setBot_clean(Boton bot_clean) {
        this.bot_clean = bot_clean;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public Tabla getTab_docentemension() {
        return tab_docentemension;
    }

    public void setTab_docentemension(Tabla tab_docentemension) {
        this.tab_docentemension = tab_docentemension;
    }

}

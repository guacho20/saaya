/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Confirmar;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Texto;
import java.util.List;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author usuario
 */
public class PeriodoEvaluacion extends Pantalla {

    private Combo com_periodo_academico = new Combo();
    private Boton bot_clean = new Boton();
    private Tabla tab_periodo_evaluacion = new Tabla();
    private Tabla tab_actividad_evaluacion = new Tabla();
    private Texto txt_parcial = new Texto();
    private Confirmar con_confirma = new Confirmar();
    private Confirmar con_confirma2 = new Confirmar();
    private Etiqueta eti_parcial = new Etiqueta();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    public PeriodoEvaluacion() {

        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
        bar_botones.agregarComponente(com_periodo_academico);
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");

        bot_clean.setIcon("ui-icon-cancel");
        bot_clean.setTitle("Limpiar");
        bot_clean.setMetodo("limpiar");
        bar_botones.agregarComponente(bot_clean);

        Boton bot_bloquear = new Boton();
        bot_bloquear.setValue("Bloquear Parcial");
        bot_bloquear.setIcon("ui-icon-locked");
        bot_bloquear.setMetodo("bloquearParcial");
        bar_botones.agregarBoton(bot_bloquear);

        Boton bot_desbloquear = new Boton();
        bot_desbloquear.setValue("Desbloquear Parcial");
        bot_desbloquear.setIcon("ui-icon-unlocked");
        bot_desbloquear.setMetodo("desbloquearParcial");
        bar_botones.agregarBoton(bot_desbloquear);

        txt_parcial.setId("txt_parcial");
        txt_parcial.setValue("");

        //TABLA 1
        tab_periodo_evaluacion.setId("tab_periodo_evaluacion");
        tab_periodo_evaluacion.setTabla("yavirac_nota_periodo_evaluacio ", "ide_ynopee", 1);
        tab_periodo_evaluacion.setCondicion("ide_ystpea=-1");
        tab_periodo_evaluacion.setHeader(eti_parcial);
        tab_periodo_evaluacion.agregarRelacion(tab_actividad_evaluacion);
        tab_periodo_evaluacion.getColumna("ide_ynotie").setCombo(ser_notas.getTipoEvaluacion("true,false"));
        tab_periodo_evaluacion.getColumna("ide_ynopee").setNombreVisual("CÓDIGO");
        tab_periodo_evaluacion.getColumna("ide_ynotie").setNombreVisual("TIPO DE EVALUACIÓN");
        //tab_periodo_evaluacion.getColumna("ide_ynotie").setUnico(true);
        tab_periodo_evaluacion.getColumna("ide_ystpea").setVisible(false);
        tab_periodo_evaluacion.getColumna("activo_ynopee").setValorDefecto("true");
        tab_periodo_evaluacion.getColumna("activo_ynopee").setLectura(true);
        tab_periodo_evaluacion.dibujar();

        PanelTabla pa_periodo_evaluacion = new PanelTabla();
        pa_periodo_evaluacion.setId("pa_periodo_evaluacion");
        pa_periodo_evaluacion.setPanelTabla(tab_periodo_evaluacion);

        // TABLA 2
        tab_actividad_evaluacion.setId("tab_actividad_evaluacion");
        tab_actividad_evaluacion.setTabla("yavirac_nota_periodo_activ_eva", "ide_ynopae", 2);
        //tab_actividad_evaluacion.setHeader("DETALLE NOTAS FINALES");
        tab_actividad_evaluacion.getColumna("ide_ynoace").setCombo(ser_notas.getActividadEvaluacion("true,false"));
        tab_actividad_evaluacion.getColumna("ide_ynopae").setNombreVisual("CÓDIGO");
        tab_actividad_evaluacion.getColumna("ide_ynoace").setNombreVisual("ACTIVIDAD EVALUACIÓN");
        //tab_actividad_evaluacion.getColumna("ide_ynoace").setUnico(true);
        tab_actividad_evaluacion.getColumna("activo_ynopae").setLectura(true);
        tab_actividad_evaluacion.getColumna("activo_ynopae").setNombreVisual("ACTIVO");
        tab_actividad_evaluacion.getColumna("activo_ynopae").setValorDefecto("true");
        tab_actividad_evaluacion.dibujar();

        PanelTabla pa_actividad_evaluacion = new PanelTabla();
        pa_actividad_evaluacion.setId("pa_actividad_evaluacion"); // nombre de i
        pa_actividad_evaluacion.setPanelTabla(tab_actividad_evaluacion);

        Division div_periodo_evaluacion = new Division();
        div_periodo_evaluacion.dividir2(pa_periodo_evaluacion, pa_actividad_evaluacion, "40%", "h");
        agregarComponente(div_periodo_evaluacion);

        //CONFIRMAR
        con_confirma.setId("con_confirma");
        con_confirma.setMessage("Está seguro que desea bloquear el registro de notas y asistencia de este prcial");
        con_confirma.setTitle("BLOQUEAR REGISTRO DE NOTAS");
        con_confirma.getBot_aceptar().setValue("Si");
        con_confirma.getBot_cancelar().setValue("No");
        agregarComponente(con_confirma);

        con_confirma2.setId("con_confirma2");
        con_confirma2.setMessage("Está seguro que desea desbloquear el registro de notas y asistencia de este");
        con_confirma2.setTitle("DESBLOQUEAR REGISTRO DE NOTAS");
        con_confirma2.getBot_aceptar().setValue("Si");
        con_confirma2.getBot_cancelar().setValue("No");
        agregarComponente(con_confirma2);

    }
String parcial="";
    public void filtroComboPeriodoAcademico() {

        tab_periodo_evaluacion.setCondicion("ide_ystpea=" + com_periodo_academico.getValue().toString());
        tab_periodo_evaluacion.ejecutarSql();
        tab_actividad_evaluacion.ejecutarValorForanea(tab_periodo_evaluacion.getValorSeleccionado());
    }

    public void bloquearParcial() {

        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione el periodo académico");
        } else if (tab_periodo_evaluacion.getValor("activo_ynopee").equals("true")) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "El parcial ya se encuentra bloqueado");
        } else {
            con_confirma.getBot_aceptar().setMetodo("confirmarBloqueo");
            utilitario.addUpdate("con_confirma,parcial");
            con_confirma.dibujar();
        }
    }

    public void confirmarBloqueo() {
        utilitario.getConexion().ejecutarSql(ser_notas.getBloquearParcial("false", tab_periodo_evaluacion.getValor(tab_periodo_evaluacion.getFilaActual(), "ide_ynotie"), com_periodo_academico.getValue().toString()));
        utilitario.getConexion().ejecutarSql(ser_notas.getBloquearActividad("false", tab_periodo_evaluacion.getValor(tab_periodo_evaluacion.getFilaActual(), "ide_ynopee")));
        utilitario.getConexion().ejecutarSql(ser_notas.getBloquearRegistroAsistencia(tab_periodo_evaluacion.getValor(tab_periodo_evaluacion.getFilaActual(), "ide_ynopee"), "true"));
        utilitario.getConexion().ejecutarSql(ser_notas.getLecturaAsistencia(tab_periodo_evaluacion.getValor(tab_periodo_evaluacion.getFilaActual(), "ide_ynotie"), "true"));
        utilitario.getConexion().ejecutarSql(ser_notas.getLecturaNota(tab_periodo_evaluacion.getValor(tab_periodo_evaluacion.getFilaActual(), "ide_ynotie"), "true"));
        con_confirma.cerrar();
        utilitario.agregarMensaje("SUCCESSFULL,", "Parcial bloqueado correctamente");
        tab_actividad_evaluacion.actualizar();
        tab_periodo_evaluacion.actualizar();
    }

    public void desbloquearParcial() {

        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione el periodo académico");
        } else if (tab_periodo_evaluacion.getValor("activo_ynopee").equals("false")) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "El parcial se encuentra desbloqueado");
        } else {
            con_confirma2.getBot_aceptar().setMetodo("confirmarDesbloqueo");
            utilitario.addUpdate("con_confirma,eti_parcial");
            con_confirma2.dibujar();
        }
    }

    
    
    public void confirmarDesbloqueo() {
        utilitario.getConexion().ejecutarSql(ser_notas.getBloquearParcial("true", tab_periodo_evaluacion.getValor(tab_periodo_evaluacion.getFilaActual(), "ide_ynotie"), com_periodo_academico.getValue().toString()));
        utilitario.getConexion().ejecutarSql(ser_notas.getBloquearActividad("true", tab_periodo_evaluacion.getValor(tab_periodo_evaluacion.getFilaActual(), "ide_ynopee")));
        utilitario.getConexion().ejecutarSql(ser_notas.getBloquearRegistroAsistencia(tab_periodo_evaluacion.getValor(tab_periodo_evaluacion.getFilaActual(), "ide_ynopee"), "false"));
        utilitario.getConexion().ejecutarSql(ser_notas.getLecturaAsistencia(tab_periodo_evaluacion.getValor(tab_periodo_evaluacion.getFilaActual(), "ide_ynotie"), "false"));
        utilitario.getConexion().ejecutarSql(ser_notas.getLecturaNota(tab_periodo_evaluacion.getValor(tab_periodo_evaluacion.getFilaActual(), "ide_ynotie"), "false"));
        con_confirma2.cerrar();
        utilitario.agregarMensaje("SUCCESSFULL,", "Parcial desbloqueado correctamente");
        tab_actividad_evaluacion.actualizar();
        tab_periodo_evaluacion.actualizar();
    }

    public void limpiar() {
        tab_periodo_evaluacion.limpiar();
        com_periodo_academico.setValue("null");
        utilitario.addUpdate("tab_periodo_evaluacion,com_periodo_academico,actividad_evaluacion");
    }

    @Override
    public void insertar() {
        if (com_periodo_academico.getValue() == null) {

            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea generar");
            return;
        } else if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.insertar();
            tab_periodo_evaluacion.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
            utilitario.addUpdate("tab_periodo_evaluacion");
        } else if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.insertar();
        }

    }

    @Override
    public void guardar() {
        if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.guardar();
        } else if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.guardar();
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.eliminar();
        } else if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.eliminar();
        }
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public Boton getBot_clean() {
        return bot_clean;
    }

    public void setBot_clean(Boton bot_clean) {
        this.bot_clean = bot_clean;
    }

    public Tabla getTab_periodo_evaluacion() {
        return tab_periodo_evaluacion;
    }

    public void setTab_periodo_evaluacion(Tabla tab_periodo_evaluacion) {
        this.tab_periodo_evaluacion = tab_periodo_evaluacion;
    }

    public Tabla getTab_actividad_evaluacion() {
        return tab_actividad_evaluacion;
    }

    public void setTab_actividad_evaluacion(Tabla tab_actividad_evaluacion) {
        this.tab_actividad_evaluacion = tab_actividad_evaluacion;
    }

    public Etiqueta getEti_parcial() {
        return eti_parcial;
    }

    public void setEti_parcial(Etiqueta eti_parcial) {
        this.eti_parcial = eti_parcial;
    }

}

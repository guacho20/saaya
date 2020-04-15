/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.aplicacion.TablaGenerica;
import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Calendario;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Espacio;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import framework.componentes.VisualizarPDF;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_matricula.ejb.ServicioMatriculas;
import paq_nota.ejb.ServicioNotas;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHONPRODUCER
 */
public class RecordAcademico extends Pantalla {

    private Tabla tab_cabecera = new Tabla();
    private Tabla tab_detalle = new Tabla();
    private AutoCompletar aut_alumno = new AutoCompletar();
    private VisualizarPDF vipdf_record = new VisualizarPDF();
    private VisualizarPDF vipdf_detalle = new VisualizarPDF();
    private SeleccionTabla sel_tab = new SeleccionTabla();
    private Calendario cal_fecha = new Calendario();
    private Dialogo dia_dialogo = new Dialogo();

    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioMatriculas ser_matricula = (ServicioMatriculas) utilitario.instanciarEJB(ServicioMatriculas.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public RecordAcademico() {

        bar_botones.getBot_insertar().setRendered(false);
        bar_botones.getBot_eliminar().setRendered(false);

        aut_alumno.setId("aut_alumno");
        aut_alumno.setAutoCompletar(ser_alumno.getDatosAlumnos("true,false"));
        aut_alumno.setSize(75);
        aut_alumno.setMetodoChange("selecionoAutocompletar");
        bar_botones.agregarComponente(new Etiqueta("Alumno :"));
        bar_botones.agregarComponente(aut_alumno);

        tab_cabecera.setId("tab_cabecera");
        tab_cabecera.setTabla("yavirac_nota_cab_rec_acad", "ide_ynocra", 1);
        tab_cabecera.setHeader("CABECERA RECORD ACADEMICO");
        tab_cabecera.setCondicion("ide_ynocra=-1");
        tab_cabecera.agregarRelacion(tab_detalle);
        tab_cabecera.getColumna("ide_yaldap").setVisible(false);
        tab_cabecera.getColumna("ide_ystmen").setCombo(ser_estructura.getMension());
        tab_cabecera.getColumna("ide_ystmen").setAutoCompletar();
        tab_cabecera.getColumna("ide_ynocra").setNombreVisual("CODIGO");
        tab_cabecera.getColumna("ide_ystmen").setNombreVisual("MENSION");
        tab_cabecera.getColumna("fecha_inicio_ynocra").setNombreVisual("FECHA INICIO");
        tab_cabecera.getColumna("fecha_fin_ynocra").setNombreVisual("FECHA CULMINACIÓN");
        tab_cabecera.getColumna("ide_ystmen").setLectura(true);
        tab_cabecera.getColumna("fecha_inicio_ynocra").setLectura(true);
        tab_cabecera.getColumna("fecha_fin_ynocra").setLectura(true);
        tab_cabecera.dibujar();

        PanelTabla pa_cabecera = new PanelTabla();
        pa_cabecera.setId("pa_cabecera");
        pa_cabecera.setPanelTabla(tab_cabecera);

        tab_detalle.setId("tab_detalle");
        tab_detalle.setHeader("DETALLE RECORD ACADEMICO");
        tab_detalle.setTabla("yavirac_nota_det_rec_acad", "ide_ynodra", 2);
        tab_detalle.getColumna("ide_ystmat").setVisible(false);
        tab_detalle.getColumna("ide_ynoest").setCombo(ser_notas.getEstadoNota());
        tab_detalle.getColumna("ide_ystpea").setCombo(ser_estructura.getPeriodoAcademico("true,false"));
        tab_detalle.getColumna("ide_ymatrc").setCombo(ser_matricula.getTipoRegitroCredito());
        tab_detalle.getColumna("ide_ymanum").setCombo(ser_matricula.getNumeroMatricula());
        tab_detalle.getColumna("ide_ystmal").setCombo(ser_estructura.getMalla());

        tab_detalle.getColumna("ide_ystpea").setAutoCompletar();
        tab_detalle.getColumna("ide_ystmal").setAutoCompletar();
        //tab_detalle.getColumna("ide_ynoest").setLectura(true);
        tab_detalle.getColumna("ide_ystpea").setLectura(true);
        tab_detalle.getColumna("ide_ymatrc").setVisible(false);
        tab_detalle.getColumna("ide_ymanum").setLectura(true);
        tab_detalle.getColumna("ide_ystmal").setLectura(true);
        tab_detalle.getColumna("codigo_mate_ynodra").setVisible(false);
        tab_detalle.getColumna("num_creditos_ynodra").setVisible(false);
        tab_detalle.getColumna("ide_ynoest").setAncho(-1);
        tab_detalle.getColumna("ide_ynoest").setLongitud(-1);
        tab_detalle.getColumna("ide_ystpea").setAncho(-1);
        tab_detalle.getColumna("ide_ystpea").setLongitud(-1);
        tab_detalle.getColumna("ide_ymatrc").setAncho(-1);
        tab_detalle.getColumna("ide_ymatrc").setLongitud(-1);
        tab_detalle.getColumna("ide_ymanum").setAncho(-1);
        tab_detalle.getColumna("ide_ymanum").setLongitud(-1);
        tab_detalle.getColumna("ide_ystmal").setAncho(-1);
        tab_detalle.getColumna("ide_ystmal").setLongitud(-1);
        tab_detalle.getColumna("ide_ynodra").setNombreVisual("CODIGO");
        tab_detalle.getColumna("ide_ynoest").setNombreVisual("ESTADO");
        tab_detalle.getColumna("ide_ystpea").setNombreVisual("PERIODO ACADÉMICO");
        tab_detalle.getColumna("ide_ymanum").setNombreVisual("N° MATRICULA");
        tab_detalle.getColumna("ide_ymatrc").setNombreVisual("TIPO APROBACIÓN");
        tab_detalle.getColumna("ide_ystmal").setNombreVisual("MATERIA");
        tab_detalle.getColumna("codigo_mate_ynodra").setNombreVisual("CODIGO MATERIA");
        tab_detalle.getColumna("num_creditos_ynodra").setNombreVisual("N° CRÉDITOS");
        tab_detalle.getColumna("nota_ynodra").setNombreVisual("CALIFICACIÓN");
        tab_detalle.getColumna("observacion_ynodra").setNombreVisual("OBSERVACIÓN");
        tab_detalle.getColumna("nota_ynodra").setMetodoChange("validarNotaEvaluacion");
        tab_detalle.setRows(12);
        tab_detalle.dibujar();

        PanelTabla pa_detalle = new PanelTabla();
        pa_detalle.setId("pa_detalle");
        pa_detalle.setPanelTabla(tab_detalle);

        Division div_record = new Division();
        div_record.setId("div_nota");
        div_record.dividir2(pa_cabecera, pa_detalle, "15%", "h");
        agregarComponente(div_record);

        bar_botones.agregarSeparador();

        Boton bot_limpiar = new Boton();
        bot_limpiar.setIcon("ui-icon-cancel");
        bot_limpiar.setMetodo("limpiar");
        bar_botones.agregarBoton(bot_limpiar);

        bar_botones.agregarSeparador();

        Boton bot_nota = new Boton();
        bot_nota.setValue("Record General");
        bot_nota.setIcon("ui-icon-print");
        bot_nota.setMetodo("generarPDF");
        bar_botones.agregarBoton(bot_nota);

        Boton bot_record_detalle = new Boton();
        bot_record_detalle.setValue("Record Detallado");
        bot_record_detalle.setIcon("ui-icon-print");
        bot_record_detalle.setMetodo("abrirReporte");
        bar_botones.agregarBoton(bot_record_detalle);

        bar_botones.agregarSeparador();

        Boton bot_fecha = new Boton();
        bot_fecha.setId("bot_fecha");
        bot_fecha.setValue("Cambiar Fecha");
        bot_fecha.setTitle("Actualizar Fecha Fin de Carrera");
        bot_fecha.setIcon(" ui-icon-calculator");
        bot_fecha.setMetodo("actualizarFecha");
        bar_botones.agregarBoton(bot_fecha);

        vipdf_record.setId("vipdf_record");
        vipdf_record.setTitle("RECORD ACADEMICO");
        agregarComponente(vipdf_record);

        sel_tab.setId("sel_tab");
        sel_tab.setSeleccionTabla(ser_estructura.getPeriodoAcademico("true,false"), "ide_ystpea");
        sel_tab.getBot_aceptar().setMetodo("aceptarReporte");
        agregarComponente(sel_tab);

        vipdf_detalle.setId("vipdf_detalle");
        vipdf_detalle.setTitle("RECORD ACADEMICO DETALLADO");
        agregarComponente(vipdf_detalle);

        dia_dialogo.setId("dia_dialogo");
        dia_dialogo.setTitle("ACTUALIZAR LA FECHA");
        dia_dialogo.setWidth("25%");
        dia_dialogo.setHeight("25%");
        dia_dialogo.setResizable(false);

        Grid gri_cuerpo = new Grid();
        gri_cuerpo.setColumns(1);
        gri_cuerpo.setWidth("100%");
        gri_cuerpo.setStyle("width:100%;overflow: auto;display: block;");
        gri_cuerpo.getChildren().clear();
        Etiqueta eti_mensaje = new Etiqueta();
        eti_mensaje.setValue("Actualizar la  fecha de culminación de la carrera");
        eti_mensaje.setStyle("font-size: 14px;border: none;text-shadow: 0px 2px 3px #ccc;background: none;");
        Espacio esp = new Espacio();
        Grid gru_cuerpo = new Grid();
        gru_cuerpo.setColumns(2);

        gru_cuerpo.getChildren().add(new Etiqueta("FECHA CULMINACIÓN: "));
        gru_cuerpo.getChildren().add(cal_fecha);
        gri_cuerpo.getChildren().add(eti_mensaje);
        gri_cuerpo.getChildren().add(esp);
        gri_cuerpo.getChildren().add(gru_cuerpo);
        dia_dialogo.getBot_aceptar().setMetodo("confirmarFecha");
        dia_dialogo.setDialogo(gri_cuerpo);
        agregarComponente(dia_dialogo);

    }

    public void validarNotaEvaluacion(AjaxBehaviorEvent evt) {
        tab_detalle.modificar(evt);
        String cod = tab_detalle.getValor(tab_detalle.getFilaActual(), "ide_ystpea");
        String nota = tab_detalle.getValor(tab_detalle.getFilaActual(),"nota_ynodra");
        TablaGenerica tab_consuta = utilitario.consultar(ser_estructura_organizacional.getPeriodoAcademicoGeneral(cod, "true,false", "0"));
        String notaglobal = tab_consuta.getValor("nota_evaluacion_ystpea");
        String notarecu = tab_consuta.getValor("nota_recuperacion_ystpea");
        Double notaevaluacion = Double.parseDouble(notaglobal);
        Double notaactividad = Double.parseDouble(nota);
        Double recuperacion = Double.parseDouble(notarecu);

        if (notaactividad < 0) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "No puede ingresar calificaciones menores a 0");
            tab_detalle.setValor("nota_ynodra", "0");
            utilitario.addUpdate("tab_detalle");
            return;
        } else if (notaactividad > notaevaluacion) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "No puede ingresar calificaciones mayores a " + notaevaluacion);
            tab_detalle.setValor("nota_ynodra", "0");
            utilitario.addUpdate("tab_detalle");
            return;
        }

    }

    String periodo = "";

    public void actualizarFecha() {
        if (aut_alumno.getValue() != null) {
            if (tab_cabecera.getTotalFilas() > 0) {
                cal_fecha.limpiar();
                dia_dialogo.dibujar();
            } else {
                utilitario.agregarMensajeInfo("ADVERTENCIA,", "El estudiante no se encuentra registrado en ninguna carrera");
            }
        } else {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione un alumno");
        }
    }

    public void confirmarFecha() {
        if (cal_fecha.getValue() == null || cal_fecha.getValue().toString().isEmpty()) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Ingrese la fecha de culminación de carrera");
            return;
        } else {
            utilitario.getConexion().ejecutarSql(ser_notas.getActualizarFechaFinRecord(cal_fecha.getFecha(), aut_alumno.getValor(), tab_cabecera.getValor(tab_cabecera.getFilaActual(), "ide_ystmen")));
            utilitario.agregarMensaje("SUCESSFUL,", "Se actualizo la fecha correctamente");
            tab_cabecera.actualizar();
            dia_dialogo.cerrar();
        }
    }

    public void abrirReporte() {
        if (aut_alumno.getValue() != null) {
            if (tab_cabecera.getTotalFilas() > 0) {
                sel_tab.dibujar();
            } else {
                utilitario.agregarMensajeInfo("ADVERTENCIA,", "El estudiante no se encuentra registrado en ninguna carrera");
            }
        } else {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione un alumno");
        }
    }

    public void aceptarReporte() {
        if (sel_tab.getSeleccionados() != "") {
            periodo = sel_tab.getSeleccionados();
            Map map_parametros = new HashMap();
            map_parametros.put("ide_ystpea", periodo);
            map_parametros.put("ide_yaldap", Integer.parseInt(aut_alumno.getValor()));
            map_parametros.put("ide_ystmen", Integer.parseInt(tab_cabecera.getValor(tab_cabecera.getFilaActual(), "ide_ystmen")));
            vipdf_detalle.setVisualizarPDF("rep_nota/rep_record_academico_detallado.jasper", map_parametros);
            vipdf_detalle.dibujar();
            utilitario.addUpdate("vipdf_detalle");
            sel_tab.cerrar();
        } else {
            utilitario.agregarMensajeInfo("ADEVRTENCIA,", "Seleccione al menos un datos");
        }
    }

    public void limpiar() {
        aut_alumno.limpiar();
        tab_cabecera.limpiar();
        tab_detalle.limpiar();
    }

    public void generarPDF() {
        if (aut_alumno.getValue() != null) {
            if (tab_cabecera.getTotalFilas() > 0) {
                Map map_parametros = new HashMap();
                map_parametros.put("ide_yaldap", Integer.parseInt(aut_alumno.getValor()));
                map_parametros.put("ide_ystmen", Integer.parseInt(tab_cabecera.getValor(tab_cabecera.getFilaActual(), "ide_ystmen")));
                vipdf_record.setVisualizarPDF("rep_nota/rep_record_academico.jasper", map_parametros);
                vipdf_record.dibujar();
                utilitario.addUpdate("vipdf_record");
            } else {
                utilitario.agregarMensajeInfo("ADVERTENCIA,", "El estudiante no se encuentra registrado en ninguna carrera");
            }
        } else {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione un alumno");
        }
    }

    public void selecionoAutocompletar() {
        tab_cabecera.setCondicion("ide_yaldap=" + aut_alumno.getValor());
        tab_cabecera.ejecutarSql();
        utilitario.addUpdate("tab_cabecera");
    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        tab_detalle.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_cabecera() {
        return tab_cabecera;
    }

    public void setTab_cabecera(Tabla tab_cabecera) {
        this.tab_cabecera = tab_cabecera;
    }

    public Tabla getTab_detalle() {
        return tab_detalle;
    }

    public void setTab_detalle(Tabla tab_detalle) {
        this.tab_detalle = tab_detalle;
    }

    public AutoCompletar getAut_alumno() {
        return aut_alumno;
    }

    public void setAut_alumno(AutoCompletar aut_alumno) {
        this.aut_alumno = aut_alumno;
    }

    public VisualizarPDF getVipdf_record() {
        return vipdf_record;
    }

    public void setVipdf_record(VisualizarPDF vipdf_record) {
        this.vipdf_record = vipdf_record;
    }

    public VisualizarPDF getVipdf_detalle() {
        return vipdf_detalle;
    }

    public void setVipdf_detalle(VisualizarPDF vipdf_detalle) {
        this.vipdf_detalle = vipdf_detalle;
    }

    public SeleccionTabla getSel_tab() {
        return sel_tab;
    }

    public void setSel_tab(SeleccionTabla sel_tab) {
        this.sel_tab = sel_tab;
    }

}

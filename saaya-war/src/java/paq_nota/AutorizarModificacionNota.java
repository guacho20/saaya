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
import framework.componentes.Combo;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Texto;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;
import paq_alumno.ejb.ServicioAlumno;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_matricula.ejb.ServicioMatriculas;
import paq_nota.ejb.ServicioNotas;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHON
 */
public class AutorizarModificacionNota extends Pantalla {

    private Combo com_periodo_academico = new Combo();
    private Combo com_materia_docente = new Combo();
    private Tabla tab_docente_mencion = new Tabla();
    private Etiqueta eti_docente = new Etiqueta();
    private Etiqueta eti_materia = new Etiqueta();
    private Etiqueta eti_fecha_asistencia = new Etiqueta();
    private Tabla tab_detalle_nota = new Tabla();
    private Tabla tab_cabecera_nota = new Tabla();
    private Tabla tab_detalle_autorizacion = new Tabla();
    private Dialogo dia_dialogo = new Dialogo();
    private final Combo com_actividad = new Combo();
    private Texto tex_detalle = new Texto();
    private Calendario cal_fecha_calificacion = new Calendario();
    private Etiqueta eti_notificacion = new Etiqueta();
    private AutoCompletar aut_docente = new AutoCompletar();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioMatriculas ser_matricula = (ServicioMatriculas) utilitario.instanciarEJB(ServicioMatriculas.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    public AutorizarModificacionNota() {
        // if (TienePerfilNota()) {

        bar_botones.getBot_atras().setRendered(false);
        bar_botones.getBot_fin().setRendered(false);
        bar_botones.getBot_siguiente().setRendered(false);
        bar_botones.getBot_inicio().setRendered(false);

        aut_docente.setId("aut_docente");
        aut_docente.setAutoCompletar(ser_personal.getDatopersonal("true,false"));
        aut_docente.setSize(40);
        aut_docente.setMetodoChange("AutoFiltro");
        bar_botones.agregarComponente(new Etiqueta("Docente:"));
        bar_botones.agregarComponente(aut_docente);

        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true,false"));
        bar_botones.agregarComponente(new Etiqueta("Periodo Académico:"));
        bar_botones.agregarComponente(com_periodo_academico);
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");

        com_materia_docente.setId("com_materia_docente");
        com_materia_docente.setMetodo("mostrarNota");
        com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente("-1", "2"));

        bar_botones.agregarComponente(new Etiqueta("Curso:"));
        bar_botones.agregarComponente(com_materia_docente);

        eti_docente.setStyle("font-size: 16px;font-weight: bold");
        eti_docente.setValue("Docente: " + docente);
        eti_notificacion.setId("eti_notificacion");
        // TAB 1 cabecera nota
        tab_cabecera_nota.setId("tab_cabecera_nota");  // todo objeto instanciado poner id 
        tab_cabecera_nota.setTabla("yavirac_nota_cabecera_nota", "ide_ynocan", 1);    // nom bdd
        tab_cabecera_nota.agregarRelacion(tab_detalle_nota);
        tab_cabecera_nota.setCondicion("ide_ynocan=-1");
        tab_cabecera_nota.getColumna("ide_ynocan").setNombreVisual("CODIGO");
        tab_cabecera_nota.getColumna("ide_ynopae").setCombo(ser_notas.getPeriodoActividadEvaluacion("0", "0", "true,false", "0"));
        tab_cabecera_nota.getColumna("ide_ystpea").setVisible(false);
        tab_cabecera_nota.getColumna("ide_ystmen").setVisible(false);
        tab_cabecera_nota.getColumna("ide_ystnie").setVisible(false);
        tab_cabecera_nota.getColumna("ide_ypedpe").setVisible(false);
        tab_cabecera_nota.getColumna("ide_yhogra").setVisible(false);
        tab_cabecera_nota.getColumna("ide_ystjor").setVisible(false);
        tab_cabecera_nota.getColumna("ide_ystmal").setVisible(false);
        tab_cabecera_nota.getColumna("ide_ynopae").setAutoCompletar();
        tab_cabecera_nota.getColumna("ide_ynopae").setLectura(true);
        tab_cabecera_nota.getColumna("ide_ynopae").setNombreVisual("ACTIVIDAD EVALUACIÓN");
        tab_cabecera_nota.getColumna("detalle_ynocan").setLectura(true);
        tab_cabecera_nota.getColumna("detalle_ynocan").setNombreVisual("DETALLE");
        tab_cabecera_nota.getColumna("fecha_calificacion_ynocan").setLectura(true);
        tab_cabecera_nota.getColumna("fecha_calificacion_ynocan").setNombreVisual("FECHA CALIFICACIÓN");
        tab_cabecera_nota.getColumna("ide_ynopae").setFiltro(true);
        tab_cabecera_nota.getColumna("detalle_ynocan").setFiltro(true);
        tab_cabecera_nota.setRows(5);
        tab_cabecera_nota.dibujar();

        PanelTabla pa_cabecera_nota = new PanelTabla();
        pa_cabecera_nota.setId("pa_cabecera_nota"); // nombre de i
        pa_cabecera_nota.setPanelTabla(tab_cabecera_nota);


        /* TAB 2 detalle nota*/
        tab_detalle_nota.setId("tab_detalle_nota");
        tab_detalle_nota.setTabla("yavirac_nota_detalle_nota", "ide_ynodet", 2);
        tab_detalle_nota.agregarRelacion(tab_detalle_autorizacion);
        tab_detalle_nota.setHeader("DETALLE NOTA");
        tab_detalle_nota.getColumna("ide_ynodet").setNombreVisual("CODIGO");
        tab_detalle_nota.getColumna("ide_yaldap").setNombreVisual("ALUMNO/A");
        tab_detalle_nota.getColumna("nota_ynodet").setNombreVisual("NOTA");
        tab_detalle_nota.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
        tab_detalle_nota.getColumna("ide_yaldap").setAutoCompletar();
        tab_detalle_nota.getColumna("ide_yaldap").setLectura(true);
        tab_detalle_nota.getColumna("nota_ynodet").setLectura(true);
        tab_detalle_nota.getColumna("recuperacion_ynodet").setLectura(true);
        tab_detalle_nota.getColumna("recuperacion_ynodet").setVisible(false);
        tab_detalle_nota.getColumna("recuperacion_ynodet").setValorDefecto("false");
        tab_detalle_nota.getColumna("bloqueo_ynodet").setVisible(false);
        tab_detalle_nota.dibujar();
        tab_detalle_nota.setRows(35);
        PanelTabla pa_detalle_nota = new PanelTabla();
        pa_detalle_nota.setId("pa_detalle_nota"); // nombre de i
        pa_detalle_nota.setPanelTabla(tab_detalle_nota);

        tab_detalle_autorizacion.setId("tab_detalle_autorizacion");
        tab_detalle_autorizacion.setTabla("yavirac_nota_detalle_autorizac", "ide_ynodau", 3);
        tab_detalle_autorizacion.setHeader("AUTORIZAR MODIFICAR NOTA");
        tab_detalle_autorizacion.getColumna("ide_ynoest").setCombo(ser_notas.getEstadoNota());
        tab_detalle_autorizacion.getColumna("ide_ynoest").setLongitud(-1);
        tab_detalle_autorizacion.getColumna("ide_ynoest").setAncho(-1);
        tab_detalle_autorizacion.getColumna("ide_ynoest").setValorDefecto(utilitario.getVariable("p_estado_autorizado"));
        tab_detalle_autorizacion.getColumna("ide_ynoest").setLectura(true);
        tab_detalle_autorizacion.getColumna("nota_ynodau").setValorDefecto("0");
        tab_detalle_autorizacion.getColumna("fecha_autorizacion_ynodau").setLectura(true);
        tab_detalle_autorizacion.getColumna("nota_anterior_ynodau").setLectura(true);
        tab_detalle_autorizacion.getColumna("ide_ypedpe").setVisible(false);
        tab_detalle_autorizacion.getColumna("fecha_registro_ynodau").setLectura(true);
        tab_detalle_autorizacion.dibujar();

        PanelTabla pa_detalle_autorizacion = new PanelTabla();
        pa_detalle_autorizacion.setId("pa_detalle_autorizacion"); // nombre de i
        pa_detalle_autorizacion.setPanelTabla(tab_detalle_autorizacion);

        ///// tabuladores
        Division div_nota = new Division();
        div_nota.setId("div_nota");
        //div_nota.dividir1(pa_detalle_nota);
        div_nota.dividir3(pa_cabecera_nota, pa_detalle_nota, pa_detalle_autorizacion, "20%", "30%", "h");
        agregarComponente(div_nota);

    }

    String docente = "";
    String documento = "";
    String ide_docente = "";

    public void filtraAlumno() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("Adevertencia,", "Seleccione el Periodo Académico");
            return;
        }
        if (com_materia_docente.getValue() == null) {
            utilitario.agregarMensajeInfo("Adevertencia,", "Seleccione la materia que desea consultar la asistencia");
            return;
        }
    }

    public void filtraEstudiantes() {
        String malla = tab_docente_mencion.getValorSeleccionado();
        TablaGenerica tab_malla = utilitario.consultar("select ide_ypemad,ide_ystmal,ide_ypedpe from yavirac_perso_malla_docente where ide_ypemad=" + malla);

    }

    public void AutoFiltro(SelectEvent evt) {
        aut_docente.onSelect(evt);
        ide_docente = aut_docente.getValor();
        com_periodo_academico.limpiar();
        com_materia_docente.limpiar();
        tab_cabecera_nota.limpiar();
        tab_detalle_nota.limpiar();
        tab_detalle_autorizacion.limpiar();
    }

    public void filtroComboPeriodoAcademico() {
        if (ide_docente.equals("")) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccion un docente");
            com_periodo_academico.limpiar();
        } else {
            com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente(com_periodo_academico.getValue().toString(), ide_docente));
            utilitario.addUpdate("com_materia_docente");
        }
    }

    public void validarNotaEvaluDacion(AjaxBehaviorEvent evt) {
        tab_detalle_nota.modificar(evt);
        String cod = com_periodo_academico.getValue() + "";
        String nota = tab_detalle_nota.getValor("nota_ynodet");
        TablaGenerica tab_consuta = utilitario.consultar(ser_estructura_organizacional.getPeriodoAcademicoGeneral(cod, "true", "0"));
        String notaglobal = tab_consuta.getValor("nota_evaluacion_ystpea");
        String notarecu = tab_consuta.getValor("nota_recuperacion_ystpea");
        Double notaevaluacion = Double.parseDouble(notaglobal);
        Double notaactividad = Double.parseDouble(nota);
        Double recuperacion = Double.parseDouble(notarecu);

        if (notaactividad < 0) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "No puede ingresar calificaciones menores a 0");
            tab_detalle_nota.setValor("nota_ynodet", "0");
            utilitario.addUpdate("tab_detalle_nota");
            return;
        } else if (notaactividad > notaevaluacion) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "No puede ingresar calificaciones mayores a " + notaevaluacion);
            tab_detalle_nota.setValor("nota_ynodet", "0");
            utilitario.addUpdate("tab_detalle_nota");
            return;
        }
        //validar si el examen tiene recuperacion
        TablaGenerica tab_examen = utilitario.consultar(ser_notas.getConsultaTipoExamen(tab_cabecera_nota.getValor(tab_cabecera_nota.getFilaActual(), "ide_ynocan"), utilitario.getVariable("p_tipo_eva_examen")));
        TablaGenerica tab_validacion = utilitario.consultar(ser_estructura_organizacional.getPeriodoAcademicoGeneral(cod, "true", "1"));
        if (tab_validacion.getTotalFilas() > 0) {

            if (tab_examen.getTotalFilas() > 0) {
                if (notaactividad < recuperacion) {
                    tab_detalle_nota.setValor("recuperacion_ynodet", "true");
                    utilitario.addUpdate("tab_detalle_nota");
                    tab_detalle_nota.guardar();
                    guardarPantalla();
                } else {
                    tab_detalle_nota.setValor("recuperacion_ynodet", "false");
                    utilitario.addUpdate("tab_detalle_nota");
                    tab_detalle_nota.guardar();
                    guardarPantalla();
                }
            }
        } else {
            tab_detalle_nota.setValor("recuperacion_ynodet", "false");
            utilitario.addUpdate("tab_detalle_nota");
            tab_detalle_nota.guardar();
            guardarPantalla();
        }

        utilitario.addUpdate("tab_detalle_nota");

    }

    public void limpiar() {
        tab_cabecera_nota.limpiar();
        tab_detalle_nota.limpiar();
        com_materia_docente.setValue("");
        com_periodo_academico.setValue("");
        utilitario.addUpdate(" tab_cabecera_nota,tab_detalle_nota,com_materia_docente,com_periodo_academico");
    }

    public void mostrarNota() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione el Periodo Académico");
            return;
        } else {
            String cod = com_materia_docente.getValue() + "";
            TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));
            tab_cabecera_nota.setCondicion("ide_ystpea =" + com_periodo_academico.getValue() + " and ide_ystnie=" + tab_consuta.getValor("ide_ystnie") + " and ide_yhogra=" + tab_consuta.getValor("ide_yhogra") + " and ide_ystjor=" + tab_consuta.getValor("ide_ystjor") + " and ide_ypedpe=" + tab_consuta.getValor("ide_ypedpe") + " and ide_ystmal=" + tab_consuta.getValor("ide_ystmal"));
            tab_cabecera_nota.ejecutarSql();
            tab_detalle_nota.ejecutarValorForanea(tab_cabecera_nota.getValorSeleccionado());

        }
    }

    @Override
    public void insertar() {
        tab_detalle_autorizacion.insertar();
        tab_detalle_autorizacion.setValor("ide_ypedpe", ide_docente);
        tab_detalle_autorizacion.setValor("fecha_autorizacion_ynodau", utilitario.getFechaActual());
        tab_detalle_autorizacion.setValor("nota_anterior_ynodau", tab_detalle_nota.getValor(tab_detalle_nota.getFilaActual(), "nota_ynodet"));
    }

    @Override
    public void guardar() {

        if (tab_detalle_autorizacion.guardar()) {
            guardarPantalla();
        }

    }

    @Override
    public void eliminar() {
        tab_detalle_autorizacion.eliminar();
    }

    public AutoCompletar getAut_docente() {
        return aut_docente;
    }

    public void setAut_docente(AutoCompletar aut_docente) {
        this.aut_docente = aut_docente;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public Combo getCom_materia_docente() {
        return com_materia_docente;
    }

    public void setCom_materia_docente(Combo com_materia_docente) {
        this.com_materia_docente = com_materia_docente;
    }

    public Tabla getTab_docente_mencion() {
        return tab_docente_mencion;
    }

    public void setTab_docente_mencion(Tabla tab_docente_mencion) {
        this.tab_docente_mencion = tab_docente_mencion;
    }

    public Etiqueta getEti_docente() {
        return eti_docente;
    }

    public void setEti_docente(Etiqueta eti_docente) {
        this.eti_docente = eti_docente;
    }

    public Etiqueta getEti_materia() {
        return eti_materia;
    }

    public void setEti_materia(Etiqueta eti_materia) {
        this.eti_materia = eti_materia;
    }

    public Etiqueta getEti_fecha_asistencia() {
        return eti_fecha_asistencia;
    }

    public void setEti_fecha_asistencia(Etiqueta eti_fecha_asistencia) {
        this.eti_fecha_asistencia = eti_fecha_asistencia;
    }

    public Tabla getTab_detalle_nota() {
        return tab_detalle_nota;
    }

    public void setTab_detalle_nota(Tabla tab_detalle_nota) {
        this.tab_detalle_nota = tab_detalle_nota;
    }

    public Tabla getTab_cabecera_nota() {
        return tab_cabecera_nota;
    }

    public void setTab_cabecera_nota(Tabla tab_cabecera_nota) {
        this.tab_cabecera_nota = tab_cabecera_nota;
    }

    public Dialogo getDia_dialogo() {
        return dia_dialogo;
    }

    public void setDia_dialogo(Dialogo dia_dialogo) {
        this.dia_dialogo = dia_dialogo;
    }

    public Texto getTex_detalle() {
        return tex_detalle;
    }

    public void setTex_detalle(Texto tex_detalle) {
        this.tex_detalle = tex_detalle;
    }

    public Calendario getCal_fecha_calificacion() {
        return cal_fecha_calificacion;
    }

    public void setCal_fecha_calificacion(Calendario cal_fecha_calificacion) {
        this.cal_fecha_calificacion = cal_fecha_calificacion;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getIde_docente() {
        return ide_docente;
    }

    public void setIde_docente(String ide_docente) {
        this.ide_docente = ide_docente;
    }

    public Etiqueta getEti_notificacion() {
        return eti_notificacion;
    }

    public void setEti_notificacion(Etiqueta eti_notificacion) {
        this.eti_notificacion = eti_notificacion;
    }

    public Tabla getTab_detalle_autorizacion() {
        return tab_detalle_autorizacion;
    }

    public void setTab_detalle_autorizacion(Tabla tab_detalle_autorizacion) {
        this.tab_detalle_autorizacion = tab_detalle_autorizacion;
    }
}

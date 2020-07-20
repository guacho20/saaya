/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import java.util.List;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_inscripcion.ejb.ServicioInscripcion;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author CRISTIAN VEGA
 */
public class AsignacionDocente extends Pantalla {

    private Combo com_mension = new Combo();
    private Combo com_periodo_academico = new Combo();
    private Boton bot_clean = new Boton();
    private Tabla tab_asignaciondocente = new Tabla();
    private Tabla tab_docentealumno = new Tabla();
    private Tabla tab_docenteseguimiento = new Tabla();
    private SeleccionTabla sel_registra_alumno = new SeleccionTabla();

    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioInscripcion ser_inscripcion = (ServicioInscripcion) utilitario.instanciarEJB(ServicioInscripcion.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public AsignacionDocente() {

        if (TienePermiso()) {

            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
            bar_botones.agregarComponente(new Etiqueta("PERIODO ACADEMICO:"));
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");       

            com_mension.setId("com_mension");
            com_mension.setCombo(ser_inscripcion.getSqlDocenteMension("-1", "-1"));
            bar_botones.agregarComponente(new Etiqueta("CARRERA:"));
            bar_botones.agregarComponente(com_mension);
            com_mension.setMetodo("filtroDocentes");

            bot_clean.setIcon("ui-icon-cancel");
            bot_clean.setTitle("Limpiar");
            bot_clean.setMetodo("limpiar");
            bar_botones.agregarComponente(bot_clean);

            tab_asignaciondocente.setId("tab_asignaciondocente");
            tab_asignaciondocente.setTabla("yavirac_ins_coordin_docent_as", "ide_yincda", 1);
            tab_asignaciondocente.setCondicion("ide_yincda= -1");
            tab_asignaciondocente.agregarRelacion(tab_docentealumno);
            tab_asignaciondocente.getColumna("ide_yincda").setNombreVisual("CODIGO");
            tab_asignaciondocente.setHeader("COORDINADOR: " + docente);
            tab_asignaciondocente.getColumna("ide_yindom").setVisible(false);
            tab_asignaciondocente.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true"));
            tab_asignaciondocente.getColumna("ide_ypedpe").setAutoCompletar();
            tab_asignaciondocente.getColumna("ide_ypedpe").setNombreVisual("DATO DOCENTE");
            tab_asignaciondocente.getColumna("observacion_yincda").setNombreVisual("OBSERVACION");
            
            tab_asignaciondocente.dibujar();

            PanelTabla pat_asignaciondocente = new PanelTabla();
            pat_asignaciondocente.setId("pat_asignaciondocente");
            pat_asignaciondocente.setPanelTabla(tab_asignaciondocente);

            tab_docentealumno.setId("tab_docentealumno");
            tab_docentealumno.setTabla("yavirac_ins_docente_alumno", "ide_yindoa", 2);
            tab_docentealumno.getColumna("ide_yindoa").setNombreVisual("CODIGO");
            tab_docentealumno.setHeader("DOCENTE ALUMNO");
            tab_docentealumno.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true"));
            tab_docentealumno.getColumna("ide_yaldap").setNombreVisual("ALUMNO");
            tab_docentealumno.getColumna("ide_yaldap").setAutoCompletar();
            tab_docentealumno.getColumna("ide_yinpin").setVisible(false);
            tab_docentealumno.getColumna("asigna_yindoa").setVisible(false);
             tab_docentealumno.getColumna("ide_yaldap").setLectura(true);
            tab_docentealumno.dibujar();

            PanelTabla pat_docentealumno = new PanelTabla();
            pat_docentealumno.setId("pat_docentealumno");
            pat_docentealumno.setPanelTabla(tab_docentealumno);

            Division div_division1 = new Division();
            div_division1.setId("div_division1");
            div_division1.dividir2(pat_asignaciondocente, pat_docentealumno, "30%", "H");

            agregarComponente(div_division1);

            //BOTON REGISTRO DE ALUMNOS
            Boton bot_registroAlumno = new Boton();
            bot_registroAlumno.setValue("Listado Alumnos");
            bot_registroAlumno.setIcon("ui-icon-note");
            bot_registroAlumno.setMetodo("selregistraAlumno");
            bar_botones.agregarBoton(bot_registroAlumno);

            sel_registra_alumno.setId("sel_registra_alumno");
            sel_registra_alumno.setTitle("SELECCIONE EL ALUMNO");
            sel_registra_alumno.setSeleccionTabla(ser_inscripcion.getSqlAlumnosInscritos("-1", "-1"), "ide_yinpin");
            sel_registra_alumno.getTab_seleccion().getColumna("doc_identidad_yaldap").setFiltro(true);
            sel_registra_alumno.getTab_seleccion().getColumna("apellido_yaldap").setFiltro(true);
            sel_registra_alumno.getTab_seleccion().getColumna("nombre_yaldap").setFiltro(true);
            sel_registra_alumno.getTab_seleccion().getColumna("descripcion_ystmen").setFiltro(true);
            sel_registra_alumno.getTab_seleccion().getColumna("ide_yaldap").setVisible(false);
            sel_registra_alumno.getTab_seleccion().getColumna("ide_ystpea").setVisible(false);
            sel_registra_alumno.getTab_seleccion().getColumna("ide_ystmen").setVisible(false);
            sel_registra_alumno.getBot_aceptar().setMetodo("registrarAlumno");
            agregarComponente(sel_registra_alumno);

        } else {
            utilitario.agregarNotificacionInfo("Mensaje,", "EL usuario ingresado no registra permisos para la asignacion de docentes. Consulte con el Administrador");
        }
    }

    public void registrarAlumno() {
        String seleccionado = sel_registra_alumno.getSeleccionados();
        System.out.println("registrarAlumno()" + seleccionado);
        if (seleccionado.equals("null") || seleccionado.isEmpty()) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione al menos un registro");
        } else {
            TablaGenerica tab_consulta = utilitario.consultar("select * from yavirac_ins_pre_inscripcion where ide_yinpin in (" + seleccionado + ")");

            for (int i = 0; i < tab_consulta.getTotalFilas(); i++) {
                tab_docentealumno.insertar();
                tab_docentealumno.setValor("ide_yaldap", tab_consulta.getValor(i, "ide_yaldap"));
                tab_docentealumno.setValor("ide_yinpin", tab_consulta.getValor(i, "ide_yinpin"));
                tab_docentealumno.setValor("asigna_yindoa", "true");
            }
            tab_docentealumno.guardar();
            guardarPantalla();
            sel_registra_alumno.cerrar();
            //tab_docentealumno.actualizar();  

        }
    }

    public void filtroComboPeriodoAcademico() {

        com_mension.setCombo(ser_inscripcion.getSqlDocenteMension(com_periodo_academico.getValue().toString(), ide_docente));
        utilitario.addUpdate("com_mension");

    }

    public void filtroDocentes() {

        String cod = com_mension.getValue().toString() + "";
        //System.out.println("COD: "+cod);
        tab_asignaciondocente.setCondicion("ide_yindom=" + cod);
        tab_asignaciondocente.ejecutarSql();
        tab_asignaciondocente.imprimirSql();
        //tab_docentealumno.ejecutarValorForanea(tab_asignaciondocente.getValorSeleccionado());
        utilitario.addUpdate("tab_asignaciondocente");     
    }

    public void selregistraAlumno() {

        //Hace aparecer el componente
        if (com_periodo_academico.getValue() == null) {

            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Académico que desea generar");
            return;
        } else if (com_mension.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione la carrera");
            return;
        } else if (tab_asignaciondocente.getTotalFilas() > 0) {
            TablaGenerica tab_consulta = utilitario.consultar("select * from yavirac_ins_docente_mension where ide_yindom=" + com_mension.getValue());
            sel_registra_alumno.getTab_seleccion().setSql(ser_inscripcion.getSqlAlumnosInscritos(com_periodo_academico.getValue().toString(), tab_consulta.getValor("ide_ystmen")));
            sel_registra_alumno.getTab_seleccion().ejecutarSql();
            sel_registra_alumno.dibujar();
        } else {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Registre un docente para asignar los alumnos");
        }

    }

    @Override
    public void insertar() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Por favor, Seleccione el Periodo Academico");
            return;
        } else if (com_mension.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Por favor, Seleccione la Carrera");
            return;
        } else if (tab_asignaciondocente.isFocus()) {
            tab_asignaciondocente.insertar();
            tab_asignaciondocente.setValor("ide_yindom", com_mension.getValue().toString());
            utilitario.addUpdate("tab_asignaciondocente");
        } else if (tab_docenteseguimiento.isFocus()) {
            tab_docenteseguimiento.insertar();
        } /*tab_asignaciondocente.insertar();
            tab_asignaciondocente.setValor("ide_ypedpe", tab_consuta.getValor("ide_ypedpe"));
            tab_asignaciondocente.setValor("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
            tab_asignaciondocente.setValor("ide_ystmen", tab_consuta.getValor("ide_ystmen"));
            tab_asignaciondocente.setValor("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
            tab_asignaciondocente.setValor("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
            tab_asignaciondocente.setValor("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
            utilitario.addUpdate("tab_asignaciondocente");*/
    }

    @Override
    public void guardar() {
        if (tab_asignaciondocente.guardar()) {
            if (tab_docentealumno.guardar()) {
                if (tab_docenteseguimiento.guardar()) {
                    guardarPantalla();
                }
            }
        }
    }
    String docente = "";
    String documento = "";
    String ide_docente = "";

    private boolean TienePermiso() {
        List sql = utilitario.getConexion().consultar(ser_estructura_organizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA"), " and not ide_ypedpe is null"));
        System.out.println(" " + sql);
        if (!sql.isEmpty()) {
            Object[] fila = (Object[]) sql.get(0);
            List sql2 = utilitario.getConexion().consultar(ser_personal.getDatoPersonalCodigo(fila[3].toString()));
            if (!sql2.isEmpty()) {
                Object[] fila2 = (Object[]) sql2.get(0);
                docente = fila2[1].toString() + " " + fila2[2].toString();
                documento = fila2[3].toString();
                ide_docente = fila2[0].toString();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void eliminar() {
        if (tab_asignaciondocente.isFocus()) {
            tab_asignaciondocente.eliminar();
        } else if (tab_docentealumno.isFocus()) {
            tab_docentealumno.eliminar();
        } else if (tab_docenteseguimiento.isFocus()) {
            tab_docenteseguimiento.eliminar();
        }
    }

    public Tabla getTab_asignaciondocente() {
        return tab_asignaciondocente;
    }

    public void setTab_asignaciondocente(Tabla tab_asignaciondocente) {
        this.tab_asignaciondocente = tab_asignaciondocente;
    }

    public Tabla getTab_docentealumno() {
        return tab_docentealumno;
    }

    public void setTab_docentealumno(Tabla tab_docentealumno) {
        this.tab_docentealumno = tab_docentealumno;
    }

    public Tabla getTab_docenteseguimiento() {
        return tab_docenteseguimiento;
    }

    public void setTab_docenteseguimiento(Tabla tab_docenteseguimiento) {
        this.tab_docenteseguimiento = tab_docenteseguimiento;
    }

    public SeleccionTabla getSel_registra_alumno() {
        return sel_registra_alumno;
    }

    public void setSel_registra_alumno(SeleccionTabla sel_registra_alumno) {
        this.sel_registra_alumno = sel_registra_alumno;
    }

}
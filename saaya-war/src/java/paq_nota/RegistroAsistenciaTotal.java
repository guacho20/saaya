/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.aplicacion.TablaGenerica;
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
 * @author JHONPRODUCER
 */
public class RegistroAsistenciaTotal extends Pantalla {

    private Combo com_periodo_academico = new Combo();
    private Combo com_materia_docente = new Combo();
    private Tabla tab_docente_mencion = new Tabla();
    private Etiqueta eti_docente = new Etiqueta();
    private Etiqueta eti_materia = new Etiqueta();
    private Etiqueta eti_fecha_asistencia = new Etiqueta();
    private Tabla tab_detalle = new Tabla();
    private Tabla tab_cabecera = new Tabla();
    private Dialogo dia_dialogo = new Dialogo();
    private final Combo com_parcial = new Combo();

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
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public RegistroAsistenciaTotal() {
        if (TienePerfilNota()) {

            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
            bar_botones.agregarComponente(new Etiqueta("Periodo Académico "));
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");

            com_materia_docente.setId("com_materia_docente");
            com_materia_docente.setMetodo("mostrarAsistencia");
            com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente("-1", "2"));

            bar_botones.agregarComponente(new Etiqueta("Curso "));
            bar_botones.agregarComponente(com_materia_docente);

            //boton limpiar
            Boton bot_limpiar = new Boton();
            bot_limpiar.setIcon("ui-icon-cancel");
            bot_limpiar.setMetodo("limpiar");
            bar_botones.agregarBoton(bot_limpiar);

            //boton registrar notas
            Boton bot_nota = new Boton();
            bot_nota.setValue("Registrar Asistencia Total");
            bot_nota.setIcon("ui-icon-note");//set icono Registrar///
            bot_nota.setMetodo("registrarAsistencia");
            bar_botones.agregarBoton(bot_nota);

            eti_docente.setStyle("font-size: 16px;font-weight: bold");
            eti_docente.setValue("Docente: " + docente);

            // TAB 1 cabecera nota
            tab_cabecera.setId("tab_cabecera");
            tab_cabecera.setTabla("yavirac_nota_cabe_asistencia", "ide_ynocaa", 1);
            tab_cabecera.setCondicion("ide_ynocaa=-1");
            tab_cabecera.setHeader("Docente: " + docente);
            tab_cabecera.agregarRelacion(tab_detalle);
            tab_cabecera.getColumna("ide_ynocaa").setNombreVisual("CODIGO");
            tab_cabecera.getColumna("ide_ynotie").setNombreVisual("TIPO EVALUCION");
            tab_cabecera.getColumna("fecha_ynocaa").setNombreVisual("FECHA INGRESO");
            tab_cabecera.getColumna("ide_ynotie").setCombo(ser_notas.getTipoEvaluacion("true,false"));
            tab_cabecera.getColumna("ide_ystpea").setVisible(false);
            tab_cabecera.getColumna("ide_ystmen").setVisible(false);
            tab_cabecera.getColumna("ide_ystnie").setVisible(false);
            tab_cabecera.getColumna("ide_ypedpe").setVisible(false);
            tab_cabecera.getColumna("ide_yhogra").setVisible(false);
            tab_cabecera.getColumna("ide_ystjor").setVisible(false);
            tab_cabecera.getColumna("ide_ystmal").setVisible(false);
            tab_cabecera.getColumna("ide_ynotie").setLectura(true);
            tab_cabecera.getColumna("fecha_ynocaa").setLectura(true);
            tab_cabecera.onSelect("bloquearAsistencia");

            tab_cabecera.dibujar();

            PanelTabla pa_cabecera = new PanelTabla();
            pa_cabecera.setId("pa_cabecera"); // nombre de i
            pa_cabecera.setPanelTabla(tab_cabecera);

            /* TAB 2 detalle nota*/
            tab_detalle.setId("tab_detalle");
            tab_detalle.setTabla("yavirac_nota_det_asistencia", "ide_ynodea", 2);
            tab_detalle.getColumna("ide_yaldap").setNombreVisual("ALUMNO/A");
            tab_detalle.getColumna("total_asistencia").setNombreVisual("TOTAL ASISTENCIA");
            tab_detalle.getColumna("total_asistencia").setMetodoChange("validarNotaEvaluacion");
            tab_detalle.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
            tab_detalle.getColumna("ide_yaldap").setLectura(true);
            tab_detalle.getColumna("boqueado_ynodea").setVisible(false);
            tab_detalle.dibujar();
            tab_detalle.setRows(35);
            PanelTabla pa_detalle = new PanelTabla();
            pa_detalle.setId("pa_detalle"); // nombre de i
            pa_detalle.setPanelTabla(tab_detalle);

            Division div_asistencia = new Division();
            div_asistencia.setId("div_nota");
            div_asistencia.dividir2(pa_cabecera, pa_detalle, "15%", "h");
            agregarComponente(div_asistencia);

            //Dialogo
            dia_dialogo.setId("dia_dialogo");
            dia_dialogo.setTitle("REGISTRO ASISTENCIA");
            dia_dialogo.setWidth("25%");
            dia_dialogo.setHeight("15%");
            dia_dialogo.setResizable(false);

            Grid gri_cuerpo = new Grid();
            gri_cuerpo.setColumns(2);
            gri_cuerpo.setWidth("100%");
            gri_cuerpo.setStyle("width:100%;overflow: auto;display: block;");
            gri_cuerpo.getChildren().clear();
            gri_cuerpo.getChildren().add(new Etiqueta("PARCIAL: "));
            com_parcial.setCombo(ser_notas.getPeriodoActividadEvaluacion("-1", "0", "false", "-1"));
            gri_cuerpo.getChildren().add(com_parcial);

            dia_dialogo.getBot_aceptar().setMetodo("aceptarDialogo");
            dia_dialogo.setDialogo(gri_cuerpo);
            agregarComponente(dia_dialogo);

        } else {
            utilitario.agregarNotificacionInfo("Mensaje,", "EL usuario ingresado no registra permisos para el control de Asistencia. Consulte con el Administrador");
        }
    }

     public void validarNotaEvaluacion(AjaxBehaviorEvent evt) {
        tab_detalle.modificar(evt);
        String cod = com_periodo_academico.getValue() + "";
        String nota = tab_detalle.getValor("total_asistencia");
        TablaGenerica tab_consuta = utilitario.consultar(ser_estructura_organizacional.getPeriodoAcademicoGeneral(cod, "true,false", "0"));
        String notaglobal = tab_consuta.getValor("nota_evaluacion_ystpea");
        String notarecu = tab_consuta.getValor("nota_recuperacion_ystpea");
        Double notaevaluacion = Double.parseDouble(notaglobal);
        Double notaactividad = Double.parseDouble(nota);
        Double recuperacion = Double.parseDouble(notarecu);

        if (notaactividad < 0) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "No puede ingresar calificaciones menores a 0");
            tab_detalle.setValor("total_asistencia", "0");
            utilitario.addUpdate("tab_detalle");
            return;
        } else if (notaactividad > notaevaluacion) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "No puede ingresar calificaciones mayores a " + notaevaluacion);
            tab_detalle.setValor("total_asistencia", "0");
            utilitario.addUpdate("tab_detalle");
            return;
        }

    }
    
    public void bloquearAsistencia(SelectEvent evt) {
        tab_cabecera.seleccionarFila(evt);
        tab_cabecera.getFilaSeleccionada().setLectura(true);
        tab_detalle.getColumna("total_asistencia").setLectura(true);
        utilitario.addUpdate("tab_detalle");
        if (tab_detalle.getValor("boqueado_ynodea").equals("true")) {
            for (int i = 0; i < tab_detalle.getTotalFilas(); i++) {
                tab_detalle.getFila(i).setLectura(true);
            }
        }

    }

    public void mostrarAsistencia() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione el Periodo Académico");
            return;
        } else {
            String cod = com_materia_docente.getValue() + "";
            TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));
            tab_cabecera.setCondicion("ide_ystpea =" + com_periodo_academico.getValue() + " and ide_ystnie=" + tab_consuta.getValor("ide_ystnie") + " and ide_yhogra=" + tab_consuta.getValor("ide_yhogra") + " and ide_ystjor=" + tab_consuta.getValor("ide_ystjor") + " and ide_ypedpe=" + tab_consuta.getValor("ide_ypedpe") + " and ide_ystmal=" + tab_consuta.getValor("ide_ystmal"));
            tab_cabecera.ejecutarSql();
            tab_detalle.ejecutarValorForanea(tab_cabecera.getValorSeleccionado());
        }
    }

    public void registrarAsistencia() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione el Periodo Academico para Generar nota");
            return;
        } else if (com_materia_docente.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione el Curso para Generar Nota");
            return;
        } else {
            com_parcial.setCombo(ser_notas.getPeriodoEvaluacion(com_periodo_academico.getValue().toString(), "false"));
            abrirDialogo();
        }
    }

    public void abrirDialogo() {
        dia_dialogo.dibujar();
    }

    public void aceptarDialogo() {
        String cod = com_materia_docente.getValue() + "";
        TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));

        TablaGenerica tab_parcial = utilitario.consultar("select ide_ynopee,ide_ynotie from yavirac_nota_periodo_evaluacio where ide_ynopee=" + com_parcial.getValue());
        TablaGenerica tab_asistencia = utilitario.consultar(ser_notas.getConsultaParcialAsistencia(com_periodo_academico.getValue().toString(), tab_consuta.getValor("ide_ystmen"), tab_consuta.getValor("ide_ystnie"), tab_consuta.getValor("ide_ypedpe"), tab_consuta.getValor("ide_yhogra"), tab_consuta.getValor("ide_ystjor"), tab_consuta.getValor("ide_ystmal"), tab_parcial.getValor("ide_ynotie")));

        if (com_parcial.getValue() == null || com_parcial.getValue().toString().isEmpty()) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione el parcial");
            return;
        } else if (tab_asistencia.getTotalFilas() > 0) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Ya tiene registrado la asistencia para este parcial");
            return;
        } else {
            
            TablaGenerica tab_malla_docente = utilitario.consultar(ser_personal.getPersonalMalla(com_materia_docente.getValue().toString()));
            String malla = tab_malla_docente.getValor("ide_ystmal");
            String grupo = tab_malla_docente.getValor("ide_yhogra");
            String jornada = tab_malla_docente.getValor("ide_ystjor");
            tab_cabecera.insertar();
            tab_cabecera.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
            tab_cabecera.setValor("ide_ystmen", tab_consuta.getValor("ide_ystmen"));
            tab_cabecera.setValor("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
            tab_cabecera.setValor("ide_ypedpe", tab_consuta.getValor("ide_ypedpe"));
            tab_cabecera.setValor("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
            tab_cabecera.setValor("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
            tab_cabecera.setValor("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
            tab_cabecera.setValor("ide_ynotie", tab_parcial.getValor("ide_ynotie"));
            tab_cabecera.setValor("fecha_ynocaa", utilitario.getFechaActual());

            TablaGenerica tab_alumnos_asistencia = utilitario.consultar(ser_matricula.getAlumnosMallaGrupo(malla, grupo, com_periodo_academico.getValue().toString(), jornada));
            String maximo = "";
            for (int i = 0; i < tab_alumnos_asistencia.getTotalFilas(); i++) {
                tab_detalle.insertar();
                tab_detalle.setValor("ide_yaldap", tab_alumnos_asistencia.getValor(i, "ide_yaldap"));
                tab_detalle.setValor("total_asistencia", "0");
                tab_detalle.setValor("boqueado_ynodea", "false");
            }

            tab_cabecera.guardar();
            tab_detalle.guardar();
            guardarPantalla();
            utilitario.addUpdate("tab_cabecera_nota,tab_detalle_nota");
            dia_dialogo.cerrar();
            //tab_detalle_nota.actualizar();
        }

    }

    String docente = "";
    String documento = "";
    String ide_docente = "";

    private boolean TienePerfilNota() {
        List sql = utilitario.getConexion().consultar(ser_estructura_organizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA"), " and not ide_ypedpe is null"));

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

    public void filtroComboPeriodoAcademico() {

        com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente(com_periodo_academico.getValue().toString(), ide_docente));
        utilitario.addUpdate("com_materia_docente");

    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        if (tab_cabecera.guardar()) {
            if (tab_detalle.guardar()) {
                guardarPantalla();
            }
        }
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_docente_mencion() {
        return tab_docente_mencion;
    }

    public void setTab_docente_mencion(Tabla tab_docente_mencion) {
        this.tab_docente_mencion = tab_docente_mencion;
    }

    public Tabla getTab_detalle() {
        return tab_detalle;
    }

    public void setTab_detalle(Tabla tab_detalle) {
        this.tab_detalle = tab_detalle;
    }

    public Tabla getTab_cabecera() {
        return tab_cabecera;
    }

    public void setTab_cabecera(Tabla tab_cabecera) {
        this.tab_cabecera = tab_cabecera;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import java.util.List;
import javax.ejb.EJB;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHON
 */
public class PersonalActividad extends Pantalla {

    private Combo com_periodo_academico = new Combo();
    private Combo com_materia_docente = new Combo();
    private Tabla tab_docente_mencion = new Tabla();
    private Etiqueta eti_docente = new Etiqueta();
    private Etiqueta eti_materia = new Etiqueta();
    private Tabla tab_actividad_docente = new Tabla();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    public PersonalActividad() {

        if (TienePerfilNota()) {

            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
            bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");

            com_materia_docente.setId("com_materia_docente");
            com_materia_docente.setMetodo("mostrarActividad");
            com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente("-1", "2"));

            bar_botones.agregarComponente(new Etiqueta("Cursos:"));
            bar_botones.agregarComponente(com_materia_docente);

            //boton limpiar
            Boton bot_limpiar = new Boton();
            bot_limpiar.setIcon("ui-icon-cancel");
            bot_limpiar.setMetodo("limpiar");
            bar_botones.agregarBoton(bot_limpiar);

            eti_docente.setStyle("font-size: 16px;font-weight: bold");
            eti_docente.setValue("Docente: " + docente);

            //TABLA
            tab_actividad_docente.setId("tab_actividad_docente");
            tab_actividad_docente.setTabla("yavirac_nota_actividad_docente", "ide_ynoacd", 1);
            tab_actividad_docente.setCondicion("ide_ynoacd=-1");
            tab_actividad_docente.setHeader("Docente: " + docente);
            tab_actividad_docente.getColumna("ide_ynopae").setCombo(ser_notas.getPeriodoActividadEvaluacion("-1", "0", "false,true", "0"));
            tab_actividad_docente.getColumna("ide_ynopae").setAutoCompletar();
            //tab_actividad_docente.getColumna("ide_ynopae").setUnico(true);
            tab_actividad_docente.getColumna("ide_ynoacd").setNombreVisual("CODIGO");
            tab_actividad_docente.getColumna("ide_ynopae").setNombreVisual("ACTIVIDAD EVALUACIÓN");
            tab_actividad_docente.getColumna("porciento_evaluacion_ynoacd").setNombreVisual(" % PARAMETRO");
            tab_actividad_docente.getColumna("porciento_evaluacion_ynoacd").setValorDefecto("0");
            tab_actividad_docente.getColumna("ide_ypedpe").setVisible(false);
            tab_actividad_docente.getColumna("ide_ystmal").setVisible(false);
            tab_actividad_docente.getColumna("ide_ystmen").setVisible(false);
            tab_actividad_docente.getColumna("ide_ystnie").setVisible(false);
            tab_actividad_docente.getColumna("ide_yhogra").setVisible(false);
            tab_actividad_docente.getColumna("ide_ystjor").setVisible(false);
            tab_actividad_docente.dibujar();

            PanelTabla pa_actividad_docente = new PanelTabla();
            pa_actividad_docente.setId("pa_actividad_docente");
            pa_actividad_docente.setPanelTabla(tab_actividad_docente);

            //instanciar una division del framework
            Division div_actividad_docente = new Division();
            div_actividad_docente.setId("div_actividad_docente");
            div_actividad_docente.dividir1(pa_actividad_docente);

            agregarComponente(div_actividad_docente);

        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el control de Asistencia. Consulte con el Administrador");
        }
    }

    String docente = "";
    String documento = "";
    String ide_docente = "";

    public void mostrarActividad() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Por Favor, Seleccione el Periodo Académico");
            return;
        } else {
            String cod = com_materia_docente.getValue() + "";
            TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));
            String mension = tab_consuta.getValor("ide_ystmen");
            TablaGenerica tab_formacion = utilitario.consultar(ser_notas.getFormacion(mension));
            tab_actividad_docente.setCondicion("ide_ypedpe=" + tab_consuta.getValor("ide_ypedpe") + "and ide_ystmal=" + tab_consuta.getValor("ide_ystmal")
                    + " and ide_ystnie=" + tab_consuta.getValor("ide_ystnie") + " and ide_yhogra=" + tab_consuta.getValor("ide_yhogra")
                    + " and ide_ystjor=" + tab_consuta.getValor("ide_ystjor") + "and ide_ystmen=" + tab_consuta.getValor("ide_ystmen")
                    + " and ide_ynopae in (select ide_ynopae from yavirac_nota_periodo_activ_eva where ide_ynopee in "
                    + "(select ide_ynopee from yavirac_nota_periodo_evaluacio where ide_ystpea=" + com_periodo_academico.getValue() + ") "
                    + " and ide_ynoace in (select ide_ynoace from yavirac_nota_actividad_evaluac where ide_ynoace in \n"
                    + "(select ide_ynoace from yavirac_nota_actividad_tipo_for where ide_ysttfe=" + tab_formacion.getValor("ide_ysttfe") + ")))");
            tab_actividad_docente.ejecutarSql();
            tab_actividad_docente.getColumna("ide_ynopae").setCombo(ser_notas.getPeriodoActividadEvaluacion(com_periodo_academico.getValue().toString(), "1", "true,false", tab_formacion.getValor("ide_ysttfe")));

        }
    }

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
            utilitario.agregarMensajeInfo("Adevertencia: ", "Por favor, Seleccione el Periodo Académico");
            return;
        }
        if (com_materia_docente.getValue() == null) {
            utilitario.agregarMensajeInfo("Adevertencia: ", "Por favor, Seleccione la materia");
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

    public void limpiar() {
        com_periodo_academico.limpiar();
        com_materia_docente.limpiar();
        tab_actividad_docente.limpiar();
        utilitario.addUpdate("tab_actividad_docente");
    }

    @Override
    public void insertar() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Por favor, Seleccione el Periodo Academico");
            return;
        } else if (com_materia_docente.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Por favor, Seleccione el curso");
            return;
        } else {
            String cod = com_materia_docente.getValue() + "";
            TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));

            tab_actividad_docente.insertar();
            tab_actividad_docente.setValor("ide_ypedpe", tab_consuta.getValor("ide_ypedpe"));
            tab_actividad_docente.setValor("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
            tab_actividad_docente.setValor("ide_ystmen", tab_consuta.getValor("ide_ystmen"));
            tab_actividad_docente.setValor("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
            tab_actividad_docente.setValor("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
            tab_actividad_docente.setValor("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
            utilitario.addUpdate("tab_actividad_docente");
        }
    }

    @Override
    public void guardar() {
        tab_actividad_docente.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {

        tab_actividad_docente.eliminar();
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

    public Tabla getTab_actividad_docente() {
        return tab_actividad_docente;
    }

    public void setTab_actividad_docente(Tabla tab_actividad_docente) {
        this.tab_actividad_docente = tab_actividad_docente;
    }

}

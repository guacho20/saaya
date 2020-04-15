/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_personal;

import framework.aplicacion.TablaGenerica;
import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author usuario
 */
public class Docente extends Pantalla {

    private Tabla tab_tipo_educacion = new Tabla(); //intanciamos componente tabla
    private Tabla tab_actividad_docente = new Tabla();
    private Combo com_periodo_academico = new Combo();
    private Combo com_materia_docente = new Combo();
    private AutoCompletar aut_alumno = new AutoCompletar();
    private Boton bot_clean = new Boton();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);

    public Docente() {
        aut_alumno.setId("aut_alumno");
        aut_alumno.setAutoCompletar(ser_personal.getDatopersonal("true,false"));
        aut_alumno.setSize(75);
        aut_alumno.setMetodoChange("selecionoAutocompletar");
        bar_botones.agregarComponente(new Etiqueta("Docente "));
        bar_botones.agregarComponente(aut_alumno);
        bot_clean.setIcon("ui-icon-cancel");
        bot_clean.setTitle("Limpiar");
        bot_clean.setMetodo("limpiar");
        bar_botones.agregarComponente(bot_clean);

        tab_tipo_educacion.setId("tab_tipo_educacion");
        tab_tipo_educacion.setTabla("yavirac_perso_dato_personal", "ide_ypedpe", 1);
        tab_tipo_educacion.setHeader("DATOS PERSONALES");
        tab_tipo_educacion.agregarRelacion(tab_actividad_docente);
        tab_tipo_educacion.getColumna("ide_ysttis").setCombo(ser_estructura.getTipoSangre("true,false"));
        tab_tipo_educacion.getColumna("ide_ystesc").setCombo(ser_estructura.getEstadoCivil("true,false"));
        //tab_tipo_educacion.getColumna("ide_ysttdp").setCombo(ser_estructura.getEstadoCivil("true,false"));
        tab_tipo_educacion.getColumna("ide_ystdoi").setCombo(ser_estructura.getDocumentoIdentidad("true,false"));
        tab_tipo_educacion.getColumna("ide_ystnac").setCombo(ser_estructura.getNacionalidad("true,false"));
        tab_tipo_educacion.getColumna("ide_ystgen").setCombo(ser_estructura.getGenero("true,false"));
        tab_tipo_educacion.getColumna("ide_ystdip").setCombo(ser_estructura.getDistribucionPolitica("true,false"));
        tab_tipo_educacion.getColumna("ide_ystard").setCombo(ser_estructura.getAreaDepartamento("true,false"));

        //crear formularios
        tab_tipo_educacion.setTipoFormulario(true);//para que se haga un formulario
        tab_tipo_educacion.getGrid().setColumns(8);//numero de columnas del formulario
        //*****************************************************************************
        tab_tipo_educacion.getColumna("ide_ypedpe").setNombreVisual("CÓDIGO");
        tab_tipo_educacion.getColumna("ide_ysttis").setNombreVisual("TIPO SANGRE");
        tab_tipo_educacion.getColumna("ide_ystesc").setNombreVisual("ESTADO CIVIL");
        //tab_tipo_educacion.getColumna("ide_yastpe").setNombreVisual("ESTADO CIVIL");
        tab_tipo_educacion.getColumna("ide_ystdoi").setNombreVisual("DOCUMENTO IDENTIDAD");
        tab_tipo_educacion.getColumna("ide_ystnac").setNombreVisual("NACIONALIDAD");
        tab_tipo_educacion.getColumna("ide_ystgen").setNombreVisual("GÉNERO");
        tab_tipo_educacion.getColumna("apellido_ypedpe").setNombreVisual("APELLIDOS");
        tab_tipo_educacion.getColumna("nombre_ypedpe").setNombreVisual("NOMBRES");
        tab_tipo_educacion.getColumna("fecha_nacimiento_ypedpe").setNombreVisual("FECHA NACIMIENTO");
        tab_tipo_educacion.getColumna("foto_ypedpe").setNombreVisual("FOTO");
        tab_tipo_educacion.getColumna("foto_ypedpe").setUpload();//cargafoto
        tab_tipo_educacion.getColumna("foto_ypedpe").setImagen();//visualizalafoto
        tab_tipo_educacion.getColumna("lugar_nacimiento_ypedpe").setNombreVisual("LUGAR NACIMIENTO");
        tab_tipo_educacion.getColumna("firma_ypedpe").setNombreVisual("FIRMA");
        tab_tipo_educacion.getColumna("firma_ypedpe").setUpload();
        tab_tipo_educacion.getColumna("firma_ypedpe").setImagen();
        tab_tipo_educacion.getColumna("doc_identidad_ypedpe").setNombreVisual("DOCUMENTO IDENTIDAD");
        tab_tipo_educacion.getColumna("edad_ypedpe").setNombreVisual("EDAD");
        tab_tipo_educacion.getColumna("codigo_reloj_ypedpe").setNombreVisual("CÓDIGO RELOJ");
        tab_tipo_educacion.getColumna("discapacitado_ypedpe").setNombreVisual("DISCAPACIDAD");
        tab_tipo_educacion.getColumna("activo_ypedpe").setNombreVisual("ACTIVO");
        //************************************************************************
        tab_tipo_educacion.dibujar();
        PanelTabla pat_tipo_educacion = new PanelTabla();
        pat_tipo_educacion.setId("pat_tipo_educacion");
        pat_tipo_educacion.setPanelTabla(tab_tipo_educacion);

           //GRID
        Grid gri_cuerpo = new Grid();
        gri_cuerpo.setColumns(4);
        gri_cuerpo.setWidth("100%");
        gri_cuerpo.setStyle("width:100%;overflow: auto;display: block;");

        
        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true,false"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");
        
        com_materia_docente.setId("com_materia_docente");
        com_materia_docente.setMetodo("mostrarResumen");
        com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente("-1", "2"));
        
        gri_cuerpo.getChildren().add(new Etiqueta("Periodo Academico "));
        gri_cuerpo.getChildren().add(com_periodo_academico);
        gri_cuerpo.getChildren().add(new Etiqueta("Curso"));
        gri_cuerpo.getChildren().add(com_materia_docente);

        //TABLA ACTIVIDAD DOCENTE
        tab_actividad_docente.setId("tab_actividad_docente");
        tab_actividad_docente.setTabla("yavirac_nota_actividad_docente", "ide_ynoacd", 2);
        tab_actividad_docente.getColumna("ide_ynoacd").setNombreVisual("CODIGO");
        tab_actividad_docente.getColumna("ide_ynopae").setNombreVisual("PERIODO ACTIVIDAD EVALUACIÓN");
        //tab_actividad_docente.getColumna("ide_ynopae").setCombo(ser_notas.getPeriodoActividadEvaluacion("-1", "0", "true,false", "0"));
        //tab_actividad_docente.getColumna("ide_ynopae").setAutoCompletar();
        tab_actividad_docente.getColumna("ide_ypedpe").setNombreVisual("DOCENTE");
        tab_actividad_docente.getColumna("porciento_evaluacion_ynoacd").setNombreVisual(" % EVALUACIÓN");
        tab_actividad_docente.getColumna("ide_ypedpe").setVisible(false);
        tab_actividad_docente.getColumna("ide_ystmal").setVisible(false);
        tab_actividad_docente.getColumna("ide_ystmen").setVisible(false);
        tab_actividad_docente.getColumna("ide_ystnie").setVisible(false);
        tab_actividad_docente.getColumna("ide_yhogra").setVisible(false);
        tab_actividad_docente.getColumna("ide_ystjor").setVisible(false);
        //tab_actividad_docente.setHeader(gri_cuerpo);
        tab_actividad_docente.dibujar();
        PanelTabla pa_actividad_docente = new PanelTabla();
        pa_actividad_docente.setId("pa_actividad_docente");
        pa_actividad_docente.setPanelTabla(tab_actividad_docente);

        Division div_tipo_educacion = new Division();
        div_tipo_educacion.dividir2(pat_tipo_educacion, pa_actividad_docente, "40%", "h");
        agregarComponente(div_tipo_educacion);
        
     
    }

    public void selecionoAutocompletar() {

        tab_tipo_educacion.setCondicion("ide_ypedpe=" + aut_alumno.getValor());
        tab_tipo_educacion.ejecutarSql();
        utilitario.addUpdate("tab_tipo_educacion");
    }

    public void filtroComboPeriodoAcademico() {
        if (aut_alumno.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Por favor, Seleccione o ingrese el docente");
            return;
        } else {
            System.out.println("periodo " + com_periodo_academico.getValue() + " docente " + aut_alumno.getValor());
            com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente(com_periodo_academico.getValue().toString(), aut_alumno.getValor()));
            //utilitario.addUpdate("com_materia_docente");
            utilitario.addUpdate("com_materia_docente");
        }
    }
    
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
    
    public void mostrarActivida() {
        if (aut_alumno.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Por favor, Seleccione o ingrese el docente");
            return;
        } else {
            TablaGenerica tab_actividad = utilitario.consultar(ser_personal.getDatoPersonalCodigo(aut_alumno.getValor()));
            tab_actividad_docente.setCondicion("ide_ypedpe = " + tab_actividad.getValor("ide_ypedpe") + " and ide_ynopae in (select ide_ynopae from yavirac_nota_periodo_activ_eva a join yavirac_nota_periodo_evaluacio b on a.ide_ynopee = b.ide_ynopee where b.ide_ystpea = " + com_periodo_academico.getValue() + ")");
            tab_actividad_docente.ejecutarSql();
            utilitario.addUpdate("tab_actividad_docente");
        }
    }

    public void limpiar() {
        aut_alumno.limpiar();
        tab_tipo_educacion.limpiar();
        com_periodo_academico.limpiar();
        utilitario.addUpdate("tab_actividad_docente");
    }

    @Override
    public void insertar() {
        if (tab_tipo_educacion.isFocus()) {
            tab_tipo_educacion.insertar();
        } else if (tab_actividad_docente.isFocus()) {
            if (com_periodo_academico.getValue() == null) {
                utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Académico");
                return;
            } else {
                tab_actividad_docente.insertar();
                tab_actividad_docente.getColumna("ide_ynopae").setCombo(ser_notas.getPeriodoActividadEvaluacion(com_periodo_academico.getValue().toString(), "1", "true,false", "1"));

            }

        }
    }

    @Override
    public void guardar() {
        if (tab_tipo_educacion.guardar()) {
            if (tab_actividad_docente.guardar()) {
                guardarPantalla();
            }
        }
    }

    @Override
    public void eliminar() {
        if (tab_tipo_educacion.isFocus()) {
            tab_tipo_educacion.eliminar();
        } else if (tab_actividad_docente.isFocus()) {
            tab_actividad_docente.eliminar();
        }
    }

    public Tabla getTab_tipo_educacion() {
        return tab_tipo_educacion;
    }

    public void setTab_tipo_educacion(Tabla tab_tipo_educacion) {
        this.tab_tipo_educacion = tab_tipo_educacion;
    }

    public Tabla getTab_actividad_docente() {
        return tab_actividad_docente;
    }

    public void setTab_actividad_docente(Tabla tab_actividad_docente) {
        this.tab_actividad_docente = tab_actividad_docente;
    }

    public AutoCompletar getAut_alumno() {
        return aut_alumno;
    }

    public void setAut_alumno(AutoCompletar aut_alumno) {
        this.aut_alumno = aut_alumno;
    }

    public Boton getBot_clean() {
        return bot_clean;
    }

    public void setBot_clean(Boton bot_clean) {
        this.bot_clean = bot_clean;
    }
}

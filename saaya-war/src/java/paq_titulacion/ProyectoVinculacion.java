/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import framework.componentes.VisualizarPDF;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;
import paq_titulacion.ejb.ServicioTitulacion;
import sistema.aplicacion.Pantalla;
import java.util.HashMap;
import java.util.Map;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author usuario
 */
public class ProyectoVinculacion extends Pantalla {

    private Tabla tab_tabla = new Tabla();
    private Tabla tab_tabla1 = new Tabla();
    private Tabla tab_tabla2 = new Tabla();
    private Tabla tab_tabla3 = new Tabla();
    private Tabla tab_tabla4 = new Tabla();
    private Tabla tab_tabla5 = new Tabla();
    private Tabla tab_tabla6 = new Tabla();
    private Tabla tab_tabla7 = new Tabla();
    private VisualizarPDF vipdf_proforma = new VisualizarPDF();
    private Tabulador tab_tabulador = new Tabulador();

    private Combo com_periodo_academico = new Combo();
    @EJB
    private final ServicioEstructuraOrganizacional ser_periodoacademico = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioTitulacion ser_titulacion = (ServicioTitulacion) utilitario.instanciarEJB(ServicioTitulacion.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);

    public ProyectoVinculacion() {

        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_periodoacademico.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");

        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADEMICO"));
        bar_botones.agregarComponente(com_periodo_academico);

        tab_tabla.setId("tab_tabla");
        tab_tabla.setTabla("yavirac_titu_proyecto", "ide_ytipro", 0);
        tab_tabla.setCondicion("ide_ystpea=-1");
        tab_tabla.setTipoFormulario(true);
        tab_tabla.getGrid().setColumns(6);
        tab_tabla.getColumna("ide_ytring").setVisible(false);
        //tab_tabla.getColumna("ide_ytiviv").setCombo(ser_titulacion.getViabilidad());
        tab_tabla.getColumna("ide_ytitiv").setCombo(ser_titulacion.getTiposVinculacion());
        tab_tabla.getColumna("IDE_YTICAM").setCombo(ser_titulacion.getCampo());
        tab_tabla.getColumna("IDE_YSTTFE").setCombo(ser_periodoacademico.getTipoFormacionEducativa("true,false"));
        tab_tabla.getColumna("ide_ytipee").setCombo(ser_titulacion.getSqlPersonaEmpresa());
        tab_tabla.getColumna("ide_ytipee").setAutoCompletar();
        tab_tabla.getColumna("objeto_proyecto_ytipro").setVisible(false);
        tab_tabla.getColumna("IDE_YTICAM").setVisible(false);
        tab_tabla.getColumna("yav_ide_ypedpe2").setVisible(false);
        tab_tabla.getColumna("ide_ytipee").setNombreVisual("RESPONSABLE EMPRESA");
        tab_tabla.getColumna("ide_ytipro").setNombreVisual("CODIGO DE PROYECTO");
        tab_tabla.getColumna("fecha_registro_ytipro").setNombreVisual("FECHA DE REGISTRO");
        tab_tabla.getColumna("objeto_proyecto_ytipro").setNombreVisual("OBJETO DEL PROYECTO");
        tab_tabla.getColumna("detalle_proyecto_ytipro").setNombreVisual("NOMBRE DEL PROYECTO");
        tab_tabla.getColumna("fecha_inicio_ytipro").setNombreVisual("FECHA DE INICIO");
        tab_tabla.getColumna("fecha_inicio_ytipro").setMetodoChange("validarFecha");
        tab_tabla.getColumna("fecha_fin_ytipro").setNombreVisual("FECHA FINAL");
        tab_tabla.getColumna("fecha_fin_ytipro").setMetodoChange("validarFecha");
        tab_tabla.getColumna("ide_ypedpe").setNombreVisual("ELEBORADO POR");
        tab_tabla.getColumna("yav_ide_ypedpe").setNombreVisual("REVISADO POR");
        tab_tabla.getColumna("yav_ide_ypedpe2").setNombreVisual("APROBADO POR");
        tab_tabla.getColumna("ide_ystmen").setNombreVisual("CARRERA");
        tab_tabla.getColumna("ide_ytiemp").setNombreVisual("EMPRESA");
        tab_tabla.getColumna("ide_ystdip").setNombreVisual("PROVINCIA");
        tab_tabla.getColumna("yav_ide_ystdip").setNombreVisual("CANTON");
        tab_tabla.getColumna("yav_ide_ystdip2").setNombreVisual("PARROQUIA");
        tab_tabla.getColumna("ide_ystpea").setVisible(false);
        tab_tabla.getColumna("ide_ytiviv").setVisible(false);
        tab_tabla.getColumna("ide_ystmen").setCombo(ser_periodoacademico.getMension());
        tab_tabla.getColumna("ide_ytiemp").setCombo(ser_titulacion.getDatoEmpresa());
        tab_tabla.getColumna("ide_ytiemp").setAutoCompletar();
        tab_tabla.getColumna("ide_ytiemp").setMetodoChange("datosEmpresa");
        tab_tabla.getColumna("ide_ystmen").setAutoCompletar();
        tab_tabla.getColumna("ide_ytiint").setCombo(ser_titulacion.getIntervaloTiempo());
        tab_tabla.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tabla.getColumna("yav_ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tabla.getColumna("yav_ide_ypedpe2").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tabla.getColumna("ide_ypedpe").setAutoCompletar();
        tab_tabla.getColumna("yav_ide_ypedpe").setAutoCompletar();
        tab_tabla.getColumna("yav_ide_ypedpe2").setAutoCompletar();
        tab_tabla.getColumna("ide_ystdip").setCombo(ser_periodoacademico.getDistribucionPolitica("true,false"));
        tab_tabla.getColumna("yav_ide_ystdip").setCombo(ser_periodoacademico.getDistribucionPolitica("true,false"));
        tab_tabla.getColumna("yav_ide_ystdip2").setCombo(ser_periodoacademico.getDistribucionPolitica("true,false"));
        tab_tabla.getColumna("ide_ystdip").setAutoCompletar();
        tab_tabla.getColumna("yav_ide_ystdip").setAutoCompletar();
        tab_tabla.getColumna("yav_ide_ystdip2").setAutoCompletar();
        tab_tabla.getColumna("anexo_ytipro").setUpload();
        tab_tabla.agregarRelacion(tab_tabla1);
        tab_tabla.agregarRelacion(tab_tabla2);
        tab_tabla.agregarRelacion(tab_tabla3);
        tab_tabla.agregarRelacion(tab_tabla4);
        tab_tabla.agregarRelacion(tab_tabla5);
        tab_tabla.agregarRelacion(tab_tabla6);
        tab_tabla.agregarRelacion(tab_tabla7);
        tab_tabla.dibujar();

        PanelTabla pat_panel = new PanelTabla();
        pat_panel.setPanelTabla(tab_tabla);

        tab_tabulador.setId("tab_tabulador");

        tab_tabla1.setId("tab_tabla1");// todo objeto instanciado poner id 
        tab_tabla1.setIdCompleto("tab_tabulador:tab_tabla1");
        tab_tabla1.setTabla("yavirac_titu_proyecto_actividad", "ide_ytipac", 1);  // nom bdd
        tab_tabla1.getColumna("ide_ytiacv").setCombo(ser_titulacion.getSqlActividadVinculacion());
        tab_tabla1.getColumna("ide_ytiacv").setAutoCompletar();
        tab_tabla1.dibujar();

        PanelTabla pa_panel1 = new PanelTabla();
        pa_panel1.setId("pa_panel1");//nombre id
        pa_panel1.setPanelTabla(tab_tabla1);

        tab_tabla2.setId("tab_tabla2");// todo objeto instanciado poner id 
        tab_tabla2.setIdCompleto("tab_tabulador:tab_tabla2");
        tab_tabla2.setTabla("yavirac_titu_proyecto_eje", "ide_ytipre", 2);  // nom bdd
        tab_tabla2.getColumna("ide_ytiejv").setCombo(ser_titulacion.getSqlEjeVinculacion());
        tab_tabla2.getColumna("ide_ytiejv").setAutoCompletar();
        tab_tabla2.dibujar();

        PanelTabla pa_panel2 = new PanelTabla();
        pa_panel2.setId("pa_panel2");//nombre id
        pa_panel2.setPanelTabla(tab_tabla2);

        tab_tabla3.setId("tab_tabla3");// todo objeto instanciado poner id 
        tab_tabla3.setIdCompleto("tab_tabulador:tab_tabla3");
        tab_tabla3.setTabla("yavirac_titu_proyecto_area", "ide_ytipra", 3);  // nom bdd
        tab_tabla3.getColumna("ide_ytiara").setCombo(ser_titulacion.getSqlAreaAplicacion());
        tab_tabla3.getColumna("ide_ytiara").setAutoCompletar();
        tab_tabla3.dibujar();

        PanelTabla pa_panel3 = new PanelTabla();
        pa_panel3.setId("pa_panel3");//nombre id
        pa_panel3.setPanelTabla(tab_tabla3);

        tab_tabla4.setId("tab_tabla4");// todo objeto instanciado poner id 
        tab_tabla4.setIdCompleto("tab_tabulador:tab_tabla4");
        tab_tabla4.setTabla("yavirac_titu_participante_docen", "ide_ytiprd", 4);  // nom bdd
        tab_tabla4.getColumna("ide_ytifua").setCombo(ser_titulacion.getSqlFuncionAsignada());
        tab_tabla4.getColumna("ide_ytitpa").setCombo(ser_titulacion.getSqlTipoParticipante());
        tab_tabla4.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tabla4.getColumna("ide_ypedpe").setAutoCompletar();
        tab_tabla4.getColumna("ide_ytifua").setAutoCompletar();
        tab_tabla4.getColumna("ide_ytitpa").setAutoCompletar();
        tab_tabla4.dibujar();

        PanelTabla pa_panel4 = new PanelTabla();
        pa_panel4.setId("pa_panel4");//nombre id
        pa_panel4.setPanelTabla(tab_tabla4);

        tab_tabla5.setId("tab_tabla5");// todo objeto instanciado poner id 
        tab_tabla5.setIdCompleto("tab_tabulador:tab_tabla5");
        tab_tabla5.setTabla("yavirac_titu_participante_est", "ide_ytipre", 5);  // nom bdd
        tab_tabla5.getColumna("ide_ytifua").setCombo(ser_titulacion.getSqlFuncionAsignada());
        tab_tabla5.getColumna("ide_ytifua").setAutoCompletar();
        tab_tabla5.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
        tab_tabla5.getColumna("ide_yaldap").setAutoCompletar();
        tab_tabla5.dibujar();

        PanelTabla pa_panel5 = new PanelTabla();
        pa_panel5.setId("pa_panel5");//nombre id
        pa_panel5.setPanelTabla(tab_tabla5);

        tab_tabla6.setId("tab_tabla6");// todo objeto instanciado poner id 
        tab_tabla6.setIdCompleto("tab_tabulador:tab_tabla6");
        tab_tabla6.setTabla("yavirac_titu_pro_objetivo", "ide_ytipoo", 6);  // nom bdd
        tab_tabla6.getColumna("IDE_YTIOBI").setCombo(ser_titulacion.getObjetivoProyectoVinculacion());
        tab_tabla6.getColumna("IDE_YTIOBI").setAutoCompletar();
        tab_tabla6.dibujar();

        PanelTabla pa_panel6 = new PanelTabla();
        pa_panel6.setId("pa_panel6");//nombre id
        pa_panel6.setPanelTabla(tab_tabla6);

        tab_tabla7.setId("tab_tabla7");// todo objeto instanciado poner id 
        tab_tabla7.setIdCompleto("tab_tabulador:tab_tabla7");
        tab_tabla7.setTabla("yavirac_titu_pro_act_resul", "ide_ytipar", 7);  // nom bdd
        tab_tabla7.getColumna("IDE_YTITIA").setCombo(ser_titulacion.getResultadoActividades());
        tab_tabla7.getColumna("IDE_YTITIA").setAutoCompletar();
        tab_tabla7.dibujar();

        PanelTabla pa_panel7 = new PanelTabla();
        pa_panel7.setId("pa_panel7");//nombre id
        pa_panel7.setPanelTabla(tab_tabla7);

        //*******************************AGREGA PESTAÑANAS*********************************************//
        tab_tabulador.agregarTab("ACTIVIDAD VINCULACION", pa_panel1);
        tab_tabulador.agregarTab("EJES ESTRATEGICOS", pa_panel2);
        tab_tabulador.agregarTab("AREAS APLICACION", pa_panel3);
        tab_tabulador.agregarTab("DOCENTES PARTICIPANTES", pa_panel4);
        tab_tabulador.agregarTab("ALUMNOS PARTICIPANTES", pa_panel5);
        tab_tabulador.agregarTab("OBEJTIVOS", pa_panel6);
        tab_tabulador.agregarTab("ACTIVIDADES/RESULTADOS", pa_panel7);

        Division div_division = new Division();
        div_division.dividir2(pat_panel, tab_tabulador, "50%", "H");
        agregarComponente(div_division);

        Boton bot_anular = new Boton();
        bot_anular.setIcon("ui-icon-search");
        bot_anular.setValue("IMPRIMIR REPORTE");
        bot_anular.setMetodo("imprimir");
        bar_botones.agregarComponente(bot_anular);

        vipdf_proforma.setId("vipdf_proforma");
        vipdf_proforma.setTitle("REPORTE PROYECTO DE VINCULACIÓN");
        agregarComponente(vipdf_proforma);
    }

    //int fecha=0;
    public void validarFecha(DateSelectEvent evt) {
        tab_tabla.modificar(evt);
        if (tab_tabla.getValor("fecha_fin_ytipro") != null) {
            int fecha = utilitario.getDiferenciasDeFechas(utilitario.getFecha(tab_tabla.getValor("fecha_inicio_ytipro")), utilitario.getFecha(tab_tabla.getValor("fecha_fin_ytipro")));
            //System.out.println("VALIDACION FECHA ==> " + fecha);
            if (fecha < 0) {
                utilitario.agregarMensajeInfo("ADVERTENCIA,", "No puede ingresar una fecha mayor que la fecha final");

            }
        } else {
            int fecha = utilitario.getDiferenciasDeFechas(utilitario.getFecha(tab_tabla.getValor("fecha_inicio_ytipro")), utilitario.getFecha(utilitario.getFechaActual().toString()));
            //System.out.println("VALIDACION FECHA ==> " + fecha);
        }

    }

    public void datosEmpresa(SelectEvent evt) {
        tab_tabla.modificar(evt);
        TablaGenerica tab_parroquia = utilitario.consultar(ser_periodoacademico.getDistribucionPoliticaPorCondicion("2", "select ide_ystdip from yavirac_stror_distribucion_pol  where ide_ystdip in (\n"
                + "select ide_ystdip from yavirac_titu_empresa  where ide_ytiemp=" + tab_tabla.getValor("ide_ytiemp")
                + ") and ide_ysttdp=4"));
        TablaGenerica tab_canton = utilitario.consultar(ser_periodoacademico.getDistribucionPoliticaPorCondicion("2", "select ide_ystdip from yavirac_stror_distribucion_pol  where ide_ystdip in (\n"
                + "select yav_ide_ystdip from yavirac_stror_distribucion_pol  where ide_ystdip=" + tab_parroquia.getValor("ide_ystdip")
                + ") and ide_ysttdp=3"));
        TablaGenerica tab_provincia = utilitario.consultar(ser_periodoacademico.getDistribucionPoliticaPorCondicion("2", "select ide_ystdip from yavirac_stror_distribucion_pol  where ide_ystdip in (\n"
                + "select yav_ide_ystdip from yavirac_stror_distribucion_pol  where ide_ystdip=" + tab_canton.getValor("ide_ystdip")
                + ") and ide_ysttdp=2"));
        tab_tabla.setValor("ide_ystdip", tab_provincia.getValor("ide_ystdip"));
        tab_tabla.setValor("yav_ide_ystdip", tab_canton.getValor("ide_ystdip"));
        tab_tabla.setValor("yav_ide_ystdip2", tab_parroquia.getValor("ide_ystdip"));
        utilitario.addUpdate("tab_tabla");
    }

    public void imprimir() {
        String usuario = utilitario.getVariable("NICK");
        if (tab_tabla.getValorSeleccionado() != null) {
            ///////////AQUI ABRE EL REPORTE
            TablaGenerica tab_provincia = utilitario.consultar(ser_periodoacademico.getDistribucionPoliticaPorCondicion("2", tab_tabla.getValor("ide_ystdip")));
            TablaGenerica tab_canton = utilitario.consultar(ser_periodoacademico.getDistribucionPoliticaPorCondicion("2", tab_tabla.getValor("yav_ide_ystdip")));
            TablaGenerica tab_parroquia = utilitario.consultar(ser_periodoacademico.getDistribucionPoliticaPorCondicion("2", tab_tabla.getValor("yav_ide_ystdip2")));
            Map parametros = new HashMap();
            parametros.put("pide_canton", tab_canton.getValor("descripcion_ystdip"));
            parametros.put("pide_provincia", tab_provincia.getValor("descripcion_ystdip"));
            parametros.put("pide_parroquia", tab_parroquia.getValor("descripcion_ystdip"));
            parametros.put("pide_proyecto", Integer.parseInt(tab_tabla.getValor("ide_ytipro")));
            parametros.put("nombre", usuario);
            //System.out.println(" paramteros " + parametros);
            vipdf_proforma.setVisualizarPDF("rep_titulacion/rep_vinculacion.jasper", parametros);
            vipdf_proforma.dibujar();
            utilitario.addUpdate("vipdf_proforma");
        } else {
            utilitario.agregarMensajeInfo("Seleccione una Solititud de proforma", "");
        }
    }

    public void filtroComboPeriodoAcademico() {
        tab_tabla.setCondicion("ide_ystpea=" + com_periodo_academico.getValue());
        tab_tabla.ejecutarSql();
        //tab_anexo_libreta.ejecutarValorForanea(tab_libreta_practica.getValorSeleccionado());
        //tab_horario_practica.ejecutarValorForanea(tab_libreta_practica.getValorSeleccionado());
        utilitario.addUpdate("tab_tabla");
    }

    @Override
    public void insertar() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Academico");
            return;
        } else if (tab_tabla.isFocus()) {
            tab_tabla.insertar();
            tab_tabla.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
            TablaGenerica tab_secue = utilitario.consultar(ser_periodoacademico.getcodigoSecuencial(utilitario.getVariable("p_secuencial_vinculacion")));
            tab_tabla.setValor("secuencial_ytipro", tab_secue.getValor("nuevo_secuencial"));
        } else if (tab_tabla1.isFocus()) {
            tab_tabla1.insertar();
        } else if (tab_tabla2.isFocus()) {
            tab_tabla2.insertar();
        } else if (tab_tabla3.isFocus()) {
            tab_tabla3.insertar();
        } else if (tab_tabla4.isFocus()) {
            tab_tabla4.insertar();
        } else if (tab_tabla5.isFocus()) {
            tab_tabla5.insertar();
        } else if (tab_tabla6.isFocus()) {
            tab_tabla6.insertar();
        } else if (tab_tabla7.isFocus()) {
            tab_tabla7.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_tabla.guardar()) {
            if (tab_tabla1.guardar()) {
                if (tab_tabla2.guardar()) {
                    if (tab_tabla3.guardar()) {
                        if (tab_tabla4.guardar()) {
                            if (tab_tabla5.guardar()) {
                                if (tab_tabla6.guardar()) {
                                    if (tab_tabla7.guardar()) {
                                        if (tab_tabla.isFilaInsertada()) {
                                            utilitario.getConexion().ejecutarSql(ser_periodoacademico.getActualizarSecuencial(utilitario.getVariable("p_secuencial_vinculacion")));
                                        }
                                        guardarPantalla();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void eliminar() {
        utilitario.getTablaisFocus().eliminar();
    }

    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    }

    public Tabla getTab_tabla1() {
        return tab_tabla1;
    }

    public void setTab_tabla1(Tabla tab_tabla1) {
        this.tab_tabla1 = tab_tabla1;
    }

    public Tabla getTab_tabla2() {
        return tab_tabla2;
    }

    public void setTab_tabla2(Tabla tab_tabla2) {
        this.tab_tabla2 = tab_tabla2;
    }

    public Tabla getTab_tabla3() {
        return tab_tabla3;
    }

    public void setTab_tabla3(Tabla tab_tabla3) {
        this.tab_tabla3 = tab_tabla3;
    }

    public Tabla getTab_tabla4() {
        return tab_tabla4;
    }

    public void setTab_tabla4(Tabla tab_tabla4) {
        this.tab_tabla4 = tab_tabla4;
    }

    public Tabla getTab_tabla5() {
        return tab_tabla5;
    }

    public void setTab_tabla5(Tabla tab_tabla5) {
        this.tab_tabla5 = tab_tabla5;
    }

    public Tabla getTab_tabla6() {
        return tab_tabla6;
    }

    public void setTab_tabla6(Tabla tab_tabla6) {
        this.tab_tabla6 = tab_tabla6;
    }

    public Tabla getTab_tabla7() {
        return tab_tabla7;
    }

    public void setTab_tabla7(Tabla tab_tabla7) {
        this.tab_tabla7 = tab_tabla7;
    }

    public VisualizarPDF getVipdf_proforma() {
        return vipdf_proforma;
    }

    public void setVipdf_proforma(VisualizarPDF vipdf_proforma) {
        this.vipdf_proforma = vipdf_proforma;
    }

}

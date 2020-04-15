/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horario;

/**
 *
 * @author ANDRES REDROBAN
 */
import framework.aplicacion.TablaGenerica;
import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Grupo;
import framework.componentes.Imagen;
import framework.componentes.PanelTabla;
import framework.componentes.Radio;
import framework.componentes.Reporte;
import framework.componentes.SeleccionCalendario;
import framework.componentes.SeleccionFormatoReporte;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import paq_asistencia.ejb.ServicioAsistencia;
import sistema.aplicacion.Pantalla;
import framework.componentes.Panel;
import framework.componentes.VisualizarPDF;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;

public class ReporteHorarioGrid extends Pantalla{
    private Reporte rep_reporte=new Reporte();
    private SeleccionFormatoReporte sel_rep=new SeleccionFormatoReporte();  
    private SeleccionCalendario sel_fechas= new SeleccionCalendario();
    private Dialogo dia_tipo = new Dialogo();
    private Radio rad_tipo_justificacion = new Radio();
    private SeleccionTabla sel_tab_carrera = new SeleccionTabla();
    private Panel panOpcion = new Panel();
    private SeleccionTabla sel_tab_periodo = new SeleccionTabla();
    private SeleccionTabla sel_tab_jornada = new SeleccionTabla();
    private SeleccionTabla sel_tab_mension = new SeleccionTabla();
    private SeleccionTabla sel_tab_nivel = new SeleccionTabla();
    private SeleccionTabla sel_tab_docente = new SeleccionTabla();
    private Combo com_periodo_academico = new Combo();
    private Combo com_periodo_jornada = new Combo();
    private Combo com_periodo_modalidad = new Combo();
    private Combo com_periodo_nivel = new Combo();
    private AutoCompletar aut_docente = new AutoCompletar();
    private AutoCompletar aut_mension = new AutoCompletar();
    private AutoCompletar aut_laboratorio = new AutoCompletar();
    private VisualizarPDF vipdf_horario_do = new VisualizarPDF();
    private VisualizarPDF vipdf_horario_al = new VisualizarPDF();
    private VisualizarPDF vipdf_horario_lab = new VisualizarPDF();
    String periodo="";
    String jornada="";
    String mension="";
    String nivel="";
    
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    
    public ReporteHorarioGrid(){
        bar_botones.getBot_insertar().setRendered(false);
        bar_botones.getBot_guardar().setRendered(false);
        bar_botones.getBot_eliminar().setRendered(false);
        bar_botones.quitarBotonsNavegacion();

       /*
        CONFIGURACION DE OBJETO REPORTE
       */
       rep_reporte.setId("rep_reporte");
       rep_reporte.getBot_aceptar().setMetodo("aceptarReporte");
       agregarComponente(rep_reporte);
       bar_botones.agregarReporte();
       sel_rep.setId("sel_rep");
       agregarComponente(sel_rep);    
       
       
        /*
        CONFIGURACION DE OBJETO REPORTE
       */
        Panel tabp2 = new Panel();
        tabp2.setStyle("font-size:19px;color:black;text-align:center;");
        tabp2.setHeader("REPORTES HORARIOS INDIVIDUALES");
        panOpcion.setId("panOpcion");
        panOpcion.setStyle("font-size:12px;color:black;text-align:left;");
        panOpcion.setTransient(true);
        panOpcion.setHeader("SELECCIONE LOS PARÁMETROS DEL REPORTE");
        Imagen yavirac = new Imagen();
        yavirac.setStyle("text-align:center;position:absolute;top:162px;left:580px;");
        yavirac.setValue("imagenes/ModHorarios/im_rep_horarios.png");
        panOpcion.getChildren().add(yavirac);
        
        /*
        COMBO QUE MUESTRA EL PERIODO ACADEMICO
       */
        Grid griCuerpo = new Grid();
        griCuerpo.setColumns(2);
        griCuerpo.getChildren().add(new Etiqueta("P. ACADÉMICO: "));
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
      //  com_periodo_academico.eliminarVacio();
        griCuerpo.getChildren().add(com_periodo_academico);
        
        /*
        COMBO QUE MUESTRA LA MODALIDAD
       */
        griCuerpo.getChildren().add(new Etiqueta("MODALIDAD: "));
        com_periodo_modalidad.setId("com_periodo_modalidad");
        com_periodo_modalidad.setCombo(ser_estructura_organizacional.getModalidad("true"));
      //  com_periodo_modalidad.eliminarVacio();
        griCuerpo.getChildren().add(com_periodo_modalidad);
        
        /*
        COMBO QUE MUESTRA LA JORNADA
       */
        griCuerpo.getChildren().add(new Etiqueta("JORNADA: "));
        com_periodo_jornada.setId("com_periodo_jornada");
        com_periodo_jornada.setCombo(ser_estructura_organizacional.getJornada("true"));
      //  com_periodo_jornada.eliminarVacio();
        griCuerpo.getChildren().add(com_periodo_jornada);
        
        /*
        AUTOCOMPLETAR QUE FILTRA LOS DOCENTES
       */
        griCuerpo.getChildren().add(new Etiqueta("DOCENTE: "));
        aut_docente.setId("aut_docente");
        aut_docente.setAutoCompletar(ser_personal.getDatopersonal("true,false"));
        aut_docente.setSize(60);
        //aut_docente.setMetodoChange("selecionoAutocompletar");
       // aut_docente.eliminarVacio();
        griCuerpo.getChildren().add(aut_docente);
        
        /*
        AUTOCOMPLETAR QUE FILTRA LAS CARRERAS O MENSIONES
       */
        griCuerpo.getChildren().add(new Etiqueta("CARRERAS: "));
        aut_mension.setId("aut_mension");
        aut_mension.setAutoCompletar(ser_estructura_organizacional.getMension());
        aut_mension.setSize(60);    
        //aut_docente.setMetodoChange("selecionoAutocompletar");
       // aut_docente.eliminarVacio();
        griCuerpo.getChildren().add(aut_mension);
        
        /*
        COMBO QUE MUESTRA EL NIVEL
       */
        griCuerpo.getChildren().add(new Etiqueta("NIVEL: "));
        com_periodo_nivel.setId("com_periodo_nivel");
        com_periodo_nivel.setCombo(ser_estructura_organizacional.getNivelEducacion());
        //com_periodo_nivel.eliminarVacio();
        griCuerpo.getChildren().add(com_periodo_nivel);
        
        /*
        AUTOCOMPLETAR QUE MUESTRA LOS LABORATORIOS
        */

        griCuerpo.getChildren().add(new Etiqueta("LABORATORIO: "));
        aut_laboratorio.setId("aut_laboratorio");
        aut_laboratorio.setAutoCompletar(ser_estructura_organizacional.getInstalacion());
        aut_laboratorio.setSize(60);
        griCuerpo.getChildren().add(aut_laboratorio);
        
        panOpcion.getChildren().add(griCuerpo);
        
        Boton botPrintLab = new Boton();
        botPrintLab.setId("botPrintLab");
        botPrintLab.setValue("IMPRIMIR REPORTE POR LABORATORIO");
        botPrintLab.setIcon("ui-icon-print");
        botPrintLab.setMetodo("generarPDFlaboratorio");
        botPrintLab.setStyle("text-align:center;position:absolute;top:410px;left:449px;");
        
        Boton botPrint = new Boton();
        botPrint.setId("botPrint");
        botPrint.setValue("IMPRIMIR REPORTE POR DOCENTE");
        botPrint.setIcon("ui-icon-print");
        botPrint.setMetodo("generarPDFdocentes");
        botPrint.setStyle("text-align:center;position:absolute;top:465px;left:470px;");
        
        Boton botPrintD = new Boton();
        botPrintD.setId("botPrintD");
        botPrintD.setValue("IMPRIMIR REPORTE POR NIVEL");
        botPrintD.setIcon("ui-icon-print");
        botPrintD.setMetodo("generarPDFniveles");
        botPrintD.setStyle("text-align:center;position:absolute;top:520px;left:490px;");

        tabp2.getChildren().add(panOpcion);
        tabp2.getChildren().add(botPrintLab);
        tabp2.getChildren().add(botPrint);
        tabp2.getChildren().add(botPrintD);
       
        agregarComponente(tabp2);
        
        vipdf_horario_do.setId("vipdf_horario_do");
        vipdf_horario_do.setTitle("REPORTE DE HORARIOS POR DOCENTE");
        agregarComponente(vipdf_horario_do);
        
        vipdf_horario_al.setId("vipdf_horario_al");
        vipdf_horario_al.setTitle("REPORTE DE HORARIOS POR NIVEL");
        agregarComponente(vipdf_horario_al);

        vipdf_horario_lab.setId("vipdf_horario_lab");
        vipdf_horario_lab.setTitle("REPORTE DE HORARIOS POR LABORATORIOS");
        agregarComponente(vipdf_horario_lab);
        
    }
    public void generarPDFdocentes() {
        if (com_periodo_academico.getValue() != null || aut_docente.getValor() != null) {
                        ///////////AQUI ABRE EL REPORTE
                        Map map_parametros = new HashMap();
                        map_parametros.put("p_periodo",Integer.parseInt(com_periodo_academico.getValue() + ""));
                        map_parametros.put("p_docente", Integer.parseInt(aut_docente.getValor() + ""));
                        vipdf_horario_do.setVisualizarPDF("rep_horario/rep_horario_docente.jasper", map_parametros);
                        vipdf_horario_do.dibujar();
                        utilitario.addUpdate("vipdf_horario_do");
        } else {
            utilitario.agregarMensajeInfo("Seleccione el periodo académico y el docente", "");
        }    
    }
    public void generarPDFniveles() {
        if (com_periodo_academico.getValue() != null|| aut_mension.getValor() != null) {
                        ///////////AQUI ABRE EL REPORTE
                        Map map_parametros = new HashMap();
                        map_parametros.put("p_periodo",Integer.parseInt(com_periodo_academico.getValue() + ""));
                        map_parametros.put("p_jornada",Integer.parseInt(com_periodo_jornada.getValue() + ""));
                        map_parametros.put("p_mension",Integer.parseInt(aut_mension.getValor()+ ""));
                        map_parametros.put("p_nivel", Integer.parseInt(com_periodo_nivel.getValue() + ""));
                        vipdf_horario_al.setVisualizarPDF("rep_horario/rep_horario_nivel_temp.jasper", map_parametros);
                        vipdf_horario_al.dibujar();
                        utilitario.addUpdate("vipdf_horario_do");
        } else {
            utilitario.agregarMensajeInfo("Seleccione el periodo académico y la carrera para generar el reporte", "");
        }    
    }
    public void generarPDFlaboratorio(){
        if (com_periodo_academico.getValue() != null|| aut_laboratorio.getValor() != null) {
            ///////////AQUI ABRE EL REPORTE
                        Map map_parametros = new HashMap();
                        map_parametros.put("p_periodo",Integer.parseInt(com_periodo_academico.getValue() + ""));
                        map_parametros.put("p_laboratorio", Integer.parseInt(aut_laboratorio.getValor() + ""));
                        vipdf_horario_lab.setVisualizarPDF("rep_horario/rep_laboratorio_individual.jasper", map_parametros);
                        vipdf_horario_lab.dibujar();
                        utilitario.addUpdate("vipdf_horario_lab");
                        
                        
        }
    }
    public void abrirListaReportes() {
        if (com_periodo_academico.getValue() != null || aut_docente.getValor() != null || aut_mension.getValor() != null){
// TODO Auto-generated method stub
     rep_reporte.dibujar();
        }
        else {
            utilitario.agregarMensajeError("No se puede continuar", "Debe seleccionar los parámetros del reporte");
        }
}


public void aceptarReporte() {
    if (rep_reporte.getReporteSelecionado().equals("Horarios por nivel")){
        if (aut_mension.getValor() != null && com_periodo_nivel != null){                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                Map map_parametros = new HashMap();
                map_parametros.put("p_periodo",Integer.parseInt(com_periodo_academico.getValue() + ""));
                map_parametros.put("p_jornada",Integer.parseInt(com_periodo_jornada.getValue() + ""));
                map_parametros.put("p_mension",Integer.parseInt(aut_mension.getValor()+ ""));
                map_parametros.put("p_nivel", Integer.parseInt(com_periodo_nivel.getValue() + ""));
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_nivel.cerrar();
                System.out.println("nivel "+nivel);
                sel_rep.dibujar();
        }
        
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    }
    else {
        utilitario.agregarMensajeInfo("No se puede continuar", "No ha seleccionado Mension o nivel");
    }
    }
    else {
        utilitario.agregarMensajeInfo("No se puede continuar", "No ha seleccionado ningun registro requerido");
    }
    
    if (rep_reporte.getReporteSelecionado().equals("Horarios por docente")){
        if (aut_docente.getValor() != null){
        if(rep_reporte.isVisible()){
            Map map_parametros = new HashMap();
            map_parametros.put("p_periodo",Integer.parseInt(com_periodo_academico.getValue() + ""));
            map_parametros.put("p_docente", Integer.parseInt(aut_docente.getValor() + ""));
            sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
            sel_tab_nivel.cerrar();
            //System.out.println("nivel "+nivel);
            sel_rep.dibujar();    
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    }
    }
    else {
        utilitario.agregarMensajeInfo("No se puede continuar", "No ha seleccionado ningun docente");
    }
    
    if (rep_reporte.getReporteSelecionado().equals("Horario Laboratorio Temporal")){
        if (aut_mension.getValor() != null && com_periodo_nivel != null){                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                Map map_parametros = new HashMap();
                map_parametros.put("p_periodo",Integer.parseInt(com_periodo_academico.getValue() + ""));
                map_parametros.put("p_jornada",Integer.parseInt(com_periodo_jornada.getValue() + ""));
                map_parametros.put("p_mension",Integer.parseInt(aut_mension.getValor()+ ""));
                map_parametros.put("p_nivel", Integer.parseInt(com_periodo_nivel.getValue() + ""));
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_nivel.cerrar();
                System.out.println("nivel "+nivel);
                sel_rep.dibujar();
        }
        
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    }
    else {
        utilitario.agregarMensajeInfo("No se puede continuar", "No ha seleccionado Mension o nivel");
    }
    }
    else {
        utilitario.agregarMensajeInfo("No se puede continuar", "No ha seleccionado ningun registro requerido");
    }
    
}    

   
    @Override
    public void insertar() {
    }

    @Override
    public void guardar() {

    }

    @Override
    public void eliminar() {
       
    }

    public Reporte getRep_reporte() {
        return rep_reporte;
    }

    public void setRep_reporte(Reporte rep_reporte) {
        this.rep_reporte = rep_reporte;
    }

    public SeleccionFormatoReporte getSel_rep() {
        return sel_rep;
    }

    public void setSel_rep(SeleccionFormatoReporte sel_rep) {
        this.sel_rep = sel_rep;
    }

    public SeleccionCalendario getSel_fechas() {
        return sel_fechas;
    }

    public void setSel_fechas(SeleccionCalendario sel_fechas) {
        this.sel_fechas = sel_fechas;
    }

    public Dialogo getDia_tipo() {
        return dia_tipo;
    }

    public void setDia_tipo(Dialogo dia_tipo) {
        this.dia_tipo = dia_tipo;
    }

    public Radio getRad_tipo_justificacion() {
        return rad_tipo_justificacion;
    }

    public void setRad_tipo_justificacion(Radio rad_tipo_justificacion) {
        this.rad_tipo_justificacion = rad_tipo_justificacion;
    }

    public SeleccionTabla getSel_tab_carrera() {
        return sel_tab_carrera;
    }

    public void setSel_tab_carrera(SeleccionTabla sel_tab_carrera) {
        this.sel_tab_carrera = sel_tab_carrera;
    }

    public Panel getPanOpcion() {
        return panOpcion;
    }

    public void setPanOpcion(Panel panOpcion) {
        this.panOpcion = panOpcion;
    }

    public SeleccionTabla getSel_tab_periodo() {
        return sel_tab_periodo;
    }

    public void setSel_tab_periodo(SeleccionTabla sel_tab_periodo) {
        this.sel_tab_periodo = sel_tab_periodo;
    }

    public SeleccionTabla getSel_tab_jornada() {
        return sel_tab_jornada;
    }

    public void setSel_tab_jornada(SeleccionTabla sel_tab_jornada) {
        this.sel_tab_jornada = sel_tab_jornada;
    }

    public SeleccionTabla getSel_tab_mension() {
        return sel_tab_mension;
    }

    public void setSel_tab_mension(SeleccionTabla sel_tab_mension) {
        this.sel_tab_mension = sel_tab_mension;
    }

    public SeleccionTabla getSel_tab_nivel() {
        return sel_tab_nivel;
    }

    public void setSel_tab_nivel(SeleccionTabla sel_tab_nivel) {
        this.sel_tab_nivel = sel_tab_nivel;
    }

    public SeleccionTabla getSel_tab_docente() {
        return sel_tab_docente;
    }

    public void setSel_tab_docente(SeleccionTabla sel_tab_docente) {
        this.sel_tab_docente = sel_tab_docente;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public Combo getCom_periodo_jornada() {
        return com_periodo_jornada;
    }

    public void setCom_periodo_jornada(Combo com_periodo_jornada) {
        this.com_periodo_jornada = com_periodo_jornada;
    }

    public Combo getCom_periodo_modalidad() {
        return com_periodo_modalidad;
    }

    public void setCom_periodo_modalidad(Combo com_periodo_modalidad) {
        this.com_periodo_modalidad = com_periodo_modalidad;
    }

    public Combo getCom_periodo_nivel() {
        return com_periodo_nivel;
    }

    public void setCom_periodo_nivel(Combo com_periodo_nivel) {
        this.com_periodo_nivel = com_periodo_nivel;
    }

    public AutoCompletar getAut_docente() {
        return aut_docente;
    }

    public void setAut_docente(AutoCompletar aut_docente) {
        this.aut_docente = aut_docente;
    }

    public AutoCompletar getAut_mension() {
        return aut_mension;
    }

    public void setAut_mension(AutoCompletar aut_mension) {
        this.aut_mension = aut_mension;
    }

    public AutoCompletar getAut_laboratorio() {
        return aut_laboratorio;
    }

    public void setAut_laboratorio(AutoCompletar aut_laboratorio) {
        this.aut_laboratorio = aut_laboratorio;
    }

    public VisualizarPDF getVipdf_horario_do() {
        return vipdf_horario_do;
    }

    public void setVipdf_horario_do(VisualizarPDF vipdf_horario_do) {
        this.vipdf_horario_do = vipdf_horario_do;
    }

    public VisualizarPDF getVipdf_horario_al() {
        return vipdf_horario_al;
    }

    public void setVipdf_horario_al(VisualizarPDF vipdf_horario_al) {
        this.vipdf_horario_al = vipdf_horario_al;
    }

    public VisualizarPDF getVipdf_horario_lab() {
        return vipdf_horario_lab;
    }

    public void setVipdf_horario_lab(VisualizarPDF vipdf_horario_lab) {
        this.vipdf_horario_lab = vipdf_horario_lab;
    }
    
}

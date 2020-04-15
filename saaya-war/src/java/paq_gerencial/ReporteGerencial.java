/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_gerencial;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Espacio;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Imagen;
import framework.componentes.ListaSeleccion;
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

/**
 * 
 */
public class ReporteGerencial extends Pantalla {
    private Reporte rep_reporte=new Reporte();
    private SeleccionFormatoReporte sel_rep=new SeleccionFormatoReporte();  
    private SeleccionTabla sel_tab_carrera = new SeleccionTabla();
    private SeleccionTabla sel_tab_periodo = new SeleccionTabla();
    private Panel panOpcion = new Panel();
    private ListaSeleccion lis_periodo = new ListaSeleccion();
    private ListaSeleccion lis_carrera = new ListaSeleccion();
    private SeleccionFormatoReporte sef_reporte=new SeleccionFormatoReporte();
    private Map p_parametros=new HashMap();
    private Combo com_reportes = new Combo();
    private SeleccionCalendario sel_fechas= new SeleccionCalendario();
    String fechai="";
    String fechaf="";
    private VisualizarPDF vipdf_comprobante = new VisualizarPDF();
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    public ReporteGerencial(){
        
        bar_botones.getBot_insertar().setRendered(false);
        bar_botones.getBot_guardar().setRendered(false);
        bar_botones.getBot_eliminar().setRendered(false);
        bar_botones.quitarBotonsNavegacion();

       rep_reporte.setId("rep_reporte");
       rep_reporte.getBot_aceptar().setMetodo("aceptarReporte");
       agregarComponente(rep_reporte);

        com_reportes.setId("com_reportes");
        com_reportes.setCombo(ser_estructura.getListaReportes(utilitario.getVariable("p_menu_reportes")));
        bar_botones.agregarComponente(com_reportes);
       
        //Diseño de fondo reporte //
        Imagen ImaReportes = new Imagen();
        ImaReportes.setValue("imagenes/ModAsistencia/Asistencia.png");
        
        panOpcion.setId("pan_opcion");
        panOpcion.setTransient(true);
        panOpcion.setHeader("REPORTES GERENCIALES");
        panOpcion.setStyle("font-size:10px;color:black;text-align:center;");
        
        Grid grid_pant = new Grid();
        grid_pant.setColumns(1);
        grid_pant.setStyle("text-align:center;position:absolute;top:210px;left:535px;");
        Etiqueta eti_encab = new Etiqueta();
        grid_pant.getChildren().add(ImaReportes);
        Boton bot_imprimir = new Boton();
        bot_imprimir.setValue("Imprimir Reporte");
        bot_imprimir.setIcon("ui-icon-print");
        bot_imprimir.setMetodo("abrirListaReportes");
        bar_botones.agregarBoton(bot_imprimir);
        grid_pant.getChildren().add(bot_imprimir);
        /*
        agregarComponente(grid_pant);
        panOpcion.getChildren().add(grid_pant);
        agregarComponente(panOpcion);
        */
        
        // creo las listas de los reportes
        Grid gri_formulario=new Grid();
        gri_formulario.setColumns(3);
        lis_periodo.setListaSeleccion("select ide_ystpea, descripcion_ystpea, fecha_inicio_ystpea, fecha_final_ystpea,descripcion_ystani from yavirac_stror_periodo_academic a,yavirac_stror_anio b where a.ide_ystani = b.ide_ystani and activo_ystpea in (true,false) order by descripcion_ystani desc, fecha_inicio_ystpea desc");
        lis_periodo.setLayout("pageDirection");
        //lis_tipo_vivienda.setMetodoChange("", "@this");
        lis_carrera.setListaSeleccion("SELECT ide_ystmen, descripcion_ystmen ,detalle_ysttfe FROM yavirac_stror_mension  a, yavirac_stror_tipo_for_educaci b where a.ide_ysttfe = b.ide_ysttfe");
        lis_carrera.setLayout("pageDirection"); 
        Espacio esp=new Espacio();
        Etiqueta eti_per= new Etiqueta("PERIODO ACADEMICO");
        eti_per.setEstiloContenido("font-size:16px;font-weight: bold;text-decoration: underline;color:blue");
        Etiqueta eti_carr= new Etiqueta("CARRERAS");
        eti_carr.setEstiloContenido("font-size:16px;font-weight: bold;text-decoration: underline;color:blue");
        gri_formulario.getChildren().add(esp);
        gri_formulario.getChildren().add(eti_per);       
        gri_formulario.getChildren().add(eti_carr);
        gri_formulario.getChildren().add(ImaReportes);
        gri_formulario.getChildren().add(lis_periodo);
        gri_formulario.getChildren().add(lis_carrera);
        
        Division div= new Division();
        div.dividir1(gri_formulario);
        agregarComponente(div);
        // agrego boton para imprimir reportes
        Boton bot_imprimirpdf = new Boton();
        bot_imprimirpdf.setIcon("ui-icon-print");
        bot_imprimirpdf.setValue("IMPRIMIR REPORTE");
        bot_imprimirpdf.setMetodo("abrirListaReportes");
        bar_botones.agregarBoton(bot_imprimirpdf);        
        
        vipdf_comprobante.setId("vipdf_comprobante");
        vipdf_comprobante.setTitle("REPORTES GERENCIALES");
        agregarComponente(vipdf_comprobante);
        
        
         //PANTALLA CONSULTA LA MENSIONES
            sel_tab_carrera.setId("sel_tab_carrera");
            sel_tab_carrera.setWidth("80%");
            sel_tab_carrera.setTitle("SELECCIONE LAS CARRERAS");
            sel_tab_carrera.getBot_aceptar().setMetodo("aceptarReporte");
            sel_tab_carrera.setSeleccionTabla(ser_estructura.getMension(), "ide_ystmen"); 
            agregarComponente(sel_tab_carrera);

         //PANTALLA CONSULTA PERIODO ACADEMICO
            sel_tab_periodo.setId("sel_tab_periodo");
            sel_tab_periodo.setWidth("80%");
            sel_tab_periodo.setTitle("SELECCIONE EL/LOS PERIODOS ACADEMICOS QUE DESEA CONSULTAR");
            sel_tab_periodo.getBot_aceptar().setMetodo("aceptarReporte");
            sel_tab_periodo.setSeleccionTabla(ser_estructura.getPeriodoAcademico("true,false"), "ide_ystpea"); 
            agregarComponente(sel_tab_periodo);          
            
            sel_fechas.setId("sel_fechas");
       sel_fechas.getBot_aceptar().setMetodo("abrirListaReportes");
       sel_fechas.setFechaActual();
       agregarComponente(sel_fechas);
       
        
    }
    String periodo="";
    String carrera="";
public void abrirListaReportes() {
// TODO Auto-generated method stub
if (lis_periodo.getSeleccionados()!="")   
        {
            periodo =lis_periodo.getSeleccionados();
            if (lis_carrera.getSeleccionados()!="")   
            {
                 carrera =lis_carrera.getSeleccionados();
                 generarPDF();

            }
            else {
            utilitario.agregarNotificacionInfo("Seleccione la carrera", "Seleccione al menos un registro");
            }
        }
    else {
            utilitario.agregarNotificacionInfo("Seleccione periodo academico", "Seleccione al menos un registro");
    }
    
}

String str_seleccionado_periodo="";

public void generarPDF() {
    TablaGenerica tab_reportes = utilitario.consultar("select * from sis_reporte where ide_repo="+com_reportes.getValue());
    String nombre_reporte="";
    String reporte="";
    nombre_reporte= tab_reportes.getValor("nom_repo");
    reporte= tab_reportes.getValor("path_repo");
    System.out.println("usuario y contraseña "+nombre_reporte+" reporte "+reporte);
    if (nombre_reporte.equals("Inscripcion Carreras y Genero")){
                   Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                 vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante");
       
    
    
    }
    else if (nombre_reporte.equals("Inscripcion Carreras y Periodo Academico")){
                
        
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                 vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante");
        
    
    }
    else if (nombre_reporte.equals("Inscritos vs Matriculados")){
                
                    
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                 vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante"); 
        
    }
    else if (nombre_reporte.equals("Matriculas Carreras y Genero")){
                
                    
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                 vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante");
    
    }
    else if (nombre_reporte.equals("Matriculas Carreras y Periodo Academico")){
                
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                 vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante");

    
    }
    else if (nombre_reporte.equals("Porcentaje de Asistencia Alumnos")){
                
                   
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante");

    }
    else if (nombre_reporte.equals("Porcentaje de Asistencia Funcionarios")){
                
                System.out.println("ingreso a reporte asistencia funcionarios");
                
                
                if (sel_fechas.isVisible()){
                    System.out.println("ingreso a ifffff");
                    fechai=sel_fechas.getFecha1String();
                    fechaf=sel_fechas.getFecha2String();
                    sel_fechas.cerrar(); 
                    Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                map_parametros.put("pfechai", fechai);
                map_parametros.put("pfechaf", fechaf);
                
                vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante");
                
                }
                        else{
                    System.out.println("ingreso a else");
                        sel_fechas.dibujar();
                        
                        }
                
                
    
    }
    else if (nombre_reporte.equals("Docentes del Instituto")){
                
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante");
    
    }
    else if (nombre_reporte.equals("Docentes por Genero")){
                
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante");
    
    }
else if (nombre_reporte.equals("Docentes por Carrera")){
                
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante");
    
    }
                        
}
public void aceptarReporte() {
    
    
    if (rep_reporte.getReporteSelecionado().equals("Inscripcion Carreras y Genero")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Inscripcion Carreras y Periodo Academico")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Inscritos vs Matriculados")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Matriculas Carreras y Genero")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Matriculas Carreras y Periodo Academico")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Porcentaje de Asistencia Alumnos")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Porcentaje de Asistencia Funcionarios")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", carrera);
                map_parametros.put("pperiodo", periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
} 
/*
public void aceptarReporte() {
    if (rep_reporte.getReporteSelecionado().equals("Inscripcion Carreras y Genero")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Inscripcion Carreras y Periodo Academico")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Inscritos vs Matriculados")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Matriculas Carreras y Genero")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                 utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Matriculas por Periodo Academico")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                 utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Porcentaje de Asistencia Alumnos")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                 utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Porcentaje de Asistencia Funcionarios")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                 utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
}    */
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
   public SeleccionTabla getSel_tab_carrera() {
        return sel_tab_carrera;
    }

    public void setSel_tab_carrera(SeleccionTabla sel_tab_carrera) {
        this.sel_tab_carrera = sel_tab_carrera;
    }

    public SeleccionTabla getSel_tab_periodo() {
        return sel_tab_periodo;
    }

    public void setSel_tab_periodo(SeleccionTabla sel_tab_periodo) {
        this.sel_tab_periodo = sel_tab_periodo;
    }

    public SeleccionCalendario getSel_fechas() {
        return sel_fechas;
    }

    public void setSel_fechas(SeleccionCalendario sel_fechas) {
        this.sel_fechas = sel_fechas;
    }

 
}

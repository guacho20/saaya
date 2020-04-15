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
import framework.componentes.Boton;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
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
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;

public class ReporteHorario extends Pantalla{
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
    String periodo="";
    String jornada="";
    String mension="";
    String nivel="";
    
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    
    public ReporteHorario(){
        bar_botones.getBot_insertar().setRendered(false);
        bar_botones.getBot_guardar().setRendered(false);
        bar_botones.getBot_eliminar().setRendered(false);
        bar_botones.quitarBotonsNavegacion();

       rep_reporte.setId("rep_reporte");
       rep_reporte.getBot_aceptar().setMetodo("aceptarReporte");
       agregarComponente(rep_reporte);
       bar_botones.agregarReporte();
       sel_rep.setId("sel_rep");
       agregarComponente(sel_rep);    
       
       sel_fechas.setId("sec_rango_fechas");
       sel_fechas.getBot_aceptar().setMetodo("aceptarReporte");
       sel_fechas.setFechaActual();
       agregarComponente(sel_fechas);
       
       Imagen ImaReportes = new Imagen();
        ImaReportes.setValue("imagenes/ModAsistencia/Asistencia.png");
        
        panOpcion.setId("pan_opcion");
        panOpcion.setTransient(true);
        panOpcion.setHeader("REPORTES HORARIO");
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
        agregarComponente(grid_pant);
        panOpcion.getChildren().add(grid_pant);
        agregarComponente(panOpcion);
        
        sel_tab_periodo.setId("sel_tab_periodo");
        sel_tab_periodo.setTitle("SELECCIONE EL PERIODO");
        sel_tab_periodo.setSeleccionTabla(ser_estructura_organizacional.getPeriodoAcademico("true, false"), "IDE_YSTPEA");
        //select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc", "ide_yhodia
        //set_tab_dias.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        sel_tab_periodo.setWidth("60%");
        sel_tab_periodo.setHeight("60%");
        sel_tab_periodo.setRadio();
        sel_tab_periodo.getBot_aceptar().setMetodo("aceptarReporte");
        agregarComponente(sel_tab_periodo);
        
        sel_tab_jornada.setId("sel_tab_jornada");
        sel_tab_jornada.setTitle("SELECCIONE LA JORNADA");
        sel_tab_jornada.setSeleccionTabla(ser_estructura_organizacional.getJornada("true"), "IDE_YSTJOR");
        //select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc", "ide_yhodia
        //set_tab_dias.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        sel_tab_jornada.setWidth("60%");
        sel_tab_jornada.setHeight("60%");
        sel_tab_jornada.setRadio();
        sel_tab_jornada.getBot_aceptar().setMetodo("aceptarReporte");
        agregarComponente(sel_tab_jornada);
        
        sel_tab_mension.setId("sel_tab_mension");
        sel_tab_mension.setTitle("SELECCIONE LA MENSIÓN");
        sel_tab_mension.setSeleccionTabla(ser_estructura_organizacional.getMension(), "IDE_YSTMEN");
        //select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc", "ide_yhodia
        //set_tab_dias.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        sel_tab_mension.setWidth("60%");
        sel_tab_mension.setHeight("60%");
        sel_tab_mension.setRadio();
        sel_tab_mension.getBot_aceptar().setMetodo("aceptarReporte");
        agregarComponente(sel_tab_mension);
        
        sel_tab_nivel.setId("sel_tab_nivel");
        sel_tab_nivel.setTitle("SELECCIONE EL NIVEL");
        sel_tab_nivel.setSeleccionTabla(ser_estructura_organizacional.getNivelEducacion(), "IDE_YSTNIE");
        //select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc", "ide_yhodia
        //set_tab_dias.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        sel_tab_nivel.setWidth("60%");
        sel_tab_nivel.setHeight("60%");
        sel_tab_nivel.setRadio();
        sel_tab_nivel.getBot_aceptar().setMetodo("aceptarReporte");
        agregarComponente(sel_tab_nivel);
        
        sel_tab_docente.setId("sel_tab_docente");
        sel_tab_docente.setTitle("SELECCIONE EL DOCENTE");
        sel_tab_docente.setSeleccionTabla(ser_personal.getDatopersonal("true,false"), "IDE_YPEDPE");
        //select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc", "ide_yhodia
        sel_tab_docente.getTab_seleccion().getColumna("apellido_ypedpe").setFiltro(true);
        sel_tab_docente.getTab_seleccion().getColumna("nombre_ypedpe").setFiltro(true);
        sel_tab_docente.setWidth("60%");
        sel_tab_docente.setHeight("60%");
        sel_tab_docente.setRadio();
        sel_tab_docente.getBot_aceptar().setMetodo("aceptarReporte");
        agregarComponente(sel_tab_docente);
    }
    public void abrirListaReportes() {
// TODO Auto-generated method stub
     rep_reporte.dibujar();
}


public void aceptarReporte() {
    if (rep_reporte.getReporteSelecionado().equals("Horarios por Jornada")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
        }
        else if (sel_tab_periodo.isVisible()){
                periodo = sel_tab_periodo.getValorSeleccionado();
                sel_tab_periodo.cerrar();
                sel_tab_jornada.dibujar();
                System.out.println("per "+periodo);
        }
        else if(sel_tab_jornada.isVisible()){
            if (sel_tab_jornada.getValorSeleccionado()!= null && sel_tab_jornada.getValorSeleccionado().isEmpty() == false) {
                System.out.println("per "+periodo);
                Map map_parametros = new HashMap();
                map_parametros.put("p_periodo",Integer.parseInt(periodo));
                map_parametros.put("p_jornada", Integer.parseInt(sel_tab_jornada.getValorSeleccionado()));
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_jornada.cerrar();
                sel_rep.dibujar();
        }
        } 
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    }
    else {
        utilitario.agregarMensajeInfo("No se puede continuar", "No ha seleccionado ningun registro requerido");
    }
    
    if (rep_reporte.getReporteSelecionado().equals("Horarios por nivel")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
        }
        else if (sel_tab_periodo.isVisible()){
                periodo = sel_tab_periodo.getValorSeleccionado();
                sel_tab_periodo.cerrar();
                sel_tab_jornada.dibujar();
                System.out.println("per "+periodo);
        }
        else if(sel_tab_jornada.isVisible()){
               jornada = sel_tab_jornada.getValorSeleccionado();
               System.out.println("jornada "+jornada);
               sel_tab_jornada.cerrar();
               sel_tab_mension.dibujar();
        }
        else if(sel_tab_mension.isVisible()){
               mension = sel_tab_mension.getValorSeleccionado();
               System.out.println("mension "+mension);
               sel_tab_mension.cerrar();
               sel_tab_nivel.dibujar();
        }
        else if(sel_tab_nivel.isVisible()){
               if (sel_tab_nivel.getValorSeleccionado()!= null && sel_tab_nivel.getValorSeleccionado().isEmpty() == false) {
                //System.out.println("per "+periodo);
                Map map_parametros = new HashMap();
                map_parametros.put("p_periodo",Integer.parseInt(periodo));
                map_parametros.put("p_jornada",Integer.parseInt(jornada));
                map_parametros.put("p_mension",Integer.parseInt(mension));
                map_parametros.put("p_nivel", Integer.parseInt(sel_tab_nivel.getValorSeleccionado()));
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_nivel.cerrar();
                System.out.println("nivel "+nivel);
                sel_rep.dibujar();
        }
        } 
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    }
    else {
        utilitario.agregarMensajeInfo("No se puede continuar", "No ha seleccionado ningun registro requerido");
    }
    
    if (rep_reporte.getReporteSelecionado().equals("Horarios por docente")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
        }
        else if (sel_tab_periodo.isVisible()){
                periodo = sel_tab_periodo.getValorSeleccionado();
                sel_tab_periodo.cerrar();
                sel_tab_jornada.dibujar();
                System.out.println("per "+periodo);
        }
        else if(sel_tab_jornada.isVisible()){
               jornada = sel_tab_jornada.getValorSeleccionado();
               System.out.println("jornada "+jornada);
               sel_tab_jornada.cerrar();
               sel_tab_mension.dibujar();
        }
        else if(sel_tab_mension.isVisible()){
               mension = sel_tab_mension.getValorSeleccionado();
               System.out.println("mension "+mension);
               sel_tab_mension.cerrar();
               sel_tab_docente.dibujar();
        }
        else if(sel_tab_docente.isVisible()){
               if (sel_tab_docente.getValorSeleccionado()!= null && sel_tab_docente.getValorSeleccionado().isEmpty() == false) {
                //System.out.println("per "+periodo);
                Map map_parametros = new HashMap();
                map_parametros.put("p_periodo",Integer.parseInt(periodo));
                map_parametros.put("p_jornada",Integer.parseInt(jornada));
                map_parametros.put("p_mension",Integer.parseInt(mension));
                map_parametros.put("p_docente", Integer.parseInt(sel_tab_docente.getValorSeleccionado()));
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_nivel.cerrar();
                System.out.println("nivel "+nivel);
                sel_rep.dibujar();
        }
        } 
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    }
    else {
        utilitario.agregarMensajeInfo("No se puede continuar", "No ha seleccionado ningun registro requerido");
    }
    
     if (rep_reporte.getReporteSelecionado().equals("Horarios por docente grupos")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                 sel_tab_periodo.dibujar();
                }

             else if (sel_tab_periodo.isVisible()){
                //System.out.println("per "+periodo);
                Map map_parametros = new HashMap();
                map_parametros.put("p_periodo", Integer.parseInt(sel_tab_periodo.getValorSeleccionado()));
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_nivel.cerrar();
                System.out.println("nivel "+nivel);
                sel_tab_periodo.cerrar();
                sel_rep.dibujar();
                
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
    
}

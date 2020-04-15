/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

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

/**
 *@author Nicolas Cajilema
 * @author Janeth Pullotasig
 * 
 */
public class ReporteAsistencia extends Pantalla {
    private Reporte rep_reporte=new Reporte();
    private SeleccionFormatoReporte sel_rep=new SeleccionFormatoReporte();  
    private SeleccionCalendario sel_fechas= new SeleccionCalendario();
    private Dialogo dia_tipo = new Dialogo();
    private Radio rad_tipo_justificacion = new Radio();
    private SeleccionTabla sel_tab_carrera = new SeleccionTabla();
    private Panel panOpcion = new Panel();
    String fechai="";
    String fechaf="";
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
    
    public ReporteAsistencia(){
        
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
       
        List lista = new ArrayList();
	       Object fila1[] = {
	           "1", "ALUMNOS"
	       };
	       Object fila2[] = {
	           "2", "COLABORADORES"
	       };
	       
	       lista.add(fila1);
	       lista.add(fila2);
       
       rad_tipo_justificacion.setId("rad_tipo_justificacion");
       rad_tipo_justificacion.setLabel("Tipo Justificación");
       rad_tipo_justificacion.setRadio(lista);
       
       dia_tipo.setId("dia_tipo");
       dia_tipo.setWidth("25%");
       dia_tipo.setHeight("20%");
       dia_tipo.getBot_aceptar().setMetodo("aceptarReporte");
       
        Grid gri_tipo = new Grid();
        gri_tipo.setId("gri_tipo");
        gri_tipo.getChildren().add(rad_tipo_justificacion);
        dia_tipo.setDialogo(gri_tipo);
        
        agregarComponente(dia_tipo);
        //Diseño de fondo reporte asistencia//
        Imagen ImaReportes = new Imagen();
        ImaReportes.setValue("imagenes/ModAsistencia/Asistencia.png");
        
        panOpcion.setId("pan_opcion");
        panOpcion.setTransient(true);
        panOpcion.setHeader("REPORTES ASISTENCIA");
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
        
        
        
        
         //PANTALLA CONSULTA LA ASISTENCIA
            sel_tab_carrera.setId("sel_tab_carrera");
            sel_tab_carrera.setWidth("80%");
            sel_tab_carrera.setTitle("SELECCIONE LAS CURSO Y MATERIAS PARA REPORTE DE ASISTENCIA");
            sel_tab_carrera.getBot_aceptar().setMetodo("aceptarReporte");
            sel_tab_carrera.setSeleccionTabla(ser_asistencia.getDocenteMensionParalelo("-1","-1"), "ide_ypemad");
            sel_tab_carrera.getTab_seleccion().getColumna("ide_ystmen").setVisible(false);
            sel_tab_carrera.getTab_seleccion().getColumna("ide_ystmat").setVisible(false);
            sel_tab_carrera.getTab_seleccion().getColumna("orden_ystnie").setVisible(false);
            sel_tab_carrera.getTab_seleccion().getColumna("ide_yhogra").setVisible(false);
            sel_tab_carrera.getTab_seleccion().getColumna("descripcion_ystmen").setFiltro(true);
            sel_tab_carrera.getTab_seleccion().getColumna("detalle_ystmat").setFiltro(true);
            sel_tab_carrera.getTab_seleccion().getColumna("apellido_ypedpe").setFiltro(true); 
            agregarComponente(sel_tab_carrera);
        
    }
public void abrirListaReportes() {
// TODO Auto-generated method stub
rep_reporte.dibujar();
}


public void aceptarReporte() {
    if (rep_reporte.getReporteSelecionado().equals("Registro Justificaciones")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_fechas.dibujar();
        }
        else if (sel_fechas.isVisible()){
                
                fechai=sel_fechas.getFecha1String();
                fechaf=sel_fechas.getFecha2String();
                sel_fechas.cerrar();
                dia_tipo.dibujar();
        }
        else if(dia_tipo.isVisible()){
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pfechai",fechai );
                map_parametros.put("pfechaf", fechaf);
                map_parametros.put("ptipo", Integer.parseInt(rad_tipo_justificacion.getValue().toString()));
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                dia_tipo.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    }
    if (rep_reporte.getReporteSelecionado().equals("Registro Asistencia Alumnos")){
         if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_fechas.dibujar();
        }
         else if (sel_fechas.isVisible()){
                
                fechai=sel_fechas.getFecha1String();
                fechaf=sel_fechas.getFecha2String();
                sel_fechas.cerrar();
                sel_tab_carrera.dibujar();
        }
         else if (sel_tab_carrera.isVisible()){
                String str_seleccionado = sel_tab_carrera.getSeleccionados();
                if (str_seleccionado != null) {
                
              
                    
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pfechai",fechai );
                map_parametros.put("pfechaf", fechaf);
                map_parametros.put("ptipo", str_seleccionado);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();    
                
                } else {
                    utilitario.agregarMensajeInfo("Debe seleccionar al menos un registro", "");
                }
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
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

    public SeleccionTabla getSel_tab_carrera() {
        return sel_tab_carrera;
    }

    public void setSel_tab_carrera(SeleccionTabla sel_tab_carrera) {
        this.sel_tab_carrera = sel_tab_carrera;
    }

 
}

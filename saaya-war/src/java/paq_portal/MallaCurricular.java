/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_portal;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import paq_estructura.*;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Reporte;
import framework.componentes.Tabla;
import framework.componentes.VisualizarPDF;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author USER
 */
public class MallaCurricular extends Pantalla{
    
    Tabla tab_carrera =new Tabla ();
    Tabla tab_mension = new Tabla();
    Tabla tab_malla = new Tabla();
    private Reporte rep_reporte = new Reporte();
    private Map p_parametros = new HashMap();
    private VisualizarPDF vipdf_comprobante = new VisualizarPDF();
   @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
   
    public MallaCurricular (){
        
         //Permite crear la tabla 
        bar_botones.getBot_inicio().setRendered(false);
        bar_botones.getBot_atras().setRendered(false);
        bar_botones.getBot_insertar().setRendered(false);
        bar_botones.getBot_eliminar().setRendered(false);
        bar_botones.getBot_fin().setRendered(false);
        bar_botones.getBot_siguiente().setRendered(false);
        bar_botones.getBot_guardar().setRendered(false);
        TablaGenerica tab_alumno=utilitario.consultar(ser_estructura.getMensionAlumno(utilitario.getVariable("ALUMNO")));
        String mension="-1";
        String nombre_mension="EL ESTUDIANTE NO REGISTRA MATRICULA";
        if(tab_alumno.getTotalFilas()>0){
            mension= tab_alumno.getValor("ide_ystmen");
            nombre_mension= tab_alumno.getValor("descripcion_ystmen");
        }
        tab_carrera.setId("tab_carrera");
        tab_carrera.setSql(ser_estructura.getCarreraMension(mension));
        tab_carrera.setHeader(nombre_mension);
        tab_carrera.getColumna("ide_ystmal").setVisible(false);
        tab_carrera.getColumna("total_horas_ystmen").setVisible(false);
        tab_carrera.getColumna("descripcion_ystmen").setVisible(false);
        tab_carrera.getColumna("detalle_ystmat").setNombreVisual("MATERIA");
        tab_carrera.getColumna("descripcion_ystnie").setNombreVisual("NIVEL");
        tab_carrera.getColumna("numero_horas_ystmal").setNombreVisual("NRO. HORAS");
        tab_carrera.getColumna("codigo_ystmal").setNombreVisual("CODIGO MATERIA");
        tab_carrera.getColumna("codigo_ystmal").setOrden(4);        
        tab_carrera.getColumna("detalle_ystmat").setOrden(2);
        tab_carrera.getColumna("descripcion_ystnie").setOrden(1);
        tab_carrera.getColumna("numero_horas_ystmal").setOrden(3);
        tab_carrera.getColumna("detalle_ystmat").setLectura(true);
        tab_carrera.getColumna("descripcion_ystnie").setLectura(true);
        tab_carrera.getColumna("numero_horas_ystmal").setLectura(true);
        tab_carrera.getColumna("codigo_ystmal").setLectura(true);
        tab_carrera.dibujar();
        
        PanelTabla pat_carrera =new PanelTabla();
        pat_carrera.setId("pat_carrera");
        pat_carrera.setPanelTabla(tab_carrera);
        
        
        
        
        Division div_carrera =new Division();
        div_carrera.setId("div_carrera");
        div_carrera.dividir1(pat_carrera);
        
        agregarComponente(div_carrera);
        
        
         Boton bot_imprimirpdf = new Boton();
            bot_imprimirpdf.setIcon("ui-icon-print");
            bot_imprimirpdf.setValue("IMPRIMIR REPORTE");
            bot_imprimirpdf.setMetodo("abrirListaReportes");
            bar_botones.agregarBoton(bot_imprimirpdf);
            
             vipdf_comprobante.setId("vipdf_comprobante");
            vipdf_comprobante.setTitle("REPORTES MALLA CURRICULAR");
            agregarComponente(vipdf_comprobante);
        
     }   
 public void abrirListaReportes() {
       
                generarPDF();
            

    }

    public void generarPDF() {
       TablaGenerica tab_alumno=utilitario.consultar(ser_estructura.getMensionAlumno(utilitario.getVariable("ALUMNO")));
        String mension="-1";
        String nombre_mension="EL ESTUDIANTE NO REGISTRA MATRICULA";
        if(tab_alumno.getTotalFilas()>0){
            mension= tab_alumno.getValor("ide_ystmen");
            nombre_mension= tab_alumno.getValor("descripcion_ystmen");
        }
        String nombre_reporte = "";
        String reporte = "";
        reporte = "rep_portal/rep_malla.jasper";

               
                Map map_parametros = new HashMap();
                    map_parametros.put("nombre", utilitario.getVariable("NICK"));
                    map_parametros.put("pide_mension", mension);
                    map_parametros.put("ide_yaldap", utilitario.getVariable("ALUMNO"));
                vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
            
        
    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        
        if(tab_carrera.isFocus()){
        tab_carrera.guardar();
        }
        else if(tab_mension.isFocus()){
         tab_mension.guardar();
        }
        else if(tab_malla.isFocus()){
            tab_malla.guardar();
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if(tab_carrera.isFocus()){
        tab_carrera.eliminar();
        }
        else if(tab_mension.isFocus()){
         tab_mension.eliminar();
        }
        else if(tab_malla.isFocus()){
            tab_malla.eliminar();
        }        
    }

    public Tabla getTab_carrera() {
        return tab_carrera;
    }

    public void setTab_carrera(Tabla tab_carrera) {
        this.tab_carrera = tab_carrera;
    }

    public Tabla getTab_mension() {
        return tab_mension;
    }

    public void setTab_mension(Tabla tab_mension) {
        this.tab_mension = tab_mension;
    }

    public Tabla getTab_malla() {
        return tab_malla;
    }

    public void setTab_malla(Tabla tab_malla) {
        this.tab_malla = tab_malla;
    }

    public Reporte getRep_reporte() {
        return rep_reporte;
    }

    public void setRep_reporte(Reporte rep_reporte) {
        this.rep_reporte = rep_reporte;
    }

    public VisualizarPDF getVipdf_comprobante() {
        return vipdf_comprobante;
    }

    public void setVipdf_comprobante(VisualizarPDF vipdf_comprobante) {
        this.vipdf_comprobante = vipdf_comprobante;
    }

    
}

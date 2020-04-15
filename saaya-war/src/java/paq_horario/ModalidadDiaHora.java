/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horario;

import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class ModalidadDiaHora extends Pantalla{
     private Tabla tab_modalidad_dia_hora = new Tabla();
     private  Combo com_periodo_academico = new Combo();
     private Dialogo dia_modalidad = new Dialogo();
     private SeleccionTabla sel_tab_modalidad = new SeleccionTabla();
  
     @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
     @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    
     
    public ModalidadDiaHora(){
    tab_modalidad_dia_hora.setId("tab_modalidad_dia_hora");   //identificador
    tab_modalidad_dia_hora.setTabla("yavirac_hora_modalidad_dia_hora", "ide_yhomdh", 1);
    tab_modalidad_dia_hora.getColumna("ide_ystmod").setCombo(ser_estructura_organizacional.getModalidad("true,false"));
    tab_modalidad_dia_hora.getColumna("ide_yhodia").setCombo(ser_horarios.getDia());
    tab_modalidad_dia_hora.getColumna("ide_yhothj").setCombo(ser_horarios.getHorarios("true,false"));
    tab_modalidad_dia_hora.getColumna("ide_yhohor").setCombo(ser_horarios.getHora("true,false"));
    tab_modalidad_dia_hora.getColumna("ide_ystpea").setVisible(false);
    tab_modalidad_dia_hora.dibujar();
        /*agregarComponente(tab_hora_hora);*/ 
        
        PanelTabla pat_modalidad_dia_hora = new PanelTabla();
        pat_modalidad_dia_hora.setId("pat_modalidad_dia_hora");
        pat_modalidad_dia_hora.setPanelTabla(tab_modalidad_dia_hora);
        Division div_modalidad_dia_hora = new Division();
        div_modalidad_dia_hora.setId("div_hora_hora");
        div_modalidad_dia_hora.dividir1(pat_modalidad_dia_hora);
        agregarComponente(div_modalidad_dia_hora);    
        
        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        agregarComponente(com_periodo_academico);
        bar_botones.agregarComponente(com_periodo_academico);
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
        
        Boton bot_generarhorario = new Boton();
        bot_generarhorario.setIcon("ui-icon-newwin");
        bot_generarhorario.setValue("Generar Matriz Horario");
        bot_generarhorario.setTitle("Generar Matriz Horario");
        bar_botones.agregarBoton(bot_generarhorario);    
        bot_generarhorario.setMetodo("generarMatrizHorario");
        
        sel_tab_modalidad.setId("sel_tab_modalidad");
        sel_tab_modalidad.setTitle("TABLA DE LA MODALIDAD");
        sel_tab_modalidad.setSeleccionTabla(ser_estructura_organizacional.getModalidad("true,false"), "ide_ystmod");
        sel_tab_modalidad.getTab_seleccion().getColumna("descripcion_ystmod").setNombreVisual("Modalidad");

        sel_tab_modalidad.setWidth("80%");
        sel_tab_modalidad.setHeight("70%");
        agregarComponente(sel_tab_modalidad);
        
                }     
     public void generarMatrizHorario (){
    
    if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea generar");
        return;
        }
        else {
       sel_tab_modalidad.dibujar();
            
                }
    
    
}
     
          public void filtroComboPeriodoAcademnico(){
        
        tab_modalidad_dia_hora.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_modalidad_dia_hora.ejecutarSql();
        utilitario.addUpdate("tab_hora_definicion");
        
    }
          
    @Override
    public void insertar() {
        if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Academico");
            return;
        }
        else {
       tab_modalidad_dia_hora.insertar();
       tab_modalidad_dia_hora.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
       utilitario.addUpdateTabla(tab_modalidad_dia_hora, "ide_ystpea", "");
    }
    }

    @Override
    public void guardar() {
        
    tab_modalidad_dia_hora.guardar();
    guardarPantalla();
        
    }

    @Override
    public void eliminar() {
     tab_modalidad_dia_hora.eliminar();
     
    }

    public Tabla getTab_modalidad_dia_hora() {
        return tab_modalidad_dia_hora;
    }

    public void setTab_modalidad_dia_hora(Tabla tab_modalidad_dia_hora) {
        this.tab_modalidad_dia_hora = tab_modalidad_dia_hora;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public Dialogo getDia_modalidad() {
        return dia_modalidad;
    }

    public void setDia_modalidad(Dialogo dia_modalidad) {
        this.dia_modalidad = dia_modalidad;
    }

    public SeleccionTabla getSel_tab_modalidad() {
        return sel_tab_modalidad;
    }

    public void setSel_tab_modalidad(SeleccionTabla sel_tab_modalidad) {
        this.sel_tab_modalidad = sel_tab_modalidad;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horario;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

import sistema.aplicacion.Pantalla;

/**
 *
 * @author ANDRES
 */
public class HoraMallaPeriodo extends Pantalla{
        private Tabla tab_hora_malla_periodo = new Tabla();
        private Combo com_periodo_academico = new Combo();
        
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

     public HoraMallaPeriodo(){
        tab_hora_malla_periodo.setId("tab_hora_malla_periodo");   //identificador
        tab_hora_malla_periodo.setTabla("yavirac_hora_malla_periodo", "ide_yhomap", 1);
        tab_hora_malla_periodo.getColumna("ide_ystmal").setCombo(ser_estructura_organizacional.getMalla());
        tab_hora_malla_periodo.getColumna("ide_yhomap").setNombreVisual("CÓDIGO PRINCIPAL");
        tab_hora_malla_periodo.getColumna("ide_ystmal").setNombreVisual("MALLA ACADÉMICA");
        tab_hora_malla_periodo.getColumna("ide_ystpea").setVisible(false);
        tab_hora_malla_periodo.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_malla_periodo = new PanelTabla();
        pat_hora_malla_periodo.setId("pat_hora_malla_periodo");
        pat_hora_malla_periodo.getMenuTabla().getItem_buscar().setRendered(false);
        pat_hora_malla_periodo.getMenuTabla().getItem_importar().setRendered(false);
        pat_hora_malla_periodo.getMenuTabla().getItem_excel().setRendered(false);
        pat_hora_malla_periodo.getMenuTabla().getItem_excel_filtro().setRendered(false);
        pat_hora_malla_periodo.getMenuTabla().getItem_formato().setRendered(false);
        pat_hora_malla_periodo.getMenuTabla().quitarSubmenuOtros();
        pat_hora_malla_periodo.setPanelTabla(tab_hora_malla_periodo);
        Division div_hora_malla_periodo = new Division();
        div_hora_malla_periodo.setId("div_hora_dia");
        div_hora_malla_periodo.dividir1(pat_hora_malla_periodo);
        agregarComponente(div_hora_malla_periodo); 
        
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADÉMICO"));
        bar_botones.agregarComponente(com_periodo_academico);
        
     }  
     public void filtroComboPeriodoAcademnico(){
        
        tab_hora_malla_periodo.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_hora_malla_periodo.ejecutarSql();
        utilitario.addUpdate("tab_hora_definicion");
        
    }
     
     @Override
    public void insertar() {
        if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Académico");
            return;
        }
        else {
        
        tab_hora_malla_periodo.insertar();
        tab_hora_malla_periodo.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
       utilitario.addUpdateTabla( tab_hora_malla_periodo, "ide_ystpea", "");
       }
    }
     @Override
    public void guardar() {
        tab_hora_malla_periodo.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_hora_malla_periodo.eliminar();
    }

    public Tabla getTab_hora_malla_periodo() {
        return tab_hora_malla_periodo;
    }

    public void setTab_hora_malla_periodo(Tabla tab_hora_malla_periodo) {
        this.tab_hora_malla_periodo = tab_hora_malla_periodo;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }
    
}

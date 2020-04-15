/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author User
 */
public class DocumentoPeriodo extends Pantalla{
private Tabla tab_tabla = new Tabla();
    private Combo com_periodo_academico = new Combo();

    @EJB
    private final ServicioEstructuraOrganizacional  ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

public DocumentoPeriodo(){
    
        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura.getPeriodoAcademico("true"));
        agregarComponente(com_periodo_academico);
        bar_botones.agregarComponente(com_periodo_academico);
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");    
    
    tab_tabla.setId("tab_tabla");
    tab_tabla.setTabla("yavirac_stror_documento_periodo", "ide_ystdop", 1);
    tab_tabla.setCondicion("ide_ystpea=-1");
    tab_tabla.getColumna("ide_ystdor").setCombo(ser_estructura.getDocumentoRequerido("true,false"));
    tab_tabla.getColumna("ide_ystrep").setCombo(ser_estructura.getRequeridoPara());
    tab_tabla.getColumna("ide_ystpea").setVisible(false);
    tab_tabla.getColumna("ide_ystdop").setNombreVisual("CÓDIGO");
    tab_tabla.getColumna("ide_ystdor").setNombreVisual("DOCUMENTO REQUERIDO");
    tab_tabla.getColumna("ide_ystrep").setNombreVisual("REQUERIDO PARA:");
    tab_tabla.dibujar();
    
    PanelTabla pat_panel = new PanelTabla();
    pat_panel.setPanelTabla(tab_tabla);
    
    Division div_dividir = new Division();
    div_dividir.dividir1(pat_panel);
    
    agregarComponente(div_dividir);
}

     public void filtroComboPeriodoAcademnico(){
        
        tab_tabla.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_tabla.ejecutarSql();
        utilitario.addUpdate("tab_pre_inscrip");
        
    }  
    @Override
    public void insertar() {
         if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Academico");
            return;
        }
         else { 
        tab_tabla.insertar();
        tab_tabla.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
        utilitario.addUpdateTabla(tab_tabla, "ide_ystpea", "");
         }
    }

    @Override
    public void guardar() {
        tab_tabla.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tabla.eliminar();
    }

    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    }
    
}

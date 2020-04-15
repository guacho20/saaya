/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author User
 */
public class FechaControl extends Pantalla{
    
     private Tabla tab_tabla = new Tabla();
     private Combo com_periodo_academico = new Combo();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
     
    public FechaControl (){
        
        
            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
            bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
            

            
        tab_tabla.setId("tab_tabla");
        tab_tabla.setTabla("yavirac_asis_fecha_control", "ide_yasfec", 1);
        tab_tabla.setCondicion("ide_yasfec=-1");
        tab_tabla.getColumna("ide_yassem").setCombo(ser_asistencia.getSemana());
        tab_tabla.getColumna("ide_ystpea").setVisible(false);
        tab_tabla.getColumna(" ide_yasfec").setNombreVisual("IDE");
        tab_tabla.getColumna(" ide_yassem").setNombreVisual("ASISTENCIA");
        tab_tabla.getColumna("fecha_yasfec").setNombreVisual("FECHA");
        tab_tabla.getColumna("observacion_yasfec").setNombreVisual("OBSERVACION");
        tab_tabla.getColumna("bloqueado_yasfec").setNombreVisual("BLOQUEADO");
        tab_tabla.getColumna("activo_yasfec").setNombreVisual("ACTIVO INHABILITADO");
        tab_tabla.getColumna(" festivo_yasfec").setNombreVisual("FESTIVO");
        tab_tabla.dibujar();
        
        PanelTabla pat_tabla = new PanelTabla();
        pat_tabla.setPanelTabla(tab_tabla);
        
        Division div_division = new Division();
        div_division.dividir1(pat_tabla);
        
        agregarComponente(div_division);
    }

    public void filtroComboPeriodoAcademnico(){
        
        tab_tabla.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_tabla.ejecutarSql();
        utilitario.addUpdate("tab_tabla");
        
     
    }     
    @Override
    public void insertar() {
        tab_tabla.insertar();
        tab_tabla.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
        utilitario.addUpdateTabla(tab_tabla, "ide_ystpea", "");
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

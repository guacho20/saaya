/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horario;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraPersonalPeriodo extends Pantalla {
    private Tabla tab_hora_personal_periodo = new Tabla(); 
    private Tabla tab_hora_horario_docente = new Tabla();
    private Combo com_periodo_academico = new Combo();
    
    
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
     @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    
     @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    
     public HoraPersonalPeriodo(){
        tab_hora_personal_periodo.setId("tab_hora_personal_periodo");   //identificador
        tab_hora_personal_periodo.setTabla("yavirac_stror_personal_periodo", "ide_ystpep", 1);
        tab_hora_personal_periodo.agregarRelacion(tab_hora_horario_docente);
        tab_hora_personal_periodo.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_hora_personal_periodo.getColumna("ide_yhotgh").setCombo(ser_horarios.getTipoGeneracionHora());
        tab_hora_personal_periodo.getColumna("ide_ystpea").setVisible(false);
        tab_hora_personal_periodo.getColumna("ide_ystpep").setNombreVisual("CÓDIGO PRINCIPAL");
        tab_hora_personal_periodo.getColumna("ide_ypedpe").setNombreVisual("PERSONAL DOCENTES");
        tab_hora_personal_periodo.getColumna("ide_yhotgh").setNombreVisual("TIPO GENERACIÓN DE HORARIO");
        tab_hora_personal_periodo.getColumna("fecha_fin_ystpep").setNombreVisual("FECHA FINAL");
        tab_hora_personal_periodo.getColumna("observacion_ystpep").setNombreVisual("OBSERVACIÓN");
        tab_hora_personal_periodo.getColumna("activo_ystpep").setNombreVisual("ACTIVO");
        tab_hora_personal_periodo.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_personal_periodo = new PanelTabla();
        pat_hora_personal_periodo.setId("pat_hora_personal_periodo");
        pat_hora_personal_periodo.setPanelTabla(tab_hora_personal_periodo);
        
        tab_hora_horario_docente.setId("tab_hora_horario_docente");   //identificador
        tab_hora_horario_docente.setTabla("yavirac_hora_horario_docente", "ide_yhohod", 2);
        tab_hora_horario_docente.getColumna("ide_yhothj").setCombo(ser_horarios.getHorarios("true,false"));
        tab_hora_horario_docente.getColumna("ide_yhohod").setNombreVisual("CÓDIGO PRINCIPAL");
        tab_hora_horario_docente.getColumna("ide_yhothj").setNombreVisual("TIPO DE HORARIO");
        tab_hora_horario_docente.getColumna("hora_inicial_yhohod").setNombreVisual("HORA INICIAL");
        tab_hora_horario_docente.getColumna("hora_final_yhohod").setNombreVisual("HORA FINAL");
        tab_hora_horario_docente.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_horario_docente = new PanelTabla();
        pat_hora_horario_docente.setId("pat_hora_horario_docente");
        pat_hora_horario_docente.setPanelTabla(tab_hora_horario_docente);
        
        Division div_hora_personal_periodo = new Division();
        div_hora_personal_periodo.setId("div_hora_personal_periodo");
        div_hora_personal_periodo.dividir2(pat_hora_personal_periodo, pat_hora_horario_docente, "50%", "H");
        agregarComponente(div_hora_personal_periodo); 
        
        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        agregarComponente(com_periodo_academico);
        bar_botones.agregarComponente(com_periodo_academico);
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
        
    }
    @Override
    public void insertar() {
       if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Academico");
            return;
        }
       else if (tab_hora_personal_periodo.isFocus()){ {
       tab_hora_personal_periodo.insertar();
       tab_hora_personal_periodo.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
       utilitario.addUpdateTabla(tab_hora_personal_periodo, "ide_ystpea", "");
       }
       }
       else if (tab_hora_horario_docente.isFocus()){
            tab_hora_horario_docente.insertar();
        }
    }

    @Override
    public void guardar() {
        if(tab_hora_personal_periodo.isFocus()){
        tab_hora_personal_periodo.guardar();
        }
        else if (tab_hora_horario_docente.isFocus()){
        tab_hora_horario_docente.guardar();
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if(tab_hora_personal_periodo.isFocus()){
       tab_hora_personal_periodo.eliminar();
        }
        else if (tab_hora_horario_docente.isFocus()){
            tab_hora_horario_docente.eliminar();
        }
    }

    public Tabla getTab_hora_personal_periodo() {
        return tab_hora_personal_periodo;
    }

    public void setTab_hora_personal_periodo(Tabla tab_hora_personal_periodo) {
        this.tab_hora_personal_periodo = tab_hora_personal_periodo;
    }

    public Tabla getTab_hora_horario_docente() {
        return tab_hora_horario_docente;
    }

    public void setTab_hora_horario_docente(Tabla tab_hora_horario_docente) {
        this.tab_hora_horario_docente = tab_hora_horario_docente;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }
    
}

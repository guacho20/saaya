/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
public class DiaAsistencia extends Pantalla {
    
    private Tabla tab_dia_asistencia = new Tabla();
 
   public DiaAsistencia() {
       
       tab_dia_asistencia.setId("tab_diaasistencia");
       tab_dia_asistencia.setTabla("yavirac_asis_dia_asistencia","ide_yasdas",1);
       tab_dia_asistencia.setHeader("DIA ASISTENCIA");
       tab_dia_asistencia.dibujar();
        
        
        PanelTabla pat_dia_asistencia = new PanelTabla();
        pat_dia_asistencia.setId("pat_diaasistencia");
        pat_dia_asistencia.setPanelTabla(tab_dia_asistencia);
       
        Division div_dia_asistencia = new Division();
        div_dia_asistencia.setId("div_dia_asistencia");
        div_dia_asistencia.dividir1(pat_dia_asistencia);
        agregarComponente(div_dia_asistencia);
    
      } 
    @Override
    public void insertar() {
        tab_dia_asistencia.insertar();
    }

    @Override
    public void guardar() {
        tab_dia_asistencia.guardar();
        guardarPantalla();
        
    }

    @Override
    public void eliminar() {
        tab_dia_asistencia.eliminar();
    }

    public Tabla getTab_diaasistencia() {
        return tab_dia_asistencia;
    }

    public void setTab_diaasistencia(Tabla tab_dia_asistencia) {
        this.tab_dia_asistencia = tab_dia_asistencia;
    }

}

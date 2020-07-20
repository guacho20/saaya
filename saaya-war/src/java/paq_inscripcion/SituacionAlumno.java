/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Cristian
 */
public class SituacionAlumno extends Pantalla{
    
    Tabla tab_situacion_alumno=new Tabla();
    public SituacionAlumno (){
        tab_situacion_alumno.setId("tab_situacion_alumno");
        tab_situacion_alumno.setTabla("yavirac_ins_situa_alum", "ide_yinsia",1);
        tab_situacion_alumno.dibujar();
        
        PanelTabla pat_situacion_alumno = new PanelTabla();
        pat_situacion_alumno.setId("pat_situacion_alumno");
        pat_situacion_alumno.setPanelTabla(tab_situacion_alumno);
        
        Division div_situacion_alumno = new Division();
        div_situacion_alumno.setId("div_situacion_alumno");
        div_situacion_alumno.dividir1(pat_situacion_alumno);
        
        agregarComponente(div_situacion_alumno);
        
              
    }
    

    @Override
    public void insertar() {
        tab_situacion_alumno.insertar();
    }

    @Override
    public void guardar() {
        tab_situacion_alumno.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_situacion_alumno.eliminar();

    }

    public Tabla getTab_situacion_alumno() {
        return tab_situacion_alumno;
    }

    public void setTab_situacion_alumno(Tabla tab_situacion_alumno) {
        this.tab_situacion_alumno = tab_situacion_alumno;
    }
    
}

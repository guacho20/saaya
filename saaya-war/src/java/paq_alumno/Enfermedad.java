/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_alumno;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author RICARDO cCOLLAHUAZO
 */
public class Enfermedad extends Pantalla{
    Tabla tab_enfermedad=new Tabla();
    
public Enfermedad(){
    tab_enfermedad.setId("tab_enfermedad");
    tab_enfermedad.setTabla("yavirac_alum_enfermedad", "ide_yalemf", 1);
    tab_enfermedad.dibujar();
    
    PanelTabla pat_enfermedad=new PanelTabla();
    pat_enfermedad.setId("pat_enfermedad");
    pat_enfermedad.setPanelTabla(tab_enfermedad);
    
    Division div_enfermedad=new Division();
    div_enfermedad.setId("div_enfermedad");
    div_enfermedad.dividir1(pat_enfermedad);
    
    agregarComponente(div_enfermedad);
}
    
    @Override
    public void insertar() {
        tab_enfermedad.insertar();
    }

    @Override
    public void guardar() {
        tab_enfermedad.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_enfermedad.eliminar();
    }

    public Tabla getTab_enfermedad() {
        return tab_enfermedad;
    }

    public void setTab_enfermedad(Tabla tab_enfermedad) {
        this.tab_enfermedad = tab_enfermedad;
    }
    
}

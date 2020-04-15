/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_titulacion.ejb.ServicioTitulacion;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ANDRES
 */
public class TipoActiviResul extends Pantalla{
   private Tabla tab_tipo_activi_resul = new Tabla();
   
   @EJB

    private final ServicioTitulacion ser_titulacion = (ServicioTitulacion) utilitario.instanciarEJB(ServicioTitulacion.class);
    
     public TipoActiviResul (){
    
    tab_tipo_activi_resul.setId("tab_tipo_activi_resul");
    tab_tipo_activi_resul.setTabla("yavirac_titu_tipo_activi_resul","ide_ytitia",1);
    tab_tipo_activi_resul.setHeader("Tipo actividad resultado");
    tab_tipo_activi_resul.getColumna("ide_ytitio").setCombo(ser_titulacion.getTipoObjetivo());
    tab_tipo_activi_resul.getColumna("ide_ytimev").setCombo(ser_titulacion.getMediosVerifica());
    tab_tipo_activi_resul.getColumna("ide_ytiacr").setCombo(ser_titulacion.getActividadResulta());
    tab_tipo_activi_resul.dibujar();    
    
    PanelTabla pat_line = new PanelTabla();
    pat_line.setId("pat_line");
    pat_line.setPanelTabla(tab_tipo_activi_resul);
    
         Division div_tipo_activi_resul = new Division();
         div_tipo_activi_resul.setId("div_tipo_activi_resul");
         div_tipo_activi_resul.dividir1(pat_line);
         
         agregarComponente(div_tipo_activi_resul);
    
}
  
     @Override
    public void insertar() {
        tab_tipo_activi_resul.insertar();
    
    }

    @Override
    public void guardar() {
    
    tab_tipo_activi_resul.guardar();
    guardarPantalla();
    }

    @Override
    public void eliminar() {
tab_tipo_activi_resul.eliminar();


    }

    public Tabla getTab_tipo_activi_resul() {
        return tab_tipo_activi_resul;
    }

    public void setTab_tipo_activi_resul(Tabla tab_tipo_activi_resul) {
        this.tab_tipo_activi_resul = tab_tipo_activi_resul;
    }   
    
}
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
public class ObjetivoIndicaMe extends Pantalla{
    private Tabla tab_objetivo_indica_me = new Tabla ();
 @EJB

    private final ServicioTitulacion ser_titulacion = (ServicioTitulacion) utilitario.instanciarEJB(ServicioTitulacion.class);
    
      public  ObjetivoIndicaMe (){
          tab_objetivo_indica_me.setId("tab_objetivo_indica_mediosv");
          tab_objetivo_indica_me.setTabla("yavirac_titu_objetivo_indica_me","ide_ytiobi", 1);
          tab_objetivo_indica_me.setHeader("Objetivo Indica Medios Verificacion ");
          tab_objetivo_indica_me.getColumna("ide_ytitio").setCombo(ser_titulacion.getTipoObjetivo());
          tab_objetivo_indica_me.getColumna("ide_ytiind").setCombo(ser_titulacion.getIndicador());
          tab_objetivo_indica_me.getColumna("ide_ytimev").setCombo(ser_titulacion.getMediosVerifica());
          
          tab_objetivo_indica_me.dibujar();
          
          PanelTabla pat_line = new PanelTabla();
          pat_line.setId("pat_line");
          pat_line.setPanelTabla(tab_objetivo_indica_me);
          
          Division div_objetivo_indica_me = new Division();
          div_objetivo_indica_me.setId("div_objetivo_indica_me");
          div_objetivo_indica_me.dividir1(pat_line);
          
          agregarComponente(div_objetivo_indica_me);
          
       
          
      }
            
      @Override
    public void insertar() {
    tab_objetivo_indica_me.insertar();
    }

    @Override
    public void guardar() {
    tab_objetivo_indica_me.guardar();
    guardarPantalla();
    }

    @Override
    public void eliminar() {
    tab_objetivo_indica_me.eliminar();
    }

    public Tabla getTab_objetivo_indica_mediosv() {
        return tab_objetivo_indica_me;
    }

    public void setTab_objetivo_indica_me(Tabla tab_objetivo_indica_me) {
        this.tab_objetivo_indica_me = tab_objetivo_indica_me;
    }
        
        
    }

    
    

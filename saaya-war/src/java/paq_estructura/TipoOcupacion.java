/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;
/**
 *
 * @author ITSY
 */
public class TipoOcupacion extends Pantalla {
 Tabla tab_tipo_ocupacion = new Tabla ();
 
  public TipoOcupacion (){
      tab_tipo_ocupacion.setId("tab_tipo_ocupacion");
      tab_tipo_ocupacion.setTabla("yavirac_stror_tipo_ocupacion", "ide_ysttio", 1);
      tab_tipo_ocupacion.dibujar();
      
      PanelTabla pa_tipo_ocupacion = new PanelTabla ();
      pa_tipo_ocupacion.setId("pa_tipo_ocupacion");
      pa_tipo_ocupacion.setPanelTabla(tab_tipo_ocupacion);
      
      Division div_tipo_ocupacion = new Division ();
      div_tipo_ocupacion.setId("div_tipo_ocupacion");
      div_tipo_ocupacion.dividir1(pa_tipo_ocupacion);
      
      agregarComponente(div_tipo_ocupacion);
      
  
  }    
     
        @Override
    public void insertar() {
       
       tab_tipo_ocupacion.insertar();
    }

    @Override
    public void guardar() {
        
       tab_tipo_ocupacion.guardar();
       guardarPantalla();

    }

    @Override
    public void eliminar() {
        tab_tipo_ocupacion.eliminar();
        
    }

    public Tabla getTab_tipo_ocupacion() {
        return tab_tipo_ocupacion;
    }

    public void setTab_tipo_ocupacion(Tabla tab_tipo_ocupacion) {
        this.tab_tipo_ocupacion = tab_tipo_ocupacion;
    }

    
    
    }
    

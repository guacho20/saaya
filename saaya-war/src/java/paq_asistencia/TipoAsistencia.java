/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Nicolas Cajilema
 */
public class TipoAsistencia extends Pantalla {
    private Tabla tab_tipoasistencia = new Tabla();
 
   public TipoAsistencia() {
       
       tab_tipoasistencia.setId("tab_tipoasistencia");
       tab_tipoasistencia.setTabla("yavirac_asis_tipo_asistencia","ide_yastas",1);
       tab_tipoasistencia.setHeader("TIPO ASISTENCIA");
       tab_tipoasistencia.dibujar();
        
      
       
        
        PanelTabla pat_tipoasistencia = new PanelTabla();
        pat_tipoasistencia.setId("pat_tipoasistencia");
        pat_tipoasistencia.setPanelTabla(tab_tipoasistencia);
       
        Division div_tipoasistencia = new Division();
        div_tipoasistencia.setId("div_tipoasistencia");
        div_tipoasistencia.dividir1(pat_tipoasistencia);
        
        agregarComponente(div_tipoasistencia);

      } 
    @Override
    public void insertar() {
       tab_tipoasistencia.insertar();
    }

    @Override
    public void guardar() {
        tab_tipoasistencia.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipoasistencia.eliminar();
    }

    public Tabla getTab_tipoasistencia() {
        return tab_tipoasistencia;
    }

    public void setTab_tipoasistencia(Tabla tab_tipoasistencia) {
        this.tab_tipoasistencia = tab_tipoasistencia;
    }
    
  }
    

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
 * @author Janeth Pullotasig
 */
public class TipoMotivo extends Pantalla {
    private Tabla tab_tipomotivo =new  Tabla();
    public TipoMotivo(){
        tab_tipomotivo.setId("tab_tipomotivo");
        tab_tipomotivo.setTabla("yavirac_asis_tipo_motivo","ide_yastmo",1);
        tab_tipomotivo.setHeader("TIPO MOTIVO");
        tab_tipomotivo.dibujar();
        
        
        
        PanelTabla pat_tipomotivo = new PanelTabla();
        pat_tipomotivo.setId("pat_tipomotivo");
        pat_tipomotivo.setPanelTabla(tab_tipomotivo);
        
       
        Division div_tipomotivo = new Division();
        div_tipomotivo.setId("div_tipomotivo");
        div_tipomotivo.dividir1(pat_tipomotivo);
        
        agregarComponente(div_tipomotivo);   
    }
    @Override
    public void insertar() {
        tab_tipomotivo.insertar();
    }

    @Override
    public void guardar() {
        tab_tipomotivo.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipomotivo.eliminar();
    }

    public Tabla getTab_tipomotivo() {
        return tab_tipomotivo;
    
    }
   public void setTab_tipomotivo(Tabla tab_tipomotivo){
       this.tab_tipomotivo = tab_tipomotivo;
   }
}

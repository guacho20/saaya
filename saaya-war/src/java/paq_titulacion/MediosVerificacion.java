/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ANDRES
 */
public class MediosVerificacion extends Pantalla{
private Tabla tab_medios_verifica =new Tabla ();
public MediosVerificacion (){
    tab_medios_verifica.setId("tab_medios_verifica");
    tab_medios_verifica.setTabla("yavirac_titu_medios_verifica", "ide_ytimev", 1);
    tab_medios_verifica.setHeader("Medios Verificacion");
    tab_medios_verifica.dibujar();
    
    PanelTabla pat_line= new PanelTabla();
        pat_line.setId("pat_line");
        pat_line.setPanelTabla(tab_medios_verifica);
        
       Division div_medios_verifica = new Division();
        div_medios_verifica.setId("div_medios_verifica");
        div_medios_verifica.dividir1(pat_line);
        
        agregarComponente(div_medios_verifica);
    
    
    
    
}
        
    @Override
    public void insertar() {
        tab_medios_verifica.insertar(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        tab_medios_verifica.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_medios_verifica.eliminar();
    }

    public Tabla getTab_medios_verifica() {
        return tab_medios_verifica;
    }

    public void setTab_medios_verifica(Tabla tab_medios_verifica) {
        this.tab_medios_verifica = tab_medios_verifica;
    }



}

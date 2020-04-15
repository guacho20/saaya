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
public class GradoDiscapacidad extends Pantalla{
    private Tabla tab_grado_discapaci = new Tabla();
    public GradoDiscapacidad (){
        tab_grado_discapaci.setId ("tab_grado_discapaci");
        tab_grado_discapaci.setTabla ("yavirac_stror_grado_discapaci","ide_ystgrd",1);
        tab_grado_discapaci.dibujar();
        
        PanelTabla pa_grado_discapaci = new PanelTabla ();
        pa_grado_discapaci.setId ("pa_grado_discapaci");
        pa_grado_discapaci.setPanelTabla(tab_grado_discapaci);
        
        Division div_grado_discapaci = new Division();
        div_grado_discapaci.setId("div_grado_discapaci");
        div_grado_discapaci.dividir1(pa_grado_discapaci);
        
        agregarComponente(div_grado_discapaci);    
    }

    public Tabla getTab_grado_discapaci() {
        return tab_grado_discapaci;
    }

    public void setTab_grado_discapaci (Tabla tab_grado_discapaci) {
        this.tab_grado_discapaci = tab_grado_discapaci;
    }    
    
    @Override
    public void insertar() {
        tab_grado_discapaci.insertar();
    }

    @Override
    public void guardar() {
        tab_grado_discapaci.guardar();
        guardarPantalla(); 
    }

    @Override
    public void eliminar() {
         tab_grado_discapaci.eliminar();
    }


}

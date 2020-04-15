
package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraTipoGeneracionHora extends Pantalla{
    private Tabla tab_hora_generacion_hora = new Tabla(); 
    public HoraTipoGeneracionHora(){
        tab_hora_generacion_hora.setId("tab_hora_generacion_hora");   //identificador
        tab_hora_generacion_hora.setTabla("yavirac_hora_tipo_genera_hor", "ide_yhotgh", 1);
        tab_hora_generacion_hora.getColumna("ide_yhotgh").setNombreVisual("CÓDIGO PRINCIPAL");
        tab_hora_generacion_hora.getColumna("detalle_yhotgh").setNombreVisual("DETALLE");
        tab_hora_generacion_hora.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_generacion_hora = new PanelTabla();
        pat_hora_generacion_hora.setId("pat_hora_generacion_hora");
        pat_hora_generacion_hora.setPanelTabla(tab_hora_generacion_hora);
        Division div_hora_generacion_hora = new Division();
        div_hora_generacion_hora.setId("div_hora_generacion_hora");
        div_hora_generacion_hora.dividir1(pat_hora_generacion_hora);
        agregarComponente(div_hora_generacion_hora); 
    }
    @Override
    public void insertar() {
        tab_hora_generacion_hora.insertar();
    }

    @Override
    public void guardar() {
        tab_hora_generacion_hora.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_hora_generacion_hora.eliminar();
    }

    public Tabla getTab_hora_generacion_hora() {
        return tab_hora_generacion_hora;
    }

    public void setTab_hora_generacion_hora(Tabla tab_hora_generacion_hora) {
        this.tab_hora_generacion_hora = tab_hora_generacion_hora;
    }
    
}

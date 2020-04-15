
package paq_estructura;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
public class Jornada extends Pantalla {
    
    private Tabla tab_stror_jornada = new Tabla();
    
    public Jornada(){
    tab_stror_jornada.setId("tab_stror_jornada");   //identificador
    tab_stror_jornada.setTabla("yavirac_stror_jornada", "ide_ystjor", 1);
    tab_stror_jornada.getColumna("ide_ystjor").setNombreVisual("CÓDIGO");
    tab_stror_jornada.getColumna("descripcion_ystjor").setNombreVisual("DESCRIPCIÓN");
    tab_stror_jornada.getColumna("activo_ystjor").setNombreVisual("ACTIVO");
    tab_stror_jornada.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_stror_jornada = new PanelTabla();
        pat_stror_jornada.setId("pat_stror_jornada");
        pat_stror_jornada.setPanelTabla(tab_stror_jornada);
        Division div_stror_jornada = new Division();
        div_stror_jornada.setId("div_stror_jornada");
        div_stror_jornada.dividir1( pat_stror_jornada);
        agregarComponente(div_stror_jornada); 
        
    }

    @Override
    public void insertar() {
        tab_stror_jornada.insertar();
    }

    @Override
    public void guardar() {
        tab_stror_jornada.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_stror_jornada.eliminar();
    }

    public Tabla getTab_stror_jornada() {
        return tab_stror_jornada;
    }

    public void setTab_stror_jornada(Tabla tab_stror_jornada) {
        this.tab_stror_jornada = tab_stror_jornada;
    }

 
    
}

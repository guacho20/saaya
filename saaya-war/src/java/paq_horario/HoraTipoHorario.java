
package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

public class HoraTipoHorario extends Pantalla {
    private Tabla tab_hora_tipo_horario = new Tabla();
    
    public HoraTipoHorario(){
    tab_hora_tipo_horario.setId("tab_hora_tipo_horario");   //identificador
    tab_hora_tipo_horario.setTabla("yavirac_hora_tipo_horario", "ide_yhotih", 1);
    tab_hora_tipo_horario.getColumna("ide_yhotih").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_hora_tipo_horario.getColumna("descripcion_yhotih").setNombreVisual("DESCRIPCIÓN");
    tab_hora_tipo_horario.getColumna("activo_yhotih").setNombreVisual("ACTIVO");
    tab_hora_tipo_horario.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_tipo_horario = new PanelTabla();
        pat_hora_tipo_horario.setId("pat_hora_tipo_horario");
        pat_hora_tipo_horario.getMenuTabla().getItem_buscar().setRendered(false);
        pat_hora_tipo_horario.getMenuTabla().getItem_importar().setRendered(false);
        pat_hora_tipo_horario.getMenuTabla().getItem_excel().setRendered(false);
        pat_hora_tipo_horario.getMenuTabla().getItem_excel_filtro().setRendered(false);
        pat_hora_tipo_horario.getMenuTabla().getItem_formato().setRendered(false);
        pat_hora_tipo_horario.getMenuTabla().quitarSubmenuOtros();
        pat_hora_tipo_horario.setPanelTabla(tab_hora_tipo_horario);
        Division div_hora_tipo_horario = new Division();
        div_hora_tipo_horario.setId("div_hora_tipo_horario");
        div_hora_tipo_horario.dividir1(pat_hora_tipo_horario);
        agregarComponente(div_hora_tipo_horario); 
        
    }

    @Override
    public void insertar() {
        tab_hora_tipo_horario.insertar();
    }

    @Override
    public void guardar() {
        tab_hora_tipo_horario.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_hora_tipo_horario.eliminar();
    }

    public Tabla getTab_hora_tipo_horario() {
        return tab_hora_tipo_horario;
    }

    public void setTab_hora_tipo_horario(Tabla tab_hora_tipo_horario) {
        this.tab_hora_tipo_horario = tab_hora_tipo_horario;
    }
    
    
    
}

package paq_horario;

import framework.componentes.Division;
import framework.componentes.Grid;
import framework.componentes.Imagen;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.faces.event.AjaxBehaviorEvent;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraDia extends Pantalla {
    private Tabla tab_hora_dia = new Tabla();
    
    public HoraDia(){
        
    Grid gri1 = new Grid();
    gri1.setMensajeInfo("Porfavor ingrese el campo DESCRIPCIÓN en letra MAYÚSCULA");
    gri1.setStyle("width:33%;top:385px;");        
    tab_hora_dia.setId("tab_hora_dia");   //identificador
    tab_hora_dia.setTabla("yavirac_hora_dia", "ide_yhodia", 1);
    
    tab_hora_dia.getColumna("ide_yhodia").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_hora_dia.getColumna("descripcion_yhodia").setNombreVisual("DESCRIPCIÓN");
    tab_hora_dia.getColumna("descripcion_yhodia").setRequerida(true);
    tab_hora_dia.getColumna("descripcion_yhodia").setMetodoChange("validaMayusculas");
    tab_hora_dia.getColumna("abreviatura_yhodia").setNombreVisual("ABREVIATURA");
    tab_hora_dia.getColumna("abreviatura_yhodia").setRequerida(true);
    tab_hora_dia.getColumna("abreviatura_yhodia").setMetodoChange("validaMayusculas");
    tab_hora_dia.getColumna("orden_yhodia").setNombreVisual("Nº ORDEN");
    tab_hora_dia.getColumna("orden_yhodia").setRequerida(true);
    tab_hora_dia.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_dia = new PanelTabla();
        pat_hora_dia.setId("pat_hora_dia");
        pat_hora_dia.getMenuTabla().getItem_buscar().setRendered(false);
        pat_hora_dia.getMenuTabla().getItem_importar().setRendered(false);
        pat_hora_dia.getMenuTabla().getItem_excel().setRendered(false);
        pat_hora_dia.getMenuTabla().getItem_excel_filtro().setRendered(false);
        pat_hora_dia.getMenuTabla().getItem_formato().setRendered(false);
        pat_hora_dia.getMenuTabla().quitarSubmenuOtros();
        pat_hora_dia.setPanelTabla(tab_hora_dia);
        Division div_hora_dia = new Division();
        div_hora_dia.setId("div_hora_dia");
        div_hora_dia.dividir1(pat_hora_dia);
        
        Imagen modalidadIma = new Imagen();
        modalidadIma.setValue("imagenes/logoinstituto_pequeño.png");
        pat_hora_dia.setWidth("100%");
        pat_hora_dia.setHeader(modalidadIma);
        
        gri1.getChildren().add(div_hora_dia);
        agregarComponente(gri1); 
        
    }
    
    public void validaMayusculas(AjaxBehaviorEvent evt){
     tab_hora_dia.modificar(evt);
     validaFinal();
 }
 public void validaFinal(){
     String valor = tab_hora_dia.getValor("descripcion_yhodia").toUpperCase();
      tab_hora_dia.setValor("descripcion_yhodia", valor);
       utilitario.addUpdateTabla(tab_hora_dia, "descripcion_yhodia", "tab_hora_dia");
      utilitario.addUpdate("tab_hora_hora");
 }

    @Override
    public void insertar() {
        tab_hora_dia.insertar();
    }

    @Override
    public void guardar() {
        tab_hora_dia.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_hora_dia.eliminar();
       utilitario.agregarMensaje("Se ha eliminado correctamente", "");
    }
 
        


    public Tabla getTab_hora_dia() {
        return tab_hora_dia;
    }

    public void setTab_hora_dia(Tabla tab_hora_dia) {
        this.tab_hora_dia = tab_hora_dia;
    
        
        
        
                }
}

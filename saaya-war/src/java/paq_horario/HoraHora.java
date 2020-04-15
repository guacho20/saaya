package paq_horario;

import framework.componentes.Division;
import framework.componentes.Grid;
import framework.componentes.Imagen;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import java.util.Locale;
import javax.faces.event.AjaxBehaviorEvent;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraHora extends Pantalla{
  private Tabla tab_hora_hora = new Tabla();
  
    public HoraHora(){
     Grid gri1 = new Grid();
    gri1.setMensajeInfo("Porfavor ingrese el campo DESCRIPCIÓN en letra MAYÚSCULA");
    gri1.setStyle("width:40%;top:385px;"); 
    tab_hora_hora.setId("tab_hora_hora");   //identificador
    tab_hora_hora.setTabla("yavirac_hora_hora", "ide_yhohor", 1);
    tab_hora_hora.getColumna("ide_yhohor").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_hora_hora.getColumna("descripcion_yhohor").setNombreVisual("DESCRIPCIÓN");
    tab_hora_hora.getColumna("descripcion_yhohor").setRequerida(true);
    tab_hora_hora.getColumna("descripcion_yhohor").setMetodoChange("validaMayusculas");
    tab_hora_hora.getColumna("abreviatura_yhohor").setNombreVisual("ABREVIATURA");
     tab_hora_hora.getColumna("abreviatura_yhohor").setMetodoChange("validaMayusculas");
    tab_hora_hora.getColumna("orden_yhohor").setNombreVisual("Nº ORDEN");
    tab_hora_hora.getColumna("orden_yhohor").setRequerida(true);
    tab_hora_hora.getColumna("activo_yhohor").setNombreVisual("ACTIVO");
    tab_hora_hora.getColumna("activo_yhohor").setRequerida(true);
    tab_hora_hora.getColumna("activo_yhohor").setValorDefecto("TRUE");
    tab_hora_hora.dibujar();
        /*agregarComponente(tab_hora_hora);*/ 
        
        PanelTabla pat_hora_hora = new PanelTabla();
        pat_hora_hora.setId("pat_hora_hora");
        pat_hora_hora.getMenuTabla().getItem_buscar().setRendered(false);
        pat_hora_hora.getMenuTabla().getItem_importar().setRendered(false);
        pat_hora_hora.getMenuTabla().getItem_excel().setRendered(false);
        pat_hora_hora.getMenuTabla().getItem_excel_filtro().setRendered(false);
        pat_hora_hora.getMenuTabla().getItem_formato().setRendered(false);
        pat_hora_hora.getMenuTabla().quitarSubmenuOtros();
        pat_hora_hora.setPanelTabla(tab_hora_hora);
        Division div_hora_hora = new Division();
        div_hora_hora.setId("div_hora_hora");
        div_hora_hora.dividir1(pat_hora_hora);
        
        Imagen modalidadIma = new Imagen();
        modalidadIma.setValue("imagenes/logoinstituto_pequeño.png");
        pat_hora_hora.setWidth("100%");
        pat_hora_hora.setHeader(modalidadIma);
        
        gri1.getChildren().add(div_hora_hora);
        agregarComponente(gri1); 
        
        
        
                }
    
 public void validaMayusculas(AjaxBehaviorEvent evt){
     tab_hora_hora.modificar(evt);
     validaFinal();
 }
 public void validaFinal(){
     String valor = tab_hora_hora.getValor("descripcion_yhohor").toUpperCase();
      tab_hora_hora.setValor("descripcion_yhohor", valor);
       utilitario.addUpdateTabla(tab_hora_hora, "descripcion_yhohor", "tab_hora_hora");
      utilitario.addUpdate("tab_hora_hora");
 }
    
    @Override
    public void insertar() {
        tab_hora_hora.insertar();
    }

    @Override
    public void guardar() {
        
    tab_hora_hora.guardar();
    guardarPantalla();
        
    }

    @Override
    public void eliminar() {
     tab_hora_hora.eliminar();
     
    }

    public Tabla getTab_hora_hora() {
        return tab_hora_hora;
    }

    public void setTab_hora_hora(Tabla tab_hora_hora) {
        this.tab_hora_hora = tab_hora_hora;
    }

    
}

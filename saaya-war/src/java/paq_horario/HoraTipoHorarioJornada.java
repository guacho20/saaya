package paq_horario;

import framework.componentes.Division;
import framework.componentes.Grid;
import framework.componentes.Imagen;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.faces.event.AjaxBehaviorEvent;
import sistema.aplicacion.Pantalla;

public class HoraTipoHorarioJornada extends Pantalla {
   private Tabla tab_hora_tipo_horario_jornada = new Tabla();
    
    public HoraTipoHorarioJornada(){
    
    Grid gri1 = new Grid();
    gri1.setMensajeInfo("Porfavor ingrese el campo DESCRIPCIÓN en letra MAYÚSCULA");
    gri1.setStyle("width:33%;top:385px;");   
    tab_hora_tipo_horario_jornada.setId("tab_hora_tipo_horario_jornada");   //identificador
    tab_hora_tipo_horario_jornada.setTabla("yavirac_hora_tipo_horario_jorna", "ide_yhothj", 1);
    tab_hora_tipo_horario_jornada.getColumna("ide_yhothj").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_hora_tipo_horario_jornada.getColumna("descripcion_yhothj").setNombreVisual("DESCRIPCIÓN");
    tab_hora_tipo_horario_jornada.getColumna("descripcion_yhothj").setRequerida(true);
    tab_hora_tipo_horario_jornada.getColumna("descripcion_yhothj").setMetodoChange("validaMayusculas");
    tab_hora_tipo_horario_jornada.getColumna("activo_yhothj").setNombreVisual("ACTIVO");
    tab_hora_tipo_horario_jornada.getColumna("activo_yhothj").setRequerida(true);
    tab_hora_tipo_horario_jornada.getColumna("activo_yhothj").setValorDefecto("TRUE");
    tab_hora_tipo_horario_jornada.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_tipo_horario_jornada = new PanelTabla();
        pat_hora_tipo_horario_jornada.setId("pat_hora_tipo_horario_jornada");
        pat_hora_tipo_horario_jornada.getMenuTabla().getItem_buscar().setRendered(false);
        pat_hora_tipo_horario_jornada.getMenuTabla().getItem_importar().setRendered(false);
        pat_hora_tipo_horario_jornada.getMenuTabla().getItem_excel().setRendered(false);
        pat_hora_tipo_horario_jornada.getMenuTabla().getItem_excel_filtro().setRendered(false);
        pat_hora_tipo_horario_jornada.getMenuTabla().getItem_formato().setRendered(false);
        pat_hora_tipo_horario_jornada.getMenuTabla().quitarSubmenuOtros();
        pat_hora_tipo_horario_jornada.setPanelTabla(tab_hora_tipo_horario_jornada);
        Division div_hora_tipo_horario_jornada = new Division();
        div_hora_tipo_horario_jornada.setId("div_hora_tipo_horario_jornada");
        div_hora_tipo_horario_jornada.dividir1(pat_hora_tipo_horario_jornada);
        
        Imagen modalidadIma = new Imagen();
        modalidadIma.setValue("imagenes/logoinstituto_pequeño.png");
        pat_hora_tipo_horario_jornada.setWidth("100%");
        pat_hora_tipo_horario_jornada.setHeader(modalidadIma);
        
        gri1.getChildren().add(div_hora_tipo_horario_jornada);
        agregarComponente(gri1); 
        
    }
    public void validaMayusculas(AjaxBehaviorEvent evt){
     tab_hora_tipo_horario_jornada.modificar(evt);
     validaFinal();
 }
 public void validaFinal(){
     String valor = tab_hora_tipo_horario_jornada.getValor("descripcion_yhothj").toUpperCase();
      tab_hora_tipo_horario_jornada.setValor("descripcion_yhothj", valor);
       utilitario.addUpdateTabla(tab_hora_tipo_horario_jornada, "descripcion_yhothj", "tab_hora_tipo_horario_jornada");
      utilitario.addUpdate("tab_hora_tipo_horario_jornada");
 }

    @Override
    public void insertar() {
        tab_hora_tipo_horario_jornada.insertar();
    }

    @Override
    public void guardar() {
        tab_hora_tipo_horario_jornada.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_hora_tipo_horario_jornada.eliminar();
    }

    public Tabla getTab_hora_tipo_horario_jornada() {
        return tab_hora_tipo_horario_jornada;
    }

    public void setTab_hora_tipo_horario_jornada(Tabla tab_hora_tipo_horario_jornada) {
        this.tab_hora_tipo_horario_jornada = tab_hora_tipo_horario_jornada;
    }

 
}
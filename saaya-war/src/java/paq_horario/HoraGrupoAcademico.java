/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class HoraGrupoAcademico extends Pantalla{
    private Tabla tab_grupo_academico = new Tabla(); 
    
    public HoraGrupoAcademico(){
        
        Grid gri1 = new Grid();
        gri1.setMensajeInfo("Porfavor ingrese el campo DESCRIPCIÓN en letra MAYÚSCULA");
        gri1.setStyle("width:30%;top:385px;"); 
        tab_grupo_academico.setId("tab_grupo_academico");   //identificador
        tab_grupo_academico.setTabla("yavirac_hora_grupo_academic", "ide_yhogra", 1);
        tab_grupo_academico.getColumna("ide_yhogra").setNombreVisual("CÓDIGO PRINCIPAL");
        tab_grupo_academico.getColumna("detalle_yhogra").setNombreVisual("DESCRIPCIÓN");
        tab_grupo_academico.getColumna("detalle_yhogra").setRequerida(true);
        tab_grupo_academico.getColumna("detalle_yhogra").setMetodoChange("validaMayusculas");
        tab_grupo_academico.getColumna("abreviatura_yhogra").setNombreVisual("ABREVIATURA");
        tab_grupo_academico.getColumna("abreviatura_yhogra").setRequerida(true);
        tab_grupo_academico.getColumna("abreviatura_yhogra").setMetodoChange("validaMayusculas");
        tab_grupo_academico.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_grupo_academico = new PanelTabla();
        pat_grupo_academico.setId("pat_grupo_academico");
        pat_grupo_academico.getMenuTabla().getItem_buscar().setRendered(false);
        pat_grupo_academico.getMenuTabla().getItem_importar().setRendered(false);
        pat_grupo_academico.getMenuTabla().getItem_excel().setRendered(false);
        pat_grupo_academico.getMenuTabla().getItem_excel_filtro().setRendered(false);
        pat_grupo_academico.getMenuTabla().getItem_formato().setRendered(false);
        pat_grupo_academico.getMenuTabla().quitarSubmenuOtros();
        pat_grupo_academico.setPanelTabla(tab_grupo_academico);
        Division div_grupo_academico = new Division();
        div_grupo_academico.setId("div_grupo_academico");
        div_grupo_academico.dividir1(pat_grupo_academico);
        
        Imagen modalidadIma = new Imagen();
        modalidadIma.setValue("imagenes/logoinstituto_pequeño.png");
        pat_grupo_academico.setWidth("100%");
        pat_grupo_academico.setHeader(modalidadIma);
        
        gri1.getChildren().add(div_grupo_academico);
        agregarComponente(gri1); 
    }
    public void validaMayusculas(AjaxBehaviorEvent evt){
     tab_grupo_academico.modificar(evt);
     validaFinal();
 }
 public void validaFinal(){
     String valor = tab_grupo_academico.getValor("detalle_yhogra").toUpperCase();
      tab_grupo_academico.setValor("detalle_yhogra", valor);
       utilitario.addUpdateTabla(tab_grupo_academico, "detalle_yhogra", "tab_grupo_academico");
      utilitario.addUpdate("tab_grupo_academico");
 }
    
        @Override
    public void insertar() {
        tab_grupo_academico.insertar();
    }

    @Override
    public void guardar() {
        tab_grupo_academico.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_grupo_academico.eliminar();
    }

    public Tabla getTab_grupo_academico() {
        return tab_grupo_academico;
    }

    public void setTab_grupo_academico(Tabla tab_grupo_academico) {
        this.tab_grupo_academico = tab_grupo_academico;
    }
}

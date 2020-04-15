/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_tramites;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author User
 */
public class Documento extends Pantalla{ //desarrollo de la pantalla documento

    private Tabla tab_documento = new Tabla();
    
    public Documento (){
        tab_documento.setId("tab_documento");
        tab_documento.setTabla("yavirac_tra_documento", "ide_ytrdoc", 1);
        tab_documento.setHeader("Registro de Documento");
        tab_documento.getColumna("ide_ytrdoc").setNombreVisual("CODIGO");
        tab_documento.getColumna("nombre_doc_ytrdoc").setNombreVisual("NOMBRE DOCUMENTO");
        tab_documento.dibujar();
        
        PanelTabla pat_documento=new PanelTabla();
        pat_documento.setId("pat_documento");
        pat_documento.setPanelTabla(tab_documento);
        
        Division div_documento = new Division();
        div_documento.setId("div_documento");
        div_documento.dividir1(pat_documento);
        
        
        agregarComponente(div_documento);
}

    @Override
    public void insertar() {
        tab_documento.insertar();
    }

    @Override
    public void guardar() {
         tab_documento.guardar();
         guardarPantalla();
    }
  
    @Override
    public void eliminar() {
        tab_documento.eliminar();
    }

    public Tabla getTab_documento() {
        return tab_documento;
    }

    public void setTab_documento(Tabla tab_documento) {
        this.tab_documento = tab_documento;
    }
    
}

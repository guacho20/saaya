/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Martha
 */
public class TipoEmpresa extends Pantalla{
    
    private Tabla tab_tipo_empresa = new Tabla();
    
    public TipoEmpresa(){
        
        tab_tipo_empresa.setId("tab_tipo_empresa");
        tab_tipo_empresa.setTabla("yavirac_titu_tipo_empresa", "ide_ytitie", 3);
        tab_tipo_empresa.setHeader("TIPO EMPRESA");
        tab_tipo_empresa.dibujar();
            
            PanelTabla pat_tipo_empresa = new PanelTabla();
            pat_tipo_empresa.setId("pat_tipo_empresa");
            pat_tipo_empresa.setPanelTabla(tab_tipo_empresa);       
            Division div_tipo_empresa = new Division();
            div_tipo_empresa.setId("div_tipo_empresa");
            div_tipo_empresa.dividir1(pat_tipo_empresa);
            
            agregarComponente(div_tipo_empresa);
            
                 
    }
    @Override
    public void insertar() {
      tab_tipo_empresa.insertar();
    }

    @Override
    public void guardar() {
       tab_tipo_empresa.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_empresa.eliminar();
    }

    public Tabla getTab_tipo_empresa() {
        return tab_tipo_empresa;
    }

    public void setTab_tipo_empresa(Tabla tab_tipo_empresa) {
        this.tab_tipo_empresa = tab_tipo_empresa;
    }

    public Utilitario getUtilitario() {
        return utilitario;
    }

    public void setUtilitario(Utilitario utilitario) {
        this.utilitario = utilitario;
    }

    public Barra getBar_botones() {
        return bar_botones;
    }

    public void setBar_botones(Barra bar_botones) {
        this.bar_botones = bar_botones;
    }

    public Grupo getGru_pantalla() {
        return gru_pantalla;
    }

    public void setGru_pantalla(Grupo gru_pantalla) {
        this.gru_pantalla = gru_pantalla;
}
    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_matricula;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;


public class DocumentoRequerido extends Pantalla{
    
        private Tabla tab_documento_requerido = new Tabla();//instanciar tabla del framework
        
     public DocumentoRequerido() {//constructor
        tab_documento_requerido.setId("tab_documento_requerido");// todo objeto instanciado poner id 
        tab_documento_requerido.setTabla("yavirac_matri_docum_req_matri", "ide_ymadrm", 1);  // nombre de la base de datos ii la clave primaria
        tab_documento_requerido.setHeader("DOCUMENTOS ENTREGADOS");
        tab_documento_requerido.dibujar();//dibuja la tabla

        PanelTabla pa_documento_requerido = new PanelTabla();//intanciamos el panel del framework
        pa_documento_requerido.setId("pa_documento_requerido");//nombre id
        pa_documento_requerido.setPanelTabla(tab_documento_requerido);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_documento_entregado =new Division ();//instanciamos
        div_documento_entregado.setId("div_documento_requerido");//es un idientificador
        div_documento_entregado.dividir1(tab_documento_requerido);
        
        agregarComponente(div_documento_entregado);//agregar componente
    }
    @Override
    public void insertar() {
        tab_documento_requerido.insertar();
    }

    @Override
    public void guardar() {
        tab_documento_requerido.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_documento_requerido.eliminar();
    }

    public Tabla getTab_documento_requerido() {
        return tab_documento_requerido;
    }

    public void setTab_documento_requerido(Tabla tab_documento_requerido) {
        this.tab_documento_requerido = tab_documento_requerido;
    }

   
}

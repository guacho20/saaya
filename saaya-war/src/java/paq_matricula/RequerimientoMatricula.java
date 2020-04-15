/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_matricula;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

public class RequerimientoMatricula extends Pantalla{
    
        private Tabla tab_requerimiento_matricula = new Tabla();//instanciar tabla del framework
        
     public RequerimientoMatricula() {//constructor
       tab_requerimiento_matricula .setId("tab_requerimiento_matricula ");// todo objeto instanciado poner id 
        tab_requerimiento_matricula .setTabla(" yavirac_matri_requi_matric", "ide_ymarem ", 1);  // nombre de la base de datos ii la clave primaria
       tab_requerimiento_matricula.setHeader("REQUERIMIENTO MATRÍCULA");
        tab_requerimiento_matricula .dibujar();//dibuja la tabla

        PanelTabla pa_requerimiento_matricula = new PanelTabla();//intanciamos el panel del framework
        pa_requerimiento_matricula.setId("pa_requerimiento_matricula");//nombre id
        pa_requerimiento_matricula.setPanelTabla(tab_requerimiento_matricula);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_requerimiento_matricula =new Division ();//instanciamos
        div_requerimiento_matricula.setId("div_requerimiento_matricula");//es un idientificador
        div_requerimiento_matricula.dividir1(tab_requerimiento_matricula);
        
        agregarComponente(div_requerimiento_matricula);//agregar componente
    }
 @Override
    public void insertar() {
        tab_requerimiento_matricula.insertar();
    }

    @Override
    public void guardar() {
        tab_requerimiento_matricula.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_requerimiento_matricula.eliminar();
    }
    
    //generar geter and seter

    public Tabla getTab_requerimiento_matricula() {
        return tab_requerimiento_matricula;
    }

    public void setTab_requerimiento_matricula(Tabla tab_requerimiento_matricula) {
        this.tab_requerimiento_matricula= tab_requerimiento_matricula;
    }
    
}
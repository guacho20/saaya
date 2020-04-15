/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author usuario
 */
public class CarrreraAfines extends Pantalla {

    private Tabla tab_carrera_afines = new Tabla();

    public CarrreraAfines() {
        tab_carrera_afines.setId("tab_carrera_afines");
        tab_carrera_afines.setTabla("yavirac_titu_carrera_afines", "ide_yticaa", 1);  // nombre de la base de datos ii la clave primaria
        tab_carrera_afines.setHeader("CARRERA AFINES");
        tab_carrera_afines.getColumna("ide_yticaa").setNombreVisual("CÓDIGO");
        tab_carrera_afines.getColumna("ide_ytiemp").setNombreVisual("CÓDIGO EMPRESA");
        tab_carrera_afines.getColumna("observacion_yticaa").setNombreVisual("OBSERVACIÓN");
        tab_carrera_afines.getColumna("activo_yticaa").setNombreVisual("ACTIVO");
        tab_carrera_afines.dibujar();//dibuja la tabla

        PanelTabla pa_actividad_evaluacion = new PanelTabla();//intanciamos el panel del framework
        pa_actividad_evaluacion.setId("pa_carrera_afines");//nombre id
        pa_actividad_evaluacion.setPanelTabla(tab_carrera_afines);//agregar a nuestra tabla el panel

        //instanciar una division del framework
        Division div_actividad_evaluacion = new Division();//instanciamos
        div_actividad_evaluacion.setId("div_carrera_afines");//es un idientificador
        div_actividad_evaluacion.dividir1(tab_carrera_afines);

        agregarComponente(div_actividad_evaluacion);//agregar componente

    }

    @Override
    public void insertar() {
        tab_carrera_afines.insertar();
    }

    @Override
    public void guardar() {
       tab_carrera_afines.guardar();
        guardarPantalla();    
    }

    @Override
    public void eliminar() {
       tab_carrera_afines.eliminar();
    }

    public Tabla getTab_carrera_afines() {
        return tab_carrera_afines;
    }

    public void setTab_carrera_afines(Tabla tab_carrera_afines) {
        this.tab_carrera_afines = tab_carrera_afines;
    }

}

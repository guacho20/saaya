/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHON
 */
public class ActividadEvaluacion extends Pantalla {
    
    private Tabla tab_actividad_evaluacion = new Tabla();//instanciar tabla del framework
    private Tabla tab_actividad_formacion = new Tabla();
    
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    public ActividadEvaluacion() {//constructor
        tab_actividad_evaluacion.setId("tab_actividad_evaluacion");// identificador 
        tab_actividad_evaluacion.setTabla("yavirac_nota_actividad_evaluac", "ide_ynoace", 1);  // nombre de la base de datos ii la clave primaria
        tab_actividad_evaluacion.setHeader("ACTIVIDAD DE EVALUACIÓN");
        tab_actividad_evaluacion.agregarRelacion(tab_actividad_formacion);
        tab_actividad_evaluacion.getColumna("ide_ynoace").setNombreVisual("CODIGO");
        tab_actividad_evaluacion.getColumna("descripcion_ynoace").setNombreVisual("DESCRIPCION");
        tab_actividad_evaluacion.getColumna("activo_ynoace").setNombreVisual("ACTIVO");
        tab_actividad_evaluacion.getColumna("activo_ynoace").setValorDefecto("false");
        tab_actividad_evaluacion.dibujar();//dibuja la tabla

        PanelTabla pa_actividad_evaluacion = new PanelTabla();//intanciamos el panel del framework
        pa_actividad_evaluacion.setId("pa_actividad_evaluacion");//nombre id
        pa_actividad_evaluacion.setPanelTabla(tab_actividad_evaluacion);//agregar a nuestra tabla el panel

        //TABLA 2
        tab_actividad_formacion.setId("tab_actividad_formacion");
        tab_actividad_formacion.setTabla("yavirac_nota_actividad_tipo_for", "ide_ynoatf", 2);
        tab_actividad_formacion.getColumna("ide_ynoatf").setNombreVisual("CODIGO");
        tab_actividad_formacion.getColumna("ide_ysttfe").setNombreVisual("FORMACION EDUCATIVA");
        tab_actividad_formacion.getColumna("ide_ysttfe").setCombo(ser_estructura_organizacional.getTipoFormacionEducativa("true"));
        tab_actividad_formacion.dibujar();
        
        PanelTabla pa_actividad_formacion = new PanelTabla();
        pa_actividad_formacion.setId("pa_actividad_formacion");
        pa_actividad_formacion.setPanelTabla(tab_actividad_formacion);

        //instanciar una division del framework
        Division div_actividad_evaluacion = new Division();//instanciamos
        div_actividad_evaluacion.dividir2(pa_actividad_evaluacion, pa_actividad_formacion, "50%", "h");
        agregarComponente(div_actividad_evaluacion);//agregar componente
    }
    
    @Override
    public void insertar() {
        if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.insertar();
        } else if (tab_actividad_formacion.isFocus()) {
            tab_actividad_formacion.insertar();
        }
        
    }
    
    @Override
    public void guardar() {
        if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.guardar();
        } else if (tab_actividad_formacion.isFocus()) {
            tab_actividad_formacion.guardar();
        }
        guardarPantalla();
    }
    
    @Override
    public void eliminar() {
        if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.eliminar();
        } else if (tab_actividad_formacion.isFocus()) {
            tab_actividad_formacion.eliminar();
        }
    }

    //generar geter and seter
    public Tabla getTab_actividad_evaluacion() {
        return tab_actividad_evaluacion;
    }
    
    public void setTab_actividad_evaluacion(Tabla tab_actividad_evaluacion) {
        this.tab_actividad_evaluacion = tab_actividad_evaluacion;
    }
    
    public Tabla getTab_actividad_formacion() {
        return tab_actividad_formacion;
    }
    
    public void setTab_actividad_formacion(Tabla tab_actividad_formacion) {
        this.tab_actividad_formacion = tab_actividad_formacion;
    }
    
}

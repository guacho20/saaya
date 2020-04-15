/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_alumno;

import framework.componentes.Reporte;
import framework.componentes.SeleccionFormatoReporte;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Espacio;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Imagen;
import framework.componentes.Panel;
import java.util.HashMap;
import java.util.Map;
import persistencia.Conexion;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ricar
 */
public class ReporteAlumno extends Pantalla {

    private Reporte rep_reporte = new Reporte(); //Listado de reportes siempre se llama rep_reporte
    private SeleccionFormatoReporte sel_rep = new SeleccionFormatoReporte();//formato de salida del reporte
    private Map map_parametros = new HashMap();//parametros del reporte
    private Panel panOpcion = new Panel();

    public ReporteAlumno() {
        bar_botones.getBot_insertar().setRendered(false);
        bar_botones.getBot_guardar().setRendered(false);
        bar_botones.getBot_eliminar().setRendered(false);
        bar_botones.quitarBotonsNavegacion();

        rep_reporte.setId("rep_reporte");//ID
        rep_reporte.getBot_aceptar().setMetodo("aceptarReporte");//ejecuta el metodo de aceptar reporte
        agregarComponente(rep_reporte);//agrega el componente a la pantalla
        bar_botones.agregarReporte();//aparece el boton de reportes en la barra de botones
        sel_rep.setId("sel_rep");//ID
        agregarComponente(sel_rep);//agrego el componente a la pantalla

        Imagen ImaReportes = new Imagen();
        ImaReportes.setValue("imagenes/ModAsistencia/Asistencia.png");

        panOpcion.setId("pan_opcion");
        panOpcion.setTransient(true);
        panOpcion.setHeader("REPORTES POBLACION");
        panOpcion.setStyle("font-size:10px;color:black;text-align:center;");

        Grid grid_pant = new Grid();
        grid_pant.setColumns(1);
        grid_pant.setStyle("text-align:center;position:absolute;top:210px;left:535px;");
        Etiqueta eti_encab = new Etiqueta();
        grid_pant.getChildren().add(ImaReportes);
        Boton bot_imprimir = new Boton();
        bot_imprimir.setValue("Imprimir Reporte");
        bot_imprimir.setIcon("ui-icon-print");
        bot_imprimir.setMetodo("abrirListaReporte");
        bar_botones.agregarBoton(bot_imprimir);
        grid_pant.getChildren().add(bot_imprimir);
        agregarComponente(grid_pant);
        panOpcion.getChildren().add(grid_pant);
        agregarComponente(panOpcion);
    }

    public void abrirListaReporte() {
        rep_reporte.dibujar();
    }

    public void aceptarReporte() {
        // pregunta que reporte a aceptado el usuario
        if (rep_reporte.getReporteSelecionado().equals("Reporte Poblacion Provincia")) {
            abrirReporte();
        } else if (rep_reporte.getReporteSelecionado().equals("Reporte Poblacion Canton")) {
            abrirReporte();
        } else if (rep_reporte.getReporteSelecionado().equals("Reporte Poblacion Parroquia")) {
            abrirReporte();
        }
    }

    public void abrirReporte() {
        rep_reporte.cerrar();
        map_parametros.clear();
        map_parametros.put("nombre", utilitario.getVariable("NICK"));
        sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
        sel_rep.dibujar();
    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reporte getRep_reporte() {
        return rep_reporte;
    }

    public void setRep_reporte(Reporte rep_reporte) {
        this.rep_reporte = rep_reporte;
    }

    public SeleccionFormatoReporte getSel_rep() {
        return sel_rep;
    }

    public void setSel_reporte(SeleccionFormatoReporte sel_rep) {
        this.sel_rep = sel_rep;
    }

}

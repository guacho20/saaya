package paq_nota;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Reporte;
import framework.componentes.SeleccionFormatoReporte;
import framework.componentes.Tabla;
import java.util.HashMap;
import java.util.Map;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHON
 */

public class TipoEvaluacion extends Pantalla {

    private Tabla tab_tipo_evaluacion = new Tabla(); //intanciamos componente tabla
    //instanciar reporte
    private Reporte rep_reporte = new Reporte();
    private SeleccionFormatoReporte sel_rep = new SeleccionFormatoReporte();
    private Map map_parametros = new HashMap();

    public TipoEvaluacion() {
        tab_tipo_evaluacion.setId("tab_tipo_evaluacion");
        tab_tipo_evaluacion.setTabla("yavirac_nota_tipo_evaluacion", "ide_ynotie", 1);
        tab_tipo_evaluacion.setHeader("TIPO DE EVALUACIÓN");
        tab_tipo_evaluacion.getColumna("ide_ynotie").setNombreVisual("CODIGO");
        tab_tipo_evaluacion.getColumna("descripcion_ynotie").setNombreVisual("DESCRIPCION");
        tab_tipo_evaluacion.getColumna("activo_ynotie").setNombreVisual("ACTIVO");
        tab_tipo_evaluacion.getColumna("activo_ynotie").setValorDefecto("false");
        tab_tipo_evaluacion.dibujar();

        PanelTabla pat_tipo_evaluacion = new PanelTabla();
        pat_tipo_evaluacion.setId("pat_tipo_evaluacion");
        pat_tipo_evaluacion.setPanelTabla(tab_tipo_evaluacion);

        Division div_tipo_evaluacion = new Division();
        div_tipo_evaluacion.setId("div_tipo_evaluacion");
        div_tipo_evaluacion.dividir1(pat_tipo_evaluacion);

        agregarComponente(div_tipo_evaluacion);

        //constructor reporte
        rep_reporte.setId("rep_reporte");
        agregarComponente(rep_reporte);
        bar_botones.agregarReporte();

        sel_rep.setId("sel_rep");
        agregarComponente(sel_rep);
    }

    //metodo de reporte
    @Override
    public void abrirListaReportes() {
        rep_reporte.dibujar();
    }

    @Override
    public void aceptarReporte() {
        if (rep_reporte.getReporteSelecionado().equals("Reporteprueba")) {
            rep_reporte.cerrar();
            map_parametros.put("titulo", "Tipo Evaluación");
            sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
            sel_rep.dibujar(); 
        }
    }

    @Override
    public void insertar() {
        tab_tipo_evaluacion.insertar();

    }

    @Override
    public void guardar() {
        tab_tipo_evaluacion.guardar();
        guardarPantalla();

    }

    @Override
    public void eliminar() {
        tab_tipo_evaluacion.eliminar();//boton guardar devido a la tabla
    }

    public Tabla getTab_tipo_evaluacion() {
        return tab_tipo_evaluacion;
    }

    public void setTab_tipo_evaluacion(Tabla tab_tipo_evaluacion) {
        this.tab_tipo_evaluacion = tab_tipo_evaluacion;
    }

    //set and get reporte
    public Reporte getRep_reporte() {
        return rep_reporte;
    }

    public void setRep_reporte(Reporte rep_reporte) {
        this.rep_reporte = rep_reporte;
    }

    public SeleccionFormatoReporte getSel_rep() {
        return sel_rep;
    }

    public void setSel_rep(SeleccionFormatoReporte sel_rep) {
        this.sel_rep = sel_rep;
    }

    public Map getMap_parametros() {
        return map_parametros;
    }

    public void setMap_parametros(Map map_parametros) {
        this.map_parametros = map_parametros;
    }

}

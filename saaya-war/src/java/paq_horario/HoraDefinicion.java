
package paq_horario;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Grupo;
import framework.componentes.Imagen;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;

import paq_horarios.ejb.ServiciosHorarios;
import sistema.aplicacion.Pantalla;

import java.util.HashMap;
import java.util.Map;
import framework.componentes.Reporte;
import framework.componentes.SeleccionFormatoReporte;

public class HoraDefinicion extends Pantalla{
    private Tabla tab_hora_definicion = new Tabla();
    private Combo com_periodo_academico = new Combo();
    private SeleccionTabla set_tab_duplicar_def_hora = new SeleccionTabla();
    private Dialogo dia_periodo_activo = new Dialogo();
    private Combo com_dia_periodo_activo = new Combo();
    
    private Reporte rep_reporte = new Reporte(); //Listado de Reportes, siempre se llama rep_reporte
    private SeleccionFormatoReporte sel_rep = new SeleccionFormatoReporte(); //formato de salida del reporte
    private Map map_parametros = new HashMap();//Parametros del reporte


    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    
    
    public HoraDefinicion(){
        
        rep_reporte.setId("rep_reporte");
    agregarComponente(rep_reporte);
    bar_botones.agregarReporte();
        
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true, false"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
        bar_botones.agregarComponente(com_periodo_academico);
        
        Boton bot_replicar = new Boton();
        bot_replicar.setIcon("ui-icon-newwin");
        bot_replicar.setValue("DUPLICAR");
        bot_replicar.setTitle("DUPLICAR");
        bar_botones.agregarBoton(bot_replicar);    
        bot_replicar.setMetodo("replicarDefinicionHora");
        
        
    tab_hora_definicion.setId("tab_hora_definicion");   //identificador
    tab_hora_definicion.setTabla("yavirac_hora_definicion_hora", "ide_yhodeh", 1);
    tab_hora_definicion.setCondicion("ide_ystpea=-1");
    tab_hora_definicion.getColumna("ide_yhothj").setCombo(ser_horarios.getHorarios("true,false"));
   // tab_hora_definicion.getColumna("ide_yhotih").setCombo(ser_horarios.getTipoHorarios("true,false"));
    tab_hora_definicion.getColumna("ide_ystjor").setCombo(ser_estructura_organizacional.getJornada("true,false"));
    tab_hora_definicion.getColumna("ide_ystmod").setCombo(ser_estructura_organizacional.getModalidad("true,false"));
    tab_hora_definicion.getColumna("ide_ystpea").setVisible(false);
    tab_hora_definicion.getColumna("ide_yhodeh").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_hora_definicion.getColumna("ide_yhothj").setNombreVisual("TIPO DE HORARIO");
    tab_hora_definicion.getColumna("ide_ystjor").setNombreVisual("JORNADA");
    tab_hora_definicion.getColumna("ide_ystmod").setNombreVisual("MODALIDAD");
    tab_hora_definicion.getColumna("hora_inicio_yhodeh").setNombreVisual("HORA INICIO");
    tab_hora_definicion.getColumna("hora_final_yhodeh").setNombreVisual("HORA FINAL");
    tab_hora_definicion.getColumna("activo_yhodeh").setNombreVisual("ACTIVO");
    tab_hora_definicion.getColumna("activo_yhodeh").setValorDefecto("TRUE");
    tab_hora_definicion.dibujar();
    
        set_tab_duplicar_def_hora.setId("set_tab_duplicar_def_hora");
        set_tab_duplicar_def_hora.setTitle("ESCOJA LOS TIEMPOS DE JORNADA");
        set_tab_duplicar_def_hora.setSeleccionTabla(ser_horarios.getDescripcionHora("-1"), "ide_yhodeh");
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("ide_ystpea").setVisible(false);
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("ide_yhothj").setVisible(false);
       // set_tab_duplicar_def_hora.getTab_seleccion().getColumna("ide_yhotih").setVisible(false);
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("ide_ystjor").setVisible(false);
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("ide_ystmod").setVisible(false);
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("activo_yhodeh").setVisible(false);
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("descripcion_ystpea").setNombreVisual("Periodo Academico");
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("descripcion_ystmod").setNombreVisual("Modalidad");
       // set_tab_duplicar_def_hora.getTab_seleccion().getColumna("descripcion_yhotih").setNombreVisual("Tipo Horario");
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("descripcion_yhothj").setNombreVisual("Tipo Horario Jornada");
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("hora_inicio_yhodeh").setNombreVisual("Hora Inicial");
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("hora_final_yhodeh").setNombreVisual("Hora Final");
        
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("descripcion_ystpea").setOrden(1);
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("descripcion_ystmod").setOrden(2);
        //set_tab_duplicar_def_hora.getTab_seleccion().getColumna("descripcion_yhotih").setOrden(3);
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("descripcion_ystjor").setOrden(3);
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("descripcion_yhothj").setOrden(4);
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("hora_inicio_yhodeh").setOrden(5);
        set_tab_duplicar_def_hora.getTab_seleccion().getColumna("hora_final_yhodeh").setOrden(6);

        set_tab_duplicar_def_hora.setWidth("80%");
        set_tab_duplicar_def_hora.setHeight("70%");
        agregarComponente(set_tab_duplicar_def_hora);
        set_tab_duplicar_def_hora.getBot_aceptar().setMetodo("replicar");
    //sel_tab_duplicar_def_hora.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_definicion = new PanelTabla();
        pat_hora_definicion.setId("pat_hora_definicion");
        pat_hora_definicion.getMenuTabla().getItem_buscar().setRendered(false);
        pat_hora_definicion.getMenuTabla().getItem_importar().setRendered(false);
        pat_hora_definicion.getMenuTabla().getItem_excel().setRendered(false);
        pat_hora_definicion.getMenuTabla().getItem_excel_filtro().setRendered(false);
        pat_hora_definicion.getMenuTabla().getItem_formato().setRendered(false);
        pat_hora_definicion.getMenuTabla().quitarSubmenuOtros();
        pat_hora_definicion.setPanelTabla(tab_hora_definicion);
        Division div_hora_definicion = new Division();
        div_hora_definicion.setId("div_hora_tipo_horario_jornada");
        div_hora_definicion.dividir1(pat_hora_definicion);
        agregarComponente(div_hora_definicion); 
        
        Imagen modalidadIma = new Imagen();
        modalidadIma.setValue("imagenes/logoinstituto_pequeño.png");
        pat_hora_definicion.setWidth("100%");
        pat_hora_definicion.setHeader(modalidadIma);
        
        // creo dialogo para crear periodo activo
        dia_periodo_activo.setId("dia_periodo_activo");
        dia_periodo_activo.setTitle("Selección de Periodo Académico");
        dia_periodo_activo.setWidth("40%");
        dia_periodo_activo.setHeight("18%");
        dia_periodo_activo.getBot_aceptar().setMetodo("aceptarPeriodo");
        dia_periodo_activo.setResizable(false);
        
        com_dia_periodo_activo.setId("com_dia_periodo_activo");
        com_dia_periodo_activo.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        
        Grupo gru_cuerpo = new Grupo();
        Etiqueta eti_mensaje = new Etiqueta();
        eti_mensaje.setValue("Seleccione el Periodo Activo");
        eti_mensaje.setStyle("font-size: 13px;border: none;text-shadow: 0px 2px 3px #ccc;background: none;");
        
        gru_cuerpo.getChildren().add(eti_mensaje);
        gru_cuerpo.getChildren().add(com_dia_periodo_activo);

        dia_periodo_activo.setDialogo(gru_cuerpo);
        agregarComponente(dia_periodo_activo);
        
    sel_rep.setId("sel_rep");
    agregarComponente(sel_rep);
    }

    @Override
public void abrirListaReportes() {
// TODO Auto-generated method stub
rep_reporte.dibujar();
}
@Override
public void aceptarReporte() {
if (rep_reporte.getReporteSelecionado().equals("ReporteDefinicionHora")){
    abrirperfilesSistemas();
}
}
private void abrirperfilesSistemas(){
    rep_reporte.cerrar();
    map_parametros.clear();
    map_parametros.put("titulo", "Definicion Hora");
    sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
    sel_rep.dibujar();
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

    public void setSel_rep(SeleccionFormatoReporte sel_rep) {
        this.sel_rep = sel_rep;
    }

    public Map getMap_parametros() {
        return map_parametros;
    }

    public void setMap_parametros(Map map_parametros) {
        this.map_parametros = map_parametros;
    }
    public void replicarDefinicionHora(){
        if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea replicar");
        return;
        }
        else {
        
            set_tab_duplicar_def_hora.getTab_seleccion().setSql(ser_horarios.getDescripcionHora(com_periodo_academico.getValue().toString()));
            set_tab_duplicar_def_hora.getTab_seleccion().ejecutarSql();
            set_tab_duplicar_def_hora.dibujar();
                }
    
}
    public void filtroComboPeriodoAcademnico(){
        
        tab_hora_definicion.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_hora_definicion.ejecutarSql();
        utilitario.addUpdate("tab_hora_definicion");
        
     
    }
    
    public void aceptarPeriodo(){
        
        if(com_dia_periodo_activo.getValue() == null){
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea replicar");
        return;
        }
        else{
            int numero_filas = set_tab_duplicar_def_hora.getNumeroSeleccionados();
            System.out.println("filas eleccionadas "+numero_filas);
            String valor1="";
            for (int i=0;i<numero_filas;i++){
                
                TablaGenerica codigo_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_hora_definicion_hora", "ide_yhodeh"));
                String primario = codigo_maximo.getValor("maximo");
                String insertar= ser_horarios.insertReplicaDefinicionHora(primario, com_dia_periodo_activo.getValue().toString(),set_tab_duplicar_def_hora.getTab_seleccion().getValor(i, "ide_yhothj"),set_tab_duplicar_def_hora.getTab_seleccion().getValor(i, "ide_ystjor"),set_tab_duplicar_def_hora.getTab_seleccion().getValor(i, "ide_ystmod"),set_tab_duplicar_def_hora.getTab_seleccion().getValor(i, "hora_inicio_yhodeh"),set_tab_duplicar_def_hora.getTab_seleccion().getValor(i, "hora_final_yhodeh"),set_tab_duplicar_def_hora.getTab_seleccion().getValor(i, "activo_yhodeh"));
                //valor1= set_tab_duplicar_def_hora.getTab_seleccion().getValor(i, "ide_yhodeh");
                //System.out.println("xxxx "+valor1);
                utilitario.getConexion().ejecutarSql(insertar);
               
            }
                 dia_periodo_activo.cerrar();
                 set_tab_duplicar_def_hora.cerrar();
                 filtroComboPeriodoAcademnico();
                 utilitario.agregarMensaje("Duplicado exitosamente","");
        }
    }
    
    
    public void replicar(){
        
        if(set_tab_duplicar_def_hora.getNumeroSeleccionados() > 0){
                  dia_periodo_activo.dibujar();
        }
        else {
          utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Academico");
            return;
        }
    }
    @Override
    public void insertar() {
//        System.out.println("vaklor del combo"+com_periodo_academico.getValue().toString());
        if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Academico");
            return;
        }
        else {
        
       tab_hora_definicion.insertar();
       tab_hora_definicion.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
       utilitario.addUpdateTabla(tab_hora_definicion, "ide_ystpea", "");
       }
    }

    @Override
    public void guardar() {
        tab_hora_definicion.guardar();
        guardarPantalla();
    }
    @Override
    public void eliminar() {
        tab_hora_definicion.eliminar();
    }

    public Tabla getTab_hora_definicion() {
        return tab_hora_definicion;
    }

    public void setTab_hora_definicion(Tabla tab_hora_definicion) {
        this.tab_hora_definicion = tab_hora_definicion;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public SeleccionTabla getSet_tab_duplicar_def_hora() {
        return set_tab_duplicar_def_hora;
    }

    public void setSet_tab_duplicar_def_hora(SeleccionTabla set_tab_duplicar_def_hora) {
        this.set_tab_duplicar_def_hora = set_tab_duplicar_def_hora;
    }

    public Dialogo getDia_periodo_activo() {
        return dia_periodo_activo;
    }

    public void setDia_periodo_activo(Dialogo dia_periodo_activo) {
        this.dia_periodo_activo = dia_periodo_activo;
    }

    public Combo getCom_dia_periodo_activo() {
        return com_dia_periodo_activo;
    }

    public void setCom_dia_periodo_activo(Combo com_dia_periodo_activo) {
        this.com_dia_periodo_activo = com_dia_periodo_activo;
    }

}

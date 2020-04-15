/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Barra;
import framework.componentes.Boton;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_alumno.ejb.ServicioAlumno;
import paq_personal.ejb.ServicioPersonal;
import paq_personal.ejb.ServicioPersonal;
import paq_titulacion.ejb.ServicioTitulacion;
import paq_horarios.ejb.ServiciosHorarios;
import sistema.aplicacion.Pantalla;
import framework.componentes.PanelTabla;
import sistema.aplicacion.Utilitario;
import framework.componentes.Tabulador;
import framework.componentes.Combo;
import framework.componentes.Dialogo;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Reporte;
import framework.componentes.SeleccionFormatoReporte;
import framework.componentes.VisualizarPDF;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martha
 */
public class LibretaPractica extends Pantalla{
   private  Tabla tab_libreta_practica = new Tabla();
   private  Tabla tab_anexo_libreta = new Tabla();
   private  Tabla tab_horario_practica = new Tabla();
   private Combo com_periodo_academico = new Combo();
   private Dialogo dia_foto = new Dialogo();
   private Tabla tab_foto = new Tabla();
   private Reporte rep_reporte=new Reporte();
   private SeleccionFormatoReporte sel_rep=new SeleccionFormatoReporte();
   private VisualizarPDF vipdf_comprobante = new VisualizarPDF();

   
    @EJB
    private final ServicioEstructuraOrganizacional ser_libreta = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioPersonal ser_responsable = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
           @EJB
    private final ServicioTitulacion ser_empresa = (ServicioTitulacion) utilitario.instanciarEJB(ServicioTitulacion.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_periodoacademico = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServiciosHorarios ser_horario = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    
    
    
    public LibretaPractica(){
        
             
        vipdf_comprobante.setId("vipdf_comprobante");
        vipdf_comprobante.setTitle("REPORTES LIBRETA DE PRACTICAS");
        agregarComponente(vipdf_comprobante);
        
        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_periodoacademico.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");
        
        
        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADEMICO"));
        bar_botones.agregarComponente(com_periodo_academico);

       Boton bot_clean = new Boton();
       bot_clean.setIcon("ui-icon-cancel");
            bot_clean.setTitle("Limpiar");
            bot_clean.setMetodo("limpiar");
            bar_botones.agregarComponente(bot_clean);        
        
        Boton bot_imprimir = new Boton();
        bot_imprimir.setValue("Imprimir Libreta Practicas");
        bot_imprimir.setIcon("ui-icon-print");
        bot_imprimir.setMetodo("abrirListaReportes");
        bar_botones.agregarBoton(bot_imprimir);    
            
        tab_libreta_practica.setId("tab_libreta_practica");
        tab_libreta_practica.setTabla("yavirac_titu_libreta_practica", "ide_ytilpr",1);
        tab_libreta_practica.setCondicion("ide_ytilpr=-1");
        tab_libreta_practica.getColumna("ide_ystmen").setCombo(ser_libreta.getMension());
        tab_libreta_practica.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
        tab_libreta_practica.getColumna("ide_ypedpe").setCombo(ser_responsable.getDatopersonal("true,false"));
        tab_libreta_practica.getColumna("yav_ide_ypedpe").setCombo(ser_responsable.getDatopersonal("true,false"));
        tab_libreta_practica.getColumna("yav_ide_ypedpe2").setCombo(ser_responsable.getDatopersonal("true,false"));
        tab_libreta_practica.getColumna("ide_ytiemp").setCombo(ser_empresa.getDatoEmpresa());
        tab_libreta_practica.getColumna("ide_ystpea").setCombo(ser_periodoacademico.getPeriodoAcademico("true,false"));
        tab_libreta_practica.getColumna("ide_ytiemp").setAutoCompletar();
        tab_libreta_practica.getColumna("ide_yaldap").setAutoCompletar();
        tab_libreta_practica.getColumna("ide_ypedpe").setAutoCompletar();
        tab_libreta_practica.setHeader("REGISTRO DE LA LIBRETA DE PRACTICA");
        tab_libreta_practica.getColumna("ide_ytilpr"). setNombreVisual("CÓDIGO");
        tab_libreta_practica.getColumna("ide_yaldap"). setNombreVisual("ALUMNO");
        tab_libreta_practica.getColumna("ide_ystmen"). setNombreVisual("MENSION");
        tab_libreta_practica.getColumna("ide_ypedpe"). setNombreVisual("RESPONSABLE REGISTRO");
        tab_libreta_practica.getColumna("yav_ide_ypedpe"). setNombreVisual("REVISOR 1");
        tab_libreta_practica.getColumna("yav_ide_ypedpe2"). setNombreVisual("REVISOR 2");
        tab_libreta_practica.getColumna("ide_ytiemp"). setNombreVisual("EMPRESA");
        tab_libreta_practica.getColumna("fecha_desde_ytilpr"). setNombreVisual("FECHA DESDE");
        tab_libreta_practica.getColumna("fecha_hasta_ytilpr"). setNombreVisual("FECHA HASTA");
        tab_libreta_practica.getColumna("resumen_trabajo_ytilpr"). setNombreVisual("RESUMEN DEL TRABAJO");
        tab_libreta_practica.getColumna("observaciones_ytilpr"). setNombreVisual("OBSERVACION");
        tab_libreta_practica.getColumna("ide_ystpea ").setVisible(false);//hace visible al campo periodo academico//
        tab_libreta_practica.agregarRelacion(tab_anexo_libreta);
        tab_libreta_practica.agregarRelacion(tab_horario_practica);
        //tab_libreta_practica.getColumna("ide_ystmen").setEstilo("width:20");
        tab_libreta_practica.getColumna("ide_ystmen").setLongitud(500);        
        tab_libreta_practica.getColumna("yav_ide_ypedpe").setLongitud(10);

            
        tab_libreta_practica.setTipoFormulario(true);//para que se haga un formulario
        tab_libreta_practica.getGrid().setColumns(4); //numero de columnas del formulario
        tab_libreta_practica.dibujar();
        
         PanelTabla pat_libreta_practica = new PanelTabla();
         pat_libreta_practica.setId("pat_libreta_practica");
         pat_libreta_practica.setPanelTabla(tab_libreta_practica);
        
        Tabulador tab_tabulador_in = new Tabulador();
        tab_tabulador_in.setId("tab_tabulador_in");
               
        tab_horario_practica.setId("tab_horario_practica");
        tab_horario_practica.setIdCompleto("tab_tabulador_in:tab_horario_practica");
        tab_horario_practica.setTabla("yavirac_titu_horario_practica", "ide_ytihpr",3);
        tab_horario_practica.setHeader("HORARIO DE PRACTICAS");
        tab_horario_practica.getColumna("ide_yhodia").setCombo(ser_horario.getDia());
        tab_horario_practica.getColumna("ide_ytihpr"). setNombreVisual("CÓDIGO");
        tab_horario_practica.getColumna("ide_ytilpr "). setNombreVisual("CÓDIGO SOLICITADO");
        tab_horario_practica.getColumna("hora_inicio_ytihpr "). setNombreVisual("HORA INICIO");
        tab_horario_practica.getColumna("hora_fin_ytihpr "). setNombreVisual("HORA FIN");
        tab_horario_practica.getColumna("numero_horas_ytihpr "). setNombreVisual("NUMERO DE HORAS");
        tab_horario_practica.dibujar();
        
        PanelTabla pat_horario_practica = new PanelTabla();
        pat_horario_practica.setId("pat_horario_practica");
        pat_horario_practica.setPanelTabla(tab_horario_practica);
        
        tab_anexo_libreta.setId("tab_anexo_libreta");
        tab_anexo_libreta.setIdCompleto("tab_tabulador_in:tab_anexo_libreta");
        tab_anexo_libreta.setTabla("yavirac_titu_anexo_libreta", "ide_ytiali",2);
        tab_anexo_libreta.setHeader("ANEXO DE LA LIBRETA");
        tab_anexo_libreta.getColumna("ide_ytiali").setNombreVisual("CÓDIGO");
        tab_anexo_libreta.getColumna("fecha_ytiali").setNombreVisual("FECHA");
        tab_anexo_libreta.getColumna("archivo_ytiali").setNombreVisual("ARCHIVO ANEXO");
        tab_anexo_libreta.getColumna("archivo_ytiali").setUpload();//subir los archivos al sistema//
        tab_anexo_libreta.getColumna("observaciones_ytiali"). setNombreVisual("OBSERVACIONES");
        tab_anexo_libreta.setTipoFormulario(true);
        tab_anexo_libreta.setLectura(true);
        tab_anexo_libreta.getGrid().setColumns(4);
        tab_anexo_libreta.dibujar();
        
        dia_foto.setId("dia_foto");
        dia_foto.setTitle("AGREGAR FOTO");
        dia_foto.setWidth("45%");
        dia_foto.setHeight("50%");
        agregarComponente(dia_foto);
        
        Boton bot_agregar = new Boton();
        bot_agregar.setMetodo("abrirDialogoAgregarFoto");
        bot_agregar.setValue("Agregar Foto");
        bot_agregar.setIcon("ui-icon-plus");
        
         Boton bot_eliminar = new Boton();
        bot_eliminar.setMetodo("eliminarFoto");
        bot_eliminar.setValue("Eliminar Foto");
        bot_eliminar.setIcon("ui-icon-trash");

        Grid gri1 = new Grid();
        gri1.setColumns(3);
        gri1.getChildren().add(bot_agregar);
        gri1.getChildren().add(bot_eliminar);
        
        PanelTabla pat_anexo_libreta = new PanelTabla();
        pat_anexo_libreta.setId("pat_anexo_libreta");
        pat_anexo_libreta.setHeader(gri1);
        pat_anexo_libreta.setPanelTabla(tab_anexo_libreta);
        
       tab_foto.setId("tab_foto");
        tab_foto.setTabla("yavirac_titu_anexo_libreta", "ide_ytiali",2);
        tab_foto.setHeader("ANEXO DE LA LIBRETA");
        tab_foto.setCondicion("ide_ytilpr=-1");
        tab_foto.getColumna("ide_ytiali").setNombreVisual("CÓDIGO");
        tab_foto.getColumna("fecha_ytiali").setNombreVisual("FECHA");
        tab_foto.getColumna("archivo_ytiali").setNombreVisual("ARCHIVO ANEXO");
        tab_foto.getColumna("archivo_ytiali").setUpload();//subir los archivos al sistema//
        tab_foto.getColumna("observaciones_ytiali"). setNombreVisual("OBSERVACIONES");
        tab_foto.getColumna("ide_ytilpr").setVisible(false);
        tab_foto.setTipoFormulario(true);
        tab_foto.setMostrarNumeroRegistros(false);

        tab_foto.dibujar();
        PanelTabla patpanel = new PanelTabla();
        patpanel.setPanelTabla(tab_foto);
        patpanel.setStyle("overflow: hidden;");
        patpanel.getMenuTabla().setRendered(false);
        Grid gf = new Grid();
        gf.getChildren().add(patpanel);
        gf.setStyle("width:" + (dia_foto.getAnchoPanel() - 5) + "px;height:" + (dia_foto.getAltoPanel() - 10) + "px;overflow: hidden;display: block;");
        dia_foto.setDialogo(gf);
        dia_foto.getBot_aceptar().setMetodo("aceptarGuardarFoto");
        
        
              
        

        //*******************************AGREGA PESTAÑANAS*********************************************//
            tab_tabulador_in.agregarTab("HORARIO DE PRACTICAS", pat_horario_practica);
            tab_tabulador_in.agregarTab("ANEXO DE LIBRETAS", pat_anexo_libreta);

            //instanciar una division del framework
            Division div_libreta_practica = new Division();//instanciamos
            div_libreta_practica.setId("div_libreta_practica");//es un idientificador
            div_libreta_practica.dividir2(pat_libreta_practica, tab_tabulador_in, "50%", "H");
            agregarComponente(div_libreta_practica);
    }
    
    public void abrirListaReportes() {
// TODO Auto-generated method stub
        if(com_periodo_academico.getValue()== null){
        utilitario.agregarMensajeError("ERROR","Seleccione el Periodo Academico");
        return;
        }
        else {
            generarPDF();
        }
        
}

public void generarPDF() {
    
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pide_libreta_practica", Integer.parseInt(tab_libreta_practica.getValor("ide_ytilpr")));
                System.out.println("parametris "+map_parametros);
                 vipdf_comprobante.setVisualizarPDF("rep_titulacion/rep_libreta_practica.jasper", map_parametros);
                vipdf_comprobante.dibujar();
                utilitario.addUpdate("vipdf_comprobante");
       
    
    
    
}

    
    public void aceptarGuardarFoto() {
        if (tab_foto.guardar()) {
            if (guardarPantalla().isEmpty()) {
                dia_foto.cerrar();
                tab_anexo_libreta.actualizar();
            }
        }
    }
    public void abrirDialogoAgregarFoto() {
        dia_foto.dibujar();
        tab_foto.setCondicion("ide_ytilpr=-1");
        tab_foto.insertar();
        tab_foto.setValor("ide_ytilpr", tab_libreta_practica.getValor("ide_ytilpr"));
    }
    public void eliminarFoto() {
        tab_anexo_libreta.setLectura(false);
        if (tab_anexo_libreta.eliminar()) {
            guardarPantalla();
        }
    }
        public void filtroComboPeriodoAcademico(){
         tab_libreta_practica.setCondicion("ide_ystpea="+com_periodo_academico.getValue());
         tab_libreta_practica.ejecutarSql();
         tab_anexo_libreta.ejecutarValorForanea(tab_libreta_practica.getValorSeleccionado());
         tab_horario_practica.ejecutarValorForanea(tab_libreta_practica.getValorSeleccionado());
         utilitario.addUpdate("tab_libreta_practica,tab_anexo_libreta,tab_horario_practica");
        }
        public void limpiar(){
            com_periodo_academico.limpiar();
            tab_libreta_practica.limpiar();
        }
    @Override
    public void insertar() {
        if(com_periodo_academico.getValue()== null){
        utilitario.agregarMensajeError("ERROR","Seleccione el Periodo Academico");
        return;
        }
        else{
        if(tab_libreta_practica.isFocus()){
            tab_libreta_practica.insertar();
            tab_libreta_practica.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
        }
        else if (tab_anexo_libreta.isFocus()){
            tab_anexo_libreta.insertar();
        }
        else if (tab_horario_practica.isFocus()){
            tab_horario_practica.insertar();
        }
    }

    }
    @Override
    public void guardar() {
        if(tab_libreta_practica.guardar()){
            if(tab_anexo_libreta.guardar()){
                if(tab_horario_practica.guardar())
                    guardarPantalla();
            }
        }
        
        
    }

    @Override
    public void eliminar() {
        utilitario.getTablaisFocus().eliminar();
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

    public Tabla getTab_libreta_practica() {
        return tab_libreta_practica;
    }

    public void setTab_libreta_practica(Tabla tab_libreta_practica) {
        this.tab_libreta_practica = tab_libreta_practica;
    }

    public Tabla getTab_anexo_libreta() {
        return tab_anexo_libreta;
    }
    

    public void setTab_anexo_libreta(Tabla tab_anexo_libreta) {
        this.tab_anexo_libreta = tab_anexo_libreta;
    }

    public Tabla getTab_horario_practica() {
        return tab_horario_practica;
    }

    public void setTab_horario_practica(Tabla tab_horario_practica) {
        this.tab_horario_practica = tab_horario_practica;
    }

    public Dialogo getDia_foto() {
        return dia_foto;
    }

    public void setDia_foto(Dialogo dia_foto) {
        this.dia_foto = dia_foto;
    }

    public Tabla getTab_foto() {
        return tab_foto;
    }

    public void setTab_foto(Tabla tab_foto) {
        this.tab_foto = tab_foto;
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

    public VisualizarPDF getVipdf_comprobante() {
        return vipdf_comprobante;
    }

    public void setVipdf_comprobante(VisualizarPDF vipdf_comprobante) {
        this.vipdf_comprobante = vipdf_comprobante;
    }

    
    
}

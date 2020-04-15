/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_tramites;

import framework.aplicacion.TablaGenerica;
import framework.componentes.AreaTexto;
import framework.componentes.Barra; //Estas lineas son para importar objetos que se utilizaran en el desarrollo
import framework.componentes.Boton;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import framework.componentes.VisualizarPDF;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;
import paq_tramites.ejb.ServicioTramite;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;


/**
 *
 * @author Personal
 */
public class Seguimiento extends Pantalla {

    private Tabla tab_ingreso = new Tabla(); //Esta linea se creo las variables que se ulizaran
    private Tabla tab_anexo = new Tabla();
    private Tabla tab_asignacion = new Tabla();
    private Tabla tab_seguimiento= new Tabla();
    private SeleccionTabla sel_registra_alumno = new SeleccionTabla(); //se crea varialble para extraer datos de otra tabla
    private SeleccionTabla sel_asigna_responsable = new SeleccionTabla();
         private VisualizarPDF vipdf_proforma = new VisualizarPDF();
    private Dialogo dia_anexos= new Dialogo();
    private Dialogo dia_reasigna= new Dialogo();
    private Dialogo dia_conlcusion= new Dialogo();
    private AreaTexto are_conclusion = new AreaTexto();
    private AreaTexto are_reasigna = new AreaTexto();
    //Creacion de servicios para tulizar como variables para extraer informacion
    @EJB
    private final ServicioTramite ser_tramite = (ServicioTramite) utilitario.instanciarEJB(ServicioTramite.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);

    //Desarrollo de la pantalla Ingreso
    public Seguimiento() {
        bar_botones.getBot_insertar().setRendered(false);
        if (tienePerfilSecretaria()) {

            //boton para asiganar trammites
            Boton bot_concluir = new Boton();
            bot_concluir.setValue("Conluir Tramite");
            bot_concluir.setIcon("ui-icon-note");
            bot_concluir.setMetodo("registrarConclusion");
            bar_botones.agregarBoton(bot_concluir);

            //boton imprimir
            Boton bot_reasignar = new Boton();
            bot_reasignar.setValue("Reasignar Tramite");
            bot_reasignar.setIcon("ui-icon-note");
            bot_reasignar.setMetodo("registrarReasignacion");
            bar_botones.agregarBoton(bot_reasignar);
            
            // Desarrollo del entorno de la pantalla ingreso
            tab_ingreso.setId("tab_ingreso");
            tab_ingreso.setTabla("yavirac_tra_ingreso", "ide_ytring", 1);
            tab_ingreso.setCondicion("estado_ytring in ('ASIGNADO','REASIGNADO') and ide_ytring in(select ide_ytring from yavirac_tra_asignacion  where ide_ypedpe="+ide_docente+" and estado_ytrasi in ('ASIGNADO','REASIGNADO'))");
            tab_ingreso.getColumna("ide_ytring").setVisible(false);
            tab_ingreso.setCampoOrden("ide_ytring desc");
            tab_ingreso.getColumna("numero_sec_ytring").setOrden(0); // Asignacion de orden de los campos
            tab_ingreso.getColumna("numero_sec_ytring").setNombreVisual("NRO DE TRÁMITE"); 
            tab_ingreso.getColumna("ide_ytrtid").setNombreVisual("TIPO DE DOCUMENTO");
            tab_ingreso.getColumna("ide_ytrtid").setOrden(12);
            tab_ingreso.getColumna("ide_ytrtid").setLectura(true);
            tab_ingreso.getColumna("asunto_ytring").setNombreVisual("ASUNTO"); //Asignacion de nombre que se mostrara en pantalla
            tab_ingreso.getColumna("asunto_ytring").setOrden(15);
            tab_ingreso.getColumna("fecha_entrega_ytring").setNombreVisual("FECHA DE ENTREGA");
            tab_ingreso.getColumna("fecha_entrega_ytring").setOrden(8);
            tab_ingreso.getColumna("cedula_ytring").setNombreVisual("CÉDULA DE IDENTIDAD");
            tab_ingreso.getColumna("cedula_ytring").setEtiqueta();
            tab_ingreso.getColumna("fecha_conclusion_ytring").setNombreVisual("FECHA DE CONCLUSIÓN");
            tab_ingreso.getColumna("fecha_conclusion_ytring").setOrden(17);
            tab_ingreso.getColumna("fecha_documento_ytring").setNombreVisual("FECHA DE DOCUMENTO");
            tab_ingreso.getColumna("fecha_documento_ytring").setOrden(2);
            tab_ingreso.getColumna("respuesta_ytring").setNombreVisual("RESPUESTA");
            tab_ingreso.getColumna("respuesta_ytring").setOrden(18);
            tab_ingreso.getColumna("hora_ytring").setNombreVisual("HORA");
            tab_ingreso.getColumna("hora_ytring").setOrden(19);
            tab_ingreso.getColumna("observaciones_ytring").setNombreVisual("OBSERVACIONES");
            tab_ingreso.getColumna("observaciones_ytring").setOrden(16);
            tab_ingreso.getColumna("ide_ytrtie").setNombreVisual("TIPO DE ENTIDAD");
            tab_ingreso.getColumna("ide_ytrtie").setOrden(1);
            tab_ingreso.getColumna("estado_ytring").setNombreVisual("ESTADO");
            tab_ingreso.getColumna("estado_ytring").setOrden(6);
            tab_ingreso.getColumna("procedencia_ytring").setNombreVisual("PROCEDENCIA");
            tab_ingreso.getColumna("procedencia_ytring").setEtiqueta();
            tab_ingreso.getColumna("ide_yaldap").setVisible(false);
            tab_ingreso.getColumna("ide_ypedpe").setOrden(11);
            tab_ingreso.getColumna("anexo_ytring").setNombreVisual("ANEXO");
            tab_ingreso.getColumna("anexo_ytring").setUpload();
            tab_ingreso.getColumna("anexo_ytring").setImagenAutocompletar(50);
            tab_ingreso.getColumna("anexo_ytring").setOrden(24);
            //tab_ingreso.getColumna("ide_ypede").setOrden(14);

            tab_ingreso.getColumna("ide_ytrtie").setCombo(ser_tramite.getSqlTipoEntidad()); // Asignacion de metodo combo
            tab_ingreso.getColumna("ide_ytrtid").setCombo(ser_tramite.getSqlTipoDocumento());
            tab_ingreso.getColumna("ide_ytrtid").setMetodoChange("textoBase");
            tab_ingreso.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
            tab_ingreso.getColumna("ide_ypedpe").setAutoCompletar();
            tab_ingreso.getColumna("ide_ypedpe").setLectura(true); // Se asigna que sera de modo de lectura, no puede hacer cambios
            tab_ingreso.getColumna("ide_ytrtie").setLectura(true);
            tab_ingreso.getColumna("fecha_documento_ytring").setValorDefecto(utilitario.getFechaActual());
            tab_ingreso.getColumna("hora_ytring").setValorDefecto(utilitario.getHoraActual()); //Se asigna que sera la hora
            tab_ingreso.getColumna("fecha_documento_ytring").setEtiqueta();  //Se asigna que el campo es de tipo etiqueta
            tab_ingreso.getColumna("fecha_entrega_ytring").setEtiqueta();
            tab_ingreso.getColumna("hora_ytring").setEtiqueta();
            tab_ingreso.getColumna("estado_ytring").setEtiqueta();
            tab_ingreso.getColumna("estado_ytring").setEstilo("font-size:15px;font-weight: bold;color:green"); //tamaño, color, ubicacion
            tab_ingreso.getColumna("fecha_conclusion_ytring").setEtiqueta();
            tab_ingreso.getColumna("fecha_conclusion_ytring").setEstilo("font-size:15px;font-weight: bold;text-decoration: underline;color:blue");
            tab_ingreso.getColumna("fecha_entrega_ytring").setEstilo("font-size:15px;font-weight: bold;text-decoration: underline;color:blue");
            tab_ingreso.getColumna("hora_ytring").setEstilo("font-size:15px;font-weight: bold;text-decoration: underline;color:blue");
            tab_ingreso.getColumna("fecha_documento_ytring").setEstilo("font-size:15px;font-weight: bold;text-decoration: underline;color:blue");

            tab_ingreso.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
            tab_ingreso.getColumna("ide_yaldap").setAutoCompletar();
            tab_ingreso.getColumna("ide_yaldap").setLectura(true);
            tab_ingreso.getColumna("numero_sec_ytring").setEtiqueta();
            tab_ingreso.getColumna("numero_sec_ytring").setEstilo("font-size:15px;font-weight: bold;text-decoration: underline;color:red");
            //tab_ingreso.getColumna("ide_ypedpe").setVisible(false);  
            tab_ingreso.getColumna("TIPO_TRAMITE_YTRING").setValorDefecto("1");
            tab_ingreso.getColumna("TIPO_TRAMITE_YTRING").setCombo(ser_tramite.tipoTramite());
            tab_ingreso.getColumna("TIPO_TRAMITE_YTRING").setLectura(true);
            tab_ingreso.getColumna("asunto_ytring").setLectura(true);
            tab_ingreso.getColumna("observaciones_ytring").setLectura(true);
            tab_ingreso.setHeader("SEGUIMIENTO DE TRAMITES ASIGNADOS - USUARIO: "+docente);
            tab_ingreso.agregarRelacion(tab_asignacion); //Se agrega rekacion con las dos variables creadas
            //tab_ingreso.agregarRelacion(tab_anexo);
            tab_ingreso.setTipoFormulario(true); //se asigna el tipo de formulario
            tab_ingreso.getGrid().setColumns(6); //Se asigna el numero de columnas que tendra la pantalla
            tab_ingreso.dibujar(); //Se plasma en pantalla

            PanelTabla pat_ingreso = new PanelTabla(); //creacion de nuevo objeto
            pat_ingreso.setId("pat_ingreso");
            pat_ingreso.setPanelTabla(tab_ingreso);

            tab_asignacion.setId("tab_asignacion");
            tab_asignacion.setTabla("yavirac_tra_asignacion", "ide_ytrasi", 2);
            tab_asignacion.setCondicion("estado_ytrasi in ('ASIGNADO','REASIGNADO')");
            tab_asignacion.getColumna("ide_ystard").setCombo(ser_estructura_organizacional.getAreaDepartamento("true,false"));
            tab_asignacion.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false")); //se asigna para que sea combo y poder elegir datos
            tab_asignacion.getColumna("ide_ystard").setAutoCompletar();
            tab_asignacion.getColumna("ide_ypedpe").setAutoCompletar();
            tab_asignacion.getColumna("ide_ystard").setEstilo("width=20px;"); //tamaño
            tab_asignacion.getColumna("ide_ypedpe").setLongitud(20);
            tab_asignacion.getColumna("ide_ystard").setLectura(true);
            tab_asignacion.getColumna("ide_ypedpe").setLectura(true);
            tab_asignacion.getColumna("fecha_asignacion_ytrasi").setLectura(true);
            tab_asignacion.getColumna("hora_ingreso_ytrasi").setLectura(true);
            tab_asignacion.getColumna("estado_ytrasi").setLectura(true);
            tab_asignacion.getColumna("tipo_ytrasi").setVisible(false); //visibilidad
            tab_asignacion.getColumna("numero_sec_ytrasi").setLectura(true);
            tab_asignacion.getColumna("numero_sec_ytrasi").setVisible(false);
            tab_asignacion.getColumna("tipo_ytrasi").setValorDefecto("1");
            tab_asignacion.setTableStyleClass("width=80%;");
            tab_asignacion.setTipoFormulario(true);
            tab_asignacion.getGrid().setColumns(6);
            tab_asignacion.agregarRelacion(tab_seguimiento);
            tab_asignacion.dibujar();

            PanelTabla pat_asignacion = new PanelTabla();
            pat_asignacion.setId("pat_asignacion");
            pat_asignacion.setPanelTabla(tab_asignacion);
            
            tab_seguimiento.setId("tab_seguimiento");
            tab_seguimiento.setTabla("yavirac_tra_seguimiento", "ide_ytrseg", 5);
            tab_seguimiento.getColumna("sumilla_ytrseg").setVisible(false);
            tab_seguimiento.getColumna("ide_ypedpe").setVisible(false);
            tab_seguimiento.getColumna("fecha_ytrseg").setLectura(true);
            tab_seguimiento.getColumna("hora_ytrseg").setLectura(true);
            tab_seguimiento.getColumna("estado_seguimiento_ytrseg").setLectura(true);
            tab_seguimiento.getColumna("reasignado_ytrseg").setLectura(true);
            tab_seguimiento.setTipoFormulario(true);
            tab_seguimiento.getGrid().setColumns(6);
            tab_seguimiento.dibujar();
            
            PanelTabla pat_seguimiento = new PanelTabla();
            pat_seguimiento.setId("pat_seguimiento");
            pat_seguimiento.setPanelTabla(tab_seguimiento);

            Division div_ingreso = new Division();
            div_ingreso.setId("div_ingreso");
            div_ingreso.dividir3(pat_ingreso, pat_asignacion,pat_seguimiento,"45%", "40%", "H"); //division de la pantalla en dos partes
            agregarComponente(div_ingreso);
            
            //dialogo conclusion
            dia_conlcusion.setId("dia_conlcusion");
            dia_conlcusion.setTitle("CONCLUIR TRAMITE");
            dia_conlcusion.setWidth("45%");
            dia_conlcusion.setHeight("25%");
            are_conclusion.setId("are_conclusion");
            are_conclusion.setCols(80);
            Etiqueta eti_conclusion= new Etiqueta("REGISTRE CONCLUSIONES:");
            Grid gri_cuerpo = new Grid();
            gri_cuerpo.setColumns(1);
            gri_cuerpo.getChildren().add(eti_conclusion);
            gri_cuerpo.getChildren().add(are_conclusion);
            dia_conlcusion.getBot_aceptar().setMetodo("aceptarConclusion");
            dia_conlcusion.setDialogo(gri_cuerpo);
            agregarComponente(dia_conlcusion);
            
            //dialogo reasiganción
            dia_reasigna.setId("dia_reasigna");
            dia_reasigna.setTitle("REASIGNACIÓN DEL TRAMITE");
            dia_reasigna.setWidth("45%");
            dia_reasigna.setHeight("25%");
            are_reasigna.setId("are_reasigna");
            are_reasigna.setCols(80);
            Etiqueta eti_reasigna= new Etiqueta("REGISTRE DETALLE REASIGNACION:");
            Grid gri_cuerpo_reasigna = new Grid();
            gri_cuerpo_reasigna.setColumns(1);
            gri_cuerpo_reasigna.getChildren().add(eti_reasigna);
            gri_cuerpo_reasigna.getChildren().add(are_reasigna);
            dia_reasigna.getBot_aceptar().setMetodo("aceptarReasigna");
            dia_reasigna.setDialogo(gri_cuerpo_reasigna);
            agregarComponente(dia_reasigna);

            //PANTALLA REGISTRA RESPONSABLE
            sel_asigna_responsable.setId("sel_asigna_responsable");
            sel_asigna_responsable.setTitle("SELECCIONE EL RESPONSABLE DE ASIGNACION");
            sel_asigna_responsable.getBot_aceptar().setMetodo("registraResponsable");
            sel_asigna_responsable.setSeleccionTabla(ser_personal.getDatoPersonalDepartamento("true,false"), "ide_ypedpe");
            sel_asigna_responsable.getTab_seleccion().getColumna("apellidos").setFiltro(true);
            sel_asigna_responsable.getTab_seleccion().getColumna("nombres").setFiltro(true);
            sel_asigna_responsable.getTab_seleccion().getColumna("ide_ystard").setVisible(false);
            sel_asigna_responsable.setRadio();
            agregarComponente(sel_asigna_responsable);            

        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos dar seguimiento al los tramites. Consulte con el Administrador");
        }
    }
    public void registrarConclusion(){
        dia_conlcusion.dibujar();
    }
    public void registrarReasignacion(){
        dia_reasigna.dibujar();
    }    
 public void imprimir(){
        String usuario=utilitario.getVariable("NICK");
        if (tab_ingreso.getValorSeleccionado() != null) {
                        ///////////AQUI ABRE EL REPORTE
                                Map parametros = new HashMap();

                        parametros.put("pide_tramite", Integer.parseInt(tab_ingreso.getValor("ide_ytring")));
                         parametros.put("nombre", usuario);
                        //System.out.println(" paramteros " + parametros);
                        vipdf_proforma.setVisualizarPDF("rep_tramites/reporte_tramite.jasper", parametros);
                        vipdf_proforma.dibujar();
                        utilitario.addUpdate("vipdf_proforma");
        } else {
            utilitario.agregarMensajeInfo("Seleccione un tramite para imprimir", "");
        }
    }
//desarrollo de evento en la variable texto base, en donde al seleccionar el tipo de documento se pondra el texto base

    String docente = "";
    String documento = "";
    String ide_docente = "";
//desarrollando perfil a los usuarios
    private boolean tienePerfilSecretaria() {
        List sql = utilitario.getConexion().consultar(ser_estructura_organizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA"), " and not ide_ypedpe is null"));

        if (!sql.isEmpty()) {
            Object[] fila = (Object[]) sql.get(0);
            List sql2 = utilitario.getConexion().consultar(ser_personal.getDatoPersonalCodigo(fila[3].toString()));
            if (!sql2.isEmpty()) {
                Object[] fila2 = (Object[]) sql2.get(0);
                docente = fila2[1].toString() + " " + fila2[2].toString();
                documento = fila2[3].toString();
                ide_docente = fila2[0].toString();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public void aceptarConclusion() {
        
        if (!are_conclusion.getValue().equals("")) {
            tab_seguimiento.insertar();
            tab_seguimiento.setValor("ide_ypedpe", ide_docente);
            tab_seguimiento.setValor("fecha_ytrseg",utilitario.getFechaActual());
            tab_seguimiento.setValor("hora_ytrseg",utilitario.getHoraActual());
            tab_seguimiento.setValor("detalle_seguimiento_ytrseg",are_conclusion.getValue().toString());
            tab_seguimiento.setValor("estado_seguimiento_ytrseg","CONCLUIDO");
            tab_seguimiento.setValor("reasignado_ytrseg","false");
            tab_ingreso.setValor("estado_ytring", "CONCLUIDO");
            tab_ingreso.setValor("fecha_conclusion_ytring", utilitario.getFechaActual());
            tab_asignacion.setValor("estado_ytrasi", "CONCLUIDO");
            utilitario.addUpdate("tab_seguimiento");
            tab_ingreso.modificar(tab_ingreso.getFilaActual());//para que haga el update
            utilitario.addUpdateTabla(tab_ingreso, "estado_ytring,fecha_conclusion_ytring", "");
            tab_asignacion.modificar(tab_asignacion.getFilaActual());//para que haga el update
            utilitario.addUpdateTabla(tab_asignacion, "estado_ytrasi", "");
            dia_conlcusion.cerrar();
            
        } else {
            utilitario.agregarMensajeInfo("Debe registrar las conclusiones del trámite", "");
        }
      
    }
public void aceptarReasigna() {
        
        if (!are_reasigna.getValue().equals("")) {
            tab_seguimiento.insertar();
            tab_seguimiento.setValor("ide_ypedpe", ide_docente);
            tab_seguimiento.setValor("fecha_ytrseg",utilitario.getFechaActual());
            tab_seguimiento.setValor("hora_ytrseg",utilitario.getHoraActual());
            tab_seguimiento.setValor("detalle_seguimiento_ytrseg",are_reasigna.getValue().toString());
            tab_seguimiento.setValor("estado_seguimiento_ytrseg","REASIGNADO");
            tab_seguimiento.setValor("reasignado_ytrseg","true");
            tab_ingreso.setValor("estado_ytring", "REASIGNADO");
            tab_ingreso.setValor("fecha_conclusion_ytring", utilitario.getFechaActual());
            tab_asignacion.setValor("estado_ytrasi", "CONCLUIDO");
            utilitario.addUpdate("tab_seguimiento");
            tab_ingreso.modificar(tab_ingreso.getFilaActual());//para que haga el update
            utilitario.addUpdateTabla(tab_ingreso, "estado_ytring,fecha_conclusion_ytring", "");
            tab_asignacion.modificar(tab_asignacion.getFilaActual());//para que haga el update
            utilitario.addUpdateTabla(tab_asignacion, "estado_ytrasi", "");
            dia_reasigna.cerrar();
            
        } else {
            utilitario.agregarMensajeInfo("Debe registrar las conclusiones del trámite", "");
        }
      
    }
    public void selregistraResponsable() {
        if (tab_ingreso.getValor("ide_ytring")==null) {
                //Controla que si ya esta insertada no vuelva a insertar
                utilitario.agregarMensajeError("Registre el Tramite", "EL tramite no se encuentra guardado");
            }
            else {
        sel_asigna_responsable.getTab_seleccion().setSql(ser_personal.getDatoPersonalDepartamento("true"));
        sel_asigna_responsable.getTab_seleccion().ejecutarSql();
        sel_asigna_responsable.dibujar();
        }
    }
    public void registraResponsable() {
        String str_seleccionado = sel_asigna_responsable.getValorSeleccionado();
        if (str_seleccionado != null) {
            tab_asignacion.insertar();
            tab_asignacion.setValor("ide_ystard", sel_asigna_responsable.getTab_seleccion().getValor(sel_asigna_responsable.getTab_seleccion().getFilaSeleccionada().getIndice(),"ide_ystard"));
            tab_asignacion.setValor("ide_ypedpe", sel_asigna_responsable.getTab_seleccion().getValor(sel_asigna_responsable.getTab_seleccion().getFilaSeleccionada().getIndice(),"ide_ypedpe"));
            tab_asignacion.setValor("fecha_asignacion_ytrasi",utilitario.getFechaActual());
            tab_asignacion.setValor("hora_ingreso_ytrasi",utilitario.getHoraActual());
            tab_asignacion.setValor("estado_ytrasi","REASIGNADO");
            sel_asigna_responsable.cerrar();
            utilitario.addUpdate("tab_asignacion");
            tab_asignacion.guardar();
            guardarPantalla();
            tab_ingreso.ejecutarSql();
       tab_asignacion.ejecutarValorForanea(tab_ingreso.getValorSeleccionado());
       tab_seguimiento.ejecutarValorForanea(tab_ingreso.getValorSeleccionado()); 

            
        } else {
            utilitario.agregarMensajeInfo("Debe seleccionar al menos un registro", "");
        }
    }

    @Override
    public void insertar() { //al registrar un nuevo registro primero se debe seleccionar el estudiante
    }

    @Override
    public void guardar() {
        if (tab_ingreso.guardar()) {
            if (tab_asignacion.guardar()) {
                if (tab_seguimiento.guardar());
            }
        }
        guardarPantalla();
        if(tab_seguimiento.getValor("estado_seguimiento_ytrseg").equals("REASIGNADO")){
            sel_asigna_responsable.dibujar();
        }
        else{
          tab_ingreso.ejecutarSql();
       tab_asignacion.ejecutarValorForanea(tab_ingreso.getValorSeleccionado());
       tab_seguimiento.ejecutarValorForanea(tab_ingreso.getValorSeleccionado());  
        
    }
    }

    @Override
    public void eliminar() {
        tab_seguimiento.eliminar();
    }

    public Tabla getTab_ingreso() {
        return tab_ingreso;
    }

    public void setTab_ingreso(Tabla tab_ingreso) {
        this.tab_ingreso = tab_ingreso;
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

    public Tabla getTab_anexo() {
        return tab_anexo;
    }

    public void setTab_anexo(Tabla tab_anexo) {
        this.tab_anexo = tab_anexo;
    }

    public Tabla getTab_asignacion() {
        return tab_asignacion;
    }

    public void setTab_asignacion(Tabla tab_asignacion) {
        this.tab_asignacion = tab_asignacion;
    }

    public SeleccionTabla getSel_registra_alumno() {
        return sel_registra_alumno;
    }

    public void setSel_registra_alumno(SeleccionTabla sel_registra_alumno) {
        this.sel_registra_alumno = sel_registra_alumno;
    }

    public SeleccionTabla getSel_asigna_responsable() {
        return sel_asigna_responsable;
    }

    public void setSel_asigna_responsable(SeleccionTabla sel_asigna_responsable) {
        this.sel_asigna_responsable = sel_asigna_responsable;
    }

    public VisualizarPDF getVipdf_proforma() {
        return vipdf_proforma;
    }

    public void setVipdf_proforma(VisualizarPDF vipdf_proforma) {
        this.vipdf_proforma = vipdf_proforma;
    }

    public Dialogo getDia_anexos() {
        return dia_anexos;
    }

    public void setDia_anexos(Dialogo dia_anexos) {
        this.dia_anexos = dia_anexos;
    }

    public Tabla getTab_seguimiento() {
        return tab_seguimiento;
    }

    public void setTab_seguimiento(Tabla tab_seguimiento) {
        this.tab_seguimiento = tab_seguimiento;
    }

    public Dialogo getDia_reasigna() {
        return dia_reasigna;
    }

    public void setDia_reasigna(Dialogo dia_reasigna) {
        this.dia_reasigna = dia_reasigna;
    }

    public Dialogo getDia_conlcusion() {
        return dia_conlcusion;
    }

    public void setDia_conlcusion(Dialogo dia_conlcusion) {
        this.dia_conlcusion = dia_conlcusion;
    }

}

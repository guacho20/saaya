package paq_matricula;

import framework.aplicacion.Fila;
import framework.aplicacion.TablaGenerica;
import framework.componentes.AreaTexto;
import framework.componentes.AutoCompletar;
import framework.componentes.Barra;
import framework.componentes.Boton;
import framework.componentes.BotonesCombo;
import framework.componentes.Combo;
import framework.componentes.Confirmar;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Espacio;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Grupo;
import framework.componentes.Imagen;
import framework.componentes.ItemMenu;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import framework.componentes.Texto;
import framework.componentes.Upload;
import framework.componentes.VisualizarPDF;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.poi.util.IOUtils;
import org.primefaces.component.media.Media;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.separator.Separator;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import paq_matricula.ejb.ServicioMatriculas;
import paq_nota.ejb.ServicioNotas;
import paq_personal.ejb.ServicioPersonal;
import persistencia.Conexion;
import sistema.aplicacion.Pantalla;

public class Matriculas extends Pantalla {

    private Tabla tab_matriculas = new Tabla();//instanciar tabla del framework
    private Tabla tab_documento_entregado = new Tabla();
    private Tabla tab_registro_credito = new Tabla();
    private Tabla tab_detalle_record = new Tabla();
    private Tabla tab_cabecera_record = new Tabla();
    private Tabla tab_tabla1 = new Tabla();
    private Tabla tab_tabla2 = new Tabla();
    private Tabla tab_tabla3 = new Tabla();
    private Tabla tab_materias = new Tabla();
    private Combo com_periodo_academico = new Combo();
    private Combo com_jornada = new Combo();
    private Combo com_paralelo = new Combo();
    private Combo com_tipo_credito = new Combo();
    private Combo com_documento_req = new Combo();
    private Boton bot_clean = new Boton();
    private Upload upl_archivo = new Upload();
    private Dialogo dia_importar = new Dialogo();
    private SeleccionTabla sel_registra_alumno = new SeleccionTabla();
    private SeleccionTabla sel_actualiza_alumno = new SeleccionTabla();
    private SeleccionTabla sel_materias = new SeleccionTabla();
    private Confirmar con_guardar_alumno = new Confirmar();
    public static String par_modulo_matricula;
    private VisualizarPDF vipdf_comprobante = new VisualizarPDF();
    private Conexion conOracle = new Conexion();
    private Dialogo dia_materias = new Dialogo();
    private Dialogo dia_dialogo = new Dialogo();
    private Dialogo dia_anular = new Dialogo();
    private Confirmar con_confirma = new Confirmar();
    private Confirmar con_informacion = new Confirmar();
    private AutoCompletar aut_alumno = new AutoCompletar();
    private AreaTexto are_detalle = new AreaTexto();
    private Media med_archivo = new Media();
    private Grupo gru_botones_dialogo = new Grupo();
    private Texto txt_observaciones = new Texto();
    private Dialogo dia_adjunto2 = new Dialogo();
    private Media med_archivo2 = new Media();
    String message = "";
    private String observacion = "";

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioMatriculas ser_matricula = (ServicioMatriculas) utilitario.instanciarEJB(ServicioMatriculas.class);
    @EJB
    private final ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    public Matriculas() {//constructor

        if (tienePerfiMatricula()) {
            par_modulo_matricula = utilitario.getVariable("p_documento_inscripcion");

            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_matricula.getPeriodoMatricula("true"));
            agregarComponente(com_periodo_academico);
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");

            //BOTON REGISTRO DE ALUMNOS
            Boton bot_registroAlumno = new Boton();
            bot_registroAlumno.setValue("Listado Alumnos");
            bot_registroAlumno.setIcon("ui-icon-note");
            bot_registroAlumno.setMetodo("selregistraAlumno");
            //grup_cuerpo.getChildren().add(bot_registroAlumno);

            //BOTON ACTUALIZAR DE ALUMNOS
            Boton bot_actualizaAlumno = new Boton();
            bot_actualizaAlumno.setValue("Actualizar Alumno");
            bot_actualizaAlumno.setIcon("ui-icon-refresh");
            bot_actualizaAlumno.setMetodo("selactualizaAlumno");
            //grup_cuerpo.getChildren().add(bot_actualizaAlumno);
            //BOTON RECEPCION DE DOCUMENTOS
            Boton bot_recibe_documento = new Boton();
            bot_recibe_documento.setValue("Recibir Documentos");
            bot_recibe_documento.setIcon("ui-icon-clipboard");
            bot_recibe_documento.setMetodo("recibeDocumento");
            // grup_cuerpo.getChildren().add(bot_recibe_documento);

            bot_clean.setIcon("ui-icon-cancel");
            bot_clean.setTitle("Limpiar");
            bot_clean.setMetodo("limpiar");
            bar_botones.agregarComponente(bot_clean);

            Boton bot_materias = new Boton();
            bot_materias.setIcon("ui-icon-print");
            bot_materias.setValue("Insertar Materias");
            bot_materias.setMetodo("abrirMaterias");

            Boton bot_aprobar = new Boton();
            bot_aprobar.setIcon("ui-icon-print");
            bot_aprobar.setValue("Aprobar Matrícula");
            bot_aprobar.setMetodo("aprobarMatricula");
            //bar_botones.agregarBoton(bot_anular);
            Boton bot_imprimir = new Boton();
            bot_imprimir.setIcon("ui-icon-print");
            bot_imprimir.setValue("Certificado Matrícula");
            bot_imprimir.setMetodo("generarPDF");

            Boton bot_anular = new Boton();
            bot_anular.setIcon("ui-icon-cancel");
            bot_anular.setValue("Anular Matrícula");
            bot_anular.setMetodo("abrirAnular");

            bar_botones.agregarBoton(bot_materias);
            bar_botones.agregarBoton(bot_aprobar);
            bar_botones.agregarBoton(bot_imprimir);
            //bar_botones.agregarBoton(bot_registroAlumno);
            bar_botones.agregarBoton(bot_anular);
            bar_botones.agregarBoton(bot_recibe_documento);

            Tabulador tab_tabulador = new Tabulador();
            tab_tabulador.setId("tab_tabulador");

            tab_matriculas.setId("tab_matriculas");// todo objeto instanciado poner id 
            tab_matriculas.setTabla("yavirac_matri_matricula", "ide_ymamat", 1);  // nombre de la base de datos ii la clave primaria
            tab_matriculas.setHeader("REGISTRO DE MATRÍCULAS");
            tab_matriculas.setCampoOrden("ide_ymamat desc");
            tab_matriculas.setCondicion("ide_ymaper=-1");
            tab_matriculas.getColumna("recibido_ymamat").setValorDefecto("false");
            tab_matriculas.getColumna("recibido_ymamat").setLectura(true);
            tab_matriculas.getColumna("ide_ystmen").setCombo(ser_estructura.getMension());
            tab_matriculas.getColumna("ide_ystnie").setCombo(ser_estructura.getNivelEducacion());
            tab_matriculas.getColumna("ide_ymatma").setCombo(ser_matricula.getTipoMatricula());
            tab_matriculas.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
            tab_matriculas.getColumna("ide_yaldap").setAutoCompletar();
            //tab_matriculas.getColumna("ide_yaldap").setLectura(true);
            tab_matriculas.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
            tab_matriculas.getColumna("ide_ypedpe").setAutoCompletar();
            tab_matriculas.getColumna("ide_ypedpe").setLectura(true);
            tab_matriculas.agregarRelacion(tab_documento_entregado);
            tab_matriculas.agregarRelacion(tab_registro_credito);

            tab_matriculas.setTipoFormulario(true);//para que se haga un formulario
            tab_matriculas.getGrid().setColumns(4); //numero de columnas del formulario
            //*********************************Etiquetas*****************************************************
            tab_matriculas.getColumna("ide_ymamat").setNombreVisual("CÓDIGO");
            tab_matriculas.getColumna("ide_ypedpe").setNombreVisual("RESPONSABLE");
            tab_matriculas.getColumna("ide_ystnie").setNombreVisual("NIVEL");
            tab_matriculas.getColumna("ide_ystnie").setLectura(true);
            tab_matriculas.getColumna("ide_ystocu").setNombreVisual("OCUPACIÓN");
            tab_matriculas.getColumna("ide_ystocu").setVisible(false);
            tab_matriculas.getColumna("ide_ystmen").setNombreVisual("MENSIÓN");
            tab_matriculas.getColumna("ide_ystmen").setLectura(true);
            tab_matriculas.getColumna("ide_ystmen").setRequerida(true);
            tab_matriculas.getColumna("ide_ystnie").setRequerida(true);
            tab_matriculas.getColumna("ide_ymatma").setRequerida(true);
            tab_matriculas.getColumna("nro_folio_ymamat").setRequerida(true);
            tab_matriculas.getColumna("nro_folio_ymamat").setNombreVisual("NRO FOLIO");
            tab_matriculas.getColumna("nro_folio_ymamat").setEtiqueta();
            tab_matriculas.getColumna("nro_folio_ymamat").setEstilo("font-size:12px;font-weight: bold;color:red");
            tab_matriculas.getColumna("estudia_otro_lugar_ymamat").setNombreVisual("ESTUDIA APARTE");
            tab_matriculas.getColumna("estudia_otro_lugar_ymamat").setVisible(false);
            tab_matriculas.getColumna("semestre_otro_lugar_ymamat").setNombreVisual("SEMESTRE");
            tab_matriculas.getColumna("semestre_otro_lugar_ymamat").setVisible(false);
            tab_matriculas.getColumna("donde_tabaja_ymamat").setNombreVisual("DONDE TRABAJA");
            tab_matriculas.getColumna("donde_tabaja_ymamat").setVisible(false);
            tab_matriculas.getColumna("recibido_ymamat").setNombreVisual("RECIBIDO");
            tab_matriculas.getColumna("recibido_ymamat").setVisible(false);
            tab_matriculas.getColumna("ide_ymaper").setVisible(false);
            tab_matriculas.getColumna("ide_yaldap").setNombreVisual("DATO ALUMNO");
            tab_matriculas.getColumna("ide_yaldap").setMetodoChange("cargarCarreraNivel");
            tab_matriculas.getColumna("ide_ystins").setNombreVisual("INSTITUCIÓN");
            tab_matriculas.getColumna("ide_ystins").setVisible(false);
            tab_matriculas.getColumna("ide_ymatma").setNombreVisual("MATRÍCULA");
            tab_matriculas.getColumna("fecha_ymamat").setNombreVisual("FECHA MATRÍCULA");
            tab_matriculas.getColumna("fecha_ymamat").setLectura(true);
            tab_matriculas.getColumna("fecha_ymamat").setValorDefecto(utilitario.getFechaActual());
            tab_matriculas.getColumna("carrera_otro_lugar_ymamat").setNombreVisual("OTRA CARRERA");
            tab_matriculas.getColumna("carrera_otro_lugar_ymamat").setVisible(false);
            tab_matriculas.getColumna("trabaja_ymamat").setNombreVisual("TRABAJA");
            tab_matriculas.getColumna("trabaja_ymamat").setVisible(false);
            tab_matriculas.getColumna("telefono_trabaja_ymamat").setNombreVisual("TELÉFONO TRABAJO");
            tab_matriculas.getColumna("telefono_trabaja_ymamat").setVisible(false);
            tab_matriculas.getColumna("aprobado_ymamat").setValorDefecto("false");
            tab_matriculas.getColumna("aprobado_ymamat").setNombreVisual("APROBADO");
            tab_matriculas.getColumna("aprobado_ymamat").setLectura(true);
            tab_matriculas.getColumna("anulado_ymamat").setNombreVisual("ANULADO");
            tab_matriculas.getColumna("anulado_ymamat").setLectura(true);
            tab_matriculas.getColumna("anulado_ymamat").setValorDefecto("false");
            tab_matriculas.getColumna("detalle_anulado_ymamat").setVisible(false);
            tab_matriculas.dibujar();

            PanelTabla pa_matriculas = new PanelTabla();//intanciamos el panel del framework
            pa_matriculas.setId("pa_matriculas");//nombre id
            pa_matriculas.setPanelTabla(tab_matriculas);//agregar a nuestra tabla el panel

            Boton bot_adjunto = new Boton();
            bot_adjunto.setValue("Ver Archivo Adjunto");
            bot_adjunto.setIcon("ui-icon-plusthick");
            bot_adjunto.setMetodo("visualizarAdjuntos");

            //*** tabulacion del documento entregado
            tab_documento_entregado.setId("tab_documento_entregado");// todo objeto instanciado poner id 
            tab_documento_entregado.setTabla("yavirac_matri_documento_entrega", "ide_ymaden", 2);  // nombre de la base de datos ii la clave primaria
            tab_documento_entregado.setIdCompleto("tab_tabulador:tab_documento_entregado");
            tab_documento_entregado.setHeader("DOCUMENTOS ENTREGADOS");
            // tab_documento_entregado.setParent(bot_adjunto);
            //***************************************************************************************************
            tab_documento_entregado.getColumna("ide_ymaden").setNombreVisual("CÓDIGO");
            tab_documento_entregado.getColumna("ide_ystdor").setNombreVisual("DOCUMENTO REQUERIDO");
            tab_documento_entregado.getColumna("fecha_ymden").setNombreVisual("FECHA");
            tab_documento_entregado.getColumna("observacion_ymaden").setNombreVisual("OBSERVACIÓN");
            tab_documento_entregado.getColumna("entregado_ymaden").setNombreVisual("ENTREGADO");
            tab_documento_entregado.getColumna("ide_ystdor").setNombreVisual("DOCUMENTO");
            tab_documento_entregado.getColumna("ide_ystdor").setCombo(ser_estructura.getDocumentoRequerido("true,false"));
            tab_documento_entregado.getColumna("archivo_ymaden").setVisible(false);
            tab_documento_entregado.setRecuperarLectura(true);
            tab_documento_entregado.dibujar();

            PanelTabla pa_documento_entregado = new PanelTabla();
            pa_documento_entregado.setId("pa_documento_entregado");
            pa_documento_entregado.setHeader(bot_adjunto);
            pa_documento_entregado.setPanelTabla(tab_documento_entregado);

            //*** tabulador de registro de credito
            tab_registro_credito.setId("tab_registro_credito");
            tab_registro_credito.setTabla("yavirac_matri_registro_credito", "ide_ymarec", 3);
            tab_registro_credito.getColumna("ide_ymatrc").setCombo(ser_matricula.getTipoRegitroCredito());
            tab_registro_credito.getColumna("ide_ymatrc").setAncho(-1);
            tab_registro_credito.getColumna("ide_ymatrc").setLongitud(-1);
            tab_registro_credito.getColumna("ide_ymanum").setCombo(ser_matricula.getNumeroMatricula());
            tab_registro_credito.getColumna("ide_ymanum").setAncho(-1);
            tab_registro_credito.getColumna("ide_ymanum").setLongitud(-1);
            tab_registro_credito.getColumna("ide_ystmal").setCombo(ser_estructura.getMalla());
            tab_registro_credito.getColumna("ide_ystmal").setAutoCompletar();
            //***************************************************************************
            tab_registro_credito.getColumna("ide_ymarec").setNombreVisual("CODIGO");
            tab_registro_credito.getColumna("ide_ymatrc").setNombreVisual("TIPO CREDITO");
            tab_registro_credito.getColumna("ide_ymanum").setNombreVisual("NRO. MATRICULA");
            tab_registro_credito.getColumna("ide_ystmal").setNombreVisual("MALLA");
            tab_registro_credito.getColumna("codigo_asignatura_ymarcm").setNombreVisual("CÓD. ASIGNATURA");
            tab_registro_credito.getColumna("numero_de_creditos_ymarcm").setNombreVisual("NRO. CRÉDITO");
            tab_registro_credito.getColumna("observacion_ymarec").setNombreVisual("OBSERVACIÓN");
            tab_registro_credito.getColumna("ide_yhogra").setCombo(ser_horarios.getGrupoAcademico());
            tab_registro_credito.getColumna("ide_yhogra").setAncho(-1);
            tab_registro_credito.getColumna("ide_yhogra").setLongitud(-1);
            tab_registro_credito.getColumna("ide_yhogra").setNombreVisual("PARALELO");
            tab_registro_credito.getColumna("ide_ystjor").setCombo(ser_estructura.getJornada("true,false"));
            tab_registro_credito.getColumna("ide_ystjor").setAncho(-1);
            tab_registro_credito.getColumna("ide_ystjor").setLongitud(-1);
            tab_registro_credito.getColumna("numero_de_creditos_ymarcm").setAncho(-1);
            tab_registro_credito.getColumna("numero_de_creditos_ymarcm").setLongitud(-1);
            tab_registro_credito.getColumna("ide_ystjor").setNombreVisual("JORNADA");

            tab_registro_credito.getColumna("observacion_ymarec").setVisible(false);
            tab_registro_credito.getColumna("ide_ystmal").setEstilo("width: 300px;");
            tab_registro_credito.getColumna("codigo_asignatura_ymarcm").setEstilo("width: 100px;");
            tab_registro_credito.getColumna("numero_de_creditos_ymarcm").setEstilo("width: 100px;");
            tab_registro_credito.setRecuperarLectura(true);
            // tab_registro_credito.setRows(6);
            tab_registro_credito.dibujar();//dibuja la tabla

            PanelTabla pa_registro_credito = new PanelTabla();//intanciamos el panel del framework
            pa_registro_credito.setId("pa_registro_credito");//nombre id
            pa_registro_credito.getMenuTabla().getItem_insertar().setRendered(false);
            pa_registro_credito.getMenuTabla().getItem_guardar().setRendered(false);
            pa_registro_credito.setPanelTabla(tab_registro_credito);//agregar a nuestra tabla el panel
            tab_registro_credito.setIdCompleto("tab_tabulador:tab_registro_credito");

            //*******************************AGREGA PESTAÑANAS*********************************************//
            tab_tabulador.agregarTab("REGISTRO DE CRÉDITOS", pa_registro_credito);
            tab_tabulador.agregarTab("DOCUMENTOS ENTREGADOS", pa_documento_entregado);

            //instanciar una division del framework
            Division div_matriculas = new Division();//instanciamos
            div_matriculas.setId("div_matriculas");//es un idientificador
            div_matriculas.dividir2(pa_matriculas, tab_tabulador, "37%", "H");
            agregarComponente(div_matriculas);

            //PANTALLA INGRESA ALUMNO
            sel_registra_alumno.setId("sel_registra_alumno");
            sel_registra_alumno.setTitle("SELECCIONE EL ALUMNO");
            sel_registra_alumno.getBot_aceptar().setMetodo("registraAlumno");
            sel_registra_alumno.setSeleccionTabla(ser_alumno.getDatosAlumnos("null"), "ide_yaldap");
            sel_registra_alumno.getTab_seleccion().getColumna("apellido_yaldap").setFiltro(true);
            sel_registra_alumno.getTab_seleccion().getColumna("nombre_yaldap").setFiltro(true);
            sel_registra_alumno.setRadio();
            agregarComponente(sel_registra_alumno);

            //PANTALLA ACTUALIZA ALUMNO
            sel_actualiza_alumno.setId("sel_actualiza_alumno");
            sel_actualiza_alumno.setTitle("SELECCIONE EL ALUMNO");
            sel_actualiza_alumno.getBot_aceptar().setMetodo("actualizaAlumno");
            sel_actualiza_alumno.setSeleccionTabla(ser_alumno.getDatosAlumnos("null"), "ide_yaldap");
            sel_actualiza_alumno.getTab_seleccion().getColumna("apellido_yaldap").setFiltro(true);
            sel_actualiza_alumno.getTab_seleccion().getColumna("nombre_yaldap").setFiltro(true);
            sel_actualiza_alumno.setRadio();
            agregarComponente(sel_actualiza_alumno);

            //DIALOGO MODIFICAR PROPIETARIO
            dia_materias.setId("dia_materias");
            dia_materias.setTitle("ASIGNAR MATERIAS");
            dia_materias.setWidth("60%");
            dia_materias.setHeight("60%");
            dia_materias.setResizable(false);
            //eti_adicional.setId("eti_adicional");
            Grid gro_cuerpo = new Grid();
            gro_cuerpo.setWidth("100%");
            gro_cuerpo.setStyle("width:100%;overflow: auto;display: block;");
            gro_cuerpo.getChildren().clear();

            com_jornada.setId("com_jornada");
            com_jornada.setCombo(ser_estructura.getJornada("true,false"));
            com_paralelo.setId("com_paralelo");
            com_paralelo.setCombo(ser_horarios.getGrupoAcademico());
            com_tipo_credito.setId("com_tipo_credito");
            com_tipo_credito.setCombo(ser_matricula.getTipoRegitroCredito());

            Grid gro_cabecera = new Grid();
            gro_cabecera.setColumns(6);
            Grid gro_pie = new Grid();

            gro_cabecera.getChildren().add(new Etiqueta("JORNADA: "));
            gro_cabecera.getChildren().add(com_jornada);
            gro_cabecera.getChildren().add(new Etiqueta("PARALELO: "));
            gro_cabecera.getChildren().add(com_paralelo);
            gro_cabecera.getChildren().add(new Etiqueta("TIPO CREDITO: "));
            gro_cabecera.getChildren().add(com_tipo_credito);

            BotonesCombo boc_seleccion_inversa = new BotonesCombo();
            ItemMenu itm_todas = new ItemMenu();
            ItemMenu itm_niguna = new ItemMenu();

            boc_seleccion_inversa.setValue("Selección Inversa");
            boc_seleccion_inversa.setIcon("ui-icon-circle-check");
            boc_seleccion_inversa.setMetodo("seleccinarInversa");
            boc_seleccion_inversa.setUpdate("tab_materias");
            itm_todas.setValue("Seleccionar Todo");
            itm_todas.setIcon("ui-icon-check");
            itm_todas.setMetodo("seleccionarTodas");
            itm_todas.setUpdate("tab_materias");
            boc_seleccion_inversa.agregarBoton(itm_todas);
            itm_niguna.setValue("Seleccionar Ninguna");
            itm_niguna.setIcon("ui-icon-minus");
            itm_niguna.setMetodo("seleccionarNinguna");
            itm_niguna.setUpdate("tab_materias");
            boc_seleccion_inversa.agregarBoton(itm_niguna);

            tab_materias.setId("tab_materias");
            tab_materias.setSql(ser_matricula.getMaterias("-1", "-1", "-1", "-1"));
            tab_materias.getColumna("ide_ystnie").setVisible(false);
            tab_materias.getColumna("detalle_ystmat").setNombreVisual("MATERIA");
            tab_materias.getColumna("codigo_ystmal").setNombreVisual("CODIGO");
            tab_materias.getColumna("numero_credito_ystmal").setNombreVisual("NRO. CREDITO");
            tab_materias.getColumna("descripcion_ystnie").setNombreVisual("NIVEL");
            tab_materias.setLectura(true);
            tab_materias.setTipoSeleccion(true);
            tab_materias.setRows(10);
            tab_materias.dibujar();

            PanelTabla pa_materia = new PanelTabla();
            pa_materia.setId("pa_materia");
            pa_materia.getChildren().add(boc_seleccion_inversa);
            pa_materia.getChildren().add(tab_materias);

            gro_pie.getChildren().add(pa_materia);

            gro_cuerpo.getChildren().add(gro_cabecera);
            gro_cuerpo.getChildren().add(new Separator());
            gro_cuerpo.getChildren().add(gro_pie);
            dia_materias.getBot_aceptar().setMetodo("insertarMaterias");
            dia_materias.setDialogo(gro_cuerpo);
            agregarComponente(dia_materias);

            /**
             * *****************LISTA LAS
             * MATERIAS*****************************************
             */
            sel_materias.setId("sel_materias");
            sel_materias.setTitle("MATERIAS");
            sel_materias.setSeleccionTabla(ser_alumno.getDatosAlumnos("null"), "ide_yaldap");
            //sel_materias.getTab_seleccion().getColumna("apellido_yaldap").setFiltro(true);
            //sel_materias.getTab_seleccion().getColumna("nombre_yaldap").setFiltro(true);
            //sel_materias.getBot_aceptar().setMetodo("actualizaAlumno");
            //sel_materias.setRadio();
            agregarComponente(sel_materias);

            con_guardar_alumno.setId("con_guardar_alumno");
            agregarComponente(con_guardar_alumno);

            vipdf_comprobante.setId("vipdf_comprobante");
            vipdf_comprobante.setTitle("CERTIFICADO DE MATRICULA");
            agregarComponente(vipdf_comprobante);

            tab_cabecera_record.setId("tab_cabecera_record");
            tab_cabecera_record.setTabla("yavirac_nota_cab_rec_acad", "ide_ynocra", 4);

            tab_detalle_record.setId("tab_detalle_record");
            tab_detalle_record.setTabla("yavirac_nota_det_rec_acad", "ide_ynodra", 5);

            //CONFIRMAR
            con_confirma.setId("con_confirma");
            con_confirma.setMessage("Está seguro que desea aprobar la matricula y generar el record académico.");
            con_confirma.setTitle("APROBAR MATRICULA");
            con_confirma.getBot_aceptar().setValue("Si");
            con_confirma.getBot_cancelar().setValue("No");
            agregarComponente(con_confirma);

            bloquearModificacion();

            //Información
            con_informacion.setId("con_informacion");
            con_informacion.setMessage(message);
            con_informacion.setTitle("INFORMACIÓN");
            con_informacion.getBot_aceptar().setMetodo("cerrarInfo");
            // con_informacion.getBot_cancelar().s;
            agregarComponente(con_informacion);

            //DIALOGO ANULAR
            dia_anular.setId("dia_anular");
            dia_anular.setTitle("ANULAR MATRICULA");
            dia_anular.setWidth("60%");
            dia_anular.setHeight("50%");
            dia_anular.setResizable(false);

            Boton bot_limpiar = new Boton();
            bot_limpiar.setIcon("ui-icon-cancel");
            bot_limpiar.setTitle("Limpiar");
            bot_limpiar.setMetodo("limpiarAnular");

            //eti_adicional.setId("eti_adicional");
            Grid gri_cuerpo_anula = new Grid();
            gri_cuerpo_anula.setWidth("100%");
            gri_cuerpo_anula.setStyle("width:100%;overflow: auto;display: block;");
            gri_cuerpo_anula.getChildren().clear();

            aut_alumno.setId("aut_alumno");
            aut_alumno.setAutoCompletar(ser_alumno.getDatosAlumnos("true"));
            aut_alumno.setMetodoChange("cargarMatricula");

            Grid gro_cabecera_anula = new Grid();
            gro_cabecera_anula.setColumns(6);
            Grid gro_pie_anula = new Grid();

            gro_cabecera_anula.getChildren().add(new Etiqueta("ESTUDIANTE: "));
            gro_cabecera_anula.getChildren().add(aut_alumno);
            gro_cabecera_anula.getChildren().add(bot_limpiar);

            tab_tabla1.setId("tab_tabla1");
            tab_tabla1.setHeader("MATRICULAS");
            tab_tabla1.setSql(ser_matricula.getMatriculasNoAnulados("0", "0"));
            tab_tabla1.getColumna("descripcion_ystnie").setNombreVisual("NIVEL");
            tab_tabla1.getColumna("descripcion_ystmen").setNombreVisual("CARRERA");
            tab_tabla1.getColumna("fecha_ymamat").setNombreVisual("FECHA");
            tab_tabla1.getColumna("aprobado_ymamat").setNombreVisual("APROBADO");
            tab_tabla1.getColumna("nro_folio_ymamat").setNombreVisual("NRO. FOLIO");
            tab_tabla1.setLectura(true);
            tab_tabla1.setTipoSeleccion(true);
            tab_tabla1.setRows(5);
            tab_tabla1.dibujar();

            PanelTabla pa_panel1 = new PanelTabla();
            pa_panel1.setId("pa_panel1");
            pa_panel1.getChildren().add(tab_tabla1);

            are_detalle.setId("are_detalle");
            are_detalle.setStyle("width: 550px;");
            are_detalle.setRows(3);

            gro_pie_anula.getChildren().add(pa_panel1);

            gri_cuerpo_anula.getChildren().add(gro_cabecera_anula);
            gri_cuerpo_anula.getChildren().add(new Separator());
            gri_cuerpo_anula.getChildren().add(gro_pie_anula);
            gri_cuerpo_anula.getChildren().add(new Etiqueta("Descripción de anulación: "));
            gri_cuerpo_anula.getChildren().add(are_detalle);
            dia_anular.getBot_aceptar().setMetodo("aceptarAnular");
            dia_anular.setDialogo(gri_cuerpo_anula);
            agregarComponente(dia_anular);

            //DIALOGO
            dia_dialogo.setId("dia_dialogo");
            dia_dialogo.setTitle("ADJUNTAR ARCHIVOS");
            dia_dialogo.setWidth("65%");
            dia_dialogo.setHeight("85%");
            dia_dialogo.setResizable(false);

            tab_tabla2.setId("tab_tabla2");
            tab_tabla2.setTabla("yavirac_matri_documento_entrega", "ide_ymaden", 5);
            tab_tabla2.getColumna("ide_ymaden").setNombreVisual("CODIGO");
            tab_tabla2.setCampoOrden("ide_ymaden desc");
            tab_tabla2.getColumna("archivo_ymaden").setVisible(false);
            tab_tabla2.getColumna("archivo_ymaden").setNombreVisual("ARCHIVO");
            tab_tabla2.getColumna("observacion_ymaden").setNombreVisual("DETALLE ARCHIVO");
            // tab_tabla2.getColumna("observacion_ymaden").setLectura(true);
            tab_tabla2.getColumna("ide_ystdor").setCombo(ser_estructura.getDocumentoRequerido("true,false"));
            tab_tabla2.getColumna("fecha_ymden").setVisible(false);
            tab_tabla2.getColumna("entregado_ymaden").setVisible(false);
            tab_tabla2.getColumna("ide_ymamat").setVisible(false);
            tab_tabla2.setTipoFormulario(true);
            tab_tabla2.getGrid().setColumns(6);
            tab_tabla2.setRecuperarLectura(true);
            tab_tabla2.dibujar();

            Boton bot_inicio2 = new Boton();
            bot_inicio2.setTitle("Inicio");
            bot_inicio2.setMetodo("inicio2");
            bot_inicio2.setIcon("ui-icon-seek-first");
            bot_inicio2.setStyle("background: #0099CC;color:#fff ;border:#0099CC;");
            bot_inicio2.setStyleClass("pull-left");

            Boton bot_siguiente2 = new Boton();
            bot_siguiente2.setTitle("Siguiente");
            bot_siguiente2.setMetodo("siguiente2");
            bot_siguiente2.setIcon("ui-icon-seek-next");
            bot_siguiente2.setStyle("background: #0099CC;color:#fff ;border:#0099CC;");
            bot_siguiente2.setStyleClass("pull-left");

            Boton bot_atras2 = new Boton();
            bot_atras2.setTitle("Anterior");
            bot_atras2.setMetodo("atras2");
            bot_atras2.setIcon("ui-icon-seek-prev");
            bot_atras2.setStyle("background: #0099CC;color:#fff ;border:#0099CC;");
            bot_atras2.setStyleClass("pull-left");

            Boton bot_fin2 = new Boton();
            bot_fin2.setTitle("Fin");
            bot_fin2.setMetodo("fin2");
            bot_fin2.setIcon("ui-icon-seek-end");
            bot_fin2.setStyle("background: #0099CC; color:#fff ;border:#0099CC;");
            bot_fin2.setStyleClass("pull-left");

            Boton bot_agregar = new Boton();
            bot_agregar.setValue("Agregar Archivo");
            bot_agregar.setMetodo("subrirArchivo");
            bot_agregar.setIcon("ui-icon-eject");
            bot_agregar.setStyle("background: #0099CC;color:#fff ;border:#0099CC;");
            bot_agregar.setStyleClass("pull-right");

            Boton bot_eliminar = new Boton();
            bot_eliminar.setValue("Eliminar Archivo");
            bot_eliminar.setMetodo("eliminarArchivo");
            bot_eliminar.setIcon("ui-icon-trash");
            bot_eliminar.setStyle("background: #0099CC;color:#fff ;border:#0099CC;");
            bot_eliminar.setStyleClass("pull-right");

            PanelGrid gri_cuerpo = new PanelGrid();
            gri_cuerpo.setColumns(1);
            gri_cuerpo.setStyle(" width: 100%;");

            Espacio esp_espacio_dialogo = new Espacio();
            esp_espacio_dialogo.setWidth("30");
            esp_espacio_dialogo.setHeight("0");

            gru_botones_dialogo.setStyleClass("ui-corner-all");
            gru_botones_dialogo.setStyle("height:25px;width:97%;padding-top: 2px;padding-bottom: 2px;display:block;padding-left: 10px;padding-right: 10px");
            gru_botones_dialogo.getChildren().add(bot_inicio2);
            gru_botones_dialogo.getChildren().add(bot_atras2);
            gru_botones_dialogo.getChildren().add(bot_siguiente2);
            gru_botones_dialogo.getChildren().add(bot_fin2);
            gru_botones_dialogo.getChildren().add(esp_espacio_dialogo);
            gru_botones_dialogo.getChildren().add(bot_eliminar);
            gru_botones_dialogo.getChildren().add(bot_agregar);

            gri_cuerpo.getChildren().add(gru_botones_dialogo);
            gri_cuerpo.getChildren().add(tab_tabla2);

            Grid gri_cuerpo_media = new Grid();
            gri_cuerpo_media.setWidth("100%");
            med_archivo.setWidth("800");
            med_archivo.setHeight("350");
            med_archivo.setStyle("");
            med_archivo.setId("med_archivo");
            gri_cuerpo.getChildren().add(med_archivo);
            dia_dialogo.getBot_aceptar().setMetodo("aceptarDialogo");
            dia_dialogo.setDialogo(gri_cuerpo);  // aqui estaba gri_cuerpo
            agregarComponente(dia_dialogo);

            //DIALGO SUBIR ARCHIVO
            // grid carga texto
            Grid gri_texto = new Grid();
            gri_texto.setId("gri_texto");
            gri_texto.setColumns(2);
            txt_observaciones.setId("txt_observaciones");
            txt_observaciones.setSize(60);
            txt_observaciones.setMaxlength(199);
            txt_observaciones.setMetodoKeyPress("autocompletarObservacion");
            com_documento_req.setId("com_documento_req");
            com_documento_req.setCombo(ser_estructura.getDocumentoRequerido("true"));
            com_documento_req.setMetodo("cargarDocumento");

            Grid gri_upload_header = new Grid();

            Grid gri_upload_content = new Grid();
            gri_upload_content.setColumns(4);

            gri_upload_content.getChildren().add(new Etiqueta("Documento rquerido: "));
            gri_upload_content.getChildren().add(com_documento_req);
            gri_upload_content.getChildren().add(new Etiqueta("Detalle Archivo: "));
            gri_upload_content.getChildren().add(txt_observaciones);
            gri_upload_header.getChildren().add(gri_upload_content);

            gri_texto.getChildren().add(gri_upload_header);
            Grid gri_cuerpo_archivo = new Grid();

            Grid gri_impo = new Grid();
            gri_impo.setColumns(1);

            //tamaño del archivo para subir
            int size_upload = Integer.parseInt(utilitario.getVariable("p_tamanio_archivo"));
            //int size_upload = 2000000;
            upl_archivo.setId("upl_archivo");
            upl_archivo.setMetodo("validarArchivo");
            upl_archivo.setUpdate("gri_valida,gri_texto");
            upl_archivo.setAuto(false);
            upl_archivo.setAllowTypes("/(\\.|\\/)(pdf)$/");
            upl_archivo.setUploadLabel("Subir archivo");
            upl_archivo.setSizeLimit(size_upload);
            upl_archivo.setCancelLabel("Cancelar Seleccion");

            gri_impo.getChildren().add(upl_archivo);
            gri_impo.setWidth("100%");

            Grid gri_valida = new Grid();
            gri_valida.setId("gri_valida");
            gri_valida.setColumns(3);

            Etiqueta eti_valida = new Etiqueta();
            eti_valida.setValueExpression("value", "pre_index.clase.upl_archivo.nombreReal");
            eti_valida.setValueExpression("rendered", "pre_index.clase.upl_archivo.nombreReal != null");
            gri_valida.getChildren().add(eti_valida);
            Imagen ima_valida = new Imagen();
            ima_valida.setWidth("22");
            ima_valida.setHeight("22");
            ima_valida.setValueExpression("rendered", "pre_index.clase.upl_archivo.nombreReal != null");
            gri_valida.getChildren().add(ima_valida);

            Etiqueta eti_info = new Etiqueta();
            eti_info.setValue("Los campos documento requerido y detalle archivo son obligatorios.");
            eti_info.setStyle("font-size: 10px;font-weight: bold;color: red;");

            gri_cuerpo_archivo.setStyle("width:" + (dia_importar.getAnchoPanel() - 5) + "px;");
            gri_cuerpo_archivo.setMensajeInfo("Debe seleccionar un archivo pdf, de máximo " + ser_estructura.getConvertBitsAMegabits(utilitario.getVariable("p_tamanio_archivo")) + " MB");
            gri_cuerpo_archivo.getChildren().add(eti_info);
            gri_cuerpo_archivo.getChildren().add(gri_texto);
            gri_cuerpo_archivo.getChildren().add(gri_impo);
            gri_cuerpo_archivo.getChildren().add(gri_valida);
            gri_cuerpo_archivo.getChildren().add(new Espacio("0", "10"));
            dia_importar.setId("dia_importar");
            dia_importar.setDialogo(gri_cuerpo_archivo);
            dia_importar.getBot_aceptar().setRendered(false);
            dia_importar.setHeight("40%");
            // dia_importar.setDynamic(false); 

            agregarComponente(dia_importar);

            /**
             * **** VISUALIZAR ADJUNTOS DEL INFORME *****
             */
            dia_adjunto2.setId("dia_adjunto2");
            dia_adjunto2.setTitle("ARCHIVOS ADJUNTADOS DEL INFORME");
            dia_adjunto2.setWidth("65%");
            dia_adjunto2.setHeight("85%");
            dia_adjunto2.setResizable(false);

            tab_tabla3.setId("tab_tabla3");
            tab_tabla3.setTabla("yavirac_matri_documento_entrega", "ide_ymaden", 5);
            tab_tabla3.getColumna("ide_ymaden").setNombreVisual("CODIGO");
            tab_tabla3.setCampoOrden("ide_ymaden desc");
            tab_tabla3.getColumna("archivo_ymaden").setVisible(false);
            tab_tabla3.getColumna("archivo_ymaden").setNombreVisual("ARCHIVO");
            tab_tabla3.getColumna("observacion_ymaden").setNombreVisual("DETALLE ARCHIVO");
            //tab_tabla3.getColumna("observacion_ymaden").setLectura(true);
            tab_tabla3.getColumna("ide_ystdor").setNombreVisual("DOCUMENTO");
            tab_tabla3.getColumna("ide_ystdor").setCombo(ser_estructura.getDocumentoRequerido("true,false"));
            tab_tabla3.getColumna("fecha_ymden").setVisible(false);
            tab_tabla3.getColumna("entregado_ymaden").setVisible(false);
            tab_tabla3.getColumna("ide_ymamat").setVisible(false);
            tab_tabla3.setTipoFormulario(true);
            tab_tabla3.getGrid().setColumns(6);
            tab_tabla3.setRecuperarLectura(true);
            tab_tabla3.dibujar();

            Boton bot_inicio5 = new Boton();
            bot_inicio5.setTitle("Inicio");
            bot_inicio5.setMetodo("inicio5");
            bot_inicio5.setIcon("ui-icon-seek-first");
            bot_inicio5.setStyle("background: #0099CC;color:#fff ;border:#0099CC;");
            bot_inicio5.setStyleClass("pull-left");

            Boton bot_siguiente5 = new Boton();
            bot_siguiente5.setTitle("Siguiente");
            bot_siguiente5.setMetodo("siguiente5");
            bot_siguiente5.setIcon("ui-icon-seek-next");
            bot_siguiente5.setStyle("background: #0099CC;color:#fff ;border:#0099CC;");
            bot_siguiente5.setStyleClass("pull-left");

            Boton bot_atras5 = new Boton();
            bot_atras5.setTitle("Anterior");
            bot_atras5.setMetodo("atras5");
            bot_atras5.setIcon("ui-icon-seek-prev");
            bot_atras5.setStyle("background: #0099CC;color:#fff ;border:#0099CC;");
            bot_atras5.setStyleClass("pull-left");

            Boton bot_fin5 = new Boton();
            bot_fin5.setTitle("Fin");
            bot_fin5.setMetodo("fin5");
            bot_fin5.setIcon("ui-icon-seek-end");
            bot_fin5.setStyle("background: #0099CC; color:#fff ;border:#0099CC;");
            bot_fin5.setStyleClass("pull-left");

            PanelGrid gri_adjunto2 = new PanelGrid();
            gri_adjunto2.setColumns(1);
            gri_adjunto2.setStyle(" width: 100%;");

            Barra bar_menu5 = new Barra();
            bar_menu5.setId("bar_menu5");
            bar_menu5.limpiar();

            bar_menu5.agregarComponente(bot_inicio5);
            bar_menu5.agregarComponente(bot_atras5);
            bar_menu5.agregarComponente(bot_siguiente5);
            bar_menu5.agregarComponente(bot_fin5);

            gri_adjunto2.getChildren().add(bar_menu5);
            gri_adjunto2.getChildren().add(tab_tabla3);

            Grid gri_cuerpo_media2 = new Grid();
            gri_cuerpo_media2.setWidth("100%");
            med_archivo2.setWidth("800");
            med_archivo2.setHeight("350");
            med_archivo2.setStyle("");
            med_archivo2.setId("med_archivo2");
            gri_adjunto2.getChildren().add(med_archivo2);
            dia_adjunto2.getBot_aceptar().setRendered(false);
            dia_adjunto2.setDialogo(gri_adjunto2);
            agregarComponente(dia_adjunto2);

        } else {
            utilitario.agregarNotificacionInfo("ADVERTENCIA", "EL usuario ingresado no registra permisos para el registro de Matriculas. Consulte con el Administrador");
        }
    }

    public void aceptarDialogo() {
        tab_matriculas.setValor("recibido_ymamat", "true");
        tab_matriculas.modificar(tab_matriculas.getFilaActual());//para que haga el update
        utilitario.addUpdate("tab_matriculas,tab_documento_entregado");
        tab_matriculas.guardar();
        // tab_documento_entregado.guardar();
        guardarPantalla();
        dia_dialogo.cerrar();
    }

    String documento_requerido = "";

    /**
     * Metodo que permite visualizar los documentosadjuntados
     */
    public void visualizarAdjuntos() {
        if (tab_documento_entregado.getTotalFilas() > 0) {
            String codigo = tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ymamat");
            tab_tabla3.setCondicion("ide_ymamat=" + codigo);
            tab_tabla3.ejecutarSql();
            dia_adjunto2.dibujar();
            visualizarArchivo2();
        } else {
            utilitario.agregarMensajeInfo("Advertencia,", "No tiene documentos");
            return;
        }
    }

    byte[] contenido;

    public void cargarDocumento() {
        documento_requerido = com_documento_req.getValue().toString();
    }

    public void validarArchivo(FileUploadEvent event) throws Exception {
        utilitario.addUpdate("txt_observaciones");
        utilitario.addUpdate("com_documento_req");
        if (documento_requerido == null || documento_requerido.toString().isEmpty()) {
            utilitario.agregarMensajeInfo("No puede continuar,", "Debe seleccionar el documento requerido.");
            return;
        }
        if (observacion == null || observacion.toString().isEmpty()) {
            utilitario.agregarMensajeInfo("No puede continuar,", "Debe ingresar un detalle del archivo adjunto.");
            return;
        } else {
            // TablaGenerica tab_maximo = utilitario.consultar(ser_seguridad.getCodigoMaximo("tra_adjunto", "ide_traadj"));
            // int maximo = Integer.parseInt(tab_maximo.getValor("Codigo"));
            //System.out.println("observacion " + txt_observaciones.getValue());
            ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
            String str_path = extContext.getRealPath("/upload/matriculas/");
            String str_path_local = str_path + "/";
            String str_path_backup = utilitario.getVariable("p_path_matriculas") + "/matriculas/";
            UploadedFile file = event.getFile();
            contenido = IOUtils.toByteArray(file.getInputstream());
            String str_nombre = tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ymamat") + utilitario.getFechaActual().replace("-", "") + utilitario.getHoraActual().replace(":", "")
                    + event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf("."), event.getFile().getFileName().length());
            String path_local = str_path_local + str_nombre;
            String path_backup = str_path_backup + str_nombre;
            guardarArchivoRuta(path_local, contenido);
            guardarArchivoRuta(path_backup, contenido);

            //Almacena la ruta del archivo en la base
            tab_tabla2.insertar();
            tab_tabla2.setValor("ide_ymamat", tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ymamat"));
            tab_tabla2.setValor("archivo_ymaden", "/upload/matriculas/" + str_nombre);
            tab_tabla2.setValor("observacion_ymaden", observacion);
            tab_tabla2.setValor("fecha_ymden", utilitario.getFechaActual());
            tab_tabla2.setValor("ide_ystdor", documento_requerido);
            tab_tabla2.setValor("entregado_ymaden", "true");
            tab_tabla2.guardar();
            guardarPantalla();
            visualizarArchivo();
            tab_documento_entregado.actualizar();
            utilitario.ejecutarJavaScript("window.location.reload();");
            dia_importar.cerrar();

            //System.out.println("pat1 " + path_local);
            //System.out.println("pat2 " + path_backup);
        }
    }

    /**
     * metodo que permite almacenar los archivos en las rutas asignadas
     *
     * @param path_local ruta local del proyecto
     * @param path_backup ruta backup
     * @param contenido byte
     */
    public void guardarArchivoRuta(String path_local, byte[] contenido) throws IOException {
        FileOutputStream local = new FileOutputStream(path_local);
        local.write(contenido);
        local.close();
    }

    /**
     * permite almacenar el detalle del adjunto en una variable con el metodo
     * keypress
     */
    public void autocompletarObservacion() {
        observacion = txt_observaciones.getValue().toString();
        //System.out.println("observacion " + observacion);
    }

    /**
     * visualiza el componente med_archivo2
     */
    public void visualizarArchivo2() {
        String codigo = tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ymamat");
        String archivo = "/upload/matriculas/bienvenido.pdf";
        TablaGenerica tab_archivo = utilitario.consultar("select * from yavirac_matri_documento_entrega  where ide_ymamat  =" + tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ymamat") + " and not archivo_ymaden is null order by ide_ymaden desc");
        if (tab_archivo.getTotalFilas() > 0) {
            archivo = tab_archivo.getValor("archivo_ymaden");
        }
        med_archivo2.setValue(archivo);
        utilitario.addUpdate("med_archivo2");
    }

    public void visualizarArchivo() {
        String archivo = "/upload/matriculas/bienvenido.pdf";
        TablaGenerica tab_archivo = utilitario.consultar("select * from yavirac_matri_documento_entrega  where ide_ymamat  =" + tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ymamat") + " and not archivo_ymaden is null order by ide_ymaden desc");
        tab_archivo.imprimirSql();
        if (tab_archivo.getTotalFilas() > 0) {

            archivo = tab_tabla2.getValor("archivo_ymaden");
        }
        med_archivo.setValue(archivo);
        //System.out.println("med arhcivo " + med_archivo.getValue());
        utilitario.addUpdate("med_archivo");
    }

    public void subrirArchivo() {
        upl_archivo.limpiar();
        txt_observaciones.limpiar();
        observacion = "";
        dia_importar.dibujar();
    }

    public void limpiarAnular() {
        aut_alumno.limpiar();
        tab_tabla1.limpiar();
        are_detalle.setValue("");
        utilitario.addUpdate("are_detalle");
    }

    public void aceptarAnular() {
        String str_seleccionado = tab_tabla1.getFilasSeleccionadas();
        String mension = tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ystmen");
        // System.out.println("seleccionado => " + str_seleccionado);
        if (aut_alumno.getValor() == null) {
            utilitario.agregarMensajeInfo("No puede continuar", "Seleccione un estudiante.");
            return;
        }
        if (str_seleccionado == null || str_seleccionado.isEmpty()) {
            utilitario.agregarMensajeInfo("No puede continuar,", "Seleccione la matricula que desea anular.");
            return;
        }
        if (are_detalle.getValue() == null || are_detalle.getValue().toString().isEmpty()) {
            utilitario.agregarMensajeInfo("No puede continuar,", "Ingrese el detalle del motivo de anulacion de la matricula.");
            return;
        }
        utilitario.getConexion().ejecutarSql(ser_notas.getActualzarEstadoRecord(utilitario.getVariable("p_estado_anulado"), aut_alumno.getValor(), mension, com_periodo_academico.getValue().toString()));
        utilitario.getConexion().ejecutarSql(ser_matricula.updateAnularMatricula(str_seleccionado, are_detalle.getValue().toString() + "Fecha anulación: " + utilitario.getFechaHoraActual()));
        utilitario.agregarMensaje("Se anulo correctamente la matricula", "");
        tab_matriculas.actualizar();
        dia_anular.cerrar();
    }

    public void abrirAnular() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Académico que desea generar");
            return;
        }
        dia_anular.dibujar();
    }

    public void cargarMatricula() {
        tab_tabla1.setSql(ser_matricula.getMatriculasNoAnulados(aut_alumno.getValor(), com_periodo_academico.getValue().toString()));
        tab_tabla1.actualizar();
    }

    public void cerrarInfo() {
        con_informacion.cerrar();
    }

    public void cargarCarreraNivel() {

        String alumno = tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_yaldap");
        TablaGenerica tab_carrera = utilitario.consultar(ser_matricula.getNivelCarreraEstadoNota(alumno));
        // tab_carrera.imprimirSql();
        if (tab_carrera.getTotalFilas() > 0) {
            String malla = tab_carrera.getValor("ide_ystmen");
            int nivel_record = Integer.parseInt(tab_carrera.getValor("ide_ystnie"));
            String estado = tab_carrera.getValor("ide_ynoest");
            TablaGenerica tab_nivel = utilitario.consultar(ser_matricula.getUltimoNivelMalla(malla));
            if (estado.equals(utilitario.getVariable("p_estado_cursando"))) {
                message = "El estudiante esta cursando un nivel actualmente";
                con_informacion.setMessage(message);
                utilitario.addUpdate("con_informacion");
                con_informacion.dibujar();
                return;
            } else if (estado.equals(utilitario.getVariable("p_estado_reprobado"))) {
                // cargo nivel
                tab_matriculas.setValor("ide_ystnie", "" + nivel_record);
                tab_matriculas.setValor("ide_ystmen", "" + malla);
                tab_matriculas.getColumna("ide_ystmen").setLectura(true);
                utilitario.addUpdateTabla(tab_matriculas, "ide_ystmen,ide_ystnie", "");
                // tab_matriculas.actualizar();  
            } else if (nivel_record < Integer.parseInt(tab_nivel.getValor("ide_ystnie"))) {
                // valido el nivel del record con el nivel de la malla 
                int nivel = (nivel_record + 1);
                tab_matriculas.setValor("ide_ystnie", "" + nivel);
                tab_matriculas.setValor("ide_ystmen", "" + malla);
                tab_matriculas.getColumna("ide_ystmen").setLectura(true);
                utilitario.addUpdateTabla(tab_matriculas, "ide_ystmen,ide_ystnie", "");
            } else {
                message = "El estudiante seleccionado ya finalizo la carrera";
                con_informacion.setMessage(message);
                utilitario.addUpdate("con_informacion");
                con_informacion.dibujar();
            }
        } else {
            message = "El estudiante no tiene registros en el record académico, registre al nuevo estudiante.";
            con_informacion.setMessage(message);
            utilitario.addUpdate("con_informacion");
            con_informacion.dibujar();
            tab_matriculas.setValor("ide_ystnie", utilitario.getVariable("p_primer_nivel"));
            tab_matriculas.setValor("ide_ystmen", "");
            tab_matriculas.getColumna("ide_ystmen").setLectura(false);
            utilitario.addUpdateTabla(tab_matriculas, "ide_ystmen,ide_ystnie", "");
        }
        // tab_carrera.imprimirSql();
    }

    public void bloquearEditarALumno() {
        if (tab_registro_credito.getTotalFilas() < 0) {
            tab_matriculas.getColumna("ide_yaldap").setLectura(true);
        } else {
            tab_matriculas.getColumna("ide_yaldap").setLectura(false);
        }
    }

    /**
     * Bloquea la modificacion de la cabecera de la matricula y tambien no
     * permite eliminar las materias registradas
     */
    public void bloquearModificacion() {
        //BLOQUEOS LECTURA
        if (tab_matriculas.getTotalFilas() > 0) {
            if (tab_matriculas.getValor("aprobado_ymamat").equals("true")) {
                tab_matriculas.getFilaSeleccionada().setLectura(true);
                utilitario.addUpdate("tab_matriculas");
            } else {
                tab_matriculas.setLectura(false);
                utilitario.addUpdate("tab_matriculas");
            }
        }
    }

    /**
     * abre el metodo confirmar
     */
    public void aprobarMatricula() {
        if (tab_matriculas.isFilaInsertada()) {
            utilitario.agregarMensajeInfo("ADVERTENCIA ", "Guarde primero en el registro que esta trabajando");
            return;
        }
        if (tab_matriculas.getValor("aprobado_ymamat").equals("true")) {
            utilitario.agregarMensajeInfo("No puede continuar,", "La matricula se encuentra aprobada");
            return;
        }

        if (tab_registro_credito.getTotalFilas() > 0) {
            con_confirma.getBot_aceptar().setMetodo("registrarRecordAcademico");
            utilitario.addUpdate("con_confirma");
            con_confirma.dibujar();
        } else {
            utilitario.agregarMensajeInfo("No puede continuar,", "Debe registrar almenos una materia");
            return;
        }

    }

    /**
     * Metodo para insertar las materias
     */
    public void insertarMaterias() {
        if (tab_matriculas.isFilaInsertada()) {
            utilitario.agregarMensajeInfo("ADVERTENCIA ", "Guarde primero el registro en el que esta trabajando");
            return;
        } else {
            if (com_jornada.getValue() == null) {
                utilitario.agregarMensajeInfo("ADVERTENCIA ", "Seleccione la jornada");
                return;
            }
            if (com_paralelo.getValue() == null) {
                utilitario.agregarMensajeInfo("ADVERTENCIA ", "Seleccione el pararelo");
                return;
            }
            if (com_tipo_credito.getValue() == null) {
                utilitario.agregarMensajeInfo("ADVERTENCIA ", "Seleccione el tipo de crédito");
                return;
            }
            String condicion = tab_materias.getFilasSeleccionadas();
            if (condicion.equals("null") || condicion.isEmpty()) {
                utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione almenos un registro");
            } else {
                for (Fila filaActual : tab_materias.getListaFilasSeleccionadas()) {
                    /* tab_registro_credito.insertar();
                    tab_registro_credito.setValor("ide_ymamat", );
                    tab_registro_credito.setValor("ide_ystmal", "" + filaActual.getCampos()[0]);
                    tab_registro_credito.setValor("ide_ymanum", "" + filaActual.getCampos()[6]);
                    tab_registro_credito.setValor("ide_ymatrc", com_tipo_credito.getValue().toString());
                    tab_registro_credito.setValor("codigo_asignatura_ymarcm", "" + filaActual.getCampos()[3]);
                    tab_registro_credito.setValor("numero_de_creditos_ymarcm", "" + filaActual.getCampos()[4]);
                    tab_registro_credito.setValor("ide_yhogra", com_paralelo.getValue().toString());
                    tab_registro_credito.setValor("ide_ystjor", com_jornada.getValue().toString()); */
                    String ide_ymarec = ser_matricula.saveRegistroCredito(tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ymamat"), "" + filaActual.getCampos()[0], "" + filaActual.getCampos()[6], com_tipo_credito.getValue().toString(),
                            "" + filaActual.getCampos()[3], "" + filaActual.getCampos()[4], com_paralelo.getValue().toString(), com_jornada.getValue().toString());
                }
                //tab_registro_credito.guardar();
                guardarPantalla();
                tab_registro_credito.actualizar();
                dia_materias.cerrar();
            }
        }
    }

    public void abrirMaterias() {
        if (tab_matriculas.getTotalFilas() > 0) {

            if (tab_matriculas.getValor("ide_ystnie") == null || tab_matriculas.getValor("ide_ystnie").isEmpty()) {
                utilitario.agregarMensajeInfo("ADVERTENCIA ", "Seleccione el nivel académico");
                return;
            }
            if (tab_matriculas.getValor("ide_ystmen") == null || tab_matriculas.getValor("ide_ystmen").isEmpty()) {
                utilitario.agregarMensajeInfo("ADVERTENCIA ", "Selecciones la mesión o carrera");
                return;
            }
            if (tab_matriculas.isFilaInsertada()) {
                utilitario.agregarMensajeInfo("ADVERTENCIA ", "Guarde el registro que esta trabajando");
                return;
            }
            if (tab_matriculas.getValor("aprobado_ymamat").equals("true") || tab_matriculas.getValor("aprobado_ymamat").equals("null")) {
                utilitario.agregarMensajeInfo("No puede insertar materias,", "La matricula se encuentra aprobada");
                return;
            }
            tab_materias.setSql(ser_matricula.getMaterias(tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ystmen"),
                    tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ystnie"), tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_yaldap"),
                    com_periodo_academico.getValue().toString()));
            tab_materias.ejecutarSql();
            dia_materias.dibujar();
        } else {
            utilitario.agregarMensajeInfo("No se puede continuar ", "Registre una matricula");
            return;
        }

    }

    public void seleccionarTodas() {
        tab_materias.setSeleccionados(null);
        Fila seleccionados[] = new Fila[tab_materias.getTotalFilas()];
        for (int i = 0; i < tab_materias.getFilas().size(); i++) {
            seleccionados[i] = tab_materias.getFilas().get(i);
        }
        tab_materias.setSeleccionados(seleccionados);
    }

    public void seleccinarInversa() {
        if (tab_materias.getSeleccionados() == null) {
            seleccionarTodas();
        } else if (tab_materias.getSeleccionados().length == tab_materias.getTotalFilas()) {
            seleccionarNinguna();
        } else {
            Fila seleccionados[] = new Fila[tab_materias.getTotalFilas() - tab_materias.getSeleccionados().length];
            int cont = 0;
            for (int i = 0; i < tab_materias.getFilas().size(); i++) {
                boolean boo_selecionado = false;
                for (int j = 0; j < tab_materias.getSeleccionados().length; j++) {
                    if (tab_materias.getSeleccionados()[j].equals(tab_materias.getFilas().get(i))) {
                        boo_selecionado = true;
                        break;
                    }
                }
                if (boo_selecionado == false) {
                    seleccionados[cont] = tab_materias.getFilas().get(i);
                    cont++;
                }
            }
            tab_materias.setSeleccionados(seleccionados);
        }
    }

    public void seleccionarNinguna() {
        tab_materias.setSeleccionados(null);
    }

    /**
     * Aprueba la matricula y genera el record académico
     */
    public void registrarRecordAcademico() {

        TablaGenerica tab_cabecera = utilitario.consultar(ser_notas.getConsultaCabeceraRecord(tab_matriculas.getValor("ide_yaldap"), tab_matriculas.getValor("ide_ystmen")));
        TablaGenerica tab_malla = utilitario.consultar(ser_notas.getConsultaMatricula(tab_matriculas.getValor("ide_ymamat")));

        if (tab_cabecera.getTotalFilas() > 0) {
            //INSERTANDO DETALLE RECORD ACADEMICO
            for (int i = 0; i < tab_malla.getTotalFilas(); i++) {
                String ide_ynodra = ser_notas.saveDetalleRecord(utilitario.getVariable("p_estado_cursando"), tab_cabecera.getValor("ide_ynocra"), tab_malla.getValor(i, "ide_ystmat"), tab_malla.getValor("ide_ystpea"), tab_malla.getValor(i, "ide_ymanum"), tab_malla.getValor(i, "ide_ymatrc"),
                        tab_malla.getValor(i, "ide_ystmal"), tab_malla.getValor(i, "codigo_ystmal"), tab_malla.getValor(i, "numero_credito_ystmal"), "0");
            }
        } else {
            //INSERTANDO CAABECERA RECORD ACADEMICO
            String ide_ynocra = ser_notas.saveCabeceraRecord(tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_yaldap"), tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ystmen"), tab_matriculas.getValor(tab_matriculas.getFilaActual(), "fecha_ymamat"));

            //INSERTANDO DETALLE RECORD ACADEMICO
            for (int i = 0; i < tab_malla.getTotalFilas(); i++) {
                String ide_ynodra = ser_notas.saveDetalleRecord(utilitario.getVariable("p_estado_cursando"), ide_ynocra, tab_malla.getValor(i, "ide_ystmat"), tab_malla.getValor("ide_ystpea"), tab_malla.getValor(i, "ide_ymanum"), tab_malla.getValor(i, "ide_ymatrc"),
                        tab_malla.getValor(i, "ide_ystmal"), tab_malla.getValor(i, "codigo_ystmal"), tab_malla.getValor(i, "numero_credito_ystmal"), "0");
            }
        }
        guardarPantalla();
        // ACTUALIZO EL CAMPO APROBADO A TRUE 
        utilitario.getConexion().ejecutarSql("update yavirac_matri_matricula set aprobado_ymamat=true where ide_ymamat=" + tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ymamat"));
        tab_matriculas.actualizar();
        con_confirma.cerrar();
        bloquearModificacion();
    }

    public void generarPDF() {
        if (tab_matriculas.isFilaInsertada()) {
            utilitario.agregarMensajeInfo("ADVERTENCIA ", "Guarde primero en el registro que esta trabajando");
            return;
        } else if (tab_matriculas.getValorSeleccionado() != null) {
            ///////////AQUI ABRE EL REPORTE
            Map map_parametros = new HashMap();
            map_parametros.put("pide_ins", Integer.parseInt(tab_matriculas.getValor("ide_ymamat")));
            map_parametros.put("nombre", utilitario.getVariable("NICK"));

            // System.out.println(" " + map_parametros);
            //vipdf_comprobante.setVisualizarPDF(data, docente, map_parametros);
            vipdf_comprobante.setVisualizarPDF("rep_matricula/rep_certificado_matricula.jasper", map_parametros);
            vipdf_comprobante.dibujar();
            utilitario.addUpdate("vipdf_comprobante");
        } else {
            utilitario.agregarMensajeInfo("Seleccione una Matricula", "");
        }
    }

    public void actualizaAlumno() {
        String str_clienteActualizado = sel_actualiza_alumno.getValorSeleccionado();
        if (str_clienteActualizado != null) {
            tab_matriculas.modificar(tab_matriculas.getFilaActual());
            tab_matriculas.setValor("ide_yaldap", str_clienteActualizado);
            utilitario.addUpdate("tab_pre_inscrip");
            con_guardar_alumno.setMessage("Esta Seguro de Actualizar el Alumno");
            con_guardar_alumno.setTitle("Confirmación de actualizar");
            con_guardar_alumno.getBot_aceptar().setMetodo("guardarActualizarAlumno");
            con_guardar_alumno.dibujar();
            utilitario.addUpdate("con_guardar_alumno");
        }

    }

    public void guardarActualizarAlumno() {
        tab_matriculas.guardar();
        con_guardar_alumno.cerrar();
        sel_actualiza_alumno.cerrar();
        guardarPantalla();
    }

    public void registraAlumno() {
        String str_seleccionado = sel_registra_alumno.getValorSeleccionado();
        if (str_seleccionado != null) {
            //Inserto los cleintes seleccionados en la tabla  
            if (tab_matriculas.isFilaInsertada() == false) {
                //Controla que si ya esta insertada no vuelva a insertar
                tab_matriculas.insertar();
            }
            tab_matriculas.setValor("ide_ymaper", com_periodo_academico.getValue().toString());
            tab_matriculas.setValor("ide_ypedpe", ide_docente);
            tab_matriculas.setValor("ide_yaldap", str_seleccionado);
            tab_matriculas.modificar(tab_matriculas.getFilaActual());//para que haga el update

            sel_registra_alumno.cerrar();
            utilitario.addUpdate("tab_pre_inscrip");
        } else {
            utilitario.agregarMensajeInfo("Debe seleccionar al menos un registro", "");
        }
    }

    public void selregistraAlumno() {

        //Hace aparecer el componente
        if (com_periodo_academico.getValue() == null) {

            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Académico que desea generar");
            return;
        } else {
            sel_registra_alumno.getTab_seleccion().setSql(ser_alumno.getDatosAlumnos("true"));
            sel_registra_alumno.getTab_seleccion().ejecutarSql();
            sel_registra_alumno.dibujar();
        }

    }

    public void selactualizaAlumno() {

        //Hace aparecer el componente
        if (com_periodo_academico.getValue() == null) {

            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Académico que desea generar");
            return;
        } else {
            if (tab_matriculas.getValor("aprobado_ymamat").equals("true")) {
                utilitario.agregarMensajeInfo("No puede continuar,", "La matricula se encuentra aprobada");
                return;
            }
            sel_actualiza_alumno.getTab_seleccion().setSql(ser_alumno.getDatosAlumnos("true"));
            sel_actualiza_alumno.getTab_seleccion().ejecutarSql();
            sel_actualiza_alumno.dibujar();
        }

    }

    public void eliminarArchivo() {
        if (tab_tabla2.getTotalFilas() > 0) {
            tab_tabla2.eliminar();
            //tab_tabla2.actualizar();
            visualizarArchivo();
            utilitario.ejecutarJavaScript("window.location.reload();");
        }
    }

    public void recibeDocumento() {
        if (tab_matriculas.getTotalFilas() < 0) {
            utilitario.agregarMensajeInfo("No puede continuar,", "No tiene ninguna matricula para recibir documentos.");
            return;
        }
        if (tab_matriculas.getValor("recibido_ymamat").equals("false")) {
            // TablaGenerica tab_documento = utilitario.consultar(ser_estructura.getDocumentoRequeridoPeriodo(com_periodo_academico.getValue().toString(), par_modulo_matricula));
            // utilitario.getConexion().ejecutarSql(ser_estructura.deleteBloqueos(utilitario.getVariable("IDE_USUA")));
            TablaGenerica tab_documento = utilitario.consultar(ser_estructura.getDocumentoRequerido("true"));
            if (tab_documento.getTotalFilas() > 0) {
                /*for (int i = 0; i < tab_documento.getTotalFilas(); i++) {
                    tab_documento_entregado.insertar();
                    tab_documento_entregado.setValor("ide_ymamat", tab_matriculas.getValor("ide_ymamat"));
                    tab_documento_entregado.setValor("ide_ystdor", tab_documento.getValor(i, "ide_ystdor"));
                    tab_documento_entregado.setValor("entregado_ymaden", "true");

                }
                tab_matriculas.setValor("recibido_ymamat", "true");
                tab_matriculas.modificar(tab_matriculas.getFilaActual());//para que haga el update
                utilitario.addUpdate("tab_matriculas,tab_documento_entregado");
                tab_matriculas.guardar();
                tab_documento_entregado.guardar();
                guardarPantalla();*/

                // subir archivos
                String codigo = tab_matriculas.getValor(tab_matriculas.getFilaActual(), "ide_ymamat");
                tab_tabla2.setCondicion("ide_ymamat=" + codigo);
                tab_tabla2.ejecutarSql();
                visualizarArchivo();
                dia_dialogo.dibujar();
            } else {
                utilitario.agregarMensajeInfo("No existe registros configurados", "Favor configurar documentos requeridos");
            }
        } else {
            utilitario.agregarMensajeError("No puede recibir Documentos", "Usted ya ejecuto la opción recibir documentos, y no puede volver a ejecutarle pues recibirlo individualmente si lo deses");
        }
    }

    public void filtroComboPeriodoAcademnico() {

        tab_matriculas.setCondicion("ide_ymaper=" + com_periodo_academico.getValue().toString());
        tab_matriculas.ejecutarSql();
        tab_registro_credito.ejecutarSql();
        tab_documento_entregado.ejecutarSql();
        utilitario.addUpdate("tab_pre_inscrip");
        bloquearModificacion();
        bloquearEditarALumno();
        // visualizarArchivo();  
        // visualizarArchivo2(); 
    }

    public void limpiar() {
        tab_matriculas.limpiar();
        com_periodo_academico.setValue("null");
        utilitario.addUpdate("tab_matriculas,com_periodo_academico");
    }
    String docente = "";
    String documento = "";
    String ide_docente = "";

    private boolean tienePerfiMatricula() {
        List sql = utilitario.getConexion().consultar(ser_estructura.getUsuarioSistema(utilitario.getVariable("IDE_USUA"), " and not ide_ypedpe is null"));

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

    public Tabla getTab_documento_entregado() {
        return tab_documento_entregado;
    }

    public void setTab_documento_entregado(Tabla tab_documento_entregado) {
        this.tab_documento_entregado = tab_documento_entregado;
    }

    public Tabla getTab_registro_credito() {
        return tab_registro_credito;
    }

    public void setTab_registro_credito(Tabla tab_registro_credito) {
        this.tab_registro_credito = tab_registro_credito;
    }

    @Override
    public void insertar() {
        if (tab_matriculas.isFocus()) {
            if (com_periodo_academico.getValue() == null) {
                utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Académico que desea generar");
                return;
            } else {
                tab_matriculas.insertar();
                tab_matriculas.setValor("ide_ymaper", com_periodo_academico.getValue().toString());
                tab_matriculas.setValor("ide_ypedpe", ide_docente);
                // tab_matriculas.modificar(tab_matriculas.getFilaActual());//para que haga el update
            }
        } else if (tab_documento_entregado.isFocus()) {
            tab_documento_entregado.insertar();
        } else if (tab_registro_credito.isFocus()) {
            tab_registro_credito.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_matriculas.isFilaInsertada()) {
            tab_matriculas.setValor("nro_folio_ymamat", ser_matricula.getSecuencialFolio());
        } 
        if (tab_matriculas.guardar()) {
            if (tab_documento_entregado.guardar()) {
                if (tab_registro_credito.guardar()) {
                    guardarPantalla();
                    // registrarRecordAcademico();
                }
            }
        }

    }

    @Override
    public void eliminar() {
        if (tab_matriculas.isFocus()) {
            tab_matriculas.eliminar();
        } else if (tab_documento_entregado.isFocus()) {
            tab_documento_entregado.eliminar();
        } else if (tab_registro_credito.isFocus()) {
            if (tab_matriculas.getValor("aprobado_ymamat").equals("true")) {
                utilitario.agregarMensajeInfo("No puede elimnar,", "La matricula se encuentra aprobada");
                return;
            }
            tab_registro_credito.eliminar();

        }
    }

    public void atras2() {
        tab_tabla2.atras();
        visualizarArchivo();
        utilitario.ejecutarJavaScript("window.location.reload();");
    }

    public void siguiente2() {
        tab_tabla2.siguiente();
        visualizarArchivo();
        utilitario.ejecutarJavaScript("window.location.reload();");
    }

    public void fin2() {
        tab_tabla2.fin();
        visualizarArchivo();
        utilitario.ejecutarJavaScript("window.location.reload();");
    }

    public void inicio2() {
        tab_tabla2.inicio();
        visualizarArchivo();
        utilitario.ejecutarJavaScript("window.location.reload();");
    }

    public void atras5() {
        tab_tabla3.atras();
        visualizarArchivo2();
        utilitario.ejecutarJavaScript("window.location.reload();");
    }

    public void siguiente5() {
        tab_tabla3.siguiente();
        visualizarArchivo2();
        utilitario.ejecutarJavaScript("window.location.reload();");
    }

    public void fin5() {
        tab_tabla3.fin();
        visualizarArchivo2();
        utilitario.ejecutarJavaScript("window.location.reload();");
    }

    public void inicio5() {
        tab_tabla3.inicio();
        visualizarArchivo2();
        utilitario.ejecutarJavaScript("window.location.reload();");
    }

    @Override
    public void actualizar() {
        super.actualizar();
        bloquearModificacion();
        bloquearEditarALumno();
    }

    @Override
    public void atras() {
        super.atras();
        bloquearModificacion();
        bloquearEditarALumno();
    }

    @Override
    public void siguiente() {
        super.siguiente();
        bloquearModificacion();
        bloquearEditarALumno();
    }

    @Override
    public void fin() {
        super.fin();
        bloquearModificacion();
        bloquearEditarALumno();
    }

    @Override
    public void inicio() {
        super.inicio();
        bloquearModificacion();
        bloquearEditarALumno();
    }

    public Tabla getTab_matriculas() {
        return tab_matriculas;
    }

    public Tabla getTab_materias() {
        return tab_materias;
    }

    public void setTab_materias(Tabla tab_materias) {
        this.tab_materias = tab_materias;
    }

    public void setTab_matriculas(Tabla tab_matriculas) {
        this.tab_matriculas = tab_matriculas;
    }

    public SeleccionTabla getSel_registra_alumno() {
        return sel_registra_alumno;
    }

    public void setSel_registra_alumno(SeleccionTabla sel_registra_alumno) {
        this.sel_registra_alumno = sel_registra_alumno;
    }

    public SeleccionTabla getSel_actualiza_alumno() {
        return sel_actualiza_alumno;
    }

    public void setSel_actualiza_alumno(SeleccionTabla sel_actualiza_alumno) {
        this.sel_actualiza_alumno = sel_actualiza_alumno;
    }

    public SeleccionTabla getSel_materias() {
        return sel_materias;
    }

    public void setSel_materias(SeleccionTabla sel_materias) {
        this.sel_materias = sel_materias;
    }

    public Confirmar getCon_guardar_alumno() {
        return con_guardar_alumno;
    }

    public void setCon_guardar_alumno(Confirmar con_guardar_alumno) {
        this.con_guardar_alumno = con_guardar_alumno;
    }

    public VisualizarPDF getVipdf_comprobante() {
        return vipdf_comprobante;
    }

    public void setVipdf_comprobante(VisualizarPDF vipdf_comprobante) {
        this.vipdf_comprobante = vipdf_comprobante;
    }

    public Tabla getTab_detalle_record() {
        return tab_detalle_record;
    }

    public void setTab_detalle_record(Tabla tab_detalle_record) {
        this.tab_detalle_record = tab_detalle_record;
    }

    public Tabla getTab_cabecera_record() {
        return tab_cabecera_record;
    }

    public void setTab_cabecera_record(Tabla tab_cabecera_record) {
        this.tab_cabecera_record = tab_cabecera_record;
    }

    public Tabla getTab_tabla1() {
        return tab_tabla1;
    }

    public void setTab_tabla1(Tabla tab_tabla1) {
        this.tab_tabla1 = tab_tabla1;
    }

    public AutoCompletar getAut_alumno() {
        return aut_alumno;
    }

    public void setAut_alumno(AutoCompletar aut_alumno) {
        this.aut_alumno = aut_alumno;
    }

    public Tabla getTab_tabla2() {
        return tab_tabla2;
    }

    public void setTab_tabla2(Tabla tab_tabla2) {
        this.tab_tabla2 = tab_tabla2;
    }

    public Upload getUpl_archivo() {
        return upl_archivo;
    }

    public void setUpl_archivo(Upload upl_archivo) {
        this.upl_archivo = upl_archivo;
    }

    public Media getMed_archivo() {
        return med_archivo;
    }

    public void setMed_archivo(Media med_archivo) {
        this.med_archivo = med_archivo;
    }

    public Tabla getTab_tabla3() {
        return tab_tabla3;
    }

    public void setTab_tabla3(Tabla tab_tabla3) {
        this.tab_tabla3 = tab_tabla3;
    }

    public Media getMed_archivo2() {
        return med_archivo2;
    }

    public void setMed_archivo2(Media med_archivo2) {
        this.med_archivo2 = med_archivo2;
    }

}

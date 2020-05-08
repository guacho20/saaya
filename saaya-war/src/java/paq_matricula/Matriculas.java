package paq_matricula;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Confirmar;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import framework.componentes.VisualizarPDF;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
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
    private Combo com_periodo_academico = new Combo();
    private Boton bot_clean = new Boton();
    private SeleccionTabla sel_registra_alumno = new SeleccionTabla();
    private SeleccionTabla sel_actualiza_alumno = new SeleccionTabla();
    private Confirmar con_guardar_alumno = new Confirmar();
    public static String par_modulo_matricula;
    private VisualizarPDF vipdf_comprobante = new VisualizarPDF();
    private Conexion conOracle = new Conexion();

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

            bot_clean.setIcon("ui-icon-cancel");
            bot_clean.setTitle("Limpiar");
            bot_clean.setMetodo("limpiar");
            bar_botones.agregarComponente(bot_clean);

            //bar_botones.agregarBoton(bot_anular);
            Boton bot_imprimir = new Boton();
            bot_imprimir.setIcon("ui-icon-print");
            bot_imprimir.setValue("CERTIFICADO MATRÍCULA");
            bot_imprimir.setMetodo("generarPDF");

            bar_botones.agregarBoton(bot_imprimir);

            Tabulador tab_tabulador = new Tabulador();
            tab_tabulador.setId("tab_tabulador");

            tab_matriculas.setId("tab_matriculas");// todo objeto instanciado poner id 
            tab_matriculas.setTabla("yavirac_matri_matricula", "ide_ymamat", 1);  // nombre de la base de datos ii la clave primaria
            tab_matriculas.setHeader("REGISTRO DE MATRÍCULAS");
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
            tab_matriculas.getColumna("ide_ystocu").setNombreVisual("OCUPACIÓN");
            tab_matriculas.getColumna("ide_ystmen").setNombreVisual("MENSIÓN");
            tab_matriculas.getColumna("estudia_otro_lugar_ymamat").setNombreVisual("ESTUDIA APARTE");
            tab_matriculas.getColumna("semestre_otro_lugar_ymamat").setNombreVisual("SEMESTRE");
            tab_matriculas.getColumna("donde_tabaja_ymamat").setNombreVisual("DONDE TRABAJA");
            tab_matriculas.getColumna("recibido_ymamat").setNombreVisual("RECIBIDO");
            tab_matriculas.getColumna("ide_ymaper").setNombreVisual("PERIODO MATRÍCULA");
            tab_matriculas.getColumna("ide_yaldap").setNombreVisual("DATO ALUMNO");
            tab_matriculas.getColumna("ide_ystins").setNombreVisual("INSTITUCIÓN");
            tab_matriculas.getColumna("ide_ymatma").setNombreVisual("MATRÍCULA");
            tab_matriculas.getColumna("fecha_ymamat").setNombreVisual("FECHA MATRÍCULA");
            tab_matriculas.getColumna("carrera_otro_lugar_ymamat").setNombreVisual("OTRA CARRERA");
            tab_matriculas.getColumna("trabaja_ymamat").setNombreVisual("TRABAJA");
            tab_matriculas.getColumna("telefono_trabaja_ymamat").setNombreVisual("TELÉFONO TRABAJO");
            tab_matriculas.dibujar();//dibuja la tabla

            PanelTabla pa_matriculas = new PanelTabla();//intanciamos el panel del framework
            pa_matriculas.setId("pa_matriculas");//nombre id
            pa_matriculas.setPanelTabla(tab_matriculas);//agregar a nuestra tabla el panel

            //*** tabulacion del documento entregado
            tab_documento_entregado.setId("tab_documento_entregado");// todo objeto instanciado poner id 
            tab_documento_entregado.setTabla("yavirac_matri_documento_entrega", "ide_ymaden", 2);  // nombre de la base de datos ii la clave primaria
            tab_documento_entregado.setHeader("DOCUMENTOS ENTREGADOS");
            //***************************************************************************************************
            tab_documento_entregado.getColumna("ide_ymaden").setNombreVisual("CÓDIGO");
            tab_documento_entregado.getColumna("ide_ystdor").setNombreVisual("DOCUMENTO REQUERIDO");
            tab_documento_entregado.getColumna("fecha_ymden").setNombreVisual("FECHA");
            tab_documento_entregado.getColumna("observacion_ymaden").setNombreVisual("OBSERVACIÓN");
            tab_documento_entregado.getColumna("entregado_ymaden").setNombreVisual("ENTREGADO");
            tab_documento_entregado.dibujar();//dibuja la tabla
            PanelTabla pa_documento_entregado = new PanelTabla();//intanciamos el panel del framework
            pa_documento_entregado.setId("pa_documento_entregado");//nombre id
            pa_documento_entregado.setPanelTabla(tab_documento_entregado);//agregar a nuestra tabla el panel
            tab_documento_entregado.setIdCompleto("tab_tabulador:tab_documento_entregado");

            //*** tabulador de registro de credito
            tab_registro_credito.setId("tab_registro_credito");// todo objeto instanciado poner id 
            tab_registro_credito.setTabla("yavirac_matri_registro_credito", "ide_ymarec", 3);  // nombre de la base de datos ii la clave primaria
            tab_registro_credito.setHeader("REGISTRO DE CREDITOS");
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
            tab_registro_credito.getColumna("ide_ymanum").setNombreVisual("NUMERO MATRICULA");
            tab_registro_credito.getColumna("ide_ystmal").setNombreVisual("MALLA");
            tab_registro_credito.getColumna("codigo_asignatura_ymarcm").setNombreVisual("CÓDIGO ASIGNATURA");
            tab_registro_credito.getColumna("numero_de_creditos_ymarcm").setNombreVisual("NUMERO CRÉDITO");
            tab_registro_credito.getColumna("observacion_ymarec").setNombreVisual("OBSERVACIÓN");
            tab_registro_credito.getColumna("ide_yhogra").setCombo(ser_horarios.getGrupoAcademico());
            tab_registro_credito.getColumna("ide_yhogra").setAncho(-1);
            tab_registro_credito.getColumna("ide_yhogra").setLongitud(-1);
            tab_registro_credito.getColumna("ide_yhogra").setNombreVisual("PARALELO");
            tab_registro_credito.getColumna("ide_ystjor").setCombo(ser_estructura.getJornada("true,false"));
            tab_registro_credito.getColumna("ide_ystjor").setAncho(-1);
            tab_registro_credito.getColumna("ide_ystjor").setLongitud(-1);
            tab_registro_credito.getColumna("ide_ystjor").setNombreVisual("JORNADA");
            tab_registro_credito.dibujar();//dibuja la tabla

            PanelTabla pa_registro_credito = new PanelTabla();//intanciamos el panel del framework
            pa_registro_credito.setId("pa_documento_entregado");//nombre id
            pa_registro_credito.setPanelTabla(tab_registro_credito);//agregar a nuestra tabla el panel
            tab_registro_credito.setIdCompleto("tab_tabulador:tab_registro_credito");

            //*******************************AGREGA PESTAÑANAS*********************************************//
            tab_tabulador.agregarTab("REGISTRO DE CRÉDITOS", pa_registro_credito);
            tab_tabulador.agregarTab("DOCUMENTOS ENTREGADOS", pa_documento_entregado);

            //instanciar una division del framework
            Division div_matriculas = new Division();//instanciamos
            div_matriculas.setId("div_matriculas");//es un idientificador
            div_matriculas.dividir2(pa_matriculas, tab_tabulador, "50%", "H");//dividir2 sirve para la division de pantallas maximo3
            //ponemos el principal luego el tabulador,el porcentaje de cuanto va hacer la pantalla,el tipo horizontal o vertical

            agregarComponente(div_matriculas);//agregar componente

            //BOTON REGISTRO DE ALUMNOS
            Boton bot_registroAlumno = new Boton();
            bot_registroAlumno.setValue("Listado Alumnos");
            bot_registroAlumno.setIcon("ui-icon-note");
            bot_registroAlumno.setMetodo("selregistraAlumno");
            bar_botones.agregarBoton(bot_registroAlumno);

            //BOTON ACTUALIZAR DE ALUMNOS
            Boton bot_actualizaAlumno = new Boton();
            bot_actualizaAlumno.setValue("Actualizar Alumno");
            bot_actualizaAlumno.setIcon("ui-icon-refresh");
            bot_actualizaAlumno.setMetodo("selactualizaAlumno");
            bar_botones.agregarBoton(bot_actualizaAlumno);
            //BOTON RECEPCION DE DOCUMENTOS
            Boton bot_recibe_documento = new Boton();
            bot_recibe_documento.setValue("Recibir Documentos");
            bot_recibe_documento.setIcon("ui-icon-clipboard");
            bot_recibe_documento.setMetodo("recibeDocumento");
            bar_botones.agregarBoton(bot_recibe_documento);

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

            con_guardar_alumno.setId("con_guardar_alumno");
            agregarComponente(con_guardar_alumno);

            vipdf_comprobante.setId("vipdf_comprobante");
            vipdf_comprobante.setTitle("CERTIFICADO DE MATRICULA");
            agregarComponente(vipdf_comprobante);

            tab_cabecera_record.setId("tab_cabecera_record");
            tab_cabecera_record.setTabla("yavirac_nota_cab_rec_acad", "ide_ynocra", 4);

            tab_detalle_record.setId("tab_detalle_record");
            tab_detalle_record.setTabla("yavirac_nota_det_rec_acad", "ide_ynodra", 5);

        } else {
            utilitario.agregarNotificacionInfo("ADVERTENCIA", "EL usuario ingresado no registra permisos para el registro de Matriculas. Consulte con el Administrador");
        }
    }

    public void registrarRecordAcademico() {

        tab_cabecera_record.limpiar();
        tab_detalle_record.limpiar();
        TablaGenerica tab_cabecera = utilitario.consultar(ser_notas.getConsultaCabeceraRecord(tab_matriculas.getValor("ide_yaldap"), tab_matriculas.getValor("ide_ystmen")));
        TablaGenerica tab_malla = utilitario.consultar(ser_notas.getConsultaMatricula(tab_matriculas.getValor("ide_ymamat")));
        //tab_malla.imprimirSql();
        if (tab_registro_credito.getTotalFilas() > 0) {
            if (tab_cabecera.getTotalFilas() > 0) {

                //System.out.println("METODO VERDADERO");
                for (int i = 0; i < tab_malla.getTotalFilas(); i++) {
                    //System.out.println("FOR 1: " + i);
                    tab_detalle_record.insertar();
                    tab_detalle_record.setValor("ide_ynoest", utilitario.getVariable("p_estado_cursando"));
                    tab_detalle_record.setValor("ide_ynocra", tab_cabecera.getValor("ide_ynocra"));
                    tab_detalle_record.setValor("ide_ystmat", tab_malla.getValor(i, "ide_ystmat"));
                    tab_detalle_record.setValor("ide_ystpea", tab_malla.getValor("ide_ystpea"));
                    tab_detalle_record.setValor("ide_ymanum", tab_malla.getValor(i, "ide_ymanum"));
                    tab_detalle_record.setValor("ide_ymatrc", tab_malla.getValor(i, "ide_ymatrc"));
                    tab_detalle_record.setValor("ide_ystmal", tab_malla.getValor(i, "ide_ystmal"));
                    tab_detalle_record.setValor("codigo_mate_ynodra", tab_malla.getValor(i, "codigo_ystmal"));
                    tab_detalle_record.setValor("num_creditos_ynodra", tab_malla.getValor(i, "numero_credito_ystmal"));
                    tab_detalle_record.setValor("nota_ynodra", "0");
                    //tab_detalle_record.setValor("observacion_ynodra", tab_malla.getValor(i, "observacion_ymarec"));
                }
                tab_detalle_record.guardar();
                tab_cabecera.limpiar();
            } else {

                //System.out.println("METODO FALSO");
                //INSERTANDO CAABECERA RECORD ACADEMICO
                tab_cabecera_record.insertar();
                tab_cabecera_record.setValor("ide_yaldap", tab_matriculas.getValor("ide_yaldap"));
                tab_cabecera_record.setValor("ide_ystmen", tab_matriculas.getValor("ide_ystmen"));
                tab_cabecera_record.setValor("fecha_inicio_ynocra", tab_matriculas.getValor("fecha_ymamat"));
                tab_cabecera_record.guardar();

                //System.out.println("CABECERA CREADO ID " + tab_cabecera_record.getValor("ide_ynocra"));
                //INSERTANDO DETALLE RECORD ACADEMICO
                if (tab_registro_credito.getTotalFilas() > 0) {
                    for (int i = 0; i < tab_malla.getTotalFilas(); i++) {
                        //System.out.println("FOR 2: " + i);
                        tab_detalle_record.insertar();
                        tab_detalle_record.setValor("ide_ynoest", utilitario.getVariable("p_estado_cursando"));
                        tab_detalle_record.setValor("ide_ynocra", tab_cabecera_record.getValor("ide_ynocra"));
                        tab_detalle_record.setValor("ide_ystmat", tab_malla.getValor(i, "ide_ystmat"));
                        tab_detalle_record.setValor("ide_ystpea", tab_malla.getValor("ide_ystpea"));
                        tab_detalle_record.setValor("ide_ymanum", tab_malla.getValor(i, "ide_ymanum"));
                        tab_detalle_record.setValor("ide_ymatrc", tab_malla.getValor(i, "ide_ymatrc"));
                        tab_detalle_record.setValor("ide_ystmal", tab_malla.getValor(i, "ide_ystmal"));
                        tab_detalle_record.setValor("codigo_mate_ynodra", tab_malla.getValor(i, "codigo_ystmal"));
                        tab_detalle_record.setValor("num_creditos_ynodra", tab_malla.getValor(i, "numero_credito_ystmal"));
                        tab_detalle_record.setValor("nota_ynodra", "0");
                        //tab_detalle_record.setValor("observacion_ynodra", tab_malla.getValor(i, "observacion_ymarec"));
                    }
                    tab_detalle_record.guardar();
                }
            }
            guardarPantalla();
        }
    }

    public void generarPDF() {
        if (tab_matriculas.getValorSeleccionado() != null) {
            ///////////AQUI ABRE EL REPORTE
            Map map_parametros = new HashMap();
            map_parametros.put("pide_ins", Integer.parseInt(tab_matriculas.getValor("ide_ymamat")));
            map_parametros.put("nombre", utilitario.getVariable("NICK"));

            System.out.println(" " + map_parametros);
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
            sel_actualiza_alumno.getTab_seleccion().setSql(ser_alumno.getDatosAlumnos("true"));
            sel_actualiza_alumno.getTab_seleccion().ejecutarSql();
            sel_actualiza_alumno.dibujar();
        }

    }

    public void recibeDocumento() {
        if (tab_matriculas.getValor("recibido_ymamat").equals("false")) {
            TablaGenerica tab_documento = utilitario.consultar(ser_estructura.getDocumentoRequeridoPeriodo(com_periodo_academico.getValue().toString(), par_modulo_matricula));
            utilitario.getConexion().ejecutarSql(ser_estructura.deleteBloqueos(utilitario.getVariable("IDE_USUA")));

            if (tab_documento.getTotalFilas() > 0) {
                for (int i = 0; i < tab_documento.getTotalFilas(); i++) {
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
                guardarPantalla();

            } else {
                utilitario.agregarMensajeInfo("No existe registros configurados", "Favor configurar documentos parea el Periodo Academico Seleccionado");
            }
        } else {
            utilitario.agregarMensajeError("No puede recibir Documentos", "Usted ya ejecuto la opción recibir documentos, y no puede volver a ejecutarle pues recibirlo individualmente si lo deses");
        }
    }

    public void filtroComboPeriodoAcademnico() {

        tab_matriculas.setCondicion("ide_ymaper=" + com_periodo_academico.getValue().toString());
        tab_matriculas.ejecutarSql();
        tab_registro_credito.ejecutarSql();
        utilitario.addUpdate("tab_pre_inscrip");

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
            utilitario.agregarMensajeInfo("Seleccione del Listado de Alumnos para realizar la Matricula", "No se puede insertar desde esta opcion");
        } else if (tab_documento_entregado.isFocus()) {
            tab_documento_entregado.insertar();
        } else if (tab_registro_credito.isFocus()) {
            tab_registro_credito.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_matriculas.guardar()) {
            if (tab_documento_entregado.guardar()) {
                if (tab_registro_credito.guardar()) {
                    guardarPantalla();
                    registrarRecordAcademico();
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
            tab_registro_credito.eliminar();
        }
    }

    public Tabla getTab_matriculas() {
        return tab_matriculas;
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

}

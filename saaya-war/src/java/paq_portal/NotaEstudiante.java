/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_portal;

import paq_nota.*;
import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Espacio;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Imagen;
import framework.componentes.ListaSeleccion;
import framework.componentes.Reporte;
import framework.componentes.VisualizarPDF;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author DIEGO
 */
public class NotaEstudiante extends Pantalla {

    private Combo com_reportes = new Combo();
    private Combo com_periodo_academico = new Combo();
    private ListaSeleccion lis_materia = new ListaSeleccion();
    private ListaSeleccion lis_actividad = new ListaSeleccion();
    private ListaSeleccion lis_parcial = new ListaSeleccion();
    private Reporte rep_reporte = new Reporte();
    private Map p_parametros = new HashMap();
    private VisualizarPDF vipdf_comprobante = new VisualizarPDF();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    public NotaEstudiante() {
        if (TienePerfilNota()) {

            bar_botones.getBot_insertar().setRendered(false);
            bar_botones.getBot_guardar().setRendered(false);
            bar_botones.getBot_eliminar().setRendered(false);
            bar_botones.quitarBotonsNavegacion();

            bar_botones.agregarComponente(new Etiqueta("Lista de Reportes"));
            com_reportes.setId("com_reportes");
            com_reportes.setCombo(ser_estructura.getListaReportes(utilitario.getVariable("p_menu_rep_portal")));
            bar_botones.agregarComponente(com_reportes);

            bar_botones.agregarComponente(new Etiqueta("Periodo Académico "));
            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");
            bar_botones.agregarComponente(com_periodo_academico);

            Boton bot_imprimirpdf = new Boton();
            bot_imprimirpdf.setIcon("ui-icon-print");
            bot_imprimirpdf.setValue("IMPRIMIR REPORTE");
            bot_imprimirpdf.setMetodo("abrirListaReportes");
            bar_botones.agregarBoton(bot_imprimirpdf);

            Imagen ImaReportes = new Imagen();
            ImaReportes.setValue("imagenes/ModAsistencia/Asistencia.png");

            Grid grid_pant = new Grid();
            grid_pant.setColumns(1);
            grid_pant.setStyle("text-align:center;position:absolute;top:210px;left:535px;");
            Etiqueta eti_encab = new Etiqueta();
            grid_pant.getChildren().add(ImaReportes);
            Boton bot_imprimir = new Boton();
            bot_imprimir.setValue("Imprimir Reporte");
            bot_imprimir.setIcon("ui-icon-print");
            bot_imprimir.setMetodo("abrirListaReportes");
            bar_botones.agregarBoton(bot_imprimir);
            grid_pant.getChildren().add(bot_imprimir);

            Grid gri_formulario = new Grid();
            gri_formulario.setColumns(2);


            lis_parcial.setListaSeleccion(ser_notas.getTipoEvaluacion("true,false"));
            lis_parcial.setLayout("pageDirection");

            Espacio esp = new Espacio();
            Etiqueta eti_parcial = new Etiqueta("PARCIAL");
            eti_parcial.setEstiloContenido("font-size:16px;font-weight: bold;text-decoration: underline;color:blue");
            gri_formulario.getChildren().add(esp);
            gri_formulario.getChildren().add(eti_parcial);
            gri_formulario.getChildren().add(ImaReportes);
            gri_formulario.getChildren().add(lis_parcial);

            Division div = new Division();
            div.dividir1(gri_formulario);
            agregarComponente(div);

            vipdf_comprobante.setId("vipdf_comprobante");
            vipdf_comprobante.setTitle("REPORTES NOTAS");
            agregarComponente(vipdf_comprobante);

        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el control de Asistencia. Consulte con el Administrador");
        }
    }

    String docente = "";
    String documento = "";
    String ide_docente = "";
    String materia = "";
    String parcial = "";
    String actividad = "";

    private boolean TienePerfilNota() {
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

    public void filtroComboPeriodoAcademico() {
        lis_materia.setListaSeleccion(ser_asistencia.getMateriaNivelDocente(com_periodo_academico.getValue().toString(), ide_docente));
        utilitario.addUpdate("lis_materia");
    }

    public void abrirListaReportes() {
        if (com_reportes.getValue() != null) {
            if (com_periodo_academico.getValue() != null) {
                generarPDF();
            } else {
                utilitario.agregarMensajeInfo("Mensaje,", "Seleccione el periodod académico");
            }
        } else {
            utilitario.agregarMensajeInfo("Mensaje,", "Seleccione un reporte de la lista de reportes");

        }

    }

    public void generarPDF() {
        TablaGenerica tab_reportes = utilitario.consultar("select * from sis_reporte where ide_repo=" + com_reportes.getValue());

        String nombre_reporte = "";
        String reporte = "";
        nombre_reporte = tab_reportes.getValor("nom_repo");
        reporte = tab_reportes.getValor("path_repo");

        if (nombre_reporte.equals("Nota Final")) {
            if (lis_materia.getSeleccionados() != "") {
                materia = lis_materia.getSeleccionados();
                //System.out.println("MATERIA >>>>>> " + materia);
                TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(materia));
                Map map_parametros = new HashMap();
               
                    map_parametros.put("nombre", utilitario.getVariable("NICK"));
                    map_parametros.put("ide_ystpea", com_periodo_academico.getValue().toString());
                    map_parametros.put("ide_ypedpe", ide_docente);
                    map_parametros.put("ide_yaldap", utilitario.getVariable("ALUMNO"));
                vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                vipdf_comprobante.dibujar();
            } else {
                utilitario.agregarMensajeInfo("Seleccione la materia,", "Seleccione al menos un registro");
            }
        } else if (nombre_reporte.equals("Nota Final Parcial")) {
            if (lis_materia.getSeleccionados() != "") {
                materia = lis_materia.getSeleccionados();
                TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(materia));
                if (lis_parcial.getSeleccionados() != "") {
                    parcial = lis_parcial.getSeleccionados();
                    Map map_parametros = new HashMap();
                    map_parametros.put("nombre", utilitario.getVariable("NICK"));
                    map_parametros.put("ide_ystpea", com_periodo_academico.getValue().toString());
                    map_parametros.put("ide_ypedpe", ide_docente);
                    map_parametros.put("ide_yaldap", utilitario.getVariable("ALUMNO"));
                    map_parametros.put("ide_ynotie", parcial);
                    vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                    vipdf_comprobante.dibujar();
                    utilitario.addUpdate("vipdf_comprobante");
                } else {
                    utilitario.agregarMensajeInfo("Seleccione el parcial,", "Seleccione al menos un registro");
                }
            } else {
                utilitario.agregarMensajeInfo("Seleccione la materia,", "Seleccione al menos un registro");
            }
        } else if (nombre_reporte.equals("Lista Estudiantes Exonerados")) {
            if (lis_materia.getSeleccionados() != "") {
                materia = lis_materia.getSeleccionados();
                TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(materia));
                if (lis_parcial.getSeleccionados() != "") {
                    parcial = lis_parcial.getSeleccionados();

                    Map map_parametros = new HashMap();
                    map_parametros.put("nombre", utilitario.getVariable("NICK"));
                    map_parametros.put("ide_ystpea", com_periodo_academico.getValue().toString());
                    map_parametros.put("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
                    map_parametros.put("ide_ypedpe", ide_docente);
                    map_parametros.put("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
                    map_parametros.put("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
                    map_parametros.put("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
                    map_parametros.put("ide_ynotie", parcial);
                    vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                    vipdf_comprobante.dibujar();
                    utilitario.addUpdate("vipdf_comprobante");
                } else {
                    utilitario.agregarMensajeInfo("Seleccione el parcial,", "Seleccione al menos un registro");
                }
            } else {
                utilitario.agregarMensajeInfo("Seleccione la materia,", "Seleccione al menos un registro");
            }

        } else if (nombre_reporte.equals("Lista Estudiantes Examen Recuperación")) {

            if (lis_materia.getSeleccionados() != "") {
                materia = lis_materia.getSeleccionados();
                TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(materia));
                if (lis_parcial.getSeleccionados() != "") {
                    parcial = lis_parcial.getSeleccionados();
                    Map map_parametros = new HashMap();
                    map_parametros.put("nombre", utilitario.getVariable("NICK"));
                    map_parametros.put("ide_ystpea", com_periodo_academico.getValue().toString());
                    map_parametros.put("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
                    map_parametros.put("ide_ypedpe", ide_docente);
                    map_parametros.put("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
                    map_parametros.put("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
                    map_parametros.put("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
                    map_parametros.put("ide_ynotie", parcial);
                    vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                    vipdf_comprobante.dibujar();
                    utilitario.addUpdate("vipdf_comprobante");
                } else {
                    utilitario.agregarMensajeInfo("Seleccione el parcial,", "Seleccione al menos un registro");
                }
            } else {
                utilitario.agregarMensajeInfo("Seleccione la materia,", "Seleccione al menos un registro");
            }
        } else if (nombre_reporte.equals("Nota Final por Actividades")) {
            if (lis_materia.getSeleccionados() != "") {
                materia = lis_materia.getSeleccionados();
                if (lis_actividad.getSeleccionados() != "") {
                    actividad = lis_actividad.getSeleccionados();
                    TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(materia));
                    if (lis_parcial.getSeleccionados() != "") {
                        parcial = lis_parcial.getSeleccionados();
                        Map map_parametros = new HashMap();
                        map_parametros.put("nombre", utilitario.getVariable("NICK"));
                        map_parametros.put("ide_ystpea", com_periodo_academico.getValue().toString());
                        map_parametros.put("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
                        map_parametros.put("ide_ypedpe", ide_docente);
                        map_parametros.put("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
                        map_parametros.put("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
                        map_parametros.put("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
                        map_parametros.put("ide_ynotie", parcial);
                        map_parametros.put("ide_ynoace", actividad);
                        vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
                        vipdf_comprobante.dibujar();
                        utilitario.addUpdate("vipdf_comprobante");
                    } else {
                        utilitario.agregarMensajeInfo("Seleccione el parcial,", "Seleccione al menos un registro");
                    }
                } else {
                    utilitario.agregarMensajeInfo("Seleccione la actividad de evaluación,", "Seleccione al menos un registro");
                }
            } else {
                utilitario.agregarMensajeInfo("Seleccione la materia,", "Seleccione al menos un registro");
            }
        }
    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public ListaSeleccion getLis_materia() {
        return lis_materia;
    }

    public void setLis_materia(ListaSeleccion lis_materia) {
        this.lis_materia = lis_materia;
    }

    public Reporte getRep_reporte() {
        return rep_reporte;
    }

    public void setRep_reporte(Reporte rep_reporte) {
        this.rep_reporte = rep_reporte;
    }

    public Map getP_parametros() {
        return p_parametros;
    }

    public void setP_parametros(Map p_parametros) {
        this.p_parametros = p_parametros;
    }

    public VisualizarPDF getVipdf_comprobante() {
        return vipdf_comprobante;
    }

    public void setVipdf_comprobante(VisualizarPDF vipdf_comprobante) {
        this.vipdf_comprobante = vipdf_comprobante;
    }

    public String getIde_docente() {
        return ide_docente;
    }

    public void setIde_docente(String ide_docente) {
        this.ide_docente = ide_docente;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

}

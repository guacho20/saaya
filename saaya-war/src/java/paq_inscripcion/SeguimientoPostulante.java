/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion;

import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.event.SelectEvent;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_inscripcion.ejb.ServicioInscripcion;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author CRISTIAN VEGA
 */
public class SeguimientoPostulante extends Pantalla {

    private Combo com_mension = new Combo();
    private Combo com_periodo_academico = new Combo();
    private Boton bot_clean = new Boton();
    private Boton bot_datos = new Boton();
    private Tabla tab_docentealumno = new Tabla();
    private Tabla tab_docenteseguimiento = new Tabla();
    private Tabla tab_datos = new Tabla();

    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioInscripcion ser_inscripcion = (ServicioInscripcion) utilitario.instanciarEJB(ServicioInscripcion.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public SeguimientoPostulante() {

        if (TienePermiso()) {

            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
            bar_botones.agregarComponente(new Etiqueta("PERIODO ACADEMICO:"));
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");

            com_mension.setId("com_mension");
            com_mension.setCombo(ser_inscripcion.getSqlDocenteAsignado("-1", "-1"));
            bar_botones.agregarComponente(new Etiqueta("CARRERA:"));
            bar_botones.agregarComponente(com_mension);
            com_mension.setMetodo("filtroDocentes");

            bot_datos.setIcon("ui-icon-search");
            bot_datos.setTitle("Ver Datos Contacto");
            bot_datos.setMetodo("abrirDialogo");
            bar_botones.agregarComponente(bot_datos);

            bot_clean.setIcon("ui-icon-cancel");
            bot_clean.setTitle("Limpiar");
            bot_clean.setMetodo("limpiar");
            bar_botones.agregarComponente(bot_clean);

            tab_docentealumno.setId("tab_docentealumno");
            tab_docentealumno.setTabla("yavirac_ins_docente_alumno", "ide_yindoa", 1);
            tab_docentealumno.setCondicion("ide_yindoa=-1");
            tab_docentealumno.onSelect("seleccionarTabla1");
            tab_docentealumno.getColumna("ide_yindoa").setNombreVisual("CODIGO");
            tab_docentealumno.setHeader("DOCENTE: " + docente);
            //tab_docentealumno.setCondicion("ide_yincda in (select ide_yincda from yavirac_ins_coordin_docent_as  where ide_ypedpe=" + ide_docente + ")");
            tab_docentealumno.getColumna("ide_yincda").setVisible(false);
            tab_docentealumno.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true"));
            tab_docentealumno.getColumna("ide_yaldap").setNombreVisual("ALUMNO");
            tab_docentealumno.getColumna("ide_yaldap").setLectura(true);
            tab_docentealumno.getColumna("ide_yinpin").setVisible(false);
            tab_docentealumno.getColumna("asigna_yindoa").setVisible(false);
            tab_docentealumno.getColumna("ide_yindoa").setLink();
            tab_docentealumno.getColumna("ide_yindoa").alinearCentro();
            tab_docentealumno.getColumna("ide_yindoa").setMetodoChange("abrirDetalle");
            tab_docentealumno.agregarRelacion(tab_docenteseguimiento);
            tab_docentealumno.dibujar();

            PanelTabla pat_docentealumno = new PanelTabla();
            pat_docentealumno.setId("pat_docentealumno");
            pat_docentealumno.setPanelTabla(tab_docentealumno);

            tab_docenteseguimiento.setId("tab_docenteseguimiento");
            tab_docenteseguimiento.setTabla("yavirac_ins_docente_seguimiento", "ide_yindos", 2);
            tab_docenteseguimiento.getColumna("ide_yindos").setNombreVisual("CODIGO");
            tab_docenteseguimiento.setHeader("SEGUIMIENTO POSTULANTE");
            tab_docenteseguimiento.getColumna("ide_yindoa").setNombreVisual("DOCENTE ALUMNO");
            tab_docenteseguimiento.getColumna("fecha_contacto_yindos").setNombreVisual("FECHA CONTACTO");
            tab_docenteseguimiento.getColumna("novedad_yindos").setNombreVisual("NOVEDAD");
            tab_docenteseguimiento.getColumna("contactado_yindos").setNombreVisual("CONTACTADO");
            tab_docenteseguimiento.getColumna("contactado_yindos").setValorDefecto("false");

            tab_docenteseguimiento.dibujar();

            PanelTabla pat_docenteseguimiento = new PanelTabla();
            pat_docenteseguimiento.setId("pat_docenteseguimiento");
            pat_docenteseguimiento.setPanelTabla(tab_docenteseguimiento);

            tab_datos.setId("tab_datos");
            tab_datos.setTabla("yavirac_ins_pre_inscripcion", "ide_yinpin", 4);
            tab_datos.setCondicion(" ide_yinpin=-1 ");
            tab_datos.setLectura(true);
            tab_datos.getColumna("ide_yinpin").setNombreVisual("CÓDIGO");
            tab_datos.getColumna("ide_yaldap").setVisible(false);
            tab_datos.getColumna("ide_ystpea").setVisible(false);
            tab_datos.getColumna("ide_ypedpe").setVisible(false);     
            tab_datos.getColumna("ide_ystmen").setVisible(false);
            tab_datos.getColumna("docum_senecyd_yinpin").setVisible(false);
            tab_datos.getColumna("fecha_inscripcion_yinpin").setVisible(false);
            tab_datos.getColumna("observacion_yinpin").setVisible(false);
            tab_datos.getColumna("recibido_yinpin").setVisible(false);
            tab_datos.getColumna("nro_folio_yinpin").setVisible(false);
            tab_datos.getColumna("ide_yinsin").setVisible(false);
            tab_datos.getColumna("nota_obtenida_yinsin").setVisible(false);
            tab_datos.getColumna("contactado_yinpin").setVisible(false);
            tab_datos.getColumna("fecha_contac_yinpin").setVisible(false);
            tab_datos.getColumna("observacion_contac_yinpin").setVisible(false);
            tab_datos.getColumna("inscrito_yinpin").setVisible(false);
            tab_datos.getColumna("fecha_registro_yinpin").setVisible(false);
            tab_datos.getColumna("celular_yinpin").setNombreVisual("NÚMERO CELULAR");
            tab_datos.getColumna("correo_yinpin").setNombreVisual("CORREO ELECTRÓNICO");
            tab_datos.getColumna("yav_ide_ypedpe").setVisible(false);
            tab_datos.dibujar();
            Division div_division = new Division();
            div_division.setId("div_division");

            div_division.dividir2(pat_docentealumno, tab_datos, "50%", "V");

            Division div_division2 = new Division();
            div_division2.setId("div_division2");
            div_division2.dividir2(div_division, pat_docenteseguimiento, "50%", "H");
            agregarComponente(div_division2);

        } else {
            utilitario.agregarNotificacionInfo("Mensaje,", "EL usuario ingresado no registra permisos para la asignacion de docentes. Consulte con el Administrador");
        }
    }

    public void abrirDetalle() {
        utilitario.agregarMensaje("", "hhhhhh");

    }
        
    public void filtroComboPeriodoAcademico() {

        com_mension.setCombo(ser_inscripcion.getSqlDocenteAsignado(com_periodo_academico.getValue().toString(), ide_docente));
        utilitario.addUpdate("com_mension");
        ///tab_docentemension.ejecutarValorForanea(tab_docentemension.getValorSeleccionado());   
    }

    public void filtroDocentes() {

        String cod = com_mension.getValue().toString() + "";
        //System.out.println("COD: "+cod);
        tab_docentealumno.setCondicion(" ide_yincda=" + cod);
        tab_docentealumno.ejecutarSql();
        //tab_asignaciondocente.imprimirSql();
        tab_datos.setCondicion("ide_yaldap=" + tab_docentealumno.getValor(tab_docentealumno.getFilaActual(), "ide_yaldap"));
        tab_datos.ejecutarSql();
        //tab_docentealumno.ejecutarValorForanea(tab_asignaciondocente.getValorSeleccionado());
        utilitario.addUpdate("tab_docentealumno");          
    }     
public void seleccionarTabla1(SelectEvent evt){ 
    System.out.println("fila actual "+tab_docentealumno.getFilaSeleccionada().getRowKey());
     System.out.println("valro 2 "+tab_docentealumno.getValor(0, "ide_yaldap"));
     tab_datos.setCondicion("ide_yaldap in (select ide_yaldap from yavirac_ins_docente_alumno where ide_yindoa="+tab_docentealumno.getFilaSeleccionada().getRowKey()+")");
       
     tab_datos.ejecutarSql();
     tab_datos.imprimirSql();
}
    String docente = "";
    String documento = "";
    String ide_docente = "";

    private boolean TienePermiso() {
        List sql = utilitario.getConexion().consultar(ser_estructura_organizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA"), " and not ide_ypedpe is null"));
        System.out.println(" " + sql);
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

    @Override
    public void insertar() {
        if (tab_docenteseguimiento.isFocus()) {
            tab_docenteseguimiento.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_docentealumno.guardar()) {
            if (tab_docenteseguimiento.guardar()) {
                guardarPantalla();
            }
        }

    }

    @Override
    public void eliminar() {
        if (tab_docentealumno.isFocus()) {
            tab_docentealumno.eliminar();
        } else if (tab_docenteseguimiento.isFocus()) {
            tab_docenteseguimiento.eliminar();
        }
    }

    public Boton getBot_clean() {
        return bot_clean;
    }

    public void setBot_clean(Boton bot_clean) {
        this.bot_clean = bot_clean;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public Tabla getTab_docentealumno() {
        return tab_docentealumno;
    }

    public void setTab_docentealumno(Tabla tab_docentealumno) {
        this.tab_docentealumno = tab_docentealumno;
    }

    public Tabla getTab_docenteseguimiento() {
        return tab_docenteseguimiento;
    }

    public void setTab_docenteseguimiento(Tabla tab_docenteseguimiento) {
        this.tab_docenteseguimiento = tab_docenteseguimiento;
    }

    public Tabla getTab_datos() {
        return tab_datos;
    }

    public void setTab_datos(Tabla tab_datos) {
        this.tab_datos = tab_datos;
    }

}

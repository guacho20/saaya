/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.event.SelectEvent;
import paq_alumno.ejb.ServicioAlumno;
import sistema.aplicacion.Pantalla;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;



/**
 *
 * @author Janeth Pullotasig
 */
public class JustificacionDocente extends Pantalla {
    private Tabla tab_tipomotivo =new  Tabla();
    private AutoCompletar aut_alumno = new AutoCompletar();
    private SeleccionTabla sel_fecha_ausencia = new SeleccionTabla();
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_EstructuraOrganizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    public JustificacionDocente(){
    if (tienePerfilSecretaria()) {    
     // boton limpiar
		Boton bot_limpiar = new Boton();
		bot_limpiar.setIcon("ui-icon-cancel");
		bot_limpiar.setMetodo("limpiar");
                
                // autocompletar alumno
		aut_alumno.setId("aut_alumno");
		String str_sql=ser_personal.getDatopersonal("true,false");
		aut_alumno.setAutoCompletar(str_sql);		
		aut_alumno.setMetodoChange("filtrarEmpleado");

		Etiqueta eti_colaborador=new Etiqueta("Colaborador/a:");
		bar_botones.agregarComponente(eti_colaborador);
		bar_botones.agregarComponente(aut_alumno);
		bar_botones.agregarBoton(bot_limpiar);
                
        
        tab_tipomotivo.setId("tab_tipomotivo");
        tab_tipomotivo.setTabla("YAVIRAC_ASIS_JUSTIFICACION","IDE_YASJUS",1);
        tab_tipomotivo.setCondicion("TIPO_YASJUS=2 AND IDE_YALDAP=-1");
        tab_tipomotivo.getColumna("TIPO_YASJUS").setVisible(false);
        tab_tipomotivo.getColumna("TIPO_YASJUS").setValorDefecto("2");
        tab_tipomotivo.getColumna("IDE_YPEDPE").setVisible(false);
        tab_tipomotivo.getColumna("yav_ide_ypedpe").setValorDefecto(ide_docente);
        tab_tipomotivo.getColumna("IDE_YPEDPE").setVisible(false);
        tab_tipomotivo.getColumna("IDE_YALDAP").setVisible(false);
        tab_tipomotivo.getColumna("ANEXO_YASJUS").setUpload();
        tab_tipomotivo.getColumna("ide_yasmpe").setCombo(ser_asistencia.getMotivoAusencia());  
        tab_tipomotivo.getColumna("yav_ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tipomotivo.getColumna("yav_ide_ypedpe").setAutoCompletar();
        tab_tipomotivo.getColumna("yav_ide_ypedpe").setLectura(true);
        
        tab_tipomotivo.setTipoFormulario(true);
        tab_tipomotivo.getGrid().setColumns(4);        
        tab_tipomotivo.dibujar();
        
        
        
        PanelTabla pat_tipomotivo = new PanelTabla();
        pat_tipomotivo.setId("pat_tipomotivo");
        pat_tipomotivo.setPanelTabla(tab_tipomotivo);
        
       
        Division div_tipomotivo = new Division();
        div_tipomotivo.setId("div_tipomotivo");
        div_tipomotivo.dividir1(pat_tipomotivo);
        
        agregarComponente(div_tipomotivo);   
        
        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el registro de SOLICITUDES. Consulte con el Administrador");
        }           
    }
    public void limpiar() {
        tab_tipomotivo.limpiar();
        aut_alumno.limpiar();
        utilitario.addUpdate("tab_tipomotivo,aut_alumno");
    }    
public void selActualizaJustificacion(){
        if (aut_alumno.getValor()!=null){
            //sel_fecha_ausencia.getTab_seleccion().setSql(ser_asistencia.getAusenciaAlumno(tab_tipomotivo.getValor("fecha_ausento_yasjus"), tab_tipomotivo.getValor("fecha_ausento_yasjus"), "false"));
            sel_fecha_ausencia.getTab_seleccion().ejecutarSql();
            sel_fecha_ausencia.dibujar();

        } else {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Alumno/a");
            return;
        }
}    
public void actualizaAsistencia(){
            String str_seleccionado = sel_fecha_ausencia.getValorSeleccionado();
        if (str_seleccionado != null) {
            //Inserto los cleintes seleccionados en la tabla  
            utilitario.getConexion().ejecutarSql("update yavirac_asis_asistencia set justificado_yasasi = true where ide_yasasi in ( "+str_seleccionado+" )");

            sel_fecha_ausencia.cerrar();
        } else {
            utilitario.agregarMensajeInfo("Debe seleccionar al menos un registro", "");
        }
}
public void filtrarEmpleado(SelectEvent evt){
		aut_alumno.onSelect(evt);
		tab_tipomotivo.setCondicion("TIPO_YASJUS=2 AND IDE_YPEDPE="+aut_alumno.getValor());
		tab_tipomotivo.ejecutarSql();

	}    
String docente = "";
    String documento = "";
    String ide_docente = "";

    private boolean tienePerfilSecretaria() {
        List sql = utilitario.getConexion().consultar(ser_EstructuraOrganizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA"), " and not ide_ypedpe is null"));

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
        if (aut_alumno.getValor()!=null){
        tab_tipomotivo.insertar();
        tab_tipomotivo.setValor("IDE_YPEDPE", aut_alumno.getValor());
        }else{
	utilitario.agregarMensajeInfo("No se puede insertar", "Debe seleccionar un Colaborador/a para Justificar");
        }
    }

    @Override
    public void guardar() {
        tab_tipomotivo.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipomotivo.eliminar();
    }

    public Tabla getTab_tipomotivo() {
        return tab_tipomotivo;
    
    }
   public void setTab_tipomotivo(Tabla tab_tipomotivo){
       this.tab_tipomotivo = tab_tipomotivo;
   }

    public AutoCompletar getAut_alumno() {
        return aut_alumno;
    }

    public void setAut_alumno(AutoCompletar aut_alumno) {
        this.aut_alumno = aut_alumno;
    }

    public SeleccionTabla getSel_fecha_ausencia() {
        return sel_fecha_ausencia;
    }

    public void setSel_fecha_ausencia(SeleccionTabla sel_fecha_ausencia) {
        this.sel_fecha_ausencia = sel_fecha_ausencia;
    }
   
}

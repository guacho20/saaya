/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.aplicacion.TablaGenerica;
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
public class JustificacionAlumno extends Pantalla {
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
    
    public JustificacionAlumno(){
    if (tienePerfilSecretaria()) {    
     // boton limpiar
		Boton bot_limpiar = new Boton();
		bot_limpiar.setIcon("ui-icon-cancel");
		bot_limpiar.setMetodo("limpiar");
                
                // autocompletar alumno
		aut_alumno.setId("aut_alumno");
		String str_sql=ser_alumno.getDatosAlumnos("true,false");
		aut_alumno.setAutoCompletar(str_sql);		
		aut_alumno.setMetodoChange("filtrarEmpleado");

		Etiqueta eti_colaborador=new Etiqueta("Alumno/a:");
		bar_botones.agregarComponente(eti_colaborador);
		bar_botones.agregarComponente(aut_alumno);
		bar_botones.agregarBoton(bot_limpiar);
                
                //BOTON JUSTIFICAR
            Boton bot_justificar = new Boton();
            bot_justificar.setValue("Justificar Ausencias");
            bot_justificar.setIcon("ui-icon-refresh");
            bot_justificar.setMetodo("selActualizaJustificacion");
            bar_botones.agregarBoton(bot_justificar);
        
        tab_tipomotivo.setId("tab_tipomotivo");
        tab_tipomotivo.setTabla("YAVIRAC_ASIS_JUSTIFICACION","IDE_YASJUS",1);
        tab_tipomotivo.setCondicion("TIPO_YASJUS=1 AND IDE_YALDAP=-1");
        tab_tipomotivo.getColumna("TIPO_YASJUS").setVisible(false);
        tab_tipomotivo.getColumna("TIPO_YASJUS").setValorDefecto("1");
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
        
            //PANTALLA ACTUALIZA ALUMNO
            sel_fecha_ausencia.setId("sel_fecha_ausencia");
            sel_fecha_ausencia.setTitle("SELECCIONE LAS FECHAS DE AUSENCIA");
            sel_fecha_ausencia.getBot_aceptar().setMetodo("actualizaAsistencia");
            sel_fecha_ausencia.setSeleccionTabla(ser_asistencia.getAusenciaAlumno("1900-01-01", "1900-01-01", "true","true"), "ide_yasasi");
            agregarComponente(sel_fecha_ausencia);
        
        
        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el registro de SOLICITUDES. Consulte con el Administrador");
        }           
    }
public void selActualizaJustificacion(){
        if (aut_alumno.getValor()!=null){
            sel_fecha_ausencia.getTab_seleccion().setSql(ser_asistencia.getAusenciaAlumno(tab_tipomotivo.getValor("fecha_ausento_yasjus"), tab_tipomotivo.getValor("fecha_ausento_yasjus"), "false","false"));
            sel_fecha_ausencia.getTab_seleccion().ejecutarSql();
            sel_fecha_ausencia.dibujar();

        } else {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Alumno/a");
            return;
        }
}  
    public void limpiar() {
        tab_tipomotivo.limpiar();
        aut_alumno.limpiar();
        utilitario.addUpdate("tab_tipomotivo,aut_alumno");
    }
public void actualizaAsistencia(){
            String str_seleccionado = sel_fecha_ausencia.getSeleccionados();
        if (str_seleccionado != null) {
            //Inserto los cleintes seleccionados en la tabla  
            utilitario.getConexion().ejecutarSql("update yavirac_asis_asistencia set justificado_yasasi = true,ide_yasjus="+tab_tipomotivo.getValor("ide_yasjus")+" where ide_yasasi in ( "+str_seleccionado+" )");
            utilitario.agregarMensaje("Justificado", "Se realizo la justificacion exitosamente");
            sel_fecha_ausencia.cerrar();
        } else {
            utilitario.agregarMensajeInfo("Debe seleccionar al menos un registro", "");
        }
}
public void filtrarEmpleado(SelectEvent evt){
		aut_alumno.onSelect(evt);
		tab_tipomotivo.setCondicion("TIPO_YASJUS=1 AND IDE_YALDAP="+aut_alumno.getValor());
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
        tab_tipomotivo.setValor("IDE_YALDAP", aut_alumno.getValor());
        }else{
	utilitario.agregarMensajeInfo("No se puede insertar", "Debe seleccionar un Alumno/a para Justificar");
        }
    }

    @Override
    public void guardar() {
        TablaGenerica tab_dias =utilitario.consultar("select * from yavirac_stror_periodo_academic where activo_ystpea=true");
        String sql="SELECT 1 as codigo,cast((now() - CAST('"+tab_dias.getValor("dias_justifica_ystpea")+" days' AS INTERVAL)) as date) as fecha";
        System.out.println("sql diasss  "+sql);
        TablaGenerica tab_fecha = utilitario.consultar(sql);
        
        if (utilitario.isFechaMenor(utilitario.getFecha(tab_tipomotivo.getValor("fecha_ausento_yasjus")), utilitario.getFecha(tab_fecha.getValor("fecha")))){
			utilitario.agregarMensajeInfo("No se puede guardar", "La fecha de ausencia supera el limite permitido para justificar que son "+tab_dias.getValor("dias_justifica_ystpea")+" dias");
			return;
		}
        else{
        tab_tipomotivo.guardar();
        guardarPantalla();
        }
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

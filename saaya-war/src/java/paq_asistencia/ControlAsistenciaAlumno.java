/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Calendario;
import framework.componentes.Combo;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionCalendario;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import framework.componentes.Texto;
import sistema.aplicacion.Pantalla;
import java.util.List;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_matricula.ejb.ServicioMatriculas;
import paq_personal.ejb.ServicioPersonal;

/**
 *
 * @author User
 */
public class ControlAsistenciaAlumno extends Pantalla{

     private Combo com_periodo_academico = new Combo();
     private Tabla tab_docente_mencion = new Tabla();
     private Tabla tab_fecha_control = new Tabla();
     private Tabla tab_asitencia= new Tabla();
     private Combo com_materia_docente = new Combo();
     private Etiqueta eti_docente = new Etiqueta();
     private Etiqueta eti_fecha_asistencia = new Etiqueta();
     private Etiqueta eti_materia = new Etiqueta();
     private  Division div = new Division();
     private Dialogo dia_fecha = new Dialogo();
     private Texto txt_fecha = new Texto();
     private Calendario cal_docente = new Calendario();
     private SeleccionTabla sel_fecha_asistencia = new SeleccionTabla();
    private SeleccionTabla sel_fecha_asistencia_consulta = new SeleccionTabla();     
     String fecha="";
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioMatriculas ser_matricula = (ServicioMatriculas) utilitario.instanciarEJB(ServicioMatriculas.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
   
    public ControlAsistenciaAlumno(){
        if (tienePerfilAsistencia()) {
            
            bar_botones.getBot_insertar().setRendered(false);
            bar_botones.getBot_eliminar().setRendered(false);
            bar_botones.getBot_atras().setRendered(false);
            bar_botones.getBot_fin().setRendered(false);
            bar_botones.getBot_siguiente().setRendered(false);
            bar_botones.getBot_inicio().setRendered(false);            
            
       
            
            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
            bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");
            
            com_materia_docente.setId("com_materia_docente");
            com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente("-1", "2"));
            //com_periodo_academico.setMetodo("abrirCalendario");
            
            bar_botones.agregarComponente(new Etiqueta("Cursos:"));
            bar_botones.agregarComponente(com_materia_docente);    
            //com_materia_docente.setMetodo("filtroHorarios");
// boton limpiar
		Boton bot_limpiar = new Boton();
		bot_limpiar.setIcon("ui-icon-cancel");
		bot_limpiar.setMetodo("limpiar");
		bar_botones.agregarBoton(bot_limpiar);
                
           
           Boton bot_consultar = new Boton();
           bot_consultar.setIcon("ui-icon-folder-open");//set icono consultar///
           bot_consultar.setValue("Consultar Asistencia");
           bot_consultar.setMetodo("filtraAlumno");
           bar_botones.agregarComponente(bot_consultar);
           
            Boton bot_asistencia = new Boton();
            bot_asistencia.setValue("Registrar Asistencia");
            bot_asistencia.setIcon("ui-icon-note");//set icono Registrar///
            bot_asistencia.setMetodo("abrirCalendario");
            bar_botones.agregarBoton(bot_asistencia);  
            
                    eti_docente.setStyle("font-size: 16px;font-weight: bold");
                    eti_docente.setValue("Docente: "+docente);
                    
                    eti_fecha_asistencia.setId("eti_fecha_asistencia");
                    eti_fecha_asistencia.setStyle("font-size: 16px;font-weight: bold");
                    eti_fecha_asistencia.setValue("Fecha Asistencia: ");
            
                    Grid grup_titulo = new Grid();
                    grup_titulo.setColumns(1);
                    grup_titulo.setWidth("100%");
                    grup_titulo.setId("grup_titulo");
                    grup_titulo.getChildren().add(eti_docente);
                    grup_titulo.getChildren().add(new Etiqueta(""));
                    grup_titulo.getChildren().add(eti_fecha_asistencia);
;
         

         tab_asitencia.setId("tab_asitencia");
         tab_asitencia.setTabla("yavirac_asis_asistencia", "ide_yasasi", 1);
         tab_asitencia.setCondicion("ide_yasasi=-1");
         tab_asitencia.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
         tab_asitencia.getColumna("ide_yaldap").setAutoCompletar();
         tab_asitencia.getColumna("ide_yaldap").setLectura(true);
         tab_asitencia.getColumna("justificado_yasasi").setLectura(true);
         tab_asitencia.getColumna("ide_yasfec").setVisible(false);
         tab_asitencia.getColumna("ide_ypedpe").setVisible(false);
         tab_asitencia.getColumna("ide_ypemad").setVisible(false);         
         tab_asitencia.getColumna("ide_yasjus").setVisible(false);
         tab_asitencia.dibujar();
         
         PanelTabla pat_asistencia = new PanelTabla();
         pat_asistencia.setPanelTabla(tab_asitencia);
          
           
            div.setId("div");
            div.dividir1(pat_asistencia);
           //agregarComponente(div_control);
           
           Division div_padre = new Division();
           div_padre.setFooter(grup_titulo, div, "16%");
           agregarComponente(div_padre);
        //gru_pantalla.getChildren().add(div);

                    //PANTALLA REGISTRA LA ASISTENCIA
            sel_fecha_asistencia.setId("sel_fecha_asistencia");
            sel_fecha_asistencia.setTitle("SELECCIONE LA FECHA PARA EL REGISTRO DE ASISTENCIA");
            sel_fecha_asistencia.getBot_aceptar().setMetodo("cargarFecha");
            sel_fecha_asistencia.setSeleccionTabla(ser_asistencia.getFechaAsistencia("-1", "false", "false", "false"), "ide_yasfec");
            sel_fecha_asistencia.setRadio();
            agregarComponente(sel_fecha_asistencia);

                    //PANTALLA CONSULTA LA ASISTENCIA
            sel_fecha_asistencia_consulta.setId("sel_fecha_asistencia_consulta");
            sel_fecha_asistencia_consulta.setTitle("SELECCIONE LA FECHA PARA CONSULTAR LA ASISTENCIA");
            sel_fecha_asistencia_consulta.getBot_aceptar().setMetodo("cargarFechaConsulta");
            sel_fecha_asistencia_consulta.setSeleccionTabla(ser_asistencia.getFechaAsistencia("-1", "false", "false", "false"), "ide_yasfec");
            sel_fecha_asistencia_consulta.setRadio();
            agregarComponente(sel_fecha_asistencia_consulta);          
        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el control de Asistencia. Consulte con el Administrador");
        }
        
    }
    public void limpiar() {
        tab_asitencia.limpiar();
        com_materia_docente.setValue("");
        com_periodo_academico.setValue("");
        eti_fecha_asistencia.setValue("Fecha Asistencia: ");
        utilitario.addUpdate("tab_asitencia,com_materia_docente,com_periodo_academico,eti_fecha_asistencia");
    }    
    public void filtraAlumno(){
        if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeInfo("Adevertencia: ", "Seleccione el Periodo Académico");
            return;
        }
        if(com_materia_docente.getValue() == null){
            utilitario.agregarMensajeInfo("Adevertencia: ", "Seleccione la materia que desea consultar la asistencia");
            return;
        }
        sel_fecha_asistencia_consulta.getTab_seleccion().setSql(ser_asistencia.getFechaAsistencia(com_periodo_academico.getValue().toString(), "true", "false,true", "false"));
        sel_fecha_asistencia_consulta.getTab_seleccion().ejecutarSql();
        sel_fecha_asistencia_consulta.dibujar();
    }
    public void abrirCalendario(){
        sel_fecha_asistencia.getTab_seleccion().setSql(ser_asistencia.getFechaAsistencia(com_periodo_academico.getValue().toString(), "true", "false", "false"));
        sel_fecha_asistencia.getTab_seleccion().ejecutarSql();
        sel_fecha_asistencia.dibujar();
    }
    public void cargarFechaConsulta(){
         String str_seleccionado = sel_fecha_asistencia_consulta.getValorSeleccionado();
        if (str_seleccionado != null) {
             TablaGenerica tab_fecha= utilitario.consultar("select ide_yasfec,fecha_yasfec,bloqueado_yasfec from yavirac_asis_fecha_control where ide_yasfec="+str_seleccionado);
             fecha=tab_fecha.getValor("fecha_yasfec");
             String bloqueado=tab_fecha.getValor("bloqueado_yasfec");
            eti_fecha_asistencia.setValue("Fecha Asistencia: "+fecha);
            tab_asitencia.setCondicion("ide_ypemad="+com_materia_docente.getValue()+" and ide_yasfec="+str_seleccionado);
            tab_asitencia.ejecutarSql();
            if(bloqueado.equals("true"))
            {
                for (int j = 0; j < tab_asitencia.getTotalFilas(); j++) {
                        tab_asitencia.getFilas().get(j).setLectura(true);
			//break;
		}
            }
            sel_fecha_asistencia_consulta.cerrar();
            utilitario.addUpdate("eti_fecha_asistencia,tab_asitencia");            
        } else {
            utilitario.agregarMensajeInfo("Debe seleccionar al menos un registro", "");
        }
    }
public void cargarFecha(){
        String str_seleccionado = sel_fecha_asistencia.getValorSeleccionado();
        if (str_seleccionado != null) {
            
            TablaGenerica tab_valida = utilitario.consultar(ser_asistencia.getControlAsistencia(com_materia_docente.getValue().toString(), str_seleccionado));
            if(tab_valida.getTotalFilas()>0){
                utilitario.agregarMensajeInfo("Asistencia Ejecutado", "El control de asistencia seleccionado ya se encuentra ejecutado, para consultar el registro de asistencia de clic en Consultar");
                sel_fecha_asistencia.cerrar();
            }
            else {
            fecha=sel_fecha_asistencia.getTab_seleccion().getValor("fecha_yasfec");
            eti_fecha_asistencia.setValue("Fecha Asistencia: "+fecha);
            TablaGenerica tab_malla_docente=utilitario.consultar(ser_personal.getPersonalMalla(com_materia_docente.getValue().toString()));
            String malla =tab_malla_docente.getValor("ide_ystmal");
            String grupo =tab_malla_docente.getValor("ide_yhogra");
            
            
            //Observacion se incremento jornada ojo tener en cuenta este servicio 
            TablaGenerica tab_alumnos_asistencia = utilitario.consultar(ser_matricula.getAlumnosMallaGrupo(malla, grupo, com_periodo_academico.getValue().toString(),"1"));
            String maximo="";
            for(int i=0;i< tab_alumnos_asistencia.getTotalFilas();i++){
                TablaGenerica tab_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_asis_asistencia", "ide_yasasi"));
                maximo=tab_maximo.getValor("maximo");
                utilitario.getConexion().ejecutarSql("insert into yavirac_asis_asistencia (ide_yasasi,ide_yasfec,ide_ypedpe,ide_yaldap,ide_ypemad,asistencia_yasasi,justificado_yasasi)" +
                                                    "values ("+maximo+","+str_seleccionado+","+ide_docente+","+tab_alumnos_asistencia.getValor(i, "ide_yaldap")+","+com_materia_docente.getValue()+",true,false)");
            }
            
            TablaGenerica tab_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_asis_control_asistencia", "ide_yascas"));
            maximo=tab_maximo.getValor("maximo");
            utilitario.getConexion().ejecutarSql("insert into yavirac_asis_control_asistencia (ide_yascas,ide_ypemad,ide_yasfec,fecha_ejecuto_yscas)" +
                                                "values ("+maximo+","+com_materia_docente.getValue()+","+str_seleccionado+",'"+utilitario.getFechaActual()+"')");

            sel_fecha_asistencia.cerrar();
            utilitario.agregarMensaje("Control de Asistencia", "Se registro de manera correcta la asistencia");
            tab_asitencia.setCondicion("ide_ypemad="+com_materia_docente.getValue()+" and ide_yasfec="+str_seleccionado);
            tab_asitencia.ejecutarSql();
            utilitario.addUpdate("eti_fecha_asistencia,tab_asitencia");
            }
            
        } else {
            utilitario.agregarMensajeInfo("Debe seleccionar al menos un registro", "");
        }
}
    String docente = "";
    String documento="";
    String ide_docente="";
        private boolean tienePerfilAsistencia() {
        List sql = utilitario.getConexion().consultar(ser_estructura_organizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA")," and not ide_ypedpe is null"));

        if (!sql.isEmpty()) {
            Object[] fila = (Object[]) sql.get(0);
                    List sql2 = utilitario.getConexion().consultar(ser_personal.getDatoPersonalCodigo(fila[3].toString()));
            if (!sql2.isEmpty()) {
                Object[] fila2 = (Object[]) sql2.get(0);
                docente = fila2[1].toString()+" "+fila2[2].toString();
                documento = fila2[3].toString();
                ide_docente=fila2[0].toString();
                    return true;
            }  
            else{
            return false;
            }
        } else {
            return false;
        }
    }
    public void filtroHorarios(){
        tab_fecha_control.setCondicion("ide_ystpea="+com_periodo_academico.getValue());
        tab_fecha_control.ejecutarSql();
         if(tab_fecha_control.getValor("bloqueado_yasfec").equals("true")){
             tab_asitencia.setLectura(true);
         }
         
        utilitario.addUpdate("tab_fecha_control,tab_asitencia");
    }    
    public void filtraEstudiantes(){
        String malla = tab_docente_mencion.getValorSeleccionado();
        TablaGenerica tab_malla = utilitario.consultar("select ide_ypemad,ide_ystmal,ide_ypedpe from yavirac_perso_malla_docente where ide_ypemad="+malla);

    }    
    public void registrarAsistencia(){
            
        String malla = tab_docente_mencion.getValorSeleccionado();
        TablaGenerica tab_malla = utilitario.consultar("select ide_ypemad,ide_ystmal,ide_ypedpe from yavirac_perso_malla_docente where ide_ypemad="+malla);
        
        String ide_fecha =tab_fecha_control.getValorSeleccionado();
        System.out.println("  ccvv "+ide_fecha);
        //TablaGenerica tab_malla_periodo = utilitario.consultar();
        utilitario.getConexion().ejecutarSql(ser_matricula.getAlumnosMallaPeriodo(tab_malla.getValor("ide_ystmal"), com_periodo_academico.getValue().toString(),ide_fecha,tab_malla.getValor("ide_ypedpe")));
        tab_asitencia.ejecutarSql();
        utilitario.addUpdate("tab_asitencia");
     
    }    
    public void filtroComboPeriodoAcademico(){
      com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente(com_periodo_academico.getValue().toString(), ide_docente));
      utilitario.addUpdate("com_materia_docente");
    }       
    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        tab_asitencia.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_docente_mencion() {
        return tab_docente_mencion;
    }

    public void setTab_docente_mencion(Tabla tab_docente_mencion) {
        this.tab_docente_mencion = tab_docente_mencion;
    }

    public Tabla getTab_fecha_control() {
        return tab_fecha_control;
    }

    public void setTab_fecha_control(Tabla tab_fecha_control) {
        this.tab_fecha_control = tab_fecha_control;
    }

    public Tabla getTab_asitencia() {
        return tab_asitencia;
    }

    public void setTab_asitencia(Tabla tab_asitencia) {
        this.tab_asitencia = tab_asitencia;
    }

    public Grupo getGru_pantalla() {
        return gru_pantalla;
    }

    public void setGru_pantalla(Grupo gru_pantalla) {
        this.gru_pantalla = gru_pantalla;
    }

    public SeleccionTabla getSel_fecha_asistencia() {
        return sel_fecha_asistencia;
    }

    public void setSel_fecha_asistencia(SeleccionTabla sel_fecha_asistencia) {
        this.sel_fecha_asistencia = sel_fecha_asistencia;
    }

    public SeleccionTabla getSel_fecha_asistencia_consulta() {
        return sel_fecha_asistencia_consulta;
    }

    public void setSel_fecha_asistencia_consulta(SeleccionTabla sel_fecha_asistencia_consulta) {
        this.sel_fecha_asistencia_consulta = sel_fecha_asistencia_consulta;
    }
    
}

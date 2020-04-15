/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horario;

/**
 *
 * @author ANDRES
 */
import framework.aplicacion.Fila;
import framework.aplicacion.TablaGenerica;
import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.BotonesCombo;
import framework.componentes.Combo;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Espacio;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Grupo;
import framework.componentes.Imagen;
import framework.componentes.ItemMenu;
import framework.componentes.PanelTabla;
import framework.componentes.Radio;
import framework.componentes.Reporte;
import framework.componentes.SeleccionCalendario;
import framework.componentes.SeleccionFormatoReporte;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import paq_asistencia.ejb.ServicioAsistencia;
import sistema.aplicacion.Pantalla;
import framework.componentes.Panel;
import org.primefaces.component.separator.Separator;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import paq_personal.ejb.ServicioPersonal;

public class HoraGeneracionHorario extends Pantalla{
    private Tabla tab_tabla = new Tabla();
    private Tabla tab_tabla1 = new Tabla();
    private Combo com_modalidad = new Combo();
    private Combo com_jornada = new Combo();
    private Panel panOpcion = new Panel();
    private Combo com_periodo_academico = new Combo();
    String jornada = "";
    String modalidad = "";
    String mension = "3, 10, 11";
    String dias = "6, 7, 8, 9, 10";
    private Object clase;
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    public HoraGeneracionHorario(){     
        bar_botones.getBot_insertar().setRendered(false);
        bar_botones.getBot_guardar().setRendered(false);
        bar_botones.getBot_eliminar().setRendered(false);
        bar_botones.getBot_inicio().setRendered(false);
        bar_botones.getBot_fin().setRendered(false);
        bar_botones.getBot_siguiente().setRendered(false);
        bar_botones.getBot_atras().setRendered(false);
        
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
      //  com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADÉMICO"));
        bar_botones.agregarComponente(com_periodo_academico);
        
        Boton bot_borrar = new Boton();
        bot_borrar.setValue("BORRAR HORARIO");
        bot_borrar.setIcon("ui-icon-print");
        bot_borrar.setMetodo("borrarHorario");
        bar_botones.agregarBoton(bot_borrar);
        
        Boton bot_consultar = new Boton();
        bot_consultar.setValue("CONSULTAR HORARIO");
        bot_consultar.setIcon("ui-icon-print");
        bot_consultar.setMetodo("consultarHorario");
        //bot_consultar.setOnclick("paq_horario:HoraPeriodoHoraTemporal");
        bar_botones.agregarBoton(bot_consultar);
        
        Imagen modalidadIma = new Imagen();
        modalidadIma.setValue("imagenes/logoinstituto_pequeño.png");
        modalidadIma.setStyle("display:block; margin:auto;");
        agregarComponente(modalidadIma);
        
       
        
        Panel tabp2 = new Panel();
        tabp2.setStyle("font-size:12px;color:black;text-align:center;");
        panOpcion.setId("panOpcion");
        panOpcion.setStyle("font-size:12px;color:black;text-align:left;");
        panOpcion.setTransient(true);
        
                
        Grid gri1 = new Grid();
        gri1.setMensajeInfo("Seleccione los parámetros solicitados para generar el horario de clases.");
        gri1.setStyle("width:75%;top:385px;");
       // gri1.setStyle("text-align:center;position:absolute;left:500px;top:385px;");
        gri1.setColumns(4);
        gri1.getChildren().add(new Etiqueta("<strong>SELECCIONE LA MODALIDAD:</strong> "));
        
        com_modalidad = new Combo();
        //com_modalidad.setMetodo("cambioTipoTransBanco");
        com_modalidad.setCombo(ser_estructura_organizacional.getModalidad("true"));
        gri1.getChildren().add(com_modalidad);
        
        gri1.getChildren().add(new Etiqueta("<strong>SELECCIONE LA JORNADA: </strong>"));
        com_jornada = new Combo();
        //com_modalidad.setMetodo("cambioTipoTransBanco");
        com_jornada.setCombo(ser_estructura_organizacional.getJornada("true"));
        gri1.getChildren().add(com_jornada);
        panOpcion.getChildren().add(gri1);
        panOpcion.getChildren().add(new Separator());
       
        Grid gri2 = new Grid();
        gri2.setStyle("width:94%;top:385px;");
        gri2.setColumns(4);
        gri2.getChildren().add(new Etiqueta("<strong>SELECCIONE LOS DIAS: </strong>"));
        
        // boton seleccion inversa 1
        Grupo gru_cuerpo = new Grupo();
        BotonesCombo boc_seleccion_inversa = new BotonesCombo();
        ItemMenu itm_todas = new ItemMenu();
        ItemMenu itm_niguna = new ItemMenu();
        boc_seleccion_inversa.setValue("Selección Inversa");
        boc_seleccion_inversa.setStyle("left:80;");
        boc_seleccion_inversa.setIcon("ui-icon-circle-check");
        boc_seleccion_inversa.setMetodo("seleccinarInversa");
        boc_seleccion_inversa.setUpdate("tab_tabla");
        itm_todas.setValue("Seleccionar Todo");
        itm_todas.setIcon("ui-icon-check");
        itm_todas.setMetodo("seleccionarTodas");
        itm_todas.setUpdate("tab_tabla");
        boc_seleccion_inversa.agregarBoton(itm_todas);
        itm_niguna.setValue("Seleccionar Ninguna");
        itm_niguna.setIcon("ui-icon-minus");
        itm_niguna.setMetodo("seleccionarNinguna");
        itm_niguna.setUpdate("tab_tabla");
        boc_seleccion_inversa.agregarBoton(itm_niguna);
            //configuracion de la tabla de valores o titulos (cabecera)
        
        tab_tabla = new Tabla();
        tab_tabla.setId("tab_tabla");
        tab_tabla.setSql(ser_horarios.getDia());
        tab_tabla.getColumna("ide_yhodia").setVisible(false);
        tab_tabla.getColumna("descripcion_yhodia").setNombreVisual(" ");
        tab_tabla.setNumeroTabla(1);
        tab_tabla.setCampoPrimaria("ide_yhodia");
        tab_tabla.setTipoSeleccion(true);
        tab_tabla.setLectura(true);
        tab_tabla.dibujar();

        gru_cuerpo.getChildren().add(boc_seleccion_inversa);
        Separator sep = new Separator();
        sep.setId("sep");
        sep.setStyle("width:0%;");
        gru_cuerpo.getChildren().add(sep);
        gru_cuerpo.getChildren().add(tab_tabla);
        gri2.getChildren().add(gru_cuerpo);

        
        // boton seleccion inversa 1
        Grupo gru_cuerpo2 = new Grupo();
        BotonesCombo boc_seleccion_inversa2 = new BotonesCombo();
        ItemMenu itm_todas2 = new ItemMenu();
        ItemMenu itm_niguna2 = new ItemMenu();
        boc_seleccion_inversa2.setValue("Selección Inversa");
        boc_seleccion_inversa2.setStyle("left:80;");
        boc_seleccion_inversa2.setIcon("ui-icon-circle-check");
        boc_seleccion_inversa2.setMetodo("seleccinarInversa2");
        boc_seleccion_inversa2.setUpdate("tab_tabla1");
        itm_todas2.setValue("Seleccionar Todo");
        itm_todas2.setIcon("ui-icon-check");
        itm_todas2.setMetodo("seleccionarTodas2");
        itm_todas2.setUpdate("tab_tabla1");
        boc_seleccion_inversa2.agregarBoton(itm_todas2);
        itm_niguna2.setValue("Seleccionar Ninguna");
        itm_niguna2.setIcon("ui-icon-minus");
        itm_niguna2.setMetodo("seleccionarNinguna2");
        itm_niguna2.setUpdate("tab_tabla1");
        boc_seleccion_inversa2.agregarBoton(itm_niguna2);
        
        
        gri2.getChildren().add(new Etiqueta("<strong>SELECCIONE LAS CARRERAS: </strong>"));
        tab_tabla1 = new Tabla();
        tab_tabla1.setId("tab_tabla1");
        tab_tabla1.setSql("SELECT ide_ystmen, descripcion_ystmen FROM yavirac_stror_mension order by descripcion_ystmen");
        tab_tabla1.getColumna("ide_ystmen").setVisible(false);
        tab_tabla1.getColumna("descripcion_ystmen").setNombreVisual(" ");
        tab_tabla1.setCampoPrimaria("ide_ystmen");
        tab_tabla.setNumeroTabla(2);
        tab_tabla1.setTipoSeleccion(true);
        tab_tabla1.setLectura(true);
        tab_tabla1.dibujar();
        
        gru_cuerpo2.getChildren().add(boc_seleccion_inversa2);
        Separator sep2 = new Separator();
        sep2.setId("sep2");
        sep2.setStyle("width:0%;");
        gru_cuerpo2.getChildren().add(sep2);
        gru_cuerpo2.getChildren().add(tab_tabla1);
        gri2.getChildren().add(gru_cuerpo2);
        
        
        panOpcion.getChildren().add(gri2);
        panOpcion.getChildren().add(new Separator());
        Boton bot_aceptar = new Boton();
        bot_aceptar.setValue("GENERAR HORARIO CLASE");
        
        
        bot_aceptar.setMetodo("generaHorarioClaseSNR");
        //bot_aceptar.setMetodo("generaHorarioClaseCNR");
       // bot_aceptar.setMetodo("generaHorarioRespaldoCNR");
        //bot_aceptar.setMetodo("generaHorarioRespaldoSNR");
        
        
        bot_aceptar.setStyle("text-align:center;position:absolute;top:575px;left:575px;");
        tabp2.getChildren().add(panOpcion);
        tabp2.getChildren().add(bot_aceptar);
        agregarComponente(tabp2);
    }
    
    public void consultarHorario(){
        utilitario.cargarPantalla("paq_horario", "HoraPeriodoHora");
        
    }
    
    public void borrarHorario(){
        String carreras = tab_tabla1.getFilasSeleccionadas();
        if (com_periodo_academico.getValue() == null && com_jornada.getValue() == null ){
            utilitario.agregarMensajeError("No se puede Borrar", "Debe seleccionar Periodo académico, modalidad, jornada y las carreras para borrar los horarios");
        } else{
            utilitario.getConexion().ejecutarSql("update yavirac_hora_periodo_hor_temp set activo_yhopeh = false where ide_ystmod = "+com_modalidad.getValue()+" and ide_ystjor = "+com_jornada.getValue()+" and ide_ystpea = "+com_periodo_academico.getValue()+" and ide_ystmen in ("+carreras+")");
            utilitario.agregarMensaje("El horario ha sido borrado exitosamente", "");
        }
    }
    
    public void generaHorarioClaseSNR(){
        if (com_periodo_academico.getValue() != null){
        
        utilitario.getConexion().ejecutarSql("delete from tab_temp");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_horario_mate");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_periodo_hor");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_horario_mate_temp");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_periodo_hor_temp");
        utilitario.getConexion().ejecutarSql(ser_horarios.ingresaHorariosinReceso());
        utilitario.getConexion().ejecutarSql(ser_horarios.ingresaHorariosinRecesoTemp());
        insertarRecesoTemp();      
        insertaHorasClaseTemp();
        utilitario.agregarMensaje("El horario se ha generado correctamente", "");
        } else {
            utilitario.agregarMensajeError("Debe seleccionar el Periodo Académico", "");
        }
    }
    
    public void generaHorarioClaseCNR(){
        if (com_periodo_academico.getValue() != null){
        utilitario.getConexion().ejecutarSql("delete from tab_temp");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_horario_mate");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_periodo_hor");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_horario_mate_temp");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_periodo_hor_temp");
        utilitario.getConexion().ejecutarSql(ser_horarios.ingresaHorariosconReceso());
        utilitario.getConexion().ejecutarSql(ser_horarios.ingresaHorariosconRecesoTemp());
        insertarRecesoTemp();
        insertaHorasClaseTemp();
        utilitario.agregarMensaje("El horario se ha generado correctamente", "");
        } else{
            utilitario.agregarMensajeError("Debe seleccionar el Periodo Académico", ""); 
        }
    }
    public void generaHorarioRespaldoCNR(){
        if (com_periodo_academico.getValue() != null){
        utilitario.getConexion().ejecutarSql("delete from tab_temp");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_horario_mate");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_periodo_hor");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_horario_mate_temp");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_periodo_hor_temp");
        utilitario.getConexion().ejecutarSql(ser_horarios.ingresaHorarioRespaldoCNR());
        utilitario.getConexion().ejecutarSql(ser_horarios.ingresaHorarioRespaldoTempCNR());
        insertarRecesoTemp();
        insertaHorasClaseTemp();
        utilitario.agregarMensaje("El horario se ha generado correctamente", "");
        } else {
             utilitario.agregarMensajeError("Debe seleccionar el Periodo Académico", ""); 
        }
    }
    public void generaHorarioRespaldoSNR(){
        if (com_periodo_academico.getValue() != null){
        utilitario.getConexion().ejecutarSql("delete from tab_temp");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_horario_mate");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_periodo_hor");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_horario_mate_temp");
        utilitario.getConexion().ejecutarSql("delete from yavirac_hora_periodo_hor_temp");
        utilitario.getConexion().ejecutarSql(ser_horarios.ingresaHorarioRespaldoSNR());
        utilitario.getConexion().ejecutarSql(ser_horarios.ingresaHorarioRespaldoTempSNR());
        insertarRecesoTemp();
        insertaHorasClaseTemp();
        utilitario.agregarMensaje("El horario se ha generado correctamente", "");
        } else {
             utilitario.agregarMensajeError("Debe seleccionar el Periodo Académico", ""); 
        }
    }
    
    public void seleccinarInversa() {
        if (tab_tabla.getSeleccionados() == null) {
            seleccionarTodas();
        } else if (tab_tabla.getSeleccionados().length == tab_tabla.getTotalFilas()) {
            seleccionarNinguna();
        } else {
            Fila seleccionados[] = new Fila[tab_tabla.getTotalFilas() - tab_tabla.getSeleccionados().length];
            int cont = 0;
            for (int i = 0; i < tab_tabla.getFilas().size(); i++) {
                boolean boo_selecionado = false;
                for (int j = 0; j < tab_tabla.getSeleccionados().length; j++) {
                    if (tab_tabla.getSeleccionados()[j].equals(tab_tabla.getFilas().get(i))) {
                        boo_selecionado = true;
                        break;
                    }
                }
                if (boo_selecionado == false) {
                    seleccionados[cont] = tab_tabla.getFilas().get(i);
                    cont++;
                }
            }
            tab_tabla.setSeleccionados(seleccionados);
        }
        //calculoTotal();
    }
     
    public void seleccionarTodas() {
        tab_tabla.setSeleccionados(null);
        Fila seleccionados[] = new Fila[tab_tabla.getTotalFilas()];
        for (int i = 0; i < tab_tabla.getFilas().size(); i++) {
            seleccionados[i] = tab_tabla.getFilas().get(i);
        }
        tab_tabla.setSeleccionados(seleccionados);
        //calculoTotal();

    }
    /**
     * DFJ*
     */
    public void seleccionarNinguna() {
        tab_tabla.setSeleccionados(null);
        //txt_total.setValue(utilitario.getFormatoNumero(0,2));
        //utilitario.addUpdate("txt_total");
    } 
    
    public void seleccinarInversa2() {
        if (tab_tabla1.getSeleccionados() == null) {
            seleccionarTodas2();
        } else if (tab_tabla1.getSeleccionados().length == tab_tabla1.getTotalFilas()) {
            seleccionarNinguna2();
        } else {
            Fila seleccionados[] = new Fila[tab_tabla1.getTotalFilas() - tab_tabla1.getSeleccionados().length];
            int cont = 0;
            for (int i = 0; i < tab_tabla1.getFilas().size(); i++) {
                boolean boo_selecionado = false;
                for (int j = 0; j < tab_tabla1.getSeleccionados().length; j++) {
                    if (tab_tabla1.getSeleccionados()[j].equals(tab_tabla1.getFilas().get(i))) {
                        boo_selecionado = true;
                        break;
                    }
                }
                if (boo_selecionado == false) {
                    seleccionados[cont] = tab_tabla1.getFilas().get(i);
                    cont++;
                }
            }
            tab_tabla1.setSeleccionados(seleccionados);
        }
        //calculoTotal();
    }
     
    public void seleccionarTodas2() {
        tab_tabla1.setSeleccionados(null);
        Fila seleccionados[] = new Fila[tab_tabla1.getTotalFilas()];
        for (int i = 0; i < tab_tabla1.getFilas().size(); i++) {
            seleccionados[i] = tab_tabla1.getFilas().get(i);
        }
        tab_tabla1.setSeleccionados(seleccionados);
        //calculoTotal();

    }
    /**
     * DFJ*
     */
    public void seleccionarNinguna2() {
        tab_tabla1.setSeleccionados(null);
        //txt_total.setValue(utilitario.getFormatoNumero(0,2));
        //utilitario.addUpdate("txt_total");
    } 
    
     public void insertarRecesoTemp(){
        
        jornada = com_jornada.getValue().toString();
        modalidad = com_modalidad.getValue().toString();
        TablaGenerica receso = utilitario.consultar(ser_horarios.getDefinicionReceso(jornada, com_periodo_academico.getValue().toString(), modalidad, utilitario.getVariable("p_tipo_receso"),"true" ));
        String maximo = "";
        receso.imprimirSql();
        TablaGenerica tab_dias = utilitario.consultar(ser_horarios.getNumDias(dias));
        TablaGenerica tab_mension = utilitario.consultar(ser_horarios.getNumMension(mension));
        for (int i=0;i<receso.getTotalFilas();i++){
            for(int j=0; j<tab_dias.getTotalFilas();j++){ 
                for (int k=0; k<tab_mension.getTotalFilas();k++){
                  TablaGenerica codigo_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("tab_temp", "ide_yhopeh"));
                  maximo = codigo_maximo.getValor("maximo");
                  String sql = "insert into tab_temp (ide_yhopeh, ide_ystmod, ide_ystjor, ide_yhohor, ide_ystpea, ide_yhodia, ide_yhothj, horainicial_yhopeh, horafinal_yhopeh, activo_yhopeh, ide_ystmen)\n" +
                               "values ("+maximo+", "+receso.getValor(i, "ide_ystmod")+", "+receso.getValor(i, "ide_ystjor")+", "+utilitario.getVariable("p_tipo_hora")+", "+com_periodo_academico.getValue()+", "+tab_dias.getValor(j, "ide_yhodia")+", "+utilitario.getVariable("p_tipo_receso")+", '"+receso.getValor(i, "hora_inicio_yhodeh")+"', '"+receso.getValor(i, "hora_final_yhodeh")+"', "+receso.getValor(i, "activo_yhodeh")+", "+tab_mension.getValor(k, "ide_ystmen")+")";
                              // System.out.print(sql);
                  utilitario.getConexion().ejecutarSql(sql);
                  
               //   tab_hora_periodo_hora.actualizar();
            }
          }
        }
    }
    
    
     
     public void insertaHorasClaseTemp(){
        double numero_horas_entrada=0;
        double numero_horas_receso=0;
        int numero_horas_total=0;
        double numero_horas_clase=0;
        String maximo="";
        String hora_ini="";
        String hora_fin="";
        boolean receso=false;
        TablaGenerica tab_entrada_salida = utilitario.consultar(ser_horarios.getDefinicionReceso(jornada, com_periodo_academico.getValue().toString(), modalidad, utilitario.getVariable("p_tipo_entrada_salida"),"true" ));
        TablaGenerica tab_periodo= utilitario.consultar("select * from yavirac_stror_periodo_academic where ide_ystpea="+com_periodo_academico.getValue());
        numero_horas_clase = Double.parseDouble(tab_periodo.getValor("hora_clase_ystpea"));
        for (int i=0;i<tab_entrada_salida.getTotalFilas();i++){
            
               utilitario.getConexion().ejecutarSql("delete from yavirac_hora_matriz; ");
               TablaGenerica tab_hora_entrada=utilitario.consultar(utilitario.getDiferenciaHorasMinutos(tab_entrada_salida.getValor(i,"hora_inicio_yhodeh"), tab_entrada_salida.getValor(i,"hora_final_yhodeh")));
               numero_horas_entrada = Double.parseDouble(tab_hora_entrada.getValor("resultado"));
               TablaGenerica tab_receso = utilitario.consultar("select * from yavirac_hora_definicion_hora where ide_ystpea="+com_periodo_academico.getValue()+" and ide_yhothj="+utilitario.getVariable("p_tipo_receso")+" and ide_ystjor="+tab_entrada_salida.getValor(i, "ide_ystjor")+" and ide_ystmod="+tab_entrada_salida.getValor(i, "ide_ystmod"));
              
               if(tab_receso.getTotalFilas()>0){
                    TablaGenerica tab_hora_receso=utilitario.consultar(utilitario.getDiferenciaHorasMinutos(tab_receso.getValor("hora_inicio_yhodeh"), tab_receso.getValor("hora_final_yhodeh")));
                    numero_horas_receso = Double.parseDouble(tab_hora_receso.getValor("resultado"));
                    receso=true;
               }
               else {
                   receso=false;
               }
               double opera=(numero_horas_entrada+numero_horas_receso)/numero_horas_clase;
               TablaGenerica resul_int=utilitario.consultar("select 1 as codigo,cast( cast('"+opera+"' as numeric) as integer) as res");
               numero_horas_total=Integer.parseInt(resul_int.getValor("res"));
           //    System.out.println("horas enteras "+numero_horas_total);
               //if(utilitario.isEnteroPositivo(numero_horas_total+"")){
               if(1==1){
                //    System.out.println("entre  horas enteras "+numero_horas_total);
                   TablaGenerica tab_inicia_hora = utilitario.consultar("select * from yavirac_stror_jornada where ide_ystjor="+tab_entrada_salida.getValor(i, "ide_ystjor")); 
                   int inicia_hora= Integer.parseInt(tab_inicia_hora.getValor("inicia_hora_ystjor"));
                   for (int j=1;j<Integer.parseInt(numero_horas_total+"");j++){
                   TablaGenerica codigo_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_hora_matriz", "ide_yhomat"));
                   maximo = codigo_maximo.getValor("maximo");
                        if(j==1){
                        //    System.out.println("valor j "+j);
                            hora_ini=tab_entrada_salida.getValor(i, "hora_inicio_yhodeh");
                       //     System.out.println("valor hora_ini "+hora_ini);
                        }
                        else{
                        //    System.out.println("valor j else "+j);
                            TablaGenerica tab_hora_ini = utilitario.consultar("select * from yavirac_hora_matriz where ide_yhomat="+(j));
                            hora_ini= tab_hora_ini.getValor("hora_fin_yhomat");
                          //  System.out.println("valor hora_ini else "+hora_ini);
                        }
                      //   System.out.println("valor j suma "+j);
                        int horas_sumar=Integer.parseInt(tab_periodo.getValor("hora_clase_ystpea"))*60;
                     //   System.out.println("valor horas_sumar suma "+horas_sumar);
                        TablaGenerica tab_hora_fin= utilitario.consultar(utilitario.getSumaHoras(hora_ini, horas_sumar+""));
                        hora_fin=tab_hora_fin.getValor("hora_nueva");
                      //  System.out.println("hora_fin "+hora_fin);
                        
                        if(receso){
                            //TablaGenerica tab_valida_receso=utilitario.consultar(ser_horarios.getResultadoExisteReceso(tab_receso.getValor("hora_inicio_yhodeh"), tab_entrada_salida.getValor(i,"hora_inicio_yhodeh"), tab_entrada_salida.getValor(i,"hora_final_yhodeh")));
                            TablaGenerica tab_valida_receso=utilitario.consultar(ser_horarios.getResultadoExisteReceso(tab_receso.getValor("hora_inicio_yhodeh"), hora_ini, hora_fin));
                            if(tab_valida_receso.getTotalFilas()>0){
                                hora_ini=tab_receso.getValor("hora_final_yhodeh");
                                TablaGenerica tab_hora_f= utilitario.consultar(utilitario.getSumaHoras(hora_ini, horas_sumar+""));
                                hora_fin=tab_hora_f.getValor("hora_nueva");
                            }
                        }
                    //    System.out.println("hora_fin saliendo else"+hora_fin);
                        utilitario.getConexion().ejecutarSql("INSERT INTO yavirac_hora_matriz(ide_yhomat, hora_inicio_yhomat, hora_fin_yhomat, orden_hora_yhomat)" +
                        " VALUES ('"+maximo+"','"+hora_ini+"','"+hora_fin+"','"+inicia_hora+"');");
                        inicia_hora=inicia_hora+1;                        
                   }
                   TablaGenerica tab_matriz_hora= utilitario.consultar("select * from yavirac_hora_matriz");
                   for(int k=0;k<tab_matriz_hora.getTotalFilas();k++){
                       
                        TablaGenerica tab_dias = utilitario.consultar(ser_horarios.getNumDias(dias));
                        TablaGenerica tab_mension = utilitario.consultar(ser_horarios.getNumMension(mension));
                         
                             for(int m=0; m<tab_dias.getTotalFilas();m++){ 
                                 
                                for (int n=0; n<tab_mension.getTotalFilas();n++){
                       
                       TablaGenerica codigo_maximo_hora = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("tab_temp ", "ide_yhopeh"));
                       maximo = codigo_maximo_hora.getValor("maximo");
                       
                       TablaGenerica tab_hohor=utilitario.consultar("select ide_yhohor,orden_yhohor from yavirac_hora_hora where orden_yhohor="+tab_matriz_hora.getValor(k, "orden_hora_yhomat"));
                       String ide_hohor=tab_hohor.getValor("ide_yhohor");
                       
                       utilitario.getConexion().ejecutarSql("INSERT INTO tab_temp(ide_yhopeh, ide_ystmod, ide_ystjor, ide_yhohor, ide_ystpea, horainicial_yhopeh,horafinal_yhopeh, activo_yhopeh, ide_yhodia, ide_yhothj, ide_ystmen)\n" +
                        " VALUES ('"+maximo+"', '"+tab_entrada_salida.getValor(i, "ide_ystmod")+"', '"+tab_entrada_salida.getValor(i, "ide_ystjor")+"', '"+ide_hohor+"', '"+com_periodo_academico.getValue()+"', '"+tab_matriz_hora.getValor(k, "hora_inicio_yhomat")+"','"+tab_matriz_hora.getValor(k, "hora_fin_yhomat")+"', 'true', '"+tab_dias.getValor(m, "ide_yhodia")+"', '"+utilitario.getVariable("p_tipo_entrada_salida")+"', '"+tab_mension.getValor(n, "ide_ystmen")+"');");
                       
                                }
                             }
                        
                    }
               }
               else{
                  //  System.out.println("elseee    horas enteras "+numero_horas_total);
                   utilitario.agregarMensajeError("No se puede continuar", "No se encuentra bien definidos las horas clase");
                   return;
               }
                //System.out.print(receso);
           }
    }
     
     
    @Override
    public void insertar() {
    }

    @Override
    public void guardar() {

    }

    @Override
    public void eliminar() {
       
    }

    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    }

    public Combo getCom_modalidad() {
        return com_modalidad;
    }

    public void setCom_modalidad(Combo com_modalidad) {
        this.com_modalidad = com_modalidad;
    }

    public Combo getCom_jornada() {
        return com_jornada;
    }

    public void setCom_jornada(Combo com_jornada) {
        this.com_jornada = com_jornada;
    }

    public Panel getPanOpcion() {
        return panOpcion;
    }

    public void setPanOpcion(Panel panOpcion) {
        this.panOpcion = panOpcion;
    }

    public Tabla getTab_tabla1() {
        return tab_tabla1;
    }

    public void setTab_tabla1(Tabla tab_tabla1) {
        this.tab_tabla1 = tab_tabla1;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public Object getClase() {
        return clase;
    }

    public void setClase(Object clase) {
        this.clase = clase;
    }
    
}


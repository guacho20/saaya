/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.aplicacion.TablaGenerica;
import framework.componentes.AutoCompletar;
import framework.componentes.Barra;
import framework.componentes.Boton;
import framework.componentes.Dialogo; 
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Reporte;
import framework.componentes.SeleccionCalendario;
import framework.componentes.SeleccionFormatoReporte;
import framework.componentes.Tabla;
import framework.componentes.Upload;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import jxl.Sheet;
import jxl.Workbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
public class Biometrico extends Pantalla {
  
    private AutoCompletar aut_opciones=new AutoCompletar();
    private SeleccionCalendario sec_rango_fechas= new SeleccionCalendario();
    private Tabla tab_biometrico = new Tabla();
    private Upload upl_archivo=new Upload();
    private Dialogo dia_subir_archivo = new Dialogo();
    private List<String[]> lis_importa=null; //Guardo los empleados y el valor del rubro
    private Reporte rep_reporte=new Reporte();
    private SeleccionFormatoReporte sel_rep=new SeleccionFormatoReporte();
    private SeleccionCalendario sel_fechas = new SeleccionCalendario();

    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
     @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
     
     @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
  
          

       
   public Biometrico() {
       
       
       
       aut_opciones.setId("aut_opciones");
       aut_opciones.setAutoCompletar(ser_personal.getDatopersonal("true,false"));
       aut_opciones.setMetodoChange("seleccionarAutocompletar");
       bar_botones.agregarComponente(new Etiqueta("EMPLEADO:"));
       bar_botones.agregarComponente(aut_opciones);
       
       Boton bot_clean = new Boton();
       bot_clean.setIcon("ui-icon-cancel");
            bot_clean.setTitle("Limpiar");
            bot_clean.setMetodo("limpiar");
            bar_botones.agregarComponente(bot_clean);

       
       rep_reporte.setId("rep_reporte");
       rep_reporte.getBot_aceptar().setMetodo("aceptarReporte");
       agregarComponente(rep_reporte);
       bar_botones.agregarReporte();
       sel_rep.setId("sel_rep");
       agregarComponente(sel_rep);

       Boton bot_abrir=new Boton();
       bot_abrir.setValue("Seleccionar Fechas");
       bot_abrir.setIcon("ui-calendario");
       bot_abrir.setMetodo("abrirRango");
       bar_botones.agregarBoton(bot_abrir);
       
       sec_rango_fechas.setId("sec_rango_fechas");
       sec_rango_fechas.getBot_aceptar().setMetodo("aceptarRango");
       sec_rango_fechas.setFechaActual();
       agregarComponente(sec_rango_fechas);
       
       bar_botones.getBot_guardar().setRendered(false);
       bar_botones.getBot_eliminar().setRendered(false);
       bar_botones.getBot_insertar().setRendered(false);
       
       Boton bot_abrir_upload = new Boton();
       bot_abrir_upload.setValue("Subir Marcaciones");
       bot_abrir_upload.setIcon("ui-icon-circle-arrow-n");//set icono Subir Marcacion///
       bot_abrir_upload.setMetodo("abrirUpload");
        bar_botones.agregarBoton(bot_abrir_upload);
       
       dia_subir_archivo.setId("dia_subir_archivo");
       dia_subir_archivo.setTitle("Subir Marcaciones Biometrico");
       dia_subir_archivo.getBot_aceptar().setRendered(false);
       dia_subir_archivo.setWidth("40%");
       dia_subir_archivo.setHeight("15%");
       agregarComponente(dia_subir_archivo);
       
       tab_biometrico.setId("tab_biometrico");
        tab_biometrico.setTabla("yavirac_asis_biometrico","ide_yasbio",1);
        tab_biometrico.setCondicion("ide_yasbio=-1");
        tab_biometrico.setHeader("REGISTRO DE MARCACIONES DEL RELOJ BIOMETRICO");
        //tab_biometrico.setLectura(true);
        tab_biometrico.getColumna("indice_yasbio").setLectura(true);
        tab_biometrico.getColumna("hora_yasbio").setLectura(true);
        tab_biometrico.getColumna("fecha_yasbio").setLectura(true);
        tab_biometrico.getColumna("puerta_yasbio").setLectura(true);
        tab_biometrico.getColumna("num_yasbio").setLectura(true);
        tab_biometrico.getColumna("nombre_yasbio").setLectura(true);
        tab_biometrico.getColumna("departamento_yasbio").setLectura(true);
        tab_biometrico.getColumna("fecha_registro_yasbio").setLectura(true);
        tab_biometrico.getColumna("codigo_reloj_yasbio").setLectura(true);
        tab_biometrico.dibujar();
        //tab_biometrico.setRows(10);
        
        PanelTabla pat_biometrico = new PanelTabla();
        pat_biometrico.setId("pat_biometrico");
        pat_biometrico.setPanelTabla(tab_biometrico);
       
        Division div_biometrico = new Division();
        div_biometrico.setId("div_biometrico");
        div_biometrico.dividir1(pat_biometrico);
        
        agregarComponente(div_biometrico);
        
        upl_archivo.setId("upl_archivo");
		upl_archivo.setMetodo("validarArchivo");

		//upl_archivo.setUpdate("gri_valida");	//	
		upl_archivo.setAuto(false);
		upl_archivo.setAllowTypes("/(\\.|\\/)(xls)$/");
		upl_archivo.setUploadLabel("Validar");
		upl_archivo.setCancelLabel("Cancelar Seleccion");
               
         //bar_botones.agregarComponente(upl_archivo);
                
                Grid gri_valor=new Grid();
		gri_valor.setColumns(2);
		gri_valor.getChildren().add(new Etiqueta("Seleccione Archivo: "));
		gri_valor.getChildren().add(upl_archivo);
		dia_subir_archivo.setDialogo(gri_valor);    
          sel_fechas.setId("sel_fechas");
          sel_fechas.setFechaActual();
          sel_fechas.getBot_aceptar().setMetodo("aceptarReporte");
          
          agregarComponente(sel_fechas);
                
      } 
   public void limpiar(){
       aut_opciones.limpiar();
       tab_biometrico.setCondicion("ide_yasbio=-1");
       tab_biometrico.ejecutarSql();
       utilitario.addUpdate("tab_biometrico");
   }
   public void seleccionarAutocompletar(SelectEvent evt){
       
       
           TablaGenerica tab_codigo_reloj = utilitario.consultar(ser_personal.getDatoPersonalCodigo(aut_opciones.getValor()));
           tab_biometrico.setCondicion("num_yasbio = "+tab_codigo_reloj.getValor("codigo_reloj_ypedpe"));
           tab_biometrico.ejecutarSql();
           utilitario.addUpdate("tab_biometrico");
           sec_rango_fechas.cerrar();  
   }

   public void abrirRango(){
      sec_rango_fechas.dibujar();
   }
   public void aceptarRango(){
       if (sec_rango_fechas.isFechasValidas()){
           
           tab_biometrico.setCondicion("fecha_yasbio between '"+sec_rango_fechas.getFecha1String()+"'  and '"+sec_rango_fechas.getFecha2String()+"'");
           tab_biometrico.ejecutarSql();
           utilitario.addUpdate("tab_biometrico");
           sec_rango_fechas.cerrar();
       }
       else
       {
           utilitario.agregarMensajeError("Las fechas selccionadas no son validas", "");
       }
   }    
   public void mensaje(FileUploadEvent evt){
       System.out.println("rrrrrrrrr");
       utilitario.agregarMensaje("entre", "voi va");
       return;
   }
   public void validarArchivo(FileUploadEvent evt){	
                                       //System.out.println("entre a metodo dubir ");

			//Leer el archivo
			String str_msg_info="";
			String str_msg_adve="";
			String str_msg_erro="";
			double dou_tot_valor_imp=0;
			try {
				//Válido que el rubro seleccionado este configurado en los tipo de nomina

				Workbook archivoExcel = Workbook.getWorkbook(evt.getFile().getInputstream());
				Sheet hoja = archivoExcel.getSheet(0);//LEE LA PRIMERA HOJA
				if (hoja == null) {
					utilitario.agregarMensajeError("No existe ninguna hoja en el archivo seleccionado", "");
					return;
				}
				int int_fin = hoja.getRows();
				upl_archivo.setNombreReal(evt.getFile().getFileName());
				str_msg_info+=getFormatoInformacion("El archivo "+upl_archivo.getNombreReal()+" contiene "+int_fin+" filas");
                                //System.out.println("entre a str_msg_info "+str_msg_info);
				lis_importa=new ArrayList<String[]>();
	
				
				for (int i = 0; i < int_fin; i++) {
					//codigo tercero remplaza a str_cedula permite leer el codigo de la factutra
					
            
                    String str_indice = hoja.getCell(0, i).getContents();	
		str_indice=str_indice.trim(); 
                                
					String str_hora =hoja.getCell(1, i).getContents();
					str_hora = str_hora.trim();
					
					String str_fecha =hoja.getCell(2, i).getContents();
					str_fecha = str_fecha.trim();
                                        
                                        
                    String str_puerta =hoja.getCell(3, i).getContents();
					str_puerta = str_puerta.trim();
                                        
                    String str_num =hoja.getCell(4, i).getContents();
					str_num = str_num.trim();
                                        
										
                    String str_nombre =hoja.getCell(5, i).getContents();
					str_nombre = str_nombre.trim();
                                        
                    String str_departemento =hoja.getCell(6, i).getContents();
					str_departemento = str_departemento.trim();
					
                    String str_fecha_registro =hoja.getCell(7, i).getContents();
					str_fecha_registro = str_fecha_registro.trim();
					
		    String str_codigo_reloj =hoja.getCell(8, i).getContents();
					str_codigo_reloj = str_codigo_reloj.trim();
					
					
		//System.out.println("imprimo el valor del Indice "+str_indice+"  hora marcacion" +str_hora+" imprime la fecha " +str_fecha+" Imprime el numero de puerta" +str_puerta+"  Imprime el numero de usuario" +str_num+"  Imprime el nombre del Usuario" +str_nombre+"  imprime en que departamento se encuentra el reloj" +str_departemento
		//+"Imprime la fecha de registro" +str_fecha_registro+"Imprime el codigo del reloj" +str_codigo_reloj );
					
					TablaGenerica tab_maximo= utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_asis_biometrico", "ide_yasbio"));
                                        
                                        String str_maximo= tab_maximo.getValor("maximo");
                                        
                                        utilitario.getConexion().ejecutarSql(ser_asistencia.insertBiometrico(str_maximo, str_indice, str_hora, str_fecha, str_puerta, str_num, str_nombre, str_departemento,str_fecha, str_codigo_reloj,utilitario.getVariable("NICK"),utilitario.getFechaActual(), utilitario.getHoraActual()));
                                        /*
					TablaGenerica tab_factura=ser_facturacion.getDatosClienteFactura(str_cedula_cliente, str_codigo_tercero);

					if(tab_factura.isEmpty() ){
						//No existe el documento en la tabla de tab_factura
						str_msg_erro+=getFormatoError("El documento de Identidad: "+str_cedula_cliente+" no se encuentra registrado en la base de datos, fila "+(i+1));
					}
					
					String str_valor_conciliar = hoja.getCell(3, i).getContents();
					String str_valor = hoja.getCell(19, i).getContents();
					double double_valor_conciliar= Double.parseDouble(str_valor_conciliar.replace(",", "."));
					System.out.println("imprimo valro a conciliar "+str_valor);
					tab_tabla.insertar();
					tab_tabla.setValor(0, "valor_conciliado_fafac", double_valor_conciliar+"");
                                            */
				}
				
				utilitario.agregarMensaje("Subido con exito", "para consultar ingrese las fechas en las que desea consultar");
				utilitario.addUpdate("tab_tabla");;
                                //upl_archivo.

			} catch (Exception e) {
				// TODO: handle exception
			}
    }
    /**
	 * Genera un mensaje de información color azul
	 * @param mensaje
	 * @return
	 */
	private String getFormatoInformacion(String mensaje){
		return "<div><font color='#3333ff'><strong>*&nbsp;</strong>"+mensaje+"</font></div>";	
	}
	/**
	 * Genera un mensaje de Error color rojo
	 * @param mensaje
	 * @return
	 */
	private String getFormatoError(String mensaje){
		return "<div><font color='#ff0000'><strong>*&nbsp;</strong>"+mensaje+"</font></div>";	
	}                        
   public void abrirUpload(){
    
       dia_subir_archivo.dibujar();
   }

public void abrirListaReportes() {
// TODO Auto-generated method stub
rep_reporte.dibujar();
}

public void aceptarReporte() {
    if (rep_reporte.getReporteSelecionado().equals("Marcaciones Biometrico")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_fechas.dibujar();
        }
        else if (sel_fechas.isVisible()){
                
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pfechainicial", sel_fechas.getFecha1String());
                map_parametros.put("pfechafinal", sel_fechas.getFecha2String());
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_fechas.cerrar();
                sel_rep.dibujar();            
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    }
}

   
   
    @Override
    public void insertar() {
        tab_biometrico.insertar();
    }

    @Override
    public void guardar() {
        tab_biometrico.guardar();
        guardarPantalla();
        
    }
    
    @Override
    public void eliminar() {
        tab_biometrico.eliminar();
    }

    
    

    public Tabla getTab_biometrico() {
        return tab_biometrico;
    }

    public void setTab_biometrico(Tabla tab_biometrico) {
        this.tab_biometrico = tab_biometrico;
    }

    public SeleccionCalendario getSec_rango_fechas() {
        return sec_rango_fechas;
    }

    public void setSec_rango_fechas(SeleccionCalendario sec_rango_fechas) {
        this.sec_rango_fechas = sec_rango_fechas;
    }

    public AutoCompletar getAut_opciones() {
        return aut_opciones;
    }

    public void setAut_opciones(AutoCompletar aut_opciones) {
        this.aut_opciones = aut_opciones;
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

    public SeleccionCalendario getSel_fechas() {
        return sel_fechas;
    }

    public void setSel_fechas(SeleccionCalendario sel_fechas) {
        this.sel_fechas = sel_fechas;
    }



    
}

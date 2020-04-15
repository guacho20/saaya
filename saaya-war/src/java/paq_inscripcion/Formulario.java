package paq_inscripcion;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.VisualizarPDF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;import java.util.Map;
;
import javax.ejb.EJB;
import org.primefaces.event.SelectEvent;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import paq_matricula.ejb.ServicioMatriculas;
import paq_titulacion.ejb.ServicioTitulacion;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author USER
 */
public class Formulario extends Pantalla{
    private Combo com_periodo_academico = new Combo(); 
    private Tabla tab_formulario_alumno=new Tabla();
    private VisualizarPDF vipdf_comprobante = new VisualizarPDF();
    
    @EJB
    private final ServicioEstructuraOrganizacional ser_formulario = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    @EJB
    private final ServicioTitulacion ser_formulario1 = (ServicioTitulacion) utilitario.instanciarEJB(ServicioTitulacion.class);
    
    @EJB
    private final ServicioPersonal ser_formulario2 = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioMatriculas ser_matricula = (ServicioMatriculas) utilitario.instanciarEJB(ServicioMatriculas.class);
    @EJB
    private final ServiciosHorarios ser_horario = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);

    
    public Formulario (){
        
        
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_formulario.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADÉMICO"));
        bar_botones.agregarComponente(com_periodo_academico);
        
        /*
          //bar_botones.agregarBoton(bot_anular);
        Boton bot_imprimir = new Boton();
        bot_imprimir.setIcon("ui-icon-print");
        bot_imprimir.setValue("FORMULARIO MATRICULA");
        bot_imprimir.setMetodo("generarPDF");

        bar_botones.agregarBoton(bot_imprimir);
        */
        tab_formulario_alumno.setId("tab_formulario_alumno");
        tab_formulario_alumno.setTabla("yavirac_ins_formulario", "ide_yinfor",1);
        tab_formulario_alumno.setCondicion("ide_ystpea=-1");
        tab_formulario_alumno.getColumna("ide_ystsex").setCombo(ser_formulario.getSexo());
        tab_formulario_alumno.getColumna("ide_ysttid").setCombo(ser_formulario.getDiscapacidad());
        tab_formulario_alumno.getColumna("ide_ystgen").setCombo(ser_formulario.getGenero("true,false"));
        tab_formulario_alumno.getColumna("ide_ystpaf").setCombo(ser_formulario.getParentezcoFamiliar("true,false"));
        tab_formulario_alumno.getColumna("ide_ystetn").setCombo(ser_formulario.getEtnia());
        tab_formulario_alumno.getColumna("ide_ystnac").setCombo(ser_formulario.getNacionalidad("true,false"));
        tab_formulario_alumno.getColumna("ide_ysttis").setCombo(ser_formulario.getTipoSangre("true,false"));
        tab_formulario_alumno.getColumna("ide_ystesc").setCombo(ser_formulario.getEstadoCivil("true,false"));
        tab_formulario_alumno.getColumna("ide_ystgrd").setCombo(ser_formulario.getGradoDiscapacidad());
        tab_formulario_alumno.getColumna("ide_ysttco").setCombo(ser_formulario.getTipoColegio());
        tab_formulario_alumno.getColumna("ide_ysttba").setCombo(ser_formulario.getTipoBachillerato());
        tab_formulario_alumno.getColumna("ide_ystnie").setCombo(ser_formulario.getNivelEducacion());
        tab_formulario_alumno.getColumna("ide_ystjor").setCombo(ser_formulario.getJornada("true,false"));
        tab_formulario_alumno.getColumna("ide_ytiemp").setCombo(ser_formulario1.getEmpresa());
        tab_formulario_alumno.getColumna("ide_ystvis").setCombo(ser_formulario.getVinculaSociedad());
        tab_formulario_alumno.getColumna("ide_ystmod").setCombo(ser_formulario.getModalidad("true,false"));
        tab_formulario_alumno.getColumna("ide_ypetip").setCombo(ser_formulario2.getTituloProfesional());
        tab_formulario_alumno.getColumna("ide_ystdip").setCombo(ser_formulario.getDistribucionPolitica("1",utilitario.getVariable("p_tipo_pais"))); 
        tab_formulario_alumno.getColumna("ide_ytiace").setCombo(ser_formulario1.getSqlActividadEconomica());
        tab_formulario_alumno.getColumna("ide_ystdoi").setCombo(ser_formulario.getDocumentoIdentidad("true,false"));
        tab_formulario_alumno.getColumna("ide_ystmen").setCombo(ser_formulario.getMension());
        tab_formulario_alumno.getColumna("ide_ystmen").setMetodoChange("nombreTitulo");
        tab_formulario_alumno.getColumna("ide_ytitie").setCombo(ser_formulario1.getTipoEmpresa());
        tab_formulario_alumno.getColumna("ide_ytiace").setCombo(ser_formulario1.getActividadEconomica());
        tab_formulario_alumno.getColumna("ide_ystfoe").setCombo(ser_formulario.getFormacionEducativa());
        tab_formulario_alumno.getColumna("ide_ystcam").setCombo(ser_formulario.getCategoriaMigratoria());
        tab_formulario_alumno.getColumna("ide_ystpea").setCombo(ser_formulario.getPeriodoAcademico("true,false"));
        tab_formulario_alumno.getColumna("ide_ystpea").setAutoCompletar();
        tab_formulario_alumno.getColumna("ide_ystani").setCombo(ser_formulario.getAnioPeriodoCarrera());
        tab_formulario_alumno.getColumna("ide_yhogra").setCombo(ser_horario.getGrupoAcademico());
        tab_formulario_alumno.getColumna("ide_ystani").setAutoCompletar();
        tab_formulario_alumno.getColumna("ide_ymatip").setCombo(ser_matricula.getTipoPeriodoMatricula());
        tab_formulario_alumno.getColumna("yav_ide_ystdip").setCombo(ser_formulario.getDistribucionPolitica("1",utilitario.getVariable("p_tipo_provincia")));
        tab_formulario_alumno.getColumna("yav_ide_ystdip2").setCombo(ser_formulario.getDistribucionPolitica("1",utilitario.getVariable("p_tipo_canton")));
        tab_formulario_alumno.getColumna("yav_ide_ystdip3").setCombo(ser_formulario.getDistribucionPolitica("1",utilitario.getVariable("p_tipo_pais")));
        tab_formulario_alumno.getColumna("yav_ide_ystdip4").setCombo(ser_formulario.getDistribucionPolitica("1",utilitario.getVariable("p_tipo_provincia")));
        tab_formulario_alumno.getColumna("yav_ide_ystdip5").setCombo(ser_formulario.getDistribucionPolitica("1",utilitario.getVariable("p_tipo_canton")));

        tab_formulario_alumno.getColumna("yav_ide_ytiace").setCombo(ser_formulario1.getActividadEconomica());
        tab_formulario_alumno.getColumna("yav_ide_ystfoe").setCombo(ser_formulario.getFormacionEducativa());
        tab_formulario_alumno.getColumna("ide_ystdom").setCombo(ser_formulario.getIdioma());
        List lista = new ArrayList();
	       Object fila1[] = {
	           "1", "Tecnicatura"
	       };
	       Object fila2[] = {
	           "2", "Tecnología"
	       };
	       
	       lista.add(fila1);
	       lista.add(fila2);
        tab_formulario_alumno.getColumna("tipo_carrera_yinfor").setRadio(lista, "2");
        List lista2 = new ArrayList();
	       Object fila3[] = {
	           "1", "Solamente Estudio"
	       };
	       Object fila4[] = {
	           "2", "Alguna Ocupacion para obtener Ingresos"
	       };
	       
	       lista2.add(fila3);
	       lista2.add(fila4);
        tab_formulario_alumno.getColumna("dedicado_a_yinfor").setRadio(lista2, "1"); 
        tab_formulario_alumno.getColumna("dedicado_a_yinfor").setRadioVertical(true);
        List lista3 = new ArrayList();
	       Object fila5[] = {
	           "1", "Sostener sus estudios"
	       };
	       Object fila6[] = {
	           "2", "Para mantener a su familia"
	       };
               Object fila7[] = {
	           "3", "Otros"
	       };
	       
	       lista3.add(fila5);
	       lista3.add(fila6);
               lista3.add(fila7);
        tab_formulario_alumno.getColumna("emplea_ingresos_yinfor").setRadio(lista2, "1"); 
        tab_formulario_alumno.getColumna("emplea_ingresos_yinfor").setRadioVertical(true);
        // ETIQUETAS
        tab_formulario_alumno.getColumna("IDE_YINFOR").setNombreVisual("CODIGO");
        tab_formulario_alumno.getColumna("IDE_YSTSEX").setNombreVisual("3. SEXO");
        tab_formulario_alumno.getColumna("ide_ystdoi").setNombreVisual("1. TIPO DOCUMENTO IDENTIDAD");      
        tab_formulario_alumno.getColumna("IDE_YSTESC").setNombreVisual("ESTADO CIVIL");   
        tab_formulario_alumno.getColumna("IDE_YSTNIE").setNombreVisual("NIVEL EDUCATIVO");
        
        tab_formulario_alumno.getColumna("DOC_IDENTIFICACION_YINFOR").setNombreVisual("2. NRO. DOCUMENTO IDENTIDAD");
        tab_formulario_alumno.getColumna("APELLIDOS_YINFOR").setNombreVisual("5. APELLIDOS");
        tab_formulario_alumno.getColumna("NOMBRES_YINFOR").setNombreVisual("NOMBRES");
        tab_formulario_alumno.getColumna("ide_ystgen").setNombreVisual("4. GENERO");
        tab_formulario_alumno.getColumna("CORREO_ELECTRONICO_YINFOR").setNombreVisual("6. CORREO ELECTRONICO");
        tab_formulario_alumno.getColumna("CELULAR_YINFOR").setNombreVisual("7. NRO. CEULAR");
        tab_formulario_alumno.getColumna("TELEFONO_YINFOR").setNombreVisual("8. NRO. CONVENCIONAL");
        tab_formulario_alumno.getColumna("DIRECCION_YINFOR").setNombreVisual("9. DIRECCION");
        tab_formulario_alumno.getColumna("CODIGO_POSTAL_YINFOR").setNombreVisual("10. CODIGO POSTAL");
        tab_formulario_alumno.getColumna("NOMBRE_EMERGENCIA_YINFOR").setNombreVisual("11. NOMBRE EN CASO EMERGENCIA");
        tab_formulario_alumno.getColumna("IDE_YSTPAF").setNombreVisual("12. PARENTEZCO FAMILIAR");
        tab_formulario_alumno.getColumna("CONTACTO_EMERGENCIA_YINFOR").setNombreVisual("13. NRO CONTACTO EMERGENCIA");
        tab_formulario_alumno.getColumna("IDE_YSTETN").setNombreVisual("14. ETNIA");
        tab_formulario_alumno.getColumna("IDE_YSTNAC").setNombreVisual("15. NACIONALIDAD");        
        tab_formulario_alumno.getColumna("HABLA_OTRO_IDIOMA_YINFOR").setNombreVisual("16. HABLA IDIOMA ANCESTRAL");
        tab_formulario_alumno.getColumna("ide_ystdom").setNombreVisual("SELECCIONE EL IDIOMA");
        tab_formulario_alumno.getColumna("FECHA_NACIMIENTO_YINFOR").setNombreVisual("17. FECHA NACIMIENTO");
        tab_formulario_alumno.getColumna("IDE_YSTTIS").setNombreVisual("18. TIPO SANGRE");        
        tab_formulario_alumno.getColumna("ide_ystdip").setNombreVisual("19. PAIS NACIMIENTO");
        tab_formulario_alumno.getColumna("yav_ide_ystdip").setNombreVisual("20. PROVINCIA NACIMIENTO");
        tab_formulario_alumno.getColumna("yav_ide_ystdip2").setNombreVisual("21. CANTON NACIMIENTO");
        tab_formulario_alumno.getColumna("IDE_YSTCAM").setNombreVisual("22. CATEGORIA MIGRATORIA");
        tab_formulario_alumno.getColumna("yav_ide_ystdip3").setNombreVisual("23. PAIS RESIDENCIA");
        tab_formulario_alumno.getColumna("yav_ide_ystdip4").setNombreVisual("24. PROVINCIA RESIDENCIA");
        tab_formulario_alumno.getColumna("yav_ide_ystdip5").setNombreVisual("25. CANTON RESIDENCIA");
        tab_formulario_alumno.getColumna("IDE_YSTESC").setNombreVisual("26. ESTADO CIVIL");        
        tab_formulario_alumno.getColumna("tiene_discapacidad_yinfor").setNombreVisual("27. TIENE DISCAPACIDAD");
        tab_formulario_alumno.getColumna("numero_conadis_yinfor").setNombreVisual("28. NRO CARNET CONADIS");
        tab_formulario_alumno.getColumna("IDE_YSTGRD").setNombreVisual("29. PORCENTAJE DISCAPACIDAD");
        tab_formulario_alumno.getColumna("ide_ysttid").setNombreVisual("30. TIPO DISCAPACIDAD");
        tab_formulario_alumno.getColumna("IDE_YSTTCO").setNombreVisual("31. TIPO COLEGIO");  
        tab_formulario_alumno.getColumna("IDE_YSTTBA").setNombreVisual("32. TIPO BACHILLERATO");
        tab_formulario_alumno.getColumna("anio_gradua_yinfor").setNombreVisual("33. AÑO GRADUACION");
        tab_formulario_alumno.getColumna("educacion_superior_yinfor").setNombreVisual("34. POSEE TITULO PROFESIONAL");
        tab_formulario_alumno.getColumna("ide_ypetip").setNombreVisual("ESPECIFIQUE TITULO");
        tab_formulario_alumno.getColumna("fecha_ini_estudio_yinfor").setNombreVisual("35. FECHA INICIO ESTUDIOS");
        tab_formulario_alumno.getColumna("fecha_matri_yinfor").setNombreVisual("36. FECHA MATRICULA");
        tab_formulario_alumno.getColumna("ide_ymatip").setNombreVisual("37. TIPO MATRICULA");
        tab_formulario_alumno.getColumna("ide_ystpea").setNombreVisual("38. PERIODO ACADEMICO");
        tab_formulario_alumno.getColumna("ide_ystani").setNombreVisual("39. AÑO PERIODO ACADEMICO");
        tab_formulario_alumno.getColumna("IDE_YSTNIE").setNombreVisual("40. NIVEL ACADEMICO");
        tab_formulario_alumno.getColumna("ide_yhogra").setNombreVisual("41. PARALELO");
        tab_formulario_alumno.getColumna("ide_ystmen").setNombreVisual("42. NOMBRE CARRERA");
        tab_formulario_alumno.getColumna("titulo_otorga_yinfor").setNombreVisual("43. TITULO OTORGA");
        tab_formulario_alumno.getColumna("tipo_carrera_yinfor").setNombreVisual("44. TIPO CARRERA");
        tab_formulario_alumno.getColumna("ide_ystmod").setNombreVisual("45. MODALIDAD CARRERA");
        tab_formulario_alumno.getColumna("IDE_YSTJOR").setNombreVisual("46. JORNADA ESTUDIOS");
        tab_formulario_alumno.getColumna("perdida_materia_yinfor").setNombreVisual("47. PERDIDO AL MENOS UNA MATERIA");
        tab_formulario_alumno.getColumna("perdida_gratuidad_yinfor").setNombreVisual("48. PERDIO GRATUIDAD");
        tab_formulario_alumno.getColumna("practicas_preprof_yinfor").setNombreVisual("49. REALIZO PRACTICAS PREPROFESIONALES");
        tab_formulario_alumno.getColumna("horas_practicas_yinfor").setNombreVisual("50. HORAS PRACTICAS PREPROFESIONALES");
        tab_formulario_alumno.getColumna("ide_ytitie").setNombreVisual("51. TIPO EMPRESA REALIZA PRACTICAS");
        tab_formulario_alumno.getColumna("ide_ytiace").setNombreVisual("52. ACTIVIDAD ECONOMICA");
        tab_formulario_alumno.getColumna("vinculacion_sociedad_yinfor").setNombreVisual("53. PARTICIPADO VINCULACION SOCIEDAD");
        tab_formulario_alumno.getColumna("ide_ystvis").setNombreVisual("54. ALCANCE PROYECTO VINCULACION");
        tab_formulario_alumno.getColumna("dedicado_a_yinfor").setNombreVisual("55. ESTUDIANTE ESTA DEDICADO A");
        tab_formulario_alumno.getColumna("IDE_YTIEMP").setNombreVisual("56. NOMBRE EMPRESA LABORA");
        tab_formulario_alumno.getColumna("yav_ide_ytiace").setNombreVisual("57. AREA TRABAJO EN LA EMPRESA");
        tab_formulario_alumno.getColumna("emplea_ingresos_yinfor").setNombreVisual("58. EN QUE EMPLEA SUS INGRESOS");
        tab_formulario_alumno.getColumna("familiar_bono_yinfor").setNombreVisual("59. USTED O FAMILIAR RECIBE BONO");
        tab_formulario_alumno.getColumna("ide_ystfoe").setNombreVisual("60. NIVEL FORMACION PADRE");
        tab_formulario_alumno.getColumna("yav_ide_ystfoe").setNombreVisual("61. NIVEL FORMACION MADRE");
        tab_formulario_alumno.getColumna("ingresos_hogar_yinfor").setNombreVisual("62. INGRESOS HOGAR");
        tab_formulario_alumno.getColumna("miembros_hogar_yinfor").setNombreVisual("63. MIEMBROS HOGAR");
        
        // ORDEN
        tab_formulario_alumno.getColumna("IDE_YINFOR").setOrden(0);
        tab_formulario_alumno.getColumna("ide_ystdoi").setOrden(1);
        tab_formulario_alumno.getColumna("DOC_IDENTIFICACION_YINFOR").setOrden(2);
        tab_formulario_alumno.getColumna("IDE_YSTSEX").setOrden(3);
        tab_formulario_alumno.getColumna("ide_ystgen").setOrden(4);
        tab_formulario_alumno.getColumna("APELLIDOS_YINFOR").setOrden(5);
        tab_formulario_alumno.getColumna("NOMBRES_YINFOR").setOrden(6);
        tab_formulario_alumno.getColumna("CORREO_ELECTRONICO_YINFOR").setOrden(7);
        tab_formulario_alumno.getColumna("CELULAR_YINFOR").setOrden(8);
        tab_formulario_alumno.getColumna("TELEFONO_YINFOR").setOrden(9);
        tab_formulario_alumno.getColumna("DIRECCION_YINFOR").setOrden(10);
        tab_formulario_alumno.getColumna("CODIGO_POSTAL_YINFOR").setOrden(11);
        tab_formulario_alumno.getColumna("NOMBRE_EMERGENCIA_YINFOR").setOrden(12);
        tab_formulario_alumno.getColumna("IDE_YSTPAF").setOrden(13);
        tab_formulario_alumno.getColumna("CONTACTO_EMERGENCIA_YINFOR").setOrden(14);
        tab_formulario_alumno.getColumna("IDE_YSTETN").setOrden(15);
        tab_formulario_alumno.getColumna("IDE_YSTNAC").setOrden(16);
        tab_formulario_alumno.getColumna("HABLA_OTRO_IDIOMA_YINFOR").setOrden(17);
        tab_formulario_alumno.getColumna("ide_ystdom").setOrden(18);
        tab_formulario_alumno.getColumna("FECHA_NACIMIENTO_YINFOR").setOrden(19);
        tab_formulario_alumno.getColumna("IDE_YSTTIS").setOrden(20);
        tab_formulario_alumno.getColumna("ide_ystdip").setOrden(21);
        tab_formulario_alumno.getColumna("yav_ide_ystdip").setOrden(22);
        tab_formulario_alumno.getColumna("yav_ide_ystdip2").setOrden(23);
        tab_formulario_alumno.getColumna("IDE_YSTCAM").setOrden(24);
        tab_formulario_alumno.getColumna("yav_ide_ystdip3").setOrden(25);
        tab_formulario_alumno.getColumna("yav_ide_ystdip4").setOrden(26);
        tab_formulario_alumno.getColumna("yav_ide_ystdip5").setOrden(27);
        tab_formulario_alumno.getColumna("IDE_YSTESC").setOrden(28);
        tab_formulario_alumno.getColumna("tiene_discapacidad_yinfor").setOrden(29);
        tab_formulario_alumno.getColumna("numero_conadis_yinfor").setOrden(30);
        tab_formulario_alumno.getColumna("IDE_YSTGRD").setOrden(31);
        tab_formulario_alumno.getColumna("ide_ysttid").setOrden(32);
        tab_formulario_alumno.getColumna("IDE_YSTTCO").setOrden(33);
        tab_formulario_alumno.getColumna("IDE_YSTTBA").setOrden(34);
        tab_formulario_alumno.getColumna("anio_gradua_yinfor").setOrden(35);
        tab_formulario_alumno.getColumna("educacion_superior_yinfor").setOrden(36);
        tab_formulario_alumno.getColumna("ide_ypetip").setOrden(37);
        tab_formulario_alumno.getColumna("fecha_ini_estudio_yinfor").setOrden(38);
        tab_formulario_alumno.getColumna("fecha_matri_yinfor").setOrden(39);
        tab_formulario_alumno.getColumna("ide_ymatip").setOrden(40);
        tab_formulario_alumno.getColumna("ide_ystpea").setOrden(41);
        tab_formulario_alumno.getColumna("ide_ystani").setOrden(42);
        tab_formulario_alumno.getColumna("IDE_YSTNIE").setOrden(43);
        tab_formulario_alumno.getColumna("ide_yhogra").setOrden(44);
        tab_formulario_alumno.getColumna("ide_ystmen").setOrden(45);
        tab_formulario_alumno.getColumna("titulo_otorga_yinfor").setOrden(46);
        tab_formulario_alumno.getColumna("tipo_carrera_yinfor").setOrden(47);
        tab_formulario_alumno.getColumna("ide_ystmod").setOrden(48);
        tab_formulario_alumno.getColumna("IDE_YSTJOR").setOrden(49);
        tab_formulario_alumno.getColumna("perdida_materia_yinfor").setOrden(50);
        tab_formulario_alumno.getColumna("perdida_gratuidad_yinfor").setOrden(51);
        tab_formulario_alumno.getColumna("practicas_preprof_yinfor").setOrden(52);
        tab_formulario_alumno.getColumna("horas_practicas_yinfor").setOrden(53);
        tab_formulario_alumno.getColumna("ide_ytitie").setOrden(54);
        tab_formulario_alumno.getColumna("ide_ytiace").setOrden(55);
        tab_formulario_alumno.getColumna("vinculacion_sociedad_yinfor").setOrden(56);
        tab_formulario_alumno.getColumna("ide_ystvis").setOrden(57);
        tab_formulario_alumno.getColumna("dedicado_a_yinfor").setOrden(58);
        tab_formulario_alumno.getColumna("IDE_YTIEMP").setOrden(59);
        tab_formulario_alumno.getColumna("yav_ide_ytiace").setOrden(60);
        tab_formulario_alumno.getColumna("emplea_ingresos_yinfor").setOrden(61);
        tab_formulario_alumno.getColumna("familiar_bono_yinfor").setOrden(62);
        tab_formulario_alumno.getColumna("ide_ystfoe").setOrden(63);
        tab_formulario_alumno.getColumna("yav_ide_ystfoe").setOrden(64);
        tab_formulario_alumno.getColumna("ingresos_hogar_yinfor").setOrden(65);
        tab_formulario_alumno.getColumna("miembros_hogar_yinfor").setOrden(66);
        
        // campos bloqueados
        tab_formulario_alumno.getColumna("ide_ystpea").setLectura(true);
        tab_formulario_alumno.getColumna("ide_ystani").setLectura(true);
        tab_formulario_alumno.getColumna("titulo_otorga_yinfor").setLectura(true);
        
        // ANCHO DE LOS COMBOS DE SELCCIONS
        tab_formulario_alumno.getColumna("ide_ytitie").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTSEX").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTTID").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTPAF").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTETN").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTNAC").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTTIS").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTCAM").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTESC").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTGRD").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTTCO").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTTBA").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTPEA").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTNIE").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTJOR").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YTIEMP").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTVIS").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTMOD").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YPETIP").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTDIP").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("YAV_IDE_YSTDIP").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YTIACE").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("YAV_IDE_YTIACE").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTMEN").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTDOI").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTGEN").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTFOE").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("YAV_IDE_YSTFOE").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTANI").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YSTDOM").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("YAV_IDE_YSTDIP2").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("YAV_IDE_YSTDIP3").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("YAV_IDE_YSTDIP4").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("YAV_IDE_YSTDIP5").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YMATIP").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("IDE_YHOGRA").setEstilo("width: 200px;");
        tab_formulario_alumno.getColumna("DOC_IDENTIFICACION_YINFOR").setLongitud(50);
        tab_formulario_alumno.getColumna("NOMBRES_YINFOR").setLongitud(50);
        tab_formulario_alumno.getColumna("APELLIDOS_YINFOR").setLongitud(50);
        tab_formulario_alumno.getColumna("CORREO_ELECTRONICO_YINFOR").setLongitud(50);
        tab_formulario_alumno.getColumna("DIRECCION_YINFOR").setLongitud(50);
        tab_formulario_alumno.getColumna("CELULAR_YINFOR").setLongitud(50);
        tab_formulario_alumno.getColumna("TELEFONO_YINFOR").setLongitud(50);
        tab_formulario_alumno.getColumna("CODIGO_POSTAL_YINFOR").setLongitud(50);
        tab_formulario_alumno.getColumna("CONTACTO_EMERGENCIA_YINFOR").setLongitud(50);
        tab_formulario_alumno.getColumna("TITULO_OTORGA_YINFOR").setLongitud(50);
        
        tab_formulario_alumno.setTipoFormulario(true);
        tab_formulario_alumno.getGrid().setColumns(6);
        tab_formulario_alumno.dibujar();
        PanelTabla pat_formulario_alumno = new PanelTabla();
        pat_formulario_alumno.setId("pat_formulario_alumno");
        pat_formulario_alumno.setPanelTabla(tab_formulario_alumno);
        
        Division div_formulario_alumno = new Division();
        div_formulario_alumno.setId("div_formulario_alumno");
        div_formulario_alumno.dividir1(pat_formulario_alumno);
        
        agregarComponente(div_formulario_alumno);
        
         vipdf_comprobante.setId("vipdf_comprobante");
            vipdf_comprobante.setTitle("FORMULARIO DE MATRICULAS");
            agregarComponente(vipdf_comprobante);     
    }
public void generarPDF() {
        if (tab_formulario_alumno.getValorSeleccionado() != null) {
            ///////////AQUI ABRE EL REPORTE
            Map map_parametros = new HashMap();
            map_parametros.put("pide_ins", Integer.parseInt(tab_formulario_alumno.getValor("ide_yinfor")));
            map_parametros.put("nombre", utilitario.getVariable("NICK"));
            
            //System.out.println(" " + str_titulos);
            vipdf_comprobante.setVisualizarPDF("rep_inscripcion/rep_formulario.jasper", map_parametros);
            vipdf_comprobante.dibujar();
            utilitario.addUpdate("vipdf_comprobante");
        } else {
            utilitario.agregarMensajeInfo("Seleccione una Inscripcion", "");
        }
    }        
    public void nombreTitulo(SelectEvent evt){
        tab_formulario_alumno.modificar(evt);
        TablaGenerica tab_titulo= utilitario.consultar("select * from yavirac_stror_mension where ide_ystmen="+tab_formulario_alumno.getValor("ide_ystmen"));
        tab_formulario_alumno.setValor("TITULO_OTORGA_YINFOR", tab_titulo.getValor("nom_titulo_ystem"));
        utilitario.addUpdate("TITULO_OTORGA_YINFOR");
    }
     public void filtroComboPeriodoAcademnico(){
        if(com_periodo_academico.getValue() != null){
        tab_formulario_alumno.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_formulario_alumno.ejecutarSql();
        utilitario.addUpdate("tab_formulario_alumno");
        }
        else{
            utilitario.agregarMensajeInfo("Periodo Academico","Seleccione el Periodo Academico");
        }
        
    }
    @Override
    public void insertar() {
         if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Académico");
            return;
        }
         else {
        tab_formulario_alumno.insertar();
             TablaGenerica tab_anio= utilitario.consultar("select * from yavirac_stror_periodo_academic where ide_ystpea="+com_periodo_academico.getValue());
        tab_formulario_alumno.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
        tab_formulario_alumno.setValor("ide_ystani", tab_anio.getValor("ide_ystani"));
        }
    }

    @Override
    public void guardar() {
        tab_formulario_alumno.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_formulario_alumno.eliminar();

    }

    public Tabla getTab_formulario_alumno() {
        return tab_formulario_alumno;
    }

    public void setTab_formulario_alumno(Tabla tab_formulario_alumno) {
        this.tab_formulario_alumno = tab_formulario_alumno;
    }

    public VisualizarPDF getVipdf_comprobante() {
        return vipdf_comprobante;
    }

    public void setVipdf_comprobante(VisualizarPDF vipdf_comprobante) {
        this.vipdf_comprobante = vipdf_comprobante;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia.ejb;

import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
@Stateless
public class ServicioAsistencia {

     /**
     * Insertar en la tabla Biometrico 
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String insertBiometrico(String ide,String indice,String hora,String fecha,String puerta,String num,String nombre,String departamento,String fecha_registro,String codigo_reloj,String usuario_ingre,String fecha_ingre,String hora_ingre) {
        String sql="";
        sql="insert into yavirac_asis_biometrico (ide_yasbio,indice_yasbio,hora_yasbio,fecha_yasbio,puerta_yasbio,num_yasbio,nombre_yasbio,departamento_yasbio,fecha_registro_yasbio,codigo_reloj_yasbio,usuario_ingre,fecha_ingre,hora_ingre)"+
                " values ("+ide+",'"+indice+"','"+hora+"','"+fecha+"','"+puerta+"','"+num+"','"+nombre+"','"+departamento+"','"+fecha_registro+"','"+codigo_reloj+"','"+usuario_ingre+"','"+fecha_ingre+"','"+hora_ingre+"')";
        return sql;
    }
       /**
     * Insertar en la tabla Biometrico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSemana() {
        String sql="";
        sql=" SELECT ide_yassem, detalle_yassem FROM yavirac_asis_semana order by detalle_yassem;";
              
        return sql;
    }   
       
    /**
     * SQL tipo motivo para asistencia
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getTipoMotivo() {
        String sql="";
        sql=" SELECT ide_yastmo, descripcion_yastmo FROM yavirac_asis_tipo_motivo order by descripcion_yastmo;";
              
        return sql;
    }     
  /**
     * SQL motivo para motiva de ausencia
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getMotivoAusencia() {
        String sql="";
        sql=" SELECT ide_yasmpe, descripcion_yasmpe FROM yavirac_asis_motivo_permiso order by descripcion_yasmpe;";
              
        return sql;
    } 
  /**
     * SQL motivo para cursos con materias para toma de  por periodo academico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getMateriaNivelDocente(String periodo,String docente) {
        String sql="";
        sql="select a.ide_ypemad,detalle_ystmat,descripcion_ystnie,detalle_yhogra,d.descripcion_ystjor,detalle_ysttfe\n" +
            "from yavirac_perso_malla_docente a,(select ide_ystmal, detalle_ystmat, descripcion_ystnie,detalle_ysttfe from yavirac_stror_malla a,yavirac_stror_nivel_educacion b,yavirac_stror_materia c,\n" +
            "yavirac_stror_mension d,yavirac_stror_tipo_for_educaci e where a.ide_ystnie = b.ide_ystnie and a.ide_ystmat = c.ide_ystmat and a.ide_ystmen=d.ide_ystmen and d.ide_ysttfe=e.ide_ysttfe) b, \n" +
            "yavirac_hora_grupo_academic c,yavirac_stror_jornada  d\n" +
            "where a.ide_ystmal = b.ide_ystmal and a.ide_yhogra= c.ide_yhogra and ide_ystpea ="+periodo+" and ide_ypedpe ="+docente+"\n" +
            "and a.ide_ystjor= d.ide_ystjor \n" +
            "order by descripcion_ystnie,detalle_ystmat,descripcion_ystjor";
              
        return sql;
    }   
/**
     * SQL fechas para el registro de asistencia de los estudiante spor periodo academico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getFechaAsistencia(String periodo,String activo,String bloqueado,String festivo) {
        String sql="";
        sql="select ide_yasfec,fecha_yasfec,bloqueado_yasfec,activo_yasfec,festivo_yasfec" +
        " from yavirac_asis_fecha_control where ide_ystpea  ="+periodo+" and activo_yasfec in ("+activo+") and festivo_yasfec in (" +festivo+") and bloqueado_yasfec in ("+bloqueado+")"+
        " order by fecha_yasfec desc";
              //System.out.println("sqldd "+sql);
        return sql;
    }     
    /**
     * SQL alumnos con sus ausencias
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getAusenciaAlumno(String fecha_inicial,String fecha_final,String justificado,String asistencia) {
        String sql="";
        sql="select ide_yasasi,fecha_yasfec,justificado_yasasi,detalle_ystmat,apellido_ypedpe,nombre_ypedpe" +
        " from yavirac_asis_asistencia a, yavirac_asis_fecha_control b, (  select a.ide_ypemad,detalle_ystmat,apellido_ypedpe,nombre_ypedpe" +
        " from yavirac_perso_malla_docente a, (" +
        " select  ide_ystmal,detalle_ystmat" +
        " from yavirac_stror_malla a, yavirac_stror_materia b" +
        " where a.ide_ystmat = b.ide_ystmat ) b,yavirac_perso_dato_personal c" +
        " where a.ide_ystmal = b.ide_ystmal" +
        " and a.ide_ypedpe = c.ide_ypedpe ) c" +
        " where a.ide_yasfec =b.ide_yasfec" +
        " and a.ide_ypemad = c.ide_ypemad" +
        " and fecha_yasfec between '"+fecha_inicial+"' and '"+fecha_final+"'" +
        " and justificado_yasasi in ("+justificado+")" +
        " and asistencia_yasasi in ("+asistencia+")" +
        " order by apellido_ypedpe";
              
        return sql;
    }   
    public String getControlAsistencia(String docente_malla,String fecha) {
          
        String sql="";
        sql="select ide_yascas,ide_ypemad from yavirac_asis_control_asistencia   where ide_ypemad ="+docente_malla+"  and ide_yasfec="+fecha;
        return sql;
 } 
    public String getDocenteMensionParalelo(String tipo,String codigo) {
          
        String sql="";
        sql="select a.ide_ypemad,ide_ystmen,ide_ystmat,apellido_ypedpe,nombre_ypedpe,doc_identidad_ypedpe,descripcion_ystpea,descripcion_ystnie,orden_ystnie,detalle_ystmat,descripcion_ystmen,a.ide_yhogra,detalle_yhogra,descripcion_ystani " +
            " from (" +
            " select a.ide_ystmal,a.ide_ystpea,a.ide_ypedpe,a.ide_ypemad,a.ide_yhogra,apellido_ypedpe,nombre_ypedpe,doc_identidad_ypedpe,descripcion_ystpea,detalle_yhogra,abreviatura_yhogra,descripcion_ystani" +
            " from  yavirac_perso_malla_docente a,yavirac_perso_dato_personal b,yavirac_stror_periodo_academic c,yavirac_hora_grupo_academic d,yavirac_stror_anio e" +
            " where a.ide_ypedpe=b.ide_ypedpe and a.ide_ystpea=c.ide_ystpea and a.ide_yhogra=d.ide_yhogra" +
            " and c.ide_ystani = e.ide_ystani" +
            " ) a,(" +
            " select a.ide_ystnie,a.ide_ystmat,a.ide_ystmen,a.ide_ystmal,descripcion_ystnie,abreviatura_ystnie,orden_ystnie,detalle_ystmat,abreviatura_ystmat,descripcion_ystmen" +
            " from yavirac_stror_malla a,yavirac_stror_nivel_educacion b,yavirac_stror_materia c,yavirac_stror_mension d where a.ide_ystnie=b.ide_ystnie and a.ide_ystmat=c.ide_ystmat and a.ide_ystmen= d.ide_ystmen " +
            " ) b " +
            " where a.ide_ystmal =b.ide_ystmal ";
        if(tipo.equals("1")){
            sql +=" and ide_ypemad in ("+codigo+") ";
        }
        return sql;
 }        
}
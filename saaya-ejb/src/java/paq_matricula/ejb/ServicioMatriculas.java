/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_matricula.ejb;

import paq_personal.ejb.*;
import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class ServicioMatriculas {

     /**
     * Insertar en la tabla Biometrico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getAlumnosMallaPeriodo (String malla,String periodo,String fecha_control,String docente) {
        String sql="";
        sql=" insert into yavirac_asis_asistencia (ide_yasasi,ide_yasfec,ide_ypedpe,ide_yaldap,asistencia_yasasi,ide_ystmal)\n" +
            "select row_number()over( order by ide_yaldap ) + (select (case when max(ide_yasasi) is null then 0 else max(ide_yasasi) end) as codigo from yavirac_asis_asistencia ) as codigo ,\n" +
            fecha_control+","+docente+", a.ide_yaldap,true," +malla+
            " from yavirac_matri_matricula a,yavirac_matri_periodo_matric b,yavirac_matri_registro_credito c\n" +
            " where a.ide_ymaper = b.ide_ymaper\n" +
            " and a.ide_ymamat = c.ide_ymamat\n" +
            " and ide_ystpea =" +periodo+
            " and ide_ystmal ="+malla;
              System.out.println("cccccc "+sql);
        return sql;
    }

         public String getTipoPeriodoMatricula() {
        String sql="";
        sql="SELECT ide_ymatip, descripcion_ymatip   FROM yavirac_matri_tipo_periodo order by descripcion_ymatip";
        return sql;
    } 
         public String getTipoMatricula() {
        String sql="";
        sql="SELECT ide_ymatma, detalle_ymatma  FROM yavirac_matri_tipo_matricula order by detalle_ymatma";
        return sql;
    } 
         public String getPeriodoMatricula(String activo) {
        String sql="";
        sql="select ide_ymaper,descripcion_ystpea,descripcion_ymatip,fecha_inicio_ymaper,fecha_final_ymaper " +
            "from yavirac_matri_periodo_matric a,yavirac_matri_tipo_periodo b,yavirac_stror_periodo_academic c " +
            "where a.ide_ymatip = b.ide_ymatip " +
            "and a.ide_ystpea=c.ide_ystpea " +
            "and activo_ymaper in ("+activo+")";
        return sql;
    }     
         public String getTipoRegitroCredito() {
        String sql="";
        sql="SELECT ide_ymatrc, detalle_ymatrc FROM yavirac_matri_tipo_reg_credito order by detalle_ymatrc;";
        return sql;
    }
         public String getNumeroMatricula() {
        String sql="";
        sql="SELECT ide_ymanum, descripcion_ymanum, abreviatura_ymanum FROM yavirac_matri_numero_matricula order by descripcion_ymanum;";
        return sql;
    }       
/**
     * Sql, de alumnos matriculados cons us distintas materias y paralelos
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getAlumnosMallaGrupo (String malla,String grupo,String periodo,String jornada) {
        String sql="";
        sql="select a.ide_ymamat,ide_ystmal,ide_yhogra,a.ide_yaldap,apellido_yaldap,nombre_yaldap" +
            " from yavirac_matri_matricula a, yavirac_matri_registro_credito b,yavirac_alum_dato_personal c,yavirac_matri_periodo_matric d" +
            " where a.ide_ymamat = b.ide_ymamat" +
            " and a.ide_yaldap = c.ide_yaldap" +
            " and a.ide_ymaper = d.ide_ymaper" +
            " and ide_ystmal ="+malla +
            " and ide_yhogra ="+grupo +
            " and d.ide_ystpea="+periodo +
            " and b.ide_ystjor="+jornada +
            " order by apellido_yaldap";
        return sql;
    }         
}

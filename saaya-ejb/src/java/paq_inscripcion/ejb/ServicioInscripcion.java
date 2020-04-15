/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion.ejb;

import paq_titulacion.ejb.*;
import paq_asistencia.ejb.*;
import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Janeth Pullotasig and Nicolas Cajilema
 */
@Stateless
public class ServicioInscripcion {

    /**
     * Dervuelve el sql de tipo emprea
     *
     * @param todos.- Ingresar todos los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlInstituos() {
        String sql = "";
        sql = "select ide_yinsin,nombre_yinsin,codigo_instit_yinsin,abreviatura_yinsin from yavirac_ins_instituto";
        return sql;
    }

    public String getSqlDocenteMension(String ide_ystpea, String ide_ypedpe) {
        String sql = "";
        sql = "select ide_yindom, descripcion_ystmen \n"
                + "from yavirac_ins_docente_mension a \n"
                + "left join yavirac_stror_mension b on a.ide_ystmen = b.ide_ystmen \n"
                + "where ide_ystpea=" + ide_ystpea + " and ide_ypedpe= " + ide_ypedpe;
        System.out.println("SQL===>"+sql);       
        return sql;

    }

     public String getSqlDocenteAsignado(String ide_ystpea, String ide_ypedpe) {
        String sql = "";
        sql = "select ide_yincda,a.ide_yindom, descripcion_ystmen \n"
                + "from yavirac_ins_docente_mension a \n"
                + "left join yavirac_stror_mension b on a.ide_ystmen = b.ide_ystmen\n"
                + "left join yavirac_ins_coordin_docent_as c on a.ide_yindom=c.ide_yindom\n"
                + "where ide_ystpea=" + ide_ystpea + " and c.ide_ypedpe=" + ide_ypedpe;
        //System.out.println("SQL===>"+sql);
        return sql;

    }        
    public String getSqlAlumnosInscritos(String ide_ystpea, String ide_ystmen) {
        String sql = "";
        sql = "select a.ide_yinpin, a.ide_yaldap, ide_ystpea, doc_identidad_yaldap, nombre_yaldap, apellido_yaldap, "
                + "                a.ide_ystmen, descripcion_ystmen "
                + "from (                "
                + "select ide_yinpin, a.ide_yaldap, ide_ystpea, doc_identidad_yaldap, nombre_yaldap, apellido_yaldap, "
                + "                a.ide_ystmen, descripcion_ystmen "
                + "                from yavirac_ins_pre_inscripcion a"
                + "                left join yavirac_alum_dato_personal b on a.ide_yaldap=b.ide_yaldap"
                + "                left join yavirac_stror_mension c on a.ide_ystmen=c.ide_ystmen"
                + "                where ide_ystpea=" + ide_ystpea + " and a.ide_ystmen=" + ide_ystmen
                + " ) a "
                + " left join yavirac_ins_docente_alumno b on a.ide_yinpin=b.ide_yinpin"
                + " where b.ide_yinpin is null";
        //System.out.println("SQL ----> "+sql);
        return sql;
    }
}

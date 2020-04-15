/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_alumno.ejb;

import paq_asistencia.ejb.*;
import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kelly collahuazo
 */
@Stateless
public class ServicioAlumno {

    /**
     * Consultamos los datos del alumno
     *
     * @return los datos del alumno
     */
    public String getDatosAlumnos(String estado) {
        String sql = "";
        sql = "Select ide_yaldap,apellido_yaldap,nombre_yaldap,doc_identidad_yaldap FROM yavirac_alum_dato_personal where activo_yaldap in (" + estado + ") order by nombre_yaldap";
        return sql;
    }

    /**
     * Consultamos los datos del alumno
     *
     * @return los datos del alumno
     */
    public String getDatosAlumnosCodigo(String ide) {
        String sql = "";
        sql = "Select ide_yaldap,apellido_yaldap,nombre_yaldap,doc_identidad_yaldap FROM yavirac_alum_dato_personal where ide_yaldap in (" + ide + ") order by nombre_yaldap";
        return sql;
    }

    /**
     * Consultamos los datos de la vivienda
     *
     * @return los datos de la vivienda
     */
    public String getDatosVivienda(String estado) {
        String sql = "";
        sql = "SELECT ide_yaltiv, descripcion_yaltip FROM yavirac_alum_tipo_vivienda";
        return sql;
    }

    /**
     * Consultamos los datos de quien cubre gastos
     *
     * @return los datos de quien cubre los gastos
     */
    public String getGasto(String estado) {
        String sql = "";
        sql = "SELECT ide_yalgas, descripcion_yalgas FROM yavirac_alum_gasto";
        return sql;
    }

    public String getOcupacion(String activo) {
        String sql = "";
        sql = "SELECT ide_yalocu, descripcion_yalocu FROM yavirac_alum_ocupacion where activo_yalocu in (" + activo + ")";
        return sql;
    }

    public String getDatosFamiliaremergencia() {
        String sql = "";
        sql = "select ide_yaldfe,nombre_yaldfe from yavirac_alum_dato_fami_emerg";
        return sql;
    }

    public String getTipoAficion() {
        String sql = "";
        sql = "select ide_yaltia,descripcion_yaltia from yavirac_alum_tipo_aficion";
        return sql;
    }

    public String getEnfermedad() {
        String sql = "";
        sql = "select ide_yalenf,descripcion_yalenf from yavirac_alum_enfermedad";
        return sql;
    }

    public String getTipoTratamiento() {
        String sql = "";
        sql = "select ide_yaltit,descripcion_yaltit from yavirac_alum_tipo_tratamiento";
        return sql;
    }

    public String getCarrera() {
        String sql = "";
        sql = "select ide_yalcar,descripcion_yalcar FROM yavirac_alum_carrera";
        return sql;
    }

    /**
     * Consultamos los datos del alumno por cedula
     *
     * @return los datos del alumno
     */
    public String getDatosAlumnosCedula(String cedula) {
        String sql = "";
        sql = "Select ide_yaldap,apellido_yaldap,nombre_yaldap,doc_identidad_yaldap FROM yavirac_alum_dato_personal where doc_identidad_yaldap in ('" + cedula + "') order by nombre_yaldap";
        return sql;
    }
}

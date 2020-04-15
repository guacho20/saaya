/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_personal.ejb;

import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
/**
 *
 * @author Nicolas Cajilema
 */
@Stateless
@LocalBean
public class ServicioPersonal {
 
     /**
     * Insertar en la tabla Biometrico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getDatopersonal (String activo) {
        String sql="";
        sql=" select ide_ypedpe,apellido_ypedpe,nombre_ypedpe,doc_identidad_ypedpe from yavirac_perso_dato_personal where activo_ypedpe in ("+activo+") order by apellido_ypedpe";
          
        return sql;
    }
      /**
     * Insertar en la tabla Biometrico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getDatoPersonalCodigo (String ide_personal) {
        String sql="";
        sql=" select ide_ypedpe,apellido_ypedpe,nombre_ypedpe,doc_identidad_ypedpe,codigo_reloj_ypedpe from yavirac_perso_dato_personal where ide_ypedpe in ("+ide_personal+")";
        return sql;
    } 
      /**
     * Tabla de datos del personal con su area
     *
     * @param estado.- estado true, false
     * @return tabla de datos
     */
    public String getDatoPersonalDepartamento (String estado) {
        String sql="";
        sql=" select ide_ypedpe,apellido_ypedpe as apellidos,nombre_ypedpe as nombres,doc_identidad_ypedpe as nro_documento ,descripcion_ystard as area,a.ide_ystard\n" +
"from yavirac_perso_dato_personal a, yavirac_stror_area_departament b where a.ide_ystard = b.ide_ystard and activo_ystard in ("+estado+")";
        return sql;
    }       
    public String getTituloProfesional() {
          
        String sql="";
        sql="select ide_ypetip,descripcion_ypetip from yavirac_perso_titu_profesional";
        return sql;
 }
    public String getPersonalMalla(String codigo) {
          
        String sql="";
        sql="select ide_ypemad,ide_ystmal,ide_ystpea,ide_ypedpe,ide_yhogra,ide_ystjor from yavirac_perso_malla_docente   where ide_ypemad ="+codigo;
        return sql;
 }
    
}

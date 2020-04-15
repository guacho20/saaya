/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_tramites.ejb;

import paq_titulacion.ejb.*;
import paq_asistencia.ejb.*;
import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
@Stateless
public class ServicioTramite {

  /**
     * Dervuelve el sql de tipo documento
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlDocumento() {
        String sql="";
        sql="select ide_ytrdoc,nombre_doc_ytrdoc from yavirac_tra_documento order by nombre_doc_ytrdoc";
        return sql;
    }  
/**
     * Dervuelve el sql de tipo entidad
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlTipoEntidad() {
        String sql="";
        sql="select ide_ytrtie,nombre_ytrtie,codigo_abreviatura_ytrtie from yavirac_tra_tipo_entidad order by nombre_ytrtie";
        return sql;
    }
/**
     * Dervuelve el sql de tipo entidad
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlTipoDocumento() {
        String sql="";
        sql="select ide_ytrtid,nombre_ytrtid from yavirac_tra_tipo_documento order by nombre_ytrtid";
        return sql;
    }  
    /**
     * Dervuelve el sql del scuencial del tramite
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlSecuencial(String codigo) {
        String sql="";
        sql="select ide_ytrsec,detalle_ytrsec,secuencial_ytrsec +1 as secuencial from yavirac_tra_secuencial where ide_ytrsec="+codigo;
        return sql;
    } 
/**
     * Actaulizar el sql del scuencial del tramite
     *
     */
    public String getUpdateSecuencial(String codigo,String valor) {
        String sql="";
        sql="update yavirac_tra_secuencial set secuencial_ytrsec="+valor+" where ide_ytrsec="+codigo;
        return sql;
    }     
/**
     * Dervuelve el sql de tipo entidad
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlTipoDocumentoPara(String ide) {
        String sql="";
        sql="select ide_ytrtid,nombre_ytrtid,texto_base_ytrtid,dias_tramite_ytrtid from yavirac_tra_tipo_documento where ide_ytrtid="+ide;
        return sql;
    }   
     public List tipoTramite(){
          List lista=new ArrayList();
		Object fila1[] = {
				"1","Tramite Interno"
		};
		
		Object fila2[] = {
				"2","Tramite Externo"
					
		};
                lista.add(fila1);
		lista.add(fila2);
		return lista;
          
      }  
}

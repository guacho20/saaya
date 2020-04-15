/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion.ejb;
import paq_asistencia.ejb.*;
import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
@Stateless
public class ServicioTitulacion {

     /**
     * Dervuelve el sql de tipo emprea
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlTipoEmpresa() {
        String sql="";
        sql="select ide_ytitie,descripcion_ytitie from yavirac_titu_tipo_empresa order by descripcion_ytitie";
        return sql;
    }
    public String getSqlActividadEconomica() {
        String sql="";
        sql="select ide_ytiace, descripcion_ytiace from yavirac_titu_actividad_economic order by descripcion_ytiace";
        return sql;
    }
    public String getSqlActividadVinculacion() {
        String sql="";
        sql="select ide_ytiacv, detalle_ytiacv from yavirac_titu_actividad_vincula order by detalle_ytiacv";
        return sql;
    }
    public String getSqlEjeVinculacion() {
        String sql="";
        sql="select ide_ytiejv, detalle_ytiejv from yavirac_titu_eje_vinculacion order by detalle_ytiejv";
        return sql;
    }
    public String getSqlAreaAplicacion() {
        String sql="";
        sql="select ide_ytiara, detalle_ytiara from yavirac_titu_area_aplica_vincula order by detalle_ytiara";
        return sql;
    }   
    public String getSqlFuncionAsignada() {
        String sql="";
        sql="select ide_ytifua, detalle_ytifua from yavirac_titu_funcion_asignada order by detalle_ytifua";
        return sql;
    } 
    public String getSqlTipoParticipante() {
        String sql="";
        sql="select ide_ytitpa, detalle_ytitpa from yavirac_titu_tipo_participante order by detalle_ytitpa";
        return sql;
    } 
   public String getSqlTipoProducto() {
        String sql="";
        sql="select ide_ytitip, descripcion_ytitip from yavirac_titu_tipo_producto order by descripcion_ytitip";
        return sql;
    } 
       public String getSqlTipoEntidad() {
        String sql="";
        sql="select ide_ytiten, descripcion_ytiten from yavirac_titu_tipo_entidad order by descripcion_ytiten";
        return sql;
}     /**
     * Dervuelve el sql de tipo persona vinculacion
     *
     * 
     * @return la Tabla tip_per
     */
    public String getSqlTipoPersonaVincula() {
        String sql="";
        sql="select ide_ytitpv, descripcion_ytitpv from yavirac_titu_tipo_per_vinc order by descripcion_ytitpv";
        return sql;
    }
        public String getSqlPersonaEmpresa() {
        String sql="";
        sql="select ide_ytipee, nombre_ytipee, docu_identida_ytipee from yavirac_titu_persona_empresa order by nombre_ytipee";
        return sql;
    }
   public String getEmpresa() {
          
        String sql="";
        sql="select ide_ytiemp,nombre_comercial_ytiemp from yavirac_titu_empresa";
        return sql;
 }
   public String getTipoEmpresa() {
          
        String sql="";
        sql="select ide_ytitie,descripcion_ytitie from yavirac_titu_tipo_empresa";
        return sql;
 }

      public String getActividadEconomica() {
          
        String sql="";
        sql="select ide_ytiace,descripcion_ytiace from yavirac_titu_actividad_economic";
        return sql;
 }
public String getDatoEmpresa() {
          
        String sql="";
        sql="select ide_ytiemp, nombre_comercial_ytiemp,ruc_ytiemp from yavirac_titu_empresa";
        return sql;
}

public String getViabilidad () {
String sql="";
sql="select ide_ytiviv,no_informe_ytiviv,fecha_ytiviv from yavirac_titu_viabilidad";
return sql;
    
    }
public String getLineaSupervision() {
String sql="";
sql="select ide_ytilii,detalle_ytilii from yavirac_titu_linea_investigacio";
return sql;
    
    }
public String getIntervaloTiempo() {
String sql="";
sql="select ide_ytiint,descripcion_ytiint from yavirac_titu_intervalo_tiempo order by descripcion_ytiint";
return sql;
    
    }
public String getIndicador() {
String sql="";
sql="select ide_ytiind,detalle_ytiind from yavirac_titu_indicador";
return sql;
    
    }
public String getMediosVerifica() {
String sql="";
sql="select ide_ytimev,detalle_ytimev from yavirac_titu_medios_verifica";
return sql;
}

public String getTipoObjetivo() {
String sql="";
sql="select ide_ytitio,detalle_ytitio from yavirac_titu_tipo_objetivo";
return sql;

}


public String getActividadResulta() {
String sql="";
sql="select ide_ytiacr,detalle_ytiacr from yavirac_titu_actividad_resulta";
return sql;

}
public String getProyectoVinculacion() {
String sql="";
sql="select ide_ytiviv from yavirac_titu_proyecto";
return sql;
}
public String getIngreso() {
String sql="";
sql="select ide_ytring from yavirac_tra_ingreso";
return sql;
}
public String getCampo() {
String sql="";
sql="select ide_yticam,detalle_yticam from yavirac_titu_campo ";
return sql;
}

public String getTiposVinculacion() {
String sql="";
sql="select ide_ytitiv, descripcion_ytitiv from yavirac_titu_tipos_vinculacion ";
return sql;
}

public String getObjetivoProyectoVinculacion() {
String sql="";
sql="select IDE_YTIOBI,detalle_ytitio as tipo,detalle_ytiind as indicador,detalle_ytimev as verificacion\n" +
"from yavirac_titu_objetivo_indica_me b\n" +
"left join yavirac_titu_tipo_objetivo c on b.ide_ytitio=c.ide_ytitio\n" +
"left join yavirac_titu_indicador d on b.ide_ytiind=d.ide_ytiind\n" +
"left join yavirac_titu_medios_verifica e on b.ide_ytimev=e.ide_ytimev\n" +
"order by detalle_ytitio desc,detalle_ytiind,detalle_ytimev";
return sql;
}
public String getResultadoActividades() {
String sql="";
sql="select IDE_YTITIA,c.ide_ytitio,detalle_ytitio as tipo,detalle_ytiacr as indicador,detalle_ytimev as verificacion\n" +
"from yavirac_titu_tipo_activi_resul b\n" +
"left join yavirac_titu_tipo_objetivo c on b.ide_ytitio=c.ide_ytitio\n" +
"left join yavirac_titu_medios_verifica e on b.ide_ytimev=e.ide_ytimev\n" +
"left join yavirac_titu_actividad_resulta d on b.ide_ytiacr=d.ide_ytiacr\n" +
"order by detalle_ytitio";
return sql;
}
}


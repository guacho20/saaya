/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horarios.ejb;

import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Andres
 */
@Stateless
public class ServiciosHorarios {
    private final Utilitario utilitario = new Utilitario();
    
       /**
     * Retorna el periodo academico vigente
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del periodo academico
     */
    public String getHorarios(String activo) {
        String sql="";
        sql="select ide_yhothj, descripcion_yhothj from yavirac_hora_tipo_horario_jorna  where activo_yhothj in ("+activo+") order by descripcion_yhothj desc";
        return sql;
    }
    
     public String getDefinicionJornada(String ide_modalidad, String ide_periodo) {
        String sql="";
        sql= "select distinct c.ide_ystjor, d.descripcion_ystjor from  yavirac_hora_definicion_hora c,  yavirac_stror_jornada d where c.ide_ystjor = d.ide_ystjor and d.descripcion_ystjor = d.descripcion_ystjor and c.ide_ystmod="+ide_modalidad+" and c.ide_ystpea="+ide_periodo+" order by c.ide_ystjor asc";
                //"select ide_ystjor from yavirac_hora_definicion_hora  where activo_yhodeh in ("+activo+")"; //order by descripcion_yhothj desc";
        return sql;
    }
               /**
     * Retorna la descripcion hora
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de la descripcion hora
     */
    public String getDescripcionHora(String codigo) {
        String sql="";
        sql="select  ide_yhodeh, c.ide_ystpea, d.ide_yhothj, a.ide_ystjor, f.ide_ystmod, hora_inicio_yhodeh, hora_final_yhodeh, activo_yhodeh, descripcion_ystjor, descripcion_ystpea, descripcion_yhothj, descripcion_ystmod \n" +
"from yavirac_hora_definicion_hora a, yavirac_stror_jornada b, yavirac_stror_periodo_academic c, yavirac_hora_tipo_horario_jorna d, yavirac_stror_modalidad f\n" +
"where a.ide_ystjor= b.ide_ystjor \n" +
"and a.ide_ystpea= c.ide_ystpea \n" +
"and a.ide_yhothj= d.ide_yhothj \n" +
"and a.ide_ystmod= f.ide_ystmod\n" +
"and a.ide_ystpea = "+codigo+
"order by descripcion_ystjor "
                ;
        return sql;
    }
     /**
     * Retorna la hora
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de la tabla hora
     */
    public String getHora(String activo) {
        String sql="";
        sql="select ide_yhohor, descripcion_yhohor from yavirac_hora_hora  where activo_yhohor in ("+activo+") order by descripcion_yhohor desc";
        return sql;
    }
    
    /**
     * Retorna el Tipo Horarios
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del tipo horarios
     */
    public String getTipoHorarios(String activo) {
        String sql="";
        sql="select ide_yhotih, descripcion_yhotih from yavirac_hora_tipo_horario  where activo_yhotih in ("+activo+") order by descripcion_yhotih ";
        return sql;
    }
     /**
     * Retorna el insert de la replica definicion hora
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del insert replica definicion hora
     */
    public String insertReplicaDefinicionHora(String ide_yhodeh,String ide_ystpea,String ide_yhothj,String ide_ystjor,String ide_ystmod,String hora_inicio_yhodeh,String hora_final_yhodeh,String activo_yhodeh) {
        String sql="";
        sql="insert into yavirac_hora_definicion_hora (ide_yhodeh, ide_ystpea, ide_yhothj, ide_ystjor, ide_ystmod, hora_inicio_yhodeh, hora_final_yhodeh, activo_yhodeh)\n" +
            "values ("+ide_yhodeh+","+ide_ystpea+", "+ide_yhothj+", "+ide_ystjor+", "+ide_ystmod+", '"+hora_inicio_yhodeh+"', '"+hora_final_yhodeh+"', "+activo_yhodeh+") ";
        return sql;
    }
 
       /**
     * Retorna el Periodo de la Hora
     *
     * @param activo.- permite el calculo de periodo de la hora 
     * @return sql del periodo de la hora 
     */
    public String getPeriodoHora(String ide_yhothj, String ide_ystpea) {
        String sql="";
        sql="SELECT   y.ide_yhodeh,y.ide_ystpea,y.ide_yhothj,y.ide_ystjor,y.ide_ystmod,y.descripcion_yhothj,y.descripcion_ystjor,y.descripcion_ystmod,y.hora_inicio_yhodeh,y.hora_final_yhodeh,y.resultado,x.descripcion_yhothj,x.descripcion_ystjor,x.descripcion_ystmod,\n" +
            "x.hora_inicio_yhodeh,x.hora_final_yhodeh,\n" +
            "(case when x.resultado is null then 0 else x.resultado end)as resultadoreceso,\n" +
            "(case when x.hora_clase_ystpea is null then 0 else x.hora_clase_ystpea end),\n" +
            "(case when (y.resultado - x.resultado) / y.hora_clase_ystpea is null then 0 else (y.resultado - x.resultado) / y.hora_clase_ystpea end) as resultadofinal\n" +
            "from (SELECT   ide_yhodeh,c.ide_ystpea, c.ide_yhothj, f.descripcion_yhothj,c.ide_ystjor,b.descripcion_ystjor,c.ide_ystmod,e.descripcion_ystmod,\n" +
            "date_part('hour', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp))) as hora1,\n" +
            " date_part('hour', cast('2001-02-16 '||hora_final_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-09-30 '||hora_final_yhodeh as timestamp))) as hora2,\n" +
            "hora_inicio_yhodeh,hora_final_yhodeh,\n" +
            "( date_part('hour', cast('2001-02-16 '||hora_final_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-09-30 '||hora_final_yhodeh as timestamp)))) - \n" +
            "(date_part('hour', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp)))) as resultado,\n" +
            " d.hora_clase_ystpea\n" +
            "from yavirac_hora_definicion_hora c , yavirac_stror_periodo_academic d, yavirac_stror_jornada b, yavirac_stror_modalidad e, yavirac_hora_tipo_horario_jorna f\n" +
            "where c.ide_ystpea = d.ide_ystpea\n" +
            "and c.ide_ystjor=b.ide_ystjor\n" +
            "and c.ide_ystmod=e.ide_ystmod\n" +
            "and c.ide_yhothj=f.ide_yhothj\n" +
            "and c.ide_yhothj= "+ide_yhothj+"\n" +
            "and c.ide_ystpea="+ide_ystpea+"\n" +
            ") y\n" +
            "left join (\n" +
            "SELECT   ide_yhodeh,c.ide_ystpea, c.ide_yhothj, f.descripcion_yhothj,c.ide_ystjor,b.descripcion_ystjor,c.ide_ystmod,e.descripcion_ystmod,\n" +
            "date_part('hour', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp))) as hora1,\n" +
            " date_part('hour', cast('2001-02-16 '||hora_final_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-09-30 '||hora_final_yhodeh as timestamp))) as hora2,\n" +
            "hora_inicio_yhodeh,hora_final_yhodeh,\n" +
            "( date_part('hour', cast('2001-02-16 '||hora_final_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-09-30 '||hora_final_yhodeh as timestamp)))) - \n" +
            "(date_part('hour', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp)))) as resultado,\n" +
            " d.hora_clase_ystpea\n" +
            "from yavirac_hora_definicion_hora c , yavirac_stror_periodo_academic d, yavirac_stror_jornada b, yavirac_stror_modalidad e, yavirac_hora_tipo_horario_jorna f\n" +
            "where c.ide_ystpea = d.ide_ystpea\n" +
            "and c.ide_ystjor=b.ide_ystjor\n" +
            "and c.ide_ystmod=e.ide_ystmod\n" +
            "and c.ide_yhothj=f.ide_yhothj\n" +
            "and c.ide_yhothj= "+ide_yhothj+"\n" +
            "and c.ide_ystpea="+ide_ystpea+"\n" +
            ") x \n" +
            "on y.ide_ystjor= x.ide_ystjor  ";
        return sql;
    }  
        public String getGrupoAcademico() {
        String sql="";
        sql="SELECT ide_yhogra, detalle_yhogra FROM yavirac_hora_grupo_academic order by detalle_yhogra desc";
        return sql;
    }
   
        public String getTipoGeneracionHora() {
        String sql="";
        sql="SELECT ide_yhotgh, detalle_yhotgh FROM yavirac_hora_tipo_genera_hor ";
        return sql;
    }
        public String getDia() {
        String sql="";
        sql="select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc ";
        return sql;
    }
         public String getMod() {
        String sql="";
        sql="select ide_ystmod, descripcion_ystmod from yavirac_stror_modalidad  ";
        return sql;
    }
         public String getDistribucionInstalacion() {
        String sql="";
        sql="select ide_yhodin, descripcion_yhodin from yavirac_hora_distribuc_instit";
        return sql;
         }
         public String getTipoAdecuacion() {
        String sql="";
        sql="select ide_yhotad, descripcion_yhotad from yavirac_hora_tipo_adecuacion";
        return sql;
         }
        public String getDefinicionHoras(String ide_ystjor, String ide_ystpea, String ide_ystmod) {
        String sql="";
        sql="select a.ide_yhodeh, b.descripcion_yhothj, c.descripcion_ystjor, d.descripcion_ystmod, a.hora_inicio_yhodeh, a.hora_final_yhodeh\n" +
            "from yavirac_hora_definicion_hora a\n" +
            "inner join yavirac_hora_tipo_horario_jorna b on a.ide_yhothj = b.ide_yhothj\n" +
            "inner join yavirac_stror_jornada c on a.ide_ystjor = c.ide_ystjor\n" +
            "inner join yavirac_stror_modalidad d on a.ide_ystmod = d.ide_ystmod\n" +
            "where a.ide_ystpea ="+ide_ystpea+" and a.ide_ystmod ="+ide_ystmod+" and a.ide_ystjor = "+ide_ystjor+"" ;
        return sql;
         }
        public String getDefinicionReceso(String ide_ystjor, String ide_ystpea, String ide_ystmod, String ide_yhothj,String estado) {
        String sql="";
        sql="select a.ide_yhodeh, a.ide_ystpea, a.ide_yhothj, b.descripcion_yhothj, a.ide_ystjor, c.descripcion_ystjor, a.ide_ystmod, d.descripcion_ystmod, a.hora_inicio_yhodeh, a.hora_final_yhodeh, a.activo_yhodeh\n" +
            "from yavirac_hora_definicion_hora a\n" +
            "inner join yavirac_hora_tipo_horario_jorna b on a.ide_yhothj = b.ide_yhothj\n" +
            "inner join yavirac_stror_jornada c on a.ide_ystjor = c.ide_ystjor\n" +
            "inner join yavirac_stror_modalidad d on a.ide_ystmod = d.ide_ystmod\n" +
            "where activo_yhodeh in ("+estado+") and a.ide_ystpea in ("+ide_ystpea+")  and a.ide_yhothj in ("+ide_yhothj+")  and a.ide_ystmod in ("+ide_ystmod+") and a.ide_ystjor in ("+ide_ystjor+")" ;
        return sql;
         }
        public String getNumDias(String ide_yhodia) {
        String sql="";
        sql="select ide_yhodia, descripcion_yhodia from yavirac_hora_dia where ide_yhodia in ("+ide_yhodia+") order by descripcion_yhodia" ;
        return sql;
         }
        public String getNumMension(String ide_ystmen) {
        String sql="";
        sql="select ide_ystmen, descripcion_ystmen from yavirac_stror_mension where ide_ystmen in ("+ide_ystmen+") order by descripcion_ystmen" ;
        return sql;
         }
        public String getResultadoExisteReceso(String hora_receso,String hora_inicio,String hora_fin) {
        String sql="";
        sql="select 1 as resultado,2 as respuesta from yavirac_hora_definicion_hora where ('"+hora_receso+"'::interval + '60' ::interval) between '"+hora_inicio+"'::interval and '"+hora_fin+"'::interval limit 1" ;
        //System.out.println("imprimo resultao receso "+sql);
        return sql;
         }  
        
        public String insertHorarioMatriz(String tipo_horario,String periodo, String mension, String jornada, String modalidad) {
        String sql="";
        sql="INSERT INTO yavirac_matriz_temp(ide_yamatz, ide_ystmal, ide_ystnie,ide_ystmat,ide_ystmen,ide_ysttif,ide_ystjor,\n" +
"ide_ymacal, ide_yhogra, ide_ystins, ide_ypedpe, numero_horas_ystmal, prioridad_materia_ystmal, maximo_horas_ystmal,ide_ystpea,minimo_horas_ystmal, \n" +
"aplica_laboratorio_ystmal, num_min_lab_ystmal, num_max_lab_ystmal,horas_semana_ystmal,horas_semana_lab_ystmal, ide_ystmod) \n" +
"select row_number()over(order by a.ide_ystmal) + (select (case when max( ide_yamatz) is null then 0 else max( ide_yamatz) end) as codigo \n" +
"from yavirac_matriz_temp) as ide, a.ide_ystmal, a.ide_ystnie, a.ide_ystmat, a.ide_ystmen, a.ide_ysttif,a.ide_ystjor,a.ide_ymacal,a.ide_yhogra, \n" +
"b.ide_ystins,c.ide_ypedpe, a.numero_horas_ystmal, a.prioridad_materia_ystmal, a.maximo_horas_ystmal, a.ide_ystpea, a.minimo_horas_ystmal, \n" +
"a.aplica_laboratorio_ystmal, a.num_min_lab_ystmal, a.num_max_lab_ystmal,a.horas_semana_ystmal, (case when a.horas_semana_lab_ystmal is null \n" +
"then 0 else a.horas_semana_lab_ystmal end) as hora_lab, d.ide_ystmod from ( select a.ide_ystmal, a.ide_ystnie, a.ide_ystmat, a.ide_ystmen, a.ide_ysttif, \n" +
"a.numero_horas_ystmal, a.prioridad_materia_ystmal, a.maximo_horas_ystmal, a.minimo_horas_ystmal, a.aplica_laboratorio_ystmal, a.num_min_lab_ystmal, \n" +
"a.num_max_lab_ystmal,ide_ystjor,ide_yhogra,ide_ymacal,b.ide_ystpea,a.horas_semana_ystmal,a.horas_semana_lab_ystmal \n" +
"from yavirac_stror_malla a,yavirac_stror_periodo_mension b, yavirac_hora_malla_periodo c,yavirac_matri_cupo_alumno d \n" +
"where a.ide_ystmen = b.ide_ystmen and b.ide_ystpea= c.ide_ystpea and a.ide_ystmal= c.ide_ystmal and b.ide_ystpea=d.ide_ystpea \n" +
"and a.ide_ystnie=d.ide_ystnie and a.ide_ystmen=d.ide_ystmen and b.ide_ystpea = "+periodo+" ) a \n" +
"left join yavirac_hora_aula_grupo b on a.ide_ymacal=b.ide_ymacal \n" +
"left join yavirac_perso_malla_docente c on a.ide_ystmal=c.ide_ystmal \n" +
"and a.ide_ystpea= c.ide_ystpea and a.ide_yhogra=c.ide_yhogra \n" +
"and a.ide_ystjor = c.ide_ystjor \n" +
"left join (select ide_ystmod,ide_ystjor,ide_ystpea,ide_ystmen from yavirac_hora_periodo_hor group by ide_ystmod,ide_ystjor,ide_ystpea,ide_ystmen) d on a.ide_ystpea = d.ide_ystpea and a.ide_ystjor = d.ide_ystjor and a.ide_ystmen = d.ide_ystmen\n" +
"where a.ide_ystjor = "+jornada+"\n" +
"and d.ide_ystmod = "+modalidad+"\n" +
"and a.ide_ystmen in ("+mension+")" ;
            if(tipo_horario.equals("1")){
            sql +=" and aplica_laboratorio_ystmal=true " ;
            }
            if(tipo_horario.equals("2")){
            sql +=" and aplica_laboratorio_ystmal=false " ;
            }
        //System.out.println("imprimo resultao receso "+sql);
        return sql;
         }  
        
        public int sumDetalleMatriz(String ide){
            int suma_hora=0;
            TablaGenerica tab_suma_detalle_matriz_temp=utilitario.consultar("select 1 as codigo,(case when sum(horas_semana_lab_yamadt) is null then 0 else sum(horas_semana_lab_yamadt) end) +\n" +
            "(case when sum(horas_semana_yamadt) is null then 0 else sum(horas_semana_yamadt) end) as hora_clase\n" +
            "from yavirac_matriz_detalle_temp group by ide_yamatz having ide_yamatz ="+ide);
            if(tab_suma_detalle_matriz_temp.getTotalFilas()>0){
                suma_hora= Integer.parseInt(tab_suma_detalle_matriz_temp.getValor("hora_clase"));
            }
            return suma_hora;
        }
        public int horaLaboratorio(String ide){
            int suma_hora=0;
            TablaGenerica tab_suma_detalle_matriz_temp=utilitario.consultar("select 1 as codigo,(case when sum(horas_semana_lab_yamadt) is null then 0 else sum(horas_semana_lab_yamadt) end) as hora_lab\n" +
                "from yavirac_matriz_detalle_temp group by ide_yamatz having ide_yamatz ="+ide);
            if(tab_suma_detalle_matriz_temp.getTotalFilas()>0){
                suma_hora= Integer.parseInt(tab_suma_detalle_matriz_temp.getValor("hora_lab"));
            }
            return suma_hora;
        }
        public int horaClase(String ide){
            int suma_hora=0;
            TablaGenerica tab_suma_detalle_matriz_temp=utilitario.consultar("select 1 as codigo,(case when sum(horas_semana_yamadt) is null then 0 else sum(horas_semana_yamadt) end) + (case when sum(horas_semana_lab_yamadt) is null then 0 else sum(horas_semana_lab_yamadt) end) as hora_clase\n" +
                "from yavirac_matriz_detalle_temp group by ide_yamatz having ide_yamatz ="+ide);
            if(tab_suma_detalle_matriz_temp.getTotalFilas()>0){
                suma_hora= Integer.parseInt(tab_suma_detalle_matriz_temp.getValor("hora_clase"));
            }
            return suma_hora;
        }
        public String insertaMatrizDetalle(String codigo,String ide_yamatz,String ide_ystnie,String ide_ystmal, String ide_ypedpe,String ide_ystmat,String ide_ystmen,String ide_ysttif,String ide_ystjor,
                                           String ide_ymacal,String ide_yhogra,String ide_ystins,String ide_ystpea,String aplica_laboratorio_yamadt,String horas_semana_lab_yamadt,String horas_semana_yamadt, String ide_ystmod){
            String sql="";
            sql="INSERT INTO yavirac_matriz_detalle_temp( ide_yamadt, ide_yamatz, ide_ystmal, ide_ystnie, ide_ypedpe, ide_ystmat,ide_ystmen, ide_ysttif, ide_ystjor, \n" +
            "ide_ymacal, ide_yhogra, ide_ystins,ide_ystpea, aplica_laboratorio_yamadt, horas_semana_lab_yamadt,horas_semana_yamadt, ide_ystmod) values ( "
                    + codigo+","+ide_yamatz+", "+ide_ystnie+", "+ide_ystmal+", "+ide_ypedpe+", "+ide_ystmat+", "+ide_ystmen+", "+ide_ysttif+", "+ide_ystjor+", "+ide_ymacal+", "+ide_yhogra+", "+ide_ystins+", "+ide_ystpea+", "+aplica_laboratorio_yamadt+", "+horas_semana_lab_yamadt+", "+horas_semana_yamadt+", "+ide_ystmod+")";
          //  System.out.println("sql: "+sql);
            return sql;
        }
        public String validaGeneracionHorarioLaboratorio(String tipo_consulta,String tipo_horario,String ide_ystmod,String ide_ystjor,String ide_ystpea,String ide_yhodia,String ide_ystmen,String ide_yhogra,String ide_ystins,String ide_ypedpe,String condicion){
            String sql="";
            if(tipo_consulta.equals("1")){
                sql+="select ide_yhodia,sum(hora_ocupada) as hora_ocupada,sum( hora_clase) as hora_clase,sum( hora_clase)-sum(hora_ocupada) as hora_disponible from (";
            }
            //if(tipo_consulta.equals("2")){
                sql +="select a.ide_yhopeh,ide_ystmod,ide_ystjor,ide_ystpea,ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia,\n" +
                "(case when contador is null then 0 else contador end) as hora_ocupada,ide_ystins,hora_clase,ide_ypedpe,ide_yhohor,descripcion_yhohor,orden_yhohor\n" +
                "from (\n" +
                "select a.ide_yhopeh,a.ide_ystmod,a.ide_ystjor,a.ide_ystpea,a.ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia,\n" +
                "hora_clase_ystpea/60 as hora_clase,a.ide_yhohor,descripcion_yhohor,orden_yhohor \n" +
                "from yavirac_hora_periodo_hor a,yavirac_hora_dia b,yavirac_stror_periodo_academic c,yavirac_hora_hora d\n" +
                "where  a.ide_yhodia = b.ide_yhodia and a.ide_yhohor = d.ide_yhohor\n" +
                " and a.ide_ystpea = c.ide_ystpea\n" +
                " and ide_yhothj =" +tipo_horario+
                " and ide_ystmod =" +ide_ystmod+
                " and ide_ystjor = " +ide_ystjor+
                " and a.ide_ystpea = " +ide_ystpea+
                " and b.ide_yhodia = " +ide_yhodia+
                " and ide_ystmen = " +ide_ystmen+
                " order by orden_yhodia\n" +
                " ) a\n" +
                " left join (select count(*) as contador,ide_ystins,ide_yhopeh,ide_ypedpe \n" +
                " from yavirac_hora_horario_mate group by ide_yhopeh,ide_ystins,ide_ypedpe )b on a.ide_yhopeh = b.ide_yhopeh and ide_ystins "+ide_ystins+" and ide_ypedpe "+ide_ypedpe+condicion;
            //}
             if(tipo_consulta.equals("1")){
                 sql+=" ) a group by ide_yhodia";
             }
          //   System.out.println("tab_previo_inserta "+sql);
            return sql;
        }
        public String validaGeneracionHorarioClase(String tipo_consulta,String tipo_horario,String ide_ystmod,String ide_ystjor,String ide_ystpea,String ide_yhodia,String ide_ystmen,String ide_yhogra,String ide_ystmal,String ide_ypedpe,String condicion){
            String sql="";
            if(tipo_consulta.equals("1")){
                sql+="select ide_yhodia,sum(hora_ocupada) as hora_ocupada,sum( hora_clase) as hora_clase,sum( hora_clase)-sum(hora_ocupada) as hora_disponible from (";
            }
            //if(tipo_consulta.equals("2")){
                sql +="select a.ide_yhopeh,ide_ystmod,ide_ystjor,ide_ystpea,ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia,\n" +
                     "(case when contador is null then 0 else contador end) as hora_ocupada,ide_yhogra,hora_clase,ide_ypedpe,descripcion_yhohor,orden_yhohor\n" +
                     "from (\n" +
                     "select a.ide_yhopeh,a.ide_ystmod,a.ide_ystjor,a.ide_ystpea,a.ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia,ide_yhohor,\n" +
                    "hora_clase_ystpea/60 as hora_clase,a.ide_yhohor,descripcion_yhohor,orden_yhohor\n" +
                    "from yavirac_hora_periodo_hor a,yavirac_hora_dia b,yavirac_stror_periodo_academic c,yavirac_hora_hora d\n" +
                    "where  a.ide_yhodia = b.ide_yhodia and a.ide_yhohor = d.ide_yhohor\n" +
                    " and a.ide_ystpea = c.ide_ystpea\n" +
                    " and ide_yhothj = " +tipo_horario+
                    " and ide_ystmod = " +ide_ystmod+
                    " and ide_ystjor = " +ide_ystjor+
                    " and a.ide_ystpea = " +ide_ystpea+
                    " and b.ide_yhodia = " +ide_yhodia+
                    " and ide_ystmen = " +ide_ystmen+
                    " order by orden_yhodia\n" +
                    " ) a\n" +
                    " left join (select count(*) as contador,ide_yhogra,ide_yhopeh,ide_ystmal,ide_ypedpe \n" +
                    " from yavirac_hora_horario_mate group by ide_yhopeh,ide_yhogra,ide_ystmal,ide_ypedpe )b on a.ide_yhopeh = b.ide_yhopeh \n" +
                    " and ide_yhogra="+ide_yhogra+" and ide_ystmal="+ide_ystmal+" and ide_ypedpe="+ide_ypedpe+condicion;
            //}
            if(tipo_consulta.equals("1")){
                 sql+=" ) a group by ide_yhodia";
             }
            sql+=" order by orden_yhodia,orden_yhohor";
            return sql;
        }
        public String getSqlDiaHabilitado(String tipo_hora,String ide_ystmod,String ide_ystjor,String ide_ystpea,String ide_ystmen){
            String sql="";
                sql="select a.ide_ystmod,a.ide_ystjor,a.ide_ystpea,a.ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia\n" +
                    "from yavirac_hora_periodo_hor a,yavirac_hora_dia b,yavirac_stror_periodo_academic c\n" +
                    "where  a.ide_yhodia = b.ide_yhodia\n" +
                    "and a.ide_ystpea = c.ide_ystpea\n" +
                    " and ide_yhothj = " +tipo_hora+
                    " and ide_ystmod =" +ide_ystmod+
                    " and ide_ystjor =  " +ide_ystjor+
                    " and a.ide_ystpea = " +ide_ystpea+
                    " and ide_ystmen = "+ide_ystmen+" group by a.ide_ystmod,a.ide_ystjor,a.ide_ystpea,a.ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia\n" +
                    " order by orden_yhodia";
             //   System.out.println(" estamos en el sql "+sql);
            return sql;
        }
        public String getFiltroDocentes(String docente){
            String sql="";
                sql="select a.ide_yhopeh, ide_ypedpe from yavirac_hora_periodo_hor_temp  a\n" +
"left join yavirac_hora_horario_mate_temp b on a.ide_yhopeh = b.ide_yhopeh\n" +
"where b.ide_ypedpe = "+docente+"";
                
                return sql;
        }
   public String getCodCarreras (String ide_ystmal) {
        String sql="";
        sql="select a.ide_ystmal,descripcion_ystnie,detalle_ystmat,descripcion_ystmen \n" +
"                                                           from yavirac_stror_malla a, yavirac_stror_nivel_educacion b,yavirac_stror_mension c,yavirac_stror_materia d\n" +
"                                                           where a.ide_ystnie = b.ide_ystnie\n" +
"                                                           and a.ide_ystmen = c.ide_ystmen\n" +
"                                                           and a.ide_ystmat = d.ide_ystmat\n" +
"                                                           and a.ide_ystmal in ("+ide_ystmal+")" ;
        return sql;

}
   
   
   public String ingresaHorarioRespaldoCNR(){
       String sql="";
       
       sql="INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('2', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('3', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('4', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('5', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('6', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('7', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('8', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('9', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('10', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('11', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('12', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('13', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('14', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('15', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('16', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('17', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('18', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('19', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('20', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('21', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('22', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('23', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('24', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('25', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('26', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('27', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('28', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('29', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('30', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('31', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('32', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('33', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('34', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('35', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('36', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('37', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('38', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('39', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('40', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('41', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('42', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('43', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('44', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('45', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('46', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('47', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('48', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('49', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('50', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('51', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('52', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('53', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('54', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('55', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('56', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('57', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('58', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('59', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('60', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('61', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('62', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('63', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('64', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('65', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('66', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('67', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('68', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('69', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('70', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('71', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('72', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('73', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('74', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('75', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('76', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('77', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('78', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('79', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('80', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('81', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('82', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('83', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('84', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('85', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('86', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('87', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('88', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('89', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('90', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('91', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('7', '38', '283', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('8', '17', '285', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('9', '77', '408', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('10', '64', '425', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('11', '37', '450', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('12', '10', '487', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('13', '37', '225', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('14', '17', '285', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('15', '10', '409', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('16', '72', '421', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('17', '64', '465', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('18', '31', '491', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('19', '70', '280', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('20', '38', '357', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('21', '72', '370', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('22', '77', '444', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('23', '37', '467', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('24', '31', '491', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('25', '77', '240', '3', '17', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('26', '10', '350', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('27', '31', '411', '3', '17', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('28', '75', '448', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('29', '38', '472', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('30', '11', '488', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('31', '30', '273', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('32', '77', '344', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('33', '10', '409', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('34', '31', '447', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('35', '37', '467', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('36', '10', '487', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('37', '77', '240', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('38', '10', '350', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('39', '72', '370', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('40', '38', '449', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('41', '11', '466', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('42', '75', '490', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('43', '11', '224', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('44', '72', '288', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('45', '77', '408', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('46', '38', '413', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('47', '70', '475', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('48', '75', '489', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('49', '37', '225', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('50', '10', '350', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('51', '17', '369', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('52', '72', '421', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('53', '31', '470', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('54', '70', '475', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('55', '38', '197', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('56', '30', '355', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('57', '37', '372', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('58', '72', '421', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('59', '64', '465', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('60', '77', '492', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('61', '30', '273', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('62', '77', '344', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('63', '10', '409', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('64', '30', '446', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('65', '72', '471', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('66', '10', '487', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('67', '30', '273', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('68', '31', '356', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('69', '72', '370', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('70', '77', '444', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('71', '11', '466', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('72', '75', '490', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('73', '77', '240', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('74', '72', '288', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('75', '37', '372', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('76', '38', '413', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('77', '70', '451', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('78', '75', '489', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('79', '70', '280', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('80', '11', '316', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('81', '17', '369', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('82', '10', '445', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('83', '31', '470', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('84', '70', '475', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('85', '11', '224', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('86', '30', '355', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('87', '37', '372', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('88', '75', '448', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('89', '70', '451', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('90', '77', '492', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('91', '72', '182', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('92', '30', '355', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('93', '37', '372', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('94', '75', '448', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('95', '38', '472', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('96', '10', '474', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('97', '37', '225', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('98', '31', '356', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('99', '30', '410', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('100', '10', '445', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('101', '37', '450', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('102', '75', '489', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('103', '17', '206', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('104', '37', '318', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('105', '31', '411', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('106', '77', '444', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('107', '75', '468', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('108', '10', '474', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('109', '72', '182', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('110', '11', '316', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('111', '38', '412', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('112', '37', '425', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('113', '74', '471', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('114', '75', '490', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('115', '38', '283', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('116', '37', '318', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('117', '11', '371', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('118', '31', '447', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('119', '75', '468', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('120', '77', '497', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('121', '10', '272', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('122', '72', '288', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('123', '38', '412', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('124', '30', '446', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('125', '75', '469', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('126', '77', '492', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('127', '37', '225', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('128', '72', '288', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('129', '30', '410', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('130', '10', '445', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('131', '31', '470', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('132', '70', '475', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('133', '17', '206', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('134', '77', '344', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('135', '31', '411', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('136', '11', '424', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('137', '75', '468', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('138', '10', '474', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('139', '72', '182', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('140', '31', '356', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('141', '77', '408', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('142', '37', '425', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('143', '74', '471', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('144', '75', '490', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('145', '70', '280', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('146', '37', '318', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('147', '11', '371', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('148', '31', '447', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('149', '75', '469', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('150', '77', '505', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('151', '10', '272', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('152', '72', '288', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('153', '30', '410', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('154', '31', '447', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('155', '75', '469', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('156', '77', '492', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('157', '42', '20', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('158', '27', '83', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('159', '70', '180', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('160', '74', '42', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('161', '79', '82', null, '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('162', '15', '153', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('163', '79', '42', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('164', '15', '73', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('165', '76', '154', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('166', '22', '56', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('167', '77', '81', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('168', '75', '179', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('169', '22', '56', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('170', '70', '75', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('171', '75', '179', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('172', '42', '20', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('173', '15', '73', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('174', '70', '180', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('175', '74', '19', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('176', '79', '82', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('177', '15', '153', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('178', '42', '20', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('179', '15', '73', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('180', '76', '154', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('181', '22', '56', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('182', '77', '81', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('183', '76', '154', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('184', '76', '41', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('185', '70', '75', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('186', '62', '181', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('187', '22', '56', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('188', '15', '73', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('189', '75', '179', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('190', '10', '42', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('191', '15', '73', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('192', '42', '157', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('193', '22', '56', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('194', '27', '83', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('195', '75', '179', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('196', '74', '19', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('197', '27', '83', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('198', '76', '154', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('199', '76', '41', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('200', '79', '82', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('201', '42', '157', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('202', '74', '19', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('203', '10', '74', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('204', '75', '179', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('205', '15', '583', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('206', '60', '74', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('207', '42', '157', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('208', '22', '56', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('209', '27', '83', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('210', '62', '181', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('211', '15', '583', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('212', '79', '82', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('213', '62', '181', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('214', '79', '42', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('215', '27', '83', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('216', '76', '154', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('217', '15', '583', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('218', '77', '81', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('219', '15', '153', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('220', '15', '583', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('221', '76', '74', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('222', '70', '180', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('223', '10', '42', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('224', '79', '82', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('225', '62', '181', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('226', '74', '19', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('227', '10', '74', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('228', '15', '153', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('229', '15', '583', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('230', '27', '83', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('231', '76', '154', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('232', '78', '515', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('233', '24', '522', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('234', '76', '537', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('235', '63', '507', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('236', '24', '522', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('237', '76', '537', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('238', '24', '508', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('239', '75', '529', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('240', '66', '547', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('241', '10', '511', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('242', '61', '521', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('243', '66', '547', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('244', '66', '500', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('245', '24', '522', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('246', '61', '542', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('247', '63', '512', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('248', '61', '531', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('249', '70', '541', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('250', '63', '507', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('251', '24', '522', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('252', '76', '537', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('253', '63', '512', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('254', '75', '529', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('255', '66', '547', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('256', '10', '511', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('257', '61', '521', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('258', '24', '549', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('259', '66', '500', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('260', '70', '526', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('261', '24', '549', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('262', '63', '512', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('263', '6', '530', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('264', '64', '538', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('265', '66', '500', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('266', '61', '531', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('267', '26', '550', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('268', '63', '512', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('269', '2', '531', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('270', '64', '538', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('271', '63', '512', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('272', '66', '518', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('273', '24', '549', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('274', '61', '502', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('275', '66', '518', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('276', '24', '549', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('277', '66', '500', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('278', '24', '522', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('279', '61', '542', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('280', '78', '515', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('281', '6', '530', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('282', '64', '538', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('283', '78', '515', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('284', '77', '527', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('285', '70', '541', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('286', '66', '506', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('287', '63', '523', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('288', '61', '542', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('289', '61', '502', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('290', '70', '526', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('291', '26', '550', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('292', '66', '500', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('293', '77', '527', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('294', '74', '545', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('295', '61', '506', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('296', '6', '530', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('297', '64', '538', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('298', '78', '515', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('299', '64', '520', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('300', '61', '542', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('301', '66', '506', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('302', '63', '523', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('303', '76', '537', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('304', '10', '511', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('305', '6', '530', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('306', '26', '550', '3', '91', null);";
       
    return sql;   
   }
   public String ingresaHorarioRespaldoTempCNR(){
       String sql="";
       
       sql="INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('2', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('3', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('4', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('5', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('6', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('7', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('8', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('9', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('10', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('11', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('12', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('13', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('14', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('15', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('16', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('17', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('18', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('19', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('20', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('21', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('22', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('23', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('24', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('25', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('26', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('27', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('28', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('29', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('30', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('31', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('32', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('33', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('34', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('35', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('36', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('37', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('38', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('39', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('40', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('41', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('42', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('43', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('44', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('45', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('46', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('47', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('48', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('49', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('50', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('51', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('52', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('53', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('54', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('55', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('56', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('57', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('58', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('59', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('60', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('61', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('62', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('63', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('64', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('65', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('66', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('67', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('68', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('69', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('70', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('71', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('72', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('73', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('74', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('75', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('76', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('77', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('78', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('79', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('80', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('81', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('82', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('83', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('84', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('85', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('86', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('87', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('88', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('89', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('90', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('91', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('7', '38', '283', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('8', '17', '285', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('9', '77', '408', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('10', '64', '425', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('11', '37', '450', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('12', '10', '487', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('13', '37', '225', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('14', '17', '285', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('15', '10', '409', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('16', '72', '421', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('17', '64', '465', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('18', '31', '491', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('19', '70', '280', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('20', '38', '357', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('21', '72', '370', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('22', '77', '444', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('23', '37', '467', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('24', '31', '491', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('25', '77', '240', '3', '17', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('26', '10', '350', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('27', '31', '411', '3', '17', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('28', '75', '448', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('29', '38', '472', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('30', '11', '488', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('31', '30', '273', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('32', '77', '344', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('33', '10', '409', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('34', '31', '447', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('35', '37', '467', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('36', '10', '487', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('37', '77', '240', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('38', '10', '350', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('39', '72', '370', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('40', '38', '449', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('41', '11', '466', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('42', '75', '490', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('43', '11', '224', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('44', '72', '288', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('45', '77', '408', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('46', '38', '413', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('47', '70', '475', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('48', '75', '489', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('49', '37', '225', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('50', '10', '350', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('51', '17', '369', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('52', '72', '421', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('53', '31', '470', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('54', '70', '475', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('55', '38', '197', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('56', '30', '355', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('57', '37', '372', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('58', '72', '421', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('59', '64', '465', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('60', '77', '492', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('61', '30', '273', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('62', '77', '344', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('63', '10', '409', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('64', '30', '446', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('65', '72', '471', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('66', '10', '487', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('67', '30', '273', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('68', '31', '356', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('69', '72', '370', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('70', '77', '444', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('71', '11', '466', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('72', '75', '490', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('73', '77', '240', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('74', '72', '288', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('75', '37', '372', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('76', '38', '413', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('77', '70', '451', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('78', '75', '489', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('79', '70', '280', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('80', '11', '316', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('81', '17', '369', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('82', '10', '445', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('83', '31', '470', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('84', '70', '475', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('85', '11', '224', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('86', '30', '355', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('87', '37', '372', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('88', '75', '448', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('89', '70', '451', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('90', '77', '492', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('91', '72', '182', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('92', '30', '355', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('93', '37', '372', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('94', '75', '448', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('95', '38', '472', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('96', '10', '474', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('97', '37', '225', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('98', '31', '356', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('99', '30', '410', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('100', '10', '445', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('101', '37', '450', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('102', '75', '489', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('103', '17', '206', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('104', '37', '318', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('105', '31', '411', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('106', '77', '444', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('107', '75', '468', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('108', '10', '474', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('109', '72', '182', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('110', '11', '316', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('111', '38', '412', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('112', '37', '425', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('113', '74', '471', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('114', '75', '490', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('115', '38', '283', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('116', '37', '318', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('117', '11', '371', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('118', '31', '447', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('119', '75', '468', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('120', '77', '497', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('121', '10', '272', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('122', '72', '288', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('123', '38', '412', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('124', '30', '446', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('125', '75', '469', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('126', '77', '492', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('127', '37', '225', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('128', '72', '288', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('129', '30', '410', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('130', '10', '445', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('131', '31', '470', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('132', '70', '475', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('133', '17', '206', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('134', '77', '344', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('135', '31', '411', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('136', '11', '424', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('137', '75', '468', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('138', '10', '474', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('139', '72', '182', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('140', '31', '356', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('141', '77', '408', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('142', '37', '425', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('143', '74', '471', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('144', '75', '490', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('145', '70', '280', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('146', '37', '318', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('147', '11', '371', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('148', '31', '447', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('149', '75', '469', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('150', '77', '505', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('151', '10', '272', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('152', '72', '288', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('153', '30', '410', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('154', '31', '447', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('155', '75', '469', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('156', '77', '492', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('157', '42', '20', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('158', '27', '83', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('159', '70', '180', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('160', '74', '42', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('161', '79', '82', null, '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('162', '15', '153', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('163', '79', '42', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('164', '15', '73', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('165', '76', '154', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('166', '22', '56', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('167', '77', '81', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('168', '75', '179', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('169', '22', '56', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('170', '70', '75', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('171', '75', '179', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('172', '42', '20', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('173', '15', '73', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('174', '70', '180', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('175', '74', '19', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('176', '79', '82', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('177', '15', '153', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('178', '42', '20', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('179', '15', '73', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('180', '76', '154', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('181', '22', '56', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('182', '77', '81', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('183', '76', '154', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('184', '76', '41', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('185', '70', '75', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('186', '62', '181', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('187', '22', '56', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('188', '15', '73', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('189', '75', '179', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('190', '10', '42', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('191', '15', '73', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('192', '42', '157', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('193', '22', '56', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('194', '27', '83', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('195', '75', '179', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('196', '74', '19', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('197', '27', '83', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('198', '76', '154', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('199', '76', '41', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('200', '79', '82', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('201', '42', '157', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('202', '74', '19', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('203', '10', '74', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('204', '75', '179', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('205', '15', '583', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('206', '60', '74', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('207', '42', '157', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('208', '22', '56', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('209', '27', '83', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('210', '62', '181', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('211', '15', '583', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('212', '79', '82', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('213', '62', '181', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('214', '79', '42', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('215', '27', '83', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('216', '76', '154', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('217', '15', '583', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('218', '77', '81', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('219', '15', '153', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('220', '15', '583', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('221', '76', '74', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('222', '70', '180', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('223', '10', '42', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('224', '79', '82', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('225', '62', '181', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('226', '74', '19', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('227', '10', '74', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('228', '15', '153', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('229', '15', '583', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('230', '27', '83', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('231', '76', '154', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('232', '78', '515', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('233', '24', '522', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('234', '76', '537', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('235', '63', '507', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('236', '24', '522', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('237', '76', '537', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('238', '24', '508', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('239', '75', '529', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('240', '66', '547', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('241', '10', '511', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('242', '61', '521', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('243', '66', '547', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('244', '66', '500', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('245', '24', '522', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('246', '61', '542', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('247', '63', '512', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('248', '61', '531', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('249', '70', '541', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('250', '63', '507', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('251', '24', '522', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('252', '76', '537', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('253', '63', '512', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('254', '75', '529', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('255', '66', '547', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('256', '10', '511', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('257', '61', '521', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('258', '24', '549', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('259', '66', '500', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('260', '70', '526', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('261', '24', '549', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('262', '63', '512', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('263', '6', '530', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('264', '64', '538', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('265', '66', '500', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('266', '61', '531', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('267', '26', '550', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('268', '63', '512', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('269', '2', '531', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('270', '64', '538', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('271', '63', '512', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('272', '66', '518', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('273', '24', '549', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('274', '61', '502', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('275', '66', '518', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('276', '24', '549', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('277', '66', '500', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('278', '24', '522', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('279', '61', '542', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('280', '78', '515', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('281', '6', '530', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('282', '64', '538', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('283', '78', '515', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('284', '77', '527', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('285', '70', '541', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('286', '66', '506', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('287', '63', '523', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('288', '61', '542', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('289', '61', '502', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('290', '70', '526', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('291', '26', '550', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('292', '66', '500', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('293', '77', '527', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('294', '74', '545', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('295', '61', '506', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('296', '6', '530', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('297', '64', '538', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('298', '78', '515', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('299', '64', '520', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('300', '61', '542', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('301', '66', '506', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('302', '63', '523', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('303', '76', '537', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('304', '10', '511', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('305', '6', '530', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('306', '26', '550', '3', '91', null);";
       
       return sql;
   }
   public String ingresaHorarioRespaldoSNR(){
       String sql="INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('17', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('18', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('19', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('20', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('21', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('22', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('23', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('24', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('25', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('26', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('27', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('28', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('29', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('30', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('31', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('32', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('33', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('34', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('35', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('36', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('37', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('38', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('39', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('40', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('41', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('42', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('43', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('44', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('45', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('46', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('47', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('48', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('49', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('50', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('51', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('52', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('53', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('54', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('55', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('56', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('57', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('58', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('59', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('60', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('61', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('62', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('63', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('64', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('65', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('66', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('67', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('68', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('69', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('70', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('71', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('72', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('73', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('74', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('75', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('76', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('77', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('78', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('79', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('80', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('81', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('82', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('83', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('84', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('85', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('86', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('87', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('88', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('89', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('90', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('91', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('7', '38', '283', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('8', '17', '285', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('9', '77', '408', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('10', '64', '425', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('11', '37', '450', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('12', '10', '487', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('13', '37', '225', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('14', '17', '285', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('15', '10', '409', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('16', '72', '421', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('17', '64', '465', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('18', '31', '491', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('19', '70', '280', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('20', '38', '357', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('21', '72', '370', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('22', '77', '444', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('23', '37', '467', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('24', '31', '491', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('25', '77', '240', '3', '17', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('26', '10', '350', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('27', '31', '411', '3', '17', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('28', '75', '448', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('29', '38', '472', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('30', '11', '488', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('31', '30', '273', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('32', '77', '344', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('33', '10', '409', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('34', '31', '447', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('35', '37', '467', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('36', '10', '487', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('37', '77', '240', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('38', '10', '350', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('39', '72', '370', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('40', '38', '449', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('41', '11', '466', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('42', '75', '490', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('43', '11', '224', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('44', '72', '288', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('45', '77', '408', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('46', '38', '413', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('47', '70', '475', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('48', '75', '489', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('49', '37', '225', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('50', '10', '350', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('51', '17', '369', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('52', '72', '421', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('53', '31', '470', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('54', '70', '475', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('55', '38', '197', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('56', '30', '355', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('57', '37', '372', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('58', '72', '421', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('59', '64', '465', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('60', '77', '492', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('61', '30', '273', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('62', '77', '344', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('63', '10', '409', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('64', '30', '446', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('65', '72', '471', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('66', '10', '487', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('67', '30', '273', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('68', '31', '356', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('69', '72', '370', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('70', '77', '444', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('71', '11', '466', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('72', '75', '490', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('73', '77', '240', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('74', '72', '288', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('75', '37', '372', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('76', '38', '413', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('77', '70', '451', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('78', '75', '489', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('79', '70', '280', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('80', '11', '316', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('81', '17', '369', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('82', '10', '445', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('83', '31', '470', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('84', '70', '475', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('85', '11', '224', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('86', '30', '355', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('87', '37', '372', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('88', '75', '448', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('89', '70', '451', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('90', '77', '492', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('91', '72', '182', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('92', '30', '355', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('93', '37', '372', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('94', '75', '448', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('95', '38', '472', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('96', '10', '474', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('97', '37', '225', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('98', '31', '356', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('99', '30', '410', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('100', '10', '445', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('101', '37', '450', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('102', '75', '489', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('103', '17', '206', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('104', '37', '318', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('105', '31', '411', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('106', '77', '444', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('107', '75', '468', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('108', '10', '474', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('109', '72', '182', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('110', '11', '316', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('111', '38', '412', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('112', '37', '425', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('113', '74', '471', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('114', '75', '490', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('115', '38', '283', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('116', '37', '318', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('117', '11', '371', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('118', '31', '447', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('119', '75', '468', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('120', '77', '497', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('121', '10', '272', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('122', '72', '288', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('123', '38', '412', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('124', '30', '446', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('125', '75', '469', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('126', '77', '492', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('127', '37', '225', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('128', '72', '288', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('129', '30', '410', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('130', '10', '445', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('131', '31', '470', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('132', '70', '475', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('133', '17', '206', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('134', '77', '344', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('135', '31', '411', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('136', '11', '424', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('137', '75', '468', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('138', '10', '474', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('139', '72', '182', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('140', '31', '356', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('141', '77', '408', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('142', '37', '425', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('143', '74', '471', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('144', '75', '490', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('145', '70', '280', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('146', '37', '318', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('147', '11', '371', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('148', '31', '447', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('149', '75', '469', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('150', '77', '505', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('151', '10', '272', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('152', '72', '288', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('153', '30', '410', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('154', '31', '447', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('155', '75', '469', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('156', '77', '492', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('157', '42', '20', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('158', '27', '83', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('159', '70', '180', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('160', '74', '42', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('161', '79', '82', null, '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('162', '15', '153', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('163', '79', '42', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('164', '15', '73', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('165', '76', '154', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('166', '22', '56', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('167', '77', '81', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('168', '75', '179', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('169', '22', '56', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('170', '70', '75', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('171', '75', '179', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('172', '42', '20', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('173', '15', '73', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('174', '70', '180', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('175', '74', '19', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('176', '79', '82', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('177', '15', '153', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('178', '42', '20', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('179', '15', '73', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('180', '76', '154', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('181', '22', '56', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('182', '77', '81', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('183', '76', '154', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('184', '76', '41', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('185', '70', '75', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('186', '62', '181', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('187', '22', '56', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('188', '15', '73', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('189', '75', '179', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('190', '10', '42', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('191', '15', '73', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('192', '42', '157', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('193', '22', '56', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('194', '27', '83', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('195', '75', '179', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('196', '74', '19', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('197', '27', '83', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('198', '76', '154', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('199', '76', '41', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('200', '79', '82', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('201', '42', '157', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('202', '74', '19', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('203', '10', '74', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('204', '75', '179', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('205', '15', '583', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('206', '60', '74', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('207', '42', '157', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('208', '22', '56', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('209', '27', '83', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('210', '62', '181', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('211', '15', '583', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('212', '79', '82', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('213', '62', '181', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('214', '79', '42', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('215', '27', '83', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('216', '76', '154', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('217', '15', '583', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('218', '77', '81', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('219', '15', '153', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('220', '15', '583', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('221', '76', '74', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('222', '70', '180', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('223', '10', '42', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('224', '79', '82', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('225', '62', '181', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('226', '74', '19', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('227', '10', '74', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('228', '15', '153', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('229', '15', '583', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('230', '27', '83', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('231', '76', '154', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('232', '78', '515', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('233', '24', '522', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('234', '76', '537', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('235', '63', '507', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('236', '24', '522', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('237', '76', '537', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('238', '24', '508', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('239', '75', '529', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('240', '66', '547', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('241', '10', '511', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('242', '61', '521', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('243', '66', '547', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('244', '66', '500', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('245', '24', '522', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('246', '61', '542', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('247', '63', '512', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('248', '61', '531', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('249', '70', '541', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('250', '63', '507', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('251', '24', '522', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('252', '76', '537', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('253', '63', '512', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('254', '75', '529', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('255', '66', '547', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('256', '10', '511', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('257', '61', '521', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('258', '24', '549', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('259', '66', '500', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('260', '70', '526', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('261', '24', '549', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('262', '63', '512', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('263', '6', '530', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('264', '64', '538', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('265', '66', '500', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('266', '61', '531', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('267', '26', '550', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('268', '63', '512', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('269', '2', '531', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('270', '64', '538', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('271', '63', '512', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('272', '66', '518', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('273', '24', '549', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('274', '61', '502', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('275', '66', '518', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('276', '24', '549', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('277', '66', '500', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('278', '24', '522', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('279', '61', '542', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('280', '78', '515', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('281', '6', '530', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('282', '64', '538', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('283', '78', '515', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('284', '77', '527', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('285', '70', '541', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('286', '66', '506', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('287', '63', '523', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('288', '61', '542', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('289', '61', '502', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('290', '70', '526', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('291', '26', '550', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('292', '66', '500', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('293', '77', '527', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('294', '74', '545', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('295', '61', '506', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('296', '6', '530', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('297', '64', '538', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('298', '78', '515', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('299', '64', '520', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('300', '61', '542', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('301', '66', '506', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('302', '63', '523', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('303', '76', '537', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('304', '10', '511', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('305', '6', '530', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('306', '26', '550', '3', '91', null);";
       
       sql="";
       return sql;
   }
   
   
   public String ingresaHorarioRespaldoTempSNR(){
       String sql="INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('17', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('18', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('19', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('20', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('21', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('22', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('23', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('24', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('25', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('26', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('27', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('28', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('29', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('30', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('31', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('32', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('33', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('34', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('35', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('36', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('37', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('38', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('39', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('40', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('41', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('42', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('43', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('44', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('45', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('46', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('47', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('48', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('49', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('50', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('51', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('52', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('53', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('54', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('55', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('56', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('57', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('58', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('59', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('60', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('61', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('62', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('63', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('64', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('65', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('66', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('67', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('68', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('69', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('70', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('71', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('72', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('73', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('74', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('75', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('76', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('77', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('78', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('79', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('80', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('81', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('82', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('83', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('84', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('85', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('86', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('87', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('88', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('89', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('90', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('91', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('7', '38', '283', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('8', '17', '285', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('9', '77', '408', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('10', '64', '425', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('11', '37', '450', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('12', '10', '487', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('13', '37', '225', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('14', '17', '285', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('15', '10', '409', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('16', '72', '421', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('17', '64', '465', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('18', '31', '491', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('19', '70', '280', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('20', '38', '357', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('21', '72', '370', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('22', '77', '444', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('23', '37', '467', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('24', '31', '491', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('25', '77', '240', '3', '17', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('26', '10', '350', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('27', '31', '411', '3', '17', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('28', '75', '448', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('29', '38', '472', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('30', '11', '488', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('31', '30', '273', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('32', '77', '344', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('33', '10', '409', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('34', '31', '447', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('35', '37', '467', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('36', '10', '487', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('37', '77', '240', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('38', '10', '350', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('39', '72', '370', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('40', '38', '449', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('41', '11', '466', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('42', '75', '490', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('43', '11', '224', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('44', '72', '288', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('45', '77', '408', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('46', '38', '413', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('47', '70', '475', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('48', '75', '489', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('49', '37', '225', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('50', '10', '350', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('51', '17', '369', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('52', '72', '421', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('53', '31', '470', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('54', '70', '475', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('55', '38', '197', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('56', '30', '355', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('57', '37', '372', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('58', '72', '421', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('59', '64', '465', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('60', '77', '492', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('61', '30', '273', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('62', '77', '344', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('63', '10', '409', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('64', '30', '446', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('65', '72', '471', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('66', '10', '487', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('67', '30', '273', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('68', '31', '356', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('69', '72', '370', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('70', '77', '444', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('71', '11', '466', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('72', '75', '490', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('73', '77', '240', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('74', '72', '288', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('75', '37', '372', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('76', '38', '413', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('77', '70', '451', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('78', '75', '489', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('79', '70', '280', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('80', '11', '316', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('81', '17', '369', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('82', '10', '445', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('83', '31', '470', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('84', '70', '475', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('85', '11', '224', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('86', '30', '355', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('87', '37', '372', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('88', '75', '448', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('89', '70', '451', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('90', '77', '492', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('91', '72', '182', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('92', '30', '355', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('93', '37', '372', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('94', '75', '448', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('95', '38', '472', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('96', '10', '474', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('97', '37', '225', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('98', '31', '356', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('99', '30', '410', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('100', '10', '445', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('101', '37', '450', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('102', '75', '489', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('103', '17', '206', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('104', '37', '318', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('105', '31', '411', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('106', '77', '444', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('107', '75', '468', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('108', '10', '474', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('109', '72', '182', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('110', '11', '316', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('111', '38', '412', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('112', '37', '425', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('113', '74', '471', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('114', '75', '490', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('115', '38', '283', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('116', '37', '318', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('117', '11', '371', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('118', '31', '447', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('119', '75', '468', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('120', '77', '497', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('121', '10', '272', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('122', '72', '288', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('123', '38', '412', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('124', '30', '446', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('125', '75', '469', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('126', '77', '492', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('127', '37', '225', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('128', '72', '288', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('129', '30', '410', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('130', '10', '445', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('131', '31', '470', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('132', '70', '475', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('133', '17', '206', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('134', '77', '344', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('135', '31', '411', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('136', '11', '424', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('137', '75', '468', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('138', '10', '474', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('139', '72', '182', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('140', '31', '356', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('141', '77', '408', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('142', '37', '425', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('143', '74', '471', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('144', '75', '490', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('145', '70', '280', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('146', '37', '318', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('147', '11', '371', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('148', '31', '447', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('149', '75', '469', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('150', '77', '505', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('151', '10', '272', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('152', '72', '288', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('153', '30', '410', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('154', '31', '447', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('155', '75', '469', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('156', '77', '492', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('157', '42', '20', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('158', '27', '83', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('159', '70', '180', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('160', '74', '42', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('161', '79', '82', null, '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('162', '15', '153', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('163', '79', '42', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('164', '15', '73', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('165', '76', '154', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('166', '22', '56', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('167', '77', '81', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('168', '75', '179', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('169', '22', '56', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('170', '70', '75', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('171', '75', '179', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('172', '42', '20', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('173', '15', '73', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('174', '70', '180', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('175', '74', '19', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('176', '79', '82', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('177', '15', '153', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('178', '42', '20', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('179', '15', '73', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('180', '76', '154', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('181', '22', '56', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('182', '77', '81', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('183', '76', '154', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('184', '76', '41', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('185', '70', '75', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('186', '62', '181', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('187', '22', '56', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('188', '15', '73', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('189', '75', '179', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('190', '10', '42', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('191', '15', '73', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('192', '42', '157', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('193', '22', '56', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('194', '27', '83', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('195', '75', '179', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('196', '74', '19', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('197', '27', '83', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('198', '76', '154', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('199', '76', '41', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('200', '79', '82', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('201', '42', '157', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('202', '74', '19', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('203', '10', '74', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('204', '75', '179', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('205', '15', '583', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('206', '60', '74', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('207', '42', '157', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('208', '22', '56', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('209', '27', '83', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('210', '62', '181', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('211', '15', '583', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('212', '79', '82', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('213', '62', '181', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('214', '79', '42', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('215', '27', '83', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('216', '76', '154', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('217', '15', '583', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('218', '77', '81', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('219', '15', '153', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('220', '15', '583', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('221', '76', '74', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('222', '70', '180', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('223', '10', '42', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('224', '79', '82', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('225', '62', '181', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('226', '74', '19', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('227', '10', '74', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('228', '15', '153', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('229', '15', '583', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('230', '27', '83', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('231', '76', '154', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('232', '78', '515', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('233', '24', '522', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('234', '76', '537', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('235', '63', '507', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('236', '24', '522', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('237', '76', '537', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('238', '24', '508', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('239', '75', '529', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('240', '66', '547', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('241', '10', '511', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('242', '61', '521', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('243', '66', '547', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('244', '66', '500', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('245', '24', '522', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('246', '61', '542', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('247', '63', '512', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('248', '61', '531', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('249', '70', '541', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('250', '63', '507', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('251', '24', '522', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('252', '76', '537', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('253', '63', '512', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('254', '75', '529', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('255', '66', '547', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('256', '10', '511', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('257', '61', '521', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('258', '24', '549', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('259', '66', '500', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('260', '70', '526', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('261', '24', '549', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('262', '63', '512', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('263', '6', '530', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('264', '64', '538', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('265', '66', '500', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('266', '61', '531', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('267', '26', '550', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('268', '63', '512', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('269', '2', '531', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('270', '64', '538', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('271', '63', '512', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('272', '66', '518', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('273', '24', '549', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('274', '61', '502', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('275', '66', '518', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('276', '24', '549', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('277', '66', '500', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('278', '24', '522', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('279', '61', '542', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('280', '78', '515', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('281', '6', '530', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('282', '64', '538', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('283', '78', '515', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('284', '77', '527', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('285', '70', '541', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('286', '66', '506', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('287', '63', '523', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('288', '61', '542', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('289', '61', '502', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('290', '70', '526', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('291', '26', '550', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('292', '66', '500', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('293', '77', '527', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('294', '74', '545', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('295', '61', '506', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('296', '6', '530', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('297', '64', '538', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('298', '78', '515', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('299', '64', '520', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('300', '61', '542', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('301', '66', '506', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('302', '63', '523', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('303', '76', '537', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('304', '10', '511', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('305', '6', '530', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('306', '26', '550', '3', '91', null);";
       
       return sql;
   }
   
   
   
   public String ingresaHorariosinReceso(){
       String sql="";
       
       sql="INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('17', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('18', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('19', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('20', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('21', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('22', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('23', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('24', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('25', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('26', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('27', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('28', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('29', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('30', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('31', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('32', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('33', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('34', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('35', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('36', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('37', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('38', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('39', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('40', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('41', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('42', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('43', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('44', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('45', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('46', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('47', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('48', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('49', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('50', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('51', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('52', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('53', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('54', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('55', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('56', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('57', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('58', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('59', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('60', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('61', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('62', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('63', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('64', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('65', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('66', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('67', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('68', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('69', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('70', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('71', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('72', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('73', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('74', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('75', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('76', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('77', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('78', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('79', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('80', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('81', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('82', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('83', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('84', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('85', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('86', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('87', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('88', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('89', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('90', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('91', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('93', '4', '5', '10', '2', '12:00:00', '13:00:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('94', '4', '5', '11', '2', '13:00:00', '14:00:00', 't', '9', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('96', '4', '5', '13', '2', '16:00:00', '17:00:00', 't', '10', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('97', '4', '5', '12', '2', '14:00:00', '15:00:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('98', '4', '5', '13', '2', '16:00:00', '17:00:00', 't', '7', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('99', '4', '6', '15', '2', '18:00:00', '19:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('100', '4', '6', '16', '2', '19:00:00', '20:00:00', 't', '10', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('101', '4', '5', '10', '2', '12:00:00', '13:00:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('102', '4', '5', '11', '2', '13:00:00', '14:00:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('103', '4', '5', '12', '2', '14:00:00', '15:00:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('104', '4', '4', '13', '2', '16:00:00', '17:00:00', 't', '8', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('105', '4', '4', '12', '2', '14:00:00', '15:00:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('106', '4', '6', '15', '2', '18:00:00', '19:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('107', '4', '6', '16', '2', '19:00:00', '20:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('108', '4', '6', '17', '2', '20:00:00', '21:00:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('7', '17', '206', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('8', '10', '350', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('9', '77', '408', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('10', '75', '448', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('11', '38', '472', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('12', '11', '488', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('13', '37', '225', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('14', '30', '355', '3', '20', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('15', '72', '370', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('16', '64', '425', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('17', '37', '450', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('18', '10', '487', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('19', '38', '197', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('20', '17', '285', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('21', '10', '409', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('22', '31', '447', '3', '23', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('23', '64', '465', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('24', '70', '475', '3', '23', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('25', '30', '273', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('26', '38', '357', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('27', '72', '370', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('28', '75', '448', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('29', '37', '467', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('30', '31', '491', '3', '26', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('31', '38', '197', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('32', '77', '344', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('33', '31', '411', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('34', '30', '445', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('35', '37', '467', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('36', '10', '487', '3', '29', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('37', '17', '206', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('38', '10', '350', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('39', '77', '408', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('40', '11', '424', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('41', '37', '450', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('42', '75', '490', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('43', '37', '225', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('44', '30', '355', '3', '35', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('45', '72', '370', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('46', '30', '446', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('47', '64', '465', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('48', '77', '492', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('49', '11', '224', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('50', '10', '350', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('51', '17', '369', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('52', '31', '447', '3', '38', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('53', '74', '471', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('54', '70', '475', '3', '38', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('55', '30', '273', '3', '41', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('56', '72', '288', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('57', '10', '409', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('58', '75', '448', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('59', '70', '451', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('60', '31', '491', null, '41', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('61', '70', '280', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('62', '77', '344', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('63', '37', '372', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('64', '75', '448', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('65', '31', '470', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('66', '10', '487', '3', '44', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('67', '30', '273', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('68', '72', '288', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('69', '31', '411', '3', '47', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('70', '77', '444', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('71', '11', '466', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('72', '75', '490', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('73', '11', '224', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('74', '72', '288', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('75', '37', '372', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('76', '75', '448', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('77', '70', '451', '3', '50', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('78', '77', '492', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('79', '70', '280', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('80', '11', '316', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('81', '17', '369', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('82', '10', '445', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('83', '75', '468', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('84', '31', '491', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('85', '30', '240', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('86', '72', '288', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('87', '37', '372', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('88', '38', '413', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('89', '70', '451', '3', '56', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('90', '75', '489', '3', '56', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('91', '72', '182', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('92', '30', '355', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('93', '10', '409', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('94', '38', '413', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('95', '31', '470', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('96', '75', '489', '3', '59', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('97', '37', '225', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('98', '72', '288', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('99', '30', '410', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('100', '77', '444', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('101', '11', '466', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('102', '70', '475', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('103', '38', '283', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('104', '11', '316', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('105', '31', '411', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('106', '72', '421', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('107', '74', '471', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('108', '10', '474', '3', '65', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('109', '30', '240', '3', '68', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('110', '37', '318', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('111', '38', '412', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('112', '64', '425', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('113', '38', '472', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('114', '75', '490', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('115', '72', '182', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('116', '37', '318', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('117', '11', '371', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('118', '30', '446', '3', '71', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('119', '75', '468', '3', '71', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('120', '2', '505', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('121', '10', '272', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('122', '8', '356', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('123', '38', '412', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('124', '31', '447', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('125', '75', '469', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('126', '77', '492', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('127', '37', '225', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('128', '17', '285', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('129', '30', '410', '3', '77', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('130', '10', '445', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('131', '31', '470', '3', '77', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('132', '77', '492', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('133', '70', '280', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('134', '77', '344', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('135', '31', '411', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('136', '72', '421', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('137', '74', '471', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('138', '10', '474', '3', '80', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('139', '30', '240', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('140', '8', '356', '3', '83', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('141', '77', '408', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('142', '64', '425', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('143', '75', '469', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('144', '11', '488', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('145', '72', '182', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('146', '37', '318', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('147', '11', '371', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('148', '10', '445', '3', '86', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('149', '75', '468', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('150', '2', '505', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('151', '10', '272', '3', '89', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('152', '8', '356', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('153', '30', '410', '3', '89', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('154', '31', '447', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('155', '75', '469', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('156', '77', '492', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('157', '60', '41', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('158', '79', '82', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('159', '42', '157', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('160', '42', '20', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('161', '15', '73', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('162', '75', '179', '3', '21', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('163', '15', '583', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('164', '77', '81', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('165', '75', '179', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('166', '10', '42', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('167', '77', '81', '3', '27', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('168', '75', '179', '3', '27', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('169', '22', '56', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('170', '15', '73', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('171', '75', '179', '3', '30', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('173', '74', '19', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('174', '79', '82', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('175', '76', '154', '3', '33', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('176', '74', '19', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('177', '15', '73', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('178', '75', '179', '3', '36', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('179', '74', '19', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('180', '15', '73', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('181', '42', '157', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('182', '22', '56', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('183', '77', '81', '3', '42', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('184', '42', '157', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('185', '76', '41', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('186', '15', '73', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('187', '42', '157', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('188', '74', '19', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('189', '15', '73', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('190', '76', '154', '3', '48', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('191', '22', '56', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('192', '27', '83', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('193', '62', '181', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('194', '42', '20', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('195', '79', '82', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('196', '15', '153', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('197', '22', '56', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('198', '10', '74', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('199', '15', '153', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('200', '42', '20', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('201', '79', '82', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('202', '15', '153', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('203', '10', '42', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('204', '27', '83', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('205', '15', '153', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('206', '22', '56', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('207', '76', '74', '3', '66', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('208', '15', '153', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('209', '15', '583', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('210', '70', '75', '3', '69', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('211', '42', '157', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('212', '79', '42', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('213', '10', '74', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('214', '62', '181', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('215', '15', '583', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('216', '79', '82', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('217', '70', '180', '3', '75', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('218', '42', '20', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('219', '27', '83', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('220', '15', '153', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('221', '15', '583', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('222', '76', '74', '3', '81', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('223', '62', '181', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('224', '79', '42', '3', '84', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('225', '70', '75', '3', '84', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('226', '76', '154', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('227', '74', '19', '3', '87', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('228', '79', '82', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('229', '76', '154', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('230', '15', '583', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('231', '27', '83', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('232', '70', '180', '3', '90', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('233', '66', '500', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('234', '70', '526', '3', '19', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('235', '24', '549', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('236', '63', '512', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('237', '64', '520', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('238', '70', '541', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('239', '24', '214', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('240', '75', '529', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('241', '64', '538', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('242', '66', '500', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('243', '63', '523', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('244', '26', '550', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('245', '66', '500', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('246', '24', '522', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('247', '2', '542', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('248', '63', '512', '3', '34', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('249', '70', '526', '3', '34', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('250', '24', '549', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('251', '63', '512', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('252', '70', '541', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('253', '61', '521', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('254', '63', '507', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('255', '75', '529', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('256', '64', '538', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('257', '78', '515', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('258', '61', '521', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('259', '76', '537', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('260', '66', '500', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('261', '61', '531', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('262', '24', '549', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('263', '63', '512', '3', '49', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('264', '6', '530', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('265', '64', '538', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('266', '10', '511', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('267', '61', '531', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('268', '26', '550', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('269', '78', '515', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('270', '77', '527', '3', '55', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('271', '2', '542', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('272', '63', '512', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('273', '66', '518', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('274', '76', '537', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('275', '61', '502', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('276', '66', '518', '3', '61', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('277', '64', '538', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('278', '78', '515', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('279', '6', '530', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('280', '66', '547', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('281', '63', '507', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('282', '64', '520', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('283', '76', '537', '3', '67', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('284', '10', '511', '3', '70', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('285', '77', '527', '3', '70', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('286', '66', '547', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('287', '66', '506', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('288', '24', '522', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('289', '64', '538', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('290', '61', '502', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('291', '6', '530', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('292', '66', '547', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('293', '78', '515', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('294', '2', '531', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('295', '74', '545', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('296', '66', '500', '3', '82', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('297', '6', '530', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('298', '66', '547', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('299', '10', '511', '3', '85', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('300', '61', '521', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('301', '2', '542', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('302', '66', '506', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('303', '24', '522', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('304', '70', '541', '3', '88', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('305', '61', '506', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('306', '6', '530', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('307', '26', '550', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('308', '70', '464', '3', '93', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('309', '70', '358', '3', '94', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('310', '70', '223', '3', '96', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('311', '10', '183', '3', '97', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('312', '10', '277', '3', '98', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('313', '10', '543', '3', '99', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('314', '10', '437', '3', '100', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('315', '75', '469', '3', '101', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('316', '75', '489', '3', '102', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('317', '75', '489', '3', '103', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('318', '77', '505', '3', '104', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('319', '77', '499', '3', '105', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('320', '77', '544', '3', '106', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('321', '77', '544', '3', '107', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('322', '77', '510', '3', '108', null);";
    
       return sql;
   }
   public String ingresaHorariosinRecesoTemp(){
       String sql="";
       
       sql="INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('17', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('18', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('19', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('20', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('21', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('22', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('23', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('24', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('25', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('26', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('27', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('28', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('29', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('30', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('31', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('32', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('33', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('34', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('35', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('36', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('37', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('38', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('39', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('40', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('41', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('42', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('43', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('44', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('45', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('46', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('47', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('48', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('49', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('50', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('51', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('52', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('53', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('54', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('55', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('56', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('57', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('58', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('59', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('60', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('61', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('62', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('63', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('64', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('65', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('66', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('67', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('68', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('69', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('70', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('71', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('72', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('73', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('74', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('75', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('76', '4', '4', '8', '2', '10:00:00', '11:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('77', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('78', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('79', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('80', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('81', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('82', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('83', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('84', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('85', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('86', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('87', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('88', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('89', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('90', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('91', '4', '4', '9', '2', '11:00:00', '12:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('93', '4', '5', '10', '2', '12:00:00', '13:00:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('94', '4', '5', '11', '2', '13:00:00', '14:00:00', 't', '9', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('96', '4', '5', '13', '2', '16:00:00', '17:00:00', 't', '10', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('97', '4', '5', '12', '2', '14:00:00', '15:00:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('98', '4', '5', '13', '2', '16:00:00', '17:00:00', 't', '7', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('99', '4', '6', '15', '2', '18:00:00', '19:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('100', '4', '6', '16', '2', '19:00:00', '20:00:00', 't', '10', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('101', '4', '5', '10', '2', '12:00:00', '13:00:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('102', '4', '5', '11', '2', '13:00:00', '14:00:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('103', '4', '5', '12', '2', '14:00:00', '15:00:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('104', '4', '4', '13', '2', '16:00:00', '17:00:00', 't', '8', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('105', '4', '4', '12', '2', '14:00:00', '15:00:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('106', '4', '6', '15', '2', '18:00:00', '19:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('107', '4', '6', '16', '2', '19:00:00', '20:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('108', '4', '6', '17', '2', '20:00:00', '21:00:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('7', '17', '206', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('8', '10', '350', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('9', '77', '408', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('10', '75', '448', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('11', '38', '472', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('12', '11', '488', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('13', '37', '225', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('14', '30', '355', '3', '20', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('15', '72', '370', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('16', '64', '425', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('17', '37', '450', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('18', '10', '487', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('19', '38', '197', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('20', '17', '285', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('21', '10', '409', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('22', '31', '447', '3', '23', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('23', '64', '465', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('24', '70', '475', '3', '23', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('25', '30', '273', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('26', '38', '357', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('27', '72', '370', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('28', '75', '448', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('29', '37', '467', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('30', '31', '491', '3', '26', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('31', '38', '197', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('32', '77', '344', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('33', '31', '411', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('34', '30', '445', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('35', '37', '467', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('36', '10', '487', '3', '29', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('37', '17', '206', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('38', '10', '350', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('39', '77', '408', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('40', '11', '424', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('41', '37', '450', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('42', '75', '490', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('43', '37', '225', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('44', '30', '355', '3', '35', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('45', '72', '370', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('46', '30', '446', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('47', '64', '465', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('48', '77', '492', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('49', '11', '224', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('50', '10', '350', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('51', '17', '369', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('52', '31', '447', '3', '38', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('53', '74', '471', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('54', '70', '475', '3', '38', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('55', '30', '273', '3', '41', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('56', '72', '288', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('57', '10', '409', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('58', '75', '448', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('59', '70', '451', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('60', '31', '491', null, '41', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('61', '70', '280', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('62', '77', '344', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('63', '37', '372', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('64', '75', '448', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('65', '31', '470', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('66', '10', '487', '3', '44', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('67', '30', '273', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('68', '72', '288', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('69', '31', '411', '3', '47', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('70', '77', '444', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('71', '11', '466', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('72', '75', '490', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('73', '11', '224', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('74', '72', '288', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('75', '37', '372', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('76', '75', '448', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('77', '70', '451', '3', '50', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('78', '77', '492', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('79', '70', '280', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('80', '11', '316', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('81', '17', '369', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('82', '10', '445', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('83', '75', '468', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('84', '31', '491', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('85', '30', '240', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('86', '72', '288', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('87', '37', '372', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('88', '38', '413', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('89', '70', '451', '3', '56', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('90', '75', '489', '3', '56', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('91', '72', '182', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('92', '30', '355', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('93', '10', '409', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('94', '38', '413', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('95', '31', '470', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('96', '75', '489', '3', '59', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('97', '37', '225', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('98', '72', '288', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('99', '30', '410', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('100', '77', '444', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('101', '11', '466', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('102', '70', '475', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('103', '38', '283', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('104', '11', '316', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('105', '31', '411', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('106', '72', '421', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('107', '74', '471', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('108', '10', '474', '3', '65', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('109', '30', '240', '3', '68', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('110', '37', '318', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('111', '38', '412', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('112', '64', '425', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('113', '38', '472', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('114', '75', '490', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('115', '72', '182', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('116', '37', '318', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('117', '11', '371', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('118', '30', '446', '3', '71', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('119', '75', '468', '3', '71', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('120', '2', '505', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('121', '10', '272', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('122', '8', '356', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('123', '38', '412', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('124', '31', '447', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('125', '75', '469', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('126', '77', '492', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('127', '37', '225', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('128', '17', '285', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('129', '30', '410', '3', '77', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('130', '10', '445', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('131', '31', '470', '3', '77', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('132', '77', '492', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('133', '70', '280', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('134', '77', '344', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('135', '31', '411', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('136', '72', '421', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('137', '74', '471', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('138', '10', '474', '3', '80', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('139', '30', '240', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('140', '8', '356', '3', '83', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('141', '77', '408', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('142', '64', '425', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('143', '75', '469', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('144', '11', '488', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('145', '72', '182', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('146', '37', '318', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('147', '11', '371', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('148', '10', '445', '3', '86', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('149', '75', '468', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('150', '2', '505', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('151', '10', '272', '3', '89', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('152', '8', '356', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('153', '30', '410', '3', '89', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('154', '31', '447', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('155', '75', '469', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('156', '77', '492', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('157', '60', '41', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('158', '79', '82', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('159', '42', '157', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('160', '42', '20', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('161', '15', '73', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('162', '75', '179', '3', '21', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('163', '15', '583', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('164', '77', '81', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('165', '75', '179', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('166', '10', '42', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('167', '77', '81', '3', '27', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('168', '75', '179', '3', '27', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('169', '22', '56', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('170', '15', '73', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('171', '75', '179', '3', '30', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('173', '74', '19', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('174', '79', '82', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('175', '76', '154', '3', '33', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('176', '74', '19', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('177', '15', '73', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('178', '75', '179', '3', '36', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('179', '74', '19', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('180', '15', '73', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('181', '42', '157', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('182', '22', '56', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('183', '77', '81', '3', '42', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('184', '42', '157', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('185', '76', '41', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('186', '15', '73', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('187', '42', '157', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('188', '74', '19', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('189', '15', '73', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('190', '76', '154', '3', '48', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('191', '22', '56', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('192', '27', '83', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('193', '62', '181', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('194', '42', '20', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('195', '79', '82', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('196', '15', '153', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('197', '22', '56', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('198', '10', '74', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('199', '15', '153', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('200', '42', '20', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('201', '79', '82', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('202', '15', '153', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('203', '10', '42', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('204', '27', '83', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('205', '15', '153', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('206', '22', '56', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('207', '76', '74', '3', '66', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('208', '15', '153', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('209', '15', '583', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('210', '70', '75', '3', '69', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('211', '42', '157', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('212', '79', '42', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('213', '10', '74', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('214', '62', '181', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('215', '15', '583', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('216', '79', '82', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('217', '70', '180', '3', '75', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('218', '42', '20', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('219', '27', '83', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('220', '15', '153', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('221', '15', '583', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('222', '76', '74', '3', '81', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('223', '62', '181', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('224', '79', '42', '3', '84', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('225', '70', '75', '3', '84', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('226', '76', '154', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('227', '74', '19', '3', '87', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('228', '79', '82', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('229', '76', '154', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('230', '15', '583', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('231', '27', '83', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('232', '70', '180', '3', '90', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('233', '66', '500', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('234', '70', '526', '3', '19', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('235', '24', '549', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('236', '63', '512', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('237', '64', '520', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('238', '70', '541', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('239', '24', '214', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('240', '75', '529', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('241', '64', '538', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('242', '66', '500', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('243', '63', '523', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('244', '26', '550', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('245', '66', '500', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('246', '24', '522', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('247', '2', '542', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('248', '63', '512', '3', '34', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('249', '70', '526', '3', '34', '1');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('250', '24', '549', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('251', '63', '512', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('252', '70', '541', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('253', '61', '521', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('254', '63', '507', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('255', '75', '529', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('256', '64', '538', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('257', '78', '515', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('258', '61', '521', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('259', '76', '537', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('260', '66', '500', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('261', '61', '531', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('262', '24', '549', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('263', '63', '512', '3', '49', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('264', '6', '530', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('265', '64', '538', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('266', '10', '511', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('267', '61', '531', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('268', '26', '550', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('269', '78', '515', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('270', '77', '527', '3', '55', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('271', '2', '542', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('272', '63', '512', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('273', '66', '518', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('274', '76', '537', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('275', '61', '502', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('276', '66', '518', '3', '61', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('277', '64', '538', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('278', '78', '515', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('279', '6', '530', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('280', '66', '547', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('281', '63', '507', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('282', '64', '520', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('283', '76', '537', '3', '67', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('284', '10', '511', '3', '70', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('285', '77', '527', '3', '70', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('286', '66', '547', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('287', '66', '506', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('288', '24', '522', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('289', '64', '538', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('290', '61', '502', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('291', '6', '530', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('292', '66', '547', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('293', '78', '515', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('294', '2', '531', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('295', '74', '545', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('296', '66', '500', '3', '82', '4');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('297', '6', '530', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('298', '66', '547', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('299', '10', '511', '3', '85', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('300', '61', '521', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('301', '2', '542', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('302', '66', '506', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('303', '24', '522', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('304', '70', '541', '3', '88', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('305', '61', '506', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('306', '6', '530', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('307', '26', '550', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('308', '70', '464', '3', '93', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('309', '70', '358', '3', '94', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('310', '70', '223', '3', '96', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('311', '10', '183', '3', '97', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('312', '10', '277', '3', '98', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('313', '10', '543', '3', '99', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('314', '10', '437', '3', '100', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('315', '75', '469', '3', '101', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('316', '75', '489', '3', '102', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('317', '75', '489', '3', '103', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('318', '77', '505', '3', '104', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('319', '77', '499', '3', '105', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('320', '77', '544', '3', '106', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('321', '77', '544', '3', '107', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('322', '77', '510', '3', '108', null);";
       return sql;
   }
   
   public String ingresaHorariosconReceso(){
       String sql="";
       sql = "INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('2', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('3', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('4', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('5', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('6', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('7', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('8', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('9', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('10', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('11', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('12', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('13', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('14', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('15', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('16', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('17', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('18', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('19', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('20', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('21', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('22', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('23', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('24', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('25', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('26', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('27', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('28', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('29', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('30', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('31', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('32', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('33', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('34', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('35', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('36', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('37', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('38', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('39', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('40', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('41', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('42', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('43', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('44', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('45', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('46', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('47', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('48', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('49', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('50', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('51', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('52', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('53', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('54', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('55', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('56', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('57', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('58', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('59', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('60', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('61', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('62', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('63', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('64', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('65', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('66', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('67', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('68', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('69', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('70', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('71', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('72', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('73', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('74', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('75', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('76', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('77', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('78', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('79', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('80', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('81', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('82', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('83', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('84', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('85', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('86', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('87', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('88', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('89', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('90', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('91', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('93', '4', '5', '10', '2', '12:30:00', '13:30:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('94', '4', '5', '11', '2', '13:30:00', '14:30:00', 't', '9', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('96', '4', '5', '13', '2', '16:00:00', '17:00:00', 't', '10', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('97', '4', '5', '12', '2', '14:30:00', '15:30:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('98', '4', '5', '13', '2', '16:00:00', '17:00:00', 't', '7', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('99', '4', '6', '15', '2', '18:00:00', '19:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('100', '4', '6', '16', '2', '19:00:00', '20:00:00', 't', '10', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('101', '4', '5', '10', '2', '12:30:00', '13:30:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('102', '4', '5', '11', '2', '13:30:00', '14:30:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('103', '4', '5', '12', '2', '14:30:00', '15:30:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('104', '4', '4', '13', '2', '16:00:00', '17:00:00', 't', '8', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('105', '4', '4', '12', '2', '14:30:00', '15:30:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('106', '4', '6', '15', '2', '18:00:00', '19:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('107', '4', '6', '16', '2', '19:00:00', '20:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor\" VALUES ('108', '4', '6', '17', '2', '20:00:00', '21:00:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('7', '17', '206', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('8', '10', '350', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('9', '77', '408', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('10', '75', '448', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('11', '38', '472', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('12', '11', '488', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('13', '37', '225', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('14', '30', '355', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('15', '72', '370', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('16', '64', '425', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('17', '37', '450', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('18', '10', '487', '3', '20', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('19', '38', '197', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('20', '17', '285', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('21', '10', '409', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('22', '31', '447', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('23', '64', '465', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('24', '70', '475', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('25', '30', '273', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('26', '38', '357', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('27', '72', '370', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('28', '75', '448', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('29', '37', '467', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('30', '31', '491', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('31', '38', '197', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('32', '77', '344', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('33', '31', '411', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('34', '30', '445', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('35', '37', '467', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('36', '10', '487', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('37', '17', '206', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('38', '10', '350', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('39', '77', '408', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('40', '11', '424', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('41', '37', '450', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('42', '75', '490', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('43', '37', '225', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('44', '30', '355', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('45', '72', '370', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('46', '30', '446', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('47', '64', '465', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('48', '77', '492', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('49', '11', '224', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('50', '10', '350', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('51', '17', '369', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('52', '31', '447', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('53', '74', '471', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('54', '70', '475', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('55', '30', '273', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('56', '72', '288', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('57', '10', '409', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('58', '75', '448', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('59', '70', '451', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('60', '31', '491', null, '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('61', '70', '280', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('62', '77', '344', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('63', '37', '372', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('64', '75', '448', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('65', '31', '470', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('66', '10', '487', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('67', '30', '273', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('68', '72', '288', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('69', '31', '411', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('70', '77', '444', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('71', '11', '466', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('72', '75', '490', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('73', '11', '224', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('74', '72', '288', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('75', '37', '372', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('76', '75', '448', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('77', '70', '451', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('78', '77', '492', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('79', '70', '280', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('80', '11', '316', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('81', '17', '369', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('82', '10', '445', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('83', '75', '468', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('84', '31', '491', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('85', '77', '240', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('86', '72', '288', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('87', '37', '372', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('88', '38', '413', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('89', '70', '451', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('90', '75', '489', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('91', '72', '182', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('92', '30', '355', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('93', '10', '409', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('94', '38', '413', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('95', '31', '470', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('96', '75', '489', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('97', '37', '225', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('98', '72', '288', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('99', '30', '410', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('100', '77', '444', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('101', '11', '466', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('102', '70', '475', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('103', '38', '283', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('104', '11', '316', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('105', '31', '411', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('106', '72', '421', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('107', '74', '471', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('108', '10', '474', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('109', '72', '182', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('110', '37', '318', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('111', '38', '412', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('112', '64', '425', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('113', '38', '472', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('114', '75', '490', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('115', '77', '240', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('116', '37', '318', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('117', '11', '371', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('118', '30', '446', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('119', '75', '468', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('120', '2', '505', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('121', '10', '272', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('122', '8', '356', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('123', '38', '412', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('124', '31', '447', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('125', '75', '469', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('126', '77', '492', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('127', '37', '225', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('128', '17', '285', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('129', '30', '410', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('130', '10', '445', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('131', '31', '470', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('132', '77', '492', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('133', '70', '280', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('134', '77', '344', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('135', '31', '411', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('136', '72', '421', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('137', '74', '471', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('138', '10', '474', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('139', '72', '182', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('140', '8', '356', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('141', '77', '408', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('142', '64', '425', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('143', '75', '469', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('144', '11', '488', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('145', '77', '240', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('146', '37', '318', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('147', '11', '371', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('148', '10', '445', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('149', '75', '468', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('150', '2', '505', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('151', '10', '272', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('152', '8', '356', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('153', '30', '410', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('154', '31', '447', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('155', '75', '469', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('156', '77', '492', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('157', '60', '41', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('158', '79', '82', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('159', '42', '157', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('160', '42', '20', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('161', '15', '73', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('162', '75', '179', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('163', '15', '583', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('164', '77', '81', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('165', '75', '179', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('166', '10', '42', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('167', '77', '81', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('168', '75', '179', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('169', '22', '56', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('170', '15', '73', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('171', '75', '179', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('173', '74', '19', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('174', '79', '82', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('175', '76', '154', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('176', '74', '19', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('177', '15', '73', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('178', '75', '179', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('179', '74', '19', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('180', '15', '73', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('181', '42', '157', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('182', '22', '56', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('183', '77', '81', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('184', '42', '157', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('185', '76', '41', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('186', '15', '73', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('187', '42', '157', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('188', '74', '19', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('189', '15', '73', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('190', '76', '154', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('191', '22', '56', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('192', '27', '83', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('193', '62', '181', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('194', '42', '20', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('195', '79', '82', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('196', '15', '153', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('197', '22', '56', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('198', '10', '74', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('199', '15', '153', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('200', '42', '20', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('201', '79', '82', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('202', '15', '153', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('203', '10', '42', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('204', '27', '83', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('205', '15', '153', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('206', '22', '56', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('207', '76', '74', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('208', '15', '153', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('209', '15', '583', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('210', '70', '75', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('211', '42', '157', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('212', '79', '42', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('213', '10', '74', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('214', '62', '181', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('215', '15', '583', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('216', '79', '82', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('217', '70', '180', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('218', '42', '20', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('219', '27', '83', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('220', '15', '153', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('221', '15', '583', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('222', '76', '74', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('223', '62', '181', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('224', '79', '42', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('225', '70', '75', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('226', '76', '154', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('227', '74', '19', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('228', '79', '82', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('229', '76', '154', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('230', '15', '583', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('231', '27', '83', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('232', '70', '180', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('233', '66', '500', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('234', '70', '526', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('235', '24', '549', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('236', '63', '512', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('237', '64', '520', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('238', '70', '541', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('239', '24', '214', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('240', '75', '529', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('241', '64', '538', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('242', '66', '500', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('243', '63', '523', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('244', '26', '550', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('245', '66', '500', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('246', '24', '522', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('247', '2', '542', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('248', '63', '512', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('249', '70', '526', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('250', '24', '549', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('251', '63', '512', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('252', '70', '541', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('253', '61', '521', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('254', '63', '507', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('255', '75', '529', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('256', '64', '538', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('257', '78', '515', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('258', '61', '521', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('259', '76', '537', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('260', '66', '500', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('261', '61', '531', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('262', '24', '549', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('263', '63', '512', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('264', '6', '530', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('265', '64', '538', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('266', '10', '511', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('267', '61', '531', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('268', '26', '550', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('269', '78', '515', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('270', '77', '527', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('271', '2', '542', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('272', '63', '512', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('273', '66', '518', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('274', '76', '537', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('275', '61', '502', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('276', '66', '518', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('277', '64', '538', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('278', '78', '515', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('279', '6', '530', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('280', '66', '547', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('281', '63', '507', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('282', '64', '520', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('283', '76', '537', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('284', '10', '511', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('285', '77', '527', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('286', '66', '547', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('287', '66', '506', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('288', '24', '522', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('289', '64', '538', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('290', '61', '502', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('291', '6', '530', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('292', '66', '547', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('293', '78', '515', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('294', '2', '531', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('295', '74', '545', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('296', '66', '500', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('297', '6', '530', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('298', '66', '547', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('299', '10', '511', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('300', '61', '521', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('301', '2', '542', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('302', '66', '506', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('303', '24', '522', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('304', '70', '541', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('305', '61', '506', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('306', '6', '530', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('307', '26', '550', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('308', '70', '464', '3', '93', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('309', '70', '358', '3', '94', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('310', '70', '223', '3', '96', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('311', '10', '183', '3', '97', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('312', '10', '277', '3', '98', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('313', '10', '543', '3', '99', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('314', '10', '437', '3', '100', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('315', '75', '469', '3', '101', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('316', '75', '489', '3', '102', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('317', '75', '489', '3', '103', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('318', '77', '505', '3', '104', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('319', '77', '499', '3', '105', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('320', '77', '544', '3', '106', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('321', '77', '544', '3', '107', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate\" VALUES ('322', '77', '510', '3', '108', null);";
    return sql;   
   }
   
   public String ingresaHorariosconRecesoTemp(){
       String sql="";
       sql = "INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('2', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('3', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('4', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '9', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('5', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('6', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('7', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '6', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('8', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('9', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('10', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '7', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('11', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('12', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('13', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '8', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('14', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('15', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('16', '4', '4', '19', '2', '10:00:00', '10:30:00', 't', '10', '5', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('17', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('18', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('19', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('20', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('21', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('22', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('23', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('24', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('25', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('26', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('27', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('28', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('29', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('30', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('31', '4', '4', '5', '2', '07:00:00', '08:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('32', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('33', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('34', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('35', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('36', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('37', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('38', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('39', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('40', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('41', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('42', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('43', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('44', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('45', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('46', '4', '4', '6', '2', '08:00:00', '09:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('47', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('48', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('49', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('50', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('51', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('52', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('53', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('54', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('55', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('56', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('57', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('58', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('59', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('60', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('61', '4', '4', '7', '2', '09:00:00', '10:00:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('62', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('63', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('64', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('65', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('66', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('67', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('68', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('69', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('70', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('71', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('72', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('73', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('74', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('75', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('76', '4', '4', '8', '2', '10:30:00', '11:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('77', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('78', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('79', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '9', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('80', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('81', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('82', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '6', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('83', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('84', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('85', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '7', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('86', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('87', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('88', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '8', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('89', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '10');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('90', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('91', '4', '4', '9', '2', '11:30:00', '12:30:00', 't', '10', '4', '11');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('93', '4', '5', '10', '2', '12:30:00', '13:30:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('94', '4', '5', '11', '2', '13:30:00', '14:30:00', 't', '9', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('96', '4', '5', '13', '2', '16:00:00', '17:00:00', 't', '10', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('97', '4', '5', '12', '2', '14:30:00', '15:30:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('98', '4', '5', '13', '2', '16:00:00', '17:00:00', 't', '7', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('99', '4', '6', '15', '2', '18:00:00', '19:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('100', '4', '6', '16', '2', '19:00:00', '20:00:00', 't', '10', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('101', '4', '5', '10', '2', '12:30:00', '13:30:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('102', '4', '5', '11', '2', '13:30:00', '14:30:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('103', '4', '5', '12', '2', '14:30:00', '15:30:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('104', '4', '4', '13', '2', '16:00:00', '17:00:00', 't', '8', '4', '5');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('105', '4', '4', '12', '2', '14:30:00', '15:30:00', 't', '7', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('106', '4', '6', '15', '2', '18:00:00', '19:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('107', '4', '6', '16', '2', '19:00:00', '20:00:00', 't', '10', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_periodo_hor_temp\" VALUES ('108', '4', '6', '17', '2', '20:00:00', '21:00:00', 't', '6', '4', '9');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('7', '17', '206', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('8', '10', '350', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('9', '77', '408', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('10', '75', '448', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('11', '38', '472', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('12', '11', '488', '3', '17', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('13', '37', '225', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('14', '30', '355', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('15', '72', '370', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('16', '64', '425', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('17', '37', '450', '3', '20', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('18', '10', '487', '3', '20', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('19', '38', '197', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('20', '17', '285', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('21', '10', '409', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('22', '31', '447', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('23', '64', '465', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('24', '70', '475', '3', '23', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('25', '30', '273', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('26', '38', '357', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('27', '72', '370', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('28', '75', '448', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('29', '37', '467', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('30', '31', '491', '3', '26', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('31', '38', '197', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('32', '77', '344', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('33', '31', '411', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('34', '30', '445', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('35', '37', '467', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('36', '10', '487', '3', '29', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('37', '17', '206', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('38', '10', '350', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('39', '77', '408', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('40', '11', '424', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('41', '37', '450', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('42', '75', '490', '3', '32', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('43', '37', '225', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('44', '30', '355', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('45', '72', '370', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('46', '30', '446', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('47', '64', '465', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('48', '77', '492', '3', '35', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('49', '11', '224', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('50', '10', '350', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('51', '17', '369', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('52', '31', '447', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('53', '74', '471', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('54', '70', '475', '3', '38', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('55', '30', '273', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('56', '72', '288', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('57', '10', '409', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('58', '75', '448', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('59', '70', '451', '3', '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('60', '31', '491', null, '41', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('61', '70', '280', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('62', '77', '344', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('63', '37', '372', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('64', '75', '448', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('65', '31', '470', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('66', '10', '487', '3', '44', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('67', '30', '273', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('68', '72', '288', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('69', '31', '411', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('70', '77', '444', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('71', '11', '466', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('72', '75', '490', '3', '47', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('73', '11', '224', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('74', '72', '288', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('75', '37', '372', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('76', '75', '448', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('77', '70', '451', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('78', '77', '492', '3', '50', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('79', '70', '280', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('80', '11', '316', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('81', '17', '369', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('82', '10', '445', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('83', '75', '468', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('84', '31', '491', '3', '53', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('85', '77', '240', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('86', '72', '288', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('87', '37', '372', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('88', '38', '413', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('89', '70', '451', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('90', '75', '489', '3', '56', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('91', '72', '182', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('92', '30', '355', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('93', '10', '409', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('94', '38', '413', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('95', '31', '470', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('96', '75', '489', '3', '59', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('97', '37', '225', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('98', '72', '288', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('99', '30', '410', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('100', '77', '444', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('101', '11', '466', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('102', '70', '475', '3', '62', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('103', '38', '283', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('104', '11', '316', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('105', '31', '411', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('106', '72', '421', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('107', '74', '471', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('108', '10', '474', '3', '65', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('109', '72', '182', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('110', '37', '318', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('111', '38', '412', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('112', '64', '425', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('113', '38', '472', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('114', '75', '490', '3', '68', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('115', '77', '240', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('116', '37', '318', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('117', '11', '371', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('118', '30', '446', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('119', '75', '468', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('120', '2', '505', '3', '71', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('121', '10', '272', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('122', '8', '356', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('123', '38', '412', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('124', '31', '447', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('125', '75', '469', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('126', '77', '492', '3', '74', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('127', '37', '225', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('128', '17', '285', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('129', '30', '410', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('130', '10', '445', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('131', '31', '470', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('132', '77', '492', '3', '77', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('133', '70', '280', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('134', '77', '344', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('135', '31', '411', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('136', '72', '421', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('137', '74', '471', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('138', '10', '474', '3', '80', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('139', '72', '182', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('140', '8', '356', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('141', '77', '408', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('142', '64', '425', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('143', '75', '469', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('144', '11', '488', '3', '83', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('145', '77', '240', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('146', '37', '318', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('147', '11', '371', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('148', '10', '445', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('149', '75', '468', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('150', '2', '505', '3', '86', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('151', '10', '272', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('152', '8', '356', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('153', '30', '410', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('154', '31', '447', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('155', '75', '469', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('156', '77', '492', '3', '89', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('157', '60', '41', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('158', '79', '82', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('159', '42', '157', '3', '18', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('160', '42', '20', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('161', '15', '73', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('162', '75', '179', '3', '21', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('163', '15', '583', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('164', '77', '81', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('165', '75', '179', '3', '24', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('166', '10', '42', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('167', '77', '81', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('168', '75', '179', '3', '27', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('169', '22', '56', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('170', '15', '73', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('171', '75', '179', '3', '30', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('173', '74', '19', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('174', '79', '82', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('175', '76', '154', '3', '33', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('176', '74', '19', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('177', '15', '73', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('178', '75', '179', '3', '36', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('179', '74', '19', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('180', '15', '73', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('181', '42', '157', '3', '39', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('182', '22', '56', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('183', '77', '81', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('184', '42', '157', '3', '42', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('185', '76', '41', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('186', '15', '73', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('187', '42', '157', '3', '45', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('188', '74', '19', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('189', '15', '73', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('190', '76', '154', '3', '48', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('191', '22', '56', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('192', '27', '83', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('193', '62', '181', '3', '51', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('194', '42', '20', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('195', '79', '82', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('196', '15', '153', '3', '54', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('197', '22', '56', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('198', '10', '74', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('199', '15', '153', '3', '57', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('200', '42', '20', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('201', '79', '82', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('202', '15', '153', '3', '60', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('203', '10', '42', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('204', '27', '83', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('205', '15', '153', '3', '63', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('206', '22', '56', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('207', '76', '74', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('208', '15', '153', '3', '66', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('209', '15', '583', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('210', '70', '75', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('211', '42', '157', '3', '69', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('212', '79', '42', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('213', '10', '74', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('214', '62', '181', '3', '72', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('215', '15', '583', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('216', '79', '82', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('217', '70', '180', '3', '75', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('218', '42', '20', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('219', '27', '83', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('220', '15', '153', '3', '78', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('221', '15', '583', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('222', '76', '74', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('223', '62', '181', '3', '81', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('224', '79', '42', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('225', '70', '75', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('226', '76', '154', '3', '84', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('227', '74', '19', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('228', '79', '82', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('229', '76', '154', '3', '87', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('230', '15', '583', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('231', '27', '83', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('232', '70', '180', '3', '90', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('233', '66', '500', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('234', '70', '526', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('235', '24', '549', '3', '19', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('236', '63', '512', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('237', '64', '520', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('238', '70', '541', '3', '22', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('239', '24', '214', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('240', '75', '529', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('241', '64', '538', '3', '25', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('242', '66', '500', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('243', '63', '523', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('244', '26', '550', '3', '28', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('245', '66', '500', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('246', '24', '522', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('247', '2', '542', '3', '31', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('248', '63', '512', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('249', '70', '526', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('250', '24', '549', '3', '34', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('251', '63', '512', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('252', '70', '541', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('253', '61', '521', '3', '37', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('254', '63', '507', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('255', '75', '529', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('256', '64', '538', '3', '40', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('257', '78', '515', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('258', '61', '521', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('259', '76', '537', '3', '43', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('260', '66', '500', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('261', '61', '531', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('262', '24', '549', '3', '46', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('263', '63', '512', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('264', '6', '530', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('265', '64', '538', '3', '49', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('266', '10', '511', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('267', '61', '531', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('268', '26', '550', '3', '52', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('269', '78', '515', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('270', '77', '527', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('271', '2', '542', '3', '55', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('272', '63', '512', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('273', '66', '518', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('274', '76', '537', '3', '58', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('275', '61', '502', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('276', '66', '518', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('277', '64', '538', '3', '61', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('278', '78', '515', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('279', '6', '530', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('280', '66', '547', '3', '64', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('281', '63', '507', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('282', '64', '520', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('283', '76', '537', '3', '67', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('284', '10', '511', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('285', '77', '527', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('286', '66', '547', '3', '70', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('287', '66', '506', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('288', '24', '522', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('289', '64', '538', '3', '73', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('290', '61', '502', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('291', '6', '530', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('292', '66', '547', '3', '76', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('293', '78', '515', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('294', '2', '531', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('295', '74', '545', '3', '79', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('296', '66', '500', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('297', '6', '530', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('298', '66', '547', '3', '82', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('299', '10', '511', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('300', '61', '521', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('301', '2', '542', '3', '85', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('302', '66', '506', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('303', '24', '522', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('304', '70', '541', '3', '88', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('305', '61', '506', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('306', '6', '530', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('307', '26', '550', '3', '91', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('308', '70', '464', '3', '93', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('309', '70', '358', '3', '94', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('310', '70', '223', '3', '96', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('311', '10', '183', '3', '97', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('312', '10', '277', '3', '98', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('313', '10', '543', '3', '99', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('314', '10', '437', '3', '100', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('315', '75', '469', '3', '101', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('316', '75', '489', '3', '102', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('317', '75', '489', '3', '103', '3');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('318', '77', '505', '3', '104', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('319', '77', '499', '3', '105', null);\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('320', '77', '544', '3', '106', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('321', '77', '544', '3', '107', '2');\n" +
"INSERT INTO \"public\".\"yavirac_hora_horario_mate_temp\" VALUES ('322', '77', '510', '3', '108', null);";
    return sql;   
   }
   
}
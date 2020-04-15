/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura.ejb;

import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Andres
 */
@Stateless
public class ServicioEstructuraOrganizacional {

    /**
     * Retorna el periodo academico vigente
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del periodo academico
     */
    public String getPeriodoAcademico(String activo) {
        String sql = "";
        sql = " select ide_ystpea, descripcion_ystpea, fecha_inicio_ystpea, fecha_final_ystpea,descripcion_ystani from yavirac_stror_periodo_academic a,yavirac_stror_anio b where a.ide_ystani = b.ide_ystani and activo_ystpea in (" + activo + ") order by descripcion_ystani desc, fecha_inicio_ystpea desc";
        return sql;
    }

    /**
     * Retorna el periodo academico general
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del periodo academico
     */
    public String getPeriodoAcademicoGeneral(String codigo, String activo, String tipo) {
        String sql = "";
        sql += " select * from yavirac_stror_periodo_academic where ide_ystpea in (" + codigo + ") and activo_ystpea in (" + activo + ") ";
        if (tipo.equals("1")) {
            sql += "and aplica_recuperacion_ystpea in (true)";
        }
        //System.out.println("yyyy "+sql);
        return sql;
    }

    /**
     * Retorna la jornada vigente
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql de la jornada
     */
    public String getJornada(String activo) {
        String sql = "";
        sql = "select ide_ystjor, descripcion_ystjor from yavirac_stror_jornada  where activo_ystjor in (" + activo + ") order by descripcion_ystjor desc";
        return sql;
    }

    /**
     * Retorna la modalidad
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getModalidad(String activo) {
        String sql = "";
        sql = "select ide_ystmod, descripcion_ystmod from yavirac_stror_modalidad  where activo_ystmod in (" + activo + ") order by descripcion_ystmod desc";
        return sql;
    }

    public String getCodigoMaximoTabla(String tabla, String primario) {
        String sql = "";
        sql = "select 1 as codigo, (case when  max(" + primario + ") is null then 1 else max(" + primario + ") end) + 1 as maximo from " + tabla;
        return sql;
    }

    /**
     * Retorna la Nacionalidad
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getNacionalidad(String activo) {
        String sql = "";
        sql = "SELECT ide_ystnac, descripcion_ystnac FROM yavirac_stror_nacionalidad where activo_ystnac in (" + activo + ")";
        return sql;
    }

    /**
     * Retorna el Tipo de Sangre
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getTipoSangre(String activo) {
        String sql = "";
        sql = "SELECT ide_ysttis, descripcion_ysttis FROM yavirac_stror_tipo_sangre where activo_ysttis in (" + activo + ")";
        return sql;
    }

    /**
     * Retorna el Documento de Identidad
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getDocumentoIdentidad(String activo) {
        String sql = "";
        sql = "SELECT ide_ystdoi, descripcion_ystdoi FROM yavirac_stror_docu_identidad where activo_ystdoi in (" + activo + ")";
        return sql;
    }

    /**
     * Retorna la Distribucion Politica
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getDistribucionPolitica(String activo) {
        String sql = "";
        sql = "SELECT ide_ystdip,descripcion_ystdip FROM yavirac_stror_distribucion_pol where activo_ystdip in (" + activo + ")";
        return sql;
    }

    /**
     * Retorna la Tipo Division Politica
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getTipoDivisionPolitica(String activo) {
        String sql = "";
        sql = "SELECT ide_ysttdp ,descripcion_ysttdp FROM yavirac_stror_tipo_divisio_po where activo_ysttdp in (" + activo + ")";
        return sql;
    }

    /**
     * Retorna la division politica por parroquias
     */
    public String getDivisionPolitcaParroquia() {
        String sql = "";
        sql = "select ide_ystdip,pais,provincia,canton,parroquia from (\n"
                + "	select  a.ide_ystdip,codigo_pais,c.descripcion_ystdip ||' ' as pais,codigo_provincia,provincia||' ' as provincia,codigo_canton,canton ||' ' as canton,a.descripcion_ystdip ||' 'as parroquia\n"
                + "	from (select * from yavirac_stror_distribucion_pol  where ide_ysttdp=4) a\n"
                + "	left join (\n"
                + "		select a.ide_ystdip,a.ide_ystdip as codigo_canton,a.descripcion_ystdip as canton,b.ide_ystdip as codigo_provincia,b.descripcion_ystdip as provincia,b.yav_ide_ystdip as codigo_pais  \n"
                + "		from (select * from  yavirac_stror_distribucion_pol  where ide_ysttdp=3) a\n"
                + "		left join (select * from  yavirac_stror_distribucion_pol  where ide_ysttdp=2) b on a.yav_ide_ystdip=b.ide_ystdip\n"
                + "	) b on a.yav_ide_ystdip=b.ide_ystdip \n"
                + "	left join yavirac_stror_distribucion_pol c on b.codigo_pais=c.ide_ystdip\n"
                + "	where not c.ide_ysttdp is null \n"
                + ")a\n"
                + "order by pais, provincia,canton,parroquia";
        return sql;
    }

    /**
     * Retorna el Estado Civil
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getEstadoCivil(String activo) {
        String sql = "";
        sql = "SELECT ide_ystesc, descripcion_ystesc FROM yavirac_stror_estado_civil where activo_ystesc in (" + activo + ")";
        return sql;
    }

    public String getMension() {
        String sql = "";
        sql = "SELECT ide_ystmen, descripcion_ystmen ,detalle_ysttfe FROM yavirac_stror_mension  a, yavirac_stror_tipo_for_educaci b where a.ide_ysttfe = b.ide_ysttfe";
        return sql;
    }

    public String getTipoInstitucion(String activo) {
        String sql = "";
        sql = "SELECT ide_ysttii, descripcion_ysttii FROM yavirac_stror_tipo_institucion where activo_ysttii in (" + activo + ")order by descripcion_ysttii";
        return sql;
    }

    public String getNivelEducacion() {
        String sql = "";
        sql = "select ide_ystnie, descripcion_ystnie from yavirac_stror_nivel_educacion ";
        return sql;
    }

    public String getParentezcoFamiliar(String activo) {
        String sql = "";
        sql = "SELECT ide_ystpaf, descripcion_ystpaf  FROM yavirac_stror_parentezco_fami where activo_ystpaf in (" + activo + ") ";
        return sql;
    }

    public String getTipoFormacionEducativa(String activo) {
        String sql = "";
        sql = "SELECT ide_ysttfe, detalle_ysttfe,abreviatura_ysttfe  FROM yavirac_stror_tipo_for_educaci where activo_ysttif in (" + activo + ")order by detalle_ysttfe";
        return sql;
    }

    public String getGenero(String activo) {
        String sql = "";
        sql = "select ide_ystgen,descripcion_ystgen from yavirac_stror_genero where activo_ystgen in (" + activo + ")";
        return sql;
    }

    public String getDocumentoRequerido(String activo) {
        String sql = "";
        sql = "select ide_ystdor,descripcion_ystdor,abreviatura_ystdor from yavirac_stror_documento_reque where activo_ystdor in (" + activo + ") order by descripcion_ystdor";
        return sql;
    }

    public String getRequeridoPara() {
        String sql = "";
        sql = "select ide_ystrep,descripcion_ystrep,abreviatura_ystrep from yavirac_stror_requerido_para order by descripcion_ystrep";
        return sql;
    }

    public String getDocumentoRequeridoPeriodo(String periodo, String req_para) {
        String sql = "";
        sql = "select ide_ystdop,a.ide_ystdor,descripcion_ystdor,ide_ystrep from yavirac_stror_documento_periodo a,yavirac_stror_documento_reque b\n"
                + " where a.ide_ystdor = b.ide_ystdor"
                + " and a.ide_ystpea =" + periodo
                + " and ide_ystrep = " + req_para
                + " order by a.ide_ystdor ";
        return sql;
    }

    public String deleteBloqueos(String ide_usua) {
        String sql = "";
        sql = "delete from sis_bloqueo where ide_usua =" + ide_usua;
        return sql;
    }

    public String getMaterias() {
        String sql = "";
        sql = "SELECT ide_ystmat, detalle_ystmat,abreviatura_ystmat  FROM yavirac_stror_materia order by detalle_ystmat";
        return sql;
    }

    public String getTipoFormacion() {
        String sql = "";
        sql = "SELECT ide_ysttif, detalle_ysttif,abreviatura_ysttif  FROM yavirac_stror_tipo_formacion order by detalle_ysttif";
        return sql;
    }

    public String getUsuarioSistema(String ide_usua, String condicion) {
        String sql = "";
        sql = "select ide_usua,nom_usua,nick_usua,ide_ypedpe from sis_usuario where ide_usua in(" + ide_usua + ") " + condicion;
        return sql;
    }

    public String getMalla() {
        String sql = "";
        sql = "select a.ide_ystmal, descripcion_ystnie, detalle_ystmat, descripcion_ystmen \n"
                + "from yavirac_stror_malla a, yavirac_stror_nivel_educacion b,yavirac_stror_mension c,yavirac_stror_materia d\n"
                + "where a.ide_ystnie = b.ide_ystnie\n"
                + "and a.ide_ystmen = c.ide_ystmen\n"
                + "and a.ide_ystmat = d.ide_ystmat\n"
                + "order by descripcion_ystmen, descripcion_ystnie";
        return sql;
    }

    public String getSexo() {
        String sql = "";
        sql = "select ide_ystsex,descripcion_ystsex from yavirac_stror_sexo ";
        return sql;

    }

    public String getDiscapacidad() {
        String sql = "";
        sql = "select ide_ysttid,descripcion_ysttid from yavirac_stror_tipo_discapacid";
        return sql;
    }

    public String getEtnia() {

        String sql = "";
        sql = "select ide_ystetn,detalle_ystetn from yavirac_stror_etnia";
        return sql;
    }

    public String getGradoDiscapacidad() {

        String sql = "";
        sql = "select ide_ystgrd,descripcion_ystgrd from yavirac_stror_grado_discapaci";
        return sql;

    }

    public String getTipoColegio() {

        String sql = "";
        sql = "select ide_ysttco,descripcion_ysttco from yavirac_stror_tipo_colegio";
        return sql;
    }

    public String getTipoBachillerato() {

        String sql = "";
        sql = "select ide_ysttba,descripcion_ysttba from yavirac_stror_tipo_bachillerato";
        return sql;
    }

    public String getTipoInstalacion() {

        String sql = "";
        sql = "select ide_ysttin,descripcion_ysttin from yavirac_stror_tipo_instalacion";
        return sql;
    }

    public String getVinculaSociedad() {

        String sql = "";
        sql = "select ide_ystvis,descripcion_ystvis from yavirac_stror_vinculacion_socie";
        return sql;
    }

    public String getInstalacion() {

        String sql = "";
        sql = "select ide_ystins,nombre_ystins from yavirac_stror_instalacion";
        return sql;
    }

    public String getFormacionEducativa() {

        String sql = "";
        sql = "select ide_ystfoe,descripcion_ystfoe from yavirac_stror_formacion_educati";
        return sql;
    }

    public String getCategoriaMigratoria() {

        String sql = "";
        sql = "select ide_ystcam,descripcion_ystcam from yavirac_stror_categoria_migrato";
        return sql;
    }

    public String getListaReportes(String opcion) {

        String sql = "";
        sql = "select ide_repo,nom_repo from sis_reporte where ide_opci=" + opcion;
        return sql;
    }

    public String getAnioPeriodoCarrera() {

        String sql = "";
        sql = "select ide_ystani,descripcion_ystani from yavirac_stror_anio";
        return sql;
    }

    public String getCarrera() {

        String sql = "";

        sql = "select ide_ystcrr,descripcion_ystcrr from yavirac_stror_carrera order by descripcion_ystcrr";

        return sql;
    }

    public String getIdioma() {

        String sql = "";
        sql = "select ide_ystdom,descripcion_ystdom from YAVIRAC_STROR_IDIOMA order by descripcion_ystdom";
        return sql;
    }

    public String getcodigoSecuencial(String secuencial) {

        String sql = "select a.ide_ystmos, ide_ystmod, descripcion_ystani,secuencial_ystmos,abreviatura_ystmos, longuitud_ystmos,aplica_abi_ystmos,aplica_anio_ystmos,\n"
                + "(case when a.aplica_anio_ystmos =false then\n"
                + "(case when aplica_abi_ystmos = true then abreviatura_ystmos||'-'||secuencial_ystmos else \n"
                + "secuencial_ystmos||'' end) else\n"
                + "(case when aplica_abi_ystmos = true then abreviatura_ystmos||'-'||descripcion_ystani||'-'||secuencial_ystmos else \n"
                + " secuencial_ystmos||'' end)   end ) as nuevo_secuencial\n"
                + " , length(secuencial_ystmos||'') as tamano\n"
                + " from yavirac_stror_modulo_secuencial a\n"
                + " left join yavirac_stror_anio b on a.ide_ystani= b.ide_ystani\n"
                + " where ide_ystmos =" + secuencial;
        return sql;
    }

    public String getActualizarSecuencial(String ide_gemos) {
        String sql = "";
        sql = "update yavirac_stror_modulo_secuencial set  secuencial_ystmos=secuencial_ystmos+1 where ide_ystmos=" + ide_gemos;
        //System.out.println("actualiza modulo " + sql);
        return sql;
    }

    public String getDistribucionPolitica(String tipo, String condicion) {

        String sql = "";
        sql = "select ide_ystdip,descripcion_ystdip from yavirac_stror_distribucion_pol";
        if (tipo.equals("1")) {
            sql += " where ide_ysttdp in ('" + condicion + "') ";
        }
        if (tipo.equals("2")) {
            sql += " where ide_ystdip in (" + condicion + ") ";
        }
        sql += " order by descripcion_ystdip";
        return sql;
    }

    /* Retorna el area departamento
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del area departamento
     */
    public String getAreaDepartamento(String activo) { //extraer informacion de la base de datos mediante un script
        String sql = "";
        sql = "select ide_ystard, descripcion_ysttad, descripcion_ystard from yavirac_stror_tipo_area_depar b,yavirac_stror_area_departament a where a.ide_ysttad = b.ide_ysttad;";
        return sql;
    }

    public String getSumaDiasFecha(String fecha, String dias) {
        String sql = "";
        sql = "SELECT 1 as codigo, cast( (CAST('" + fecha + "' AS DATE) + CAST('" + dias + " days' AS INTERVAL)) as date) as fecha;";
        return sql;
    }

    public String getMallaDocente() {
        String sql = "";
        sql = "select a.ide_ystmal,detalle_ystmat,descripcion_ystnie,detalle_yhogra,descripcion_ystjor,detalle_ysttfe\n"
                + "from yavirac_perso_malla_docente a\n"
                + "left join yavirac_stror_malla b on a.ide_ystmal=b.ide_ystmal\n"
                + "left join yavirac_stror_materia c on b.ide_ystmat=c.ide_ystmat\n"
                + "left join yavirac_stror_nivel_educacion d on b.ide_ystnie=d.ide_ystnie\n"
                + "left join yavirac_hora_grupo_academic e on a.ide_yhogra=e.ide_yhogra\n"
                + "left join yavirac_stror_jornada f on a.ide_ystjor=f.ide_ystjor\n"
                + "left join yavirac_stror_mension g on b.ide_ystmen=g.ide_ystmen \n"
                + "left join yavirac_stror_tipo_for_educaci h on g.ide_ysttfe=h.ide_ysttfe";
        return sql;
    }

    public String getTipoOperadora() {

        String sql = "";
        sql = "select ide_ysttio,descripcion_ysttio from yavirac_stror_tipo_operadora";
        return sql;
    }

    public String getTipoTelefono() {

        String sql = "";
        sql = "select ide_ysttit,descripcion_ysttit from yavirac_stror_tipo_telefonia";
        return sql;
    }

    public String getTipoCorreo() {

        String sql = "";
        sql = "select ide_ysttoc,descripcion_ysttic from yavirac_stror_tipo_correo";
        return sql;
    }

    public String getTipoDiscapacidad() {

        String sql = "";
        sql = "select ide_ysttid,descripcion_ysttid from yavirac_stror_tipo_discapacid";
        return sql;
    }

    public String getTipoArchivo() {

        String sql = "";
        sql = "select ide_ysttia,descripcion_ysttia from yavirac_stror_tipo_archivo";
        return sql;
    }

    public String getInstitucion() {

        String sql = "";
        sql = "select ide_ystins,descripcion_ystins from yavirac_stror_institucion;";
        return sql;
    }

    public String getMensionAlumno(String alumno) {

        String sql = "";
        sql = "select * from yavirac_stror_mension where ide_ystmen in ( select ide_ystmen from yavirac_matri_matricula  where ide_yaldap=" + alumno + " order by ide_ymamat desc limit 1)";
        return sql;
    }

    public String getCarreraMension(String mension) {

        String sql = "";
        sql = "select a.ide_ystmen,descripcion_ystmen,total_horas_ystmen,codigo_ystmal,numero_horas_ystmal,b.ide_ystmal,detalle_ystmat,descripcion_ystnie\n"
                + "   from yavirac_stror_mension a, yavirac_stror_malla b,yavirac_stror_materia c,yavirac_stror_nivel_educacion d\n"
                + "   where a.ide_ystmen=b.ide_ystmen\n"
                + "   and b.ide_ystmat= c.ide_ystmat\n"
                + "   and b.ide_ystnie=d.ide_ystnie\n"
                + "   and a.ide_ystmen=" + mension
                + "   order by orden_ystnie,detalle_ystmat";
        return sql;
    }

    public String getMensionFormacion() {
        String sql = "";
        sql = "select ide_ystmen,detalle_ysttfe,descripcion_ystmen,fechaapro_ystmen\n"
                + "from yavirac_stror_mension a\n"
                + "left join yavirac_stror_tipo_for_educaci b on a.ide_ysttfe=b.ide_ysttfe\n"
                + "order by detalle_ysttfe,descripcion_ystmen";
        return sql;
    }
}

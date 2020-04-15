/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.parametros;

import framework.aplicacion.Parametro;
import java.util.ArrayList;
import java.util.List;

public class Parametros {

    public List<Parametro> getParametrosSistema() {
        List<Parametro> lis_parametros = new ArrayList<>();
//////////////////////////////////////////////////////////////////////
        /*
         * SISTEMA MODULO =0
         */
        lis_parametros.add(new Parametro("0", "P_SIS_RESETEO_CLAVE", "Indica cuando se resetea la clave de un usuario", "6", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "P_SIS_ACTIVA_USUARIO", "Indica cuando se pone en estado activo a un usuario", "4", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "P_SIS_DESACTIVA_USUARIO", "Indica cuando se pone en estado inactivo a un usuario", "3", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "P_SIS_INGRESO_USUARIO", "Indica cuando un usuario accede exitosamente al sistema", "0", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "P_SIS_FALLO_INGRESO", "Indica cuando no se produce un acceso al sistema por parte de un usuario", "1", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "P_SIS_CADUCO_SESSION", "Indica cuando a un usuario se le expira el tiempo de su session", "7", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "P_SIS_SALIO_USUARIO", "Indica cuando un usuario sale del sistema", "8", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "P_SIS_CAMBIO_CLAVE", "Indica cuando un usuario cambia su clave", "5", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "P_SIS_CREAR_USUARIO", "Indica cuando se crea un usuario en el sistema", "9", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "P_SIS_BLOQUEA_USUARIO", "Indica cuando se bloqueao a un usuario", "2", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "P_SIS_DESBLOQUEA_USUARIO", "Indica cuando se desbloquea a un usuario", "10", "SIS_ACCION_AUDITORIA", "IDE_ACAU", "DESCRIPCION_ACAU"));
        lis_parametros.add(new Parametro("0", "p_tipo_pais", "Indica el tipo pais para recargar los paises", "2", "yavirac_stror_tipo_divisio_po", "ide_ysttdp", "descripcion_ysttdp"));
        lis_parametros.add(new Parametro("0", "p_tipo_provincia", "Indica tipo provincia para recargar las provincias", "2", "yavirac_stror_tipo_divisio_po", "ide_ysttdp", "descripcion_ysttdp"));
        lis_parametros.add(new Parametro("0", "p_tipo_canton", "Indica tipo canton para recragar cantones", "2", "yavirac_stror_tipo_divisio_po", "ide_ysttdp", "descripcion_ysttdp"));


        /*
         * ESTRUCTURA MODULO =2
         */
        lis_parametros.add(new Parametro("2", "p_gth_tipo_documento_cedula", "Indica el tipo de documento cedula", "1", "yavirac_stror_docu_identidad", "ide_ystdoi", "descripcion_ystdoi"));
        lis_parametros.add(new Parametro("2", "p_gth_tipo_documento_ruc", "Indica la el tipo documento de ruc", "2", "yavirac_stror_docu_identidad", "ide_ystdoi", "descripcion_ystdoi"));
        lis_parametros.add(new Parametro("2", "p_menu_reportes", "la opcion para reportes", "2", "sis_opcion", "ide_opci", "nom_opci"));

        /*
         * INSCRIPCION MODULO =3
         */
        lis_parametros.add(new Parametro("3", "p_documento_inscripcion", "Indica que los documentos son para inscripciones", "2", "yavirac_stror_requerido_para", "ide_ystrep", "descripcion_ystrep"));
        /*
         * MATRICULAS MODULO =4
         */
        lis_parametros.add(new Parametro("4", "p_documento_matricula", "Indica que los documentos son para matriculas", "2", "yavirac_stror_requerido_para", "ide_ystrep", "descripcion_ystrep"));
        /*
         * HORARIOS MODULO =5
         */
        lis_parametros.add(new Parametro("5", "p_tipo_receso", "Indica el receso para generacion de horarios", "2", "yavirac_hora_tipo_horario_jorna", "ide_yhothj", "descripcion_yhothj"));
        lis_parametros.add(new Parametro("5", "p_tipo_hora", "Indica el codigo del numero de hora de tipo receso, asi como hora clase dice primera hora, segunda esta dice receso", "20", "yavirac_hora_hora", "ide_yhohor", "descripcion_yhohor"));
        lis_parametros.add(new Parametro("5", "p_tipo_entrada_salida", "Indica el tipo de hora entrada salida para generacion de horarios", "2", "yavirac_hora_tipo_horario_jorna", "ide_yhothj", "descripcion_yhothj"));

        /*
         * TRAMITES MODULO =6
         */
        lis_parametros.add(new Parametro("6", "p_secuencial_externo", "Indica el nuemro secuecnial para tramites internos", "2", "yavirac_tra_secuencial", "ide_ytrsec", "detalle_ytrsec"));
        lis_parametros.add(new Parametro("6", "p_secuencial_interno", "Indica el nuemro secuecnial para tramites internos", "2", "yavirac_tra_secuencial", "ide_ytrsec", "detalle_ytrsec"));
        lis_parametros.add(new Parametro("6", "p_entidad_yavirac", "Indica el tipo entidad yavirac para tramites internos", "2", "yavirac_tra_tipo_entidad", "ide_ytrtie", "nombre_ytrtie"));
        /*
         * TRAMITES MODULO =7
         */
        lis_parametros.add(new Parametro("7", "p_proyecto_tipo_prac", "Indica el proyecto de tipo practica", "1", "yavirac_titu_tipos_vinculacion", "ide_ytitiv", "descripcion_ytitiv"));

        /*
         * NOTAS MODULO =7
         */
        lis_parametros.add(new Parametro("7", "p_tipo_eva_examen", "Indica el tipo de evaluación de examen para verificar si tiene recuperacion de examen", "2", "yavirac_nota_actividad_evaluac", "ide_ynoace", "descripcion_ynoace"));
        lis_parametros.add(new Parametro("7", "p_menu_rep_nota", "Indica los reportes que tiene el modulo notas", "2", "sis_opcion", "ide_opci", "nom_opci"));
        lis_parametros.add(new Parametro("7", "p_estado_cursando", "Indica el estado del record academico", "2", "yavirac_nota_estado_nota", "ide_ynoest", "detalle_ynoest"));
        lis_parametros.add(new Parametro("7", "p_estado_aprobado", "Indica el estado de aprovacion de materia del record academico", "2", "yavirac_nota_estado_nota", "ide_ynoest", "detalle_ynoest"));
        lis_parametros.add(new Parametro("7", "p_estado_reprobado", "Indica el estado que reprueba una materia", "2", "yavirac_nota_estado_nota", "ide_ynoest", "detalle_ynoest"));
        lis_parametros.add(new Parametro("7", "p_nivel_inicio", "Indica el nivel inicial de la carrera", "2", "yavirac_stror_nivel_educacion", "ide_ystnie", "descripcion_ystnie"));
        lis_parametros.add(new Parametro("7", "p_nivel_fin", "Indica el nivel final de laa carrera", "2", "yavirac_stror_nivel_educacion", "ide_ystnie", "descripcion_ystnie"));
        lis_parametros.add(new Parametro("7", "p_tipo_exa_recuperacion", "Indica el tipo de examen de recuperacion", "2", "yavirac_nota_actividad_evaluac", "ide_ynoace", "descripcion_ynoace"));
        lis_parametros.add(new Parametro("7", "p_estado_autorizado", "Indica que esta autorizado para modificar una nota una vez cerrado el periodo academico", "2", "yavirac_nota_estado_nota", "ide_ynoest", "detalle_ynoest"));
        lis_parametros.add(new Parametro("7", "p_estado_registrado", "Indica que el docente modifico una nota que le autorizaron", "2", "yavirac_nota_estado_nota", "ide_ynoest", "detalle_ynoest"));
        lis_parametros.add(new Parametro("7", "p_nombre_secretaria", "Indica el nombre de la secretaria para imprimir elrecord academico", "NOMBRE PRUEBA"));
        
        /*
         * TITULACION MODULO =8
         */
        lis_parametros.add(new Parametro("8", "p_secuencial_vinculacion", "Indica el el secuencial para el formato proyecto vincualcion", "2", "yavirac_stror_modulo_secuencial", "ide_ystmos", "abreviatura_ystmos"));
        /*
         * PORTAL MODULO =8
         */
        lis_parametros.add(new Parametro("9", "p_menu_rep_portal", "Indica los reportes que tiene el modulo notas portal", "2", "sis_opcion", "ide_opci", "nom_opci"));

//////////////////////////////////////////////////////////////////////
        return lis_parametros;
    }
}

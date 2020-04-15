
package paq_matricula;


import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;


public class RequerimientoPeriodoAcademico extends Pantalla{
    
        private Tabla tab_requerimiento_periodo_academico = new Tabla();//instanciar tabla del framework
        
     public RequerimientoPeriodoAcademico() {//constructor
       tab_requerimiento_periodo_academico.setId("tab_requerimiento_periodo_academico");// todo objeto instanciado poner id 
        tab_requerimiento_periodo_academico.setTabla("yavirac_matri_req_per_acade", "ide_ymarep", 1);  // nombre de la base de datos ii la clave primaria
        tab_requerimiento_periodo_academico.setHeader("REQUERIMIENTO PERIODO ACADÉMICO");
        tab_requerimiento_periodo_academico.dibujar();//dibuja la tabla

        PanelTabla pa_requerimiento_periodo_academico= new PanelTabla();//intanciamos el panel del framework
        pa_requerimiento_periodo_academico.setId("pa_requerimiento_periodo_academico");//nombre id
        pa_requerimiento_periodo_academico.setPanelTabla(tab_requerimiento_periodo_academico);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_requerimiento_periodo_academico =new Division ();//instanciamos
        div_requerimiento_periodo_academico.setId("div_requerimiento_periodo_academico");//es un idientificador
        div_requerimiento_periodo_academico.dividir1(tab_requerimiento_periodo_academico);
        
        agregarComponente(div_requerimiento_periodo_academico);//agregar componente
    }
    @Override
    public void insertar() {
        tab_requerimiento_periodo_academico.insertar();
    }

    @Override
    public void guardar() {
        tab_requerimiento_periodo_academico.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_requerimiento_periodo_academico.eliminar();
    }
    
    //generar geter and seter

    public Tabla getTab_documento_entregado() {
        return tab_requerimiento_periodo_academico;
    }

    public void setTab_requerimiento_periodo_academico(Tabla tab_requerimiento_periodo_academico) {
        this.tab_requerimiento_periodo_academico = tab_requerimiento_periodo_academico;
    }
    
}

package paq_matricula;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;


public class TipoPeriodo extends Pantalla{
    
        private Tabla tab_tipo_periodo = new Tabla();//instanciar tabla del framework
        
     public TipoPeriodo () {//constructor
       tab_tipo_periodo.setId("tab_tipo_periodo");// todo objeto instanciado poner id 
        tab_tipo_periodo.setTabla("yavirac_matri_tipo_periodo", "ide_ymatip", 1);  // nombre de la base de datos ii la clave primaria
        tab_tipo_periodo.setHeader("TIPO PERIODO DE MATRÍCULA");
        tab_tipo_periodo.getColumna("ide_ymatip").setNombreVisual("CÓDIGO");
        tab_tipo_periodo.getColumna("descripcion_ymatip").setNombreVisual("DESCRIPCIÓN");
        tab_tipo_periodo.dibujar();//dibuja la tabla

        PanelTabla pa_tipo_periodo = new PanelTabla();//intanciamos el panel del framework
        pa_tipo_periodo.setId("pa_tipo_periodo");//nombre id
        pa_tipo_periodo.setPanelTabla(tab_tipo_periodo);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_tipo_periodo =new Division ();//instanciamos
        div_tipo_periodo.setId("div_tipo_periodo");//es un idientificador
        div_tipo_periodo.dividir1(pa_tipo_periodo);
        
        agregarComponente(div_tipo_periodo);//agregar componente
    }
    @Override
    public void insertar() {
        tab_tipo_periodo.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_periodo.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_periodo.eliminar();
    }
    
    //generar geter and seter

    public Tabla getTab_tipo_periodo() {
        return tab_tipo_periodo;
    }

    public void setTab_tipo_periodo(Tabla tab_tipo_periodo) {
        this.tab_tipo_periodo = tab_tipo_periodo;
    }


    
}

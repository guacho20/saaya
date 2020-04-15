
package paq_matricula;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;


public class HorarioMatricula extends Pantalla{
    
        private Tabla tab_horario_matricula = new Tabla();//instanciar tabla del framework
        
     public HorarioMatricula () {//constructor
        tab_horario_matricula.setId("tab_horario_matricula");// todo objeto instanciado poner id 
        tab_horario_matricula.setTabla(" yavirac_matri_horar_matri", "ide_ymaper", 1);  // nombre de la base de datos ii la clave primaria
        tab_horario_matricula.setHeader("HORARIO MATRÍCULA");
        tab_horario_matricula.dibujar();//dibuja la tabla

        PanelTabla pa_horario_matricula = new PanelTabla();//intanciamos el panel del framework
        pa_horario_matricula.setId("pa_horario_matricula");//nombre id
        pa_horario_matricula.setPanelTabla(tab_horario_matricula);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_horario_matricula =new Division ();//instanciamos
        div_horario_matricula.setId("div_horario_matricula");//es un idientificador
        div_horario_matricula.dividir1(tab_horario_matricula);
        
        agregarComponente(div_horario_matricula);//agregar componente
    }
    @Override
    public void insertar() {
        tab_horario_matricula.insertar();
    }

    @Override
    public void guardar() {
        tab_horario_matricula.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_horario_matricula.eliminar();
    }
    
    //generar geter and seter

    public Tabla getTab_horario_matricula() {
        return tab_horario_matricula;
    }

    public void setTab_horario_matricula(Tabla tab_horario_matricula) {
        this.tab_horario_matricula = tab_horario_matricula;
    }
    
}

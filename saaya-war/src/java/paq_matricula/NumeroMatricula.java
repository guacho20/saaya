package paq_matricula;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;


public class NumeroMatricula extends Pantalla{
    
        private Tabla tab_numero_matricula = new Tabla();//instanciar tabla del framework
        
     public NumeroMatricula() {//constructor
        tab_numero_matricula.setId("tab_numero_matricula");// todo objeto instanciado poner id 
        tab_numero_matricula.setTabla("yavirac_matri_numer_matricu", "ide_ymanum", 1);  // nombre de la base de datos ii la clave primaria
        tab_numero_matricula.setHeader("NÚMERO DE MATRÍCULA");
        tab_numero_matricula.dibujar();//dibuja la tabla

        PanelTabla pa_numero_matricula = new PanelTabla();//intanciamos el panel del framework
        pa_numero_matricula.setId("pa_numero_matricula");//nombre id
        pa_numero_matricula.setPanelTabla(tab_numero_matricula);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_numero_matricula =new Division ();//instanciamos
        div_numero_matricula.setId("div_numero_matriucla");//es un idientificador
        div_numero_matricula.dividir1(tab_numero_matricula);
        
        agregarComponente(div_numero_matricula);//agregar componente
    }
    @Override
    public void insertar() {
         tab_numero_matricula.insertar();
    }

    @Override
    public void guardar() {
         tab_numero_matricula.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_numero_matricula.eliminar();
    }
    
    //generar geter and seter

    public Tabla getTab_numero_matricula() {
        return tab_numero_matricula;
    }

    public void setTab_numero_matricula(Tabla tab_numero_matricula) {
        this.tab_numero_matricula = tab_numero_matricula;
    }

    public Utilitario getUtilitario() {
        return utilitario;
    }

    public void setUtilitario(Utilitario utilitario) {
        this.utilitario = utilitario;
    }

    public Barra getBar_botones() {
        return bar_botones;
    }

    public void setBar_botones(Barra bar_botones) {
        this.bar_botones = bar_botones;
    }

    public Grupo getGru_pantalla() {
        return gru_pantalla;
    }

    public void setGru_pantalla(Grupo gru_pantalla) {
        this.gru_pantalla = gru_pantalla;
    }
   
}

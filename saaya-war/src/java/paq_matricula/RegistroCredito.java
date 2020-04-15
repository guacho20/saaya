
package paq_matricula;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

public class RegistroCredito extends Pantalla{
    
        private Tabla tab_registro_credito = new Tabla();//instanciar tabla del framework
        
     public RegistroCredito() {//constructor
        tab_registro_credito.setId("tab_registro_credito");// todo objeto instanciado poner id 
        tab_registro_credito.setTabla("yavirac_matri_regis_cred", "ide_ymarec", 1); // nombre de la base de datos ii la clave primaria
        tab_registro_credito.setHeader("REGISTRO DE CRÉDITOS");
        tab_registro_credito.dibujar();//dibuja la tabla

        PanelTabla pa_registro_credito = new PanelTabla();//intanciamos el panel del framework
        pa_registro_credito.setId("pa_documento_entregado");//nombre id
        pa_registro_credito.setPanelTabla(tab_registro_credito);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_registro_credito =new Division ();//instanciamos
       div_registro_credito.setId("div_documento_entregado");//es un idientificador
        div_registro_credito.dividir1(tab_registro_credito);
        
        agregarComponente(div_registro_credito);//agregar componente
    }
    @Override
    public void insertar() {
        tab_registro_credito.insertar();
    }

    @Override
    public void guardar() {
       tab_registro_credito.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_registro_credito.eliminar();
    }

    public Tabla getTab_registro_credito() {
        return tab_registro_credito;
    }

    public void setTab_registro_credito(Tabla tab_registro_credito) {
        this.tab_registro_credito = tab_registro_credito;
    }

    public Barra getBar_botones() {
        return bar_botones;
    }

    public void setBar_botones(Barra bar_botones) {
        this.bar_botones = bar_botones;
    }
    
}
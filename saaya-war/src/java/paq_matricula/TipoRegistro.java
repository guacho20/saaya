
package paq_matricula;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;


public class TipoRegistro extends Pantalla {
      private Tabla tab_tipo_registro = new Tabla();//instanciar tabla del framework

    public TipoRegistro() {//constructor
        tab_tipo_registro.setId("tab_tipo_registro");// todo objeto instanciado poner id 
        tab_tipo_registro.setTabla("yavirac_matri_tipo_reg_cred", "ide_ymatrc", 1);  // nombre de la base de datos ii la clave primaria
        tab_tipo_registro.setHeader("TIPO DE REGISTROS");
        tab_tipo_registro.dibujar();//dibuja la tabla

        PanelTabla pa_tipo_registro = new PanelTabla();//intanciamos el panel del framework
        pa_tipo_registro.setId("pa_tipo_registro");//nombre id
        pa_tipo_registro .setPanelTabla(tab_tipo_registro);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_tipo_registro  =new Division ();//instanciamos
        div_tipo_registro .setId("div_tipo_registro");//es un idientificador
        div_tipo_registro .dividir1(tab_tipo_registro);
        
        agregarComponente(div_tipo_registro);//agregar componente
    }

    @Override
    public void insertar() {
       tab_tipo_registro.insertar();
    }

    @Override
    public void guardar() {
      tab_tipo_registro.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_tipo_registro.eliminar();
    }

    public Tabla getTab_tipo_registro() {
        return tab_tipo_registro;
    }

    public void setTab_tipo_registro(Tabla tab_tipo_registro) {
        this.tab_tipo_registro = tab_tipo_registro;
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

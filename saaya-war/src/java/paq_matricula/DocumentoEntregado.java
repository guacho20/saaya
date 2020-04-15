
package paq_matricula;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;


public class DocumentoEntregado extends Pantalla{
    
        private Tabla tab_documento_entregado = new Tabla();//instanciar tabla del framework
        
     public DocumentoEntregado() {//constructor
        tab_documento_entregado.setId("tab_documento_entregado");// todo objeto instanciado poner id 
        tab_documento_entregado.setTabla("yavirac_matri_docu_entre_matri", "ide_ymdem", 1);  // nombre de la base de datos ii la clave primaria
        tab_documento_entregado.setHeader("DOCUMENTOS ENTREGADOS");
        tab_documento_entregado.dibujar();//dibuja la tabla

        PanelTabla pa_documento_entregado = new PanelTabla();//intanciamos el panel del framework
        pa_documento_entregado.setId("pa_documento_entregado");//nombre id
        pa_documento_entregado.setPanelTabla(tab_documento_entregado);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_documento_entregado =new Division ();//instanciamos
        div_documento_entregado.setId("div_documento_entregado");//es un idientificador
        div_documento_entregado.dividir1(tab_documento_entregado);
        
        agregarComponente(div_documento_entregado);//agregar componente
    }
    @Override
    public void insertar() {
        tab_documento_entregado.insertar();
    }

    @Override
    public void guardar() {
        tab_documento_entregado.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_documento_entregado.eliminar();
    }

    public Tabla getTab_documento_entregado() {
        return tab_documento_entregado;
    }

    public void setTab_documento_entregado(Tabla tab_documento_entregado) {
        this.tab_documento_entregado = tab_documento_entregado;
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
}

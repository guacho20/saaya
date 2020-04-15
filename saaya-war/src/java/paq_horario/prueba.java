/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horario;

/**
 *
 * @author ANDRES
 */
import framework.aplicacion.Fila;
import framework.aplicacion.TablaGenerica;
import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.BotonesCombo;
import framework.componentes.Combo;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Grupo;
import framework.componentes.Imagen;
import framework.componentes.ItemMenu;
import framework.componentes.PanelTabla;
import framework.componentes.Radio;
import framework.componentes.Reporte;
import framework.componentes.SeleccionCalendario;
import framework.componentes.SeleccionFormatoReporte;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import paq_asistencia.ejb.ServicioAsistencia;
import sistema.aplicacion.Pantalla;
import framework.componentes.Panel;
import org.primefaces.component.separator.Separator;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import paq_personal.ejb.ServicioPersonal;
public class prueba extends Pantalla{
    
     private Tabla tab_tabla = new Tabla();
     private Tabla tab_tabla1 = new Tabla();
     private Division div_division = new Division();
    
      @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
     
    public prueba(){
        BotonesCombo boc_seleccion_inversa = new BotonesCombo();
        ItemMenu itm_todas = new ItemMenu();
        ItemMenu itm_niguna = new ItemMenu();
        boc_seleccion_inversa.setValue("Selección Inversa");
        boc_seleccion_inversa.setIcon("ui-icon-circle-check");
        boc_seleccion_inversa.setMetodo("seleccinarInversa");
        boc_seleccion_inversa.setUpdate("tab_tabla");
        itm_todas.setValue("Seleccionar Todo");
        itm_todas.setIcon("ui-icon-check");
        itm_todas.setMetodo("seleccionarTodas");
        itm_todas.setUpdate("tab_tabla");
        boc_seleccion_inversa.agregarBoton(itm_todas);
        itm_niguna.setValue("Seleccionar Ninguna");
        itm_niguna.setIcon("ui-icon-minus");
        itm_niguna.setMetodo("seleccionarNinguna");
        itm_niguna.setUpdate("tab_tabla");
        boc_seleccion_inversa.agregarBoton(itm_niguna);
        
        tab_tabla = new Tabla();
        tab_tabla.setId("tab_tabla");
        tab_tabla.setSql(ser_horarios.getDia());
        tab_tabla.getColumna("ide_yhodia").setVisible(false);
        tab_tabla.getColumna("descripcion_yhodia").setNombreVisual(" ");
        tab_tabla.setNumeroTabla(1);
        tab_tabla.setCampoPrimaria("ide_yhodia");
        tab_tabla.setTipoSeleccion(true);
        tab_tabla.setLectura(true);
        tab_tabla.dibujar();
        PanelTabla pat_panel1 = new PanelTabla();
        pat_panel1.getChildren().add(boc_seleccion_inversa);
            
        pat_panel1.setPanelTabla(tab_tabla);
        
        
      /*  tab_tabla1 = new Tabla();
        tab_tabla1.setId("tab_tabla1");
        tab_tabla1.setSql("SELECT ide_ystmen, descripcion_ystmen FROM yavirac_stror_mension order by descripcion_ystmen");
        tab_tabla1.getColumna("ide_ystmen").setVisible(false);
        tab_tabla1.getColumna("descripcion_ystmen").setNombreVisual(" ");
        tab_tabla1.setCampoPrimaria("ide_ystmen");
        tab_tabla1.setTipoSeleccion(true);
        tab_tabla1.setLectura(true);    
        tab_tabla1.dibujar();
        
        PanelTabla pat_panel2 = new PanelTabla();
        //pat_panel2.getChildren().add(boc_seleccion_inversa);
            
        pat_panel2.setPanelTabla(tab_tabla1);*/
        
        
         div_division.setId("div_division");
         div_division.dividir1(pat_panel1);
       //  div_division.dividir2(pat_panel1, pat_panel2, "50%", "V");
         
       gru_pantalla.getChildren().add(div_division);
       agregarComponente(div_division);
    }
     public void seleccinarInversa() {
        if (tab_tabla.getSeleccionados() == null) {
            seleccionarTodas();
        } else if (tab_tabla.getSeleccionados().length == tab_tabla.getTotalFilas()) {
            seleccionarNinguna();
        } else {
            Fila seleccionados[] = new Fila[tab_tabla.getTotalFilas() - tab_tabla.getSeleccionados().length];
            int cont = 0;
            for (int i = 0; i < tab_tabla.getFilas().size(); i++) {
                boolean boo_selecionado = false;
                for (int j = 0; j < tab_tabla.getSeleccionados().length; j++) {
                    if (tab_tabla.getSeleccionados()[j].equals(tab_tabla.getFilas().get(i))) {
                        boo_selecionado = true;
                        break;
                    }
                }
                if (boo_selecionado == false) {
                    seleccionados[cont] = tab_tabla.getFilas().get(i);
                    cont++;
                }
            }
            tab_tabla.setSeleccionados(seleccionados);
        }
        //calculoTotal();
    }
     
    public void seleccionarTodas() {
        tab_tabla.setSeleccionados(null);
        Fila seleccionados[] = new Fila[tab_tabla.getTotalFilas()];
        for (int i = 0; i < tab_tabla.getFilas().size(); i++) {
            seleccionados[i] = tab_tabla.getFilas().get(i);
        }
        tab_tabla.setSeleccionados(seleccionados);
        //calculoTotal();

    }
    /**
     * DFJ*
     */
    public void seleccionarNinguna() {
        tab_tabla.setSeleccionados(null);
        //txt_total.setValue(utilitario.getFormatoNumero(0,2));
        //utilitario.addUpdate("txt_total");
    }
    @Override
    public void insertar() {
    }

    @Override
    public void guardar() {

    }

    @Override
    public void eliminar() {
       
    }

    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    }

    public Tabla getTab_tabla1() {
        return tab_tabla1;
    }

    public void setTab_tabla1(Tabla tab_tabla1) {
        this.tab_tabla1 = tab_tabla1;
    }

    public Division getDiv_division() {
        return div_division;
    }

    public void setDiv_division(Division div_division) {
        this.div_division = div_division;
    }
    
    
}

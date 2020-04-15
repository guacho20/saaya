/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Arbol;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ANDRES
 */
public class ModuloSecuencial extends Pantalla{

      private Tabla tab_stror_modulo_secuencial = new Tabla();
      private  Tabla tab_stror_modulo_secuencial_hijo = new Tabla();
      private Arbol arb_arbol = new Arbol();
     @EJB

    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
     
      public ModuloSecuencial(){
   
          tab_stror_modulo_secuencial .setId("tab_stror_modulo_secuencial");
          tab_stror_modulo_secuencial.setTabla("yavirac_stror_modulo", "ide_ystmod", 1);
          
          tab_stror_modulo_secuencial.setCampoPadre("yav_ide_ystmod"); // cuando se hace un arcbol
        tab_stror_modulo_secuencial.setCampoNombre("descripcion_ystmod"); // cuando se hace un arbol
          tab_stror_modulo_secuencial.getColumna("ide_ystmod").setNombreVisual("CODIGO");
         tab_stror_modulo_secuencial.getColumna("descripcion_ystmod").setNombreVisual("DESCRIPCION");
         tab_stror_modulo_secuencial.getColumna("activo_ystmod").setNombreVisual("ACTIVO");
         
           tab_stror_modulo_secuencial.agregarArbol(arb_arbol);
           tab_stror_modulo_secuencial.agregarRelacion(tab_stror_modulo_secuencial_hijo);
         
           tab_stror_modulo_secuencial.dibujar();
          
           arb_arbol.setId("arb_arbol");
           arb_arbol.dibujar();
          
          PanelTabla pa_modulo = new PanelTabla();
          pa_modulo.setId("pa_modulo");
          pa_modulo.setPanelTabla(tab_stror_modulo_secuencial);
          
       tab_stror_modulo_secuencial_hijo.setId("tab_stror_modulo_secuencial_hijo");
       tab_stror_modulo_secuencial_hijo.setTabla("yavirac_stror_modulo_secuencial", "ide_ystmos",2);
       tab_stror_modulo_secuencial_hijo.getColumna("ide_ystani").setCombo(ser_estructura.getAnioPeriodoCarrera());
       tab_stror_modulo_secuencial_hijo.getColumna("ide_ystmos").setNombreVisual("CODIGO");
       tab_stror_modulo_secuencial_hijo.getColumna("ide_ystani").setNombreVisual("AÑO");
       tab_stror_modulo_secuencial_hijo.getColumna("abreviatura_ystmos").setNombreVisual("ABREVIATURA");
       tab_stror_modulo_secuencial_hijo.getColumna("secuencial_ystmos").setNombreVisual("SECUENCIAL");
       tab_stror_modulo_secuencial_hijo.getColumna("aplica_abi_ystmos").setNombreVisual("APLICA ABREVIATURA");
       tab_stror_modulo_secuencial_hijo.getColumna("aplica_anio_ystmos").setNombreVisual("APLICA AÑO");
       tab_stror_modulo_secuencial_hijo.getColumna("longuitud_ystmos").setNombreVisual("LONGITUD");
       tab_stror_modulo_secuencial_hijo.getColumna("activo_ystmos").setNombreVisual("ACTIVO");
       
       tab_stror_modulo_secuencial_hijo.dibujar();
       
       
       PanelTabla pa_secuencial = new PanelTabla();
       pa_secuencial.setId("pa_secuencial");
       pa_secuencial.setPanelTabla(tab_stror_modulo_secuencial_hijo);
       
                
          Division div_tabla1 = new Division();
          div_tabla1.setId("div_tabla1");
          div_tabla1.dividir2(pa_modulo,pa_secuencial,"40%", "H");
          
        Division div_division = new Division();
        div_division.setId("div_division");
        div_division.dividir2(arb_arbol, div_tabla1, "21%", "V");  //arbol y div3
          
          agregarComponente(div_division);
      }
    
    @Override
    public void insertar() {
         if(tab_stror_modulo_secuencial.isFocus()){
         tab_stror_modulo_secuencial.insertar();
         }
         else if(tab_stror_modulo_secuencial_hijo.isFocus()){
             tab_stror_modulo_secuencial_hijo.insertar();
         }


}

    @Override
    public void guardar() {
  if( tab_stror_modulo_secuencial.guardar()){
      tab_stror_modulo_secuencial_hijo.guardar();
      guardarPantalla();
  }
  
    
    
    }

    @Override
    public void eliminar() {
        
if(tab_stror_modulo_secuencial.isFocus()){
         tab_stror_modulo_secuencial.eliminar();
         }
         else if(tab_stror_modulo_secuencial_hijo.isFocus()){
             tab_stror_modulo_secuencial_hijo.eliminar();
         }    }

    public Tabla getTab_stror_modulo_secuencial() {
        return tab_stror_modulo_secuencial;
    }

    public void setTab_stror_modulo_secuencial(Tabla tab_stror_modulo_secuencial) {
        this.tab_stror_modulo_secuencial = tab_stror_modulo_secuencial;
    }

    public Tabla getTab_stror_modulo_secuencial_hijo() {
        return tab_stror_modulo_secuencial_hijo;
    }

    public void setTab_stror_modulo_secuencial_hijo(Tabla tab_stror_modulo_secuencial_hijo) {
        this.tab_stror_modulo_secuencial_hijo = tab_stror_modulo_secuencial_hijo;
    }

    public Arbol getArb_arbol() {
        return arb_arbol;
    }

    public void setArb_arbol(Arbol arb_arbol) {
        this.arb_arbol = arb_arbol;
    }
    
    
    
    
}




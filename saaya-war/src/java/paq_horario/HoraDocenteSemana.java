
package paq_horario;

/**
 *
 * @author ANDRES
 */
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

public class HoraDocenteSemana extends Pantalla{
        private Tabla tab_docente_dias = new Tabla();
        
      public HoraDocenteSemana(){
          tab_docente_dias.setId("tab_docente_dias");   //identificador
          tab_docente_dias.setTabla("yavirac_hora_docente_dias", "ide_yhodse", 1);
          tab_docente_dias.getColumna("ide_ystpea").setVisible(false);
          tab_docente_dias.getColumna("ide_ypedpe").setNombreVisual("PERSONAL DOCENTES");
          tab_docente_dias.getColumna("ide_yhodia").setNombreVisual("DIA");
          tab_docente_dias.dibujar();
          
          PanelTabla pat_docente_dias = new PanelTabla();
          pat_docente_dias.setId("pat_docente_dias");
          pat_docente_dias.setPanelTabla(tab_docente_dias);
          Division div_docente_dias = new Division();
          div_docente_dias.setId("div_docente_dias");
          div_docente_dias.dividir1(pat_docente_dias);
          agregarComponente(div_docente_dias);
      } 
      @Override
      public void insertar() {
        tab_docente_dias.insertar();
      }

      @Override
      public void guardar() {
        tab_docente_dias.guardar();
        guardarPantalla();
      }

      @Override
       public void eliminar() {
       tab_docente_dias.eliminar();
     }
}

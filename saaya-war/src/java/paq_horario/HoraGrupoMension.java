
package paq_horario;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import sistema.aplicacion.Pantalla;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
/**
 *
 * @author Andres
 */
public class HoraGrupoMension extends Pantalla {
    
    private Tabla tab_grupo_mension = new Tabla(); 
    private Tabla tab_grupo_jornada = new Tabla();
    private Combo com_periodo_academico = new Combo();
    
     @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
     @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
     
     public HoraGrupoMension(){
        tab_grupo_mension.setId("tab_grupo_mension");   //identificador
        tab_grupo_mension.setTabla("yavirac_hora_grupo_mension", "ide_yhogrm", 1);
        tab_grupo_mension.agregarRelacion(tab_grupo_jornada);
        tab_grupo_mension.setCondicion("ide_ystpea=-1");
        tab_grupo_mension.getColumna("ide_ystpea").setCombo(ser_estructura_organizacional.getPeriodoAcademico("true,false"));
        tab_grupo_mension.getColumna("ide_ystmen").setCombo(ser_estructura_organizacional.getMension());
        tab_grupo_mension.getColumna("ide_yhogra").setCombo(ser_horarios.getGrupoAcademico());
        tab_grupo_mension.getColumna("ide_yhotgh").setCombo(ser_horarios.getTipoGeneracionHora());
        tab_grupo_mension.getColumna("ide_ystnie").setCombo(ser_estructura_organizacional.getNivelEducacion());
        tab_grupo_mension.getColumna("ide_ystpea").setVisible(false);
        tab_grupo_mension.getColumna("ide_yhogrm").setNombreVisual("CÓDIGO PRINCIPAL");
        tab_grupo_mension.getColumna("ide_ystmen").setNombreVisual("MENSIÓN CARRERAS");
        tab_grupo_mension.getColumna("ide_yhogra").setNombreVisual("GRUPO ACADÉMICO");
        tab_grupo_mension.getColumna("ide_yhotgh").setNombreVisual("TIPO GENERACIÓN DE HORARIO");
        tab_grupo_mension.getColumna("ide_ystnie").setNombreVisual("NIVEL DE EDUCACIÓN");
        tab_grupo_mension.getColumna("detalle_yhogrm").setNombreVisual("DETALLE");
        tab_grupo_mension.dibujar();
            
        PanelTabla pat_grupo_mension = new PanelTabla();
        pat_grupo_mension.setId("pat_grupo_mension");
        pat_grupo_mension.setPanelTabla(tab_grupo_mension);

        
        tab_grupo_jornada.setId("tab_grupo_jornada");   //identificador
        tab_grupo_jornada.setTabla("yavirac_hora_grupo_jornada", "ide_yhogrj", 2);
        tab_grupo_jornada.getColumna("ide_ystjor").setCombo(ser_estructura_organizacional.getJornada("true,false"));
        tab_grupo_jornada.getColumna("ide_yhogrj").setNombreVisual("CODIGO");
        tab_grupo_jornada.getColumna("ide_ystjor").setNombreVisual("JORNADA");
        tab_grupo_jornada.dibujar();
        PanelTabla pat_grupo_jornada = new PanelTabla();
        pat_grupo_jornada.setId("pat_grupo_jornada");
        pat_grupo_jornada.getMenuTabla().getItem_buscar().setRendered(false);
        pat_grupo_jornada.getMenuTabla().getItem_importar().setRendered(false);
        pat_grupo_jornada.getMenuTabla().getItem_excel().setRendered(false);
        pat_grupo_jornada.getMenuTabla().getItem_excel_filtro().setRendered(false);
        pat_grupo_jornada.getMenuTabla().getItem_formato().setRendered(false);
        pat_grupo_jornada.getMenuTabla().quitarSubmenuOtros();
        pat_grupo_jornada.setPanelTabla(tab_grupo_jornada);
        
        Division div_grupo_mension = new Division();
        div_grupo_mension.setId("div_grupo_mension");
        div_grupo_mension.dividir2(pat_grupo_mension, pat_grupo_jornada, "50%","H");
        agregarComponente(div_grupo_mension); 
        
      com_periodo_academico.setId("com_periodo_academico");
      com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
      agregarComponente(com_periodo_academico);
      bar_botones.agregarComponente(com_periodo_academico);
      com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
      //
        
    }
          public void filtroComboPeriodoAcademnico(){
        
        tab_grupo_mension.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_grupo_mension.ejecutarSql();
        utilitario.addUpdate("tab_grupo_mension");
        
    }
     
    @Override
    public void insertar() {
       if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Academico");
            return;
        }
       else if (tab_grupo_mension.isFocus()){
       tab_grupo_mension.insertar();
       tab_grupo_mension.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
       utilitario.addUpdateTabla(tab_grupo_mension, "ide_ystpea", "");
       }
       else if (tab_grupo_jornada.isFocus()){
            tab_grupo_jornada.insertar();
        }
    }

    @Override
    public void guardar() {
        if(tab_grupo_mension.isFocus()){
        tab_grupo_mension.guardar();
        }
        else if (tab_grupo_jornada.isFocus()){
        tab_grupo_jornada.guardar();
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       if(tab_grupo_mension.isFocus()){
       tab_grupo_mension.eliminar();
        }
        else if (tab_grupo_jornada.isFocus()){
            tab_grupo_jornada.eliminar();
        }
    }

    public Tabla getTab_grupo_mension() {
        return tab_grupo_mension;
    }

    public void setTab_grupo_mension(Tabla tab_grupo_mension) {
        this.tab_grupo_mension = tab_grupo_mension;
    }

    public Tabla getTab_grupo_jornada() {
        return tab_grupo_jornada;
    }

    public void setTab_grupo_jornada(Tabla tab_grupo_jornada) {
        this.tab_grupo_jornada = tab_grupo_jornada;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }
    
   /*public void filtroComboPeriodoAcademnico(){
        
        tab_grupo_mension.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
    }*/
    
}

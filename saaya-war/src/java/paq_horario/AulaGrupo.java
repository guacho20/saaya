package paq_horario;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import sistema.aplicacion.Pantalla;



public class AulaGrupo extends Pantalla {
   private Tabla tab_aula_grupo = new Tabla();
   private Tabla tab_cupo_alumno = new Tabla();
   private Combo com_periodo_academico = new Combo();
   
@EJB
    private final ServicioEstructuraOrganizacional ser_instalacion = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);   
   
@EJB
    private final ServiciosHorarios ser_instalacion1 = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);   
     
       
    public AulaGrupo(){
       
     com_periodo_academico.setId("cmb_periodo_academico");
     com_periodo_academico.setCombo(ser_instalacion.getPeriodoAcademico("true,false"));
     com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
     
     bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
        bar_botones.agregarComponente(com_periodo_academico);

    
    tab_cupo_alumno.setId("tab_cupo_alumno");   
    tab_cupo_alumno.setTabla("yavirac_matri_cupo_alumno", "ide_ymacal", 1);   
    tab_cupo_alumno.getColumna("ide_ystmen").setCombo(ser_instalacion.getMension());
    tab_cupo_alumno.getColumna("ide_ystnie").setCombo(ser_instalacion.getNivelEducacion());
    tab_cupo_alumno.getColumna("ide_yhogra").setCombo(ser_instalacion1.getGrupoAcademico());
    tab_cupo_alumno.getColumna("ide_ystjor").setCombo(ser_instalacion.getJornada("true,false"));
    tab_cupo_alumno.getColumna("ide_ystpea").setVisible(false);
    tab_cupo_alumno.setCondicion("ide_ystpea=-1");
    
    tab_cupo_alumno.agregarRelacion(tab_aula_grupo);
    tab_cupo_alumno.dibujar();
      
    
    tab_aula_grupo.setId("tab_aula_grupo");   
    tab_aula_grupo.setTabla("yavirac_hora_aula_grupo", "ide_yhoagr", 2);
    tab_aula_grupo.getColumna("ide_ystins").setCombo(ser_instalacion.getInstalacion());
    tab_aula_grupo.dibujar();    

    PanelTabla pat_cupo_alumno = new PanelTabla();
        pat_cupo_alumno.setId("pat_cupo_alumno");
        pat_cupo_alumno.setPanelTabla(tab_cupo_alumno);
        
    PanelTabla pat_aula_grupo = new PanelTabla();
        pat_aula_grupo.setId("pat_aula_grupo");
        pat_aula_grupo.setPanelTabla(tab_aula_grupo);
        
        
        Division div_aula_grupo = new Division();
        div_aula_grupo.setId("div_aula_grupo");
        div_aula_grupo.dividir2(pat_cupo_alumno, pat_aula_grupo, "50%", "H");
        agregarComponente(div_aula_grupo); 
        
    }
    public void filtroComboPeriodoAcademnico(){
        tab_cupo_alumno.setCondicion("ide_ystpea="+com_periodo_academico.getValue());
        tab_cupo_alumno.ejecutarSql();
        tab_aula_grupo.ejecutarValorForanea(tab_cupo_alumno.getValorSeleccionado());
        
        utilitario.addUpdate("tab_cupo_alumno,tab_aula_grupo");
    }

    @Override
    public void insertar() {
        if(tab_cupo_alumno.isFocus()){
        tab_cupo_alumno.insertar();
        tab_cupo_alumno.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
        utilitario.addUpdate("tab_cupo_alumno");
        }
        else if (tab_aula_grupo.isFocus()){
            tab_aula_grupo.insertar();
        }
    }

    @Override
    public void guardar() {
        if(tab_cupo_alumno.guardar()){
        tab_aula_grupo.guardar();
        guardarPantalla();
        }
    }

    @Override
    public void eliminar() {
        tab_aula_grupo.eliminar();
    }

    public Tabla getTab_aula_grupo() {
        return tab_aula_grupo;
    }

    public void setTab_aula_grupo(Tabla tab_aula_grupo) {
        this.tab_aula_grupo = tab_aula_grupo;
    }

    public Tabla getTab_cupo_alumno() {
        return tab_cupo_alumno;
    }

    public void setTab_cupo_alumno(Tabla tab_cupo_alumno) {
        this.tab_cupo_alumno = tab_cupo_alumno;
    }
}

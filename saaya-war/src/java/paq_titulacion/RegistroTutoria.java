/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;
import paq_personal.ejb.ServicioPersonal;
import paq_alumno.ejb.ServicioAlumno;


/**
 *
 * @author Martha
 */
public class RegistroTutoria extends Pantalla{
    private Tabla tab_tutorias = new Tabla();
    private Tabla tab_tutores = new Tabla();
    private Tabla tab_tutorias_alumno = new Tabla();
    private Tabla tab_seguimiento = new Tabla();
    private Combo com_periodo_academico = new Combo();
   
    @EJB
    private final ServicioEstructuraOrganizacional ser_mension = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_datopersonal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioAlumno ser_datoalumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
     
    
    public RegistroTutoria(){
         
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_mension.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        
        
       
        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADÉMICO"));
        bar_botones.agregarComponente(com_periodo_academico);         
         
         
        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADÉMICO"));
       
        
        
        
          tab_tutorias.setId("tab_tutorias");  // todo objeto instanciado poner id 
        tab_tutorias.setTabla("yavirac_titu_tutorias", "ide_ytitut", 1);    // nom bdd
        tab_tutorias.getColumna("ide_ystmen").setCombo(ser_mension.getMension());
         tab_tutorias.getColumna("ide_ypedpe").setCombo(ser_datopersonal.getDatopersonal("true,false"));
        
         tab_tutorias.getColumna("ide_ypedpe").setAutoCompletar();
        tab_tutorias.getColumna("ide_ystpea").setVisible(false);
        
         tab_tutorias.agregarRelacion(tab_tutores);
        tab_tutorias.agregarRelacion(tab_tutorias_alumno);
        tab_tutorias.agregarRelacion(tab_seguimiento);
        tab_tutorias.setHeader("REGISTRO DE TUTORIAS");
        tab_tutorias.setTipoFormulario(true);
        tab_tutorias.getGrid().setColumns(4);
        tab_tutorias.dibujar();
         
         
        PanelTabla pa_tutorias = new PanelTabla();
        pa_tutorias.setId("pat_tutorias"); // nombre de i
        pa_tutorias.setPanelTabla(tab_tutorias);

        //tabla perioado de evaluacion
        tab_tutores.setId("tab_tutores");  // todo objeto instanciado poner id 
        tab_tutores.setIdCompleto("tab_tabulador:tab_tutores");
        tab_tutores.setTabla("yavirac_titu_tutores", "ide_ytitur", 2);    // nom bdd
        tab_tutores.dibujar();

        PanelTabla pa_tutores = new PanelTabla();
        pa_tutores.setId("pat_tutores"); // nombre de i
        pa_tutores.setPanelTabla(tab_tutores);

        //tabla perioado de evaluacion
        tab_tutorias_alumno.setId("tab_tutorias_alumno");  // todo objeto instanciado poner id 
        tab_tutorias_alumno.setIdCompleto("tab_tabulador:tab_tutorial_alumno");
       tab_tutorias_alumno.setTabla("yavirac_titu_tutorias_alumno", "ide_ytitua", 3);
       tab_tutorias_alumno.getColumna("ide_yaldap").setCombo(ser_datoalumno.getDatosAlumnos("true,false"));// nom bdd
        tab_tutorias_alumno.dibujar();

        PanelTabla pa_tutorial_alumno = new PanelTabla();
        pa_tutorial_alumno.setId("pat_tutorias_alumno"); // nombre de i
        pa_tutorial_alumno.setPanelTabla(tab_tutorias_alumno);

        //tabla perioado de evaluacion
        tab_seguimiento.setId("tab_seguimiento");  // todo objeto instanciado poner id 
        tab_seguimiento.setIdCompleto("tab_tabulador:tab_seguimiento");
        tab_seguimiento.setTabla("yavirac_titu_seguimiento", "ide_ytiseg", 4);    // nom bdd
        tab_seguimiento.dibujar();

        PanelTabla pa_seguimiento = new PanelTabla();
        pa_seguimiento.setId("pat_seguimiento"); // nombre de i
        pa_seguimiento.setPanelTabla(tab_seguimiento);

        Tabulador tab_tabulador = new Tabulador();
        tab_tabulador.setId("tab_tabulador");

        tab_tabulador.agregarTab("TUTORES", pa_tutores);
        tab_tabulador.agregarTab("ALUMNOS INTEGRANTES", pa_tutorial_alumno);
        tab_tabulador.agregarTab("SEGUIMIENTO", pa_seguimiento);

        Division div_tutoriales = new Division();
        div_tutoriales.dividir2(pa_tutorias, tab_tabulador, "30%", "h");
        agregarComponente(div_tutoriales);

     }
     public void filtroComboPeriodoAcademnico(){
        
        tab_tutorias.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_tutorias.ejecutarSql();
        tab_seguimiento.ejecutarValorForanea(tab_tutorias.getValorSeleccionado());
        tab_tutores.ejecutarValorForanea(tab_tutorias.getValorSeleccionado());
        tab_tutorias_alumno.ejecutarValorForanea(tab_tutorias.getValorSeleccionado());
        utilitario.addUpdate("tab_tutorias,tab_seguimiento,tab_tutores,tab_tutorias_alumno");
        
    }     
    @Override
    public void insertar() {
        
                if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Académico");
            return;
        }
        else {
        if (tab_tutorias.isFocus()) {
            tab_tutorias.insertar();
        tab_tutorias.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
            
        } else if (tab_tutores.isFocus()) {
            tab_tutores.insertar();
        } else if (tab_tutorias_alumno.isFocus()) {
            tab_tutorias_alumno.insertar();
        } else if (tab_seguimiento.isFocus()) {
            tab_seguimiento.insertar();
        }
        }
    }

    @Override
    public void guardar() {
        if (tab_tutorias.guardar()) {
            if (tab_tutores.guardar()) {
                if (tab_tutorias_alumno.guardar()) {
                    tab_seguimiento.guardar();
                }
            }
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if (tab_tutorias.isFocus()) {
            tab_tutorias.eliminar();
        } else if (tab_tutores.isFocus()) {
            tab_tutores.eliminar();
        } else if (tab_tutorias_alumno.isFocus()) {
            tab_tutorias_alumno.eliminar();
        } else if (tab_seguimiento.isFocus()) {
            tab_seguimiento.eliminar();
        }
    }

    public Tabla getTab_tutorias() {
        return tab_tutorias;
    }

    public void setTab_tutorias(Tabla tab_tutorias) {
        this.tab_tutorias = tab_tutorias;
    }

    public Tabla getTab_tutores() {
        return tab_tutores;
    }

    public void setTab_tutores(Tabla tab_tutores) {
        this.tab_tutores = tab_tutores;
    }

    public Tabla getTab_tutorias_alumno() {
        return tab_tutorias_alumno;
    }

    public void setTab_tutorias_alumno(Tabla tab_tutorial_alumno) {
        this.tab_tutorias_alumno = tab_tutorias_alumno;
    }

    public Tabla getTab_seguimiento() {
        return tab_seguimiento;
    }

    public void setTab_seguimiento(Tabla tab_seguimiento) {
        this.tab_seguimiento = tab_seguimiento;
    }
 
}

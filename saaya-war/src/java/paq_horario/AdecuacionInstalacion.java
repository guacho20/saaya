package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import sistema.aplicacion.Pantalla;



public class AdecuacionInstalacion extends Pantalla {
   private Tabla tab_adecu_instalacion = new Tabla();
   private Tabla tab_instalacion = new Tabla();
   
    @EJB
    private final ServicioEstructuraOrganizacional ser_instalacion = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
   
    @EJB
    private final ServiciosHorarios ser_instalacion_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    
       
    public AdecuacionInstalacion(){

    
    tab_instalacion.setId("tab_instalacion");   
    tab_instalacion.setTabla("yavirac_stror_instalacion", "ide_ystins", 1);
    tab_instalacion.getColumna("ide_ysttin").setCombo(ser_instalacion.getTipoInstalacion());
    tab_instalacion.getColumna("ide_yhodin").setCombo(ser_instalacion_horarios.getDistribucionInstalacion());
    tab_instalacion.getColumna("ide_ystins").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_instalacion.getColumna("ide_ysttin").setNombreVisual("TIPO INSTALACIÓN");
    tab_instalacion.getColumna("ide_yhodin").setNombreVisual("DISTRIBUCIÓN INSTALACIÓN");
    tab_instalacion.getColumna("nombre_ystins").setNombreVisual("LABORATORIO/AULA");
    tab_instalacion.getColumna("abreviatura_ystins").setNombreVisual("ABREVIATURA");
    tab_instalacion.getColumna("capinicial_ystins").setNombreVisual("CAPACIDAD INICIAL");
    tab_instalacion.getColumna("capfinal_ystins").setNombreVisual("CAPACIDAD FINAL");
    tab_instalacion.getColumna("estado_ystins").setNombreVisual("ACTIVO");

   
    tab_instalacion.agregarRelacion(tab_adecu_instalacion);
    tab_instalacion.dibujar();
    
    tab_adecu_instalacion.setId("tab_adecu_instalacion");   
    tab_adecu_instalacion.setTabla("yavirac_hora_adecu_instalacion", "ide_yhoain", 2);
    tab_adecu_instalacion.getColumna("ide_yhotad").setCombo(ser_instalacion_horarios.getTipoAdecuacion());
    tab_instalacion.getColumna("ide_yhoain").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_instalacion.getColumna("ide_yhotad").setNombreVisual("EQUIPO");
    tab_instalacion.getColumna("descrpcion_yhoain").setNombreVisual("DESCRIPCIÓN");
    tab_instalacion.getColumna("fechaalta_yhoain").setNombreVisual("FECHA SALIDA");
    tab_instalacion.getColumna("fechabaja_yhoain").setNombreVisual("FECHA INGRESO");
    tab_instalacion.getColumna("estado_yhoain").setNombreVisual("ACTIVO");
    tab_adecu_instalacion.dibujar();    

    PanelTabla pat_instalacion = new PanelTabla();
        pat_instalacion.setId("pat_instalacion");
        pat_instalacion.setPanelTabla(tab_instalacion);
        
        PanelTabla pat_adecu_instalacion = new PanelTabla();
        pat_adecu_instalacion.setId("pat_adecu_instalacion");
        pat_adecu_instalacion.setPanelTabla(tab_adecu_instalacion);
        
        Division div_adecu_instalacion = new Division();
        div_adecu_instalacion.setId("div_adecu_instalacion");
        div_adecu_instalacion.dividir2(pat_instalacion, pat_adecu_instalacion, "50%", "H");
        agregarComponente(div_adecu_instalacion); 
        
    }

    @Override
    public void insertar() {
        if(tab_instalacion.isFocus()){
        tab_instalacion.insertar();
        }
        else if (tab_adecu_instalacion.isFocus()){
            tab_adecu_instalacion.insertar();
        }
    }

    @Override
    public void guardar() {
        if(tab_instalacion.isFocus()){
        tab_instalacion.guardar();    
        }
        else if (tab_adecu_instalacion.isFocus()){
        tab_adecu_instalacion.guardar();    
        }

        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if(tab_instalacion.isFocus()){
        tab_instalacion.eliminar();    
        }
        else if (tab_adecu_instalacion.isFocus()){
        tab_adecu_instalacion.eliminar();    
        }
    }

    public Tabla getTab_adecu_instalacion() {
        return tab_adecu_instalacion;
    }

    public void setTab_adecu_instalacion(Tabla tab_adecu_instalacion) {
        this.tab_adecu_instalacion = tab_adecu_instalacion;
    }

    public Tabla getTab_instalacion() {
        return tab_instalacion;
    }

    public void setTab_instalacion(Tabla tab_instalacion) {
        this.tab_instalacion = tab_instalacion;
    }

    

 
}

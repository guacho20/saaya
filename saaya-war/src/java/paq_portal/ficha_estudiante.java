/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_portal;

import framework.componentes.Division;
import framework.componentes.Imagen;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author OROCHII VEGA
 */
public class ficha_estudiante extends Pantalla {

    private Tabla tab_tabla1 = new Tabla();
    private Tabla tab_tabla2 = new Tabla();
    private Tabla tab_tabla3 = new Tabla();
    private Tabla tab_tabla4 = new Tabla();

    @EJB
    private final ServicioAlumno servicioAlumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);

    public ficha_estudiante() {
        //Permite crear la tabla 
        bar_botones.getBot_inicio().setRendered(false);
        bar_botones.getBot_atras().setRendered(false);
        bar_botones.getBot_insertar().setRendered(false);
        bar_botones.getBot_eliminar().setRendered(false);
        bar_botones.getBot_fin().setRendered(false);
        bar_botones.getBot_siguiente().setRendered(false);
        bar_botones.getBot_guardar().setRendered(false);
        
        tab_tabla1.setId("tab_tabla1");
        tab_tabla1.setIdCompleto("tab_tabulador:tab_tabla1");
        tab_tabla1.setTabla("yavirac_alum_dato_personal", "ide_yaldap", 1);
        tab_tabla1.setCondicion("ide_yaldap=" + utilitario.getVariable("ALUMNO"));
        tab_tabla1.getColumna("ide_ysttis").setCombo(ser_estructura.getTipoSangre("true,false"));
        tab_tabla1.getColumna("ide_ystdoi").setCombo(ser_estructura.getDocumentoIdentidad("true,false"));
        tab_tabla1.getColumna("ide_ystdip").setCombo(ser_estructura.getDivisionPolitcaParroquia());
        tab_tabla1.getColumna("ide_ystgen").setCombo(ser_estructura.getGenero("true,false"));
        tab_tabla1.getColumna("IDE_YSTESC").setCombo(ser_estructura.getEstadoCivil("true,false"));
        tab_tabla1.getColumna("IDE_YSTNAC").setCombo(ser_estructura.getNacionalidad("true,false"));
        tab_tabla1.getColumna("IDE_YSTNAC").setNombreVisual("NACIONALIDAD");
        tab_tabla1.getColumna("ide_ystdip").setNombreVisual("LUGAR NACIMIENTO");
        tab_tabla1.getColumna("ide_ysttis").setNombreVisual("TIPO DE SANGRES");
        tab_tabla1.getColumna("ide_ystdoi").setNombreVisual("DOCUMENTO IDENTIDAD");
        tab_tabla1.getColumna("ide_ystgen").setNombreVisual("GENERO");
        tab_tabla1.getColumna("NOMBRE_YALDAP ").setNombreVisual("NOMBRES");
        tab_tabla1.getColumna("IDE_YSTESC").setNombreVisual("ESTADO CIVIL");
        tab_tabla1.getColumna("DOC_IDENTIDAD_YALDAP").setNombreVisual("C.I.");
        tab_tabla1.getColumna("FECHA_NAC_YALDAP").setNombreVisual("FECHA NACIMIENTO");
        tab_tabla1.getColumna("APELLIDO_YALDAP").setNombreVisual("APELLIDOS");
        tab_tabla1.getColumna("DISCAPACIDAD_YALDAP").setNombreVisual("DISCAPACIDAD");
        tab_tabla1.getColumna("ACTIVO_YALDAP").setNombreVisual("ACTIVO");
        tab_tabla1.getColumna("EDAD_YALDAP ").setVisible(false);
        tab_tabla1.getColumna("FOTOGRAFIA_YALDAP").setVisible(false);
        tab_tabla1.getColumna("FIRMA_YALDAP").setVisible(false);
        tab_tabla1.getColumna("IDE_YALDAP").setVisible(false);
        tab_tabla1.setMostrarNumeroRegistros(false);
        tab_tabla1.getGrid().setColumns(4);
        tab_tabla1.setHeader("DATOS PERSONALES");
        tab_tabla1.setLectura(true);
        tab_tabla1.setTipoFormulario(true);
        
        tab_tabla1.dibujar();

        Tabulador tab_tabulador = new Tabulador();
        tab_tabulador.setId("tab_tabulador");

        //Es el contenedor de la tabla
        PanelTabla pat_panel1 = new PanelTabla();
        pat_panel1.setId("pat_panel1");
        pat_panel1.setPanelTabla(tab_tabla1);
        
        tab_tabla4.setId("tab_tabla4");
        tab_tabla4.setIdCompleto("tab_tabulador:tab_tabla4");
        tab_tabla4.setTabla("yavirac_alum_direccion_alumno", "ide_yaldia", 4);
        tab_tabla4.setCondicion("ide_yaldap=" + utilitario.getVariable("ALUMNO"));
        tab_tabla4.getColumna("ide_yaldia").setVisible(false); // para ocultar
        tab_tabla4.getColumna("ide_yaldap").setVisible(false); // para ocultar
        tab_tabla4.getColumna("ide_yaldia").setNombreVisual("IDE");
        tab_tabla4.getColumna("ide_yaldia").setOrden(1);
        tab_tabla4.getColumna("ide_ystdip").setCombo(ser_estructura.getDivisionPolitcaParroquia());
        tab_tabla4.getColumna("ide_ystdip").setNombreVisual("DIRECCIÓN ACTUAL");
        tab_tabla4.getColumna("ide_ystdip").setOrden(2);
        tab_tabla4.getColumna("descripcion_yaldia").setNombreVisual("LUGAR RESIDENCIA");
        tab_tabla4.getColumna("descripcion_yaldia").setOrden(3);
        tab_tabla4.getColumna("notificacion_yaldia").setNombreVisual("NOTIFICACIÓN");
        tab_tabla4.getColumna("notificacion_yaldia").setOrden(4);
        tab_tabla4.getColumna("activo_yaldia").setNombreVisual("ACTIVO");
        tab_tabla4.getColumna("activo_yaldia").setOrden(5);
        tab_tabla4.setLectura(true);
        tab_tabla4.dibujar();
        PanelTabla pat_panel4 = new PanelTabla();
        pat_panel4.setPanelTabla(tab_tabla4);


        tab_tabla2.setId("tab_tabla2");
        tab_tabla2.setTabla("yavirac_alum_telefono", "ide_yaltel", 2);
        tab_tabla2.setIdCompleto("tab_tabulador:tab_tabla2");
        tab_tabla2.setCondicion("ide_yaldap=" + utilitario.getVariable("ALUMNO"));
        tab_tabla2.getColumna("ide_yaldap").setVisible(false);
        tab_tabla2.getColumna("ide_yaltel").setVisible(false);
        tab_tabla2.getColumna("ide_yaldfe").setVisible(false);
        tab_tabla2.getColumna("ide_yaltel").setOrden(1);
        tab_tabla2.getColumna("ide_ysttit").setNombreVisual("TIPO TELEFONO");
        tab_tabla2.getColumna("ide_ysttit").setCombo(ser_estructura.getTipoTelefono());
        tab_tabla2.getColumna("ide_ysttio").setCombo(ser_estructura.getTipoOperadora());
        tab_tabla2.getColumna("ide_ysttio").setNombreVisual("TIPO OPERADORA");
        tab_tabla2.getColumna("ide_ysttio").setOrden(2);
        tab_tabla2.getColumna("numero_yaltel").setNombreVisual("NÚMERO DE TELÉFONO FIJO");
        tab_tabla2.getColumna("numero_yaltel").setOrden(4);
        tab_tabla2.getColumna("activo_yaltel").setNombreVisual("ACTIVO");
        tab_tabla2.getColumna("activo_yaltel").setOrden(6);
        tab_tabla2.getColumna("notificacion_yaltel").setNombreVisual("MODIFICACIÓN TELEFONO");
        tab_tabla2.getColumna("notificacion_yaltel").setOrden(5);
        tab_tabla2.setLectura(true);
        tab_tabla2.dibujar();

        PanelTabla pat_panel2 = new PanelTabla();
        pat_panel2.setId("pat_panel2");
        pat_panel2.setPanelTabla(tab_tabla2);

        tab_tabla3.setId("tab_tabla3");
        tab_tabla3.setTabla("yavirac_alum_correo", "ide_yalcor", 3);
        tab_tabla3.setIdCompleto("tab_tabulador:tab_tabla3");
        tab_tabla3.getColumna("ide_yalcor").setVisible(false);
        tab_tabla3.getColumna("ide_yaldap").setVisible(false);
        tab_tabla3.getColumna("ide_yaldfe").setVisible(false);
        tab_tabla3.setCondicion("ide_yaldap=" + utilitario.getVariable("ALUMNO"));
        tab_tabla3.getColumna("ide_ysttoc").setCombo(ser_estructura.getTipoCorreo());
        tab_tabla3.getColumna("ide_ysttoc").setNombreVisual("TIPO CORREO");
        tab_tabla3.getColumna("descripcion_yalcor").setNombreVisual("CORREO");
        tab_tabla3.getColumna("notificacion_yalcor").setNombreVisual("DESCRIPCION CAMBIO DE CORREO");
        tab_tabla3.getColumna("activo_yalcor ").setNombreVisual("ACTIVO");
        tab_tabla3.setLectura(true);
        tab_tabla3.dibujar();

        PanelTabla pat_panel3 = new PanelTabla();
        pat_panel3.setId("pat_panel3");
        pat_panel3.setPanelTabla(tab_tabla3);
        
        Imagen modalidadIma = new Imagen();
        modalidadIma.setValue("imagenes/logocabeceras.png");
        pat_panel1.setWidth("100%");
        pat_panel1.setHeader(modalidadIma);
       
        
        tab_tabulador.agregarTab("DATOS PERSONALES", pat_panel1);
        tab_tabulador.agregarTab("DIRECCION", pat_panel4);
        tab_tabulador.agregarTab("TELEFONO", pat_panel2);
        tab_tabulador.agregarTab("CORREO", pat_panel3);
        

        //Permite la dision de la pantalla
        Division div_division1 = new Division();
        div_division1.setId("div_division1");
        div_division1.dividir1(tab_tabulador);

        agregarComponente(div_division1);

    }

    @Override
    public void insertar() {
        tab_tabla1.insertar();
        insertar();
    }

    @Override
    public void guardar() {

    }

    @Override
    public void eliminar() {

    }

    public Tabla getTab_tabla1() {
        return tab_tabla1;
    }

    public void setTab_tabla1(Tabla tab_tabla1) {
        this.tab_tabla1 = tab_tabla1;
    }

    public Tabla getTab_tabla2() {
        return tab_tabla2;
    }

    public void setTab_tabla2(Tabla tab_tabla2) {
        this.tab_tabla2 = tab_tabla2;
    }

    public Tabla getTab_tabla3() {
        return tab_tabla3;
    }

    public void setTab_tabla3(Tabla tab_tabla3) {
        this.tab_tabla3 = tab_tabla3;
    }

    public Tabla getTab_tabla4() {
        return tab_tabla4;
    }

    public void setTab_tabla4(Tabla tab_tabla4) {
        this.tab_tabla4 = tab_tabla4;
    }

}

package paq_matricula;

import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_matricula.ejb.ServicioMatriculas;
import sistema.aplicacion.Pantalla;

public class PeriodoMatricula extends Pantalla {

    private Tabla tab_periodo_matricula = new Tabla();//instanciar tabla del framework
    private Combo com_periodo_academico = new Combo();
    private Boton bot_clean = new Boton();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioMatriculas ser_matricula = (ServicioMatriculas) utilitario.instanciarEJB(ServicioMatriculas.class);

    public PeriodoMatricula() {//constructor

        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura.getPeriodoAcademico("true"));
        agregarComponente(com_periodo_academico);
        bar_botones.agregarComponente(com_periodo_academico);
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");

        bot_clean.setIcon("ui-icon-cancel");
        bot_clean.setTitle("Limpiar");
        bot_clean.setMetodo("limpiar");
        bar_botones.agregarComponente(bot_clean);

        tab_periodo_matricula.setId("tab_periodo_matricula");// todo objeto instanciado poner id 
        tab_periodo_matricula.setTabla("yavirac_matri_periodo_matric", "ide_ymaper", 1);  // nombre de la base de datos ii la clave primaria
        tab_periodo_matricula.setCondicion("ide_ystpea=-1");
        tab_periodo_matricula.getColumna("aplica_toda_ymaper").setValorDefecto("true");
        tab_periodo_matricula.getColumna("aplica_toda_ymaper").setVisible(false);
        tab_periodo_matricula.getColumna("ide_ystcrr").setVisible(false);
        tab_periodo_matricula.getColumna("ide_ystnie").setVisible(false);
        tab_periodo_matricula.getColumna("ide_ystpea").setVisible(false);
        tab_periodo_matricula.getColumna("ide_ymatip").setCombo(ser_matricula.getTipoPeriodoMatricula());
        //*************************************************************************************************
        tab_periodo_matricula.getColumna("ide_ymaper").setNombreVisual("CÓDIGO");
        tab_periodo_matricula.getColumna("ide_ymatip").setNombreVisual("TIPO");
        tab_periodo_matricula.getColumna("fecha_inicio_ymaper").setNombreVisual("FECHA INICIO");
        tab_periodo_matricula.getColumna("fecha_final_ymaper").setNombreVisual("FECHA FINAL");
        tab_periodo_matricula.getColumna("observaciones_ymaper").setNombreVisual("OBSERVACIÓN");
        tab_periodo_matricula.getColumna("activo_ymaper").setNombreVisual("ACTIVO");
        tab_periodo_matricula.setHeader("PERIODO MATRÍCULA");
        tab_periodo_matricula.dibujar();//dibuja la tabla

        PanelTabla pa_periodo_matricula = new PanelTabla();//intanciamos el panel del framework
        pa_periodo_matricula.setId("pa_periodo_matricula");//nombre id
        pa_periodo_matricula.setPanelTabla(tab_periodo_matricula);//agregar a nuestra tabla el panel

        //instanciar una division del framework
        Division div_periodo_matricula = new Division();//instanciamos
        div_periodo_matricula.setId("div_periodo_matricula");//es un idientificador
        div_periodo_matricula.dividir1(pa_periodo_matricula);

        agregarComponente(div_periodo_matricula);//agregar componente
    }

    public void filtroComboPeriodoAcademnico() {

        tab_periodo_matricula.setCondicion("ide_ystpea=" + com_periodo_academico.getValue().toString());
        tab_periodo_matricula.ejecutarSql();
        utilitario.addUpdate("tab_pre_inscrip");

    }

    public void limpiar() {
        tab_periodo_matricula.limpiar();
        com_periodo_academico.setValue("null");
        utilitario.addUpdate("tab_periodo_matricula,com_periodo_academico");
    }

    @Override
    public void insertar() {
        if (com_periodo_academico.getValue() == null) {

            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea generar");
            return;
        } else {
            tab_periodo_matricula.insertar();
            tab_periodo_matricula.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
            utilitario.addUpdate("tab_periodo_matricula");
        }
    }

    @Override
    public void guardar() {
        tab_periodo_matricula.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_periodo_matricula.eliminar();
    }

    //generar geter and seter
    public Tabla getTab_periodo_matricula() {
        return tab_periodo_matricula;
    }

    public void setTab_periodo_matricula(Tabla tab_periodo_matricula) {
        this.tab_periodo_matricula = tab_periodo_matricula;
    }

}

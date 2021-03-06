package empresa;

public class Empleado {
    private int legajo;
    private String ayn;
    private Sector sector;
    
    /**
     * Constructor vacio para serializacion.
     * No utilizar.
     */
    public Empleado(){
        
    }
    
    public Empleado(int legajo, String ayn, Sector sector) {
        this.legajo = legajo;
        this.ayn = ayn;
        this.sector = sector;
    }
    
    /*
     * ***********************************************
     * Getters y setters agregados para serializacion.
     * ***********************************************
     */


    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public void setAyn(String ayn) {
        this.ayn = ayn;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }


    /*
     * ***********************************************
     */

    public int getLegajo() {
        return legajo;
    }

    public String getAyn() {
        return ayn;
    }

    public Sector getSector() {
        return sector;
    }
    
    public boolean autorizaOperacion(int codOpe){
        return sector.permiteOperar(codOpe);
    }
}


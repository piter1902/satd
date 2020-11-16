/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaterialP4.Ejercicio3;

/**
 *
 * @author goyo
 */
public class Ej3_PasajeVuelo implements java.io.Serializable {

    private String origen;
    private String destino;
    private String tipoTarifa;
    private String aerolinea;

    public Ej3_PasajeVuelo() {

    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getTipoTarifa() {
        return tipoTarifa;
    }

    public String getAerolinea() {
        return aerolinea;
    }
//metodos asignacion

    public void setOrigen(String o) {
        origen = o;
    }

    public void setDestino(String d) {
        destino = d;
    }

    public void setTipoTarifa(String tp) {
        tipoTarifa = tp;
    }

    public void setAerolinea(String a) {
        aerolinea = a;
    }
}

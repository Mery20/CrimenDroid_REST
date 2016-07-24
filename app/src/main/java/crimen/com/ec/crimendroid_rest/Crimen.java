package crimen.com.ec.crimendroid_rest;

import java.util.Date;

/**
 * Created by cesaralcivar on 19/7/16.
 */
public class Crimen {

    private int crimenId;
    private String titulo;
    private boolean resuelto;
    private Date fecha;

    public int getCrimenId() {
        return crimenId;
    }

    public void setCrimenId(int crimenId) {
        this.crimenId = crimenId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    @Override
    public String toString() {
        return "Crimen{" +
                "crimenId=" + crimenId +
                ", titulo='" + titulo + '\'' +
                ", resuelto=" + resuelto +
                '}';
    }
}

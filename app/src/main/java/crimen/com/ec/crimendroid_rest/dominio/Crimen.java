package crimen.com.ec.crimendroid_rest.dominio;

import java.util.Date;

/**
 * Created by cesaralcivar on 19/7/16.
 */
public class Crimen {

    private int crimenId;
    private String titulo;
    private boolean resuelto;
    private Date fecha;

    public Crimen(){
        fecha=new Date();
    }

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

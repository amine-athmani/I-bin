package dz.cdta.smartbin.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Tache {

    private Long idTask;
    private Date datedebutTask;
    private Date dateFinTask;
    private String discreptionTask;
    private int etatTache;

    public Tache(Long idTask, Date datedebutTask, Date dateFinTask, String discreptionTask, int etatTache) {
        this.idTask = idTask;
        this.datedebutTask = datedebutTask;
        this.dateFinTask = dateFinTask;
        this.discreptionTask = discreptionTask;
        this.etatTache = etatTache;
    }

    public Date getDatedebutTask() {
        return datedebutTask;
    }

    public void setDatedebutTask(Date datedebutTask) {
        this.datedebutTask = datedebutTask;
    }

    public Date getDateFinTask() {
        return dateFinTask;
    }

    public void setDateFinTask(Date dateFinTask) {
        this.dateFinTask = dateFinTask;
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }


    public String getDiscreptionTask() {
        return discreptionTask;
    }

    public void setDiscreptionTask(String discreptionTask) {
        this.discreptionTask = discreptionTask;
    }

    public int getEtatTache() {
        return etatTache;
    }

    public void setEtatTache(int etatTache) {
        this.etatTache = etatTache;
    }
}

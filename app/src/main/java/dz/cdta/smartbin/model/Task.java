package dz.cdta.smartbin.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.util.Log;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import dz.cdta.smartbin.data.SmartBinTypeConverters;


@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private Long idTask;

    @ColumnInfo(name = "date_debut")
    private Date datedebutTask;

    @ColumnInfo(name = "date_fin")
    private Date dateFinTask;

    @ColumnInfo(name = "description")
    private String descriptionTask;

    @ColumnInfo(name = "etat_tache")
    private int etatTache;
/*
    @ColumnInfo(name = "smart_bins")
    @TypeConverters(SmartBinTypeConverters.class)
    private List<SmartBin> smartBinList;
*/
    public Task(Long idTask, Date datedebutTask, Date dateFinTask, String descriptionTask, int etatTache) {
        this.idTask = idTask;
        this.datedebutTask = datedebutTask;
        this.dateFinTask = dateFinTask;
        this.descriptionTask = descriptionTask;
        this.etatTache = etatTache;
        //this.smartBinList = smartBinList;
    }

    public Task(Tache tache, List<SmartBin> smartBinList) {
        //this.smartBinList = smartBinList;
        this.idTask = tache.getIdTask();
        /*
        this.datedebutTask = tache.getDatedebutTask();
        this.dateFinTask = tache.getDateFinTask();
        */
        this.descriptionTask = tache.getDiscreptionTask();
        this.etatTache = tache.getEtatTache();
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
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

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public int getEtatTache() {
        return etatTache;
    }

    public void setEtatTache(int etatTache) {
        this.etatTache = etatTache;
    }
    /*
    public List<SmartBin> getSmartBinList() {
        return smartBinList;
    }

    public void setSmartBinList(List<SmartBin> smartBinList) {
        this.smartBinList = smartBinList;
    }
    */

}

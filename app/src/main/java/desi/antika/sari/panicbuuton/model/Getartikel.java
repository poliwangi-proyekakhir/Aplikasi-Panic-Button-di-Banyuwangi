package desi.antika.sari.panicbuuton.model;

import java.util.List;

public class Getartikel {
    String status,message;
    List<Artikel> data;

    public Getartikel(String status, String message, List<Artikel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Artikel> getData() {
        return data;
    }

    public void setData(List<Artikel> data) {
        this.data = data;
    }
}

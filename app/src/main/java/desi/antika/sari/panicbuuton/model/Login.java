package desi.antika.sari.panicbuuton.model;

public class Login {
    String status,message,id,nama,level;

    public Login(String status, String message, String id, String nama, String level) {
        this.status = status;
        this.message = message;
        this.id = id;
        this.nama = nama;
        this.level = level;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

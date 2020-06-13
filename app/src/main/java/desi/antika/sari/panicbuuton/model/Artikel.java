package desi.antika.sari.panicbuuton.model;

public class Artikel {
    String id_artikel,judul,isi,foto,tgl_dibuat,id_status_artikel,id_pengguna;

    public Artikel(String id_artikel, String judul, String isi, String foto, String tgl_dibuat, String id_status_artikel, String id_pengguna) {
        this.id_artikel = id_artikel;
        this.judul = judul;
        this.isi = isi;
        this.foto = foto;
        this.tgl_dibuat = tgl_dibuat;
        this.id_status_artikel = id_status_artikel;
        this.id_pengguna = id_pengguna;
    }

    public String getId_artikel() {
        return id_artikel;
    }

    public void setId_artikel(String id_artikel) {
        this.id_artikel = id_artikel;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTgl_dibuat() {
        return tgl_dibuat;
    }

    public void setTgl_dibuat(String tgl_dibuat) {
        this.tgl_dibuat = tgl_dibuat;
    }

    public String getId_status_artikel() {
        return id_status_artikel;
    }

    public void setId_status_artikel(String id_status_artikel) {
        this.id_status_artikel = id_status_artikel;
    }

    public String getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(String id_pengguna) {
        this.id_pengguna = id_pengguna;
    }
}

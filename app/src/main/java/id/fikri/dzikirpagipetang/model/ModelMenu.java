package id.fikri.dzikirpagipetang.model;

/**
 * Created by fikri on 20/01/17.
 */

public class ModelMenu {
    private String judul, jumlah;
    private int no;

    public ModelMenu(){}

    public ModelMenu(int no, String judul, String jumlah){
        this.no = no;
        this.judul = judul;
        this.jumlah = jumlah;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}

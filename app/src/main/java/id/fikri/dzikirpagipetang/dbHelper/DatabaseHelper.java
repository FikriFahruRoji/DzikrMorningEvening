package id.fikri.dzikirpagipetang.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by fikri on 20/12/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public String tableMorning = "morning";
    public String tableEvening = "evening";

    public String colMId = "morning_id";
    public String colMJudul = "morning_judul";
    public String colMJumlah = "morning_jumlah";
    public String colMDoa = "morning_arabic";
    public String colMTerjemah = "morning_terjemah";
    public String colMRiwayat = "morning_riwayat";
    public String colMManfaat = "morning_manfaat";
    public String colMMedia = "morning_media";

    public String colEId = "evening_id";
    public String colEJudul = "evening_judul";
    public String colEJumlah = "evening_jumlah_baca";
    public String colEDoa = "evening_arabic";
    public String colETerjemah = "evening_terjemah";
    public String colERiwayat = "evening_riwayat";
    public String colEManfaat = "evening_manfaat";
    public String colEMedia = "evening_media";

    private SQLiteDatabase db;

    public final String create_table_morning = "CREATE TABLE " + tableMorning + " ( " +
            colMId + " INT PRIMARY KEY, " +
            colMJudul + " TEXT, " +
            colMJumlah + " TEXT, " +
            colMDoa +" TEXT, " +
            colMTerjemah +" TEXT, " +
            colMRiwayat +" TEXT, " +
            colMManfaat +" TEXT, " +
            colMMedia +" INT);";

    public final String create_table_evening = "CREATE TABLE " + tableEvening + " ( " +
            colEId + " INT PRIMARY KEY, " +
            colEJudul + " TEXT, " +
            colEJumlah + " TEXT, " +
            colEDoa +" TEXT, " +
            colETerjemah +" TEXT, " +
            colERiwayat +" TEXT, " +
            colEManfaat +" TEXT, " +
            colEMedia +" INT);";

    public DatabaseHelper(Context context) {
        super(context, "dzikr.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_morning);
        db.execSQL(create_table_evening);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableMorning);
        db.execSQL("DROP TABLE IF EXISTS " + tableEvening);
        onCreate(db);
    }

    public boolean insert_table_morning(int id, String judul, String jumlah, String doa, String terjemah, String riwayat, String manfaat, int media) {
        db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(colMId, id);
        content_values.put(colMJudul, judul);
        content_values.put(colMJumlah, jumlah);
        content_values.put(colMDoa, doa);
        content_values.put(colMTerjemah, terjemah);
        content_values.put(colMRiwayat, riwayat);
        content_values.put(colMManfaat, manfaat);
        content_values.put(colMMedia, media);
        long result = db.insert(tableMorning, null, content_values);
        return result != -1;
    }

    public boolean insert_table_evening(int id, String judul, String jumlah, String doa, String terjemah, String riwayat, String manfaat, int media) {
        db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(colEId, id);
        content_values.put(colEJudul, judul);
        content_values.put(colEJumlah, jumlah);
        content_values.put(colEDoa, doa);
        content_values.put(colETerjemah, terjemah);
        content_values.put(colERiwayat, riwayat);
        content_values.put(colEManfaat, manfaat);
        content_values.put(colEMedia, media);
        long result = db.insert(tableEvening, null, content_values);
        return result != -1;
    }

    public Cursor select_menu_morning() {
        db = this.getWritableDatabase();
        Cursor transaction = db.rawQuery("SELECT " + colMId + ", " + colMJudul + ", " + colMJumlah + " FROM " + tableMorning + " ORDER BY " + colMId, null);
        return transaction;
    }

    public Cursor select_menu_evening() {
        db = this.getWritableDatabase();
        Cursor transaction = db.rawQuery("SELECT " + colEId + ", " + colEJudul + ", " + colEJumlah + " FROM " + tableEvening + " ORDER BY " + colEId, null);
        return transaction;
    }

    public Cursor select_morning(String id) {
        db = this.getWritableDatabase();
        Cursor transaction = db.rawQuery("SELECT * FROM " + tableMorning + " WHERE " + colMId + " = " + id , null);
        return transaction;
    }

    public Cursor select_evening(String id) {
        db = this.getWritableDatabase();
        Cursor transaction = db.rawQuery("SELECT * FROM " + tableEvening + " WHERE " + colEId + " = " + id , null);
        return transaction;
    }
}
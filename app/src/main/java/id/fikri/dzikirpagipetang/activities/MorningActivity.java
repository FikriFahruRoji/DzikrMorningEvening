package id.fikri.dzikirpagipetang.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import id.fikri.dzikirpagipetang.R;
import id.fikri.dzikirpagipetang.dbHelper.DatabaseHelper;

public class MorningActivity extends AppCompatActivity {

    public static int ID;

    private int fav;
    private DatabaseHelper myDb;
    private TextView txNo, txLatin, txDoa, txHaditsHead, txTerjemah, txTerjemahHead, txSyarah, txSyarahHead, txFootnote;
    private ImageView visibleHadits, visibleTerjemah, visibleSyarah;
    private Button mediaPlay, mediaNext, mediaPrev, mediaStop;
    private Toolbar toolbar;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

//        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
//        final CollapsingToolbarLayout collapsingtoolbarlayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//
//        appbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                if (state.name().equals("COLLAPSED")) {
//                    collapsingtoolbarlayout.setTitle("Hadits ke - " + Integer.toString(ID));
//                } else if (state.name().equals("EXPANDED")) {
//                    collapsingtoolbarlayout.setTitle("");
//                } else if (state.name().equals("IDLE")) {
//                    collapsingtoolbarlayout.setTitle("");
//                }
//            }
//        });

        txDoa = (TextView) findViewById(R.id.tv_doa);

        Typeface face = Typeface.createFromAsset(getAssets(), "font.otf");
        txDoa.setTypeface(face);

        myDb = new DatabaseHelper(this);
        Cursor menu = myDb.select_morning(String.valueOf(ID));
        if (menu.getCount() == 0) {
            return;
        }
        while (menu.moveToNext()) {
            txDoa.setText(menu.getString(0) + "\n" +
                    menu.getString(1) + "\n" +
                    menu.getString(2) + "\n" +
                    menu.getString(3) + "\n" +
                    menu.getString(4) + "\n" +
                    menu.getString(5) + "\n" +
                    menu.getString(6) + "\n" +
                    menu.getString(7));
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.release();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null) mediaPlayer.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}

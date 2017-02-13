package id.fikri.dzikirpagipetang.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import id.fikri.dzikirpagipetang.R;
import id.fikri.dzikirpagipetang.dbHelper.DatabaseHelper;
import id.fikri.dzikirpagipetang.fragment.EveningFragment;
import id.fikri.dzikirpagipetang.fragment.HomeFragment;
import id.fikri.dzikirpagipetang.fragment.MorningFragment;

public class MainActivity extends AppCompatActivity {

    private Drawer result = null;

    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        HomeFragment f = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();



        //Create the drawer
        result = new DrawerBuilder(this)
                //this layout have to contain child layouts
                .withRootView(R.id.drawer_container)
//                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.mipmap.ic_launcher).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.nav_dzikir_pagi).withIcon(R.mipmap.ic_launcher).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.nav_dzikir_petang).withIcon(R.mipmap.ic_launcher).withIdentifier(3)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            if (drawerItem.getIdentifier() == 1) {
                                HomeFragment f = new HomeFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
                                getSupportActionBar().setTitle(getString(R.string.nav_home));
                            } else if (drawerItem.getIdentifier() == 2) {
                                MorningFragment f = new MorningFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
                                getSupportActionBar().setTitle(getString(R.string.nav_dzikir_pagi));
                            } else if (drawerItem.getIdentifier() == 3) {
                                EveningFragment f = new EveningFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
                                getSupportActionBar().setTitle(getString(R.string.nav_dzikir_petang));
                            }
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        if (prefs.getBoolean("firstLaunch", true)) {
            prefs.edit().putBoolean("firstLaunch", false).commit();
            Resources res = getResources();
            int[] morning_id = {1,2,3,4,5,6,7,8,9,10};
            String[] morning_judul = {"judul1","judul2","judul3","judul4","judul5","judul6","judul7","judul8","judul9","judul10"};
            String[] morning_jumlah = {"jumlah1","jumlah2","jumlah3","jumlah4","jumlah5","jumlah6","jumlah7","jumlah8","jumlah9","jumlah10"};
            String[] morning_arabic = {"1","2","3","4","5","6","7","8","9","10"};
            String[] morning_terjemah = {"1","2","3","4","5","6","7","8","9","10"};
            String[] morning_riwayat = {"1","2","3","4","5","6","7","8","9","10"};
            String[] morning_manfaat = {"1","2","3","4","5","6","7","8","9","10"};
            int[] morning_media = {1,2,3,4,5,6,7,8,9,10};

            String[] evening_id = {"1","2","3","4","5","6","7","8","9","10"};
            String[] evening_judul = {"judul1","judul2","judul3","judul4","judul5","judul6","judul7","judul8","judul9","judul10"};
            String[] evening_jumlah = {"jumlah1","jumlah2","jumlah3","jumlah4","jumlah5","jumlah6","jumlah7","jumlah8","jumlah9","jumlah10"};
            String[] evening_arabic = {"1","2","3","4","5","6","7","8","9","10"};
            String[] evening_terjemah = {"1","2","3","4","5","6","7","8","9","10"};
            String[] evening_riwayat = {"1","2","3","4","5","6","7","8","9","10"};
            String[] evening_manfaat = {"1","2","3","4","5","6","7","8","9","10"};
            int[] evening_media = {1,2,3,4,5,6,7,8,9,10};


            for (int i = 0; i < morning_id.length; i++) {
                myDB.insert_table_morning(morning_id[i], morning_judul[i], morning_jumlah[i], morning_arabic[i], morning_terjemah[i], morning_riwayat[i], morning_manfaat[i], morning_media[i]);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}

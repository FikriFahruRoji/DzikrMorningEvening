package id.fikri.dzikirpagipetang.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HomeFragment f = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.app_name));

        //Create the drawer
        result = new DrawerBuilder(this)
                //this layout have to contain child layouts
                .withActivity(this)
//                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withFullscreen(true)
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
                                collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                            } else if (drawerItem.getIdentifier() == 2) {
                                MorningFragment f = new MorningFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
                                collapsingToolbarLayout.setTitle(getString(R.string.nav_dzikir_pagi));
                            } else if (drawerItem.getIdentifier() == 3) {
                                EveningFragment f = new EveningFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
                                collapsingToolbarLayout.setTitle(getString(R.string.nav_dzikir_petang));
                            }
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();


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

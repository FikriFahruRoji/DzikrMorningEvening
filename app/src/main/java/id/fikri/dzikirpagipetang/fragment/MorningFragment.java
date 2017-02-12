package id.fikri.dzikirpagipetang.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.fikri.dzikirpagipetang.R;
import id.fikri.dzikirpagipetang.adapter.MenuAdapter;
import id.fikri.dzikirpagipetang.dbHelper.DatabaseHelper;
import id.fikri.dzikirpagipetang.model.ModelMenu;


/**
 * A simple {@link Fragment} subclass.
 */
public class MorningFragment extends Fragment {

    private List<ModelMenu> menuList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuAdapter mAdapter;

    private DatabaseHelper myDB;

    public MorningFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_morning, container, false);
        // end onCreate
        return viewRoot;
    }

}

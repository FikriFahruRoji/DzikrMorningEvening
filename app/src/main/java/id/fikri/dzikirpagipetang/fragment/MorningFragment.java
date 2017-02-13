package id.fikri.dzikirpagipetang.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.fikri.dzikirpagipetang.R;
import id.fikri.dzikirpagipetang.activities.MainActivity;
import id.fikri.dzikirpagipetang.activities.MorningActivity;
import id.fikri.dzikirpagipetang.adapter.MenuAdapter;
import id.fikri.dzikirpagipetang.dbHelper.DatabaseHelper;
import id.fikri.dzikirpagipetang.model.ModelMenu;
import id.fikri.dzikirpagipetang.util.RecyclerTouchListener;


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

        myDB = new DatabaseHelper(getActivity());

        recyclerView = (RecyclerView) viewRoot.findViewById(R.id.recycler_view);
        mAdapter = new MenuAdapter(menuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getActivity(), MorningActivity.class);
                MorningActivity.ID = position + 1;
                startActivity(i);
            }
            @Override
            public void onLongClick(View view, int position) {}
        }));

        menuList.clear();
        Cursor menu = myDB.select_menu_morning();
        while (menu.moveToNext()) {
            ModelMenu mModelMenu = new ModelMenu(menu.getInt(0), menu.getString(1), menu.getString(2));
            menuList.add(mModelMenu);
        }
        mAdapter.notifyDataSetChanged();

        // end onCreate
        return viewRoot;
    }

}

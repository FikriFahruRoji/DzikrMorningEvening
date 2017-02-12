package id.fikri.dzikirpagipetang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.fikri.dzikirpagipetang.R;
import id.fikri.dzikirpagipetang.model.ModelMenu;


/**
 * Created by fikri on 20/01/17.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private List<ModelMenu> menuList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView no, judul, jumlah;

        public MyViewHolder(View view) {
            super(view);
            no = (TextView) view.findViewById(R.id.tv_no);
            judul = (TextView) view.findViewById(R.id.tv_judul);
            jumlah = (TextView) view.findViewById(R.id.tv_jumlah);
        }
    }


    public MenuAdapter(List<ModelMenu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelMenu menu = menuList.get(position);
        holder.no.setText(Integer.toString(menu.getNo()));
        holder.judul.setText(menu.getJudul());
        holder.jumlah.setText(menu.getJumlah());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
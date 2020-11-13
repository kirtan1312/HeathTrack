package com.example.CPS731;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<Calories> CalorieList;
    private Activity context;
    private RoomDB database;
    public MainAdapter(Activity context, List<Calories> CalorieList){
        this.context = context;
        this.CalorieList = CalorieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.db_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Calories data = CalorieList.get(position);
        database = RoomDB.getInstance(context);
        holder.textView.setText(data.getValue()+"");
        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calories d = CalorieList.get(holder.getAdapterPosition());
                final int sid = d.getId();
                String value = d.getValue();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                final EditText editText = dialog.findViewById(R.id.editext);
                Button btnUpdate = dialog.findViewById(R.id.btupdate);
                editText.setText((value) + "Calorjes");
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        String utext = editText.getText().toString().trim();
                        database.mainDAO().update(sid, (utext));
                        CalorieList.clear();
                        CalorieList.addAll(database.mainDAO().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calories d = CalorieList.get(holder.getAdapterPosition());
                database.mainDAO().delete(d);
                int position = holder.getAdapterPosition();
                CalorieList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,CalorieList.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return CalorieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.DBtextView);
            btEdit = itemView.findViewById(R.id.DBbtnEdit);
            btDelete = itemView.findViewById(R.id.DBbtnDelete);
        }
    }
}

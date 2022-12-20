package com.cieep.pmdm_c_08_retrofit_ejercicio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cieep.pmdm_c_08_retrofit_ejercicio.R;
import com.cieep.pmdm_c_08_retrofit_ejercicio.modelos.DataItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserVH> {

    private List<DataItem> objects;
    private int resource;
    private Context context;

    public UsersAdapter(List<DataItem> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(context).inflate(resource, null);
        userView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new UserVH(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        DataItem item = objects.get(position);

        holder.lblNombre.setText(item.getFirstName());
        holder.lblApellidos.setText(item.getLastName());
        holder.lblEmail.setText(item.getEmail());

        Picasso.get()
                .load(item.getAvatar())
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imgUser);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class UserVH extends RecyclerView.ViewHolder {

        ImageView imgUser;
        TextView lblNombre;
        TextView lblApellidos;
        TextView lblEmail;

        public UserVH(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgImagenUserCard);
            lblNombre = itemView.findViewById(R.id.lblNombreUserCard);
            lblApellidos = itemView.findViewById(R.id.lblApellidosUserCard);
            lblEmail = itemView.findViewById(R.id.lblEmailUserCard);

        }
    }
}

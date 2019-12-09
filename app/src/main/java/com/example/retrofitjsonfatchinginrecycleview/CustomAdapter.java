package com.example.retrofitjsonfatchinginrecycleview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {
    private Context context;
    private ArrayList<Hero> heroArrayList;

    public CustomAdapter(Context context, ArrayList<Hero> heroArrayList) {
        this.context = context;
        this.heroArrayList = heroArrayList;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView heroImage;
        TextView heroName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            heroImage = itemView.findViewById(R.id.heroIM);
            heroName = itemView.findViewById(R.id.heroNameTV);

        }
    }

    @NonNull
    @Override
    public CustomAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_marvel_hero,parent,false);


        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.viewHolder holder, final int position) {

        final Hero heroListPosition = heroArrayList.get(position);

        Picasso.get().load(heroListPosition.getImageurl()).into(holder.heroImage);
        holder.heroName.setText(heroListPosition.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,DisplayActivity.class);
                intent.putExtra("HeroData", heroListPosition);
                context.startActivity(intent);
                Toast.makeText(context, "position"+holder.heroName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroArrayList.size();
    }

}

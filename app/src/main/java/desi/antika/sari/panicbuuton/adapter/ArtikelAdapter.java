package desi.antika.sari.panicbuuton.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import desi.antika.sari.panicbuuton.R;
import desi.antika.sari.panicbuuton.activity.ArtikelActivity;
import desi.antika.sari.panicbuuton.model.Artikel;
import desi.antika.sari.panicbuuton.rest.Retroserver;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.BlogHolder> {
    private Context context;
    private List<Artikel> list;
    private Retroserver url;

    public ArtikelAdapter(Context context, List<Artikel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BlogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_artikel, parent, false);
        final BlogHolder myBlogHolder = new BlogHolder(view);

        return myBlogHolder;
    }

    @Override
    public void onBindViewHolder(final BlogHolder holder, final int position) {

//        holder.judul.setText(list.get(position).getJudul());
        Spanned html = Html.fromHtml(list.get(position).getIsi());
        String desc = String.valueOf(html);
        holder.deskripsi.setText(desc);
        //holder.deskripsi.setText(list.get(position).getDeskripsi());
        if (list.get(position).getFoto() != null) {
            Glide.with(context)
                    .load(url.url_img + "artikel/" + list.get(position).getFoto())
                    .into(holder.img);
        }
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(context, ArtikelActivity.class);
                bundle.putString("judul",list.get(position).getJudul());
                bundle.putString("deskripsi",list.get(position).getIsi());
                bundle.putString("foto",url.url_img + "/artikel/" + list.get(position).getFoto());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BlogHolder extends RecyclerView.ViewHolder {
        TextView judul, deskripsi;
        ImageView img;
        LinearLayout card;

        public BlogHolder(View itemView) {
            super(itemView);
            deskripsi = itemView.findViewById(R.id.txtIsi);
            img = itemView.findViewById(R.id.imgFoto);
            card = itemView.findViewById(R.id.layoutArtikel);

        }
    }

}

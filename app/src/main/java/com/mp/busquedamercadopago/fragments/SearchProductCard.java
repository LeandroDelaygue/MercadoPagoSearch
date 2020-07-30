package com.mp.busquedamercadopago.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mp.busquedamercadopago.R;
import com.mp.busquedamercadopago.activities.ProductDetail;
import com.mp.busquedamercadopago.classes.Constants;
import com.mp.busquedamercadopago.entities.Product;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



/**
 * Created by Leandro
 */

public class SearchProductCard extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    static TextView tv_title;
    static TextView tv_subtitle;
    static TextView tv_detail;
    List<Product> products;
    static ImageView imv_image;
    Activity actividad;
    static CardView cv_search;

    public static class SearchHolder extends RecyclerView.ViewHolder {


        public SearchHolder(View itemView) {
            super(itemView);

            cv_search=  itemView.findViewById(R.id.cv_search);
            tv_title =  itemView.findViewById(R.id.tv_title);
            tv_subtitle =   itemView.findViewById(R.id.tv_subtitle);
            tv_detail =  itemView.findViewById(R.id.tv_detail);
            imv_image =  itemView.findViewById(R.id.imv_prod_cat);

        }
    }


    public SearchProductCard(List<Product> products, Activity actividad) {


        this.actividad = actividad;
        this.products = products;


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout_search, parent, false);


        return new SearchHolder(view);

    }


    @Override
    public int getItemViewType(int position) {
        return position;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int listPosition) {

        final Product product = this.products.get(listPosition);


        tv_detail.setTypeface(com.mp.busquedamercadopago.activities.Activity.typeface_regular);
        tv_subtitle.setTypeface(com.mp.busquedamercadopago.activities.Activity.typeface_regular);
        tv_title.setTypeface(com.mp.busquedamercadopago.activities.Activity.typeface_roboto_bold);


        try {

            tv_title.setText(product.getName());
            tv_subtitle.setText(product.getId());
            tv_detail.setText(product.getDomain_id());

            if (!product.getPictures().isEmpty()) {
                Glide.with(actividad.getApplicationContext()).load(product.getPictures().get(0).url).into(imv_image);
            }
            cv_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intento = new Intent(actividad, ProductDetail.class);
                    intento.putExtra(Constants.SER_PRODUCT, product);
                    actividad.startActivity(intento);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }


    }


    @Override
    public int getItemCount() {
        return products.size();
    }

}

package com.mp.busquedamercadopago.activities;


import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mp.busquedamercadopago.R;
import com.mp.busquedamercadopago.classes.Constants;

import com.mp.busquedamercadopago.entities.Category;
import com.mp.busquedamercadopago.entities.Product;


/**
 * Created by Leandro on 29/07/2020.
 */

public class ProductDetail extends Activity {


    private TextView tv_title, tv_subtitle, tv_detail;
    private GridView gvwImages;
    private LinearLayout lnimage;
    Product product = null;
    Category category = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);


        tv_title = findViewById(R.id.tv_title);
        tv_subtitle = findViewById(R.id.tv_subtitle);
        tv_detail = findViewById(R.id.tv_detail);
        gvwImages = findViewById(R.id.gvwImages);
        lnimage = findViewById(R.id.lnimage);

        tv_detail.setTypeface(typeface_regular);
        tv_subtitle.setTypeface(typeface_regular);
        tv_title.setTypeface(typeface_roboto_bold);


        if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable(Constants.SER_PRODUCT) != null) {

            try {

                product = (Product) getIntent().getExtras().getSerializable(Constants.SER_PRODUCT); //


                tv_title.setText(product.getName());


                tv_subtitle.setText(product.getDomain_id());

                StringBuilder sbl = new StringBuilder();
                sbl.append(getString(R.string.ficha_tecnica));
                sbl.append("\n");
                sbl.append("\n");
                for (Product.Atributes atribute : product.getAttributes()) {
                    sbl.append(atribute.name);
                    sbl.append("\n");
                }
                tv_detail.setText(sbl.toString());
                gvwImages.setAdapter(new ImageAdapter(this));

            } catch (Exception e) {


            }
        } else if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable(Constants.SER_CATEGORY) != null) {

            try {

                category = (Category) getIntent().getExtras().getSerializable(Constants.SER_CATEGORY); //
                lnimage.setVisibility(View.GONE);

                tv_title.setText(category.getTitle());
                tv_subtitle.setText(category.getId());

                StringBuilder sbl = new StringBuilder();
                if (null!=category.getInstallments()) {

                    sbl.append(getString(R.string.cuotas_disponibles));
                    sbl.append("\n");
                    sbl.append("\n");

                    sbl.append(getString(R.string.cantidad));
                    sbl.append("" + category.getInstallments().quantity);
                    sbl.append("\n");
                    sbl.append(getString(R.string.monto));
                    sbl.append("" + category.getInstallments().amount);
                    sbl.append("\n");
                    sbl.append("\n");

                }
                if (null!=category.getAddress()) {

                    sbl.append(getString(R.string.info_vendedor));
                    sbl.append("\n");
                    sbl.append("\n");

                    sbl.append(getString(R.string.provincia));
                    sbl.append("" + category.getAddress().state_name);
                    sbl.append("\n");
                    sbl.append(getString(R.string.ciudad));
                    sbl.append("" + category.getAddress().city_name);
                    sbl.append("\n");

                }
                tv_detail.setText(sbl.toString());


            } catch (Exception e) {


            }
        }


    }


    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return product.getPictures().size();
        }

        public Product.Pictures getItem(int position) {
            return product.getPictures().get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.adapter_image, null);

            try {

                ImageView imageView = v.findViewById(R.id.imageGrid);


                Glide.with(mContext)
                        .load(getItem(position).url)
                        .dontAnimate().into(imageView);


            } catch (Exception e) {

            }
            return v;
        }
    }

    public void salir(View v) {
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();

        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

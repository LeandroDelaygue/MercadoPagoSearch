package com.mp.busquedamercadopago.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mp.busquedamercadopago.R;
import com.mp.busquedamercadopago.entities.Category;
import com.mp.busquedamercadopago.interfaces.IIntegrationMercadoPago;
import com.mp.busquedamercadopago.services.MercadoPagoServices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mp.busquedamercadopago.activities.Activity.typeface_roboto_bold;

/**
 * Created by Leandro on 30/07/2020.
 */

public class SearchCategoryFragment extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ProgressDialog progressDialog;
    EditText edtSearch;
    LinearLayout lnNoProducts;

    boolean searchByName = false;
    ImageButton imbSearchCategoryCancel;

    public SearchCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        ImageButton imbSearch = root.findViewById(R.id.imbSearch);
        imbSearchCategoryCancel = root.findViewById(R.id.imbSearchCancel);
        TextView tvNoProducts = root.findViewById(R.id.tvNoProducts);
        lnNoProducts = root.findViewById(R.id.lnNoProducts);
        edtSearch = root.findViewById(R.id.edtSearch);

        recyclerView =  root.findViewById(R.id.rcv_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        edtSearch.setHint("Buscar Por Categoria Ej.MLA1055");
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search();
                    return true;
                }
                return false;
            }
        });

        imbSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });

        imbSearchCategoryCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearSearch();
            }
        });



        tvNoProducts.setTypeface(typeface_roboto_bold);


        hideContainer();


        return root;
    }


    private void clearSearch() {
        edtSearch.setText("");
        hideContainer();
    }

    private boolean isValid() {

        boolean isValid = false;
        //Agregar todas las validaciones del req. Puede devolver un string en caso de que sea más de una validación.
        //Y si devuelve "" es válido, sino mostrar  el string
        if (!edtSearch.getText().equals("")) {
            isValid = true;
        }
        return isValid;

    }

    private void showContainer() {
        recyclerView.setVisibility(View.VISIBLE);
        lnNoProducts.setVisibility(View.GONE);
        imbSearchCategoryCancel.setVisibility(View.VISIBLE);
    }

    private void hideContainer() {
        recyclerView.setVisibility(View.GONE);
        lnNoProducts.setVisibility(View.VISIBLE);
        imbSearchCategoryCancel.setVisibility(View.GONE);
    }

    private void search() {


        if (isValid()) {


            //llamar al servicio y completar
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.buscando_productos,edtSearch.getText().toString()));
            progressDialog.show();
            progressDialog.setIndeterminate(true);


            MercadoPagoServices mpService = new MercadoPagoServices();
            IIntegrationMercadoPago integrationMP = mpService.CustomMPServices().create(IIntegrationMercadoPago.class);
            Call<JsonObject> callSearch = integrationMP.getCategories(edtSearch.getText().toString());

            try {

                callSearch.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {



                        try {
                            JSONObject jsonObject = new JSONObject(String.valueOf(response.body()));

                            JSONArray jsonArray =  jsonObject.getJSONArray("results");
                            List<Category> resultsSearch =   new Gson().fromJson(jsonArray.toString(), new TypeToken<List<Category>>(){}.getType());

                            cerrarProgressDialog();

                            if (resultsSearch.isEmpty()){
                                mostrarDialogError(getString(R.string.sin_resultados)+" "+edtSearch.getText().toString());
                            }else{
                                showContainer();

                                adapter = new SearchCategoryCard(resultsSearch, getActivity());

                                recyclerView.setAdapter(adapter);

                            }


                        } catch (Exception e) {
                            cerrarProgressDialog();
                            mostrarDialogError(getString(R.string.error_inesperado));
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                        cerrarProgressDialog();

                        mostrarDialogError(getString(R.string.sin_conexion));

                    }
                });

            } catch (Exception e) {
                cerrarProgressDialog();
            }



        } else {
            hideContainer();
            mostrarDialogError("Debe inngresar ");
        }

    }

    private void cerrarProgressDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    private void mostrarDialogError(String mensaje) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Ups!");
        alertDialog.setMessage(mensaje);
       // alertDialog.setIcon();

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });

        alertDialog.show();

    }


}

package desi.antika.sari.panicbuuton.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import desi.antika.sari.panicbuuton.R;
import desi.antika.sari.panicbuuton.adapter.ArtikelAdapter;
import desi.antika.sari.panicbuuton.model.Artikel;
import desi.antika.sari.panicbuuton.model.Getartikel;
import desi.antika.sari.panicbuuton.other.SessionManager;
import desi.antika.sari.panicbuuton.rest.ApiRequest;
import desi.antika.sari.panicbuuton.rest.Retroserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {
    private RecyclerView card;
    private LinearLayoutManager manager;
    private ArtikelAdapter adapter;
    private List<Artikel> list;
    private SessionManager session;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        session = new SessionManager(getContext());
        card=(RecyclerView)root.findViewById(R.id.recyclerview);
        getAll();
        return root;
    }

    // menganbil data artikel dari server
    private void getAll(){
        list = new ArrayList<>();
        ApiRequest apiRequest = Retroserver.getClient().create(ApiRequest.class);
        Call<Getartikel> ArtikelCall = apiRequest.getArtikel();
        ArtikelCall.enqueue(new Callback<Getartikel>() {
            @Override
            public void onResponse(Call<Getartikel> call, Response<Getartikel> response) {
               // Log.d(TAG,"data: "+response.body());
                if(response.isSuccessful()){
                    list = response.body().getData();
                    manager = new LinearLayoutManager(getActivity());
                    card.setLayoutManager(manager);
                    adapter = new ArtikelAdapter(getActivity(), list);
                    card.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(), "Jaringan bermasalah!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Getartikel> call, Throwable t) {
                Log.d(TAG,"onfailure");
            }
        });
    }


}

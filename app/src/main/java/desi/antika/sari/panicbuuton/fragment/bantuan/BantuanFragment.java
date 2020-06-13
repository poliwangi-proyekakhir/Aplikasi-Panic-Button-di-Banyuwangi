package desi.antika.sari.panicbuuton.fragment.bantuan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import desi.antika.sari.panicbuuton.R;

public class BantuanFragment extends Fragment {

    private BantuanViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(BantuanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bantuan, container, false);

        return root;
    }
}

package desi.antika.sari.panicbuuton.fragment.pengaturan;

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

public class PengaturanFragment extends Fragment {

    private PengaturanViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(PengaturanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pengaturan, container, false);

        return root;
    }
}

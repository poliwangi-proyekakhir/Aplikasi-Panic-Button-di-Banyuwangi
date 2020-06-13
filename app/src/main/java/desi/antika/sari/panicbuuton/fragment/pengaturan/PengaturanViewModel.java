package desi.antika.sari.panicbuuton.fragment.pengaturan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PengaturanViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PengaturanViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is pengaturan fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
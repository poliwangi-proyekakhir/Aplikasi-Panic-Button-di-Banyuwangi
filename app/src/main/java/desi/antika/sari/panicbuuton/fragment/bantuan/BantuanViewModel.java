package desi.antika.sari.panicbuuton.fragment.bantuan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BantuanViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BantuanViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bantuan fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
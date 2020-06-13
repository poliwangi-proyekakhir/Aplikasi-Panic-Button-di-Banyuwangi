package desi.antika.sari.panicbuuton.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import desi.antika.sari.panicbuuton.R;

public class ArtikelActivity extends AppCompatActivity {
    private TextView judul;
    private WebView deskripsi;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        judul =(TextView) findViewById(R.id.txtJudul);
        deskripsi =(WebView) findViewById(R.id.txtDeskripsi);
        img =(ImageView) findViewById(R.id.imgFoto);

        judul.setText(getIntent().getStringExtra("judul"));
        String myHtmlString = "<html><body>";
        String HtmlString =  "</body></html>";
        String content_view = myHtmlString+getIntent().getStringExtra("deskripsi")+HtmlString;
        deskripsi.loadData(content_view, "text/html", null);
        deskripsi.getSettings();
        deskripsi.setBackgroundColor(0x00000000);

        Glide.with(this)
                .load(String.valueOf(getIntent().getExtras().get("foto")))
                .into(img);
    }
}

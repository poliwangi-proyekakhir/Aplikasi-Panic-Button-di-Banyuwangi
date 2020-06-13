package desi.antika.sari.panicbuuton;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import desi.antika.sari.panicbuuton.other.GlobalUtil;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import desi.antika.sari.panicbuuton.model.Hasil;
import desi.antika.sari.panicbuuton.rest.ApiRequest;
import desi.antika.sari.panicbuuton.rest.Retroserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private Button browser,register;
    private EditText nik,nama,alamat,telpon,tglLahir,tempatLahir,username,passsword,konfirmasi;
    private TextView txtFoto;
    private ImageView imgKtp;
    private String base64ktp = "";
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private final int GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        requestMultiplePermissions();

        nik=(EditText)findViewById(R.id.fieldNik);
        nama=(EditText)findViewById(R.id.fieldNama);
        alamat=(EditText)findViewById(R.id.fieldAlamat);
        telpon=(EditText)findViewById(R.id.fieldTelpon);
        tglLahir=(EditText)findViewById(R.id.fieldTglLahir);
        tempatLahir=(EditText)findViewById(R.id.fieldTempatLahir);
        username=(EditText)findViewById(R.id.fieldUsername);
        passsword=(EditText)findViewById(R.id.fieldPassword);
        konfirmasi=(EditText)findViewById(R.id.fieldKorfirmasi);
        imgKtp=(ImageView)findViewById(R.id.fotoKtp);
        txtFoto=(TextView) findViewById(R.id.txtKtp);
        browser=(Button)findViewById(R.id.btnBrowser);
        register=(Button)findViewById(R.id.btnRegister);

        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    register.setEnabled(true);
                    return;
                }else{
                    postRegister();
                }
            }
        });
        tglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

    }

    //memanggil dialog calender
    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tglLahir.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    // memanggil gambar gallery
    private void openGallery() {
       /* Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);*/

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    // menampilkan gambar ke imageview
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {

            return;
        }

        if (requestCode == GALLERY) {
            if (data != null) {
                try {
                    // menampilkan gambar
                    imageUri = data.getData();
                    Bitmap imgProfile = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imgKtp.setImageBitmap(imgProfile);
                    // merubah img bitmap ke basestring 64
                    base64ktp       = GlobalUtil.getStringImage(imgProfile);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public String getPath(Uri uri)
    {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    // validasi untuk field harus diisi
        public boolean validate () {
            boolean valid = true;

            String NIK = nik.getText().toString();
            String Nama = nama.getText().toString();
            String Alamat = alamat.getText().toString();
            String Telpon = telpon.getText().toString();
            String TglLahir = tglLahir.getText().toString();

            if (NIK.isEmpty()) {
                nik.setError("NIK harus diisi");
                valid = false;
            } else {
                nik.setError(null);
            }

            if (Nama.isEmpty()) {
                nama.setError("Nama harus diisi");
                valid = false;
            } else {
                nama.setError(null);
            }
            if (Alamat.isEmpty()) {
                alamat.setError("Alamat harus diisi");
                valid = false;
            } else {
                alamat.setError(null);
            }

            if (Telpon.isEmpty()) {
                telpon.setError("Telpon harus diisi");
                valid = false;
            } else {
                telpon.setError(null);
            }
            if (TglLahir.isEmpty()) {
                tglLahir.setError("Tanggal Lahir harus diisi");
                valid = false;
            } else {
                tglLahir.setError(null);
            }

            return valid;
        }

        private void postRegister () {
            String user = username.getText().toString();
            String pass = passsword.getText().toString();
            String konfirm = konfirmasi.getText().toString();
            String NIK = nik.getText().toString();
            String Nama = nama.getText().toString();
            String Alamat = alamat.getText().toString();
            String Telpon = telpon.getText().toString();
            String TempatLahir = tempatLahir.getText().toString();
            String TglLahir = tglLahir.getText().toString();

            ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
            Call<Hasil> get = api.postRegister(user, pass,konfirm, NIK, Nama, Alamat, Telpon, TempatLahir, TglLahir, base64ktp);
            get.enqueue(new Callback<Hasil>() {
                @Override
                public void onResponse(Call<Hasil> call, Response<Hasil> response) {
                    if (response.isSuccessful()) {
                        String status = response.body().getStatus();
                        String pesan = response.body().getMessage();
                        switch (status) {
                            case "success":
                                Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                finish();
                                break;
                            case "failed":
                                Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                String data = "tidakada";
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Jaringan bermasalah!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Hasil> call, Throwable t) {
                    Log.d("RETRO", "FAILED : respon gagal");
                }
            });
        }

        private void requestMultiplePermissions() {
            Dexter.withActivity(this)
                    .withPermissions(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            // check if all permissions are granted
                            if (report.areAllPermissionsGranted()) {
                               // Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                            }
                            // check for permanent denial of any permission
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                // show alert dialog navigating to Settings

                            }
                        }
                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).
                    withErrorListener(new PermissionRequestErrorListener() {
                        @Override
                        public void onError(DexterError error) {
                            Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .onSameThread()
                    .check();
        }



}


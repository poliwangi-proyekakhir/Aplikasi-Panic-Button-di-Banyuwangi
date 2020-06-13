package desi.antika.sari.panicbuuton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaCas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import desi.antika.sari.panicbuuton.model.Login;
import desi.antika.sari.panicbuuton.other.SessionManager;
import desi.antika.sari.panicbuuton.rest.ApiRequest;
import desi.antika.sari.panicbuuton.rest.Retroserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView register;
    private Button login;
    private EditText username,password;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());
        register=(TextView)findViewById(R.id.txtRegister);
        login=(Button) findViewById(R.id.btnLogin);
        username=(EditText)findViewById(R.id.Username);
        password=(EditText)findViewById(R.id.Password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    login.setEnabled(true);
                    return;
                }else{
                    cekLogin();
                }
               /* Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);*/
            }
        });
    }

    // validasi field yg belum diisis
    public boolean validate() {
        boolean valid = true;

        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (user.isEmpty()) {
            username.setError("username harus diisi");
            valid = false;
        } else {
            username.setError(null);
        }

        if (pass.isEmpty()) {
            password.setError("password harus diisi");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    // cek login konek ke server
    private void cekLogin(){
       String user = username.getText().toString();
       String pass = password.getText().toString();
        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
        Call<Login> get = api.postLogin(user,pass);
        get.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.isSuccessful()) {
                    String status = response.body().getStatus();
                    String pesan = response.body().getMessage();
                    switch (status) {
                        case "success":
                            String id = response.body().getId();
                            String nama = response.body().getNama();
                            String level = response.body().getStatus();
                            session.createSession(id,nama,level);
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                            break;
                        case "failed":
                            Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            String data = "tidakada";
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Jaringan bermasalah!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");
            }
        });

    }
}

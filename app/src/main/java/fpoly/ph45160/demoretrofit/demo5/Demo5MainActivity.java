package fpoly.ph45160.demoretrofit.demo5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fpoly.ph45160.demoretrofit.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demo5MainActivity extends AppCompatActivity {

    EditText txt1, txt2, txt3;
    TextView tvKQ;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5_main);

        txt1 = findViewById(R.id.demo51Txt1);
        txt2 = findViewById(R.id.demo51Txt2);
        txt3 = findViewById(R.id.demo51Txt3);
        tvKQ = findViewById(R.id.demo51KQ);
        btn1 = findViewById(R.id.demo51Btn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData(txt1, txt2, txt3, tvKQ);
            }
        });
    }

    private void  insertData(EditText txt1, EditText txt2, EditText txt3, TextView tvKQ) {
        SanPham s = new SanPham(txt1.getText().toString(),
                txt2.getText().toString(),
                txt3.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4/project/demoRetrofit/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceInsertSanPham insertSanPham
                = retrofit.create(InterfaceInsertSanPham.class);
        Call<SvrResponseSanPham> call
                = insertSanPham.insertSanPham(s.getMaSP(), s.getTenSP(), s.getMoTa());

        call.enqueue(new Callback<SvrResponseSanPham>() {
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res = response.body();
                tvKQ.setText(res.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });

    }
}
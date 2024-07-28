package fpoly.ph45160.demoretrofit.demo5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fpoly.ph45160.demoretrofit.R;
import fpoly.ph45160.demoretrofit.demo5.delete.InterfaceDelete;
import fpoly.ph45160.demoretrofit.demo5.select.InterfaceSelect;
import fpoly.ph45160.demoretrofit.demo5.select.SvrResponseSelect;
import fpoly.ph45160.demoretrofit.demo5.update.InterfaceUpdate;
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
//                insertData(txt1, txt2, txt3, tvKQ);
                selectData();
//                deleteData(txt1);
//                updateData(txt1, txt2, txt3, tvKQ);

            }
        });
    }

    private void  insertData(EditText txt1, EditText txt2, EditText txt3, TextView tvKQ) {
        SanPham s = new SanPham(txt1.getText().toString(),
                txt2.getText().toString(),
                txt3.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2/project/demoRetrofit/")
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

    private void  updateData(EditText txt1, EditText txt2, EditText txt3, TextView tvKQ) {
        SanPham s = new SanPham(txt1.getText().toString(),
                txt2.getText().toString(),
                txt3.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2/project/demoRetrofit/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceUpdate updateSanPham
                = retrofit.create(InterfaceUpdate.class);
        Call<SvrResponseSanPham> call
                = updateSanPham.updateSanPham(s.getMaSP(), s.getTenSP(), s.getMoTa());

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

    private void  deleteData(EditText txt1) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2/project/demoRetrofit/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceDelete deleteSanPham
                = retrofit.create(InterfaceDelete.class);
        Call<SvrResponseSanPham> call
                = deleteSanPham.deleteSanPham(txt1.getText().toString());

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

    String strKQ ="";
    List<SanPham> ls;
    private  void selectData() {
        strKQ = "";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2/project/demoRetrofit/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceSelect interfaceSelect = retrofit.create(InterfaceSelect.class);
        Call<SvrResponseSelect> call = interfaceSelect.selectSanPham();
        call.enqueue(new Callback<SvrResponseSelect>() {
            @Override
            public void onResponse(Call<SvrResponseSelect> call, Response<SvrResponseSelect> response) {
                SvrResponseSelect res = response.body();
                 ls = new ArrayList<>(Arrays.asList(res.getProducts()));
                 for (SanPham p : ls) {
                     strKQ+="MaSP: " + p.getMaSP()+"; TenSp:" + p.getTenSP() + "; MoTa: " + p.getMoTa() + "\n";
                 }
                 tvKQ.setText(strKQ);
            }

            @Override
            public void onFailure(Call<SvrResponseSelect> call, Throwable throwable) {
                tvKQ.setText(throwable.getMessage());
            }
        });


    }
}
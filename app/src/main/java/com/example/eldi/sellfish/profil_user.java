package com.example.eldi.sellfish;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class profil_user extends AppCompatActivity {
    public static final String updateUser ="http://192.168.43.241/sellfish/user.php?apicall=update_user";
    EditText etNama,etUsername,etPassword,etEmail,etNoKtp;
    String nama,username,password,email,no_ktp,user_id;
    Button simpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_user);

        etNama=(EditText)findViewById(R.id.etNama);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etNoKtp=(EditText)findViewById(R.id.etNoKtp);
        simpan=(Button)findViewById(R.id.btnSimpan);

        user_id=getIntent().getStringExtra("user_id");
        nama=getIntent().getStringExtra("nama");
        username=getIntent().getStringExtra("username");
        password=getIntent().getStringExtra("password");
        email=getIntent().getStringExtra("email");
        no_ktp=getIntent().getStringExtra("no_ktp");




        etNama.setText(nama);
        etUsername.setText(username);
        etPassword.setText(password);
        etEmail.setText(email);
        etNoKtp.setText(no_ktp);

simpan.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        updateUser();
    }
});

    }


    public void updateUser(){
        nama = etNama.getText().toString().trim();
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        no_ktp = etNoKtp.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, updateUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            if (status) {
                                //JSONObject user = jObj.getJSONObject("user");
                                Toast.makeText(getApplicationContext(), "update data sukses", Toast.LENGTH_SHORT).show();
                                onBackPressed();


                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }



                       /* if(response.trim().equals("success")){
                            openProfile();
                        }else{
                            Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        }*/

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(profil_user.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id",user_id);
                map.put("nama", nama);
                map.put("username", username);
                map.put("password", password);
                map.put("email", email);
                map.put("no_ktp", no_ktp);
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
       /* RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); */
    }


}

package com.example.eldi.sellfish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity {

    String nama, username, password, email, noKtp;
    EditText etNama, etUserName, etPassword, etEmail, etKtp;
    public static final String registerURL = "http://192.168.43.241/sellfish/register.php"; //local 10.0.3.2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNama = (EditText) findViewById(R.id.etNama);
        etUserName = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etKtp = (EditText) findViewById(R.id.etKtp);
    }

    private void userRegister() {
        nama = etNama.getText().toString().trim();
        username = etUserName.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        noKtp = etKtp.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, registerURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            if (status) {
                                //JSONObject user = jObj.getJSONObject("user");
                                Toast.makeText(getApplicationContext(), "Registrasi sukses", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);

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
                        Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("nama", nama);
                map.put("username", username);
                map.put("password", password);
                map.put("email", email);
                map.put("no_ktp", noKtp);
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
       /* RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); */
    }

    public void register(View view) {
        userRegister();
    }
}



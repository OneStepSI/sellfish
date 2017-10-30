package com.example.eldi.sellfish;

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

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etUserName,etPassword;
    public String username ,password ,email,user_id;
    int level;
    public static final String loginURL ="http://192.168.43.241/sellfish/login.php";//"http://192.168.43.241/sellfish/login.php"; //local 10.0.3.2
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName = (EditText)findViewById(R.id.etUsername);
        etPassword= (EditText)findViewById(R.id.etPassword);
     /*   etPassword.setKeyListener(null);
        etPassword.setCursorVisible(false);
        etPassword.setPressed(false);
        etPassword.setFocusable(false);*/

      /*  btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        }); */


    }

    private void userLogin() {
        username = etUserName.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            if (!error) {
                                //JSONObject user = jObj.getJSONObject("user");
                                user_id = jObj.getString("id_user");
                                username = jObj.getString("username").trim();
                                email = jObj.getString("email").trim();
                                level = jObj.getInt("level");

                                if (level == 1) {
                                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                    intent.putExtra("user_id", user_id);
                                    intent.putExtra("username", username);
                                    intent.putExtra("email", email);
                                    startActivity(intent);
                                } else if (level == 2) {
                                    Intent intent = new Intent(LoginActivity.this, PembeliActivity.class);
                                    intent.putExtra("user_id", user_id);
                                    intent.putExtra("username", username);
                                    intent.putExtra("email", email);
                                    startActivity(intent);
                                } else if (level == 3) {
                                    Intent intent = new Intent(LoginActivity.this, PenjualActivity.class);
                                    intent.putExtra("user_id", user_id);
                                    intent.putExtra("username", username);
                                    intent.putExtra("email", email);
                                    startActivity(intent);
                                } else if (level == 4) {
                                    Intent intent = new Intent(LoginActivity.this, SupirActivity.class);
                                    intent.putExtra("user_id", user_id);
                                    intent.putExtra("username", username);
                                    intent.putExtra("email", email);
                                    startActivity(intent);
                                }


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
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_USERNAME, username);
                map.put(KEY_PASSWORD, password);
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
       /* RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); */
    }

    public void login(View view){
        userLogin();
    }
}

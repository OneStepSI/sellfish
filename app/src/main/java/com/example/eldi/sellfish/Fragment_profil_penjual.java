package com.example.eldi.sellfish;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

/**
 * Created by eldi on 18/10/2017.
 */
public class Fragment_profil_penjual extends Fragment {
    public static final String getUserProfil ="http://192.168.43.241/sellfish/user.php?apicall=get_user_by_id";
    private TextView username_penjual, email_penjual;
    int id;

    String nama,username,password,email,no_ktp,user_id;
    Button tentang,profil;

    public Fragment_profil_penjual() {

    }

    public static Fragment_profil_penjual newInstance() {
        Fragment_profil_penjual fragment = new Fragment_profil_penjual();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil_penjual, container, false);
        username_penjual = (TextView) view.findViewById(R.id.username_penjual);
        email_penjual=(TextView) view.findViewById(R.id.email_penjual);
        tentang=(Button)view.findViewById(R.id.about);
        profil=(Button)view.findViewById(R.id.profil);

        String txtUsername = getArguments().getString("username");
        String txtEmail = getArguments().getString("email");
        id= Integer.parseInt(getArguments().getString("id"));
        username_penjual.setText(txtUsername);
        email_penjual.setText(txtEmail);
        // Inflate the layout for this

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProfil();
            }
        });

        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),about.class);
                startActivity(i);
            }
        });

        return view;

        //return inflater.inflate(R.layout.fragment_profil_penjual, container, false);
    }
    public void getProfil(){
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching data...", false, false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getUserProfil,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            if (!error) {
                                loading.dismiss();
                                //JSONObject user = jObj.getJSONObject("user");
                               // loading.dismiss();
                                user_id = jObj.getString("id_user");
                                nama = jObj.getString("nama");
                                username = jObj.getString("username").trim();
                                password=jObj.getString("password");
                                email = jObj.getString("email").trim();
                                no_ktp=jObj.getString("no_ktp");

                                Intent i = new Intent(getActivity(),profil_user.class);
                                i.putExtra("user_id", user_id);
                                i.putExtra("nama",nama);
                                i.putExtra("username",username );
                                i.putExtra("password",password);
                                i.putExtra("email", email);
                                i.putExtra("no_ktp",no_ktp);




                                startActivity(i);


                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getActivity(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id", String.valueOf(id));
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
       /* RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); */
    }
}


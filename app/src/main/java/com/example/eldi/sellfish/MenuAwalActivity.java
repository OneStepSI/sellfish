package com.example.eldi.sellfish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuAwalActivity extends AppCompatActivity {
    ImageButton btnToLogin, btnToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_awal);

        btnToLogin = (ImageButton) findViewById(R.id.btnToLogin);
        btnToRegister = (ImageButton) findViewById(R.id.btnToRegister);

        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAwalActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAwalActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

    }
}

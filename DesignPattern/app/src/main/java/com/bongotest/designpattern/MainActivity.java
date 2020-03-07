package com.bongotest.designpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bongotest.designpattern.decorator.activities.DecoratorMainActivity;
import com.bongotest.designpattern.factory.activities.FactoryMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button factoryBtn = findViewById(R.id.factory_btn);
        Button decoratorBtn = findViewById(R.id.decorator_btn);

        factoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FactoryMainActivity.class);
                startActivity(intent);
            }
        });

        decoratorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DecoratorMainActivity.class);
                startActivity(intent);
            }
        });
    }
}

package au.edu.utas.shiduoz.assignment2.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import au.edu.utas.shiduoz.assignment2.R;

public class CreateActivity extends AppCompatActivity {

    Button closeBtn, addBtn, saveGoBtn;
    TextView selectDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // close
        closeBtn = findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // create action & add detail
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo create or save operation
                Intent intent = new Intent(CreateActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
        // create action
        saveGoBtn = findViewById(R.id.saveGoBtn);
        saveGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo create or save operation
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

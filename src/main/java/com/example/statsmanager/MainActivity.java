package com.example.statsmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private String myText;
    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    TextView sm_text = findViewById(R.id.sm_text);
    TextView sm_date = findViewById(R.id.sm_date);

    Button sm_btn_add = findViewById(R.id.sm_btn_add);
    Button sm_btn_reset = findViewById(R.id.sm_btn_reset);
    Button sm_btn_note = findViewById(R.id.sm_btn_note);

    ConstraintLayout sm_layout_main = findViewById(R.id.sm_Layout_main);
    ConstraintLayout sm_layout_sec = findViewById(R.id.sm_Layout_second);
    LinearLayout sm_LinearLayout = findViewById(R.id.sm_LinearLayout);

    Context context = getApplicationContext();
    SharedPreferences sharedPref = context.getSharedPreferences(
            "StreakManager.Data", Context.MODE_PRIVATE
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateSettings();
    }

    public void showAlertDialogButtonClicked(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Write your note:");

        final EditText noteInput = new EditText(MainActivity.this);
        noteInput.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
        builder.setView(noteInput);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myText = noteInput.getText().toString();
                Toast.makeText(MainActivity.this, "SAVED", Toast.LENGTH_LONG).show();
                sm_LinearLayout.addView(createNewTextView(myText));
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void dateSettings() {
        dateTimeDisplay = (TextView) findViewById(R.id.sm_date);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);
    }

    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(date+" : " + text);
        return textView;
    }



}
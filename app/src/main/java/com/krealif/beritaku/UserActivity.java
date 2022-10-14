package com.krealif.beritaku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;
import java.util.Locale;

public class UserActivity extends AppCompatActivity {

    private TextInputEditText inputDate;
    private Spinner spinnerCategory;
    private Button btnNext;
    private LinearLayout containerSpinner;

    public static final String AGE_KEY = "AGE_KEY";
    public static final String CATEGORY_KEY = "CATEGORY_KEY";

    private String userAge;
    private String userCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        inputDate = findViewById(R.id.input_date);
        spinnerCategory = findViewById(R.id.spinner_category);
        containerSpinner = findViewById(R.id.container_spinner);
        btnNext = findViewById(R.id.btn_next);

        inputDate.setKeyListener(null);
        loadSpinnerCategory();

        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        inputDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) showDatePicker();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputDate.getText().toString().isEmpty()) {
                    inputDate.setError("This field cannot be left empty");
                } else {
                    Intent intent = new Intent(view.getContext(), NewsActivity.class);
                    intent.putExtra(CATEGORY_KEY, userCategory);
                    intent.putExtra(AGE_KEY, userAge);
                    startActivity(intent);
                }
            }
        });
    }

    private void showDatePicker() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "date-picker");
    }

    private void loadSpinnerCategory() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_category, android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        if (spinnerCategory != null) {
            spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    userCategory = adapterView.getItemAtPosition(i).toString().toLowerCase(Locale.ROOT);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

        }
    }

    public void processDatePickerResult(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        userAge = String.valueOf(calculateAge(date));
        inputDate.setText(sdf.format(date.getTime()));
        inputDate.setError(null);
    }

    private int calculateAge(Calendar dob) {
//      www.javatpoint.com/java-calculate-age
        Calendar currentDate = new GregorianCalendar();
        int age = currentDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if ((dob.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH)) || (dob.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH) && dob.get(Calendar.DAY_OF_MONTH) > currentDate.get(Calendar.DAY_OF_MONTH)))
        {
            age--;
        }
        return age;
    }

}
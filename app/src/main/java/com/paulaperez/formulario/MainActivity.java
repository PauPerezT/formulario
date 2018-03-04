package com.paulaperez.formulario;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText user, pass, passC, email, etdate;
    private Button btLogin, btDate;
    private DatePickerDialog datePickerDialog;
    private RadioButton rbFem, rbMas;
    private CheckBox cbHobbies1, cbHobbies2, cbHobbies3, cbHobbies4;
    private Spinner spCity;
    private TextView  tvResults;
    private  String username,password, cPassword, emailC, genre, dayPrint, monthPrint, yearPrint, hobbies,date, city;
    private int flagPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user=(EditText)findViewById(R.id.etUser);
        pass=(EditText)findViewById(R.id.etPass);
        passC=(EditText)findViewById(R.id.etPassC);
        email=(EditText)findViewById(R.id.etEmail);
        rbFem=(RadioButton) findViewById(R.id.rbFemale);
        rbMas=(RadioButton) findViewById(R.id.rbMale);
        etdate=(EditText)findViewById(R.id.etDate);
        btDate=(Button)findViewById(R.id.btBirth);
        cbHobbies1=(CheckBox)findViewById(R.id.cbHobbies1);
        cbHobbies2=(CheckBox)findViewById(R.id.cbHobbies2);
        cbHobbies3=(CheckBox)findViewById(R.id.cbHobbies3);
        cbHobbies4=(CheckBox)findViewById(R.id.cbHobbies4);
        btLogin=(Button)findViewById(R.id.btLogin);
        spCity=(Spinner)findViewById(R.id.spinnerBP);
        tvResults=(TextView)findViewById(R.id.main_results_tv);



        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.cities));
        spCity.setAdapter(citiesAdapter);


        //Data capturing
        btDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Text capturing
                username=user.getText().toString();
                password=pass.getText().toString();
                cPassword=passC.getText().toString();
                emailC=email.getText().toString();
                city=spCity.getSelectedItem().toString();

                //Genre Capturing
                if(rbFem.isChecked()){
                    genre=getString(R.string.fem);
                    //Toast.makeText(MainActivity.this,"yupi", Toast.LENGTH_LONG).show();
                }else {
                    genre=getString(R.string.mas);
                    //Toast.makeText(MainActivity.this,"el machote", Toast.LENGTH_LONG).show();
                }

                date = etdate.getText().toString();

                hobbies="";
                if ( cbHobbies1.isChecked() ){
                    hobbies= getString(R.string.gtCinema);
                }

                if ( cbHobbies2.isChecked() ){
                    if( !hobbies.isEmpty() ) hobbies+=", ";
                    hobbies=hobbies+getString(R.string.gtEat);
                }

                if (cbHobbies3.isChecked()==true){
                    if( !hobbies.isEmpty() ) hobbies+=", ";
                    hobbies=hobbies+getString(R.string.pSport);
                }

                if (cbHobbies4.isChecked()==true){
                    if( !hobbies.isEmpty() ) hobbies+=", ";
                    hobbies=hobbies+getString(R.string.nAbove);
                }



                //To print

                if(checkEmptyField()==true && passwordAreEqual()==true){

                    printResults();
                }/*else if(checkEmptyField()==false){
                    checkEmptyField();

                }else if (passwordAreEqual()==false){
                    passwordAreEqual();
                }*/else{
                    tvResults.setText("");
                }




            }
        });
    }

    private void printResults(){
        String results="";

        results+=getString(R.string.user)+":"+username+"\n";
        results+=getString(R.string.pass)+":"+password+"\n";
        results+=getString(R.string.email)+":"+emailC+"\n";
        results+=getString(R.string.genre)+":"+genre+"\n";
        results+=getString(R.string.pass)+":"+password+"\n";
        results+=getString(R.string.etBirthName)+":"+date+"\n";
        results+=getString(R.string.birthPlace)+":"+city+"\n";
        results+=getString(R.string.hobbies)+":"+hobbies;

        tvResults.setText(results);
    }

    private boolean checkEmptyField(){
        if( username.isEmpty() || password.isEmpty() || cPassword.isEmpty() || emailC.isEmpty() || date.isEmpty() || city.isEmpty() || hobbies.isEmpty()){
            Toast.makeText(MainActivity.this, getText(R.string.empty_fields), Toast.LENGTH_LONG ).show();
            return false;
        }
        return true;
    }

    private boolean passwordAreEqual(){
        // Password
        if( !password.equals(cPassword)){
            Toast.makeText(MainActivity.this,getString(R.string.pass_not_equal),Toast.LENGTH_LONG).show();
            passC.setError( getString(R.string.pass_not_equal) );
            return false;
        }else{
            passC.setError( null);
            return true;
        }
    }

    public void showDatePickerDialog() {
        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public  void onDateSet(DatePicker datePicker, int year, int month, int day) {
                etdate.setText(Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year));
            }
        };

        Calendar currentTime = Calendar.getInstance();

        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(MainActivity.this, dateListener,currentTime.get(Calendar.YEAR),currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }


}






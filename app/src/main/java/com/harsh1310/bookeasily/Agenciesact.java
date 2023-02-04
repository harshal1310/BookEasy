package com.harsh1310.bookeasily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Agenciesact extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    String states[], Email = "", id, Contact_Number="", Aadhar_Number="",  Pincode="", State="", City="", Password = "", Confirm_password = "", Profession = "",  Name = "", Alternate_Contact_Number;
    String TAG = "abcdefg";
    EditText editTextEmail, editTextContact_No, editTextAadhar_No,  editTextPassword, editTextConfirmPassword, editTextPincode, editTextName, editTextAlternate_contact_No;
    ProgressDialog pd;
    Spinner  spinner_state, spinner_City;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference database, rec_ref, subref, seeker_ref, dup_ref;
    ArrayList<User> Userlist;

    Credentials pref;
Button signup;
    SharedPreferences sharedPreferences,sp1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenciesact);
        mAuth = FirebaseAuth.getInstance();
        pref=Credentials.getInstance(this);

        database = FirebaseDatabase.getInstance().getReference();

        sharedPreferences= getSharedPreferences("Categories" , Context.MODE_PRIVATE);
        // sp1=getSharedPreferences("Pincode",Context.MODE_PRIVATE);
        init();
signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
createuser();
        //        writedata();
    }
});
        // to counter the issue where the backgroung image is not static and is compressing along with the scroll view wheneverthe keboard pops up
        getWindow().setBackgroundDrawableResource(R.drawable.blue_stripes_bg);
        setTitle("Sign Up");
        states = getResources().getStringArray(R.array.states);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item , states)
        {
            @Override
            public boolean isEnabled(int position)
            {
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position , View convertView , ViewGroup parent)
            {
                View view = super.getDropDownView(position , convertView , parent);
                TextView tv = (TextView)view;
                if(position == 0)
                {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else
                {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_state.setAdapter(spinnerArrayAdapter);
        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent , View view , int position , long id)
            {
                String s = parent.getSelectedItem().toString();
                if(s.equals("Andaman and Nicobar Islands"))
                {
                    andaman();
                }
                else if(s.equals("Andhra Pradesh"))
                {
                    andhra();
                }
                else if(s.equals("Arunachal Pradesh"))
                {
                    arunachal();
                }
                else if(s.equals("Assam"))
                {
                    assam();
                }
                else if(s.equals("Bihar"))
                {
                    bihar();
                }
                else if(s.equals("Chandigarh"))
                {
                    chandigarh();
                }
                else if(s.equals("Chattisgarh"))
                {
                    chattisgarh();
                }
                else if(s.equals("Darda and Nagar Haveli"))
                {
                    dadra();
                }
                else if(s.equals("Daman and Diu"))
                {
                    daman();
                }
                else if(s.equals("Delhi"))
                {
                    delhi();
                }
                else if(s.equals("Goa"))
                {
                    goa();
                }
                else if(s.equals("Gujarat"))
                {
                    gujarat();
                }
                else if(s.equals("Haryana"))
                {
                    haryana();
                }
                else if(s.equals("Himachal Pradesh"))
                {
                    himachal();
                }
                else if(s.equals("Jammu and Kashmir"))
                {
                    jammu();
                }
                else if(s.equals("Jharkhand"))
                {
                    jharkhand();
                }
                else if(s.equals("Karnataka"))
                {
                    karnataka();
                }
                else if(s.equals("Kerala"))
                {
                    kerala();
                }
                else if(s.equals("Lakshwadweep"))
                {
                    lakshwadeep();
                }
                else if(s.equals("Madhya Pradesh"))
                {
                    madhyapradesh();
                }
                else if(s.equals("Maharashtra"))
                {
                    maharashtra();
                }
                else if(s.equals("Manipur"))
                {
                    manipur();
                }
                else if(s.equals("Meghalaya"))
                {
                    meghalaya();
                }
                else if(s.equals("Mizoram"))
                {
                    mizoram();
                }
                else if(s.equals("Nagaland"))
                {
                    nagaland();
                }
                else if(s.equals("Orissa"))
                {
                    orissa();
                }
                else if(s.equals("Pondicherry"))
                {
                    pondicherry();
                }
                else if(s.equals("Punjab"))
                {
                    punjab();
                }
                else if(s.equals("Rajasthan"))
                {
                    rajasthan();
                }
                else if(s.equals("Sikkim"))
                {
                    sikkim();
                }
                else if(s.equals("Tamil Nadu"))
                {
                    tamilnadu();
                }
                else if(s.equals("Tripura"))
                {
                    tripura();
                }
                else if(s.equals("Uttar Pradesh"))
                {
                    uttarpradesh();
                }
                else if(s.equals("Uttaranchal"))
                {
                    uttaranchal();
                }
                else if(s.equals("West Bengal"))
                {
                    westbengal();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

        });

    }

    public void init()
    {
        Userlist = new ArrayList<>();
        editTextEmail = findViewById(R.id.agencymail);
        editTextPassword = findViewById(R.id.agencypass);
        editTextConfirmPassword = findViewById(R.id.agencyConfirmPassword);
        editTextAadhar_No = findViewById(R.id.Agencynumber);

        editTextName = findViewById(R.id.AgencyName);
        editTextPincode = findViewById(R.id.editTextPincodeforagency);
        editTextContact_No = findViewById(R.id.agencyContact_No);
        editTextAlternate_contact_No = findViewById(R.id.agencyAlternate_No);


        spinner_state = findViewById(R.id.statespinnerforagency);
        spinner_City = findViewById(R.id.agencyspinner);

signup=findViewById(R.id.button_nextagency);
        pd = new ProgressDialog(this);
    }
    public void createuser()
    {
        if(validation()==true) {
           // State = spinner_state.getSelectedItem().toString().trim();

         //   City = spinner_City.getSelectedItem().toString().trim();


            pd.setTitle("Registering User Please Wait...");
            pd.show();
            pd.setCanceledOnTouchOutside(false);
            Email = editTextEmail.getText().toString().trim();
            Password = editTextPassword.getText().toString().trim();
            Name = editTextName.getText().toString().trim();
            Contact_Number = editTextContact_No.getText().toString().trim();
            Aadhar_Number = editTextAadhar_No.getText().toString().trim();
            Alternate_Contact_Number = editTextAlternate_contact_No.getText().toString().trim();
            State = spinner_state.getSelectedItem().toString().trim();

            City = spinner_City.getSelectedItem().toString().trim();
            Pincode = editTextPincode.getText().toString().trim();
            Confirm_password = editTextConfirmPassword.getText().toString().trim();


            String m = editTextEmail.getText().toString();
            String p = editTextPassword.getText().toString();

            //     if(! validation())
            //   {
            //Toast.makeText(getApplication() , "Reached till validation" , Toast.LENGTH_LONG).show();
            //   pd.dismiss();
            //     return;
            //}
            //Toast.makeText(getApplication() , "outside" , Toast.LENGTH_LONG).show();
            //pd.setTitle("Registering Please Wait");
            //pd.show();
            mAuth.createUserWithEmailAndPassword(m, p).addOnSuccessListener(Agenciesact.this, new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    Toast.makeText(Agenciesact.this, "User Registered", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    id = FirebaseAuth.getInstance().getUid();
                    pref.settypeofuser("Agency");
                       pref.setid(id);
                 pref.setagencyname(Name);
                    //       id = currentUser.getUid();
                    writedata();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Agenciesact.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        }
    }


    public void writedata() {

        {//   duplicate();
            //       Toast.makeText(this , "Recruiter " , Toast.LENGTH_SHORT).show();
            //subref = rec_ref.child(id);
            User u = new User(Email, id, Contact_Number, Aadhar_Number, Pincode, State, City, Name,Alternate_Contact_Number);
User u1=new User(Email);
        //subref.child("Email").setValue(u.getEmail());
        //subref.child("Name").setValue(u.getName());
        //subref.child("Id").setValue(u.getId());
        //subref.child("Mobile").setValue(u.getContact_Number());
        //subref.child("Alternate Mobile").setValue(u.getAlternate_Contact_Number());
        //subref.child("Aadhaar").setValue(u.getAadhar_Number());
        //   subref.child("Address").setValue(u.getStreet_No());
        //subref.child("Pincode").setValue(u.getPincode());
        //subref.child("State").setValue(spinner_state.getSelectedItem());
        //subref.child("City").setValue(spinner_City.getSelectedItem());

        //     subref.child("Type").setValue(u.getType());
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Agencies").child(u.getPincode());
DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Agencymail");
        db.push().setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Agenciesact.this, "Regiestered", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Agenciesact.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        ref.push().setValue(u1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
        // Toast.makeText(Signup.this,"done",Toast.LENGTH_LONG).show();
        String categorie = "Recruiter";
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("categorie", categorie);
        editor.commit();
        startActivity(new Intent(Agenciesact.this, Login.class));
    }
    }

    private boolean validation()
    {
        boolean valid = true;

//Name=editTextName.getText().toString().trim();
        //editTextEmail = findViewById(R.id.agencymail);
        //editTextPassword = findViewById(R.id.agencypass);
        /*editTextConfirmPassword = findViewById(R.id.agencyConfirmPassword);
        editTextAadhar_No = findViewById(R.id.Agencynumber);

        editTextName = findViewById(R.id.AgencyName);
        editTextPincode = findViewById(R.id.editTextPincodeforagency);
        editTextContact_No = findViewById(R.id.agencyContact_No);
        editTextAlternate_contact_No = findViewById(R.id.agencyAlternate_No);


        spinner_state = findViewById(R.id.statespinnerforagency);
        spinner_City = findViewById(R.id.agencyspinner);
*/


        if(editTextName.getText().toString().isEmpty()||editTextName.getText().toString().length()==0)
        {
            editTextName.setError("Enter Name");
            editTextName.requestFocus();
            return false;
        }
        if(editTextAadhar_No.getText().toString().isEmpty()||editTextAadhar_No.getText().toString().length()==0)
        {
            editTextAadhar_No.setError("Enter Adharnum");
            editTextAadhar_No.requestFocus();
            return false;

        }
        if(editTextEmail.getText().toString().isEmpty()||editTextEmail.getText().toString().length()==0)
        {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return false;
        }

        if(! Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString().trim()).matches())
        {
            editTextEmail.setError("Please Enter valid Email address");
            editTextEmail.requestFocus();
            valid = false;
            return valid;
        }
        if(editTextPassword.getText().toString().isEmpty()||editTextPassword.getText().toString().length()==0)
        {
            editTextPassword.setError("Please Enter the password");
            editTextPassword.requestFocus();
            valid = false;
            return valid;
        }
        if(editTextPassword.getText().toString().length() < 6)
        {
            editTextPassword.setError("Minimum password length is 6");
            editTextPassword.requestFocus();
            valid = false;
            return valid;
        }



        if(editTextPincode.getText().toString().isEmpty()||editTextPincode.getText().toString().length()==0)
        {
            editTextPincode.setError("Enter Pincode");
            editTextPincode.requestFocus();
            valid = false;
            return valid;
        }
        if(editTextContact_No.getText().toString().length()!=10)
        {
            editTextContact_No.setError("Enter Correct Mobile Number");
            editTextContact_No.requestFocus();
            valid = false;
            return valid;
        }

        if(editTextContact_No.getText().toString().length()!=10)
        {
            editTextContact_No.setError("Enter Correct Mobile Number");
            editTextContact_No.requestFocus();
            valid = false;
            return valid;
        }

        if(editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())==false)
        {
            Toast.makeText(Agenciesact.this,"Password not matched",Toast.LENGTH_SHORT).show();
            valid=false;
        }

        return valid;
    }

    public void andaman()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.andaman , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void andhra()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.andhrapradesh , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void arunachal()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.arunachalpradesh , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void assam()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.assam , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void bihar()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.bihar , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void chandigarh()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.chandigarh , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void chattisgarh()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.chattisgarh , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void dadra()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.dadranagarhaveli , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void daman()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.damandiu , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void delhi()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.Delhi , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void goa()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.Goa , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void gujarat()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.gujarat , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void haryana()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.haryana , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void himachal()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.himachal , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void jammu()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.jammu , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void jharkhand()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.jarkhand , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void karnataka()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.karnataka , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void kerala()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.kerala , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void lakshwadeep()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.lakshwadeep , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void madhyapradesh()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.madhyapradesh , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void maharashtra()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.maharashtra , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void manipur()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.manipur , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void meghalaya()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.meghalaya , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void mizoram()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.mizoram , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void nagaland()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.nagaland , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void orissa()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.orissa , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void pondicherry()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.pondicherry , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void punjab()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.Punjab , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void rajasthan()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.rajasthan , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void sikkim()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.sikkim , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void tamilnadu()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.tamilnadu , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void tripura()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.tripura , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void uttarpradesh()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.uttarpradesh , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void uttaranchal()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.uttaranchal , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    public void westbengal()
    {
        ArrayAdapter<CharSequence> adap2 = ArrayAdapter.createFromResource(this , R.array.westbengal , android.R.layout.simple_spinner_item);
        adap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(adap2);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {

    }
}

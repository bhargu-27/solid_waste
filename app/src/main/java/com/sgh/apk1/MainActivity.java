package com.sgh.apk1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity {

    private Button _decrease;
    private Button _increase;
    private String _stringVal;
    public int n=0;
    protected Button mFileset;
    Button place_order;
    public TextView quantity_field;
    public EditText txt_quantity,txt_address;
    public FirebaseAuth mAuth;
    public DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("member");
    public FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    public DatabaseReference mDatabase= firebaseDatabase.getReference("Orders");

    private  String final_address,name,userID;
    Member member = new Member();
    Order order;
    TextView Num;
    String u_type,check_email;
    private void addMember()
    {
        Toast.makeText(MainActivity.this, "in the member function", Toast.LENGTH_SHORT).show();
        String quantity=txt_quantity.getText().toString().trim();
        String address=txt_address.getText().toString().trim();
        Toast.makeText(MainActivity.this, "values are taken", Toast.LENGTH_SHORT).show();
        if(!TextUtils.isEmpty(address))
        {

            Toast.makeText(MainActivity.this, "Inserting values", Toast.LENGTH_SHORT).show();
            //String id=mDatabase.push().getKey();

            Order order=new Order(name,address,quantity,String.valueOf(n),u_type);

                mDatabase.child(String.valueOf(n)).setValue(order);

            Toast.makeText(MainActivity.this, "Your Order has been Taken", Toast.LENGTH_SHORT).show();

        }

    }
    public void redirecting_to_page()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "in the main method", Toast.LENGTH_SHORT).show();


        txt_quantity=(EditText)findViewById(R.id.quantity);
        txt_address=(EditText)findViewById(R.id.address);
        place_order=(Button)findViewById(R.id.place_order);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        check_email = user.getEmail();
        userID=user.getUid();

        Toast.makeText(MainActivity.this, "get the userid", Toast.LENGTH_SHORT).show();
        Query query=FirebaseDatabase.getInstance().getReference("member").orderByChild("email").equalTo(check_email);
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    Toast.makeText(MainActivity.this, "reading from database", Toast.LENGTH_SHORT).show();
                    name= String.valueOf(ds.child("fullname"));
                    u_type=String.valueOf(ds.child("userType"));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        query.addListenerForSingleValueEvent(valueEventListener);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    n=(int)dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        View view;
        mFileset =(Button)findViewById(R.id.place_order);
        mFileset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                name=name.toString();
                Toast.makeText(MainActivity.this, "before calling member", Toast.LENGTH_SHORT).show();
                addMember();
                //1- create child in root object
                //2-assign some value to that child
                // member.setAddress(address_field.getText().toString());
                // mDatabase.push().child("Address").setValue(final_address);
                //mDatabase.child().child("Address").setValue(final_address);
                // TextView tv=(TextView)findViewById(textView7);
                //String text=tv.getText().toString();
                //int n=Integer.parseInt(text);
                //mDatabase.push().child("Quantity").setValue(n);
                //mDatabase.child().child("Quantity").setValue(n);
                /*Map<String,Object> taskMap = new HashMap<>();
                taskMap.put("Address", final_address);
                taskMap.put("Quantity", n);
                mDatabase.updateChildren(taskMap);  */
                redirecting_to_page();
            }
        });


        view=this. getWindow().getDecorView();
        view.setBackgroundResource(R.color.background);
        //Toast.makeText(MainActivity.this, "Connection Success",Toast.LENGTH_LONG).show();
    }



    public void btn_logout(View view) {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}








package com.viveksb007.androidterminal;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Terminal extends Activity {

    ArrayList<String> cmds = new ArrayList<>();
    Button mExecute;
    EditText mCommand;
    ListView mListView;
    ArrayAdapter<String> arrayAdapter;
    FirebaseDatabase database;
    DatabaseReference myRef,postRef,getRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_terminal);

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        postRef = myRef.child(getIntent().getExtras().getString("Username"));

        mExecute = (Button)findViewById(R.id.btnExecute);
        mCommand = (EditText)findViewById(R.id.etCommand);
        mListView = (ListView)findViewById(R.id.lvCommands);

        mExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCommand.getText().toString().equals(""))
                {
                    Toast.makeText(Terminal.this, "Command to daal le pehle", Toast.LENGTH_SHORT).show();
                }else {
                    cmds.add(mCommand.getText().toString());
                    mListView.invalidateViews();
                    mCommand.setText("");
                    mCommand.setHint("Enter Command");
                    PostCMD();
                }
            }
        });
        //arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cmds);
        //arrayAdapter = new ArrayAdapter<>(this,R.layout.cmdlayout,cmds);
        mListView.setAdapter(new CustomAdapter(this,cmds));

        getRef = myRef.child(getIntent().getExtras().getString("Username")).child("response");

        getRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                cmds.add(dataSnapshot.getValue().toString());
                mListView.invalidateViews();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void PostCMD()
    {
        postRef.child("cmds").push().setValue(cmds.get(cmds.size()-1));
    }


}

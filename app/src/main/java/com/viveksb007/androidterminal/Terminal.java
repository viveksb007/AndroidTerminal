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

import java.util.ArrayList;


public class Terminal extends Activity {

    ArrayList<String> cmds = new ArrayList<>();
    Button mExecute;
    EditText mCommand;
    ListView mListView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_terminal);
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
                }
            }
        });
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cmds);
        mListView.setAdapter(arrayAdapter);

    }


}

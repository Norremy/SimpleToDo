package sg.edu.rp.c346.id22030544.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTask;
    Button btnAdd;
    Button btnClear;
    ListView lv;
    Spinner spn;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lv = findViewById(R.id.listView);
        spn = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.buttonDelete);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        btnClear.setEnabled(true);
                        etTask.setHint("Type in new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnClear.setEnabled(true);
                        btnDelete.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed");

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> arrTasks = new ArrayList<String>();

        ArrayAdapter aaTasks = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, arrTasks);
        lv.setAdapter(aaTasks);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                arrTasks.add(task);
                aaTasks.notifyDataSetChanged();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrTasks.isEmpty()){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",
                            Toast.LENGTH_LONG).show();

                }
                else{
                    arrTasks.clear();
                    aaTasks.notifyDataSetChanged();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etTask.getText().toString();
                if(arrTasks.isEmpty()){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",
                            Toast.LENGTH_LONG).show();

                } else if (text.isEmpty()) {
                    Toast.makeText(MainActivity.this,"You did not input index",
                            Toast.LENGTH_LONG).show();}
                else if (!text.matches("[0-9]+")) {
                    Toast.makeText(MainActivity.this,"Please input an integer",
                            Toast.LENGTH_LONG).show();}
                else{
                    int pos = Integer.parseInt(etTask.getText().toString());
                    if (pos<0||pos>arrTasks.size()) {
                            Toast.makeText(MainActivity.this,"Wrong index number",
                            Toast.LENGTH_LONG).show();
                }
                    else{
                            arrTasks.remove(pos);
                            aaTasks.notifyDataSetChanged();}

                }
            }
        });



    }
}
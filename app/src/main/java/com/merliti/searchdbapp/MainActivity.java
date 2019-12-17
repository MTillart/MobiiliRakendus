package com.merliti.searchdbapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ListView listView;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DatabaseAccess databaseAccess;
    SearchView searchView;
    TrashAdapter tAdabter;
    ArrayList<Trash> trashArrayList;
    ArrayList<Trash> selectedList;
    TextView tvName, tvContainer, tvDescription;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SearchView searchView;

        //db = new SQLiteAssetHelper();

        searchView = findViewById(R.id.searchInput);
        searchView.setIconified(false);
        listView = (ListView) findViewById(R.id.listView);
        tvName = findViewById(R.id.name);
        tvContainer = findViewById(R.id.container);
        tvDescription = findViewById(R.id.description);

        tvName.setText("");
        tvContainer.setText("");
        tvDescription.setText("");

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

        databaseAccess.open();

        listItem = new ArrayList<>();


        searchView.setQueryHint("Kilekott, hambahari...");

        List<String> names = databaseAccess.getNames();
        //databaseAccess.close();

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
//              names);
//        this.listView.setAdapter(adapter);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    searchView.getLayoutParams().height = 200;
                }

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchView.getLayoutParams().height = 200;
                ArrayList<String> namesList = new ArrayList<>();
                List<String> names = databaseAccess.getNames();

                Log.d(TAG, "onQueryTextChange: " + newText);

                if(newText.length() != 0){
                    tvName.setText("");
                    tvContainer.setText("");
                    tvDescription.setText("");

                    listView.setVisibility(View.VISIBLE);

                for (String trash: names){
                    if (trash.toLowerCase().contains(newText.toLowerCase())){
                        namesList.add(trash);
                        Log.d(TAG, "onQueryTextChange: changes in query" );
                    }

                }
//                ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, namesList);
////                namesList.
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                        namesList);
                listView.setAdapter(adapter);
            }else{
                    listView.setVisibility(View.INVISIBLE);
                }
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: CLIKKED");

                hideKeyboard(MainActivity.this);
                listView.setVisibility(View.INVISIBLE);



                String selectedTrash = (String) listView.getItemAtPosition( position );
                //String str = trashItem.name.toString();

                Trash selTrash;
                String selName = "";

                selTrash = databaseAccess.getTrashByName(selectedTrash);

                tvName.setText(selTrash.getName());
                tvContainer.setText(selTrash.getContainer());
                tvDescription.setText(selTrash.getDescription());


                Log.d(TAG, "onItemClick: "+ selName);



                //trashArrayList = new ArrayList<Trash>();
                //trashArrayList = databaseAccess.getTrash();

                //tAdabter = new TrashAdapter(MainActivity.this, 0, trashArrayList);
                //listView.setAdapter(tAdabter);
                //String str = listView.getItemAtPosition(1).getClass().getName();

            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d(TAG, "onFocusChange: FOCUS CHAHGe");
            }
        });
    }
    
    

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

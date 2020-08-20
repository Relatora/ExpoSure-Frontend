package com.example.exposure.Activity.Organizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.exposure.API.RetrofitClient;
import com.example.exposure.Adapter.EventAdapter;
import com.example.exposure.R;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import io.reactivex.functions.Consumer;

import io.reactivex.schedulers.Schedulers;

import com.example.exposure.API.IExpoEvents;
import com.example.exposure.Model.ExpoEvent;

public class ListAllEventsActivity extends AppCompatActivity {

    // Access API via interface property
    IExpoEvents myAPI;

    //A disposable container that can hold onto multiple other
    // disposables and offers O(1) add and removal complexity.
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    // RecyclerView property
    RecyclerView recycler_event_search;

    // LinearLayoutManager property
    LinearLayoutManager layoutManager;
    EventAdapter adapter;

    // MaterialSearchBar property
    MaterialSearchBar materialSearchBar;

    // custom suggest list property. Ideally would be retrieved from DB,
    // however for speed I hard coded it here.
    List<String> suggestList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_events);

        // Init API
        myAPI = getAPI();


        //View
        recycler_event_search = findViewById(R.id.recycler_event_search);
        recycler_event_search.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_event_search.setLayoutManager(layoutManager);
        recycler_event_search.addItemDecoration(new DividerItemDecoration(this,layoutManager.getOrientation()));

        materialSearchBar =  findViewById(R.id.event_search_bar);
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.clearFocus();

        // Add suggest list
        addSuggestList();

        // Monitors searchbar for user input to populate it with suggested items
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for(String search_term:suggestList)
                    if(search_term.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search_term);

                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    getAllEvents();
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }



            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        getAllEvents();
    }

    // Custom suggested list
    private void addSuggestList() {
        // add suggest list
        // manual
        suggestList.add("data");
        suggestList.add("technology");
        suggestList.add("center");
        suggestList.add("Hall");

        materialSearchBar.setLastSuggestions(suggestList);
    }

    // create an instance of API
    private IExpoEvents getAPI() {
        return RetrofitClient.getInstance().create(IExpoEvents.class);
    }

    // ctrl + o
    // clear disposable
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    // plugs into adapter and searches for event name
    private void startSearch(String query) {
        materialSearchBar.clearFocus();
        compositeDisposable.add(myAPI.searchExpoEvent(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ExpoEvent>>() {
                    @Override
                    public void accept(List<ExpoEvent> expoEvents) throws Exception {
                        adapter = new EventAdapter(expoEvents);
                        recycler_event_search.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        String msg = getString(R.string.not_found);
                        Toast.makeText(ListAllEventsActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }));

    }

    // plugs into adapter and gets ExpoEvents
    private void  getAllEvents(){

        compositeDisposable.add(myAPI.getExpoEventList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ExpoEvent>>() {
                    @Override
                    public void accept(List<ExpoEvent> expoEvents) throws Exception {
                        adapter = new EventAdapter(expoEvents);
                        recycler_event_search.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        String msg = getString(R.string.not_found);
                        Toast.makeText(ListAllEventsActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

}

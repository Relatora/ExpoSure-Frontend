package com.example.exposure.Activity.Business;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.exposure.API.RetrofitClient;
import com.example.exposure.Adapter.EventAdapter;
import com.example.exposure.Adapter.ProductAdapter;
import com.example.exposure.R;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import io.reactivex.functions.Consumer;

import io.reactivex.schedulers.Schedulers;


import com.example.exposure.API.IProducts;
import com.example.exposure.Model.Product;

public class ListAllProductsActivity extends AppCompatActivity {

    // Access API via interface property
    IProducts myAPI;

    //A disposable container that can hold onto multiple other
    // disposables and offers O(1) add and removal complexity.
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    // RecyclerView property
    RecyclerView recycler_product_search;

    // LinearLayoutManager property
    LinearLayoutManager layoutManager;
    ProductAdapter adapter;

    // MaterialSearchBar property
    MaterialSearchBar materialSearchBar;

    // custom suggest list property. Ideally would be retrieved from DB,
    // however for speed I hard coded it here.
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_products);


    }

    protected void onStart() {
        super.onStart();

        // Init API
        myAPI = getAPI();

        // TODO: check integrity of product listing
        // TODO: build get product by name in backend
        // TODO: Fix adapter not intgrated into product consumer

        //View
        recycler_product_search = findViewById(R.id.recycler_product_search);
        recycler_product_search.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_product_search.setLayoutManager(layoutManager);
        recycler_product_search.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        materialSearchBar = findViewById(R.id.product_search_bar);
        materialSearchBar.setCardViewElevation(10);

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
                for (String search_term : suggestList)
                    if (search_term.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
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
                if (!enabled)
                    getAllProducts();
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        getAllProducts();
    }

    // Custom suggested list
    private void addSuggestList() {
        // add suggest list
        // manual
        suggestList.add("shark");
        suggestList.add("romba");
        suggestList.add("phone");
        suggestList.add("device");

        materialSearchBar.setLastSuggestions(suggestList);
    }

    // create an instance of API
    private IProducts getAPI() {
        return RetrofitClient.getInstance().create(IProducts.class);
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
        compositeDisposable.add(myAPI.searchProducts(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> products) throws Exception {
                        adapter = new ProductAdapter(products);
                        recycler_product_search.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        String msg = getString(R.string.not_found);
                        Toast.makeText(ListAllProductsActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    // plugs into adapter and gets Products
    private void  getAllProducts(){

        compositeDisposable.add(myAPI.getProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> products) throws Exception {
                        adapter = new ProductAdapter(products);
                        recycler_product_search.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        String msg = getString(R.string.not_found);
                        Toast.makeText(ListAllProductsActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}

package com.shopping.wallmart.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.shopping.wallmart.myapplication.com.wallmart.models.CategoryModel;
import com.shopping.wallmart.myapplication.com.wallmart.models.ProductModel;
import com.shopping.wallmart.myapplication.com.wallmart.serverrequest.API;
import com.shopping.wallmart.myapplication.com.wallmart.serverrequest.ServerRequest;
import com.shopping.wallmart.myapplication.com.wallmart.serverresponse.CatergoryResponse;
import com.shopping.wallmart.myapplication.com.wallmart.serverresponse.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener{

    private Spinner categorySpinner;
    private GridView gridView;
    private static ArrayAdapter<String> catergoryAdapter;
    List<String> categoryList = new ArrayList<String>();
    List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
    ServerRequest request = new ServerRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        categorySpinner = (Spinner) findViewById(R.id.categoryspinner);
        gridView = (GridView) findViewById(R.id.grid);

        getCategories();

        categorySpinner.setOnItemSelectedListener(this);
    }

    private void getCategories() {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Loding categories");
        String url = API.MAIN_URL + API.CATEGORY;



     /* JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
              new Response.Listener<JSONObject>(){
                 @Override
                  public  void onResponse(JSONObject response){
                   loading.dismiss();
                     categorySpinner = (Spinner) findViewById(R.id.countryspinner);
                     List<String> categoryList = new ArrayList<String>();
                     try {
                         JSONArray categoryArray = response.getJSONArray("contents");
                         for(int i = 0 ; i < categoryArray.length(); i++){
                             JSONObject categoryItems = (JSONObject) categoryArray.get(i);

                             *//*if(categoryItems.get("active") == true){
                                 categoryList.add(displayName)
                             }*//*


                         }
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }

                 }
              },
              new Response.ErrorListener(){

                  @Override
                  public void onErrorResponse(VolleyError error) {
                      loading.dismiss();
                  }
              }
              );

*/

        request.getCategoryDetails(getApplicationContext(), url, new Listener<CatergoryResponse>() {
                    @Override
                    public void onResponse(CatergoryResponse response) {
                        loading.dismiss();
                        categoryList.clear();
                        categoryModelList.clear();
                        categoryList.add(getResources().getString(R.string.categories_prompt));
                        CategoryModel category = new CategoryModel(getResources().getString(R.string.categories_prompt), "-1");
                        categoryModelList.add(category);

                        if(response != null) {



                            CatergoryResponse.Catergory[] catergoryItems = response.getmCategoryList();
                            for(CatergoryResponse.Catergory eachCategory : catergoryItems){
                                if(eachCategory.getmCategoryStatus()){
                                    categoryList.add(eachCategory.getmCategoryName());
                                    CategoryModel category1 = new CategoryModel(eachCategory.getmCategoryName(), eachCategory.getmCategoryId());
                                    categoryModelList.add(category1);
                                }
                            }

                            if(categoryList != null && categoryList.size() > 0){
                                catergoryAdapter = new ArrayAdapter<String>(getApplicationContext(),
                                        R.layout.spinner_item, categoryList);

                                catergoryAdapter.setDropDownViewResource(R.layout.spinner_item);
                                categorySpinner.setAdapter(catergoryAdapter);
                                categorySpinner.setPrompt(getResources().getString(R.string.categories_prompt));
                                categorySpinner.setSelection(1);

                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        error.networkResponse.toString();
                    }
                }
    );

       /* RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);*/



    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.setSelection(i);
        TextView textView = adapterView.findViewById(R.id.textview1);
        textView.setText(catergoryAdapter.getItem(i));
        if(i > 0) {
            CategoryModel selectedCategory = categoryModelList.get(i);
            String id = selectedCategory.categoryId;
            getSelectedCategoryItems(id);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    void getSelectedCategoryItems(String id) {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Loding categories");
        String url = API.MAIN_URL + String.format(API.PRODUCTS,id);
        final ArrayList<ProductModel> mProductList = new ArrayList<ProductModel>();

        request.getProductDetails(getApplicationContext(), url, new Listener<ProductResponse>() {
                    @Override
                    public void onResponse(ProductResponse response) {
                        loading.dismiss();
                       for(int i = 0; i < response.getmRecorsList().length; i++) {

                           mProductList.clear();
                           ProductResponse.ProductInfo [] productsInfo = response.getmRecorsList()[i].getMattributes().getmRecords();

                           for (ProductResponse.ProductInfo product : productsInfo){
                               ProductModel productDetails = new ProductModel(product);
                               mProductList.add(productDetails);
                           }


                           showGrid(mProductList);
                       }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        error.networkResponse.toString();
                    }
                }
        );
    }

    private void showGrid(ArrayList<ProductModel> mProductList) {

        ProductGridViewAdapter gridViewAdapter = new ProductGridViewAdapter(getApplicationContext(), mProductList);
        gridView.setAdapter(gridViewAdapter);
    }
}

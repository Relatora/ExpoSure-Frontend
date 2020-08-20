package com.example.exposure.Adapter;

import androidx.appcompat.widget.AppCompatImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.exposure.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import com.example.exposure.Model.Product;

/**
 * Product adapter class to populate RecyclerView with REST data coming from NodeJS backend
 */
public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{
    List<Product> productList;
    Context context;


    /**
     * Adapter Constructor
     * @param productList product List
     */
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    /**
     * Inflates layout with data received from backend
     * @param parent context
     * @param viewType viewType
     * @return new MyViewHolder(itemView) View
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.product_layout,parent, false);

        return new MyViewHolder(itemView);
    }

    /**
     * Binds holder with data received from backend based on position of object in thee REST list received from backend
     * @param holder Custom made container for data plugged into controls
     * @param position position of object in REST list returned from backend
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(productList.get(position).getName());
        holder.description.setText(productList.get(position).getDescription());
        holder.dimentions.setText(productList.get(position).getDimentions());
        holder.price.setText(productList.get(position).getPrice());

        // Load Images - Picasso library - takes care of async,
        //TODO: Fix  - conversion between blob and bitmap - complete but needs to be
        // tested - Product adapter is not detected to test this logic
        Blob blob = productList.get(position).getQR_code();
        byte[] byteArray = new byte[0];
        int blobLength = 0;
        try {
            blobLength = (int) blob.length();
            byteArray = blob.getBytes(1, blobLength);
            //release the blob and free up memory. (since JDBC 4.0)
            blob.free();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getErrorCode();
        }

        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
        // Load Images
        Picasso.get()
                .load(getImageUri(context,bm))
                .resize(400, 150)
                .centerCrop()
                .into(holder.QRCode);

        // Alternating colors of holder between white nad light grey to improve readability
        if( position % 2 == 0)
            holder.root_view.setCardBackgroundColor(Color.parseColor("#E1E1E1"));
    }

    /**
     * Gets a temp Uri of the images fetched from the database a blob then converted to a bitmap image
     * @param inContext Context of the ItemView
     * @param inImage bitmap image
     * @return Uri.parse(path) Uri required by Picasso library to successfully render QR Code image
     */
    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    /**
     * Gets List Size for proper iteration
     * @return productList.size()
     */
    @Override
    public int getItemCount() {
        return productList.size();
    }


    /**
     * Create a custom view holder that ties the holder with the control objects
     */
    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView root_view;
        TextView name, description, dimentions, price;
        AppCompatImageView QRCode;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            root_view = itemView.findViewById(R.id.root_view);
            name = itemView.findViewById(R.id.txt_product_name);
            description = itemView.findViewById(R.id.txt_product_description);
            dimentions= itemView.findViewById(R.id.txt_product_dimensions);
            price = itemView.findViewById(R.id.txt_product_price);
            QRCode = itemView.findViewById(R.id.img_Product_QRCode);

            context = itemView.getContext();
        }
    }
}

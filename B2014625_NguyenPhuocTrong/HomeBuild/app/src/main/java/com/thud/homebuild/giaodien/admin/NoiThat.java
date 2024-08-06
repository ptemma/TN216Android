package com.thud.homebuild.giaodien.admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thud.homebuild.R;
import com.thud.homebuild.dulieu.Item;
import com.thud.homebuild.dulieu.User;
import com.thud.homebuild.xuly.ItemAdapter;
import com.thud.homebuild.xuly.UserAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public class NoiThat extends AppCompatActivity {
    private ActivityResultLauncher<String> mGetContent;
    TextInputLayout layoutItemName, layoutPrice, layoutPicture;
    TextInputEditText edtItemName, edtPrice, edtPicture;
    ImageView imgHinhAnh;
    Item item;
    ItemAdapter itemAdapter;
    UserAdapter userAdapter;
    String imageName;
    User user;
    Bitmap bm;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_that);
        itemAdapter = new ItemAdapter(this);
        userAdapter = new UserAdapter(this);
        SharedPreferences sharedPreferences = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String email;
        email = sharedPreferences.getString("email", "");
        user = userAdapter.getUser(email);
        imgHinhAnh = findViewById(R.id.img_hinhanh);

        layoutItemName = findViewById(R.id.layout_itemName);
        layoutPrice = findViewById(R.id.layout_price);

        edtItemName = findViewById(R.id.edt_itemName);
        edtPrice = findViewById(R.id.edt_price);
        imgHinhAnh = findViewById(R.id.img_hinhanh);


        // Khởi tạo và đăng ký ActivityResultLauncher
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if (result != null) {
                            handleSelectedImage(result);
                        }
                    }
                });
    }
    public void openImagePicker(View view) {
        mGetContent.launch("image/*");
    }
    private void handleSelectedImage(Uri selectedImageUri) {
        Bitmap bitmap = null;
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getContentResolver(), selectedImageUri));
            } else {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
            }} catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap != null) {
            imageName = getFileName(selectedImageUri);
            imgHinhAnh.setImageBitmap(bitmap);
        }
    }


    public void addItem(View view){
        String itemName = edtItemName.getText().toString();
        if (TextUtils.isEmpty(itemName)) {
            layoutItemName.setError("Vui lòng nhập tên nội thất");
            layoutItemName.requestFocus();
            return;
        } else {
            layoutItemName.setError(null);
        }

        String priceString = edtPrice.getText().toString();
        Integer price;
        if (TextUtils.isEmpty(priceString)) {
            layoutPrice.setError("Vui lòng nhập giá");
            layoutPrice.requestFocus();
            return;
        } else {
            try {
                price = Integer.parseInt(priceString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                layoutPrice.setError("Giá không hợp lệ");
                layoutPrice.requestFocus();
                return;
            }
            layoutPrice.setError(null);
        }
        if (imageName == null) {
            return;
        }
        long result = itemAdapter.addItem(itemName, price, imageName, user.getId());

        if(result != -1){
            Toast.makeText(this, "Nội thất được thêm thành công" + imageName, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NoiThat.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = null;
            try {
                cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        result = cursor.getString(nameIndex);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
    private void copyImageToDrawable(String sourcePath, String imageName) {
        try {
            InputStream inputStream = new FileInputStream(sourcePath);
            AssetManager assetManager = getAssets();
            OutputStream outputStream = assetManager.openFd("drawable/" + imageName).createOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
package com.thud.homebuild.giaodien.cuahang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.thud.homebuild.R;
import com.thud.homebuild.dulieu.Cart;
import com.thud.homebuild.dulieu.Item;
import com.thud.homebuild.dulieu.User;
import com.thud.homebuild.xuly.CartAdapter;
import com.thud.homebuild.xuly.ImageAdapter;
import com.thud.homebuild.xuly.ItemAdapter;
import com.thud.homebuild.xuly.UserAdapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class CuaHangFragment extends Fragment{
    SharedPreferences infoRefs;
    private Integer[] Images = {
            R.drawable.bancafe, R.drawable.bancafe2ghe, R.drawable.bangoc,
            R.drawable.bantrangdiem, R.drawable.bantrangdiemgo, R.drawable.boghebanh, R.drawable.chaucay,
            R.drawable.dulechtamtron, R.drawable.dungculamvuon, R.drawable.ghebang, R.drawable.ghebanh,
            R.drawable.giadoke, R.drawable.giadungchendia, R.drawable.giatreo2tang,
            R.drawable.giuongdanang, R.drawable.guongmaicanh, R.drawable.guongtreodenled
            };
//    R.drawable.kesach, R.drawable.khaydungdao, R.drawable.khaydungdaomuondia, R.drawable.khunggiuongsofa,
//    R.drawable.khunggiuongtreem, R.drawable.lodunggiavi, R.drawable.lotsan, R.drawable.nemcaosu, R.drawable.nemmut,
//    R.drawable.nemmutchotreem, R.drawable.sofagiuongden, R.drawable.sofagiuongtrang, R.drawable.sofagoc2cho,
//    R.drawable.sofaguong, R.drawable.sofangoaitroi, R.drawable.thotgo, R.drawable.thungrac, R.drawable.thungracsinhhoc,
//    R.drawable.treodungcubep, R.drawable.voghebanh

    ItemAdapter itemAdapter;
    UserAdapter userAdapter;
    CartAdapter cartAdapter;
    List<Item> items;
    public CuaHangFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        {
            View view = inflater.inflate(R.layout.fragment_cua_hang, container, false);
            itemAdapter = new ItemAdapter(getActivity());
            userAdapter = new UserAdapter(getActivity());
            cartAdapter = new CartAdapter(getActivity());
            items = itemAdapter.AllItems();
            GridView dgvMenu = view.findViewById(R.id.dgv_menu);
            ImageAdapter imageAdapter = new ImageAdapter(getActivity(), R.layout.list_item_layout, Images, items);
            dgvMenu.setAdapter(imageAdapter);
            infoRefs = getActivity().getSharedPreferences("infoItem", Context.MODE_PRIVATE);
            SharedPreferences itemRefs = getActivity().getSharedPreferences("item", Context.MODE_PRIVATE);
            SharedPreferences userRefs = getActivity().getSharedPreferences("profile", Context.MODE_PRIVATE);

            int itemId = itemRefs.getInt("itemId", 0);
            String email = userRefs.getString("email", "");
            User user = userAdapter.getUser(email);
            Cart cart = cartAdapter.getMyCart(user.getId());
            imageAdapter.setBuyItemClickListener(new ImageAdapter.BuyItemClickListener() {
                @Override
                public void onBuyItemClick(int position) {
                    long result = cartAdapter.addItemToCart(cart.getCartId(), itemId);
                    if (result != -1) {
                        // Thêm thành công
                        Toast.makeText(getContext(), "Đã thêm item vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    } else {
                        // Thêm không thành công
                        Toast.makeText(getContext(), "Có lỗi xảy ra khi thêm item vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            imageAdapter.setMuaItemClickListener(new ImageAdapter.MuaItemClickListener() {
                @Override
                public void onMuaItemClick(int position) {

                    LocalDate now = LocalDate.now();
                    SharedPreferences.Editor editor = infoRefs.edit();
                    editor.putString("name", items.get(position).getItemName());
                    editor.putFloat("price", items.get(position).getPrice());
                    editor.putString("time", now.toString());
                    editor.apply();
                    Toast.makeText(getContext(), "Bạn đã mua sản phẩm thành công, xem chi tiết tại Thông báo", Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }

}
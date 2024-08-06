package com.thud.homebuild.giaodien.giohang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;

public class GioHangFragment extends Fragment {

    SharedPreferences infoRefs;
    CartAdapter cartAdapter;
    UserAdapter userAdapter;
    ItemAdapter itemAdapter;
    List<Item> items;
    User user;

    public GioHangFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    ListView listView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_gio_hang, container, false);
        cartAdapter = new CartAdapter(getActivity());
        userAdapter = new UserAdapter(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("profile", Context.MODE_PRIVATE);
        String email= sharedPreferences.getString("email", "");
        User user = userAdapter.getUser(email);
        Cart cart = cartAdapter.getMyCart(user.getId());;
        listView = view.findViewById(R.id.lst_items);
        items = cartAdapter.getItems(cart.getCartId());
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), R.layout.list_item_layout, null, items);
        infoRefs = getActivity().getSharedPreferences("infoItem", Context.MODE_PRIVATE);

        if (items.isEmpty()) {
            Toast.makeText(getContext(), "Không có mặt hàng trong giỏ hàng", Toast.LENGTH_SHORT).show();
        } else {
            itemAdapter = new ItemAdapter(getActivity(), items);
            listView.setAdapter(itemAdapter);
        }


        return view;
    }
}
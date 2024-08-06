package com.example.ql_sudungnuoc.xuly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ql_sudungnuoc.dulieu.DbHelper;
import com.example.ql_sudungnuoc.dulieu.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangAdapter {

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private String[] allColumns= {
            DbHelper.KH_MSKH,
            DbHelper.KH_HOTEN,
            DbHelper.KH_DIENTHOAI,
            DbHelper.KH_DOITUONG,
            DbHelper.KH_THANHTOAN,
            DbHelper.KH_KHUVUC
    };
    public KhachHangAdapter(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public  long insertKhachHang(KhachHang khachHang){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.KH_MSKH, khachHang.getMskh());
        values.put(DbHelper.KH_HOTEN,khachHang.getHoten());
        values.put(DbHelper.KH_DIENTHOAI,khachHang.getDienthoai());
        values.put(DbHelper.KH_DOITUONG, khachHang.getDoituong());
        values.put(DbHelper.KH_THANHTOAN, khachHang.getThanhtoan());
        values.put(DbHelper.KH_KHUVUC, khachHang.getKhuvuc());

        return db.insert(DbHelper.TABLE_KHACHHANG, null, values);
    }

    public int updateKhachHang(int mskh, String strHoTen,
                               String strDienThoai, String strDoiTuong,
                               String strThanhToan, int khuvuc){
        ContentValues values = new ContentValues();
        values.put(DbHelper.KH_HOTEN, strHoTen);
        values.put(DbHelper.KH_DIENTHOAI, strDienThoai);
        values.put(DbHelper.KH_DOITUONG, strDoiTuong);
        values.put(DbHelper.KH_THANHTOAN, strThanhToan);
        values.put(DbHelper.KH_KHUVUC, khuvuc);
        return db.update(DbHelper.TABLE_KHACHHANG, values,
                DbHelper.KH_MSKH + " = " + mskh, null);
    }
    public int deleteKhachHang(int mskh) {
        return db.delete(DbHelper.TABLE_KHACHHANG,
                DbHelper.KH_MSKH + " = " + mskh, null);
    }

    private KhachHang cursorKhachHang(Cursor cursor){
        KhachHang khachHang = new KhachHang();
        khachHang.setMskh(cursor.getInt(0));
        khachHang.setHoten(cursor.getString(1));
        khachHang.setDienthoai(cursor.getString(2));
        khachHang.setDoituong(cursor.getString(3));
        khachHang.setThanhtoan(cursor.getString(4));
        khachHang.setKhuvuc(cursor.getInt(5));
        return khachHang;
    }
    public List<KhachHang> getAllKhachHang (){
        List<KhachHang> khachHangs = new ArrayList<KhachHang>();
        Cursor cursor= db.query(DbHelper.TABLE_KHACHHANG,
                allColumns,
                null,
                null,
                null,
                null,
                null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                KhachHang values= cursorKhachHang(cursor);
                khachHangs.add(values);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return khachHangs;
    }

    public Boolean KtraKH(int id){
        Boolean tontai= false;
        List<KhachHang> khachHangs = getAllKhachHang();
        int i=0;
        while ((!tontai) && (i < khachHangs.size())){
            if (khachHangs.get(i).getMskh() == id)
                tontai = true;
            else i++;
        }
        return tontai;
    }
    public void clode(){
        db.close();
        dbHelper.close();
    }

}

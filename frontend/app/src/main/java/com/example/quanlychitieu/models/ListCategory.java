package com.example.quanlychitieu.models;

import com.example.quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;

public class ListCategory {
    public static List<Category> getCategoryFoodList() {
        List<Category> categoryList = new ArrayList<>();

        Category cate1 = new Category();
        cate1.setCategoryName("Ăn uống");
        cate1.setImg(R.drawable.an_uong);
        categoryList.add(cate1);

        Category cate2 = new Category();
        cate2.setCategoryName("Ăn trưa");
        cate2.setImg(R.drawable.an_trua);
        categoryList.add(cate2);

        Category cate3 = new Category();
        cate3.setCategoryName("Ăn tiệm");
        cate3.setImg(R.drawable.an_tiem);
        categoryList.add(cate3);

        Category cate4 = new Category();
        cate4.setCategoryName("Ăn tối");
        cate4.setImg(R.drawable.an_toi);
        categoryList.add(cate4);

        Category cate5 = new Category();
        cate5.setCategoryName("Cafe");
        cate5.setImg(R.drawable.coffee);
        categoryList.add(cate5);

        return categoryList;
    }
    public static List<Category> getCategoryLivingList(){
        List<Category> categoryList = new ArrayList<>();

        Category cate1 = new Category();
        cate1.setCategoryName("Dịch vụ sinh hoạt");
        cate1.setImg(R.drawable.dichvu_sinhhoat);
        categoryList.add(cate1);

        Category cate2 = new Category();
        cate2.setCategoryName("Điện");
        cate2.setImg(R.drawable.tien_dien);
        categoryList.add(cate2);

        Category cate3 = new Category();
        cate3.setCategoryName("Gas");
        cate3.setImg(R.drawable.tien_gas);
        categoryList.add( cate3);

        Category cate4 = new Category();
        cate4.setCategoryName("Internet");
        cate4.setImg(R.drawable.tien_internet);
        categoryList.add(cate4);

        Category cate5 = new Category();
        cate5.setCategoryName("Truyền hình");
        cate5.setImg(R.drawable.television);
        categoryList.add(cate5);

        Category cate6 = new Category();
        cate6.setCategoryName("Tiền nuớc");
        cate6.setImg(R.drawable.tien_nuoc);
        categoryList.add(cate6);

        return categoryList;
    }

    public static List<Category> getCategoryEnjoymentList(){
        List<Category> categoryList = new ArrayList<>();
        Category cate1 = new Category();
        cate1.setCategoryName("Hưởng thụ");
        cate1.setImg(R.drawable.huong_thu);
        categoryList.add(cate1);

        Category cate2 = new Category();
        cate2.setCategoryName("Du lịch");
        cate2.setImg(R.drawable.du_lich);
        categoryList.add(cate2);

        Category cate3 = new Category();
        cate3.setCategoryName("Làm đẹp");
        cate3.setImg(R.drawable.lam_dep);
        categoryList.add( cate3);

        Category cate4 = new Category();
        cate4.setCategoryName("Mỹ phẩm");
        cate4.setImg(R.drawable.my_pham);
        categoryList.add(cate4);

        Category cate5 = new Category();
        cate5.setCategoryName("Phim ảnh ca nhạc");
        cate5.setImg(R.drawable.phimanh_canhac);
        categoryList.add(cate5);

        Category cate6 = new Category();
        cate6.setCategoryName("Vui chơi giải trí");
        cate6.setImg(R.drawable.vuichoi_giaitri);
        categoryList.add(cate6);

        return categoryList;
    }

    public static List<Category> getCategoryReceivingList(){
        List<Category> categoryList = new ArrayList<>();

        Category cate1 = new Category();
        cate1.setCategoryName("Lãi tiết kiệm");
        cate1.setImg(R.drawable.lai_tiet_kiem);
        categoryList.add(cate1);

        Category cate2 = new Category();
        cate2.setCategoryName("Được cho/tặng");
        cate2.setImg(R.drawable.duoccho_tang);
        categoryList.add(cate2);

        Category cate3 = new Category();
        cate3.setCategoryName("Lương");
        cate3.setImg(R.drawable.luong);
        categoryList.add( cate3);

        Category cate4 = new Category();
        cate4.setCategoryName("Thưởng");
        cate4.setImg(R.drawable.thuong);
        categoryList.add(cate4);

        Category cate5 = new Category();
        cate5.setCategoryName("Tiền lãi");
        cate5.setImg(R.drawable.tien_lai);
        categoryList.add(cate5);

        return categoryList;
    }
}

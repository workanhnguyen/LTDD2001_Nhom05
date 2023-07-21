package com.example.quanlychitieu.sampledatas;

import com.example.quanlychitieu.models.CategoryType;

import java.util.ArrayList;
import java.util.List;

public class CategoryData {
        public static List<CategoryType> getExpenseCategoryTypeList() {
            List<CategoryType> categoryList = new ArrayList<>();

            categoryList.add(new CategoryType(1, "Ăn sáng"));
            categoryList.add(new CategoryType(2, "Ăn trưa"));
            categoryList.add(new CategoryType(3, "Ăn chiều"));
            categoryList.add(new CategoryType(4, "Ăn tối"));
            categoryList.add(new CategoryType(5, "Ăn vặt"));

            return categoryList;
        }
        public static List<CategoryType> getIncomeCategoryTypeList(){
            List<CategoryType> categoryList = new ArrayList<>();

            categoryList.add(new CategoryType(6, "Lương"));
            categoryList.add(new CategoryType(7, "Thưởng"));
            categoryList.add(new CategoryType(8, "Được cho, tặng"));
            categoryList.add(new CategoryType(9, "Lãi ngân hàng"));
            categoryList.add(new CategoryType(10, "Lãi tiết kiệm"));

            return categoryList;
        }

}

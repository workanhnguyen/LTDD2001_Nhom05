# [SPENDIFY - Ứng dụng quản lý chi tiêu cá nhân]
(Vui lòng đọc hết hướng dẫn phía dưới)

[1. File Powerpoint thuyết trình lý thuyết về Unity](https://docs.google.com/presentation/d/1UUyWsMAs5fLb2D5b0yy8Mn1Rzwoj9ItZ/edit?usp=sharing&ouid=105580800192484285399&rtpof=true&sd=true)  
[2. File source code demo Unity](https://drive.google.com/file/d/16vFJj-8d03AWuxB5CLFMipxQZ7gQpnAd/view?usp=drive_link)  
[3. Bảng phân công công việc](https://drive.google.com/file/d/1TVM0gVLT_IQbITkYu2gaJbSp8OVGBGGA/view?usp=sharing)  
[4. Bảng mô tả nghiệp vụ](https://drive.google.com/file/d/1v2dclUJrE9YvEnkuOxuNrhoJz1e9lY2v/view?usp=sharing)  
[5. Bảng hướng dẫn sử dụng](https://drive.google.com/file/d/17tcACXfLb0eQnYpDgBWnO2EB4IIFEGZ7/view?usp=sharing)  

### Thành viên nhóm 05:
1. 2051012004 - Nguyễn Vân Anh (Nhóm trưởng)  
2. 2051012019 - Lương Tấn Đạt  
3. 2054052051 - Đào Như Quỳnh  
4. 2051050265 - Đặng Văn Mãi

### Tổng quan về ứng dụng:
* Phía giao diện được thực hiện bằng ngôn ngữ `Java` trên Android Studio.
* Phía server được thực hiện bằng framework `Java Spring Boot`, tạo ra các API giao tiếp với phía giao diện người dùng.
* Phía server đã được deploy lên `Redhat OpenShift` (do sử dụng tài khoản miễn phí, nên tốc độ truy xuất dữ liệu chậm và bị giới hạn 30 ngày).

### Các chức năng chính thực hiện được:
1. Đăng nhập / đăng ký người dùng.
2. Tạo các khoản thu chi theo danh mục.
3. Xem thống kê thu chi theo tháng.
4. Xem, sửa, xóa người dùng phía quản trị viên.
5. Đăng xuất tài khoản.
6. Ngoài ra nhóm có sử dụng Fragment, Widget (ImageView, CalendarView), có menu, setting, trang chính, trang con.

### Các chức năng nhóm phát triển thêm:
1. Chuyển đổi ngôn ngữ tiếng Việt / tiếng Anh.
2. Tạo ví với số tiền ban đầu.
3. Xem danh sách ví đã tạo.
4. Chỉnh sửa, xóa ví.
5. Chỉnh sửa, xóa các khoản thu chi đã tạo.
6. Xem danh mục thu chi.
7. Chỉnh sửa thông tin cá nhân.
8. Đổi mật khẩu tài khoản.
9. Cài đặt tùy chọn ẩn / hiện số dư.

### Hướng dẫn cài đặt:
Cách 1: Dùng file `spendify-release.apk` có sẵn phía trên, tải về điện thoại và tiến hành cài đặt.  
Cách 2: 
- Sử dụng lệnh sau để tải project về máy tính.

	git clone https://github.com/workanhnguyen/LTDD2001_Nhom05

- Sau đó mở folder `frontend` của project bằng `Android Studio`.
- Tiếp theo mở file `graddle-wrapper.properties` và chọn `Sync now`.
- Chạy project.

### Tài khoản sử dụng:
1. Tài khoản admin:
   * Name: admin
   * Password: admin
  
2. Tài khoản người dùng: có thể tự đăng ký để test.

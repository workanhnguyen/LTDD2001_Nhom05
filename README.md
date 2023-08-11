# [SPENDIFY - Ứng dụng quản lý chi tiêu]

### Thành viên nhóm 05:
1. 2051012004 - Nguyễn Vân Anh (Nhóm trưởng)  
2. 2051012019 - Lương Tấn Đạt  
3. 2054052051 - Đào Như Quỳnh  
4. 2051050265 - Đặng Văn Mãi

### Tổng quan về ứng dụng:
* Phía giao diện được thực hiện bằng ngôn ngữ Java trên Android Studio.
* Phía server được thực hiện bằng framework Java Spring Boot, tạo ra các API giao tiếp với phía giao diện người dùng.
* Phía server đã được deploy lên Redhat OpenShift (do sử dụng tài khoản miễn phí, nên tốc độ truy xuất dữ liệu chậm và bị giới hạn 30 ngày).

### Các chức năng chính thực hiện được:
1. Đăng nhập / đăng ký người dùng.
2. Tạo các khoản thu chi theo danh mục.
3. Xem thống kê thu chi theo tháng.
4. Xem, sửa, xóa người dùng phía quản trị viên.
5. Đăng xuất tài khoản.

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
Cách 2: Sử dụng lệnh:

	git clone https://github.com/workanhnguyen/LTDD2001_Nhom05

để tải project về máy tính. Sau đó mở folder `frontend` của project bằng Android Studio và chạy project này.

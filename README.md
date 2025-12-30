# HỆ THỐNG ĐẶT VÉ XEM PHIM PHÂN TÁN

# Movie Ticket Booking – Microservices Architecture

---

**Sinh viên thực hiện:**  
Hoàng Đức Hùng - B25CHHT026
Nguyễn Hồng Sơn - B25CHHT049
Vũ Thanh Thiên - B25CHHT055

## 1. Giới thiệu (Overview)

Hệ thống Đặt vé xem phim là một hệ thống phân tán theo kiến trúc Microservices, mô phỏng đầy đủ quy trình nghiệp vụ của một nền tảng đặt vé rạp chiếu phim trong thực tế, bao gồm:

- Xác thực người dùng
- Tra cứu rạp – phim – suất chiếu – ghế
- Đặt vé và giữ chỗ
- Thanh toán
- Gửi vé điện tử cho khách hàng

---

## 2. Mục tiêu hệ thống

- Xây dựng hệ thống phân tán gồm nhiều service độc lập
- Áp dụng kiến trúc Microservices và Message Queue
- Mô phỏng luồng nghiệp vụ đặt vé – giữ chỗ – thanh toán – gửi vé
- Dễ dàng mở rộng và triển khai thực tế

---

## 3. Tổng quan kiến trúc hệ thống (Architecture)

Hệ thống gồm **5 service độc lập**, mỗi service đảm nhiệm một nghiệp vụ riêng:

| Service              | Mô tả                                    |
| -------------------- | ---------------------------------------- |
| Auth Service         | Xác thực và phân quyền người dùng        |
| Cinema Service       | Quản lý rạp chiếu, phim, suất chiếu, ghế |
| Booking Service      | Đặt vé, giữ ghế, thanh toán              |
| Payment Service      | Giả lập thanh toán                       |
| Notification Service | Gửi email và vé                          |

Các service giao tiếp thông qua REST API và RabbitMQ.

---

## 4. Công nghệ sử dụng

- Backend Spring Boot
- ORM Spring Data JPA
- Database SQL Server
- Auth JWT
- Message Queue RabbitMQ
- Build Maven
- Container Docker
- Giao tiếp REST + Async Messaging

---

## 5. Sơ đồ kiến trúc logic

- Client → Auth Service (xác thực)
- Client → Cinema Service (xem phim, rạp, suất chiếu)
- Client → Booking Service (đặt vé, giữ ghế)
- Booking Service → Payment Service (thanh toán)
- Payment Service → Notification Service (gửi vé)

---

## 6. Auth Service

**Chức năng**

- Xác thực người dùng
- Quản lý token (JWT)

**Vai trò**

- Đảm bảo các thao tác nghiệp vụ được thực hiện bởi người dùng hợp lệ

---

## 7. Cinema Service

**Chức năng**

- Cung cấp danh sách rạp chiếu
- Danh sách phim
- Suất chiếu theo rạp
- Danh sách ghế theo suất chiếu

**Đặc điểm**

- Chỉ đọc dữ liệu
- Không ảnh hưởng tới trạng thái hệ thống

---

## 8. Booking Service

**Chức năng**

- Tạo booking
- Giữ ghế (HOLD)
- Hủy giữ ghế khi quá hạn
- Cập nhật trạng thái BOOKED sau thanh toán

**Ý nghĩa**

- Là trung tâm xử lý nghiệp vụ đặt vé

---

## 9. Payment Service

**Chức năng**

- Giả lập thanh toán
- Lưu trạng thái thanh toán
- Phát sự kiện thanh toán thành công

**Trạng thái**

- PENDING
- SUCCESS
- FAILED

---

## 10. Notification Service

**Chức năng**

- Nhận message từ RabbitMQ
- Gửi email vé cho khách hàng
- Hiển thị thông tin vé

---

## 11. Luồng nghiệp vụ chính

1. Người dùng chọn phim, rạp, suất chiếu
2. Đặt ghế → trạng thái HOLD
3. Thanh toán
4. Payment SUCCESS
5. Booking chuyển sang BOOKED
6. Gửi vé qua Notification Service

---

## 12. Message Queue (RabbitMQ)

- Giảm coupling giữa các service
- Đảm bảo xử lý bất đồng bộ
- Tăng độ tin cậy hệ thống

---

## 13. Bảo mật hệ thống

- JWT Authentication
- Phân tách quyền truy cập
- Che dữ liệu nhạy cảm bằng DTO

---

## 14. Độ tin cậy và tính sẵn sàng

- Các service độc lập
- Có thể scale riêng lẻ
- Hạn chế lỗi lan truyền

---

## 15. Khả năng mở rộng lên Production

- Dễ dàng container hóa bằng Docker
- Có thể dùng Kubernetes
- Thay thế Payment giả lập bằng cổng thanh toán thật
- Tích hợp SMS, Push Notification

---

## 16. Kết luận

Hệ thống đã áp dụng thành công các khái niệm cốt lõi của Hệ thống phân tán như: loại trừ tương hỗ, điều khiển tương tranh, giao tiếp bất đồng bộ và giao dịch phân tán. Việc ánh xạ lý thuyết vào các công nghệ thực tế như Redis, RabbitMQ và Microservices giúp hệ thống đảm bảo tính nhất quán, mở rộng và chịu lỗi.
Mặc dù còn ở mức mô phỏng (giả lập thanh toán, chưa tích hợp cổng thực tế), nhưng kiến trúc và cách tổ chức hệ thống hoàn toàn có thể phát triển thành sản phẩm thực tế nếu tiếp tục đầu tư.

---

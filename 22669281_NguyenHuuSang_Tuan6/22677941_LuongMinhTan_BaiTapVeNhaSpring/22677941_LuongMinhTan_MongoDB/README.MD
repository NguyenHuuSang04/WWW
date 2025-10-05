# Employee Management & Analytics System (Spring Boot + MongoDB)

## Mục lục
- [Giới thiệu](#giới-thiệu)
- [Cấu trúc Dự Án](#cấu-trúc-dự-án)
- [Cài đặt & Cấu hình](#cài-đặt--cấu-hình)
- [API Endpoints](#api-endpoints)
    - [CRUD Employee](#crud-employee)
    - [Analytics/Thống kê](#analytics-thống-kê)
- [Mô hình Service/Repository](#mô-hình-servicerepository)
- [Tác giả](#tác-giả)

---

## Giới thiệu

Đây là hệ thống quản lý nhân viên sử dụng **Spring Boot** và **MongoDB** với hai nhóm tính năng chính:
- Thao tác CRUD cơ bản cho Employee.
- Thống kê, phân tích dữ liệu nhân viên theo nhiều tiêu chí với Aggregation MongoDB.

---

## Cấu trúc Dự Án

```
src/
 ├─ main/
 │   ├─ java/
 │   │   ├─ your.package.name/
 │   │   │   ├─ controller/
 │   │   │   │   └─ EmployeeController.java
 │   │   │   ├─ service/
 │   │   │   │   ├─ EmployeeService.java
 │   │   │   │   └─ EmployeeAnalyticsService.java
 │   │   │   ├─ repository/
 │   │   │   │   ├─ EmployeeRepository.java
 │   │   │   │   └─ EmployeeAnalyticsRepository.java
 │   │   │   ├─ model/
 │   │   │   │   ├─ Employee.java
 │   │   │   │   └─ Department.java
 │   │   │   └─ dto/
 │   │   │       ├─ AvgAgeByStatusDTO.java
 │   │   │       └─ AvgSalaryByDeptIdDTO.java
 │   └─ resources/
 │       └─ application.properties
```

---

## Cài đặt & Cấu hình

1. **Cấu hình MongoDB trong `application.properties`:**

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/your_database_name
spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
spring.http.encoding.force=true
```

> **Lưu ý:** Đổi `your_database_name` thành tên MongoDB bạn sử dụng.

2. **Chạy MongoDB server** trước khi chạy ứng dụng.

3. **Build & Run:**
    - Sử dụng Maven:
      ```bash
      mvn spring-boot:run
      ```
    - Hoặc chạy trực tiếp từ IDE.

---

## API Endpoints

### CRUD Employee

| Chức năng                  | Method | URL                                    | Body/Params           |
|----------------------------|--------|-----------------------------------------|-----------------------|
| Tạo/Cập nhật Employee      | POST   | `/api/employees`                        | JSON Employee         |
| Lấy tất cả Employee        | GET    | `/api/employees`                        |                       |
| Lấy Employee theo Mongo _id| GET    | `/api/employees/{id}`                   | Path: id              |
| Xóa Employee theo _id      | DELETE | `/api/employees/{id}`                   | Path: id              |
| Tìm theo empId             | GET    | `/api/employees/by-empid/{empId}`       | Path: empId           |
| Tìm theo tên (search)      | GET    | `/api/employees/search?name=xxx`        | Query: name           |
| Tìm theo status            | GET    | `/api/employees/by-status/{status}`     | Path: status          |
| Tìm theo mã phòng ban      | GET    | `/api/employees/by-dept/{deptId}`       | Path: deptId          |

---

### Analytics/Thống kê

| Chức năng                                                        | Method | URL                                   | Mô tả                                 |
|------------------------------------------------------------------|--------|---------------------------------------|----------------------------------------|
| Employees có salary cao nhất                                     | GET    | `/api/employees/max-salary`           | Danh sách nhân viên lương cao nhất     |
| Employees lớn tuổi nhất                                          | GET    | `/api/employees/max-age`              | Danh sách nhân viên lớn tuổi nhất      |
| Thống kê số employee & lương trung bình theo phòng ban           | GET    | `/api/employees/analytics/dept`       | Trả về list AvgSalaryByDeptIdDTO       |
| Thống kê số employee & tuổi trung bình theo status               | GET    | `/api/employees/analytics/status`     | Trả về list AvgAgeByStatusDTO          |
| Employees theo lương tăng dần trong từng phòng ban               | GET    | `/api/employees/sorted-by-salary`     | Trả về list Document                   |

---

## Mô hình Service/Repository

- `EmployeeRepository` ⟶ `EmployeeService`  
  (Quản lý CRUD và truy vấn đơn giản cho `Employee`)
- `EmployeeAnalyticsRepository` ⟶ `EmployeeAnalyticsService`  
  (Thống kê, phân tích dữ liệu, truy vấn aggregate cho `Employee`)

---

## Tác giả

- **Họ tên:** (Điền tên bạn)
- **Email:** (Điền email bạn)
- **Github:** (Điền link github nếu có)

---

> **Liên hệ tác giả để biết thêm chi tiết hoặc nhận tài liệu mẫu các lớp Service, Repository, DTO, Controller!**
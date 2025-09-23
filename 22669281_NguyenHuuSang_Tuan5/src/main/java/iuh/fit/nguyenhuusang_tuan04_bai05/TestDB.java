package iuh.fit.nguyenhuusang_tuan04_bai05;
import iuh.fit.nguyenhuusang_tuan04_bai05.dao.DepartmentDAO;
import iuh.fit.nguyenhuusang_tuan04_bai05.model.Department;
import org.mariadb.jdbc.MariaDbDataSource;
import java.sql.SQLException;
public class TestDB {
    public static void main(String[] args) throws SQLException {
        // Tạo DataSource kết nối tới DB của bạn
        MariaDbDataSource ds = new MariaDbDataSource();
        ds.setUrl("jdbc:mariadb://localhost:3306/tuan04_bai05");
        ds.setUser("root");
        ds.setPassword("root");

        DepartmentDAO dao = new DepartmentDAO(ds);
        System.out.println("Danh sách phòng ban:");
        for (Department d : dao.getAll()) {
            System.out.println(d.getId() + " - " + d.getName());
        }
    }
}



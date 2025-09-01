/**
 * @description:  Kiem thu,menu va du lieu mau
 * @author:  Le Cao Phu Tinh
 * @version: 1.0
 * @created: Aug 30, 2025 11:09:59 PM
 */
package iuh.fit.cs;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestCourse {
	//Ham tao du lieu mau
    public static void initData(CourseList courseList) {
        System.out.println("Đang khởi tạo dữ liệu mẫu... ");
        courseList.addCourse(new Course(3, "Kỹ thuật lập trình", "CS101", "Công nghệ thông tin"));
        courseList.addCourse(new Course(4, "Mạng máy tính", "IT201", "Công nghệ thông tin"));
        courseList.addCourse(new Course(1, "Giáo dục thể chất", "GD102", "Giáo dục"));
        courseList.addCourse(new Course(4, "Cấu trúc dữ liệu và giải thuật", "CS202", "Công nghệ thông tin"));
        courseList.addCourse(new Course(2, "Toán cao cấp", "KT103", "Kinh tế"));
        courseList.addCourse(new Course(2, "Giáo dục quốc phòng", "GD103", "Giáo dục"));
        courseList.addCourse(new Course(3, "Lập trình hướng đối tượng", "IT303", "Công nghệ thông tin"));
        System.out.println("--- Khởi tạo hoàn tất. ---");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseList courseList = new CourseList(100);
        
        //Khoi tao du lieu mau
        initData(courseList);

        int choice;
        String inputId, inputKeyword, inputDept;

        do {
            showMenu();
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
            case 1://Them khoa hoc
                System.out.println("\n 1.Thêm khóa học mới");
                System.out.print("Nhập mã khóa học: ");
                String newId = scanner.nextLine();

                System.out.print("Nhập tên khóa học: ");
                String newTitle = scanner.nextLine();

                System.out.print("Nhập số tín chỉ: ");
                int newCredit = scanner.nextInt();
                scanner.nextLine(); 

                System.out.print("Nhập tên khoa: ");
                String newDept = scanner.nextLine();

                Course newCourse = new Course(newCredit, newTitle, newId, newDept);
                boolean success = courseList.addCourse(newCourse);
                if (success) {
                    System.out.println("Thêm khóa học thành công!");
                } else {
                    // Thông báo lỗi đã được xử lý trong phương thức addCourse
                }
                break;
                case 2: //Xoa khoa hoc
                    System.out.println("\n 2. Xóa khóa học theo mã");
                    System.out.print("Nhập mã khóa học cần xóa: ");
                    inputId = scanner.nextLine();
                    courseList.removeCourse(inputId);
                    break;
                case 3: //Tim khoa hoc theo ma khoa
                    System.out.println("\n 3. Tìm kiếm khóa học theo mã");
                    System.out.print("Nhập mã khóa học cần tìm: ");
                    inputId = scanner.nextLine();
                    Course foundCourse = courseList.searchCourse(inputId);
                    if (foundCourse != null) {
                        System.out.println("Tìm thấy: " + foundCourse);
                    } else {
                        System.out.println("Không tìm thấy khóa học với mã " + inputId);
                    }
                    break;
                case 4: //Tim khoa hoc theo tu khoa
                    System.out.println("\n 4. Tìm kiếm khóa học theo tiêu đề (từ khóa)");
                    System.out.print("Nhập từ khóa tiêu đề: ");
                    inputKeyword = scanner.nextLine();
                    Course[] coursesByTitle = courseList.searchCourseByTitle(inputKeyword);
                    if (coursesByTitle.length > 0) {
                        Arrays.stream(coursesByTitle).forEach(System.out::println);
                    } else {
                        System.out.println("Không tìm thấy khóa học nào phù hợp.");
                    }
                    break;
                case 5: //Tim khoa hoc theo khoa
                    System.out.println("\n 5. Tìm kiếm khóa học theo khoa");
                    System.out.print("Nhập tên khoa: ");
                    inputDept = scanner.nextLine();
                    List<Course> coursesByDept = courseList.searchCourseByDepartment(inputDept);
                    if (!coursesByDept.isEmpty()) {
                        coursesByDept.forEach(System.out::println);
                    } else {
                        System.out.println("Không tìm thấy khóa học nào cho khoa " + inputDept);
                    }
                    break;
                case 6: //Sap xep khoa hoc
                    System.out.println("\n 6. Sắp xếp các khóa học theo tiêu đề");
                    Course[] sortedCourses = courseList.sortCourses();
                    Arrays.stream(sortedCourses).forEach(System.out::println);
                    break;
                case 7: //Tim khoa hoc co tin chi cao nhat
                    System.out.println("\n 7. Tìm các khóa học có số tín chỉ cao nhất");
                    Course[] maxCreditCourses = courseList.findMaxCreditCourses();
                    Arrays.stream(maxCreditCourses).forEach(System.out::println);
                    break;
                case 8: //Tim khoa co nhieu khoa hoc nhat
                    System.out.println("\n 8. Tìm khoa có nhiều khóa học nhất");
                    String deptWithMostCourses = courseList.findDepartmentWithMostCourses();
                    System.out.println("Khoa: " + deptWithMostCourses);
                    break;
                case 9: //Thoat
                    System.out.println("Đã thoát chương trình.");
                    break;
                default: //Nhap sai lua chon
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
            System.out.println();
        } while (choice != 9);

        scanner.close();
    }
//Hien thi menu
    private static void showMenu() {
        System.out.println("================== MENU ==================");
        System.out.println("1. Thêm khóa học mới");
        System.out.println("2. Xóa khóa học theo mã");
        System.out.println("3. Tìm kiếm khóa học theo mã");
        System.out.println("4. Tìm kiếm khóa học theo tiêu đề (từ khóa)");
        System.out.println("5. Tìm kiếm khóa học theo khoa");
        System.out.println("6. Sắp xếp các khóa học theo tiêu đề");
        System.out.println("7. Tìm các khóa học có số tín chỉ cao nhất");
        System.out.println("8. Tìm khoa có nhiều khóa học nhất");
        System.out.println("9. Thoát chương trình");
        System.out.println("==========================================");
    }
}
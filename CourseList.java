/**
 * @description:  Thuc hien cac chuc nang
 * @author:  Le Cao Phu Tinh
 * @version: 1.0
 * @created: Aug 30, 2025 6:26:22 PM
 */
package iuh.fit.cs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseList {
	//Property
    private int count = 0;
    private Course[] courses;

    public CourseList(int n) {
        if (n <= 0) throw new IllegalArgumentException("Số lượng khóa học phải > 0");
        courses = new Course[n];
    }

    public boolean addCourse(Course c) {
        if (exists(c)) {
            System.out.println("Lỗi: Mã khóa học đã tồn tại!");
            return false;
        }
        if (count == courses.length) {
            System.out.println("Danh sách đầy, không thể thêm!");
            return false;
        }
        courses[count++] = c;
        return true;
    }

    public boolean exists(Course c) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equalsIgnoreCase(c.getId())) {
                return true;
            }
        }
        return false;
    }
    //Getters
    public Course[] getCourses() {
        return Arrays.copyOf(courses, count);
    }

    public boolean removeCourse(String id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equalsIgnoreCase(id)) {
                for (int j = i; j < count - 1; j++) {
                    courses[j] = courses[j + 1];
                }
                courses[--count] = null;
                return true;
            }
        }
        System.out.println("Không tìm thấy khóa học để xóa.");
        return false;
    }

    public Course searchCourse(String id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equalsIgnoreCase(id)) {
                return courses[i];
            }
        }
        return null;
    }

    public Course[] searchCourseByTitle(String keyword) {
        return Arrays.stream(courses, 0, count)
                .filter(c -> c.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toArray(Course[]::new);
    }

    public List<Course> searchCourseByDepartment(String dept) {
        return Arrays.stream(courses, 0, count)
                .filter(c -> c.getDepartment().equalsIgnoreCase(dept))
                .collect(Collectors.toList());
    }

    public Course[] sortCourses() {
        Course[] sorted = getCourses();
        Arrays.sort(sorted, Comparator.comparing(Course::getTitle));
        return sorted;
    }

    public Course[] findMaxCreditCourses() {
        if (count == 0) return new Course[0];

        int maxCredit = Arrays.stream(courses, 0, count)
                .mapToInt(Course::getCredit)
                .max()
                .orElse(0);

        return Arrays.stream(courses, 0, count)
                .filter(c -> c.getCredit() == maxCredit)
                .toArray(Course[]::new);
    }

    public String findDepartmentWithMostCourses() {
        if (count == 0) return null;

        Map<String, Long> map = Arrays.stream(courses, 0, count)
                .collect(Collectors.groupingBy(Course::getDepartment, Collectors.counting()));

        return map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
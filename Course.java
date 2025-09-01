/**
 * @description:  Khoi tao Course
 * @author:  Le Cao Phu Tinh
 * @version: 1.0
 * @created: Aug 30, 2025 6:23:54 PM
 */
package iuh.fit.cs;

public class Course {
	private int credit;
	private String department;
	private String id;
	private String title;
	public int length;
	//Constructor day du
	public Course(int credit, String department, String id, String title) {
        if (id == null || id.length() < 3 || !id.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Mã khóa học không hợp lệ.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên khóa học không được để trống.");
        }
        if (credit <= 0) {
            throw new IllegalArgumentException("Số tín chỉ phải lớn hơn 0.");
        }
        this.id = id;
        this.title = title;
        this.credit = credit;
        this.department = department;

		this.credit = credit;
		this.department = department;
		this.id = id;
		this.title = title;
	}
	//Constructor mac dinh
	public Course() {
	}
	//Getters
	public int getCredit() {
		return credit;
	}
	public String getDepartment() {
		return department;
	}
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	//Setters
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
	    return String.format("Mã: %s | Tên: %s | Tín chỉ: %d | Khoa: %s", id, title, credit, department);
	}
	
}

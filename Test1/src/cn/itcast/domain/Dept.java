package cn.itcast.domain;

public class Dept {

	private int id;
	private String deptname;
	private String deptdesc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptdesc() {
		return deptdesc;
	}
	public void setDeptdesc(String deptdesc) {
		this.deptdesc = deptdesc;
	}
	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dept(int id, String deptname, String deptdesc) {
		super();
		this.id = id;
		this.deptname = deptname;
		this.deptdesc = deptdesc;
	}
	@Override
	public String toString() {
		return "Dept [id=" + id + ", deptname=" + deptname + ", deptdesc=" + deptdesc + "]";
	}
	
}

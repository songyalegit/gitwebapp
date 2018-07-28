package cn.itcast.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.itcast.domain.Dept;
import cn.itcast.domain.Employee;
import cn.itcast.tools.JDBCUtils;

public class MainApp {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from dept";
		List<Dept> deptlist = qr.query(sql, new BeanListHandler<Dept>(Dept.class));
		Map<Double, Dept> map = new HashMap<>();
		for (Dept dept : deptlist) {
			map.put(Double.parseDouble(dept.getId()+""), dept);
		}
		String sql1 = "SELECT e.* FROM employee e LEFT JOIN dept d ON e.deptid=d.id WHERE e.deptid=d.id";
		List<Object[]> objlist = qr.query(sql1, new ArrayListHandler());
		List<Employee> emplist=new ArrayList<>();
		for (Object[] obj : objlist) {
			Employee emp = new Employee();
			emp.setId(Double.parseDouble(obj[0].toString()));
			emp.setName(obj[1].toString());
			emp.setAge(Double.parseDouble(obj[2].toString()));
			emp.setSex(obj[3].toString());
			emp.setSalary(Double.parseDouble(obj[4].toString()));
			emp.setEmpdate(obj[5].toString());
			emp.setDeptid(Double.parseDouble(obj[6].toString()));
			if(map.containsKey(emp.getDeptid())){
				emp.setDeptname(map.get(emp.getDeptid()));
			}
			emplist.add(emp);
		}
		for(Employee e:emplist){
			System.out.println(e);
		}
		
	}

}

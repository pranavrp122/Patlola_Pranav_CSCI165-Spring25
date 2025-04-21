// Employee.java
/*
 * File:	Employee.java
 * Author:	Ken Whitener
 * Date:	4/15/2024
 * 
 * Description: This class represents an employee with a hire date, id number, and department.
 * 				Employee is a subclass of Person.
 */

 public class Employee extends Person {

	private Date	hireDate;
	private int		id;
	private String	department;

	// no argument
	public Employee() {}

	// overloaded constructor
	/**
	 * 
	 * @param p
	 * @param hired
	 * @param id
	 * @param dept
	 */
	public Employee(Person p, Date hired, int id, String dept) {
		super(p);			// call to super class copy constructor
		setHireDate(hired);	// sets will protect privacy
		setId(id);
		setDepartment(dept);
	}

	// overloaded constructor
	/**
	 * 
	 * @param first
	 * @param last
	 */
	public Employee(String first, String last){
		super(first, last);
	}

	// copy constructor
	/**
	 * 
	 * @param toCopy
	 */
	public Employee(Employee toCopy){
		this(toCopy, toCopy.hireDate, toCopy.id, toCopy.department);
	}

	/**
	 * 
	 * @return the date the Employee was hired
	 */
	public Date getHireDate() {
		return new Date(hireDate);			// privacy protection
	}

	/**
	 * 
	 * @param hireDate
	 */
	public void setHireDate(Date hireDate) {
		this.hireDate = new Date(hireDate); // privacy protection
	}

	/**
	 * 
	 * @return The Employee's ID number
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The Employee's Department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * 
	 * @param department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return super.toString() + "\nHired: " + hireDate + "\nID: " + id + "\nDept: " + department;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)                    return true;    // identity check
		if (obj == null)                    return false;   // null check
		if (getClass() != obj.getClass())   return false;   // origin check 
		if (!super.equals(obj))             return false;   // super class check

		Employee other = (Employee) obj;    // downcast

		// hireDate
		if (hireDate == null) {
			if (other.hireDate != null)     return false;
		} else if (!hireDate.equals(other.hireDate)) return false;

		// id
		if (id != other.id)                 return false;

		// department
		if (department == null) {
			if (other.department != null)   return false;
		} else if (!department.equals(other.department)) return false;

		return true;
	}
}

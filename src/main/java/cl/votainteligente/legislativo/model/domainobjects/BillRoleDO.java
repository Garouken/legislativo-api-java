package cl.votainteligente.legislativo.model.domainobjects;

import java.util.Date;

import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Role;

public class BillRoleDO {
	private Long billId;
	private Long roleId;
	private String roleName;
	private Date billEntryDate;
	private Date roleStartDate;
	private Date roleEndDate;

	public BillRoleDO(Bill bill, Role role) {
		this.billId = bill.getId();
		this.roleId = role.getId();
		this.roleName = role.getClass().getSimpleName();
		this.billEntryDate = bill.getEntryDate();
		this.roleStartDate = role.getStartDate();
		this.roleEndDate = role.getEndDate();
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getBillEntryDate() {
		return billEntryDate;
	}

	public void setBillEntryDate(Date billEntryDate) {
		this.billEntryDate = billEntryDate;
	}

	public Date getRoleStartDate() {
		return roleStartDate;
	}

	public void setRoleStartDate(Date roleStartDate) {
		this.roleStartDate = roleStartDate;
	}

	public Date getRoleEndDate() {
		return roleEndDate;
	}

	public void setRoleEndDate(Date roleEndDate) {
		this.roleEndDate = roleEndDate;
	}

}

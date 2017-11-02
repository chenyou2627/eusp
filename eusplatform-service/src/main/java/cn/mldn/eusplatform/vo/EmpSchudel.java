package cn.mldn.eusplatform.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EmpSchudel implements Serializable {

	private String eid;
	private String ename;
	private String photo;
	private String levelTitle;
	private String deptTitle;
	private Long allRecorders ;
	public Long getAllRecorders() {
		return allRecorders;
	}
	public void setAllRecorders(Long allRecorders) {
		this.allRecorders = allRecorders;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getLevelTitle() {
		return levelTitle;
	}
	public void setLevelTitle(String levelTitle) {
		this.levelTitle = levelTitle;
	}
	public String getDeptTitle() {
		return deptTitle;
	}
	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}
	@Override
	public String toString() {
		return "EmpSchudel [eid=" + eid + ", ename=" + ename + ", photo=" + photo + ", levelTitle=" + levelTitle
				+ ", deptTitle=" + deptTitle + "]";
	}
}

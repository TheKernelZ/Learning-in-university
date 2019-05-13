package com.thekernel.entity;

public class FileInfo {

	public int fid;
	public String fname;
	public String fcategory;
	public String fsize;
	public int fuid;
	public String ftime;

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFcategory() {
		return fcategory;
	}

	public void setFcategory(String fcategory) {
		this.fcategory = fcategory;
	}

	public String getFsize() {
		return fsize;
	}

	public void setFsize(String fsize) {
		this.fsize = fsize;
	}

	public int getFuid() {
		return fuid;
	}

	public void setFuid(int fuid) {
		this.fuid = fuid;
	}

	public String getFtime() {
		return ftime;
	}

	public void setFtime(String ftime) {
		this.ftime = ftime;
	}

	@Override
	public String toString() {
		return "FileInfo [fid=" + fid + ", fname=" + fname + ", fcategory=" + fcategory + ", fsize=" + fsize + ", fuid="
				+ fuid + ", ftime=" + ftime + "]";
	}

	public FileInfo() {
		super();
	}

	public FileInfo(int fid, String fname, String fcategory, String fsize, int fuid, String ftime) {
		super();
		this.fid = fid;
		this.fname = fname;
		this.fcategory = fcategory;
		this.fsize = fsize;
		this.fuid = fuid;
		this.ftime = ftime;
	}

}

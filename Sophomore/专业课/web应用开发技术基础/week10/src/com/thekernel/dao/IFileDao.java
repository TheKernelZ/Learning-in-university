package com.thekernel.dao;

import java.util.List;

import com.thekernel.entity.FileInfo;

public interface IFileDao {

	int insertList(List<FileInfo> files);
	
	List<FileInfo> selectAll();
	
}

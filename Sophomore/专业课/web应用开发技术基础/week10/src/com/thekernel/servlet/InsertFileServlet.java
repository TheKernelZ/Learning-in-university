package com.thekernel.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thekernel.dao.IFileDao;
import com.thekernel.dao.IUserDao;
import com.thekernel.dao.impl.FileDaoImpl;
import com.thekernel.dao.impl.UserDaoImpl;
import com.thekernel.entity.FileInfo;
import com.thekernel.entity.UInfo;

public class InsertFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getParameter("path");
		path = "WEB-INF\\classes\\com\\thekernel\\servlet\\" + path;
		List<FileInfo> files = new ArrayList<FileInfo>();
		FileInfo my_file = null;
		HttpSession session = req.getSession();
		String username = "";
		IUserDao Userdao = new UserDaoImpl();
		IFileDao FileDao = new FileDaoImpl();
		UInfo u = null;
		
		username = (String) session.getAttribute("username");
		u = Userdao.selectByUsername(username);
		String result = req.getSession().getServletContext().getRealPath(path);

		File file = new File(result);
		File[] fileList = file.listFiles();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (int i = 0; i < fileList.length; i++) {

			if (fileList[i].isFile()) {
				my_file = new FileInfo(i, 
						fileList[i].getName().substring(0, fileList[i].getName().lastIndexOf(".")),
						fileList[i].getName().substring(fileList[i].getName().lastIndexOf(".")+1, fileList[i].getName().length()),
						fileList[i].length() + "",
						u.getUserid(),
						df.format(new Date()).toString()
				);
			}

			if (fileList[i].isDirectory()) {

				my_file = new FileInfo(i, 
						fileList[i].getName(),
						"Ŀ¼",
						fileList[i].length() + "",
						u.getUserid(),
						df.format(new Date()).toString()
				);
			}
			
			files.add(my_file);
		}
		
		FileDao.insertList(files);
		
		req.getRequestDispatcher("InsertFile.jsp").forward(req, resp);

	}
}

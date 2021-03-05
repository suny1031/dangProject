package com.dang.album.model.service;

import com.dang.album.model.dao.AlbumDao;

import com.dang.common.jdbc.JDBCTemplate;

public class AlbumService {
	AlbumDao albumDao = new AlbumDao();
	JDBCTemplate jbt = JDBCTemplate.getInstance();

}

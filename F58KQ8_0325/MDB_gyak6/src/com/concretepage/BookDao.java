package com.concretepage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class BookDao {
	public void save(Book book) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.concretepage.BookMapper.insertBook", book);
		session.commit();
		session.close();
	}

	public List<Book> getAllData() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Book> books = session.selectList("com.concretepage.BookMapper.findAllBooks");
		session.close();
		return books;
	}

}

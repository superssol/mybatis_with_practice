package com.exercise.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Excutor {
	
	private static SqlSessionFactory sqlSessionFactory; 

	static {
		try {
			String resource = "mybatis/config-mybatis.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			Shop shop = new Shop();
			shop.setShopNo(100);
//			shop.setShopName("test shop");
//			shop.setShopLocation("boramae");
			shop.setShopStatus("n");
			
//			sqlSession.insert("shopMapper.insert", shop);
			sqlSession.update("shopMapper.update", shop);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}

}

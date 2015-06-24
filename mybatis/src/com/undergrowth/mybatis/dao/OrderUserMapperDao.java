package com.undergrowth.mybatis.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.undergrowth.mybatis.mapper.OrderUserMapper;
import com.undergrowth.mybatis.po.OrdersUser;
import com.undergrowth.mybatis.po.OrdersUserDetailPojo;
import com.undergrowth.mybatis.po.OrdersUserPojo;
import com.undergrowth.mybatis.po.UserToOrdersVo;
import com.undergrowth.mybatis.util.SqlSessionUtil;


/**
 * 测试代码
 * resuType--将sql的列名和pojo的属性进行一一的映射
 * resultMap--可以避免使用resultType方式的重复记录
 * 		association--将sql列映射到pojo对象中
 * 		collection--将关联信息映射到list列表中
 * @author u1
 *
 */
public class OrderUserMapperDao {

	SqlSessionFactory sqlSessionFactory=null;
	
	@Before
	public void before() throws IOException{
		sqlSessionFactory=SqlSessionUtil.getSqlSessionFactory("mybatis-conf.xml");
	}
	
	
	/**
	 * 使用resultType进行一对一的映射
	 */
	@Test
	public void testOneToOne(){
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			OrderUserMapper  orderUserMapper=sqlSession.getMapper(OrderUserMapper.class);
			List<OrdersUser> ordersUsers=orderUserMapper.findOrdersUserByResultType();
			for (OrdersUser ordersUser : ordersUsers) {
				System.out.println(ordersUser);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
	
	/**
	 * 使用resultMap进行一对一的映射
	 */
	@Test
	public void testOneToOneResultMap(){
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			OrderUserMapper  orderUserMapper=sqlSession.getMapper(OrderUserMapper.class);
			List<OrdersUserPojo> ordersUsers=orderUserMapper.findOrdersUserByResultMap();
			for (OrdersUserPojo ordersUser : ordersUsers) {
				System.out.println(ordersUser); 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
	/**
	 * 使用resultMap和collection进行一对多的映射
	 */
	@Test
	public void findOrderUserDetailResultMap(){
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			OrderUserMapper  orderUserMapper=sqlSession.getMapper(OrderUserMapper.class);
			List<OrdersUserDetailPojo> ordersUsers=orderUserMapper.findOrderUserDetailResultMap();
			for (OrdersUserDetailPojo ordersUser : ordersUsers) {
				System.out.println(ordersUser); 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
	/**
	 * 使用resultMap和collection association进行多对多的映射
	 */
	@Test
	public void findUserAndItemsResultMap(){
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			OrderUserMapper  orderUserMapper=sqlSession.getMapper(OrderUserMapper.class);
			List<UserToOrdersVo> ordersUsers=orderUserMapper.findUserAndItemsResultMap();
			for (UserToOrdersVo ordersUser : ordersUsers) {
				System.out.println(ordersUser); 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
}

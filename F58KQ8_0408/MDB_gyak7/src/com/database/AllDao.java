package com.database;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class AllDao {
	//meal
	public void saveMeal(Meal meal) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.database.MealMapper.insertMeal", meal);
		session.commit();
		session.close();
	}

	public List<Meal> getAllMealData() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Meal> meals = session.selectList("com.database.MealMapper.findAllMeal");
		session.close();
		return meals;
	}
	
	public void deleteMealById(int MID) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            session.delete("com.database.MealMapper.deleteMealById", MID);
            session.commit();
        }
	}
	
	// customer
	public void saveCustomer(Customer customer) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.database.CustomerMapper.insertCustomer", customer);
		session.commit();
		session.close();
	}

	public List<Customer> getAllCustomerData() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Customer> customers = session.selectList("com.database.CustomerMapper.findAllCustomer");
		session.close();
		return customers;
	}
	
	public void deleteCustomerById(int CID) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            session.delete("com.database.CustomerMapper.deleteCustomerById", CID);
            session.commit();
        }
	}
	
	// complex selections
	public Meal getMealById(int MID) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Meal meal = session.selectOne("com.database.MealMapper.findMealById", MID);
		session.close();
		return meal;
	}
	
	public Customer getCustomerById(int CID) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Customer customer = session.selectOne("com.database.CustomerMapper.findCustomerById", CID);
		session.close();
		return customer;
	}
	
	public Meal getExpensiveMeal() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Meal meal = session.selectOne("com.database.MealMapper.selectExpensiveMeal");
		session.close();
		return meal;
	}
	
	public Meal getCheapMeal() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Meal meal = session.selectOne("com.database.MealMapper.selectCheapMeal");
		session.close();
		return meal;
	}

	public List<MealOrder> findAllCustomerRelationships() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<MealOrder> mealorder = session.selectList("com.database.MealOrderMapper.findAllCustomerRelationships");
		session.close();
		return mealorder;
	}
	
	public List<Customer> findCustomersWhoOrdered() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Customer> customers = session.selectList("com.database.MealOrderMapper.findCustomersWhoOrdered");
		session.close();
		return customers;
	}
	
	// mealorder
	public void saveMealOrder(MealOrder mealorder) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.database.MealOrderMapper.insertMealOrder", mealorder);
		session.commit();
		session.close();
	}

	public void deleteMealOrderForCustomer(int CID) {
		try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            session.delete("com.database.MealOrderMapper.deleteMealOrderForCustomer", CID);
            session.commit();
            session.close();
        }
	}
	
}

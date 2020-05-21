package com.project.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.UserDao;
import com.project.entity.User;

@Repository(value = "userDaoImpl")
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public void updateUser(User user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	public void deleteUser(int id) {
		sessionFactory.getCurrentSession().delete(this.getUserById(id));
	}

	public User getUserById(int id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	public User getUserByName(String name, String password) {
		String hql = "FROM User WHERE name = :nameUser AND password = :passwordUser";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nameUser", name);
		query.setParameter("passwordUser", password);
		return (User) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		String hql = "FROM User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}

package com.jthink.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public abstract class BaseService<T> {

	@Autowired
	protected Mapper<T> mapper;

	public Mapper<T> getMapper() {
		return mapper;
	}

	public List<T> selectAll() {
		return mapper.selectAll();
	}

	public T selectByKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Transactional
	public int save(T entity) {
		return mapper.insert(entity);
	}

	@Transactional
	public int delete(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Transactional
	public int deleteByExample(Object example) {
		return mapper.deleteByExample(example);
	}

	@Transactional
	public int batchDelete(List<String> list, String property, Class<T> clazz) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(property, list);
		return this.mapper.deleteByExample(example);
	}

	@Transactional
	public int updateAll(T entity) {
		return mapper.updateByPrimaryKey(entity);
	}

	@Transactional
	public int updateNotNull(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Transactional
	public int updateNotNullByExample(T record, Example example) {
		return mapper.updateByExampleSelective(record, example);
	}

	public List<T> selectByExample(Object example) {
		return mapper.selectByExample(example);
	}

}

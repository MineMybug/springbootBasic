package com.ruanhang.springbootBasic.mapper;

import java.util.List;

import com.ruanhang.springbootBasic.entity.UserEntity;
import com.ruanhang.springbootBasic.param.UserPage;

/** 
 * @author  阮航  
 * @date 创建时间：2017年11月22日 上午11:04:47 
 * @version 1.0 
*/
public interface UserMapper {

	List<UserEntity> getAll();
	
	void insertUser (UserEntity userEntity);
	
	int insert(UserEntity user);

    int update(UserEntity user);

    int delete(Long id);
    
    UserEntity getOne(Long id);
    
    List<UserEntity> getList(UserPage userEntity);
    
    int getCount(UserPage userEntity);
}


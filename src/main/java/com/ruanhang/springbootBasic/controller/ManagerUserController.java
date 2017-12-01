package com.ruanhang.springbootBasic.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruanhang.springbootBasic.entity.UserEntity;
import com.ruanhang.springbootBasic.mapper.UserMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 阮航
 * @date 创建时间：2017年11月28日 下午6:32:15
 * @version 1.0
 */
@RestController
public class ManagerUserController {
	
	@Resource
	private UserMapper userMapper;

	@ApiOperation(value = "查找所有用户", notes = "查找所有用户")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public List<UserEntity> findAll() {
		List<UserEntity> userEntities = userMapper.getAll();
		return userEntities;
	}

	@ApiOperation(value = "获一个用户信息", notes = "根据url的id来获取详细信息")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/getOne", method = RequestMethod.GET)
	public UserEntity getOne(@PathVariable Long id) {
		return userMapper.getOne(id);
	}

	@ApiOperation(value = "增加用户", notes = "增加用户")
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public void insertUser(@RequestBody UserEntity userEntity) {
		UserEntity user = new UserEntity();
		user.setUserName(userEntity.getUserName());
		user.setUserSex(userEntity.getUserSex());
		user.setId(userEntity.getId());
		user.setPassWord(userEntity.getPassWord());
		user.setNickName(userEntity.getNickName());
		userMapper.insert(user);
	}

	@ApiOperation(value = "修改用户信息", notes = "修改用户信息")
	@RequestMapping(value = "/updateUserEntity", method = RequestMethod.POST)
	public int updateUserEntity(Long id, @RequestBody UserEntity userEntity) {
		int num = userMapper.update(id, userEntity);
		return num;
	}

	@ApiIgnore
	@RequestMapping("/testhello")
	public String hello() {
		return "hello";
	}
}

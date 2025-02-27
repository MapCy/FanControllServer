package com.moon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moon.common.pojo.MoonResult;
import com.moon.mapper.TbContactMapper;
import com.moon.pojo.TbContact;
import com.moon.pojo.TbContactExample;
import com.moon.pojo.TbContactExample.Criteria;
import com.moon.service.ContactService;

/*
 * 联系人管理Service
 */
@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private TbContactMapper contactMapper;
	
	@Override
	public MoonResult getAllContact() {
		//返回所有联系人
		TbContactExample example = new TbContactExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull();
		
		List<TbContact> list = contactMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return MoonResult.ok(list);
		}
		return MoonResult.ok();
	}

	@Override
	public MoonResult addContact(TbContact contact) {
		//补全Contact对象
		contact.setId(null);
		
		contactMapper.insert(contact);
		
		return MoonResult.ok();
	}

	@Override
	public void delContact(String number) {
		TbContactExample example = new TbContactExample();
		Criteria criteria = example.createCriteria();
		criteria.andNumberEqualTo(number);
		//删除该条记录
		contactMapper.deleteByExample(example);
	}

	@Override
	public void updateContact(TbContact contact) {
		//补全Contact对象
		TbContact con = new TbContact();
		con.setId(null);
		con.setNumber(contact.getNumber());
		con.setName(contact.getName());
		con.setPhone(contact.getPhone());
		con.setEmail(contact.getEmail());
		con.setRoom(contact.getRoom());
		
		contactMapper.updateByPrimaryKey(con);
	}

	@Override
	public TbContact selectByNumber(String number) {
		TbContactExample example = new TbContactExample();
		Criteria criteria = example.createCriteria();
		criteria.andNumberEqualTo(number);
		
		List<TbContact> list = contactMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}

package com.brycen.hrm.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.model.SkillEntity;
import com.brycen.hrm.repository.SkillRepository;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.response.object.SkillResponse;
import com.brycen.hrm.service.SkillService;
import com.brycen.hrm.status.BaseConvert;
import com.brycen.hrm.status.Code;

@Service
public class SkillImpl implements SkillService {

	@Autowired
	private BaseConvert baseCovert;

	@Autowired
	private SkillRepository skillRepos;

	/**
	 * Lấy danh sách tất cả skill
	 */
	@Override
	public Response getAllSkill() {
		Response response = new Response();
		try {
			List<SkillEntity> list = skillRepos.findAll();
			List<SkillResponse> result = new ArrayList<SkillResponse>();

			for (SkillEntity entity : list) {
				SkillResponse resSkill = baseCovert.skillToRespose(entity);
				result.add(resSkill);
			}

			response.setData(result);

		} catch (Exception e) {
			response.setCode(Code.unknown);
			response.setMessage(e.getMessage());
		}
		return response;
	}
}

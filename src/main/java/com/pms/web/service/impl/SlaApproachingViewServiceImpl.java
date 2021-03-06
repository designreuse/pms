package com.pms.web.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.jpa.entities.SlaApproachingView;
import com.pms.jpa.repositories.SlaApproachingRepo;
import com.pms.web.service.SlaApproachingViewService;

@Service("slaApproachinService")
public class SlaApproachingViewServiceImpl implements SlaApproachingViewService {

	@Autowired
	private SlaApproachingRepo slaApproachingRepo;

	@Override
	public List<SlaApproachingView> findTicketsApproachingSLA(final int noOfDays) {
		return null;
	}

	@Override
	public List<SlaApproachingView> findTicketsApproachingSLA() {
		List<SlaApproachingView> slaDataViews = slaApproachingRepo.findAll();
		return slaDataViews == null?Collections.EMPTY_LIST:slaDataViews;
	}


}

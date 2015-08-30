package vn.edu.fpt.timetabling.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScheduleAndRoomServiceImpl {

}

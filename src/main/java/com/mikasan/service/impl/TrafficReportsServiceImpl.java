package com.mikasan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikasan.mapper.TrafficReportsMapper;
import com.mikasan.pojo.TrafficReports;
import com.mikasan.service.TrafficReportsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 31402
* @description 针对表【traffic_reports(流量安全威胁报告表)】的数据库操作Service实现
* @createDate 2025-08-22 20:40:05
*/
@Service
public class TrafficReportsServiceImpl extends ServiceImpl<TrafficReportsMapper, TrafficReports>
    implements TrafficReportsService{

    @Override
    public TrafficReports getTrafficReportById(String id) {
        return this.getById(id);
    }

    @Override
    public List<TrafficReports> getAllTrafficReports() {
        return this.list();
    }
}
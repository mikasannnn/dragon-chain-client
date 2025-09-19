package com.mikasan.service;

import com.mikasan.pojo.TrafficReports;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 31402
* @description 针对表【traffic_reports(流量安全威胁报告表)】的数据库操作Service
* @createDate 2025-08-22 20:40:05
*/
public interface TrafficReportsService extends IService<TrafficReports> {
    /**
     * 根据ID获取流量安全报告
     *
     * @param id 报告ID
     * @return 流量安全报告
     */
    TrafficReports getTrafficReportById(String id);

    /**
     * 获取所有流量安全报告
     *
     * @return 流量安全报告列表
     */
    List<TrafficReports> getAllTrafficReports();
}
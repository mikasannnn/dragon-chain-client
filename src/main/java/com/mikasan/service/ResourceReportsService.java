package com.mikasan.service;

import com.mikasan.pojo.ResourceReports;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 31402
* @description 针对表【resource_reports(资源安全报告表)】的数据库操作Service
* @createDate 2025-08-22 20:40:05
*/
public interface ResourceReportsService extends IService<ResourceReports> {
    /**
     * 根据ID获取资源安全报告
     *
     * @param id 报告ID
     * @return 资源安全报告
     */
    ResourceReports getResourceReportById(String id);

    /**
     * 获取所有资源安全报告
     *
     * @return 资源安全报告列表
     */
    List<ResourceReports> getAllResourceReports();
}
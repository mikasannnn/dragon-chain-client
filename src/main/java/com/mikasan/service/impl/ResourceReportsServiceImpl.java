package com.mikasan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikasan.mapper.ResourceReportsMapper;
import com.mikasan.pojo.ResourceReports;
import com.mikasan.service.ResourceReportsService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
* @author 31402
* @description 针对表【resource_reports(资源安全报告表)】的数据库操作Service实现
* @createDate 2025-08-22 20:40:05
*/
//@Slf4j
@Service
public class ResourceReportsServiceImpl extends ServiceImpl<ResourceReportsMapper, ResourceReports>
    implements ResourceReportsService{

    @Override
    public ResourceReports getResourceReportById(String id) {
        return this.getById(id);
    }

    @Override
    public List<ResourceReports> getAllResourceReports() {
        return this.list();
    }
}  

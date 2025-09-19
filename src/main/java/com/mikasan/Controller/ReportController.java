package com.mikasan.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mikasan.common.Result;
import com.mikasan.config.HyperLedgerFabricProperties;
import com.mikasan.pojo.ResourceReports;
import com.mikasan.pojo.TrafficReports;
import com.mikasan.service.ResourceReportsService;
import com.mikasan.service.TrafficReportsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ReportController Controller
 *
 * @author MiKaSan
 * @Description : 资源安全报告控制器，用于管理资源安全报告的增删改查操作
 * @since 2025/8/22 20:44
 */

@RestController
@RequestMapping("/report")
@Slf4j
@AllArgsConstructor
@CrossOrigin
@Tag(name = "报告管理", description = "提供资源安全报告和流量安全威胁报告的管理功能")
public class ReportController {

    final Contract contract;

//    final HyperLedgerFabricProperties hyperLedgerFabricProperties;

//    final Gateway gateway;

    private final Gson gson = new Gson();

    @Autowired
    private ResourceReportsService resourceReportsService;

    @Autowired
    private TrafficReportsService trafficReportsService;



    @PostMapping("/addResourceReport")
    @Transactional
    @Operation(summary = "添加资源安全报告")
    public Result<String> addResourceReport(@RequestBody ResourceReports reports) {
        try {
            log.info("接收到资源报告: {}", reports);
            contract.submitTransaction("addResourceReport", gson.toJson(reports));
            // 保存到数据库
            resourceReportsService.save(reports);
            return Result.success("资源安全报告添加成功",null);
        } catch (Exception e) {
            log.error("添加资源报告失败", e);
            return Result.fail("添加资源报告失败: " + e.getMessage());
        }
    }

    @GetMapping("/getResourceReport/{id}")
    @Operation(summary = "根据ID获取资源安全报告")
    public Result<ResourceReports> getResourceReport(@PathVariable String id) {
        try {
            byte[] resultBytes = contract.evaluateTransaction("getResourceReport",id);
            String resultJson = StringUtils.newStringUtf8(resultBytes);
            log.info("从智能合约返回的二进制数据: {}", resultBytes);
            if (resultJson != null && !resultJson.isEmpty()) {
                // 反序列化成 ResourceReports 对象
                ResourceReports report = gson.fromJson(resultJson, ResourceReports.class);

                // 检查字段是否为空
                if (report.getId() == null || report.getReportName() == null) {
                    return Result.fail("报告数据缺失，ID：" + id);
                }

                return Result.success("获取资源安全报告成功", report);
            } else {
                return Result.fail("未找到ID为 " + id + " 的资源安全报告");
            }
            // 从数据库获取
//            ResourceReports report = resourceReportsService.getResourceReportById(id);
//            if (resultJson != null) {
//                return Result.success("获取资源安全报告成功", resultJson);
//            } else {
//                return Result.fail("未找到ID为 " + id + " 的资源安全报告");
//            }
        } catch (Exception e) {
            log.error("获取资源报告失败", e);
            return Result.fail("获取资源报告失败: " + e.getMessage());
        }
    }

    @GetMapping("/getResourceReports")
    @Operation(summary = "获取所有资源安全报告")
    public Result<List<ResourceReports>> getAllResourceReports() {

        try {
            // 从智能合约中获取所有资源报告
            byte[] resultBytes = contract.evaluateTransaction("getAllResourceReport");
            String resultJson = StringUtils.newStringUtf8(resultBytes);
            if (resultJson != null && !resultJson.isEmpty()) {

                // 解析 JSON 字符串为 List<ResourceReports>
                List<ResourceReports> reportList = gson.fromJson(resultJson, new TypeToken<List<ResourceReports>>(){}.getType());

                return Result.success("获取所有资源安全报告成功", reportList);
            }else {
                return Result.fail("未找到任何资源安全报告");
            }
            // 从数据库获取所有资源报告
//            List<ResourceReports> reports = resourceReportsService.getAllResourceReports();
        } catch (Exception e) {
            log.error("获取所有资源报告失败", e);
            return Result.fail("获取所有资源报告失败: " + e.getMessage());
        }
    }

    @PostMapping("/addTrafficReport")
    @Transactional
    @Operation(summary = "添加流量安全报告")
    public Result<String> addTrafficReport(@RequestBody TrafficReports reports) {
        try {
            String trafficId = "Traffic" + reports.getId();
            log.info("接收到流量报告: {}", reports);
            contract.submitTransaction("addTrafficReport", gson.toJson(reports));
            // 保存到数据库
            trafficReportsService.save(reports);
            return Result.success("流量安全报告添加成功", "流量安全报告添加成功");
        } catch (Exception e) {
            log.error("添加流量报告失败", e);
            return Result.fail("添加流量报告失败: " + e.getMessage());
        }
    }

    @GetMapping("/getTrafficReport/{id}")
    @Operation(summary = "根据ID获取流量安全报告")
    public Result<TrafficReports> getTrafficReport(@PathVariable String id) {
        try {
            byte[] resultBytes = contract.evaluateTransaction("getTrafficReport", id);
            log.info("从智能合约返回的二进制数据: {}", resultBytes);
            String resultJson = StringUtils.newStringUtf8(resultBytes);
            log.info("从智能合约返回的原始数据: {}", resultJson);

            if (resultJson != null && !resultJson.isEmpty()) {
                // 反序列化成 TrafficReports 对象
                TrafficReports report = gson.fromJson(resultJson, TrafficReports.class);

                // 检查字段是否为空
                if (report.getId() == null || report.getReportName() == null) {
                    return Result.fail("报告数据缺失，ID：" + id);
                }

                return Result.success("获取流量安全报告成功", report);
            } else {
                return Result.fail("未找到ID为 " + id + " 的流量安全报告");
            }
            // 从数据库获取
//            TrafficReports report = trafficReportsService.getTrafficReportById(id);
//            if (report != null) {
//                return Result.success("获取流量安全报告成功", report);
//            } else {
//                return Result.fail("未找到ID为 " + id + " 的流量安全报告");
//            }
        } catch (Exception e) {
            log.error("获取流量报告失败", e);
            return Result.fail("获取流量报告失败: " + e.getMessage());
        }
    }

    @GetMapping("/getTrafficReports")
    @Operation(summary = "获取所有流量安全报告")
    public Result<List<TrafficReports>> getAllTrafficReports() {
        try {
            byte[] resultBytes = contract.evaluateTransaction("getAllTrafficReports");
            String resultJson = StringUtils.newStringUtf8(resultBytes);
            if(resultJson != null && !resultJson.isEmpty()){

                // 解析 JSON 字符串为 List<TrafficReports>
                List<TrafficReports> reportList = gson.fromJson(resultJson, new TypeToken<List<TrafficReports>>(){}.getType());

                return Result.success("获取所有流量安全报告成功", reportList);
            }else {
                return Result.fail("未找到任何流量安全报告");
            }
            // 从数据库获取所有流量报告
//            List<TrafficReports> reports = trafficReportsService.getAllTrafficReports();
//            return Result.success("获取所有流量安全报告成功", reports);
        } catch (Exception e) {
            log.error("获取所有流量报告失败", e);
            return Result.fail("获取所有流量报告失败: " + e.getMessage());
        }
    }
}
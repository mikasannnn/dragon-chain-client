package com.mikasan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.util.Map;

/**
 * @TableName resource_reports
 */
@TableName(value ="resource_reports")
@ApiModel(value = "ResourceReports对象", description = "资源报告实体类")
@Data
public class ResourceReports {
    private String id ;

    private String reportName;

    private String gatewayName;

    private String content;

    private Long reportTime;

    @TableField(typeHandler = JacksonTypeHandler.class, jdbcType = JdbcType.VARCHAR)
    private Map<String,String> details;


}
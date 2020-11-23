package test.thread.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 快舟物流合同表
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="FastBoatCon对象", description="快舟物流合同表")
public class FastBoatCon extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;

    private Integer supplierId;

    private String supplierName;

    @ApiModelProperty(value = "指定费率")
    private String rate;

    private String note;

    @ApiModelProperty(value = "合同开始时间")
    private String formDate;

    @ApiModelProperty(value = "合同结束时间")
    private String toDate;

    @ApiModelProperty(value = "合同号")
    private String contractNo;

    @ApiModelProperty(value = "发给电签的唯一key")
    private String uuid;

    @ApiModelProperty(value = "导入版本号")
    private String version;

    @ApiModelProperty(value = "状态0未发起，1已发起 2发起失败")
    private Integer status;

    @ApiModelProperty(value = "发起时间")
    private LocalDateTime reqDate;

    @ApiModelProperty(value = "发起log")
    private String reqLog;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updatedTime;


}

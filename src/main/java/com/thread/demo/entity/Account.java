package com.thread.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Account对象", description="")
@TableName(value = "account")
public class Account extends Model<Account> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账户名称")
    @TableField(value = "person_account", exist = true)
    private String personAccount;

    @ApiModelProperty(value = "账户金额")
    private Integer personMoney;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", personAccount='" + personAccount + '\'' +
                ", personMoney=" + personMoney +
                '}';
    }
}

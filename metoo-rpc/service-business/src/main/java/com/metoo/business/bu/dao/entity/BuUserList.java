package com.metoo.business.bu.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author loongya
 * @since 2021-01-06
 */
@Entity
@Table(name = "bu_user_list")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BuUserList对象", description="")
public class BuUserList extends Model<BuUserList> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String city;

    private String gander;

    private String name;

    private String number;

    private Integer sate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

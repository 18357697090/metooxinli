package com.metoo.pojo.tj.vo;

import com.loongya.core.util.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 用户举报表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TjReportVo extends BaseVo {
    private Long id;

    private Date creationTime;

    private String content;

    private String picture;

    private Integer uid;

    private Integer state;

    private String type;

    private Integer reportId;

}

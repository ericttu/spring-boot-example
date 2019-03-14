package dev.erictu.demoswagger.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * user
 *
 * @author eric.t.tu
 * @date 2019/3/14 10:35
 */
@Data
public class User {
    private Long id;

    private String name;

    private Integer age;
}

package com.ocean.showdoc.controller.param.user;

import lombok.*;

/**
 * @author oceanBin on 2020/3/30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResetPwdReq {

    private String password;
    private String new_password;
}

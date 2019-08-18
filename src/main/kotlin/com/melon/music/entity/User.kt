package com.melon.music.entity

import java.sql.Date
import javax.persistence.*

/**
 * 用户Entity
 *  id          主键id
 *  avator      头像
 *  name        姓名
 *  sex         性别
 *  birthday    生日
 *  address     地址
 *  description 描述
 *  password    密码
 *  mobile      手机号
 */
@Entity
@Table(name="t_user")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        var avatar: String,
        var name: String,
        @Column(name="sex", nullable = true, length = 1)
        var sex: String,
        @Column(name="birthday", nullable = true, columnDefinition = "DATE COMMENT '出生日期'")
        var birthday: Date,
        var address: String,
        var description: String,
        @Column(name="password", nullable = false)
        var password: String,
        var mobile: String
) {}
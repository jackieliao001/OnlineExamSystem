package com.rabbiter.oes.system.mapper;

import com.rabbiter.oes.exam.entity.Admin;
import com.rabbiter.oes.exam.entity.Student;
import com.rabbiter.oes.exam.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select user_id,user_name,sex,tel,id_card, email from `sys_user` where account_num = #{username} and pwd = #{password}")
    Admin adminLogin(@Param("username") String username, @Param("password") String password);

    @Select("select teacherId,teacherName,institute,sex,tel,email,cardId," +
            "type,role from teacher where teacherId = #{username} and pwd = #{password}")
    Teacher teacherLogin(@Param("username") Integer username, @Param("password") String password);

    @Select("select studentId,studentName,grade,major,clazz,institute,tel," +
            "email,cardId,sex,role from student where studentId = #{username} and pwd = #{password}")
    Student studentLogin(@Param("username") Integer username, @Param("password") String password);
}

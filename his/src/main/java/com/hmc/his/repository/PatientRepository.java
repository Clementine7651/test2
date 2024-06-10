package com.hmc.his.repository;

import com.hmc.his.model.Patient;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatientRepository {
    List<Patient> selectPatients();
    @Select("select name from patient")
    List<String> selectPatientNames();

    @Select("select * from patient where id = #{id}")
    Patient selectPatientById(Integer id);

    @Insert("insert into patient(id, name) values (#{id}, #{name})")
    int insertPatients(Patient patient);

    @Update("update patient set id=#{id}, name=#{name} where id=#{id}")
    int updatePatients(Patient patient);

    @Delete("delete from patient where id=#{id}")
    int deletePatients(Integer id);

}

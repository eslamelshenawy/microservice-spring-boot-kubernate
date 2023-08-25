package com.example.dbservice.repository;

import com.example.dbservice.dto.LanguageDTO;
import com.example.dbservice.model.Employee;
import com.example.dbservice.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {

    @Query(value = "SELECT nls_language value ,initcap(nls_language) id FROM apps.fnd_languages WHERE installed_flag IN ('B', 'I')", nativeQuery = true)
    List<Language> getLanguages();

    @Query(value = "select * from xxmob.xxx_mob_hr_emp_main_v where person_id = 1014804 ", nativeQuery = true)
    Employee getEmployeeData();

}

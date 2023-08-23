package com.example.dbservice.repository;

import com.example.dbservice.dto.LanguageDTO;
import com.example.dbservice.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {

    @Query(value = "SELECT nls_language value ,initcap(nls_language) id FROM apps.fnd_languages WHERE installed_flag IN ('B', 'I')", nativeQuery = true)
    List<Language> getLanguages();

    @Query(value = "declare\n" +
            "luserid number:=0;\n" +
            "lresult varchar2 (2500):=0;\n" +
            "begin\n" +
            "lresult:=XXMOB.XXXMOBILELOGIN.VALIDATELOGIN ('A.RADWAN','R123456', 'ARABIC', luserid);\n" +
            "dbmsoutput.putline(lresult);\n" +
            "dbmsoutput.putline(luser_id);\n" +
            "end;", nativeQuery = true, countQuery = "select 1 from dual")
    String executeNativeQuery();

}

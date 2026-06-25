package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m from Member m WHERE m.name=:name")
    List<Member> findMember(@Param("name") String name);

    @Query("SELECT m from Member m WHERE m.name=:name AND m.email=:email")
    List<Member> findMember(@Param("name") String name, @Param("email") String email);
}

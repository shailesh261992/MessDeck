package com.app.messdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.messdeck.entity.MessDeckServiceInfo;

public interface MessDeckServiceInfoRepository extends JpaRepository<MessDeckServiceInfo, Long> {

}

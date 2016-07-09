package com.app.messdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.messdeck.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}

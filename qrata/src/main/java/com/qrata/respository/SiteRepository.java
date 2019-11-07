package com.qrata.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrata.models.Site;

public interface SiteRepository extends JpaRepository<Site, Long>{

}

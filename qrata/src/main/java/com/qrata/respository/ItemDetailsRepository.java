package com.qrata.respository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qrata.models.ItemDetails;
import com.qrata.models.SiteReview;

@Repository
public interface ItemDetailsRepository extends JpaRepository<ItemDetails, Long> {

	@Query("SELECT sr from ItemDetails sr where UPPER(sr.itemName) LIKE UPPER(concat('%', :description,'%')) or UPPER(sr.itemDescription) LIKE UPPER(concat('%', :description,'%'))")
	public abstract List<ItemDetails> qrataSearchKeywords_SearchTerm1(@Param("description") String searchTerm);
	
}

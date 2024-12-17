package com.example.solrDemo.api.repository;


import com.example.solrDemo.api.models.UnNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnNumberJpaRepository extends JpaRepository<UnNumbers, String> {

}

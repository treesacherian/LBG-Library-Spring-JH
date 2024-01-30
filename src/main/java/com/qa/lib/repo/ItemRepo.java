package com.qa.lib.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.lib.domain.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}

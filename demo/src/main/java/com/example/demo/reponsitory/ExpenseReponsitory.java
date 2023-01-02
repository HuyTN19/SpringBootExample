package com.example.demo.reponsitory;

import com.example.demo.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ExpenseReponsitory extends JpaRepository<Expense, Long> {
    // SELECT * From tbl_expenses where category = ?
    Page<Expense> findByCategory(String category, Pageable page);

    // SELECT * FROM tbl_expenses WHERE name LIKE '%Keyword%'
    Page<Expense> findByNameContaining(String keyword, Pageable page);

    //SELECT * FROM tbl_expenses WHERE date BETWEEN 'startDate' and 'endDate'
    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);
}

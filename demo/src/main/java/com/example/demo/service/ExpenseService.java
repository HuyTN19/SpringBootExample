package com.example.demo.service;


import com.example.demo.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ExpenseService {
    Page<Expense> getAll(Pageable page);

    Expense getExpenseById(long id);

    void deleteOne(long id);

    Expense saveExpense(Expense expense);

    Expense updateExpensesDetails(Long id, Expense expense);
    List<Expense> readByCategory(String category, Pageable page);

    List<Expense> readByName(String keyword, Pageable page);

    List<Expense> readByDate(Date startDate, Date endDate, Pageable page);

}
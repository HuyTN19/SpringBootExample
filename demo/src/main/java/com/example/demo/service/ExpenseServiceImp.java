package com.example.demo.service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.entity.Expense;
import com.example.demo.reponsitory.ExpenseReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImp implements ExpenseService{
    @Autowired
    private ExpenseReponsitory reponsitory;


    @Override
    public Page<Expense> getAll(Pageable page) {
        return  reponsitory.findAll(page);

    }

    @Override
    public Expense getExpenseById(long id) {
        Optional<Expense> expense = reponsitory.findById(id);
        if(expense.isPresent()) {
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense id not found for the id " + id);
    }

    @Override
    public void deleteOne(long id) {
       Expense expense=  getExpenseById(id);
        reponsitory.delete(expense);
    }

    @Override
    public Expense saveExpense(Expense expense) {
        return reponsitory.save(expense);
    }

    public Expense updateExpensesDetails(Long id, Expense expense){
        Expense existingExpenses = getExpenseById(id);
        existingExpenses.setName(expense.getName() != null ? expense.getName() : existingExpenses.getName());
        existingExpenses.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpenses.getDescription());
        existingExpenses.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpenses.getCategory());
        existingExpenses.setExpense_amount(expense.getExpense_amount() != null ? expense.getExpense_amount() : existingExpenses.getExpense_amount());
        existingExpenses.setDate(expense.getDate() != null ? expense.getDate() : existingExpenses.getDate());
        return reponsitory.save(existingExpenses);
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable page) {
        return reponsitory.findByCategory(category, page).toList();
    }

    @Override
    public List<Expense> readByName(String keyword, Pageable page) {
        return reponsitory.findByNameContaining(keyword, page).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
        if(startDate == null){
            startDate = new Date(0);
        }
        if (endDate == null ){
            endDate = new Date(System.currentTimeMillis());
        }
        Page<Expense> pages = reponsitory.findByDateBetween(startDate, endDate, page);
        return pages.toList();
    }
}

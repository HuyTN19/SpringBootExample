package com.example.demo.controler;

import com.example.demo.entity.Expense;
import com.example.demo.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService service;


    @GetMapping("/expenses")
    public Page<Expense> getAllExpenses(Pageable page){
        return service.getAll(page);
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id){
        return  service.getExpenseById(id);

    }
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public String deleteExpenseById(@RequestParam("id") Long id){
        service.deleteOne(id);
        return "Delete the expense object by its id " + id ;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense  saveExpensesDetails(@Valid @RequestBody Expense expense){
        return service.saveExpense(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetail(@RequestBody Expense expense, @PathVariable Long id){
        return service.updateExpensesDetails(id, expense);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page){
        return service.readByCategory(category, page);

    }

    @GetMapping("/expenses/name")
    public List<Expense> getAllExpensesByName(@RequestParam String keyword, Pageable page){
        return service.readByName(keyword, page);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getAllExpensesByDate(
            @RequestParam(required = false)String startDate,
            @RequestParam(required = false)String endDate,
            Pageable page) {
        Date dateStart = null;
        Date dateEnd = null;

        try {
            dateStart = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
            dateEnd = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);

        } catch (Exception exception) {
            exception.getMessage();
        }
        return service.readByDate(dateStart, dateEnd, page);
    }
}

package com.modeln.expense.expenses_tracker.service;

import com.modeln.expense.expenses_tracker.modeln.Expense;
import com.modeln.expense.expenses_tracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpense(String name) {
        return expenseRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Cannot Find Expense by Name - %s", name)));
    }
    public  void updateExpense(Expense expense){
        Expense savedExpense = expenseRepository.findById(expense.getId()).orElseThrow(() -> new RuntimeException(String.format("Cannot Find Expense by ID %s", expense.getId())));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpeseAmount(expense.getExpeseAmount());
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}

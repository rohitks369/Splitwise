package controller;

import java.util.*;

import Service.ExpenseService;
import expense.Expense;
import expense.ExpenseMetaData;
import expense.ExpenseType;
import models.Split;
import models.User;

public class ExpenseManager {
	List<Expense> expenses;
	Map<String, User> userMap;
	Map<String,Map<String,Double>> balanceSheet;
	
	public ExpenseManager() {
		expenses=new ArrayList<Expense>();
		userMap=new HashMap<String, User>();
		balanceSheet=new HashMap<String, Map<String,Double>>();
	}
	
	public void addUser(User user) {
		userMap.put(user.getId(),user);
		balanceSheet.put(user.getId(), new HashMap<String, Double>());
	}
	
	public void addExpense(ExpenseType expenseType,double amount,String paidBy, List<Split> splits,ExpenseMetaData expenseMetaData) {
		Expense expense=ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits, expenseMetaData);
		expenses.add(expense);
	}
}

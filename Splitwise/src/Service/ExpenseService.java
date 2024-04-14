package Service;

import java.util.List;

import expense.EqualExpense;
import expense.ExactExpense;
import expense.Expense;
import expense.ExpenseMetaData;
import expense.ExpenseType;
import expense.PercentExpense;
import models.PercentSplit;
import models.Split;
import models.User;

public class ExpenseService {
	public static Expense createExpense(ExpenseType expenseType,double amount,User paidBy, List<Split> splits,ExpenseMetaData expenseMetaData) {
		switch(expenseType) {
		case EXACT:
			return new ExactExpense(amount, paidBy, splits, expenseMetaData);
		case PERCENT:
			for (Split split : splits) {
                PercentSplit percentSplit = (PercentSplit) split;
                split.setAmount((amount*percentSplit.getPercent())/100.0);
            }
            return new PercentExpense(amount, paidBy, splits, expenseMetaData);
		case EQUAL:
			int totalSplits=splits.size();
			double splitAmount=((double)Math.round(amount*100/totalSplits))/100.0;
			for(Split split:splits) {
				split.setAmount(splitAmount);
			}
			splits.get(0).setAmount(splitAmount+(amount-splitAmount*totalSplits));
			return new EqualExpense(amount, paidBy, splits, expenseMetaData);
		default:
			return null;
		}
	}
}

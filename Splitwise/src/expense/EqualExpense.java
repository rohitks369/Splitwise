package expense;

import java.util.List;

import models.EqualSplit;
import models.Split;
import models.User;

public class EqualExpense extends Expense{

	
	public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metadata) {
		super(amount, paidBy, splits, metadata);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate() {
		for(Split split:getSplits()) {
			if(!(split instanceof EqualSplit)) {
				return false;
			}
		}
		return false;
	}

}

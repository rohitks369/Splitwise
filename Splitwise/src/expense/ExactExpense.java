package expense;

import java.util.List;

import models.ExactSplit;
import models.Split;
import models.User;

public class ExactExpense extends Expense {

	public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metadata) {
		super(amount, paidBy, splits, metadata);
	}

	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
			if (!(split instanceof ExactSplit)) {
				return false;
			}
		}

		// verify the total sum of shares is equal to the total amount
		double totalAmount = getAmount();
		double sumSplitAmount = 0;
		for (Split split : getSplits()) {
			ExactSplit exactSplit =  (ExactSplit) split;
			sumSplitAmount += exactSplit.getAmount();
		}

		if (totalAmount != sumSplitAmount) {
			return false;
		}

		return true;
	}
}

import java.util.Scanner;
public class FracCalc {
  //produceAnswer method
	public static String produceAnswer(String expression) {
		//Scanner
		String input = expression;
    if (!input.contains(" + ") && !input.contains(" - ") && !input.contains(" * ") && !input.contains(" / ")) {
      return "ERROR: Invalid format!";
    }
		String operand1 = input.substring(0, input.indexOf(" "));
		String operator = input.substring(input.indexOf(" ") + 1, input.indexOf(" ") + 2);
		String operand2 = input.substring(input.indexOf(" ") + 3, input.length());
		
		//Declaring variables
		String whole = "0";
		String numerator = "0";
		String denominator = "1";
		int w_int;
		int w_int2;
		int n_int;
		int n_int2;
		int d_int;
		int d_int2;
		
		//Parse first operand
		if (operand1.contains("_") && operand1.contains("/")) {
			whole = operand1.substring(0, operand1.indexOf("_"));
			numerator = operand1.substring(operand1.indexOf("_") + 1, operand1.indexOf("/"));
			denominator = operand1.substring(operand1.indexOf("/") + 1, operand1.length());
			w_int = Integer.parseInt(whole);
		    n_int = Integer.parseInt(numerator);
		    d_int = Integer.parseInt(denominator);
        //Divide by zero
		    if (d_int == 0) {
		        return "ERROR: Cannot divide by zero.";
		    }
		}
		else if (!operand1.contains("_") && operand1.contains("/")) {
			whole = "0";
			numerator = operand1.substring(0, operand1.indexOf("/"));
			denominator = operand1.substring(operand1.indexOf("/") + 1, operand1.length());
			w_int = Integer.parseInt(whole);
		    n_int = Integer.parseInt(numerator);
		    d_int = Integer.parseInt(denominator);
        //Divide by zero
		    if (d_int == 0) {
		        return "ERROR: Cannot divide by zero.";
		    }
		}
		else {
			whole = operand1;
			w_int = Integer.parseInt(whole);
		    n_int = Integer.parseInt(numerator);
		    d_int = Integer.parseInt(denominator);
		}
		
		//Reset back to original state
		whole = "0";
		numerator = "0";
		denominator = "1";
		
		//Parse second operand
		if (operand2.contains("_") && operand2.contains("/")) {
			whole = operand2.substring(0, operand2.indexOf("_"));
			numerator = operand2.substring(operand2.indexOf("_") + 1, operand2.indexOf("/"));
			denominator = operand2.substring(operand2.indexOf("/") + 1, operand2.length());
			w_int2 = Integer.parseInt(whole);
		    n_int2 = Integer.parseInt(numerator);
		    d_int2 = Integer.parseInt(denominator);
        //Divide by zero
		    if (d_int2 == 0) {
		        return "ERROR: Cannot divide by zero.";
		    }
		}
		else if (!operand2.contains("_") && operand2.contains("/")) {
			numerator = operand2.substring(0, operand2.indexOf("/"));
			denominator = operand2.substring(operand2.indexOf("/") + 1, operand2.length());
			w_int2 = Integer.parseInt(whole);
		    n_int2 = Integer.parseInt(numerator);
		    d_int2 = Integer.parseInt(denominator);
        //Divide by zero
		    if (d_int2 == 0) {
		        return "ERROR: Cannot divide by zero.";
		    }
		}
		else {
			whole = operand2;
			w_int2 = Integer.parseInt(whole);
		    n_int2 = Integer.parseInt(numerator);
		    d_int2 = Integer.parseInt(denominator);
		}
		
		//Handling Negatives
		if(w_int < 0) {
	        n_int *= -1;
	    }
	    if(w_int2 < 0) {
	        n_int2 *= -1;
	    }
		
	    //Conversion to improper fractions
		n_int = (w_int * d_int) + n_int;
		n_int2 = (w_int2 * d_int2) + n_int2;
		
		//Declaring more variables for solving and simplifying
		int newNum;
		int newDen;
		
		//Calculations
		if (operator.equals("+")){
			newNum = (n_int * d_int2) + (n_int2 * d_int);
			newDen = d_int * d_int2;
		}
		else if (operator.equals("-")) {
			newNum = (n_int * d_int2) - (n_int2 * d_int);
			newDen = d_int * d_int2;
		}
		else if (operator.equals("*")) {
			newNum = n_int * n_int2;
			newDen = d_int * d_int2;
		}
		else if (operator.equals("/")) {
			newNum = n_int * d_int2;
			newDen = d_int * n_int2;
		}
		else {
			return "ERROR: Invalid operator!";
		}
		
		//If new numerator is 0
		if (newNum == 0) {
			return ("0");
		}
		
		//Simplifying
		int gcd = 0;
		boolean negative = false;
		if (newNum < 0) {
			newNum *= -1;
			negative = true;
		}
		if (newDen < 0) {
			newDen *= -1;
			negative = true;
		}
		if (newNum > newDen) {
			int wNum = newNum / newDen;
			newNum = newNum - (wNum * newDen);
			for (int i = newNum + newDen ; i>0 ; i--) {
				if (newNum % i == 0 && newDen % i == 0) {
					gcd = i;
					newNum = newNum / gcd;
					newDen = newDen / gcd;
					break;
				}
				else {
					gcd = 1;
				}
		    }
			if (newNum >= 0 && negative == true) {
		    	wNum *= -1;
		    }
		    if (newNum == 0) {
		    	return String.format(wNum + " ");
		    }
		    return String.format(wNum + "_" + newNum + "/" + newDen);
		    }
		  	else {
		  		for (int i = newNum + newDen; i>0; i--) {
		  			if (newNum % i == 0 && newDen % i == 0) {
		  					gcd = i;
		  					newNum = newNum / gcd;
		  					newDen = newDen / gcd;
		  					break;
		  			}
		  			else {
		  				gcd = 1;
		  			}
		  		}
		  		if (newNum > 0 && negative == true) {
			    	newNum *= -1;
		  		}
		  		if (newNum == newDen && negative == false) {
		  			return String.format("1");
		  		}
		  		else if (newNum == newDen && negative == true) {
		  			return String.format("-1");
		  		}
		  		if (newDen == 1 && negative == false) {
		  			return String.format(newNum + " ");
		  		}
		  		else if (newDen == 1 && negative == true) {
		  			return String.format(newNum * -1 + " ");
		  		}
		  		return String.format(newNum + "/" + newDen);
		  	}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean loop = true;
		while (loop == true) {
			System.out.println("Enter an expression or type QUIT to exit the program.");
			String expression = in.nextLine();
			if (expression.equalsIgnoreCase("quit")) {
				System.out.println("Goodbye.");
				in.close();
				loop = false;
			}
			else {
				System.out.println(produceAnswer(expression));
			}
		}
	}
}
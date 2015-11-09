import java.util.*;


class IRNodeList
{
	public List<IRNode> NodeList = new ArrayList<IRNode>();
	
	public List<Expr> ExprList;	
	public SymbolTableStack STS;

	public int TempCounter = 1;	

	public IRNodeList(ExprInterpreter EI, SymbolTableStack STS)
	{
		this.ExprList = EI.ExprList;
		this.STS = STS;
		this.Decode();
	}
		
	public void Decode()
	{
		for(Expr E : this.ExprList)
		{
			if(E.id == "WRITE") // currently assuming we are only writing one var per call of WRITE(...)
			{
				if(SymbolLookup(E.expr).equals("INT"))
				{
					this.NodeList.add(new IRNode("WRITEI", E.expr));
				}
				else if(SymbolLookup(E.expr).equals("FLOAT"))
				{
					this.NodeList.add(new IRNode("WRITEF", E.expr));
				}
			}
			else // an add/sub/mult/div or assign function
			{
				if(HelperFunctions.ExpressionType(E.expr) == 0) // assign function
				{
					if(SymbolLookup(E.id).equals("INT"))
					{
						this.NodeList.add(new IRNode("STOREI", E.expr, "$T" + String.valueOf(this.TempCounter)));
						this.NodeList.add(new IRNode("STOREI", "$T" + String.valueOf(this.TempCounter), E.id));
						this.TempCounter ++;
					}
					else if(SymbolLookup(E.id).equals("FLOAT"))
					{
						this.NodeList.add(new IRNode("STOREF", E.expr, "$T" + String.valueOf(this.TempCounter)));
						this.NodeList.add(new IRNode("STOREF", "$T" + String.valueOf(this.TempCounter), E.id));
						this.TempCounter ++;
					}
				}
				else // a line of expression(s)
				{
					if(SymbolLookup(E.id).equals("INT"))
					{
						String expr = E.expr.replaceAll("\\s","");
						if(HelperFunctions.CountOccurancesOf("(", expr) == 0)
						{
							StringBuilder builder = new StringBuilder(expr);
							//System.out.println("No Parenthesis: " + builder);
							String reduced = Reduce(builder, 1);
							//System.out.println(reduced);
							this.NodeList.add(new IRNode("STOREI", reduced, E.id));
						}
						else if(HelperFunctions.CountOccurancesOf("(", expr) == 1)
						{
							// Parenthesis Reduction(s) First
							StringBuilder builder = new StringBuilder(expr);
							int open = builder.indexOf("(");
							int close = builder.indexOf(")");
							StringBuilder subBuilder = new StringBuilder(builder.substring(open+1,close));
							//System.out.println(subBuilder);
							// Now simplify the subBuilder (stuff between parenthesis) to a single register
							String reduced = Reduce(subBuilder, 1);
							builder.replace(open, close+1, reduced);
							//System.out.println(builder);
							/* Reduce again for the rest of the equation now that the parenthesis are gone */
							reduced = Reduce(builder, 1);
							this.NodeList.add(new IRNode("STOREI", reduced, E.id));
						}
						else if(HelperFunctions.CountOccurancesOf("(", expr) > 1)
						{
							//System.out.println(expr + "multiple paren");
							String reduced;
							StringBuilder builder = new StringBuilder(expr);
							while(HelperFunctions.CountOccurancesOf('(', builder) != 0)
							{
								int open = builder.indexOf("(");
								int close = builder.indexOf(")");
								StringBuilder subBuilder = new StringBuilder(builder.substring(open+1,close));
								//System.out.println(subBuilder);
								reduced = Reduce(subBuilder, 1);
								builder.replace(open, close+1, reduced);
							}
							reduced = Reduce(builder, 1);
							this.NodeList.add(new IRNode("STOREI", reduced, E.id));
						}
					}
					else if(SymbolLookup(E.id).equals("FLOAT"))
					{
						//System.out.println("FLOAT FOUND, ABORT ABORT ABORT!");
						String expr = E.expr.replaceAll("\\s","");
						if(HelperFunctions.CountOccurancesOf("(", expr) == 0)
						{
							StringBuilder builder = new StringBuilder(expr);
							//System.out.println("No Parenthesis: " + builder);
							String reduced = ReduceF(builder);
							this.NodeList.add(new IRNode("STOREF", reduced, E.id));
						}
						else if(HelperFunctions.CountOccurancesOf("(", expr) == 1)
						{
							// Parenthesis Reduction(s) First
							StringBuilder builder = new StringBuilder(expr);
							int open = builder.indexOf("(");
							int close = builder.indexOf(")");
							StringBuilder subBuilder = new StringBuilder(builder.substring(open+1,close));
							//System.out.println(subBuilder);
							// Now simplify the subBuilder (stuff between parenthesis) to a single register
							String reduced = ReduceF(subBuilder);
							builder.replace(open, close+1, reduced);
							//System.out.println(builder);
							/* Reduce again for the rest of the equation now that the parenthesis are gone */
							reduced = ReduceF(builder);
							this.NodeList.add(new IRNode("STOREF", reduced, E.id));
						}
						else if(HelperFunctions.CountOccurancesOf("(", expr) > 1)
						{
							//System.out.println(expr + "multiple paren");
							String reduced;
							StringBuilder builder = new StringBuilder(expr);
							while(HelperFunctions.CountOccurancesOf('(', builder) != 0)
							{
								int open = builder.indexOf("(");
								int close = builder.indexOf(")");
								StringBuilder subBuilder = new StringBuilder(builder.substring(open+1,close));
								//System.out.println(subBuilder);
								reduced = ReduceF(subBuilder);
								builder.replace(open, close+1, reduced);
							}
							reduced = ReduceF(builder);
							this.NodeList.add(new IRNode("STOREF", reduced, E.id));
						}
					}
					
				}
			}
		}
	}

	public String Reduce(StringBuilder subBuilder, int order)
	{
		while(HelperFunctions.ExpressionType(subBuilder) != 0) // while expression still has an op in it
		{
			//System.out.println("Reducing: " + subBuilder);
			int multOp = subBuilder.indexOf("*");
			int divOp = subBuilder.indexOf("/");
			if(multOp != -1 && divOp == -1)
			{
				int i = multOp;
				int left = CheckLeftExpression(subBuilder, i);
				if(HelperFunctions.isInteger(subBuilder.substring(i-left, i).toString()))
				{
					this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i-left, i);
					subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
					if(this.TempCounter < 10)
					{
						i += 2;
					}
					else if(this.TempCounter < 100)
					{
						i += 3;
					}
					else if(this.TempCounter < 1000)
					{
						i += 4;
					}
					this.TempCounter ++;
					left = CheckLeftExpression(subBuilder, i);
				}
				int right = CheckRightExpression(subBuilder, i);
				if(HelperFunctions.isInteger(subBuilder.substring(i+1, i+right).toString()))
				{
					this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i+1, i+right);
					subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
					this.TempCounter ++;
					right = CheckRightExpression(subBuilder, i);
				}
				this.NodeList.add(new IRNode("MULTI", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
				subBuilder.delete(i-left, i+right);
				subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
				this.TempCounter ++;
			}
			else if(multOp == -1 && divOp != -1)
			{
				int i = divOp;
				int left = CheckLeftExpression(subBuilder, i);
				if(HelperFunctions.isInteger(subBuilder.substring(i-left, i).toString()))
				{
					this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i-left, i);
					subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
					if(this.TempCounter < 10)
					{
						i += 2;
					}
					else if(this.TempCounter < 100)
					{
						i += 3;
					}
					else if(this.TempCounter < 1000)
					{
						i += 4;
					}
					this.TempCounter ++;
					left = CheckLeftExpression(subBuilder, i);
				}
				int right = CheckRightExpression(subBuilder, i);
				if(HelperFunctions.isInteger(subBuilder.substring(i+1, i+right).toString()))
				{
					this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i+1, i+right);
					subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
					this.TempCounter ++;
					right = CheckRightExpression(subBuilder, i);
				}
				this.NodeList.add(new IRNode("DIVI", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
				subBuilder.delete(i-left, i+right);
				subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
				this.TempCounter ++;
			}
			else if(multOp != -1 && divOp != -1)
			{
				int i = (multOp < divOp) ? multOp : divOp;
				int left = CheckLeftExpression(subBuilder, i);
				if(HelperFunctions.isInteger(subBuilder.substring(i-left, i).toString()))
				{
					this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i-left, i);
					subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
					if(this.TempCounter < 10)
					{
						i += 2;
					}
					else if(this.TempCounter < 100)
					{
						i += 3;
					}
					else if(this.TempCounter < 1000)
					{
						i += 4;
					}
					this.TempCounter ++;
					left = CheckLeftExpression(subBuilder, i);
				}
				int right = CheckRightExpression(subBuilder, i);
				if(HelperFunctions.isInteger(subBuilder.substring(i+1, i+right).toString()))
				{
					this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i+1, i+right);
					subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
					this.TempCounter ++;
					right = CheckRightExpression(subBuilder, i);
				}
				if(multOp < divOp)
				{
					this.NodeList.add(new IRNode("MULTI", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
				}
				else
				{
					this.NodeList.add(new IRNode("DIVI", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
				}
				subBuilder.delete(i-left, i+right);
				subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
				this.TempCounter ++;
			}
			else // at this point, we have no more *'s or /'s
			{
				int addOp = subBuilder.indexOf("+");
				int subOp = subBuilder.indexOf("-");
				if(addOp != -1 && subOp == -1)
				{
					int i = addOp;
					int left = CheckLeftExpression(subBuilder, i);
					if(HelperFunctions.isInteger(subBuilder.substring(i-left, i).toString()))
					{
						this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i-left, i);
						subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
						if(this.TempCounter < 10)
						{
							i += 2;
						}
						else if(this.TempCounter < 100)
						{
							i += 3;
						}
						else if(this.TempCounter < 1000)
						{
							i += 4;
						}
						this.TempCounter ++;
						left = CheckLeftExpression(subBuilder, i);
					}
					int right = CheckRightExpression(subBuilder, i);
					if(HelperFunctions.isInteger(subBuilder.substring(i+1, i+right).toString()))
					{
						this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i+1, i+right);
						subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
						this.TempCounter ++;
						right = CheckRightExpression(subBuilder, i);
					}
					this.NodeList.add(new IRNode("ADDI", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i-left, i+right);
					subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
					this.TempCounter ++;
				}
				else if(addOp == -1 && subOp != -1)
				{
					int i = subOp;
					int left = CheckLeftExpression(subBuilder, i);
					if(HelperFunctions.isInteger(subBuilder.substring(i-left, i).toString()))
					{
						//System.out.println("Number found in expression: " + subBuilder.substring(i+1, i+right));
						this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i-left, i);
						subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
						if(this.TempCounter < 10)
						{
							i += 2;
						}
						else if(this.TempCounter < 100)
						{
							i += 3;
						}
						else if(this.TempCounter < 1000)
						{
							i += 4;
						}
						this.TempCounter ++;
						left = CheckLeftExpression(subBuilder, i);
					}
					int right = CheckRightExpression(subBuilder, i);
					if(HelperFunctions.isInteger(subBuilder.substring(i+1, i+right).toString()))
					{
						this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i+1, i+right);
						subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
						this.TempCounter ++;
						right = CheckRightExpression(subBuilder, i);
					}
					this.NodeList.add(new IRNode("SUBI", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i-left, i+right);
					subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
					this.TempCounter ++;
				}
				else if(addOp != -1 && subOp != -1)
				{
					int i = (addOp < subOp) ? addOp : subOp;
					int left = CheckLeftExpression(subBuilder, i);
					if(HelperFunctions.isInteger(subBuilder.substring(i-left, i).toString()))
					{
						this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i-left, i);
						subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
						if(this.TempCounter < 10)
						{
							i += 2;
						}
						else if(this.TempCounter < 100)
						{
							i += 3;
						}
						else if(this.TempCounter < 1000)
						{
							i += 4;
						}
						this.TempCounter ++;
						left = CheckLeftExpression(subBuilder, i);
					}
					int right = CheckRightExpression(subBuilder, i);
					if(HelperFunctions.isInteger(subBuilder.substring(i+1, i+right).toString()))
					{
						this.NodeList.add(new IRNode("STOREI", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i+1, i+right);
						subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
						this.TempCounter ++;
						right = CheckRightExpression(subBuilder, i);
					}
					if(addOp < subOp)
					{
						this.NodeList.add(new IRNode("ADDI", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					}
					else
					{
						this.NodeList.add(new IRNode("SUBI", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					}
					subBuilder.delete(i-left, i+right);
					subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
					this.TempCounter ++;
				}
			}
		}
		return subBuilder.toString();
	}

	public String ReduceF(StringBuilder subBuilder)
	{
		while(HelperFunctions.ExpressionType(subBuilder) != 0) // while expression still has an op in it
		{
			//System.out.println("ReducingF: " + subBuilder);
			int multOp = subBuilder.indexOf("*");
			int divOp = subBuilder.indexOf("/");
			if(multOp != -1 && divOp == -1)
			{
				int i = multOp;
				int left = CheckLeftExpression(subBuilder, i);
				if(HelperFunctions.IsFloat(subBuilder.substring(i-left, i).toString()))
				{
					this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i-left, i);
					subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
					if(this.TempCounter < 10)
					{
						i += 2;
					}
					else if(this.TempCounter < 100)
					{
						i += 3;
					}
					else if(this.TempCounter < 1000)
					{
						i += 4;
					}
					this.TempCounter ++;
					left = CheckLeftExpression(subBuilder, i);
				}
				int right = CheckRightExpression(subBuilder, i);
				if(HelperFunctions.IsFloat(subBuilder.substring(i+1, i+right).toString()))
				{
					this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i+1, i+right);
					subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
					this.TempCounter ++;
					right = CheckRightExpression(subBuilder, i);
				}
				this.NodeList.add(new IRNode("MULTF", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
				subBuilder.delete(i-left, i+right);
				subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
				this.TempCounter ++;
			}
			else if(multOp == -1 && divOp != -1)
			{
				int i = divOp;
				int left = CheckLeftExpression(subBuilder, i);
				if(HelperFunctions.IsFloat(subBuilder.substring(i-left, i).toString()))
				{
					this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i-left, i);
					subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
					if(this.TempCounter < 10)
					{
						i += 2;
					}
					else if(this.TempCounter < 100)
					{
						i += 3;
					}
					else if(this.TempCounter < 1000)
					{
						i += 4;
					}
					this.TempCounter ++;
					left = CheckLeftExpression(subBuilder, i);
				}
				int right = CheckRightExpression(subBuilder, i);
				if(HelperFunctions.IsFloat(subBuilder.substring(i+1, i+right).toString()))
				{
					this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i+1, i+right);
					subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
					this.TempCounter ++;
					right = CheckRightExpression(subBuilder, i);
				}
				this.NodeList.add(new IRNode("DIVF", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
				subBuilder.delete(i-left, i+right);
				subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
				this.TempCounter ++;
			}
			else if(multOp != -1 && divOp != -1)
			{
				int i = (multOp < divOp) ? multOp : divOp;
				int left = CheckLeftExpression(subBuilder, i);
				if(HelperFunctions.IsFloat(subBuilder.substring(i-left, i).toString()))
				{
					this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i-left, i);
					subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
					if(this.TempCounter < 10)
					{
						i += 2;
					}
					else if(this.TempCounter < 100)
					{
						i += 3;
					}
					else if(this.TempCounter < 1000)
					{
						i += 4;
					}
					this.TempCounter ++;
					left = CheckLeftExpression(subBuilder, i);
				}
				int right = CheckRightExpression(subBuilder, i);
				if(HelperFunctions.IsFloat(subBuilder.substring(i+1, i+right).toString()))
				{
					this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i+1, i+right);
					subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
					this.TempCounter ++;
					right = CheckRightExpression(subBuilder, i);
				}
				if(multOp < divOp)
				{
					this.NodeList.add(new IRNode("MULTF", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
				}
				else
				{
					this.NodeList.add(new IRNode("DIVF", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
				}
				subBuilder.delete(i-left, i+right);
				subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
				this.TempCounter ++;
			}
			else // at this point, we have no more *'s or /'s
			{
				int addOp = subBuilder.indexOf("+");
				int subOp = subBuilder.indexOf("-");
				if(addOp != -1 && subOp == -1)
				{
					int i = addOp;
					int left = CheckLeftExpression(subBuilder, i);
					if(HelperFunctions.IsFloat(subBuilder.substring(i-left, i).toString()))
					{
						this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i-left, i);
						subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
						if(this.TempCounter < 10)
						{
							i += 2;
						}
						else if(this.TempCounter < 100)
						{
							i += 3;
						}
						else if(this.TempCounter < 1000)
						{
							i += 4;
						}
						this.TempCounter ++;
						left = CheckLeftExpression(subBuilder, i);
					}
					int right = CheckRightExpression(subBuilder, i);
					if(HelperFunctions.IsFloat(subBuilder.substring(i+1, i+right).toString()))
					{
						this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i+1, i+right);
						subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
						this.TempCounter ++;
						right = CheckRightExpression(subBuilder, i);
					}
					this.NodeList.add(new IRNode("ADDF", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i-left, i+right);
					subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
					this.TempCounter ++;
				}
				else if(addOp == -1 && subOp != -1)
				{
					int i = subOp;
					int left = CheckLeftExpression(subBuilder, i);
					if(HelperFunctions.IsFloat(subBuilder.substring(i-left, i).toString()))
					{
						//System.out.println("Number found in expression: " + subBuilder.substring(i+1, i+right));
						this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i-left, i);
						subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
						if(this.TempCounter < 10)
						{
							i += 2;
						}
						else if(this.TempCounter < 100)
						{
							i += 3;
						}
						else if(this.TempCounter < 1000)
						{
							i += 4;
						}
						this.TempCounter ++;
						left = CheckLeftExpression(subBuilder, i);
					}
					int right = CheckRightExpression(subBuilder, i);
					if(HelperFunctions.IsFloat(subBuilder.substring(i+1, i+right).toString()))
					{
						this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i+1, i+right);
						subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
						this.TempCounter ++;
						right = CheckRightExpression(subBuilder, i);
					}
					this.NodeList.add(new IRNode("SUBF", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					subBuilder.delete(i-left, i+right);
					subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
					this.TempCounter ++;
				}
				else if(addOp != -1 && subOp != -1)
				{
					int i = (addOp < subOp) ? addOp : subOp;
					int left = CheckLeftExpression(subBuilder, i);
					if(HelperFunctions.IsFloat(subBuilder.substring(i-left, i).toString()))
					{
						this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i-left, i), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i-left, i);
						subBuilder.insert(i-left, "$T" + String.valueOf(this.TempCounter));
						if(this.TempCounter < 10)
						{
							i += 2;
						}
						else if(this.TempCounter < 100)
						{
							i += 3;
						}
						else if(this.TempCounter < 1000)
						{
							i += 4;
						}
						this.TempCounter ++;
						left = CheckLeftExpression(subBuilder, i);
					}
					int right = CheckRightExpression(subBuilder, i);
					if(HelperFunctions.IsFloat(subBuilder.substring(i+1, i+right).toString()))
					{
						this.NodeList.add(new IRNode("STOREF", subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
						subBuilder.delete(i+1, i+right);
						subBuilder.insert(i+1, "$T" + String.valueOf(this.TempCounter));
						this.TempCounter ++;
						right = CheckRightExpression(subBuilder, i);
					}
					if(addOp < subOp)
					{
						this.NodeList.add(new IRNode("ADDF", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					}
					else
					{
						this.NodeList.add(new IRNode("SUBF", subBuilder.substring(i-left, i), subBuilder.substring(i+1, i+right), "$T" + String.valueOf(this.TempCounter)));
					}
					subBuilder.delete(i-left, i+right);
					subBuilder.insert(i-left, "$T"+String.valueOf(this.TempCounter));
					this.TempCounter ++;
				}
			}
		}
		return subBuilder.toString();
	}

	public int CheckLeftExpression(StringBuilder sb, int index)
	{
		//System.out.println("Entering Left:" + sb);
		int i = index - 1;
		int offset = 0;
		while(sb.charAt(i) != '+' && sb.charAt(i) != '-' && sb.charAt(i) != '*' && sb.charAt(i) != '/')
		{
			if(i == 0)
			{
				return offset + 1;
			}
			i --;
			offset ++;
		} 
		return offset;
	}

	public int CheckRightExpression(StringBuilder sb, int index)
	{
		int i = index + 1;
		int offset = 1;
		//System.out.println("Entering Right:" + sb);
		while(sb.charAt(i) != '+' && sb.charAt(i) != '-' && sb.charAt(i) != '*' && sb.charAt(i) != '/')
		{
			if(i == sb.length()-1)
			{
				return offset + 1;
			}
			i ++;
			offset ++;
		}
		/*
		while(i != sb.length() && sb.charAt(i) != '+' && sb.charAt(i) != '-' && sb.charAt(i) != '*' && sb.charAt(i) != '/')
		{
			i ++;
			offset ++;
		}
		*/
		return offset;
	}

	/* Given a symbol (variable) name, look at the symbol table to determine its type */
	public String SymbolLookup(String SymbolName)
	{
		for(SymbolTable ST : this.STS.SymbolTables)
		{
			for(Symbol S : ST.SYMBOLS)
			{
				if(S.var_name.equals(SymbolName))
				{
					return S.var_type;
				}
			}
		}
		return "Symbol Not Found";
	}

	public void PrintExprList()
	{
		for(Expr E : this.ExprList)
		{
			System.out.println("ID: " + E.id + " Expr: " + E.expr);
		}
	}

	public void AddNode(IRNode node)
	{
		this.NodeList.add(node);
	}
	
	public void PrintNodeList()
	{
		for(IRNode IR : this.NodeList)
		{
			IR.PrintNode();
		}
	}	
}

class IRNode
{
	public String OpCode;
	public String FirstOperand;
	public String SecondOperand;
	public String Result;
		
	public IRNode(String OpCode, String Result)
	{
		this.OpCode = OpCode;
		this.Result = Result;
	}

	public IRNode(String OpCode, String OpOne, String Result)
	{
		this.OpCode = OpCode;
		this.FirstOperand = OpOne;
		this.Result = Result;
	}

	public IRNode(String OpCode, String OpOne, String OpTwo, String Result)
	{
		this.OpCode = OpCode;
		this.FirstOperand = OpOne;
		this.SecondOperand = OpTwo;
		this.Result = Result;
	}

	public void PrintNode()
	{
		if(this.FirstOperand == null && this.SecondOperand == null)
		{
			System.out.println(";" + this.OpCode + " " + this.Result);
		}
		else if(this.FirstOperand != null && this.SecondOperand == null)
		{
			System.out.println(";" + this.OpCode + " " + this.FirstOperand + " " + this.Result);
		}
		else
		{
			System.out.println(";" + this.OpCode + " " + this.FirstOperand + " " + this.SecondOperand + " " + this.Result);
		}
	}
	
}

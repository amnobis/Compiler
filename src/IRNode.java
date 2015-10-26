import java.util.*;


class IRNodeList
{
	public List<IRNode> NodeList = new ArrayList<IRNode>();
	
	public void AddNode(IRNode node)
	{
		this.NodeList.add(node);
	}

	public void PrintNodeList()
	{
		for(IRNode IR : NodeList)
		{
			System.out.println("ID: " + IR.id + " Expr: " + IR.expr);
		}
	}	

}

class IRNode
{
/*
	public String OpCode;
	public String FirstOperand;
	public String SecondOperand;
	public String Result;
*/
	public String id;
	public String expr; 	

	public IRNode(String id, String expr)
	{
		this.id = id;
		this.expr = expr;
	}
	/*
	public IRNode(String Op, String First, String Second)
	{
		this.OpCode = Op;
		this.FirstOperand = First;
		this.SecondOperand = Second;
	}

	public IRNode(String Op, String First, String Second, String Result)
	{
		this.OpCode = Op;
		this.FirstOperand = First;
		this.SecondOperand = Second;
		this.Result = Result;
	}*/
}

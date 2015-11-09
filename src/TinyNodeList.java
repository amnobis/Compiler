import java.util.*;


public class TinyNodeList
{
	int tempCount = 0;
	SymbolTableStack STS;
	IRNodeList IR;
	List<String> vars = new ArrayList<String>();
	TinyNode head;
	TinyNode tail;
	
	class TinyNode
	{
		TinyNode prev;
		TinyNode next;
		String op;
		String a1;
		String a2;
		String text;

		public TinyNode(String convertedText)
		{
			//this.op = ;
			this.text = convertedText;
		}
	}

	public void GetVariables()
	{
		for(SymbolTable ST : this.STS.SymbolTables)
		{
			for(Symbol S : ST.SYMBOLS)
			{
				//System.out.println("Symbol: " + S.var_name);
				if(!this.vars.contains(S.var_name))
				{
					this.vars.add(S.var_name);
				}
			}
		}
		for(String s : this.vars)
		{
			this.AddTinyNode("var " + s);
		}
	}

	public void AddTinyNode(String convertedText)
	{
		//System.out.println("Adding Tiny Node!");
		TinyNode tn = new TinyNode(convertedText);
		tn.prev = this.tail;
		this.tail.next = tn;
		this.tail = tn;
	}

	public String CheckReg(String regText)
	{
		if(regText.indexOf('$') != -1)
		{
			return "r" + String.valueOf(this.tempCount);
			//this.tempCount ++;
		}
		return regText;
	}

	public void ConvertNodes()
	{
		for(IRNode irn : this.IR.NodeList)
		{
			System.out.println(irn.OpCode);
			if(irn.OpCode == "ADDI")
			{	
			}
			else if(irn.OpCode == "SUBI")
			{
				
			}
			else if(irn.OpCode == "MULTI")
			{
				
			}
			else if(irn.OpCode == "DIVI")
			{
				
			}
			else if(irn.OpCode == "STOREI")
			{
				this.AddTinyNode("move " + this.CheckReg(irn.FirstOperand) + " " + this.CheckReg(irn.Result));
				this.tempCount ++;
			}
		}
	}

	public void PrintNodes()
	{
		TinyNode pointer = head;
		TinyNode tmp;
		while(pointer.next != null)
		{
			System.out.println(pointer.text);
			tmp = pointer.next;
			pointer = tmp;
		}
		System.out.println(pointer.text); // print last node
	}

	public TinyNodeList(SymbolTableStack sts, IRNodeList irnodes)
	{
		this.head = new TinyNode("Head");
		this.tail = head;
		this.STS = sts;
		this.IR = irnodes;
		this.GetVariables();
		this.ConvertNodes();
		this.PrintNodes();

	}
}


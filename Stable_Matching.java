import java.util.*;
class Stable_Matching
{
	public static void main(String...args)
	{
		ArrayList<Integer> Term_list=new ArrayList<Integer>();
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the number of men and women:");
		int N=scan.nextInt();
		for(int i=0;i<N;i++)
		{
			Term_list.add(i);
		}
		ArrayList<Man> man_list=new ArrayList<Man>();
		for(int i=0;i<N;i++)
		{
			System.out.println("Enter the woman according to the preference of man "+(i+1));
			Man m_obj=new Man();
			for(int j=0;j<N;j++)
			{
				System.out.println("Enter the woman_number:");
				int woman_number=scan.nextInt();
				m_obj.pref.add(woman_number-1);
			}
			man_list.add(m_obj);
		}
		
		ArrayList<Woman> woman_list=new ArrayList<Woman>();
		for(int i=0;i<N;i++)
		{
			System.out.println("Enter the man according to the preference of woman "+(i+1));
			Woman w_obj=new Woman();
			for(int a=0;a<N;a++)
			{
				w_obj.pref.add(-1);
			}
			for(int j=0;j<N;j++)
			{
				System.out.println("Enter the man_number:");
				int man_number=scan.nextInt();
				w_obj.pref.set(man_number-1,j+1);
			}
			woman_list.add(w_obj);
		}
		
		Matching(Term_list,man_list,woman_list);
	}	
	
	public static void Matching(ArrayList<Integer> Term_list, ArrayList<Man> man_list, ArrayList<Woman> woman_list)
	{
		while(Term_list.size()!=0)
		{
			int num=Term_list.get(Term_list.size()-1);//Takes O(1) time
			Man mn=man_list.get(num);
			Woman wn=woman_list.get(mn.pref.get(mn.count));
			mn.count=mn.count+1;
			if(wn.status==0)
			{
				wn.status=1;
				wn.man_num=num;
				Term_list.remove(Term_list.size()-1);
			}
			
			else
			{
				if(wn.pref.get(wn.man_num)>wn.pref.get(num))
				{
					Term_list.remove(Term_list.size()-1);
					Term_list.add(wn.man_num);
					wn.man_num=num;
					
				}
			}
		}
		System.out.println("The stable matching pairs are");
		System.out.println();
		for(int i=0;i<woman_list.size();i++)
		{
			Woman wn=woman_list.get(i);
			System.out.println("("+(wn.man_num+1)+","+(i+1)+")");
		}
	}
}

class Man
{
	ArrayList<Integer> pref=new ArrayList<Integer>();	
	int count=0;//This is a pointer pointing to the woman of highest prefrence in man's pref list.
}

class Woman
{
	ArrayList<Integer> pref=new ArrayList<Integer>();
	int status=0;// If status is 0 woman is not engaged, if status is 1 she is engaged
	int man_num=-1;
}
import java.util.ArrayList;


public class Board {
	
	public Cell [][] board;
	public ArrayList<Integer> un_variables;
	
	Board(Cell[][] b){
		
		un_variables = new ArrayList<Integer>();
		board = b;
		int p = 0;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(!board[i][j].Preset){
					un_variables.add(p);
				}
				p++;
			}
		}
		
	}
	
	
	
	public boolean Complete(){
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(!check_Consistent(i,j,board[i][j].Cell_val)){
					return false;
				}
			}
		}
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				
				if(board[i][j].Cell_val==0){
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public boolean check_Consistent(int x,int y,int val){
		
		int i = x;
		int j = y;
		
		for(int k=0;k<9;k++){								//Remove row Cell domain value
			if((k!=j && board[i][k].Cell_val==val)){
				return false;
			}
		}
		
		for(int k=0;k<9;k++){								//Remove col Cell domain value
			if(k!=i && board[k][j].Cell_val==val){
				return false;
			}
		}
		
		int x_val = (i/3)*3;
		int y_val = (j/3)*3;
		
		for(int m=x_val;m<x_val+3;m++){					//Remove squr Cell domain value
			for(int n=y_val;n<y_val+3;n++){
				if((m!=i && n!=j) && board[m][n].Cell_val==val){
					return false;
				}			
			}
		}
		
		return true;
	}
	
	
	public Cell[][] Check_Complete_Details(){
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(!check_Consistent(i,j,board[i][j].Cell_val) && (board[i][j].Cell_val!=0)){
					if(!board[i][j].Preset)board[i][j].rw_flag=0;
				}
			}
		}
		
		return board;
	}
	
	public String toString() 
	{
		String str="";
		
		for (int i=0; i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				str += (board[i][j].Cell_val+" ");
			}
			str +="\n";
		}
		return str;
	}
	
}

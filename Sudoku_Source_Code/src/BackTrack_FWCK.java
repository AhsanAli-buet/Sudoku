
public class BackTrack_FWCK {
	
	public Board board;
	public int back_track = 0;
	public int Variable_Count;
	public int Value_Count;
	BackTrack_FWCK(Board b){
		
		Variable_Count = 0;
		Value_Count = 0;
		board = b;
	}
	
	
	public boolean BackTrack(){
		
		
		if(board.Complete())
		{
			//System.out.println("Sudoku completed: Variable Count: "+Variable_Count+" Value_Count: "+Value_Count);
			//System.out.println(board.toString());
			return true;
		}
			
		//int position = get_Variable_First();
		int position = get_Variable_MRV();
		int i = position / 9;
		int j = position % 9;
		
		if(position==-1){
			
			//System.out.println("corssed 81: \n\n"+board.toString());
			return true;
		}
		
		if(board.board[i][j].Domain.isEmpty()){
			
			//System.out.println("domain empty:\n\n"+board.toString());
			return false;
		}
		
		
		if(!board.board[i][j].assigned){
			
			for(int k=0;k<board.board[i][j].Domain.size();k++){
				
				int value = get_Value_First(i,j,k);
				
				if(board.check_Consistent(i,j,value)){
					
					
					board.board[i][j].Cell_val = value;
					board.board[i][j].assigned = true;
					
					if(Forward_Check(i,j,board.board[i][j].Cell_val)){
						
						try{

							boolean result = BackTrack();
							if(result)return result;
							
						}catch(Exception IndexOutofBound){
							//System.out.println(board.un_variables.size()+" "+position+1);
						}
					}
					
					
					Backward_Check(i,j,board.board[i][j].Cell_val);
					board.board[i][j].Cell_val = 0;
					board.board[i][j].assigned = false;
				
				}
			}
			
		}
		
		return false;
	}
	
	public int [][] get_Board(){
		
		int [][] data = new int [Sudoku_window.dimension][Sudoku_window.dimension];
		for (int i = 0; i < Sudoku_window.dimension; i++) 
			for (int j = 0; j < Sudoku_window.dimension; j++) 
				data[i][j] = board.board[i][j].Cell_val;
		
		return data;
	}
	
	public void Initial_Forward_Checking(){
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(board.board[i][j].Preset){
					
					int val = board.board[i][j].Cell_val;
					Forward_Check(i,j,val);
					
				}
			}
		}
	}
	
	public boolean Forward_Check(int x,int y,int val){
		
		int i = x;
		int j = y;
		
		for(int k=0;k<9;k++){								//Remove row Cell domain value
			if((!board.board[i][k].Preset) && k!=j && (!board.board[i][k].assigned)){
				if(board.board[i][k].Domain.contains((Integer)val)){
					board.board[i][k].Domain.remove((Integer)val);
				}
			}
		}
		
		for(int k=0;k<9;k++){								//Remove col Cell domain value
			if((!board.board[k][j].Preset) && k!=i && (!board.board[k][j].assigned)){
				if(board.board[k][j].Domain.contains((Integer)val)){
					board.board[k][j].Domain.remove((Integer)val);
				}
			}
		}
		
		int x_val = (i/3)*3;
		int y_val = (j/3)*3;
		
		for(int m=x_val;m<x_val+3;m++){					//Remove squr Cell domain value
			for(int n=y_val;n<y_val+3;n++){
				if((!board.board[m][n].Preset) &&(m!=i && n!=j) && (!board.board[m][n].assigned)){
					if(board.board[m][n].Domain.contains((Integer)val)){
						board.board[m][n].Domain.remove((Integer)val);
					}
				}			
			}
		}
		
		for(int m=0;m<9;m++){								//Check Inference 
			for(int n=0;n<9;n++){
				if(board.board[m][n].Domain.isEmpty()){
					
					//System.out.println("domain empty at row "+m+" col "+n+" for index "+x+" "+y+" value: "+val);
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public void Backward_Check(int x,int y,int val){
		
		int i = x;
		int j = y;
		
		for(int k=0;k<9;k++){								//Remove row Cell domain value
			if((!board.board[i][k].Preset) && k!=j && (!board.board[i][k].assigned)){
				if(!board.board[i][k].Domain.contains((Integer)val)){
					board.board[i][k].Domain.add(val);
				}
				
			}
		}
		
		for(int k=0;k<9;k++){								//Remove col Cell domain value
			if((!board.board[k][j].Preset) && k!=i && (!board.board[k][j].assigned)){
				if(!board.board[k][j].Domain.contains((Integer)val)){
					board.board[k][j].Domain.add(val);
				}
				
			}
		}
		
		int x_val = (i/3)*3;
		int y_val = (j/3)*3;
		
		for(int m=x_val;m<x_val+3;m++){					//Remove squr Cell domain value
			for(int n=y_val;n<y_val+3;n++){
				if((!board.board[m][n].Preset) &&(m!=i && n!=j) && (!board.board[m][n].assigned)){
					if(!board.board[m][n].Domain.contains((Integer)val)){
						board.board[m][n].Domain.add(val);
					}
				}			
			}
		}
	}
	
	
	public int get_Variable_First(){
		
		Variable_Count++;
		
		for(int i=0;i<board.un_variables.size();i++){
			if(!board.board[board.un_variables.get(i)/9][board.un_variables.get(i)%9].assigned){
				return board.un_variables.get(i);
			}
		}
		
		return -1;
	}
	
	
	public int get_Variable_MRV(){
		
		 int min = 20;
		 int variable_pos = -1;
		 Variable_Count++;
		 
		for(int i=0;i<board.un_variables.size();i++){
				
			int x = board.un_variables.get(i)/9;
			int y = board.un_variables.get(i)%9;
			
			if((!board.board[x][y].assigned) && (board.board[x][y].Domain.size()< min)){
					
				min = board.board[x][y].Domain.size();
				variable_pos = board.un_variables.get(i);
			}
		}
		
		//System.out.println("variable "+ variable_pos/9+" "+ variable_pos%9);
		
		return variable_pos;
	}
	
	
	
	public int get_Value_First(int x,int y,int z){
		
		Value_Count++;
		for(int j=0;j<board.board[x][y].Domain.size();j++){
			
			if(j==z){
				
				return board.board[x][y].Domain.get(z); 
			}
		}
		
		return 0;
	}
	

}

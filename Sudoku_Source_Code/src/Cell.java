import java.util.ArrayList;


public class Cell {
	
	public ArrayList<Integer> Domain;
	public boolean Preset;
	public int Cell_val;
	public boolean assigned;
	public int val_pick;
	public int rw_flag=1;
	
	Cell(int x,int y,int val){
		
		Domain = new ArrayList<Integer>();
		val_pick = -1;
		if(val!=0){
			Preset = true;
			assigned = true;
			Cell_val = val;
			Domain.add(val);
		}
		else{
			
			Preset = false;
			assigned = false;
			for(int i=0;i<9;i++){
				Domain.add(i+1);
			}
			Cell_val = 0;
		}
	}
	
	public void set_preset(Boolean flag){
		this.Preset = flag;
	}
	
}

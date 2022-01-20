import javax.print.attribute.standard.PresentationDirection;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Sudoku_window extends JPanel{

  
    private static final long serialVersionUID = 0;
    private static JTextField f[][]= new JTextField[9][9] ;
    private JPanel p[][]= new JPanel [3][3];
    public static JFrame Sudoku_window;
    public static JButton B_About;
    public static JButton B_New_game;
    public static JButton B_Solve;
    public static JButton B_Check;
    public static int dimension;
    public static ArrayList<int[][]>Board_list = new ArrayList<int[][]>();
    public static int present_board_id=0;
    
    public static JFrame AboutWindow = new JFrame();
	public static JLabel L_About = new JLabel("Sudoku Game");
	public static JLabel T_About = new JLabel("",JLabel.CENTER);
    

    public Sudoku_window(){
    	
        setSize(500,500);
		
        for(int x=0; x<=8; x++){
            for(int y=0; y<=8; y++){
                f[x][y]=new JTextField(1);
                f[x][y].setFont(new Font("Lucida Console",Font.BOLD,18));
                f[x][y].setHorizontalAlignment(SwingConstants.CENTER);
                f[x][y].setDocument(new JTextFieldLimit(1));     
            }
        }

        for(int x=0; x<=2; x++){
            for(int y=0; y<=2; y++){
                p[x][y]=new JPanel(new GridLayout(3,3));
               
            }
        }

        setLayout(new GridLayout(3,3,5,5));
        
        set_panel_Field();
        
        set_panel_edit(false);
        
    }
    
    public static void main (String[] args){
    	
    	Create_Sudoku_GUI();
    	getInput();
    }
    
    public static void set_panel_edit(Boolean flag){
    	
    	for(int i=0;i<=8;i++)
        	for(int j=0;j<=8;j++)
        		f[i][j].setEditable(flag);
    }
    
    public static void Create_Sudoku_GUI(){
    	
    	Sudoku_window = new JFrame("Sudoku Game");
    	Sudoku_window.setBounds(300, 100, 500, 600);
    	
		Sudoku_window.setLayout(null);
		
		B_About = new JButton("About");
		B_About.setBounds(15,515,100,40);
        B_About.setBackground(Color.GRAY);
        B_About.setForeground(Color.CYAN);
        B_About.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
        B_About.setHorizontalTextPosition(SwingConstants.CENTER);
        B_About.setBorder(new LineBorder(Color.DARK_GRAY,2));
        
        B_New_game = new JButton("New Game");
        B_New_game.setBounds(130,515,130,40);
        B_New_game.setBackground(Color.GRAY);
        B_New_game.setForeground(Color.CYAN);
        B_New_game.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
        B_New_game.setHorizontalTextPosition(SwingConstants.CENTER);
        B_New_game.setBorder(new LineBorder(Color.DARK_GRAY,2));
        
        B_Check = new JButton("Check");
        B_Check.setBounds(270,515,100,40);
        B_Check.setBackground(Color.GRAY);
        B_Check.setForeground(Color.CYAN);
        B_Check.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
        B_Check.setHorizontalTextPosition(SwingConstants.CENTER);
        B_Check.setBorder(new LineBorder(Color.DARK_GRAY,2));
        B_Check.setEnabled(false);
        
        B_Solve = new JButton("Solve");
        B_Solve.setBounds(380,515,100,40);
        B_Solve.setBackground(Color.GRAY);
        B_Solve.setForeground(Color.CYAN);
        B_Solve.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
        B_Solve.setHorizontalTextPosition(SwingConstants.CENTER);
        B_Solve.setBorder(new LineBorder(Color.DARK_GRAY,2));
        B_Solve.setEnabled(false);
        
		Sudoku_window m = new Sudoku_window();
		Sudoku_window.getContentPane().add(m);
		Sudoku_window.getContentPane().add(B_About);
		Sudoku_window.getContentPane().add(B_New_game);
		Sudoku_window.getContentPane().add(B_Check);
		Sudoku_window.getContentPane().add(B_Solve);
		
    	Sudoku_window.setResizable(false);
		Sudoku_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Sudoku_window.setVisible(true);
		
		Build_About_Window();
		
		set_ActionListener();
    	
    }
    
public static void Build_About_Window(){
		
		AboutWindow.setBounds(0, 0, 350, 500);
		AboutWindow.setLocationRelativeTo(Sudoku_window);
		AboutWindow.setContentPane(new JLabel(new ImageIcon("theme.bmp")));
		AboutWindow.getContentPane().setLayout(null);
		AboutWindow.setTitle("About Sudoku Game");
		AboutWindow.setResizable(false);
		
		T_About.setForeground(Color.BLACK);
		T_About.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 13));
		T_About.setBackground(new Color(0,0,0,0));
		T_About.setEnabled(true);
		AboutWindow.getContentPane().add(T_About);
		T_About.setBounds(20,20,325,450);
		T_About.setText("<html>Sudoku Game is an assignment of <br>AI LAB BUET CSE Level-4 Term-1, developed by Ahsan Ali 1105083. This is developed in java jdk7." +
				"<br><br>Sudoku Game is a simple strategy game. The objective is to fill a 9X9 grid with digits so that each column, each row, " +
				"and each of the nine 3X3 subgrids (boxes) contains all of the digits from 1 to 9. New Game provides a partially completed grid." +
				"Anyone can check if sudoku solved by clicking check button and get solved game by clicking solve button."+
				"<br>As Sudoku game is a Constrained Stratification Problem so the solution of Sudoku Board is generated by Backtracking with Forward Checking"+
				" , MRV and Value First Heuristics."+
				"<br><br>Thank you.<br>Ahsan Ali(1105083),BUET,CSE</html>");
		
		L_About.setForeground(Color.CYAN);
		L_About.setHorizontalAlignment(SwingConstants.CENTER);
		L_About.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
		L_About.setBounds(20,20, 280,25);
		AboutWindow.getContentPane().add(L_About);
		
		AboutWindow.setVisible(false);
	}
    
    @Override
    protected void paintComponent(Graphics g) {

      super.paintComponent(g);
      g.setColor(Color.BLACK);
      g.fillRect(0,0,500,500);
          
    }
    
    public static void set_ActionListener(){
    	
    	B_New_game.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				B_Solve.setEnabled(true);
				B_Check.setEnabled(true);
				Random rnd = new Random();
				while(true){
					int num = rnd.nextInt(Board_list.size());
					if(present_board_id!=num){
						present_board_id = num;
						break;
					}
				}
				
				int [][] data = new int[dimension][dimension];
				data = Board_list.get(present_board_id);
				
				set_panel_edit(true);
				
				for (int i = 0; i < dimension; i++) {
					for (int j = 0; j < dimension; j++) {
						
						f[i][j].setText("");
						f[i][j].setForeground(Color.MAGENTA);
						if(data[i][j]!=0){
							f[i][j].setText(Integer.toString(data[i][j]));
							f[i][j].setForeground(Color.BLACK);
							f[i][j].setEditable(false);
						}
						//System.out.printf("%d ",data[i][j]);
					}
					//System.out.println("\n");
				}
				//System.out.println("\n\n\n");
			}
    	});
    	
    	B_Solve.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				int [][] data = new int[dimension][dimension];
				data = Board_list.get(present_board_id);
				data = Sudoku.inputBoards(data);
				Insert_solution(data);
				set_panel_edit(false);
				B_Solve.setEnabled(false);
				B_Check.setEnabled(false);
			}
    	});
    	
    	B_About.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				AboutWindow.setVisible(true);
			}
    	});
    	
    	AboutWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
            	AboutWindow.setVisible(false);
            }
        });
    	
    	B_Check.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				Cell [][] data = new Cell[dimension][dimension];
				for(int i=0;i<=8;i++){
		        	for(int j=0;j<=8;j++){
		        		String str = f[i][j].getText();
		        		if(str.equals(""))data[i][j] = new Cell(i,j,0);
		        		else {
		        			int val = Integer.parseInt(f[i][j].getText());
		        			data[i][j] = new Cell(i,j,val);
		        			if(val!=Board_list.get(present_board_id)[i][j]){
		        				data[i][j].set_preset(false);
		        			}
		        		}
		        	}
				}				
		        	
				Board check_board = new Board(data);
				data = check_board.Check_Complete_Details();
				Update_check_data(data);
			}
    	});
    	
    	for(int i=0;i<=8;i++){
        	for(int j=0;j<=8;j++){
        		JTextField_doclistener dcl = new JTextField_doclistener(f[i][j]);
        		f[i][j].getDocument().addDocumentListener(dcl);
        	}
        }
    	
    }
    
    public static void getInput(){
    	
    	Scanner in;
		try {
			in = new Scanner(new File("Input.txt"));
			dimension = in.nextInt();
			
			while(in.nextInt()!=0){
				
				int [][] input_data = new int[dimension][dimension];
				for (int i = 0; i < dimension; i++) 
					for (int j = 0; j < dimension; j++) 
						input_data[i][j] = in.nextInt();
				
				Board_list.add(input_data);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void Insert_solution(int [][]data){
    	
    	for (int i = 0; i < dimension; i++){ 
			for (int j = 0; j < dimension; j++){
				f[i][j].setText(Integer.toString(data[i][j]));
				f[i][j].setForeground(Color.BLACK);
				if(Board_list.get(present_board_id)[i][j]==0){
					f[i][j].setForeground(Color.BLUE);
				}
				
			}
    	}
    
    }
    
    public static void Update_check_data(Cell[][] data){
    	
    	Boolean flag = true;
    	Boolean flag2 = false;
    	
    	for (int i = 0; i < dimension; i++){ 
			for (int j = 0; j < dimension; j++){
				if(!data[i][j].Preset && (data[i][j].rw_flag==0)){
					flag = false;
					f[i][j].setText(Integer.toString(data[i][j].Cell_val));
					f[i][j].setBackground(Color.RED);
					f[i][j].setForeground(Color.WHITE);
					//System.out.println(data[i][j].Cell_val);
				}
			}
    	}
    	
    	if(flag){
    		flag2 = true;
    		for (int i = 0; i < dimension; i++){ 
    			for (int j = 0; j < dimension; j++){
    				if(data[i][j].Cell_val==0)flag2=false;
    				break;
    			}
    		}
    	}
    	
    	if(flag2){
    		JOptionPane.showMessageDialog(Sudoku_window, "  You Win Sudoku !!!", "Yahoo", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("rz_thumb.png"));
    	}
    }
    
    
    public void set_panel_Field(){
    	
    	p[0][0].add(f[0][0]);
        p[0][0].add(f[0][1]);
        p[0][0].add(f[0][2]);
        p[0][1].add(f[0][3]);
        p[0][1].add(f[0][4]);
        p[0][1].add(f[0][5]);
        p[0][2].add(f[0][6]);
        p[0][2].add(f[0][7]);
        p[0][2].add(f[0][8]);
        
        p[0][0].add(f[1][0]);
        p[0][0].add(f[1][1]);
        p[0][0].add(f[1][2]);
        p[0][1].add(f[1][3]);
        p[0][1].add(f[1][4]);
        p[0][1].add(f[1][5]);
        p[0][2].add(f[1][6]);
        p[0][2].add(f[1][7]);
        p[0][2].add(f[1][8]);
        
        p[0][0].add(f[2][0]);
        p[0][0].add(f[2][1]);
        p[0][0].add(f[2][2]);
        p[0][1].add(f[2][3]);
        p[0][1].add(f[2][4]);
        p[0][1].add(f[2][5]);
        p[0][2].add(f[2][6]);
        p[0][2].add(f[2][7]);
        p[0][2].add(f[2][8]);
        
        p[1][0].add(f[3][0]);
        p[1][0].add(f[3][1]);
        p[1][0].add(f[3][2]);
        p[1][1].add(f[3][3]);
        p[1][1].add(f[3][4]);
        p[1][1].add(f[3][5]);
        p[1][2].add(f[3][6]);
        p[1][2].add(f[3][7]);
        p[1][2].add(f[3][8]);
        
        p[1][0].add(f[4][0]);
        p[1][0].add(f[4][1]);
        p[1][0].add(f[4][2]);
        p[1][1].add(f[4][3]);
        p[1][1].add(f[4][4]);
        p[1][1].add(f[4][5]);
        p[1][2].add(f[4][6]);
        p[1][2].add(f[4][7]);
        p[1][2].add(f[4][8]);
        
        p[1][0].add(f[5][0]);
        p[1][0].add(f[5][1]);
        p[1][0].add(f[5][2]);
        p[1][1].add(f[5][3]);
        p[1][1].add(f[5][4]);
        p[1][1].add(f[5][5]);
        p[1][2].add(f[5][6]);
        p[1][2].add(f[5][7]);
        p[1][2].add(f[5][8]);
        
        p[2][0].add(f[6][0]);
        p[2][0].add(f[6][1]);
        p[2][0].add(f[6][2]);
        p[2][1].add(f[6][3]);
        p[2][1].add(f[6][4]);
        p[2][1].add(f[6][5]);
        p[2][2].add(f[6][6]);
        p[2][2].add(f[6][7]);
        p[2][2].add(f[6][8]);
        
        p[2][0].add(f[7][0]);
        p[2][0].add(f[7][1]);
        p[2][0].add(f[7][2]);
        p[2][1].add(f[7][3]);
        p[2][1].add(f[7][4]);
        p[2][1].add(f[7][5]);
        p[2][2].add(f[7][6]);
        p[2][2].add(f[7][7]);
        p[2][2].add(f[7][8]);
        
        p[2][0].add(f[8][0]);
        p[2][0].add(f[8][1]);
        p[2][0].add(f[8][2]);
        p[2][1].add(f[8][3]);
        p[2][1].add(f[8][4]);
        p[2][1].add(f[8][5]);
        p[2][2].add(f[8][6]);
        p[2][2].add(f[8][7]);
        p[2][2].add(f[8][8]);
        
        add(p[0][0]);
        add(p[0][1]);
        add(p[0][2]);
        add(p[1][0]);
        add(p[1][1]);
        add(p[1][2]);
        add(p[2][0]);
        add(p[2][1]);
        add(p[2][2]);
    	
    }
}

class JTextFieldLimit extends PlainDocument {
	  private int limit;
	  JTextFieldLimit(int limit) {
	    super();
	    this.limit = limit;
	  }

	  JTextFieldLimit(int limit, boolean upper) {
	    super();
	    this.limit = limit;
	  }

	  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	    if (str == null)
	      return;

	    if ((getLength() + str.length()) <= limit) {
	      super.insertString(offset, str, attr);
	    }
	  }
}

class JTextField_doclistener implements  DocumentListener{
	
	JTextField txf;
	JTextField_doclistener(JTextField t){
		super();
		txf = t;
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		txf.setForeground(Color.MAGENTA);
		txf.setBackground(UIManager.getColor("TextField.background"));
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		txf.setForeground(Color.MAGENTA);
		txf.setBackground(UIManager.getColor("TextField.background"));
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		txf.setForeground(Color.MAGENTA);
		txf.setBackground(UIManager.getColor("TextField.background"));
	}
	
	
}


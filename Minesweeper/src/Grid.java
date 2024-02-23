
/*
 * Email: q.palinyc@gmail.com
 * Youtube link: https://youtu.be/-CvhkOSt47w 
 * 
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Grid {

 
    private boolean[][] bombGrid;
    private int[][] countGrid;
    private int numRows;
    private int numColumns;
    private int numBombs;
    	
    public static void main(String[] args ) {
    		Grid grid = new Grid();
    		grid.printGrid();
    		Grid_Gui vd = new Grid_Gui();
    	     
            vd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            vd.pack();
            vd.setVisible(true);
    	}
    

    public Grid() {
       
        numBombs = 25;
        numRows = 10;
        numColumns = 10;
        bombGrid = new boolean[numRows][numColumns];
        countGrid = new int[numRows][numColumns];
        createBombGrid();
        createCountGrid();
        
        
    }
    public Grid(int row, int columns) {
    	
    	
    	if(row >= 0) {
        numRows = row;
    	}else
    		numRows = 10;
    	
    	if(columns >= 0)
        numColumns = columns;
    	else
    		numColumns = 10;
    	
        bombGrid = new boolean[numRows][numColumns];
        countGrid = new int[numRows][numColumns];
        numBombs = 25;
        createBombGrid();
        createCountGrid();
    }
    public Grid(int row, int columns, int numBombs) {
    	
    	if(row >= 0) {
            numRows = row;
        	}else
        		numRows = 10;
        	
        	if(columns >= 0)
            numColumns = columns;
        	else
        		numColumns = 10;
    	
        	bombGrid = new boolean[numRows][numColumns];
            countGrid = new int[numRows][numColumns];
    	
    	
    	if(numBombs <= numRows * numColumns){
    		this.numBombs = numBombs;
    	}else {
    	double b = 0.5 * numRows * numColumns;
    	
    	this.numBombs = (int)b;
    	}
    	
    	
        createBombGrid();
        createCountGrid();
    }
    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public boolean[][] getBombGrid() {
        boolean[][]copyBombGrid = new boolean [numRows][numColumns];
        for(int row = 0; row < numRows;row++) {
        	for(int column = 0; column < numColumns;column++ ) {
        		copyBombGrid[row][column] = bombGrid[row][column];
        	}
        }
        return copyBombGrid;
    }

    public int[][] getCountGrid() {
    	int[][] copyCountGrid = new int [numRows][numColumns];
        for(int row = 0; row < numRows;row++) {
        	for(int column = 0; column < numColumns;column++ ) {
        		copyCountGrid[row] [column]= countGrid[row][column];
        	}
        }
        return copyCountGrid;
    }

    public boolean isBombAtLocation(int row, int column) {
        return bombGrid[row][column];
    }

    public int getCountAtLocation(int row, int column) {
      return countGrid[row][column];
      }

    private void createBombGrid() {
      
    	Random random = new Random();
    	for(int i = 1; i <= numBombs; i++) {
    	
    		int rowNum = random.nextInt(numRows);
    		int columnNum = random.nextInt(numColumns);
    		while(bombGrid[rowNum][columnNum] == true) {
    			 
    			 rowNum = random.nextInt(numRows);
        		 columnNum = random.nextInt(numColumns);
    		}
    		bombGrid[rowNum][columnNum] = true;
    	}
    }

  
    private void createCountGrid() {
    	for(int row = 0; row < bombGrid.length; row++) {
    		for(int column = 0; column < bombGrid[row].length;column++) {
    			if(bombGrid[row][column]) {
    				Bombs(countGrid,row,column);
    			}
    		}
    	}

}

    private void Bombs(int[][] Grid, int Row, int Column) {
    	for(int row = Row -1; row <= Row + 1; row++) {
    		for(int column = Column - 1; column <= Column + 1;column++) {
    			if(row >= 0 && row < numRows && column >= 0 && column < numColumns) {
    				countGrid[row][column]++;
    				}
    			}
    		}
    	}


	public void printGrid() {
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                if (bombGrid[row][column]) {
                    System.out.print(" T ");
                } else {
                    System.out.print(" F ");
                }
            }
            System.out.println(); // Move to the next line after each row
        }
    }
}
class Grid_Gui extends JFrame implements ActionListener {

	//private JCheckBoxMenuItem showFormItem;
    //private Grid grid = new Grid(10, 10, 25);
    private Grid grid = new Grid(10, 10);
    private JButton[][] btnGrid = new JButton[grid.getNumRows()][grid.getNumColumns()];
    private JTextField[][] numBombsGrid = new JTextField[grid.getNumRows()][grid.getNumColumns()];
    private JButton[][] bombsGrid = new JButton[grid.getNumRows()][grid.getNumColumns()];
    static int yesNo = -2;
    int noBombCount = 0;
  
    GridBagConstraints gbc = new GridBagConstraints();
    
    ImageIcon bombImage = new ImageIcon("images/bomb.png");
    ImageIcon bombImgScaled = new ImageIcon(bombImage.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));

    ImageIcon youLost = new ImageIcon("images/you_lost_screen.png");
    ImageIcon youLostScaled = new ImageIcon(youLost.getImage().getScaledInstance(60,60, Image.SCALE_FAST));
    
    ImageIcon youWon = new ImageIcon("images/pngwing.com.png");
    ImageIcon youWonScaled = new ImageIcon(youWon.getImage().getScaledInstance(60, 60, Image.SCALE_FAST));
    
    ImageIcon Grid = new ImageIcon("images/pngegg.png");
    ImageIcon gridScaled = new ImageIcon(Grid.getImage().getScaledInstance(50, 50,Image.SCALE_FAST));
    
    //JFileChooser fileChoser = new JFileChooser();
    
    private JCheckBoxMenuItem showFormItem;
    
    public static void main(String[] args) {
    	
        Grid_Gui vd = new Grid_Gui();
     
        vd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vd.pack();
        vd.setVisible(true);
    }
    
    private JMenuBar createMenuBar() {
    	JMenuBar menuBar = new JMenuBar();
    	
    	JMenu fileMenu = new JMenu("Click Menu");
    	
    	
    	showFormItem = new JCheckBoxMenuItem("Human Form");
    	showFormItem.setSelected(true);
    	
    	fileMenu.add(showFormItem);
    	
    	
    	JMenuItem exitItem = new JMenuItem("EXIT");
    	
    	fileMenu.add(exitItem);
    	
    	menuBar.add(fileMenu);
    	
    	showFormItem.addActionListener(new ActionListener(){

		
			public void actionPerformed(ActionEvent e) {
			JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)e.getSource();
			
			//Grid_Gui gc = new Grid_Gui();
			setVisible(menuItem.isSelected());
			
			 if (menuItem.isSelected()) {
                 int cellSize = 100;

                 for (int row = 0; row < grid.getNumRows(); row++) {
                     for (int column = 0; column < grid.getNumColumns(); column++) {
                    	 
                         gbc.gridx = row;
                         gbc.gridy = column;

                         bombsGrid[row][column] = new JButton(bombImgScaled);
                         bombsGrid[row][column].setVisible(false);
                         bombsGrid[row][column].setPreferredSize(new Dimension(cellSize, cellSize));
                         bombsGrid[row][column].addActionListener(Grid_Gui.this);
                         add(bombsGrid[row][column], gbc);

                         gbc.gridx = row;
                         gbc.gridy = column;
                         numBombsGrid[row][column] = new JTextField();
                         numBombsGrid[row][column].setText(Integer.toString(grid.getCountAtLocation(row, column)));
                         numBombsGrid[row][column].setVisible(false);
                         numBombsGrid[row][column].setPreferredSize(new Dimension(cellSize, cellSize));
                         add(numBombsGrid[row][column], gbc);

                         gbc.gridx = row;
                         gbc.gridy = column;
                         btnGrid[row][column] = new JButton();
                         btnGrid[row][column].setIcon(gridScaled);
                         btnGrid[row][column].addActionListener(Grid_Gui.this);
                         btnGrid[row][column].setPreferredSize(new Dimension(cellSize, cellSize));
                         add(btnGrid[row][column], gbc);
                     }
                 }
			 }
				
			}
    		
    	});
    	
    	fileMenu.setMnemonic(KeyEvent.VK_M);
    	exitItem.setMnemonic(KeyEvent.VK_X);
    	exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
    	
    	exitItem.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Game Over", "You Lost!", DISPOSE_ON_CLOSE, youLostScaled);
				int action = JOptionPane.showConfirmDialog(Grid_Gui.this, 
						"Do you really want to exit the application?",
						"Confirm Exit",JOptionPane.OK_CANCEL_OPTION);
				if(action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
    		
    	});
    	return menuBar;
    }

    public Grid_Gui() {
    	
    	setJMenuBar(createMenuBar());
    	
        setLayout(new GridBagLayout());
        setTitle("Minesweeper");
        setSize(800, 800);
        
        if(showFormItem.isSelected()) {
        	
        
        int cellSize = 50; // Adjust the cell size as needed

       
        
        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int column = 0; column < grid.getNumColumns(); column++) {

            	
                gbc.gridx = row;
                gbc.gridy = column;

               
                bombsGrid[row][column] = new JButton(bombImgScaled);
                bombsGrid[row][column].setVisible(false);
                bombsGrid[row][column].setPreferredSize(new Dimension(cellSize, cellSize));
                bombsGrid[row][column].addActionListener(this);
                add(bombsGrid[row][column], gbc);

                gbc.gridx = row;
                gbc.gridy = column;
                numBombsGrid[row][column] = new JTextField();
                numBombsGrid[row][column].setText(Integer.toString(grid.getCountAtLocation(row, column)));
                numBombsGrid[row][column].setVisible(false);
                numBombsGrid[row][column].setPreferredSize(new Dimension(cellSize, cellSize));
                add(numBombsGrid[row][column], gbc);

                gbc.gridx = row;
                gbc.gridy = column;
                btnGrid[row][column] = new JButton();
                btnGrid[row][column].setIcon(gridScaled);
                btnGrid[row][column].addActionListener(this);
                btnGrid[row][column].setPreferredSize(new Dimension(cellSize, cellSize));
                add(btnGrid[row][column], gbc);
            }
        }
        }
    }

    public void actionPerformed(ActionEvent event) {
        JButton sourceEvent = (JButton) event.getSource();
        sourceEvent.setEnabled(false);

        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int column = 0; column < grid.getNumColumns(); column++) {
                if (btnGrid[row][column] == event.getSource()) {
                    if (grid.isBombAtLocation(row, column)) {
                        revealAllCells();
                  JOptionPane.showMessageDialog(null,  "You Lost!", "Game Over", DISPOSE_ON_CLOSE, youLostScaled);
                        yesNo = JOptionPane.showConfirmDialog(this,
                                "You would like to try to play again?", "Minesweeper",
                                JOptionPane.YES_NO_OPTION);
                        if (yesNo == 0) {
                            resetGame();
                        } else {
                            this.dispose();
                        }
                    } else {
                        ++noBombCount;
                        btnGrid[row][column].setVisible(false);
                        numBombsGrid[row][column].setVisible(true);

                        if (noBombCount == (grid.getNumRows() * grid.getNumColumns()) - grid.getNumBombs()) {
                        	JOptionPane.showMessageDialog(null, "You Won!", "Game Over", DISPOSE_ON_CLOSE, youWonScaled);
                            yesNo = JOptionPane.showConfirmDialog(this,
                                    "You would like to play again", "Game over",
                                    JOptionPane.YES_NO_OPTION);
                        	
                            if (yesNo == 0) {
                                resetGame();
                            } else {
                                this.dispose();
                            }
                        }
                    }
                }
            }
        }
    }

    private void revealAllCells() {
        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int column = 0; column < grid.getNumColumns(); column++) {
                btnGrid[row][column].setVisible(false);
                if (grid.isBombAtLocation(row, column)) {
                    bombsGrid[row][column].setVisible(true);
                    numBombsGrid[row][column].setVisible(false);
                } else {
                    numBombsGrid[row][column].setVisible(true);
                }
            }
        }
    }

    private void resetGame() {
        this.dispose();
        Grid_Gui newFrame = new Grid_Gui();
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.pack();
        newFrame.setVisible(true);
    }
}

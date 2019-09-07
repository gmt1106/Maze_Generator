package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final static int WINDOW_WIDTH = 1000;
    private final static int WINDOW_HEIGHT = 829;

    //BSL = block side length
    //WBSL = BSL + 2 * SPACE
    //NBS = number of block on side
    // level 1 : 15x15 maze
    private final static int LEVEL_1_NBS = 15;
    private final static int LEVEL_1_BSL = 47;
    private final static int LEVEL_1_WBSL = 51;

    // level 2 : 20X20 maze
    private final static int LEVEL_2_NBS = 20;
    private final static int LEVEL_2_BSL = 34;
    private final static int LEVEL_2_WBSL = 38;

    // level 3 : 30X30 maze
    private final static int LEVEL_3_NBS = 30;
    private final static int LEVEL_3_BSL = 21;
    private final static int LEVEL_3_WBSL = 25;

    // level 4 : 40X40 maze
    private final static int LEVEL_4_NBS = 40;
    private final static int LEVEL_4_BSL = 15;
    private final static int LEVEL_4_WBSL = 19;

    //space between blocks
    private final static int SPACE = 2;


    private Maze maze;
    private int level;
    private int NBS;
    private int BSL;
    private int WBSL;
    private String mazeTypeString;
    private boolean mazeBool = false;
    private boolean answerBool = false;
    private boolean defineBool = false;
    private Board board;


    private JButton level2Button;
    private JButton level4Button;
    private JButton level3Button;
    private JButton level1Button;
    private JButton answerButton;

    private JComboBox<String> mazeType;


    public GUI() {


        this.setTitle("Maze Game");
        //set the size of the window
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        //make the window visible
        this.setVisible(true);
        //make the user not able to resize the window
        this.setResizable(false);
        //window will be closed if user click X button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set the background of the window
        board = new Board();
        this.setContentPane(board);
        // deactivate the default layout
        this.setLayout(null);


        //set button on the window
        Level1ButtonAction level1ButtonAction = new Level1ButtonAction();
        level1Button = new JButton("LEVEL 1");
        level1Button.addActionListener(level1ButtonAction);
        level1Button.setBounds(840, 200,  100, 20);
        board.add(level1Button);

        Level2ButtonAction level2ButtonAction = new Level2ButtonAction();
        level2Button = new JButton("LEVEL 2");
        level2Button.addActionListener(level2ButtonAction);
        level2Button.setBounds(840, 300,  100, 20);
        board.add(level2Button);

        Level3ButtonAction level3ButtonAction = new Level3ButtonAction();
        level3Button = new JButton("LEVEL 3");
        level3Button.addActionListener(level3ButtonAction);
        level3Button.setBounds(840, 400,  100, 20);
        board.add(level3Button);

        Level4ButtonAction level4ButtonAction = new Level4ButtonAction();
        level4Button = new JButton("LEVEL 4");
        level4Button.addActionListener(level4ButtonAction);
        level4Button.setBounds(840, 500,  100, 20);
        board.add(level4Button);

        AnswerButtonAction answerButtonAction = new AnswerButtonAction();
        answerButton = new JButton("AnswerKey");
        answerButton.addActionListener(answerButtonAction);
        answerButton.setBounds(840, 600,  100, 20);
        board.add(answerButton);


        //set the maze type combo box on the window
        String[] options = {"DepthFirstSearch Maze", "Kruskal Maze", "Prime Maze"};
        mazeType = new JComboBox<>(options);
        mazeType.setBounds(820, 100, 150, 20);
        board.add(mazeType);
        this.setVisible(true);


    }


    public class Board extends JPanel {


        // draw on the window that is created
        public void paintComponent(Graphics g) {

            if (! mazeBool) {

                g.setColor(Color.DARK_GRAY);
                g.fillRect(0,0,getWidth(),getHeight());

            }
            else {

                g.setColor(Color.DARK_GRAY);
                g.fillRect(0,0,getWidth(),getHeight());

                if (defineBool) {

                    if (mazeTypeString.equals("DepthFirstSearch Maze")) {

                        maze = new DepthFirstSearchMaze(NBS);

                    }
                    else if (mazeTypeString.equals("Kruskal Maze")) {

                        maze = new KruskalMaze(NBS);
                    }
                    else {

                        maze = new PrimMaze(NBS);
                    }

                    defineBool = false;
                }

                drawMaze(g);

            }

        }


        // draw maze by filling the space between connected blocks
        public void drawMaze(Graphics g) {

            if (! answerBool) {

                g.setColor(Color.GRAY);
                for (int i = 0; i < NBS; i++) {
                    for (int j = 0; j < NBS; j++) {
                        g.fillRect(SPACE + i * WBSL, SPACE + j * WBSL, BSL, BSL);
                    }
                }
            }
            else {

                drawAnswer(g);
            }


            //mark the start and end point
            g.setColor(Color.RED);
            g.fillRect(SPACE, SPACE, BSL, BSL);
            g.fillRect(SPACE + (NBS - 1) * WBSL, SPACE + (NBS - 1) * WBSL, BSL, BSL);


            //loop all the blocks exist in the maze by looping all id value of blocks
            for (int i = 0; i < NBS * NBS; i++) {

                Block currentBlock = maze.getBlockByIdNumInMaze(i);

                int x = (i % NBS) * WBSL;
                int y = (i / NBS) * WBSL;

                // check the side of the currentBlock if it is open
                // the max num of possible adjacent block = 4
                for (int j = 0; j < 4; j++) {

                    if (currentBlock.isSideIfOpen(j)) {

                        g.setColor(Color.GRAY);

                        switch (j) {
                            case 0: //up
                                g.fillRect(x + SPACE,          y,               BSL,   SPACE);
                                break;
                            case 1: //right
                                g.fillRect(x + SPACE + BSL, y + SPACE,       SPACE, BSL);
                                break;
                            case 2: //down
                                g.fillRect(x + SPACE,       y + SPACE + BSL, BSL,   SPACE);
                                break;
                            case 3: //left
                                g.fillRect(    x,               y + SPACE,       SPACE, BSL);
                                break;
                            default:
                                break;
                        }

                    }
                }
            }


        }

        // draw answer key of the maze
        public void drawAnswer(Graphics g) {


            //loop all the blocks exist in the maze by looping all id value of blocks
            for (int i = 0; i < NBS * NBS; i++) {

                if (maze.isThisBlockAnswer(i)) {

                    g.setColor(new Color(115, 115, 115));

                }
                else{

                    g.setColor(Color.GRAY);

                }

                int x = (i % NBS) * WBSL;
                int y = (i / NBS) * WBSL;

                g.fillRect(SPACE + x, SPACE + y, BSL, BSL);

            }
        }

    }


    public class Level1ButtonAction implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            level = 1;
            NBS = LEVEL_1_NBS;
            BSL = LEVEL_1_BSL;
            WBSL = LEVEL_1_WBSL;
            checkMazeType();

        }
    }

    public class Level2ButtonAction implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            level = 2;
            NBS = LEVEL_2_NBS;
            BSL = LEVEL_2_BSL;
            WBSL = LEVEL_2_WBSL;
            checkMazeType();

        }
    }


    public class Level3ButtonAction implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            level = 3;
            NBS = LEVEL_3_NBS;
            BSL = LEVEL_3_BSL;
            WBSL = LEVEL_3_WBSL;
            checkMazeType();

        }
    }

    public class Level4ButtonAction implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            level = 4;
            NBS = LEVEL_4_NBS;
            BSL = LEVEL_4_BSL;
            WBSL = LEVEL_4_WBSL;
            checkMazeType();
        }
    }

    private void checkMazeType() {

        mazeBool = true;
        defineBool = true;
        mazeTypeString = (String) mazeType.getSelectedItem();

    }


    public class AnswerButtonAction implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            answerBool = !answerBool;
        }
    }

}

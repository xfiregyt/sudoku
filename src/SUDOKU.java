package sudoku;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 4.0
 */
public class SUDOKU extends JFrame {
    GridLayout gridLayout1 = new GridLayout(9, 9);
    BorderLayout borderLayout = new BorderLayout(5, 3);
    Point[][] cell = null;
    JButton jbRestart = null;
    JPanel jPanel = null;

    // 9个行监视器，一个监视器保证一行内1-9没有重复
    Space[] rowObserver = new Space[9];
    // 9个列监视器，一个监视器保证一列内1-9没有重复
    Space[] colObserver = new Space[9];
    // 9个块监视器，一个监视器保证一块内1-9没有重复
    Space[] bckObserver = new Space[9];
    // 4个特殊块监视器，一个监视器保证一块内1-9没有重复
//    Space[] spcObserver = new Space[4];

    public SUDOKU() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setTitle("SUDOKU -v4.0");

        // Restart
        jbRestart = new JButton("Restart");
        jbRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // TODO 自动生成方法存根
                try {
                    jPanel.removeAll();
                    jPanel = null;
                    jbInit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        jPanel = new JPanel();
        jPanel.setLayout(gridLayout1);

        getContentPane().setLayout(borderLayout);
        getContentPane().add(jbRestart, BorderLayout.NORTH);
        getContentPane().add(jPanel, BorderLayout.CENTER);
        cell = new Point[9][9];

        // 初始化监视器
        for (int i = 0; i < 9; i++) {
            rowObserver[i] = new Space();
            colObserver[i] = new Space();
            bckObserver[i] = new Space();
//            if (i < 4) {
//                spcObserver[i] = new MyObserver();
//            }
        }

        // 初始化 cell，并增加监视器
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cell[i][j] = new Point();
                jPanel.add(cell[i][j]);

                // 增加　行监视器
                cell[i][j].addListener(rowObserver[i]);
//                rowObserver[i].addObserver(cell[i][j]);
//                rowObserver[i].add(cell[i][j]);
                // 增加　列监视器
                cell[i][j].addListener(colObserver[j]);
//                colObserver[j].addObserver(cell[i][j]);
//                colObserver[j].add(cell[i][j]);
            }
        }

        // 增加　块、特殊块监视器
        for (int index = 0; index < bckObserver.length; index++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    // 块监视器
                    if ((i >= 3 * (index / 3) && i < 3 * (index / 3 + 1))
                        &&
                        (j >= 3 * (index % 3) && j < 3 * (index % 3 + 1))) {
                        cell[i][j].addListener(bckObserver[index]);
//                        bckObserver[index].addObserver(cell[i][j]);
//                        bckObserver[index].add(cell[i][j]);
                    }

                    // 特殊块监视器
//                    if (index < 4) {
//                        if ((i >= 4 * (index / 2) + 1 &&
//                             i < 4 * (index / 2 + 1))
//                            &&
//                            (j >= 4 * (index % 2) + 1 &&
//                             j < 4 * (index % 2 + 1))
//                                ) {
//                            cell[i][j].getValue()
//                                    .addObserver(spcObserver[index]);
//                            spcObserver[index].addObserver(cell[i][j]);
////                            System.out.println("bck" + index +
////                                               " contained: "
////                                               + "cell[" + i + "][" + j + "]");
//                        }
//                    }
                }
            }
        }
        this.setLocation(200, 200);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SUDOKU sudoku = new SUDOKU();
    }
}

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

    // 9���м�������һ����������֤һ����1-9û���ظ�
    Space[] rowObserver = new Space[9];
    // 9���м�������һ����������֤һ����1-9û���ظ�
    Space[] colObserver = new Space[9];
    // 9�����������һ����������֤һ����1-9û���ظ�
    Space[] bckObserver = new Space[9];
    // 4��������������һ����������֤һ����1-9û���ظ�
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
                // TODO �Զ����ɷ������
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

        // ��ʼ��������
        for (int i = 0; i < 9; i++) {
            rowObserver[i] = new Space();
            colObserver[i] = new Space();
            bckObserver[i] = new Space();
//            if (i < 4) {
//                spcObserver[i] = new MyObserver();
//            }
        }

        // ��ʼ�� cell�������Ӽ�����
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cell[i][j] = new Point();
                jPanel.add(cell[i][j]);

                // ���ӡ��м�����
                cell[i][j].addListener(rowObserver[i]);
//                rowObserver[i].addObserver(cell[i][j]);
//                rowObserver[i].add(cell[i][j]);
                // ���ӡ��м�����
                cell[i][j].addListener(colObserver[j]);
//                colObserver[j].addObserver(cell[i][j]);
//                colObserver[j].add(cell[i][j]);
            }
        }

        // ���ӡ��顢����������
        for (int index = 0; index < bckObserver.length; index++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    // �������
                    if ((i >= 3 * (index / 3) && i < 3 * (index / 3 + 1))
                        &&
                        (j >= 3 * (index % 3) && j < 3 * (index % 3 + 1))) {
                        cell[i][j].addListener(bckObserver[index]);
//                        bckObserver[index].addObserver(cell[i][j]);
//                        bckObserver[index].add(cell[i][j]);
                    }

                    // ����������
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

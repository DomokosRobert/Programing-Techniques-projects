package View;

import Controller.SimulationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JTextField minService;
    private JTextField nrClients;
    private JTextField simInterval;
    private JTextField minArrival;
    private JButton Start;
    private JTextField maxArrival;
    private JTextField maxService;
    private JTextField nrQueues;
    private JPanel FirstPanel;

    public static JFrame frame;
    public GUI() {
        Start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int intSim= Integer.parseInt(simInterval.getText());
                int intMinServ= Integer.parseInt(minService.getText());
                int intMaxServ= Integer.parseInt(maxService.getText());
                int intMinArr= Integer.parseInt(minArrival.getText());
                int intMaxArr= Integer.parseInt(maxArrival.getText());
                int intNrCl= Integer.parseInt(nrClients.getText());
                int intNrQ= Integer.parseInt(nrQueues.getText());

                frame.setVisible(false);
                SimulationManager sim = new SimulationManager(intSim, intMaxServ, intMinServ,intNrQ,intNrCl,intMinArr,intMaxArr);
                JFrame frame = new JFrame("Queues Simulator");
                SimulationFrame fr = new SimulationFrame();
                frame.setContentPane(fr.getPanel1());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                sim.setFrame(fr);
                Thread t = new Thread(sim);
                t.start();

            }
        });
    }

    public JPanel getFirstPanel() { return FirstPanel; }

}
